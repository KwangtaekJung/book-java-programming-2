package ch14.part02.main7;

public class TetMain {

    public static class MyThread extends Thread {
        boolean available = true;
        boolean stop = false;

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public void setStop(boolean stop) {
            this.stop = stop;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            int count = 0;

            while(!stop) {
                if (available) {
                    System.out.println(name + " Thread running[" + (++count) + "]");
                } else {
                    System.out.println(name + " execute yield()[" + (++count) + "]");
                    Thread.yield();
                }
            }
        }
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        MyThread t4 = new MyThread();
        MyThread t5 = new MyThread();
        MyThread t6 = new MyThread();

        t1.start(); t2.start(); t3.start(); t4.start(); t5.start(); t6.start();

        t1.setAvailable(false);

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
        }

        t1.setStop(true);
        t2.setStop(true);
        t3.setStop(true);
        t4.setStop(true);
        t5.setStop(true);
        t6.setStop(true);
    }
}
