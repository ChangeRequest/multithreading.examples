package school.lemon.changerequest.java.multithreading.examples;

public class _10InterruptionDemo {

    public static void main(String[] args) throws InterruptedException {
        NamePrinter printer = new NamePrinter();
        printer.start();
        Thread.sleep(1_000);
        printer.interrupt();

        NamePrinterWithSleep printerWithSleep = new NamePrinterWithSleep();
        printerWithSleep.start();
        Thread.sleep(1_000);
        printerWithSleep.interrupt();
    }

    private static class NamePrinter extends Thread {

        public void run() {
            while (!isInterrupted()) {
                System.out.println(getName());
            }
        }

    }

    private static class NamePrinterWithSleep extends Thread {

        public void run() {
            try {
                while (!isInterrupted()) {
                    System.out.println(getName());
                    sleep(200);
                }
                System.out.println("Interrupted by isInterrupted = false!");
            } catch (InterruptedException e) {
                System.out.println("Interrupted be exception!" + e);
                System.out.println("IsInterrupted = " + isInterrupted());
            }
        }
    }

}
