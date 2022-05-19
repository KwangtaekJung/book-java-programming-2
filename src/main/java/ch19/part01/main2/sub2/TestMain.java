package ch19.part01.main2.sub2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {

        List<String> names1 = new ArrayList<>();
        names1.add("b");
        names1.add("c");
        names1.add("a");

        List<String> names2 = new ArrayList<>();
        names2.add("b");
        names2.add("c");
        names2.add("a");

        System.out.println("Sort 익명클래스");
        TestMain test = new TestMain();
        test.sort1(names1);
        System.out.println(names1);

        System.out.println("Sort 람다식");
        test.sort2(names2);
        System.out.println(names2);
    }

    private void sort1(List<String> names) {
        Collections.sort(names, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    private void sort2(List<String> names) {
        Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
    }

}
