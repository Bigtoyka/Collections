import java.util.Iterator;

public class CarHashSet<T> implements CarSet<T> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private int size = 0;
    private Object[] array = new Object[INITIAL_CAPACITY];

    @Override
    public boolean add(T car) {
        if (size >= (array.length * LOAD_FACTOR)) {
            increaseArray();
        }
        boolean added = add(car, array);
        if (added) {
            size++;
        }
        return added;
    }

    private boolean add(T car, Object[] dst) {
        int position = getElementPosition(car, dst.length);
        if (dst[position] == null) {
            Entry entry = new Entry(car, null);
            dst[position] = entry;
            return true;
        } else {
            Entry existElement = (Entry) dst[position]; // existElement - существующий элемент
            while (true) {
                if (existElement.value.equals(car)) { // Проверяем есть ли там такой же автомабиль
                    return false;
                } else if (existElement.next == null) {
                    existElement.next = new Entry(car, null);
                    return true;
                } else {
                    existElement = existElement.next;
                }
            }
        }
    }

    @Override
    public boolean remove(T car) {
        int position = getElementPosition(car, array.length);
        if (array[position] == null) {
            return false;
        }
        Entry secondLast = (Entry) array[position];
        Entry last = secondLast.next;
        if (secondLast.value.equals(car)) {
            array[position] = last;
            size--;
            return true;
        }
        while (last != null) {
            if (last.value.equals(car)) {
                secondLast.next = last.next; // на обьект, который был в last никто не ссылается и он исчезнет
                size--;
                return true;
            } else {
                secondLast = last;
                last = last.next;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array =new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean contains(T car) {
        int position = getElementPosition(car, array.length);
        if (array[position] == null) {
            return false;
        }
        Entry seclondlast =(Entry) array[position];
        Entry last = seclondlast.next;
        if (seclondlast.value.equals(car)) {
            return true;
        }
        while (last != null) {
            if (last.value.equals(car)) {
                return true;
            } else {
                seclondlast = last;
                last = last.next;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int index = 0;
            int arrayIndex = 0; // нужно для хранения номера ячейки
            Entry entry; // последний полученный объект

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                while(array[arrayIndex] == null) {
                    arrayIndex++;
                }
                if(entry == null){
                    entry =(Entry) array[arrayIndex];
                }
                T result = entry.value;
                entry = entry.next;
                if(entry == null){
                    arrayIndex++;
                }
                index++;
                return result;
            }
        };
    }

    private int getElementPosition(T car, int arrayLenght) {
        return Math.abs(car.hashCode() % arrayLenght);
    }

    private void increaseArray() { // при увеличении массива, проходимся по всем элементам, чтобы передобавитьих в массив
        Object[] newArray = new Object[array.length * 2];
        for (Object entry : array) {
            Entry existedElement = (Entry) entry;
            while (existedElement != null) {
                add(existedElement.value,  newArray);
                existedElement = existedElement.next;
            }
        }
        array = newArray;
    }

    private class Entry {
        private T value;
        private Entry next;

        public Entry(T value, Entry next) {
            this.value = value;
            this.next = next;
        }
    }
}
