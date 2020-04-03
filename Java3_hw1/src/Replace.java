import java.util.Arrays;

public class Replace {
    public static void main(String[] args) {
        Integer array1[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String array2[] = {"A", "B", "C", "D", "E", "F"} ;
        replace(array1,0,9);
        replace(array2,1,2);
    }

    private static void replace(Object[] array, int position1, int position2) {
        System.out.println("Before replacement " + Arrays.toString(array));
        Object tmp = array[position1];
        array[position1] = array[position2];
        array[position2] = tmp;
        System.out.println("After replacement " + Arrays.toString(array));
    }
}
