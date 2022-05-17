package ch14.part03.main7.sub3;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
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
                return print(1, 5, "supplyAsync1()");
            }
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return print(6, 10, "supplyAsync2()");
            }
        });

        CompletableFuture<String> future3 = future1.thenCombine(future2, new BiFunction<Integer, Integer, String>() {
            @Override
            public String apply(Integer t, Integer u) {
                return String.valueOf(t + u);
            }
        });
        String join = future3.join();
        System.out.println("result of future3 = " + join);
    }
}
