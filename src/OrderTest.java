import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    void InitialState() {
        Order order = new Order();

        assertNotNull(order.getOrderId());
        assertTrue(order.getItems().isEmpty());
        assertEquals(0.0, order.getTotalPrice());
    }

    @Test
    void AddOrderItem() {
        Order order = new Order();

        order.addOrderItem("Телевизор", 25000.99);
        order.addOrderItem("Наушники", 4999.50);

        List<String> items = order.getItems();
        assertEquals(2, items.size());
        assertTrue(items.contains("Телевизор"));
        assertTrue(items.contains("Наушники"));
        assertEquals(25000.99 + 4999.50, order.getTotalPrice(), 0.001);
    }

    @Test
    void AddOrderItemWithEmptyName() {
        Order order = new Order();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> order.addOrderItem("", 100.0));
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> order.addOrderItem(null, 100.0));

        assertEquals("Товар не может быть пустым", exception.getMessage());
        assertEquals("Товар не может быть пустым", exception1.getMessage());
    }

    @Test
    void AddOrderItemWithZeroPrice() {
        Order order = new Order();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> order.addOrderItem("Товар", -1));

        assertEquals("Цена товара не может быть отрицательной", exception.getMessage());
    }

    @Test
    void GetItemsReturnsCopy() {
        Order order = new Order();
        order.addOrderItem("Книга", 500.0);
        List<String> items = order.getItems();
        items.add("Попытка изменить копию");

        assertEquals(1, order.getItems().size());
    }

    @Test
    void GetOrderInfoEmpty() {
        Order order = new Order();

        assertTrue(order.getOrderInfo().contains("Номер заказа: ORD-"));
        assertTrue(order.getOrderInfo().contains("Список товаров пуст"));
        assertTrue(order.getOrderInfo().contains("Итоговая сумма: 0,00 руб."));
    }

    @Test
    void GetOrderInfoWithItems() {
        Order order = new Order();
        order.addOrderItem("Мышь", 1500.0);
        order.addOrderItem("Клавиатура", 3500.0);

        assertTrue(order.getOrderInfo().contains("Номер заказа: ORD-"));
        assertTrue(order.getOrderInfo().contains(" - Мышь"));
        assertTrue(order.getOrderInfo().contains(" - Клавиатура"));
        assertTrue(order.getOrderInfo().contains("Итоговая сумма: 5000,00 руб."));
    }

    @Test
    void OrderIdUniqueness() {
        Order order1 = new Order();
        Order order2 = new Order();

        assertNotEquals(order1.getOrderId(), order2.getOrderId());
    }
}