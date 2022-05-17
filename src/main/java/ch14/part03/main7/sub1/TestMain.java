package ch14.part03.main7.sub1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TestMain {

    public static void main(String[] args) {
        CompletableFuture<Object> future = new CompletableFuture<>();
        new Thread() {
            @Override
            public void run() {
                int sum = 0;

                for (int i = 1; i <= 3; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sum += i;
                    System.out.println(String.format("i=[%d], sum=[%d]", i, sum));
                }
                future.complete(sum);
            }
        }.start();
        System.out.println("Thread Start() 완료");

        try {
            Object object = future.get();
            System.out.println("결과 값=" + object);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Object join = future.join();
        System.out.println("결과 값=" + join);

        System.out.println("메인 함수 종료");
    }
}
