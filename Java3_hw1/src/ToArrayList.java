import java.util.ArrayList;
import java.util.Arrays;

public class ToArrayList {
    public static void main(String[] args) {
        String[] arrayString = {"Alpha", "Bravo", "Charlie", "Delta", "Foxtrot"};
        Integer[] arrayInt = {1, 2, 3, 4, 5};
        System.out.println(toArrayList(arrayString) + " is a " + toArrayList(arrayString).getClass().getSimpleName());
        System.out.println(toArrayList(arrayInt) + " is a " + toArrayList(arrayInt).getClass().getSimpleName());
    }

    private static <T> ArrayList<T> toArrayList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }
}
