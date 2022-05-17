package ch14.part03.main7.sub2;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class TestMain {

    public static int print(int start, int end, String title) {
        System.out.println(title + "함수 시작");
        int sum = 0;
        for (int i = start; i <= end; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum += i;
            System.out.println(title + ": i=[" + i + "], sum=[" + sum + "]");
        }
        System.out.println(title + "함수 종료");
        return sum;
    }

    public static void main(String[] args) {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                print(1, 3, "runAync()");
            }
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return print(1, 3, "supplyAsync()");
            }
        });

        Void join1 = future1.join();
        Integer join2 = future2.join();
        System.out.println("future1 결과 값=" + join1);
        System.out.println("result of future2=" + join2);
    }
}
