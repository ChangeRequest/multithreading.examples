package school.lemon.changerequest.java.multithreading.examples;

@SuppressWarnings("ALL")
public class _00DaemonDemo {

    public static class DemonPrinter extends Thread {

        public DemonPrinter() {
            setDaemon(true);
        }

        @Override
        public void run() {
            while (true) {
                System.out.println("Hello World, from Daemon");
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        new DemonPrinter().start();
        Thread.sleep(1_000);
        System.out.println("Awaked!");
    }

}
