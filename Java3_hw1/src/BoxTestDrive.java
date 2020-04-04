
public class BoxTestDrive {
    public static void main(String[] args) {
        // создаем коробки под яблоки
        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        // наполняем коробки яблоками
        appleBox1.addFruit(new Apple(), 5);
        appleBox2.addFruit(new Apple(), 30);
        // создаем коробки под апельсины
        Box<Orange> orangeBox1 = new Box<>();
        Box<Orange> orangeBox2 = new Box<>();
        // наполняем коробки апельсинами
        orangeBox1.addFruit(new Orange(), 20);
        orangeBox2.addFruit(new Orange(), 20);
        // выводим вес коробок
        System.out.println("Вес appleBox1 = " + appleBox1.getWeight());
        System.out.println("Вес appleBox2 = " + appleBox2.getWeight());
        System.out.println("Вес orangeBox1 = " + orangeBox1.getWeight());
        System.out.println("Вес orangeBox2 = " + orangeBox2.getWeight());
        // сравниваем коробки
        System.out.println("По весу appleBox1 и appleBox2 " + (appleBox1.compareBox(appleBox2) ? "равны." : "не равны."));
        System.out.println("По весу orangeBox1 и orangeBox2 " + (orangeBox1.compareBox(orangeBox2) ? "равны." : "не равны."));
        System.out.println("По весу appleBox2 и orangeBox1 " + (appleBox2.compareBox(orangeBox1) ? "равны." : "не равны."));
        // пересыпаем фрукты из коробки в коробку
        appleBox1.passBox(appleBox2);
        System.out.println("Вес appleBox1 после пересыпания = " + appleBox1.getWeight());
        System.out.println("Вес appleBox2 после пересыпания = " + appleBox2.getWeight());
        orangeBox1.passBox(orangeBox2);
        System.out.println("Вес orangeBox1 после пересыпания = " + orangeBox1.getWeight());
        System.out.println("Вес orangeBox2 после пересыпания = " + orangeBox2.getWeight());

//         appleBox1.passBox(orangeBox1); пересыпание не работает, потому что разные типы коробок

    }
}
