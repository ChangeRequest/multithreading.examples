package school.lemon.changerequest.java.multithreading.examples;

@SuppressWarnings("ALL")
public class _01RaceConditionDemo {

    public static class IntHolder {
        private int value = 0;
        public void increment() {value++;}
        public void decrement() {value--;}
        public int getValue() {return value;}
    }

    public static class Incrementor extends Thread {
        private IntHolder intHolder;
        public Incrementor(IntHolder intHolder) { this.intHolder = intHolder; }
        public void run() {
            for (int i = 0; i < 1_000_000; ++i) intHolder.increment();
            System.out.println("Incrementor done!");
        }
    }

    public static class Decrementor extends Thread {
        private IntHolder intHolder;
        public Decrementor(IntHolder intHolder) { this.intHolder = intHolder;}
        public void run() {
            for (int i = 0; i < 1_000_000; ++i) intHolder.decrement();
            System.out.println("Decrementor done!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        IntHolder intHolder = new IntHolder();
        System.out.println(intHolder.getValue());
        new Incrementor(intHolder).start();
        new Decrementor(intHolder).start();
        Thread.sleep(1_000);
        System.out.println(intHolder.getValue());
    }
}
