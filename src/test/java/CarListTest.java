import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarListTest {
    private CarList carList;

    @BeforeEach
    void setUp() {
        carList = new CarLinkedList();
        for (int i = 0; i < 100; i++) {
            carList.add(new Car("brand" + i, i));
        }
    }

    @Test
    public void testAdd100ElThenSizeMustBe100() {
        assertEquals(100, carList.size());// Cравнивает реальный рез и то что вернет метод size
    }

    @Test
    public void whenElRemovedByIndexThenSizeMustBeDecreased() { // когда удалили эл по индексу, то размер должен быть уменьшен
        assertTrue(carList.removeAt(5));
        assertEquals(99, carList.size());
    }

    @Test
    public void whenElRemovedThenSizeMustBeDecreased() {
        Car car = new Car("Takayama", 15);
        carList.add(car);
        assertEquals(101, carList.size());
        assertTrue(carList.remove(car));
        assertEquals(100, carList.size());
    }

    @Test
    public void whenNonExELRemovedThenReturnFalse() { // Если удаляем не существующий эл, то возвращено False
        Car car = new Car("Takayama", 15);
        assertFalse(carList.remove(car));
        assertEquals(100, carList.size());
    }

    @Test
    public void whenListClearedThenSizeMustBe0() {
        carList.clear();
        assertEquals(0, carList.size());
    }

    @Test
    public void whenIndexOutOfBoundsThenThrownException() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> { // В Junit5 Обращаемся к исключениям через Exception
            carList.get(100);//Обращаемся к 100, так как всего 100 эл и последний 99
        });
    }
    @Test
    public void methodGetReturnedRightValue(){
        Car car = carList.get(0);
        assertEquals("brand0", car.getBrand());
    }

    @Test
    public void insertIntoMidle(){
        Car car = new Car("BMW", 1);
        carList.add(car,50);
        Car carFromList = carList.get(50);
        assertEquals("BMW",carFromList.getBrand());
    }

    @Test
    public void insertIntoFirstPosition(){
        Car car = new Car("BMW", 1);
        carList.add(car,0);
        Car carFromList = carList.get(0);
        assertEquals("BMW",carFromList.getBrand());
    }

    @Test
    public void insertIntoLastPosition(){
        Car car = new Car("BMW", 1);
        carList.add(car,100);
        Car carFromList = carList.get(100);
        assertEquals("BMW",carFromList.getBrand());
    }
}