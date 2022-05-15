package ch14.part03.main5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestMain {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        pool.execute(new MyRunnable("thread1", 2));
        pool.execute(new MyRunnable("thread2", 4));
        pool.execute(new MyRunnable("thread3", 6));

//        pool.shutdown();
//        pool.shutdownNow();

        try {
            boolean awaitTermination = pool.awaitTermination(3500, TimeUnit.MILLISECONDS);
            System.out.println("awaitTermination = " + awaitTermination);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("쓰레드 종료");
    }

    public static class MyRunnable implements Runnable {
        private String title;
        private int count;

        public MyRunnable(String title, int count) {
            this.title = title;
            this.count = count;
        }

        @Override
        public void run() {

            String name = Thread.currentThread().getName() + "\t" + title;
            for (int i = 0; i < count; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException 발생-" + name);
                }
                System.out.println(name + "\t" + i);
            }
        }
    }
}
