package ch14.part03.main6;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestMain {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(1);

        Future<String> future1 = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return print("쓰레드1", 3);
            }
        });

        Future<?> future2 = pool.submit(new Runnable() {
            @Override
            public void run() {
                print("쓰레드2", 3);
            }
        });

        Future<?> future3 = pool.submit(new Runnable() {
            @Override
            public void run() {
                print("쓰레드3", 3);
            }
        });

        try {
            String result = future1.get();
            System.out.println("결과값=" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        boolean cancel = future3.cancel(true);

        try {
            Object result2 = future2.get();
            System.out.println("결과값=" + result2);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("메인 쓰레드 종료");
    }

    public static String print(String title, int count) {
        String name = Thread.currentThread().getName();

        for (int i = 0; i < count; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("\t" + title + ",예외발생[" + name + "]");
            }
            System.out.println(String.format("%s.%s[%d]", title, name, i));
        }
        return "쓰레드 종료" + title + "," + name;
    }
}
