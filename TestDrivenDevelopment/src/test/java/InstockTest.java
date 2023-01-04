import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class InstockTest {
    private Instock instock;

    @Before
    public void setInstock() {
        this.instock = new Instock();
    }

    @Test
    public void testGetCountShouldReturnZeroWhenEmpty() {
        assertEquals(Integer.valueOf(0), this.instock.getCount());
    }

    @Test
    public void testGetCountShouldReturnTwoWhenTwoProductsAreAdded() {
        addProducts(2);
        assertEquals(Integer.valueOf(2), this.instock.getCount());
    }

    @Test
    public void testContainsShouldReturnFalseWhenTheProductIsNotPresent() {
        Product product = new Product("OrangeJuice", 5.29, 2);
        this.instock.add(product);

        Product wantedProduct = new Product("Oatmeal", 35.99, 9);
        Boolean contains = this.instock.contains(wantedProduct);

        assertNotNull(contains);
        assertFalse(contains);
    }

    @Test
    public void testAddShouldStoreTheProductByValidatingWithContains() {
        Product wantedProduct = new Product("GroundBeef", 57.21, 13);
        this.instock.add(wantedProduct);
        Boolean contains = this.instock.contains(wantedProduct);

        assertNotNull(contains);
        assertTrue(contains);
    }

    @Test
    public void testAddShouldNotAllowAdditionOfTheProductWithThwSameLabel() {
        String label = "IceCram";

        this.instock.add(new Product(label, 1.99, 2));
        this.instock.add(new Product(label, 5.45, 3));

        Integer count = this.instock.getCount();

        assertEquals(Integer.valueOf(1), count);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindWhenTheIndexOutOfBoundsWithNegativeNumber() {
        this.instock.add(new Product("Mozzarella", 16.89, 200));
        this.instock.find(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindWhenTheIndexOutOfBoundWithIndexEqualToTheSize() {
        addProducts(2);
        this.instock.find(2);
    }

    @Test
    public void testFindShouldReturnTheFirstProduct() {
        Product firstProduct = new Product("Salad", 5.66, 236);
        this.instock.add(firstProduct);
        Product secondProduct = new Product("Pizza", 14.57, 8);
        this.instock.add(secondProduct);

        Product foundProduct = this.instock.find(0);

        assertEquals(firstProduct, foundProduct);
    }

    @Test
    public void testFindShouldReturnTheLastProduct() {
        Product firstProduct = new Product("Salad", 5.66, 236);
        this.instock.add(firstProduct);
        Product secondProduct = new Product("Pizza", 14.57, 0);
        this.instock.add(secondProduct);
        Product lastProduct = new Product("Cream", 4.87, 34);
        this.instock.add(lastProduct);

        Product foundProduct = this.instock.find(2);

        assertEquals(lastProduct, foundProduct);
    }

    @Test
    public void testFindShouldReturnCorrectProduct() {
        Product firstProduct = new Product("Salad", 5.66, 236);
        this.instock.add(firstProduct);
        Product secondProduct = new Product("Pizza", 14.57, 8);
        this.instock.add(secondProduct);
        Product lastProduct = new Product("Cream", 4.87, 34);
        this.instock.add(lastProduct);

        Product foundProduct = this.instock.find(1);

        assertEquals(secondProduct, foundProduct);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeQuantityShouldThrowWhenTheProductLabelIsNotInStock() {
        this.instock.add(new Product("Parmesan", 18.32, 153));
        this.instock.changeQuantity("OutOfStock", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeQuantityShouldThrowWhenTheGivenQuantityIsNegative() {
        String label = "Peppers";

        this.instock.add(new Product(label, 14, 19));
        this.instock.changeQuantity(label, -1);
    }

    @Test
    public void testChangeQuantityWithZeroShouldSetQuantityCorrect() {
        String label = "Crisps";

        this.instock.add(new Product(label, 11.2, 9999));

        this.instock.changeQuantity(label, 0);

        Integer actualQuantity = this.instock.find(0).getQuantity();

        assertNotNull(actualQuantity);
        assertEquals(Integer.valueOf(0), actualQuantity);
    }

    @Test
    public void testChangeQuantityShouldSetQuantityCorrect() {
        String label = "Milk";

        this.instock.add(new Product(label, 2.35, 4));

        Integer newQuantity = 167;
        this.instock.changeQuantity(label, newQuantity);

        Integer actualQuantity = this.instock.find(0).getQuantity();

        assertNotNull(actualQuantity);
        assertEquals(newQuantity, actualQuantity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByLabelShouldThrowWhenTheProductLabelInNotInStock() {
        this.instock.add(new Product("Tomatoes", 1.42, 43));
        this.instock.findByLabel("Watermelon");
    }

    @Test
    public void testFindByNameShouldReturnCorrectProduct() {
        addProducts(4);

        String label = "Fanta";

        Product expected = new Product(label, 2.41, 9);
        this.instock.add(expected);

        Product found = this.instock.findByLabel(label);
        assertEquals(expected, found);
    }

    @Test
    public void testFindFirstByAlphabeticalOrderShouldReturnEmptyCollectionWhenTheArgumentIsNegativeNumber() {
        addProducts(2);

        Iterable<Product> productIterable = this.instock.findFirstByAlphabeticalOrder(-1);
        assertNotNull(productIterable);

        List<Product> productList = new ArrayList<>();
        productIterable.forEach(productList::add);

        assertTrue(productList.isEmpty());
    }

    @Test
    public void testFindFirstByAlphabeticalOrderShouldReturnCorrectProducts() {
        List<Product> sortedProducts = addProducts(3);
        sortedProducts.sort(Comparator.comparing(Product::getLabel));

        Iterable<Product> productIterable = this.instock.findFirstByAlphabeticalOrder(3);
        assertNotNull(productIterable);

        List<Product> products = new ArrayList<>();
        productIterable.forEach(products::add);

        assertThat(products, is(sortedProducts));
    }

    @Test
    public void testFindByAlphabeticalOrderShouldReturnAllProductsWhenTheArgumentIsLargerThenTheSize() {
        addProducts(12);

        Iterable<Product> productIterable = this.instock.findFirstByAlphabeticalOrder(9999);
        assertNotNull(productIterable);

        AtomicInteger actualCount = new AtomicInteger();
        productIterable.forEach(p -> actualCount.incrementAndGet());

        assertEquals(0, actualCount.get());
    }

    @Test
    public void testFinaAllInRangeShouldReturnEmptyCollectionWhenThereAreNoSuchProducts() {
        this.instock.add(new Product("Pineapple", 0.99, 9));
        this.instock.add(new Product("Candy", 2.00, 14));

        Iterable<Product> allInRange = this.instock.findAllInRange(1.00, 2.00);
        assertNotNull(allInRange);

        List<Product> productList = new ArrayList<>();
        allInRange.forEach(productList::add);

        assertTrue(productList.isEmpty());
    }

    @Test
    public void testFindAllInRangeShouldReturnCorrectProducts() {
        //Products that don't have to be returned
        List<Product> unwantedProducts = new ArrayList<>();

        Product p1 = new Product("Lasagna", 0.99, 8);
        this.instock.add(p1);
        unwantedProducts.add(p1);

        Product p2 = new Product("CocaCola", 2.00, 11);
        this.instock.add(p2);
        unwantedProducts.add(p2);

        //Products to be returned
        List<Product> foundProducts = new ArrayList<>();
        Product p3 = new Product("Soup", 1.00, 1);
        this.instock.add(p3);
        foundProducts.add(p3);

        Product p4 = new Product("Cake", 1.99, 4);
        this.instock.add(p4);
        foundProducts.add(p4);

        //Assert
        Iterable<Product> allInRange = this.instock.findAllInRange(1.00, 2.00);
        assertNotNull(allInRange);

        List<Product> actual = new ArrayList<>();
        allInRange.forEach(actual::add);

        assertThat(actual, is(foundProducts));
        assertThat(actual, is(not(unwantedProducts)));
    }

    @Test
    public void testFindAllInRangeShouldReturnAllProductsInDescendingOrderOfPrice() {
        List<Product> sortedProducts = new ArrayList<>();

        Product p1 = new Product("Waffle", 2.23, 3);
        this.instock.add(p1);
        sortedProducts.add(p1);

        Product p2 = new Product("Sauce", 21.53, 52);
        this.instock.add(p2);
        sortedProducts.add(p2);

        Product p3 = new Product("Rice", 8.48, 1);
        this.instock.add(p3);
        sortedProducts.add(p3);

        Product p4 = new Product("Curd", 2.24, 988);
        this.instock.add(p4);
        sortedProducts.add(p4);

        sortedProducts.sort(Comparator.comparingDouble(Product::getPrice));

        Iterable<Product> iterable = this.instock.findAllInRange(0, 9999);
        assertNotNull(iterable);

        List<Product> products = new ArrayList<>();
        iterable.forEach(products::add);

        assertThat(products, is(sortedProducts));
    }

    @Test
    public void testFindAllByPriceShouldReturnEmptyCollectionWhenThereAreNoSuchProducts() {
        this.instock.add(new Product("Water", 0.99, 12));
        this.instock.add(new Product("Honey", 1.01, 3));

        Iterable<Product> allByPrice = this.instock.findAllByPrice(1.00);
        assertNotNull(allByPrice);

        List<Product> productList = new ArrayList<>();
        allByPrice.forEach(productList::add);

        assertTrue(productList.isEmpty());
    }

    @Test
    public void testFindAllByPriceShouldReturnAllMatchingProducts() {
        //Products that don't have to be returned
        List<Product> unwantedProducts = new ArrayList<>();

        Product p1 = new Product("Lasagna", 3.24, 8);
        this.instock.add(p1);
        unwantedProducts.add(p1);

        Product p2 = new Product("CocaCola", 3.25, 11);
        this.instock.add(p2);
        unwantedProducts.add(p2);

        //Products to be returned
        List<Product> foundProducts = new ArrayList<>();
        Product p3 = new Product("Soup", 3.26, 1);
        this.instock.add(p3);
        foundProducts.add(p3);

        Product p4 = new Product("Cake", 3.26, 4);
        this.instock.add(p4);
        foundProducts.add(p4);

        //Assert
        Iterable<Product> allByPrice = this.instock.findAllByPrice(3.26);
        assertNotNull(allByPrice);

        List<Product> products = new ArrayList<>();
        allByPrice.forEach(products::add);

        assertThat(foundProducts, is(products));
        assertThat(unwantedProducts, is(not(products)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void findFirstMostExpensiveProductsShouldThrowWhenTheArgumentGreaterThanTheQuantityOfTheStock() {
        addProducts(4);
        this.instock.findFirstMostExpensiveProducts(5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findFirstMostExpensiveProductsShouldThrowWhenTheArgumentIsNegativeNumber() {
        addProducts(2);
        this.instock.findFirstMostExpensiveProducts(-1);
    }

    @Test
    public void testFirstMostExpensiveProductsShouldReturnCorrect() {
        List<Product> expected = addProducts(6);

        expected = expected
                .stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(4)
                .collect(Collectors.toList());

        Iterable<Product> mostExpensiveProducts = this.instock.findFirstMostExpensiveProducts(4);
        assertNotNull(mostExpensiveProducts);

        List<Product> actual = new ArrayList<>();
        mostExpensiveProducts.forEach(actual::add);

        assertThat(actual, is(expected));
    }

    @Test
    public void testFindAllByQuantityShouldReturnEmptyCollectionWhenThereIsNoProductWithIdenticalQuantity() {
        this.instock.add(new Product("Cheese", 1.0, 2));

        Iterable<Product> allByQuantity = this.instock.findAllByQuantity(1);
        assertNotNull(allByQuantity);

        List<Product> products = new ArrayList<>();
        allByQuantity.forEach(products::add);

        assertTrue(products.isEmpty());
    }

    @Test
    public void testFindAllByQuantityShouldReturnCorrectCollection() {
        //Products that don't have to be returned
        List<Product> unwantedProducts = new ArrayList<>();

        Product p1 = new Product("Ruffles", 5.63, 3);
        this.instock.add(p1);
        unwantedProducts.add(p1);

        Product p2 = new Product("Pork", 9.32, 1);
        this.instock.add(p2);
        unwantedProducts.add(p2);

        //Products to be returned
        List<Product> foundProducts = new ArrayList<>();
        Product p3 = new Product("Soup", 12.26, 2);
        this.instock.add(p3);
        foundProducts.add(p3);

        Product p4 = new Product("Cake", 37.86, 2);
        this.instock.add(p4);
        foundProducts.add(p4);

        //Assert
        Iterable<Product> allByQuantity = this.instock.findAllByQuantity(2);
        assertNotNull(allByQuantity);

        List<Product> products = new ArrayList<>();
        allByQuantity.forEach(products::add);

        assertThat(products, is(foundProducts));
        assertThat(products, is(not(unwantedProducts)));
    }

    @Test
    public void testIteratorShouldReturnEmptyCollectionWhenThereAreNoProducts() {
        Iterator<Product> iterator = this.instock.iterator();
        assertNotNull(iterator);

        List<Product> productList = new ArrayList<>();

        iterator.forEachRemaining(productList::add);

        assertTrue(productList.isEmpty());
    }

    @Test
    public void testIteratorShouldReturnAllProducts() {
        List<Product> addedProducts = addProducts(5);

        Iterator<Product> iterator = this.instock.iterator();
        assertNotNull(iterator);

        List<Product> iteratedProducts = new ArrayList<>();
        iterator.forEachRemaining(iteratedProducts::add);

        assertThat(iteratedProducts, is(addedProducts));
    }

    private List<Product> addProducts(int count) {
        List<Product> addedProducts = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Product testProduct = new Product("TestLabelNo_" + i, i + 0.99, i + 1);
            this.instock.add(testProduct);
            addedProducts.add(testProduct);
        }

        return addedProducts;
    }
}