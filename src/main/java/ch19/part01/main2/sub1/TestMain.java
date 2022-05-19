package ch19.part01.main2.sub1;

import java.util.function.IntBinaryOperator;
import java.util.function.Supplier;

public class TestMain {

    public static void main(String[] args) {

        TestMain test = new TestMain();

        Math add = (int x, int y) -> x + y;

        Math subtract = (x, y) -> x - y;

        Math multiply = (int x, int y) -> {return x * y;};

        Math divide = (int x, int y) -> x / y;

        System.out.println("20 + 5 = " + test.operation(20, 5, add));
        System.out.println("20 - 5 = " + test.operation(20, 5, subtract));
        System.out.println("20 * 5 = " + test.operation(20, 5, multiply));
        System.out.println("20 / 5 = " + test.operation(20, 5, divide));

        Echo msg1 = (msg) -> { System.out.println("Hello! " + msg); };
        Echo msg2 = msg -> System.out.println("Hello! " +msg);

        msg1.echo("a");
        msg2.echo("b");

        Process process = () -> System.out.println("no parameter!");
        process.echo();

        Supplier<String> supplier = () -> "Hello!! Supplier";
        System.out.println(supplier.get());
    }

    private int operation(int x, int y, Math math) {
        return math.operation(x, y);
    }

    interface Math {
        int operation(int x, int y);
    }

    interface Echo {
        void echo(String msg);
    }

    interface Process {
        void echo();
    }
}
