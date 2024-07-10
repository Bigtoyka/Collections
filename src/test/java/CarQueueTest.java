import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarQueueTest {

    private CarQueue queue;

    @BeforeEach
    void setUp() {
        queue = new CarLinkedList();
        for (int i = 0; i < 10; i++) {
            queue.add(new Car("brand" + i, i));
        }
    }

    @Test
    void add() {
        assertEquals(10, queue.size());
    }

    @Test
    void peak() {
        Car car = queue.peek();
        assertEquals("brand0", car.getBrand());
        assertEquals(10, queue.size());
    }

    @Test
    void poll() {
        Car car = queue.poll();
        assertEquals("brand0", car.getBrand());
        assertEquals(9, queue.size());
    }
}