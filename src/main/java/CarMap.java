import java.util.List;
import java.util.Set;

public interface CarMap {
    void put(CarOwner key, Car value);

    Car get(CarOwner key);

    Set<CarOwner> keySet();

    List<Car> val();

    boolean remove(CarOwner key);

    int size();

    void clear();
}
