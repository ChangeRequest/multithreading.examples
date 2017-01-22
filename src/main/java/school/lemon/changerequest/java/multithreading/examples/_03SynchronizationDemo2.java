package school.lemon.changerequest.java.multithreading.examples;

@SuppressWarnings("ALL")
public class _03SynchronizationDemo2 {

    public static class NameWriter extends Thread {

        public NameWriter(String name) {
            super(name);
        }

        public synchronized void run() {
            for (int i = 0; i < 1_000; ++i) {
                System.out.println(getName());
            }
        }
    }

    public static void main(String[] args) {
        new NameWriter("FirstThread").start();
        new NameWriter("SecondThread").start();
    }
}
