import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarCollectionTest {

    private CarCollection carCollection;

    @BeforeEach
    void setUp() {
        carCollection = new CarHashSet();
        for (int i = 0; i < 100; i++) {
            carCollection.add(new Car("brand" + i, i));
        }
    }

    @Test
    public void contains() {
        assertTrue(carCollection.contains(new Car("Brand20", 20)));
        assertFalse(carCollection.contains(new Car("Brand200", 20)));
    }
}