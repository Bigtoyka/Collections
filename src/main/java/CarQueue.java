public interface CarQueue<T> extends CarCollection<T> {
    boolean add(T car);

    T peek();

    T poll();
}
