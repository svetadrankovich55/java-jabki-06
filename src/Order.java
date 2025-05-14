import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    private final String orderId;  // read-only
    private final List<String> items;
    private double totalPrice;

    public Order() {
        this.orderId = generateOrderId();
        this.items = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    // Генерация уникального ID заказа
    //создаёт случайный UUID + преобразует UUID в строку + берёт первые 8 символов из UUID + переводит буквы в верхний регистр
    private String generateOrderId() {
        return "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public String getOrderId() {
        return orderId;
    }

    public List<String> getItems() {
        return new ArrayList<>(items); // Возвращаем копию для защиты от изменений
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void addOrderItem(String item, double price) {
        if (item == null || item.trim().isEmpty()) {
            throw new IllegalArgumentException("Товар не может быть пустым");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Цена товара не может быть отрицательной");
        }
        items.add(item);
        totalPrice += price;
    }

    public String getOrderInfo() {
        StringBuilder info = new StringBuilder();

        // Заголовок с ID заказа
        info.append("Информация по заказу: \n").append("Номер заказа: ").append(orderId).append("\n");

        //Список товаров
        if (items.isEmpty()) {
            info.append("Список товаров пуст\n");
        } else {
            for (String item : items) {
                info.append(" - ").append(item).append("\n");
            }
        }

        //Итоговая сумма
        info.append("Итоговая сумма: ")
                .append(String.format("%.2f", totalPrice)).append(" руб.");

        return info.toString();
    }
}