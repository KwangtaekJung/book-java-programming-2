package ch14.part03.main7.sub4;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
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

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return print(1, 3, "supplyAsync1()");
            }
        });

        CompletableFuture<String> future2 = future1.thenCompose(new Function<Integer, CompletionStage<String>>() {
            @Override
            public CompletionStage<String> apply(Integer integer) {
                CompletableFuture<String> future3 = CompletableFuture.supplyAsync(new Supplier<String>() {
                    @Override
                    public String get() {
                        return "결과=" + print(integer, integer + 1, "supplyAsyn2()");
                    }
                });
                return future3;
            }
        });

        String join = future2.join();
        System.out.println("result of future2 = " + join);
    }
}
