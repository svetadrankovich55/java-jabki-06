import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {

    // Тестирование конструктора + геттеров
    @ParameterizedTest
    @CsvSource({
            "Игорь, 100",
            "Светлана, 0",
            "Bob, 2147483647" // максимальное значение int
    })
    void constructorValidInput(String owner, int money) {
        Wallet wallet = new Wallet(owner, money);

        assertEquals(owner, wallet.getOwner());
        assertEquals(money, wallet.getMoney());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 300})
    void setMoneyValidInput(int amount) {
        Wallet wallet = new Wallet("Алексей", 35);
        wallet.setMoney(amount);

        assertEquals(amount, wallet.getMoney());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -100})
    void setMoneyNegativeInput(int amount) {
        Wallet wallet = new Wallet("Алексей", 35);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> wallet.setMoney(amount));

        assertEquals("Баланс кошелька не может быть отрицательным", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"John", "A", "Соловей-разбойник", "Игорь Игоревич"})
    void setOwnerValidInput(String name) {
        Wallet wallet = new Wallet("Old", 100);
        wallet.setOwner(name);

        assertEquals(name, wallet.getOwner());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  ",})
    void setOwnerNegativeInput(String name) {
        Wallet wallet = new Wallet("Светлана", 100);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> wallet.setOwner(name));

        assertEquals("Владелец кошелька не может быть пустым", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            // initialBalance, spendAmount, expectedBalance
            "100, 30, 70",    // Обычное списание
            "50, 50, 0",      // Списание всей суммы
            "200, 0, 200"     // Списание нуля
    })
    void spendValidAmount(int initialBalance, int spendAmount, int expectedBalance) {
        Wallet wallet = new Wallet("Max", initialBalance);
        wallet.spend(spendAmount);

        assertEquals(expectedBalance, wallet.getMoney());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -100})
    void spendNegativeAmount(int invalidAmount) {
        Wallet wallet = new Wallet("Owner", 100);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> wallet.spend(invalidAmount));

        assertEquals("Сумма списания должна быть должна неотрицательной", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 200})
    void spendMoneyLessAmount(int invalidAmount) {
        Wallet wallet = new Wallet("Owner", 100);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> wallet.spend(invalidAmount));

        assertEquals("Недостаточно средств для списания", exception.getMessage());
    }
}