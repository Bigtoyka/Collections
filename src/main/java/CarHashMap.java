import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarHashMap<K,V> implements CarMap<K,V> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private int size = 0;
    private Object[] array = new Object[INITIAL_CAPACITY];

    @Override
    public void put(K key, V value) {
        if (size >= (array.length * LOAD_FACTOR)) {
            increaseArray();
        }
        boolean puted = put(key, value, array);
        if (puted) {
            size++;
        }
    }

    private boolean put(K key, V value, Object[] dst) {
        int pos = getPos(key, dst.length);
        if (dst[pos] == null) {
            dst[pos] = new Entry(key, value, null);
            return true;
        }
        Entry exist =(Entry) dst[pos];
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
    public V get(K key) {
        int pos = getPos(key, array.length);
        Entry exist = (Entry) array[pos];
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
    public Set<K> keySet() {
        Set<K> newKeySet = new HashSet<>();
        for (Object entry : array) {
            Entry exist = (Entry) entry;
            while (exist != null) {
                newKeySet.add(exist.key);
                exist = exist.next;
            }
        }
        return newKeySet;
    }

    @Override
    public List<V> val() {
        List<V> newArray = new ArrayList<>();
        for (Object entry : array) {
            Entry exist = (Entry) entry;
            while (exist != null) {
                newArray.add(exist.value);
                exist = exist.next;
            }
        }
        return newArray;
    }

    @Override
    public boolean remove(K key) {
        int pos = getPos(key, array.length);
        Entry exist =(Entry)  array[pos];
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
        array = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    private class Entry {
        K key;
        V value;
        Entry next;

        public Entry(K key, V value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private void increaseArray() {
        Object[] newArray = new Object[array.length * 2];
        for (Object entry : array) {
            Entry existedElement = (Entry) entry;
            while (existedElement != null) {
                put(existedElement.key, existedElement.value, newArray);
                existedElement = existedElement.next;
            }
        }
        array = newArray;
    }

    private int getPos(K key, int arrayLenght) {
        return Math.abs(key.hashCode() % arrayLenght);
    }
}
