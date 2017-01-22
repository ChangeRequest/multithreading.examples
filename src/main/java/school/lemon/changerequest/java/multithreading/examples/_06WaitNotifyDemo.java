package school.lemon.changerequest.java.multithreading.examples;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class _06WaitNotifyDemo {

    private static final int COUNTER = 10;

    public static class Consumer extends Thread {

        private List<Integer> values;

        public Consumer(List<Integer> values) {
            this.values = values;
        }

        public void run() {
            try {
                for (int i = 0; i < COUNTER; ++i) {
                    synchronized (values) {
                        while (values.isEmpty()) {
                            System.out.println("Values are empty! Waiting...");
                            values.wait();
                        }
                        System.out.println(values.remove(0));
                    }
                }
            } catch (InterruptedException e) {
                System.out.println(getName() + ": interrupted!");
            }
        }
    }

    public static class Suplier extends Thread {

        private List<Integer> values;

        public Suplier(List<Integer> values) {
            this.values = values;
        }

        public void run() {
            try {
                for (int i = 0; i < COUNTER; ++i) {
                    synchronized (values) {
                        values.add(i);
                        values.notify();
                    }
                    sleep(1_000);
                }
            } catch (InterruptedException e) {
                System.out.println(getName() + ": interrupted!");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        Consumer consumer = new Consumer(list);
        Suplier suplier = new Suplier(list);

        consumer.start();
        suplier.start();

        consumer.join();
        suplier.join();
        System.out.println("Completed!");
    }
}
