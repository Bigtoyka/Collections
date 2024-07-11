import java.util.List;
import java.util.Set;

public interface CarMap<K,V> {
    void put(K key, V value);

    V get(K key);

    Set<K> keySet();

    List<V> val();

    boolean remove(K key);

    int size();

    void clear();
}
