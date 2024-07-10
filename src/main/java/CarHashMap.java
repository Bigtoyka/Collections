import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarHashMap implements CarMap {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private int size = 0;
    private Entry[] array = new Entry[INITIAL_CAPACITY];

    @Override
    public void put(CarOwner key, Car value) {
        if (size >= (array.length * LOAD_FACTOR)) {
            increaseArray();
        }
        boolean puted = put(key, value, array);
        if (puted) {
            size++;
        }
    }

    private boolean put(CarOwner key, Car value, Entry[] dst) {
        int pos = getPos(key, dst.length);
        if (dst[pos] == null) {
            dst[pos] = new Entry(key, value, null);
            return true;
        }
        Entry exist = dst[pos];
        while (true) {
            if (exist.key.equals(key)) {
                exist.value = value;
                return false;
            } else if (exist.next == null) {
                exist.next = new Entry(key, value, null);
                return true;
            } else {
                exist = exist.next;
            }
        }
    }

    @Override
    public Car get(CarOwner key) {
        int pos = getPos(key, array.length);
        Entry exist = array[pos];
        while (exist != null) {
            if (exist.key.equals(key)) {
                return exist.value;
            } else {
                exist = exist.next;
            }
        }
        return null;
    }

    @Override
    public Set<CarOwner> keySet() {
        Set<CarOwner> newKeySet = new HashSet<>();
        for (Entry entry : array) {
            Entry exist = entry;
            while (exist != null) {
                newKeySet.add(exist.key);
                exist = exist.next;
            }
        }
        return newKeySet;
    }

    @Override
    public List<Car> val() {
        List<Car> newArray = new ArrayList<>();
        for (Entry entry : array) {
            Entry exist = entry;
            while (exist != null) {
                newArray.add(exist.value);
                exist = exist.next;
            }
        }
        return newArray;
    }

    @Override
    public boolean remove(CarOwner key) {
        int pos = getPos(key, array.length);
        Entry exist = array[pos];
        if (exist != null && exist.key.equals(key)) {
            array[pos] = exist.next;
            size--;
            return true;
        } else {
            while (exist != null) {
                Entry nextEl = exist.next;
                if (nextEl == null) {
                    return false;
                }
                if (nextEl.key.equals(key)) {
                    exist.next = nextEl.next;
                    size--;
                }
                exist = exist.next;
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
        array = new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    private static class Entry {
        CarOwner key;
        Car value;
        Entry next;

        public Entry(CarOwner key, Car value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private void increaseArray() {
        Entry[] newArray = new Entry[array.length * 2];
        for (Entry entry : array) {
            Entry existedElement = entry;
            while (existedElement != null) {
                put(existedElement.key, existedElement.value, newArray);
                existedElement = existedElement.next;
            }
        }
        array = newArray;
    }

    private int getPos(CarOwner key, int arrayLenght) {
        return Math.abs(key.hashCode() % arrayLenght);
    }
}
