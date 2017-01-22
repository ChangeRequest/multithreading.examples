package school.lemon.changerequest.java.multithreading.examples;

@SuppressWarnings("ALL")
public class _09VolatileDemo {

    // TODO: add volatile to fix an issue with caching, should be like:
    // private static volatile boolean exit = false;
    private static boolean exit = false;

    private static Thread first = new Thread() {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {
            }
            exit = true;
            System.out.println("First thread finished");
        }
    };

    private static Thread second = new Thread() {
        @Override
        public void run() {
            while (!exit) {
            }
            System.out.println("Second thread finished");
        }
    };

    public static void main(String[] args) {
        first.start();
        System.out.println("First thread started");
        second.start();
        System.out.println("Second thread started");
    }

}
