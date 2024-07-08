import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CarCollection cars = new CarArrayList();
        for(int i = 0; i< 10; i++){
            cars.add(new Car("Brand"+i,i));
        }
        for(Car car : cars){
            System.out.println(car.getBrand() + " " + car.getNumber());
        }
    }

}
