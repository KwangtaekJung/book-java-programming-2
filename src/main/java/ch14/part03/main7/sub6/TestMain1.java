package ch14.part03.main7.sub6;


import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class TestMain1 {

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
                return print(1, 2, "supplyAsync1()");
            }
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return print(3, 6, "supplyAsync2()");
            }
        });

        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return print(7, 11, "supplyAsync3()");
            }
        });

        CompletableFuture<Void> allOf = CompletableFuture.allOf(future1, future2, future3);
        Void join = allOf.join();
        System.out.println("result of allOf = " + join);

        System.out.println();

        CompletableFuture<Integer> future4 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return print(1, 2, "supplyAsync4()");
            }
        });

        CompletableFuture<Integer> future5 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return print(3, 6, "supplyAsync5()");
            }
        });

        CompletableFuture<Integer> future6 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return print(7, 11, "supplyAsync6()");
            }
        });

        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(future4, future5, future6);
        Object join2 = anyOf.join();
        System.out.println("result of anyOf = " + join2);
    }
}
