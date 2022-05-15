package ch14.part03.main4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestMain {
    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(2);
        Callable<String> c1 = new MyCallable("Thread-1", 2);
        Callable<String> c2 = new MyCallable("Thread-2", 3);
        Callable<String> c3 = new MyCallable("Thread-3", 4);

        List<Callable<String>> list = new ArrayList<>();
        Collections.addAll(list, c1, c2, c3);

        /**
         * invokeAll() 함수 사용 시 - 주석 해제 이 후 실행
         */
//        try {
//            List<Future<String>> invokeAll = pool.invokeAll(list);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        try {
            String invokeAny = pool.invokeAny(list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("쓰레드 종료");
    }

    public static class MyCallable implements Callable<String> {

        private String title;
        private int count;

        public MyCallable(String title, int count) {
            this.title = title;
            this.count = count;
        }

        @Override
        public String call() throws Exception {
            String msg = Thread.currentThread().getName() + " " + title;

            for (int i = 0; i < count; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("인터럽트" + Thread.currentThread().getName());
                    e.printStackTrace();
                }
                System.out.println(msg + " " + i);
            }
            return msg;
        }
    }
}
