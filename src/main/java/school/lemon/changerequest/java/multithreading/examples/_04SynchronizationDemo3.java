package school.lemon.changerequest.java.multithreading.examples;

@SuppressWarnings("ALL")
public class _04SynchronizationDemo3 {

    public static class NameWriter extends Thread {
        public NameWriter(String name) { super(name); }
        public void run() {
            synchronized (NameWriter.class) {
                for (int i = 0; i < 1_000; ++i) {
                    System.out.println(getName());
                }
            }
        }
    }

    public static void main(String[] args) {
        new NameWriter("FirstThread").start();
        new NameWriter("SecondThread").start();
    }
}
