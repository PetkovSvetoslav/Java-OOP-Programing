import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ChainblockImplTest {

    private ChainblockImpl chainblock;
    private Transaction transaction;

    @Before
    public void setChainblock() {
        this.chainblock = new ChainblockImpl();
    }

    @Before
    public void setFirstTransaction() {
        this.transaction = new TransactionImpl
                (1, TransactionStatus.SUCCESSFUL, "Angel", "Peter", 10.00);
    }

    @Test
    public void Add_SingleTransaction_IncreaseCountOfTransactionsByOne() {
        this.chainblock.add(this.transaction);

        int actualCount = this.chainblock.getCount();

        assertEquals(1, actualCount);
    }

    @Test
    public void Add_SingleTransaction_ReturnsSameTransaction() {
        this.chainblock.add(this.transaction);

        Transaction actualTransaction = this.chainblock.getById(this.transaction.getId());

        assertEquals(this.transaction, actualTransaction);
    }

    @Test
    public void Add_TwoTransactionsWithTheSameId_IncreaseCountOfTransactionsByOne() {
        final int ID = 1;

        createAndAddTransaction(ID, TransactionStatus.SUCCESSFUL, "Angel", "Peter", 10.00);
        createAndAddTransaction(ID, TransactionStatus.FAILED, "Daniel", "James", 11.00);

        int actualCount = this.chainblock.getCount();

        assertEquals(1, actualCount);
    }

    @Test
    public void Add_TwoTransactionsWithTheSameId_ReturnsOnlyTheFirstOne() {
        final int ID = 1;

        Transaction firstTransaction = createAndAddTransaction
                (ID, TransactionStatus.SUCCESSFUL, "Angel", "Peter", 10.00);

        Transaction secondTransaction = createAndAddTransaction
                (ID, TransactionStatus.FAILED, "Daniel", "James", 11.00);

        Transaction actualTransaction = this.chainblock.getById(ID);

        assertEquals(firstTransaction, actualTransaction);
    }

    @Test
    public void Contains_EmptyChainblock_ReturnsFalse() {
        boolean result = this.chainblock.contains(this.transaction);

        assertFalse(result);
    }

    @Test
    public void Contains_AddedTransaction_ReturnsTrue() {
        this.chainblock.add(this.transaction);
        boolean result = this.chainblock.contains(this.transaction);

        assertTrue(result);
    }

    @Test
    public void ContainsId_EmptyChainBlock_ReturnsFalse() {
        boolean result = this.chainblock.contains(this.transaction.getId());

        assertFalse(result);
    }

    @Test
    public void ContainsId_AddedTransaction_ReturnsTrue() {
        this.chainblock.add(this.transaction);
        boolean result = this.chainblock.contains(this.transaction.getId());

        assertTrue(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ChangeTransactionStatus_NonExistentTransaction_ThrowsException() {
        this.chainblock.changeTransactionStatus(1, TransactionStatus.FAILED);
    }

    @Test
    public void ChangeTransactionStatus_SingleTransaction_CorrectStatusChange() {
        final int ID = 1;

        this.chainblock.add(this.transaction);

        final TransactionStatus NEW_STATUS = TransactionStatus.FAILED;
        this.chainblock.changeTransactionStatus(ID, NEW_STATUS);

        TransactionStatus transactionStatus = this.chainblock.getById(ID).getStatus();

        assertEquals(NEW_STATUS, transactionStatus);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RemoveTransactionById_NonExistentTransaction_ThrowsException() {
        this.chainblock.removeTransactionById(1);
    }

    @Test
    public void RemoveTransactionById_TwoAddedTransactions_RemoveTheFirstOne() {
        Transaction firstTransaction = createAndAddTransaction
                (1, TransactionStatus.SUCCESSFUL, "Angel", "Peter", 10.00);

        Transaction secondTransaction = createAndAddTransaction
                (2, TransactionStatus.FAILED, "Daniel", "James", 11.00);

        this.chainblock.removeTransactionById(1);

        assertEquals(1, this.chainblock.getCount());
        assertTrue(this.chainblock.contains(secondTransaction));
        assertFalse(this.chainblock.contains(firstTransaction));
    }

    @Test
    public void RemoveTransactionById_TwoAddedTransactions_RemoveTheSecondOne() {
        Transaction firstTransaction = createAndAddTransaction
                (1, TransactionStatus.SUCCESSFUL, "Angel", "Peter", 10.00);

        Transaction secondTransaction = createAndAddTransaction
                (2, TransactionStatus.FAILED, "Daniel", "James", 11.00);

        this.chainblock.removeTransactionById(2);

        assertEquals(1, this.chainblock.getCount());
        assertTrue(this.chainblock.contains(firstTransaction));
        assertFalse(this.chainblock.contains(secondTransaction));
    }

    @Test(expected = IllegalArgumentException.class)
    public void GetById_NonExistentTransaction_ThrowsException() {
        this.chainblock.getById(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void GetByTransactionStatus_TransactionsWithADifferentStatusesFromTheRequestedOne_ThrowsException() {
        createAndAddTransaction
                (1, TransactionStatus.FAILED, "Ivan", "Morgan", 11.33);

        this.chainblock.getByTransactionStatus(TransactionStatus.SUCCESSFUL);
    }

    @Test
    public void GetByTransactionStatus_ThreeTransactionsWithDifferentStatus_ReturnsTheFirstAndTheLast() {
        final TransactionStatus STATUS = TransactionStatus.ABORTED;

        Transaction firstTransaction = createAndAddTransaction
                (1, STATUS, "Ivan", "Morgan", 1.00);

        Transaction secondTransaction = createAndAddTransaction
                (2, TransactionStatus.FAILED, "Simon", "Nikola", 2.00);

        Transaction thirdTransaction = createAndAddTransaction
                (3, STATUS, "Anna", "Otis", 3.00);

        List<Transaction> expectedTransactions = new ArrayList<>
                (Arrays.asList(firstTransaction, thirdTransaction));

        Iterable<Transaction> actualTransactions =
                this.chainblock.getByTransactionStatus(STATUS);

        assertThat(actualTransactions, is(expectedTransactions));
    }

    @Test
    public void GetByTransactionStatus_ThreeTransactionsWithTheSameStatus_ReturnsTransactionsSortedByAmount() {
        final TransactionStatus STATUS = TransactionStatus.ABORTED;

        Transaction firstTransaction = createAndAddTransaction
                (1, STATUS, "Ivan", "Morgan", 0.99);

        Transaction secondTransaction = createAndAddTransaction
                (2, STATUS, "Simon", "Nikola", 1.01);

        Transaction thirdTransaction = createAndAddTransaction
                (3, STATUS, "Anna", "Otis", 1.00);

        List<Transaction> expectedTransactions = new ArrayList<>
                (Arrays.asList(firstTransaction, secondTransaction, thirdTransaction));

        expectedTransactions.sort(Comparator.comparingDouble(Transaction::getAmount));

        Iterable<Transaction> actualTransactions = this.chainblock.getByTransactionStatus(STATUS);

        assertThat(actualTransactions, is(expectedTransactions));
    }

    @Test(expected = IllegalArgumentException.class)
    public void GetAllSendersWithTransactionStatus_TransactionsWithADifferentStatusesFromTheRequestedOne_ThrowsException() {
        createAndAddTransaction
                (1, TransactionStatus.FAILED, "Ivan", "Morgan", 11.33);

        this.chainblock.getAllSendersWithTransactionStatus(TransactionStatus.SUCCESSFUL);
    }

    @Test
    public void GetAllSendersWithTransactionStatus_FourTransactionsWithTheSameStatusAndOneWithDifferent_ReturnThreeTransactions() {
        final TransactionStatus SUCCESSFUL = TransactionStatus.SUCCESSFUL;
        final String FIRST_SENDER = "Chris";

        createAndAddTransaction(1, SUCCESSFUL, FIRST_SENDER, "Gabi", 40.00);
        createAndAddTransaction(2, SUCCESSFUL, FIRST_SENDER, "Lily", 30.00);
        createAndAddTransaction(3, TransactionStatus.FAILED, FIRST_SENDER, "Finn", 20.00);

        final String SECOND_SENDER = "Mike";
        createAndAddTransaction(4, SUCCESSFUL, SECOND_SENDER, "Angelo", 10.00);

        List<String> expectedSenders = new ArrayList<>(Arrays.asList(FIRST_SENDER, FIRST_SENDER, SECOND_SENDER));

        Iterable<String> senders = this.chainblock.getAllSendersWithTransactionStatus(SUCCESSFUL);

        assertThat(senders, is(expectedSenders));
    }

    @Test
    public void GetAllSendersWithTransactionStatus_AddedFourWithTheSameStatus_ReturnSortedByTransactions() {
        final TransactionStatus ABORTED = TransactionStatus.ABORTED;
        final String FIRST_SENDER = "Chris";
        final String SECOND_SENDER = "Mike";

        //From first sender
        createAndAddTransaction(1, ABORTED, FIRST_SENDER, "Gabi", 10.00);
        //From second sender
        createAndAddTransaction(4, ABORTED, SECOND_SENDER, "Angelo", 30.00);
        createAndAddTransaction(5, ABORTED, SECOND_SENDER, "Natasha", 50.00);
        //From first sender
        createAndAddTransaction(2, ABORTED, FIRST_SENDER, "Lily", 20.00);
        createAndAddTransaction(3, TransactionStatus.UNAUTHORIZED, FIRST_SENDER, "Finn", 40.00);

        List<String> expectedSenders = new ArrayList<>
                (Arrays.asList(SECOND_SENDER, SECOND_SENDER, FIRST_SENDER, FIRST_SENDER));

        Iterable<String> sortedSenders = this.chainblock.getAllSendersWithTransactionStatus(ABORTED);

        assertThat(sortedSenders, is(expectedSenders));
    }

    @Test
    public void GetAllSendersWithTransactionStatus_ThreeSenders_ReturnSortedByTransactions() {
        final TransactionStatus ABORTED = TransactionStatus.ABORTED;
        final String FIRST_SENDER = "Chris";
        final String SECOND_SENDER = "Mike";
        final String THIRD_SENDER = "Katie";

        createAndAddTransaction(1, ABORTED, SECOND_SENDER, "Tony", 30.00);
        createAndAddTransaction(2, ABORTED, FIRST_SENDER, "Frankie", 10.00);
        createAndAddTransaction(3, ABORTED, THIRD_SENDER, "Ellis", 20.00);

        List<String> expectedSenders = new ArrayList<>
                (Arrays.asList(SECOND_SENDER, THIRD_SENDER, FIRST_SENDER));

        Iterable<String> sortedSenders = this.chainblock.getAllSendersWithTransactionStatus(ABORTED);

        assertThat(sortedSenders, is(expectedSenders));
    }

    @Test(expected = IllegalArgumentException.class)
    public void GetAllReceiversWithTransactionStatus_TransactionsWithADifferentStatusesFromTheRequestedOne_ThrowsException() {
        createAndAddTransaction
                (1, TransactionStatus.FAILED, "Ivan", "Morgan", 11.33);

        this.chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.SUCCESSFUL);
    }

    @Test
    public void GetAllReceiversWithTransactionStatus_FourTransactionsWithTheSameStatusAndOneWithDifferent_ReturnThreeTransactions() {
        final TransactionStatus SUCCESSFUL = TransactionStatus.SUCCESSFUL;
        final String FIRST_RECEIVER = "Chris";

        createAndAddTransaction(1, SUCCESSFUL, "Gabi", FIRST_RECEIVER, 40.00);
        createAndAddTransaction(2, SUCCESSFUL, "Lily", FIRST_RECEIVER, 30.00);
        createAndAddTransaction(3, TransactionStatus.FAILED, "Finn", FIRST_RECEIVER, 20.00);

        final String SECOND_RECEIVER = "Mike";
        createAndAddTransaction(4, SUCCESSFUL, "Angelo", SECOND_RECEIVER, 10.00);

        List<String> expectedReceivers = new ArrayList<>(Arrays.asList(FIRST_RECEIVER, FIRST_RECEIVER, SECOND_RECEIVER));

        Iterable<String> receivers = this.chainblock.getAllReceiversWithTransactionStatus(SUCCESSFUL);

        assertThat(receivers, is(expectedReceivers));
    }

    @Test
    public void GetAllReceiversWithTransactionStatus_AddedFourWithTheSameStatus_ReturnSortedByTransactions() {
        final TransactionStatus ABORTED = TransactionStatus.ABORTED;
        final String FIRST_RECEIVER = "Chris";
        final String SECOND_RECEIVER = "Mike";

        //From first receiver
        createAndAddTransaction(1, ABORTED, "Gabi", FIRST_RECEIVER, 10.00);
        //From second receiver
        createAndAddTransaction(4, ABORTED, "Angelo", SECOND_RECEIVER, 30.00);
        createAndAddTransaction(5, ABORTED, "Natasha", SECOND_RECEIVER, 50.00);
        //From first receiver
        createAndAddTransaction(2, ABORTED, "Lily", FIRST_RECEIVER, 20.00);
        createAndAddTransaction(3, TransactionStatus.UNAUTHORIZED, "Finn", FIRST_RECEIVER, 40.00);

        List<String> expectedReceiver = new ArrayList<>
                (Arrays.asList(SECOND_RECEIVER, SECOND_RECEIVER, FIRST_RECEIVER, FIRST_RECEIVER));

        Iterable<String> sortedReceivers = this.chainblock.getAllReceiversWithTransactionStatus(ABORTED);

        assertThat(sortedReceivers, is(expectedReceiver));
    }

    @Test
    public void GetAllReceiversWithTransactionStatus_ThreeSenders_ReturnSortedByTransactions() {
        final TransactionStatus ABORTED = TransactionStatus.ABORTED;
        final String FIRST_RECEIVER = "Chris";
        final String SECOND_RECEIVER = "Mike";
        final String THIRD_RECEIVER = "Katie";

        createAndAddTransaction(1, ABORTED, "Tony", SECOND_RECEIVER, 30.00);
        createAndAddTransaction(2, ABORTED, "Frankie", FIRST_RECEIVER, 10.00);
        createAndAddTransaction(3, ABORTED, "Ellis", THIRD_RECEIVER, 20.00);

        List<String> expectedReceivers = new ArrayList<>
                (Arrays.asList(SECOND_RECEIVER, THIRD_RECEIVER, FIRST_RECEIVER));

        Iterable<String> sortedReceivers = this.chainblock.getAllReceiversWithTransactionStatus(ABORTED);

        assertThat(sortedReceivers, is(expectedReceivers));
    }

    @Test
    public void GetAllOrderedByAmountDescendingThenById_EmptyChainblock_ReturnsEmptyCollection() {
        Iterable<Transaction> transactions = this.chainblock.getAllOrderedByAmountDescendingThenById();
        assertNotNull(transactions);

        List<Transaction> transactionList = convertIntoAList(transactions);

        assertTrue(transactionList.isEmpty());
    }

    @Test
    public void GetAllOrderedByAmountDescendingThenById_FourTransactions_ReturnsTransactionsSortedByAmount() {
        TransactionStatus STATUS = TransactionStatus.SUCCESSFUL;

        Transaction firstTransaction = createAndAddTransaction(1, STATUS, "From_1", "To_1", 0.01);
        Transaction secondTransaction = createAndAddTransaction(2, STATUS, "From_2", "To_2", 0.03);
        Transaction thirdTransaction = createAndAddTransaction(3, STATUS, "From_3", "To_3", 0.04);
        Transaction fourthTransaction = createAndAddTransaction(4, STATUS, "From_4", "To_4", 0.02);

        List<Transaction> sortedByAmount = new ArrayList<>
                (Arrays.asList(thirdTransaction, secondTransaction, fourthTransaction, firstTransaction));

        Iterable<Transaction> orderedTransactions = this.chainblock.getAllOrderedByAmountDescendingThenById();

        assertThat(orderedTransactions, is(sortedByAmount));
    }

    @Test
    public void GetAllOrderedByAmountDescendingThenById_FourTransactions_ReturnsTransactionsSortedByID() {
        TransactionStatus STATUS = TransactionStatus.SUCCESSFUL;

        Transaction firstTransaction = createAndAddTransaction(2, STATUS, "From_1", "To_1", 1.00);
        Transaction secondTransaction = createAndAddTransaction(3, STATUS, "From_2", "To_2", 1.00);
        Transaction thirdTransaction = createAndAddTransaction(4, STATUS, "From_3", "To_3", 1.00);
        Transaction fourthTransaction = createAndAddTransaction(1, STATUS, "From_4", "To_4", 1.00);

        List<Transaction> sortedByID = new ArrayList<>
                (Arrays.asList(fourthTransaction, firstTransaction, secondTransaction, thirdTransaction));

        Iterable<Transaction> orderedTransactions = this.chainblock.getAllOrderedByAmountDescendingThenById();

        assertThat(orderedTransactions, is(sortedByID));
    }

    @Test
    public void GetAllOrderedByAmountDescendingThenById_FourTransactions_ReturnsTransactionsSortedByAmountThenById() {
        TransactionStatus STATUS = TransactionStatus.SUCCESSFUL;

        Transaction firstTransaction = createAndAddTransaction(3, STATUS, "From_1", "To_1", 0.99);
        Transaction secondTransaction = createAndAddTransaction(4, STATUS, "From_2", "To_2", 1.00);
        Transaction thirdTransaction = createAndAddTransaction(1, STATUS, "From_3", "To_3", 0.99);
        Transaction fourthTransaction = createAndAddTransaction(2, STATUS, "From_4", "To_4", 1.01);

        List<Transaction> sorted = new ArrayList<>
                (Arrays.asList(fourthTransaction, secondTransaction, thirdTransaction, firstTransaction));

        Iterable<Transaction> orderedTransactions = this.chainblock.getAllOrderedByAmountDescendingThenById();

        assertThat(orderedTransactions, is(sorted));
    }

    @Test(expected = IllegalArgumentException.class)
    public void GetBySenderOrderedByAmountDescending_SendersOtherThanTheRequestedOne_ThrowsException() {
        final String TRANSACTION_SENDER = "Zack";
        createAndAddTransaction(1, TransactionStatus.FAILED, TRANSACTION_SENDER, "Peter", 1.00);

        final String SEARCH_NAME = "Zelda";
        this.chainblock.getBySenderOrderedByAmountDescending(SEARCH_NAME);
    }

    @Test
    public void GetBySenderOrderedByAmountDescending_WithDifferentSenders_ReturnsOnlyTheCorrectOnes() {
        final String SENDER = "Zack";

        Transaction firstTransaction = createAndAddTransaction(1, TransactionStatus.UNAUTHORIZED, "Jimmy", "Bella", 999.99);
        Transaction secondTransaction = createAndAddTransaction(2, TransactionStatus.FAILED, SENDER, "Pepe", 888.88);
        Transaction thirdTransaction = createAndAddTransaction(3, TransactionStatus.ABORTED, "Ace", "Luke", 777.77);
        Transaction fourthTransaction = createAndAddTransaction(4, TransactionStatus.ABORTED, SENDER, "Apollo", 666.66);
        Transaction fifthTransaction = createAndAddTransaction(5, TransactionStatus.FAILED, SENDER, "Ted", 555.55);

        List<Transaction> expectedTransactions = new ArrayList<>
                (Arrays.asList(secondTransaction, fourthTransaction, fifthTransaction));

        Iterable<Transaction> transactions = this.chainblock.getBySenderOrderedByAmountDescending(SENDER);

        assertThat(transactions, is(expectedTransactions));
    }

    @Test
    public void GetBySenderOrderedByAmountDescending_WithDifferentSenders_ReturnsSortedTransactions() {
        final String SENDER = "Zack";

        Transaction firstTransaction = createAndAddTransaction(1, TransactionStatus.SUCCESSFUL, SENDER, "Peter", 1.02);
        Transaction secondTransaction = createAndAddTransaction(2, TransactionStatus.UNAUTHORIZED, "Emil", "Zian", 1.03);
        Transaction thirdTransaction = createAndAddTransaction(3, TransactionStatus.ABORTED, SENDER, "Hans", 1.04);
        Transaction fourthTransaction = createAndAddTransaction(4, TransactionStatus.SUCCESSFUL, SENDER, "Cody", 1.01);
        Transaction fifthTransaction = createAndAddTransaction(5, TransactionStatus.FAILED, "Tony", "Paul", 1.05);

        List<Transaction> expectedTransactions = new ArrayList<>
                (Arrays.asList(thirdTransaction, firstTransaction, fourthTransaction));

        Iterable<Transaction> orderedTransactions = this.chainblock.getBySenderOrderedByAmountDescending(SENDER);

        assertThat(orderedTransactions, is(expectedTransactions));
    }

    @Test(expected = IllegalArgumentException.class)
    public void GetByReceiverOrderedByAmountThenById_ReceiversOtherThanTheRequestedOne_ThrowsException() {
        final String TRANSACTION_RECEIVER = "Leo";
        createAndAddTransaction(1, TransactionStatus.FAILED, "Plamen", TRANSACTION_RECEIVER, 1.00);

        final String SEARCH_NAME = "Felix";
        this.chainblock.getByReceiverOrderedByAmountThenById(SEARCH_NAME);
    }

    @Test
    public void GetByReceiversOrderedByAmountThanById_WithDifferentReceivers_ReturnsOnlyTheCorrectOnes() {
        final String RECEIVER = "Laura";
        final double AMOUNT = 999.99;

        Transaction firstTransaction = createAndAddTransaction(1, TransactionStatus.UNAUTHORIZED, "Jimmy", RECEIVER, AMOUNT);
        Transaction secondTransaction = createAndAddTransaction(2, TransactionStatus.FAILED, "Pepe", "Bella", AMOUNT);
        Transaction thirdTransaction = createAndAddTransaction(3, TransactionStatus.ABORTED, "Ace", RECEIVER, AMOUNT);
        Transaction fourthTransaction = createAndAddTransaction(4, TransactionStatus.ABORTED, "Luke", RECEIVER, AMOUNT);
        Transaction fifthTransaction = createAndAddTransaction(5, TransactionStatus.FAILED, "Barbara", "Apollo", AMOUNT);

        List<Transaction> expectedTransactions = new ArrayList<>
                (Arrays.asList(firstTransaction, thirdTransaction, fourthTransaction));

        Iterable<Transaction> transactions = this.chainblock.getByReceiverOrderedByAmountThenById(RECEIVER);

        assertThat(transactions, is(expectedTransactions));
    }

    @Test
    public void GetByReceiverOrderedByAmountThenById_WithOneSender_ReturnsSortedByAmountTransactions() {
        final String RECEIVER = "Larry";

        Transaction firstTransaction = createAndAddTransaction(1, TransactionStatus.SUCCESSFUL, "Stefan", RECEIVER, 1.05);
        Transaction secondTransaction = createAndAddTransaction(2, TransactionStatus.UNAUTHORIZED, "Stella", RECEIVER, 1.03);
        Transaction thirdTransaction = createAndAddTransaction(3, TransactionStatus.ABORTED, "Nikol", RECEIVER, 1.04);
        Transaction fourthTransaction = createAndAddTransaction(4, TransactionStatus.SUCCESSFUL, "Esteban", RECEIVER, 1.01);
        Transaction fifthTransaction = createAndAddTransaction(5, TransactionStatus.FAILED, "Boris", RECEIVER, 1.02);

        List<Transaction> expectedlyArranged = new ArrayList<>
                (Arrays.asList(firstTransaction, thirdTransaction, secondTransaction, fifthTransaction, fourthTransaction));

        Iterable<Transaction> orderedTransactions = this.chainblock.getByReceiverOrderedByAmountThenById(RECEIVER);

        assertThat(orderedTransactions, is(expectedlyArranged));
    }

    @Test
    public void GetByReceiverOrderedByAmountThenById_WithOneSender_ReturnsSortedByIdTransactions() {
        final String RECEIVER = "Vladimir";
        final double AMOUNT = 999.99;

        Transaction firstTransaction = createAndAddTransaction(5, TransactionStatus.SUCCESSFUL, "Emilia", RECEIVER, AMOUNT);
        Transaction secondTransaction = createAndAddTransaction(1, TransactionStatus.UNAUTHORIZED, "Milen", RECEIVER, AMOUNT);
        Transaction thirdTransaction = createAndAddTransaction(2, TransactionStatus.ABORTED, "Emil", RECEIVER, AMOUNT);
        Transaction fourthTransaction = createAndAddTransaction(4, TransactionStatus.SUCCESSFUL, "Stan", RECEIVER, AMOUNT);
        Transaction fifthTransaction = createAndAddTransaction(3, TransactionStatus.FAILED, "Austin", RECEIVER, AMOUNT);

        List<Transaction> expectedlyArranged = new ArrayList<>
                (Arrays.asList(secondTransaction, thirdTransaction, fifthTransaction, fourthTransaction, firstTransaction));

        Iterable<Transaction> orderedTransactions = this.chainblock.getByReceiverOrderedByAmountThenById(RECEIVER);

        assertThat(orderedTransactions, is(expectedlyArranged));
    }

    @Test
    public void GetByTransactionStatusAndMaximumAmount_StatusesOtherThanTheRequestedOne_ReturnsEmptyCollection() {
        final double CURRENT_AMOUNT = 1.00;

        createAndAddTransaction(1, TransactionStatus.UNAUTHORIZED, "From", "To", CURRENT_AMOUNT);

        final TransactionStatus REQUIRED_STATUS = TransactionStatus.SUCCESSFUL;
        final double MAXIMUM_AMOUNT = 999.99;

        Iterable<Transaction> transactionIterable = this.chainblock.getByTransactionStatusAndMaximumAmount(REQUIRED_STATUS, MAXIMUM_AMOUNT);
        assertNotNull(transactionIterable);

        List<Transaction> transactionList = convertIntoAList(transactionIterable);

        assertTrue(transactionList.isEmpty());
    }

    @Test
    public void GetByTransactionStatusAndMaximumAmount_AmountsGreaterThanTheMaximum_ReturnsEmptyCollection() {
        final TransactionStatus STATUS = TransactionStatus.SUCCESSFUL;
        final double MAXIMUM_AMOUNT = 999.99;

        createAndAddTransaction(1, STATUS, "From", "To", 1000.00);

        Iterable<Transaction> transactionIterable = this.chainblock.getByTransactionStatusAndMaximumAmount(STATUS, MAXIMUM_AMOUNT);
        assertNotNull(transactionIterable);

        List<Transaction> transactionList = convertIntoAList(transactionIterable);

        assertTrue(transactionList.isEmpty());
    }

    @Test
    public void GetByTransactionStatusAndMaximumAmount_WithTheRequiredData_ReturnsSortedByAmountTransactions() {
        final TransactionStatus STATUS = TransactionStatus.FAILED;
        final double MAXIMUM_AMOUNT = 999.99;

        Transaction firstTransaction = createAndAddTransaction(1, STATUS, "Emilia", "Donatello", 999.99);
        Transaction secondTransaction = createAndAddTransaction(2, STATUS, "Milen", "Angelina", 998.99);
        Transaction thirdTransaction = createAndAddTransaction(3, STATUS, "Emil", "Alan", 999.00);
        Transaction fourthTransaction = createAndAddTransaction(4, STATUS, "Stan", "Robert", 998.00);
        Transaction fifthTransaction = createAndAddTransaction(5, STATUS, "Austin", "Samuel", 999.98);

        List<Transaction> expectedlyArranged = new ArrayList<>
                (Arrays.asList(firstTransaction, fifthTransaction, thirdTransaction, secondTransaction, fourthTransaction));

        Iterable<Transaction> orderedTransactions = this.chainblock.getByTransactionStatusAndMaximumAmount(STATUS, MAXIMUM_AMOUNT);

        assertThat(orderedTransactions, is(expectedlyArranged));
    }

    @Test
    public void GetByTransactionStatusAndMaximumAmount_WithRequiredData_ReturnsSortedByIdTransactions() {
        final TransactionStatus STATUS = TransactionStatus.FAILED;
        final double MAXIMUM_AMOUNT = 999.99;

        Transaction firstTransaction = createAndAddTransaction(4, STATUS, "Emilia", "Donatello", MAXIMUM_AMOUNT);
        Transaction secondTransaction = createAndAddTransaction(1, STATUS, "Milen", "Angelina", MAXIMUM_AMOUNT);
        Transaction thirdTransaction = createAndAddTransaction(5, STATUS, "Emil", "Alan", MAXIMUM_AMOUNT);
        Transaction fourthTransaction = createAndAddTransaction(2, STATUS, "Stan", "Robert", MAXIMUM_AMOUNT);
        Transaction fifthTransaction = createAndAddTransaction(3, STATUS, "Austin", "Samuel", MAXIMUM_AMOUNT);

        List<Transaction> expectedlyArranged = new ArrayList<>
                (Arrays.asList(secondTransaction, fourthTransaction, fifthTransaction, firstTransaction, thirdTransaction));

        Iterable<Transaction> orderedTransactions = this.chainblock.getByTransactionStatusAndMaximumAmount(STATUS, MAXIMUM_AMOUNT);

        assertThat(orderedTransactions, is(expectedlyArranged));
    }

    @Test(expected = IllegalArgumentException.class)
    public void GetBySenderAndMinimumAmountDescending_SenderOtherThanTheRequiredOne_ThrowsException() {
        final String REQUIRED_SENDER = "Gary";
        final double MINIMUM_AMOUNT = 1.00;

        createAndAddTransaction(1, TransactionStatus.SUCCESSFUL, "Michael", "Robin", 999.99);

        this.chainblock.getBySenderAndMinimumAmountDescending(REQUIRED_SENDER, MINIMUM_AMOUNT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void GetBySenderAndMinimumAmountDescending_AmountLessThanOrEqualToRequiredAmount_ThrowsException() {
        final String REQUIRED_SENDER = "Steve";
        final double MINIMUM_AMOUNT = 999.99;

        createAndAddTransaction(1, TransactionStatus.FAILED, REQUIRED_SENDER, "Philip", 1.00);
        createAndAddTransaction(2, TransactionStatus.ABORTED, REQUIRED_SENDER, "Ian", MINIMUM_AMOUNT - 0.01);
        createAndAddTransaction(3, TransactionStatus.SUCCESSFUL, REQUIRED_SENDER, "Robin", MINIMUM_AMOUNT);

        this.chainblock.getBySenderAndMinimumAmountDescending(REQUIRED_SENDER, MINIMUM_AMOUNT);
    }

    @Test
    public void GetBySenderAndMinimumAmountDescending_WithTheRequiredData_ReturnsSortedByAmountTransactions() {
        final String SENDER = "Brad";
        final double MINIMUM_AMOUNT = 0.99;

        Transaction firstTransaction = createAndAddTransaction(1, TransactionStatus.SUCCESSFUL, SENDER, "Donatello", 1.00);
        Transaction secondTransaction = createAndAddTransaction(2, TransactionStatus.SUCCESSFUL, SENDER, "Angelina", 9.99);
        Transaction thirdTransaction = createAndAddTransaction(3, TransactionStatus.ABORTED, SENDER, "Alan", 1.99);
        Transaction fourthTransaction = createAndAddTransaction(4, TransactionStatus.UNAUTHORIZED, SENDER, "Robert", 10.00);
        Transaction fifthTransaction = createAndAddTransaction(5, TransactionStatus.FAILED, SENDER, "Samuel", 1.01);

        List<Transaction> expectedlyArranged = new ArrayList<>
                (Arrays.asList(fourthTransaction, secondTransaction, thirdTransaction, fifthTransaction, firstTransaction));

        Iterable<Transaction> orderedTransactions = this.chainblock.getBySenderAndMinimumAmountDescending(SENDER, MINIMUM_AMOUNT);

        assertThat(orderedTransactions, is(expectedlyArranged));
    }

    @Test
    public void GetBySenderAndMinimumAmountDescending_WithTheRequiredData_ReturnsSortedByIdTransactions() {
        final String SENDER = "Brad";
        final double MINIMUM_AMOUNT = 0.99;
        final double AMOUNT = MINIMUM_AMOUNT + 0.01;

        Transaction firstTransaction = createAndAddTransaction(1, TransactionStatus.SUCCESSFUL, SENDER, "Donatello", AMOUNT);
        Transaction secondTransaction = createAndAddTransaction(5, TransactionStatus.SUCCESSFUL, SENDER, "Angelina", AMOUNT);
        Transaction thirdTransaction = createAndAddTransaction(4, TransactionStatus.ABORTED, SENDER, "Alan", AMOUNT);
        Transaction fourthTransaction = createAndAddTransaction(3, TransactionStatus.UNAUTHORIZED, SENDER, "Robert", AMOUNT);
        Transaction fifthTransaction = createAndAddTransaction(2, TransactionStatus.FAILED, SENDER, "Samuel", AMOUNT);

        List<Transaction> expectedlyArranged = new ArrayList<>
                (Arrays.asList(firstTransaction, fifthTransaction, fourthTransaction, thirdTransaction, secondTransaction));

        Iterable<Transaction> orderedTransactions = this.chainblock.getBySenderAndMinimumAmountDescending(SENDER, MINIMUM_AMOUNT);

        assertThat(orderedTransactions, is(expectedlyArranged));
    }

    @Test(expected = IllegalArgumentException.class)
    public void GetByReceiverAndAmountRange_ReceiverOtherThanTheRequiredOne_ThrowsException() {
        final String REQUIRED_RECEIVER = "Henry";
        final double LOWER_BOUND = 0.99;
        final double UPPER_BOUND = 1.00;

        createAndAddTransaction(1, TransactionStatus.SUCCESSFUL, "Michael", "Robin", LOWER_BOUND);

        this.chainblock.getByReceiverAndAmountRange(REQUIRED_RECEIVER, LOWER_BOUND, UPPER_BOUND);
    }

    @Test(expected = IllegalArgumentException.class)
    public void GetByReceiverAndAmountRange_AmountsOutOfRange_ThrowsException() {
        final String REQUIRED_RECEIVER = "Henry";
        final double LOWER_BOUND = 1.00;
        final double UPPER_BOUND = 10.00;

        createAndAddTransaction(1, TransactionStatus.SUCCESSFUL, "Michael", REQUIRED_RECEIVER, 0.99);
        createAndAddTransaction(2, TransactionStatus.ABORTED, "Michael", REQUIRED_RECEIVER, 10.00);
        createAndAddTransaction(3, TransactionStatus.SUCCESSFUL, "Michael", REQUIRED_RECEIVER, 0.01);

        this.chainblock.getByReceiverAndAmountRange(REQUIRED_RECEIVER, LOWER_BOUND, UPPER_BOUND);
    }

    @Test
    public void GetByReceiverAndAmountRange_WithTheRequiredData_ReturnsSortedByAmountTransactions() {
        final String RECEIVER = "Clark";
        final double LOWER_BOUND = 1.00;
        final double UPPER_BOUND = 10.00;

        Transaction firstTransaction = createAndAddTransaction(1, TransactionStatus.SUCCESSFUL, "Donatello", RECEIVER, 1.01);
        Transaction secondTransaction = createAndAddTransaction(2, TransactionStatus.SUCCESSFUL, "Angelina", RECEIVER, 9.99);
        Transaction thirdTransaction = createAndAddTransaction(3, TransactionStatus.ABORTED, "Alan", RECEIVER, 1.00);
        Transaction fourthTransaction = createAndAddTransaction(4, TransactionStatus.UNAUTHORIZED, "Robert", RECEIVER, 6.00);
        Transaction fifthTransaction = createAndAddTransaction(5, TransactionStatus.FAILED, "Samuel", RECEIVER, 5.99);

        List<Transaction> expectedlyArranged = new ArrayList<>
                (Arrays.asList(secondTransaction, fourthTransaction, fifthTransaction, firstTransaction, thirdTransaction));

        Iterable<Transaction> orderedTransactions = this.chainblock.getByReceiverAndAmountRange(RECEIVER, LOWER_BOUND, UPPER_BOUND);

        assertThat(orderedTransactions, is(expectedlyArranged));
    }

    @Test
    public void GetByReceiverAndAmountRange_WithTheRequiredData_ReturnsSortedByIdTransactions() {
        final String RECEIVER = "Clark";
        final double LOWER_BOUND = 1.00;
        final double UPPER_BOUND = 10.00;

        Transaction firstTransaction = createAndAddTransaction(5, TransactionStatus.SUCCESSFUL, "Donatello", RECEIVER, LOWER_BOUND);
        Transaction secondTransaction = createAndAddTransaction(2, TransactionStatus.SUCCESSFUL, "Angelina", RECEIVER, LOWER_BOUND);
        Transaction thirdTransaction = createAndAddTransaction(4, TransactionStatus.ABORTED, "Alan", RECEIVER, LOWER_BOUND);
        Transaction fourthTransaction = createAndAddTransaction(1, TransactionStatus.UNAUTHORIZED, "Robert", RECEIVER, LOWER_BOUND);
        Transaction fifthTransaction = createAndAddTransaction(3, TransactionStatus.FAILED, "Samuel", RECEIVER, LOWER_BOUND);

        List<Transaction> expectedlyArranged = new ArrayList<>
                (Arrays.asList(fourthTransaction, secondTransaction, fifthTransaction, thirdTransaction, firstTransaction));

        Iterable<Transaction> orderedTransactions = this.chainblock.getByReceiverAndAmountRange(RECEIVER, LOWER_BOUND, UPPER_BOUND);

        assertThat(orderedTransactions, is(expectedlyArranged));
    }

    @Test
    public void GetAllInAmountRange_AmountsOutOfRange_ReturnsEmptyCollection() {
        createAndAddTransaction(1, TransactionStatus.SUCCESSFUL, "Tony", "Ivan", 0.99);
        createAndAddTransaction(2, TransactionStatus.ABORTED, "Daniel", "Ellis", 2.00);

        final double LOWER_BOUND = 1.00;
        final double UPPER_BOUND = 2.00;

        Iterable<Transaction> allInRange = this.chainblock.getAllInAmountRange(LOWER_BOUND, UPPER_BOUND);

        List<Transaction> transactionList = convertIntoAList(allInRange);

        assertTrue(transactionList.isEmpty());
    }

    @Test
    public void GetAllInAmountRange_AmountsInTheRange_ReturnsInTheOrderOfAddition() {
        final double LOWER_BOUND = 1.00;
        final double UPPER_BOUND = 2.00;

        Transaction firstTransaction = createAndAddTransaction(3, TransactionStatus.ABORTED, "Stella", "Nikol", 1.99);
        Transaction secondTransaction = createAndAddTransaction(4, TransactionStatus.FAILED, "Esteban", "Jack", 1.00);
        Transaction thirdTransaction = createAndAddTransaction(1, TransactionStatus.ABORTED, "Tommy", "Gary", 1.59);
        Transaction fourthTransaction = createAndAddTransaction(2, TransactionStatus.SUCCESSFUL, "Xander", "Sean", 1.99);
        Transaction fifthTransaction = createAndAddTransaction(5, TransactionStatus.UNAUTHORIZED, "Steven", "Liam", 1.01);

        List<Transaction> expectedOrder = new ArrayList<>
                (Arrays.asList(firstTransaction, secondTransaction, thirdTransaction, fourthTransaction, fifthTransaction));

        Iterable<Transaction> allInRange = this.chainblock.getAllInAmountRange(LOWER_BOUND, UPPER_BOUND);

        assertThat(allInRange, is(expectedOrder));
    }

    private Transaction createAndAddTransaction(int id, TransactionStatus status, String from, String to, double amount) {
        Transaction transaction = new TransactionImpl(id, status, from, to, amount);
        this.chainblock.add(transaction);

        return transaction;
    }

    private static <T> List<T> convertIntoAList(Iterable<T> iterable) {
        List<T> transactionList = new ArrayList<>();
        iterable.forEach(transactionList::add);

        return transactionList;
    }
}
