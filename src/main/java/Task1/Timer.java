package Task1;

public class Timer {
    public static void main(String[] args) {
        Thread timerThread = new Thread(new Counter());
        Thread messageThread = new Thread(new Message());

        timerThread.start();
        messageThread.start();
    }
}

class Message implements Runnable {
    @Override
    public void run () {
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("5 second have passed");
        }
    }
}

class Counter implements Runnable {
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (true) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - startTime;
            System.out.println("The program has been running for " + elapsedTime/1000 + " seconds now!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
