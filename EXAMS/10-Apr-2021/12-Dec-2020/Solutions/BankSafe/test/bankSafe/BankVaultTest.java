package bankSafe;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BankVaultTest {
    private BankVault bankVault;
    private Item item;

    private static final String CELL = "A1";

    private static final String OWNER = "Mike";
    private static final String ITEM_ID = "C#DK";

    @Before
    public void setBankVault() {
        this.bankVault = new BankVault();
    }

    @Before
    public void setItem() {
        this.item = new Item(OWNER, ITEM_ID);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void GetVaultCells_ModifiesAValue_ThrowsException() {
        Map<String, Item> vaultCells = this.bankVault.getVaultCells();

        vaultCells.put("NewString", this.item);
    }

    @Test
    public void GetValues_ThreeItems_ReturnsSameValues() throws OperationNotSupportedException {
        Item firstItem = new Item("Stoyan", "F12K");
        Item secondItem = new Item("Finn", "AK47");
        Item thirdItem = new Item("Ricardo", "K19");

        final String FIRST_CELL = "A1";
        final String SECOND_CELL = "B1";
        final String THIRD_CELL = "C1";

        this.bankVault.addItem(FIRST_CELL, firstItem);
        this.bankVault.addItem(SECOND_CELL, secondItem);
        this.bankVault.addItem(THIRD_CELL, thirdItem);

        Map<String, Item> vaultCells = this.bankVault.getVaultCells();

        assertThat(vaultCells.get(FIRST_CELL), is(firstItem));
        assertThat(vaultCells.get(SECOND_CELL), is(secondItem));
        assertThat(vaultCells.get(THIRD_CELL), is(thirdItem));
    }

    @Test(expected = IllegalArgumentException.class)
    public void AddItem_InNonExistentCell_ThrowsException() throws OperationNotSupportedException {
        final String NON_EXISTENT_CELL = "Z5";

        this.bankVault.addItem(NON_EXISTENT_CELL, this.item);
    }

    @Test(expected = IllegalArgumentException.class)
    public void AddItem_TwoItemsIntTheSameCell_ThrowsException() throws OperationNotSupportedException {
        Item firstItem = new Item("Peter", "C43");
        Item secondItem = new Item("Gregor", "EA90");

        this.bankVault.addItem(CELL, firstItem);
        this.bankVault.addItem(CELL, secondItem);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void AddItem_OneItemInTwoDifferentCells_ThrowsException() throws OperationNotSupportedException {
        final String FIRST_CELL = "A1";
        final String SECOND_CELL = "A2";

        this.bankVault.addItem(FIRST_CELL, this.item);
        this.bankVault.addItem(SECOND_CELL, this.item);
    }

    @Test
    public void AddItem_OneItem_ReturnsTheSameItem() throws OperationNotSupportedException {
        this.bankVault.addItem(CELL, this.item);

        Item returnedIdem = this.bankVault.getVaultCells().get(CELL);

        assertThat(returnedIdem, is(this.item));
    }

    @Test
    public void AddItem_OneItem_ReturnsCorrectMessage() throws OperationNotSupportedException {
        String returnedMessage = this.bankVault.addItem(CELL, this.item);

        String expectedMessage = String.format("Item:%s saved successfully!", this.item.getItemId());

        assertThat(returnedMessage, is(expectedMessage));
    }

    @Test(expected = IllegalArgumentException.class)
    public void RemoveItem_NonExistentCell_ThrowsException() throws OperationNotSupportedException {
        final String NON_EXISTENT_CELL = "Z5";

        this.bankVault.addItem("A1", this.item);
        this.bankVault.removeItem(NON_EXISTENT_CELL, this.item);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RemoveItem_InCellThatDoesNotHaveThisItem_ThrowsException() throws OperationNotSupportedException {
        this.bankVault.addItem(CELL, this.item);
        Item differentItem = new Item("Philip", "MGK11");

        this.bankVault.removeItem(CELL, differentItem);
    }

    @Test
    public void RemoveItem_OneItem_SetTheCellWithNull() throws OperationNotSupportedException {
        this.bankVault.addItem(CELL, this.item);
        Item beforeRemove = this.bankVault.getVaultCells().get(CELL);

        this.bankVault.removeItem(CELL, this.item);
        Item afterRemove = this.bankVault.getVaultCells().get(CELL);

        assertNotNull(beforeRemove);
        assertNull(afterRemove);
    }

    @Test
    public void RemoveItem_OneItem_ReturnsCorrectMessage() throws OperationNotSupportedException {
        this.bankVault.addItem(CELL, this.item);

        String expectedMessage = String.format("Remove item:%s successfully!", this.item.getItemId());

        String returnedMessage = this.bankVault.removeItem(CELL, this.item);

        assertEquals(expectedMessage, returnedMessage);
    }
}