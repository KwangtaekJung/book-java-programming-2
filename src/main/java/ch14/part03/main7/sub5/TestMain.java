package ch14.part03.main7.sub5;

import java.util.concurrent.CompletableFuture;
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

        CompletableFuture<String> future2 = future1.thenApply(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                System.out.println(3 / 0);
                return "결과=" + String.valueOf(integer);
            }
        });

        CompletableFuture<String> future3 = future2.exceptionally(new Function<Throwable, String>() {
            @Override
            public String apply(Throwable throwable) {
                System.out.println("에러 발생: " + throwable.getMessage());
                return throwable.getMessage();
            }
        });

        String join = future3.join();
        System.out.println("result of future3 = " + join);

        //Lambda 표현식으로 구현하기
//        CompletableFuture.supplyAsync(() -> {
//            return print(1, 3, "supplyAsync1()");
//        }).thenApply(t -> {
//            System.out.println(3/0);
//            return "result = " + String.valueOf(t);
//        }).exceptionally(t -> {
//            System.out.println("에러 발생:" + t.getMessage());
//            return t.getMessage();
//        });
//        String join = future.join();
//        System.out.println("result of future = " + join);

    }
}
