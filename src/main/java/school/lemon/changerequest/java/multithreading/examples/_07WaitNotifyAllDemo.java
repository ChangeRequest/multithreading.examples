package school.lemon.changerequest.java.multithreading.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("ALL")
public class _07WaitNotifyAllDemo {

    private static final int COUNTER = 5;
    private static final int SUPLIERS_COUNT = 1;
    private static final int CONSUMERS_COUNT = 2;

    public static class Consumer extends Thread {

        private List<Integer> values;

        public Consumer(List<Integer> values) {
            this.values = values;
        }

        public void run() {
            while (true) {
                synchronized (values) {
                    try {
                        consumeValues();
                        if (isInterrupted()) {
                            System.out.println(getName() + ": done!");
                            return;
                        }
                        waitForValues();
                    } catch (InterruptedException ex) {
                        System.out.println(getName() + ": interrupted!");
                        interrupt();
                    }
                }
            }
        }

        private void waitForValues() throws InterruptedException {
            while (values.isEmpty()) {
                System.out.println(getName() + ": values are empty! Waiting...");
                values.wait();
            }
        }

        private void consumeValues() {
            while (!values.isEmpty()) {
                System.out.println(getName() + ": " + values.remove(0));
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
                        values.add(new Random().nextInt());
                        values.notifyAll();
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

        Suplier[] supliers = createAndStartSupliers(list);
        Consumer[] consumers = createAndStartConsumers(list);

        waitForSupliers(supliers);
        waitAndInterruptConsumers(consumers);

        System.out.println("Completed!");
    }

    private static void waitAndInterruptConsumers(Consumer[] consumers)
            throws InterruptedException {
        for (int i = 0; i < consumers.length; ++i) {
            consumers[i].interrupt();
            consumers[i].join();
        }
    }

    private static void waitForSupliers(Suplier[] supliers) throws InterruptedException {
        for (int i = 0; i < supliers.length; ++i) {
            supliers[i].join();
        }
    }

    private static Consumer[] createAndStartConsumers(List<Integer> list) {
        Consumer[] consumers = new Consumer[CONSUMERS_COUNT];
        for (int i = 0; i < consumers.length; ++i) {
            consumers[i] = new Consumer(list);
            consumers[i].start();
        }
        return consumers;
    }

    private static Suplier[] createAndStartSupliers(List<Integer> list) {
        Suplier[] supliers = new Suplier[SUPLIERS_COUNT];
        for (int i = 0; i < supliers.length; ++i) {
            supliers[i] = new Suplier(list);
            supliers[i].start();
        }
        return supliers;
    }
}
