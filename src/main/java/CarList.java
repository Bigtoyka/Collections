public interface CarList<T> extends CarCollection<T>{
    T get(int index);
    boolean add(T car);
    boolean add(T car, int index);
    boolean remove(T car);
    boolean removeAt(int index);
    int size();
    void clear();
}
