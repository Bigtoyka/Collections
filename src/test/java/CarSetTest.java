import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CarSetTest {
    private CarSet carSet;

    @BeforeEach
    void setUp() {
        carSet = new CarHashSet();
        for (int i = 0; i < 10; i++) {
            carSet.add(new Car("brand" + i, i));
        }
    }

    @Test
    void add() {
        Car car = new Car("BMW",1);
        assertTrue(carSet.add(car));
        assertEquals(11, carSet.size());
        Car car1 = new Car("BMW",1);
        assertFalse(carSet.add(car1));
        assertEquals(11, carSet.size());
    }

    @Test
    void remove() {
        Car car = new Car("brand0",0);
        assertTrue(carSet.remove(car));
        assertEquals(9, carSet.size());
        assertFalse(carSet.remove(car));
        assertEquals(9, carSet.size());
    }

    @Test
    void size() {
        assertEquals(10, carSet.size());
    }

    @Test
    void clear() {
        carSet.clear();
        assertEquals(0, carSet.size());
    }

    @Test
    public void containsInCollections(){
        assertEquals(10, carSet.size());
        Car car = new Car("Audi", 0);
        carSet.add(car);
        assertTrue(carSet.contains(car));
        assertFalse(carSet.contains(new Car("Brand200", 20)));

    }
}