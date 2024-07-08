public interface CarList extends CarCollection{
    Car get(int index);
    boolean add(Car car);
    boolean add(Car car, int index);
    boolean remove(Car car);
    boolean removeAt(int index);
    int size();
    void clear();
}
