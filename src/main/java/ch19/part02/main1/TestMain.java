package ch19.part02.main1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Cafe Mocha");
        list.add("Americano");
        list.add("Cafe Latte");

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        Collections.sort(list, (o1, o2) -> o1.compareTo(o2));
        Collections.sort(list, String::compareTo);
        list.sort(String::compareTo);

        System.out.println("-->> collection sort");
        for (String obj : list) {
            System.out.println(obj);
        }

        System.out.println("-->> stream sort");
        list.stream().sorted().forEach(System.out::println);
    }
}
