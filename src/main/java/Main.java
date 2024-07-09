import java.util.*;

public class Main {
    public static void main(String[] args) {

        Set<Integer> numbers = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int res = o1.compareTo(o2);
                return -res;
            }
        });
        for (int i = 0; i < 100; i++) {
            numbers.add((int) (Math.random() * 10));
        }
        for (int number : numbers) {
            System.out.println(number);
        }
    }

}
