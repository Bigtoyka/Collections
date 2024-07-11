import java.util.Arrays;
import java.util.Iterator;

public class CarArrayList<T> implements CarList<T> {
    private Object[] array = new Object[10];
    private int size = 0;

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    @Override
    public boolean add(T car) {
        if (size >= array.length) {
//            array = Arrays.copyOf(array, array.length * 2); Тоже самое, что и снизу.
            Object[] newArray = new Object[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[size] = car;
        size += 1;
        return true;
    }

    @Override
    public boolean remove(T car) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(car)) {
                return removeAt(i);
            }
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        checkIndex(index);
//        System.arraycopy(array, index + 1, array, index , size - 1 - index);
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size -= 1;
        return true;
    }

    @Override
    public boolean add(T car, int index) {
        increaseArray();
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = car;
        size += 1;
        return true;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = new Car[10];
        size = 0;

    }

    @Override
    public Iterator<T> iterator() { // сделан чтобы обходить массив с помощью for each
        return new Iterator<T>() {

            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return (T) array[index++]; //постинкремент
            }
        };
    }

    @Override
    public boolean contains(T car) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(car)) {
                return true;
            }
        }
        return false;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void increaseArray() {
        if (size >= array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
    }
}
