import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> box = new ArrayList<>();

    //Вычисляем вес коробки
    public float getWeight() {
        float weight = 0.0f;
        for (T each : box) {
            //складываем вес всех фруктов в коробке
            weight = weight + each.getWightFruit();
        }
        return weight;
    }

    //сравнение коробок по весу
    //здесь применяем ораничение "?" позволяющее сравнивать все подклассы T
    public boolean compareBox(Box<?> anotherBox) {
        return (this.getWeight() == anotherBox.getWeight());
    }

    //пересыпаем из одной коробки в другую
    public void passBox(Box<T> anotherBox){
        anotherBox.box.addAll(this.box);
        this.box.clear();
    }

    // добавляем фрукты к коробку
    public void addFruit(T fruit, int count) {
        for (int i = 0; i < count; i++) {
            this.box.add(fruit);
        }
    }
}

