package school.lemon.changerequest.java.multithreading.examples;

@SuppressWarnings("ALL")
public class _08DeadLockDemo {

    private static final Integer[] FORKS = {0, 1, 2, 3, 4};

    public static class Philosopher extends Thread {

        private Integer fork1;
        private Integer fork2;

        public Philosopher(Integer fork1, Integer fork2) {
            this.fork1 = fork1;
            this.fork2 = fork2;
        }

        public void run() {
            try {
                while (true) {
                    synchronized (fork1) {
                        System.out.println("Thinking...");
                        Thread.sleep(1_000);
                        synchronized (fork2) {
                            System.out.println(getName() + ": Eating with forks " + fork1 + ", " + fork2);
                            Thread.sleep(1_000);
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Philosopher[] philosophers = {new Philosopher(FORKS[0], FORKS[1]), new Philosopher(FORKS[1], FORKS[2]),
                new Philosopher(FORKS[2], FORKS[3]), new Philosopher(FORKS[3], FORKS[4]), new Philosopher(FORKS[4],
                FORKS[0])};
        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i].start();
        }
    }

}
