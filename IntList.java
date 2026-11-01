import java.util.Arrays;

public class IntList {
    private int[] array = new int[2];
    private int size = 0;
    public void add(int value){
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        array[size] = value;
        size++;
    }
    public int size(){
        return size;
    }

    public int get(int index) {
        return array[index];
    }
}
