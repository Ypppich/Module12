package Task2;

public class FizzBuzz {
    private int n;
    private int current;
    private Object lock = new Object();

    public FizzBuzz(int n) {
        this.n = n;
        this.current = 1;
    }

    public void fizz() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (current > n || current % 3 != 0 || current % 5 == 0) {
                    if (current > n) {
                        return;
                    }
                    lock.wait();
                }
                String result = "fizz";
                current++;
                lock.notifyAll();
                if (current > n) {
                    System.out.print(result);
                    return;
                }
                System.out.print(result + ", ");
            }
        }
    }

    public void buzz() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (current > n || current % 5 != 0 || current % 3 == 0) {
                    if (current > n) {
                        return;
                    }
                    lock.wait();
                }
                String result = "buzz";
                current++;
                lock.notifyAll();
                if (current > n) {
                    System.out.print(result);
                    return;
                }
                System.out.print(result + ", ");
            }
        }
    }

    public void fizzbuzz() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (current > n || current % 3 != 0 || current % 5 != 0) {
                    if (current > n) {
                        return;
                    }
                    lock.wait();
                }
                String result = "fizzbuzz";
                current++;
                lock.notifyAll();
                if (current > n) {
                    System.out.print(result);
                    return;
                }
                System.out.print(result + ", ");
            }
        }
    }

    public void number() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (current > n || (current % 3 == 0 || current % 5 == 0)) {
                    if (current > n) {
                        return;
                    }
                    lock.wait();
                }
                String result = String.valueOf(current);
                current++;
                lock.notifyAll();
                if (current > n) {
                    System.out.print(result);
                    return;
                }
                System.out.print(result + ", ");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int n = 15;
        FizzBuzz fizzBuzz = new FizzBuzz(n);

        Thread threadA = new Thread(() -> {
            try {
                fizzBuzz.fizz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                fizzBuzz.buzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadD = new Thread(() -> {
            try {
                fizzBuzz.number();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        threadA.join();
        threadB.join();
        threadC.join();
        threadD.join();
    }
}
