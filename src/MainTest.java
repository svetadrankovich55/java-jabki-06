import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void accountTest() {
        Account account = new Account();
        Assertions.assertEquals(0.0, account.getBalance());

        account.deposit(100);
        Assertions.assertEquals(100.0, account.getBalance());

        account.deposit(80);
        Assertions.assertEquals(180.0, account.getBalance());

        account.withdraw(-100);
        Assertions.assertEquals(180.0, account.getBalance());

        account.withdraw(100);
        Assertions.assertEquals(80.0, account.getBalance());

        account.withdraw(80);
        Assertions.assertEquals(0.0, account.getBalance());

        account.withdraw(1);
        Assertions.assertEquals(0.0, account.getBalance());
    }

}