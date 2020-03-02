package hw;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Runner {
    public static void main(String[] args) {
        final List<Integer> ints = new ArrayList<Integer>(); //throws java.util.ConcurrentModificationException
//        final CopyOnWriteArrayList<Integer> ints = new CopyOnWriteArrayList<>();//no exception

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    ints.add(new Random().nextInt(100));
                }
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    Integer sum = ints.stream()
                            .reduce(0, (a, b) -> a + b);
                    System.out.println("sum = " + sum);
                }
            }
        };
        Thread thread3 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    Integer sum = ints.stream()
                            .reduce(0, (a, b) -> a * a + b * b);
                    System.out.println("sqrt of sum of squares = " + Math.sqrt(sum));
                }
            }
        };

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
