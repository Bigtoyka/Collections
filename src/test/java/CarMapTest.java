import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarMapTest {
    private CarMap<CarOwner,Car> map;

    @BeforeEach
    void setUp() {
        map = new CarHashMap<>();
        for (int i = 0; i < 10; i++) {
            CarOwner carOwner = new CarOwner(i, "Name" + i, "LastName" + i);
            Car car = new Car("Brand" + i, i);
            map.put(carOwner, car);
        }
    }

    @Test
    void put() {
        assertEquals(10, map.size());
    }

    @Test
    void get() {
        CarOwner key = new CarOwner(0, "Name0", "LastName0");
        Car value = map.get(key);
        assertEquals("Brand0", value.getBrand());
    }

    @Test
    void keySet() {
        assertEquals(10, map.size());
        assertEquals(10, map.keySet().size());
    }

    @Test
    void val() {
        assertEquals(10, map.size());
        assertEquals(10, map.val().size());
    }

    @Test
    void remove() {
        CarOwner carOwner = new CarOwner(0, "Name0", "LastName0");
        assertTrue(map.remove(carOwner));
        assertEquals(9, map.size());
        assertFalse(map.remove(carOwner));
    }
}