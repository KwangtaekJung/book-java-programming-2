package ch19.part03.main1;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TestMain {

    public static void main(String[] args) {

        Optional<String> empty = Optional.empty();
        String orElse1 = empty.orElse("값이 없음.");
        System.out.println("result of orElse() = " + orElse1);

        //--------------------------------------------------------------
        String orElseGet = empty.orElseGet(new Supplier<String>() {
            @Override
            public String get() {
                return "결과 값이 없어서 Supplier 를 통해 새로운 값을 return 함.";
            }
        });
        System.out.println("result of orElseGet() = " + orElseGet);

        //--------------------------------------------------------------
        //since java 1.9
        //Optional 내부 자료가 값을 가질 경우 Optional 객체를 그대로 반환 하며,
        //값이 없으면 Supplier 의 get() 함수에 의해 생성된 Optional 객체 반환 (Optional 객체를 반환 한다는 점이 orElseGet 과 차이점 이다.)
        Optional<String> or = empty.or(new Supplier<Optional<? extends String>>() {
            @Override
            public Optional<? extends String> get() {
                return Optional.of("no value");
            }
        });
        or.ifPresentOrElse(e -> System.out.println("or = " + e), () -> System.out.println("or = 값이 없음."));

        //--------------------------------------------------------------
        {
            Optional<Integer> of = Optional.of(123);
            Optional<Integer> filter1 = of.filter(new Predicate<Integer>() {
                @Override
                public boolean test(Integer integer) {
                    if (integer >= 100) return true;
                    return false;
                }
            });
            Optional<Integer> filter2 = of.filter(new Predicate<Integer>() {
                @Override
                public boolean test(Integer integer) {
                    if (integer < 100) return true;
                    return false;
                }
            });
            filter1.ifPresentOrElse( //익명 클래스
                    new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) {
                            System.out.println("filter1 = " + integer);
                        }
                    },
                    new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("filter1 = 값이 없음.");
                        }
                    }
            );
            filter2.ifPresentOrElse(  //람다 표현식
                    e -> System.out.println("filter2 = " + e),
                    () -> System.out.println("filter2 = 값이 없음."));
        }

        //--------------------------------------------------------------
        Optional<Integer> of = Optional.of(1);
        Optional<String> of_map = of.map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return String.valueOf(integer);
            }
        });
        System.out.println("of_map = " + of_map);
    }
}
