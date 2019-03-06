import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.stub;

public class AccountTest {

    Auditor auditor;


    @Before
    public void setUp() throws Exception {
        auditor = Mockito.mock(Auditor.class);
        Mockito.stub(auditor.notifywithdraw(Mockito.anyInt())).toReturn(true);

    }

    @Test
    public void testInitialBalance() {
        Account account = new Account(10,null, auditor);
         assertEquals(10, account.addMoney(0));
    }

    @Test
    public void testAddMoney() {
        Account account = new Account(0,null, auditor);
        assertEquals(3, account.addMoney(3));
    }

    @Test
    public void testAdd5And4MoneyToWallet() {
        Account account = new Account(0,null, auditor);
        account.addMoney(5);
        assertEquals(5, account.addMoney(0));
        account.addMoney(4);
        assertEquals(9, account.addMoney(0));
    }

    @Test
    public void withdrawMoneyFromWalletSuccessfully() {
        Account account = new Account(0, null, auditor);
        account.addMoney(100);
        assertEquals(10, account.withdrawMoney(10));
    }

    @Test
    public void withdrawTenFromWalletAndGetBalance() throws Exception, InsufficientFundsException {
        Account account = new Account(0, null, auditor);
        account.addMoney(100);
        account.withdrawMoney(10);
        assertEquals(90, account.addMoney(0));
    }


    @Test
    public void withdraw10000Of100BalanceAccount() throws Exception, InsufficientFundsException {
        Account account = new Account(100,null, auditor);
        assertEquals(10000,account.withdrawMoney(10000));
    }

    @Test
    public void mockEmailWhenAccountIsOverdrawn1000() {
        Owner mockedOwner = Mockito.mock(Owner.class);
        Account account = new Account(10,null, auditor);
        account.setOwner(mockedOwner);
        account.withdrawMoney(20);
        Mockito.verify(mockedOwner).sendEmail(-10);

    }


    @Test
    public void verifyIfAuditorWasNotifiedWith10WhenWithDrawnWas10() {
        Auditor auditor = Mockito.mock(Auditor.class);
        Account account = new Account(10,null,auditor);
        account.withdrawMoney(10);
        Mockito.verify(auditor).notifywithdraw(10);



    }


}

