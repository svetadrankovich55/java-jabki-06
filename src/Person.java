public class Person {

    String name;
    Integer age;

    Person() {
        this.name = "Неизвестно";
        this.age = 0;
    }

    Person(String name) {
        this.name = name;
        this.age = 0;
    }

    Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void displayInfo() {
        System.out.printf(
                "Имя: %s, Возраст: %s\n",
                this.name,
                this.age
        );
    }
}
