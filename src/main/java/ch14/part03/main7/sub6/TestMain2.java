package ch14.part03.main7.sub6;

import java.util.concurrent.CompletableFuture;

public class TestMain2 {

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

        CompletableFuture.allOf(
                CompletableFuture.supplyAsync(() -> {
                    return print(1, 2, "supplyAsync1()");
                }),
                CompletableFuture.supplyAsync(() -> {
                    return print(3, 6, "supplyAsync2()");
                }),
                CompletableFuture.supplyAsync(() -> {
                    return print(7, 11, "supplyAsync3()");
                })
        ).join();

        Object join = CompletableFuture.anyOf(
                CompletableFuture.supplyAsync(() -> {
                    return print(1, 2, "supplyAsync4()");
                }),
                CompletableFuture.supplyAsync(() -> {
                    return print(3, 6, "supplyAsync5()");
                }),
                CompletableFuture.supplyAsync(() -> {
                    return print(7, 11, "supplyAsync6()");
                })
        ).join();
        System.out.println("result of anyOf = " + join);
    }
}
