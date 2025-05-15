public class Wallet {

    private String owner;
    private int money;

    public Wallet(String owner, int money) {
        this.owner = owner;
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("Баланс кошелька не может быть отрицательным");
        }
        this.money = money;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        if (owner == null || owner.trim().isEmpty()) {
            throw new IllegalArgumentException("Владелец кошелька не может быть пустым");
        }
        this.owner = owner;
    }

    public void spend(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма списания должна быть должна неотрицательной");
        }
        if (money < amount) {
            throw new IllegalArgumentException("Недостаточно средств для списания");
        }
        money -= amount;
    }
}