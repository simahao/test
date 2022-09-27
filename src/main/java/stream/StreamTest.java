package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {

    private long counter;

    private List<String> list = Arrays.asList("abc1", "abc2", "abc3", "abc4");

    private void wasCalled() {
        counter++;
        System.out.println(counter);
    }

    public void testLambda1() {
        counter = 0;
        List<String> dlist = list.stream().filter(element -> {
            System.out.println("filter was called");
            return element.contains("2");
        }).map(element -> {
            System.out.println("map was called");
            return element.toUpperCase();
        }).collect(Collectors.toList());

        System.out.println(dlist);
    }

    public void testLambda2() {
        Stream<String> stream = list.stream().filter(element -> {
            wasCalled();
            return element.contains("2");
        });

        Optional<String> ret = stream.findFirst();
    }

    public void testLambda3() {
        long size = list.stream().skip(2).map(element -> {
            wasCalled();
            return element.substring(0, 3);
        }).count();

        System.out.println(size);
    }

    public void testLambda4() {
        OptionalInt reduced = IntStream.range(1, 4).reduce((a, b) -> a + b);
        System.out.println(reduced.getAsInt());

        int i = IntStream.range(1, 4).reduce(10, (a, b) -> a + b);
        System.out.println("i: " + i);


        int i1 = Stream.of(1, 2, 3).reduce(10, (a, b) -> a + b, (a, b) -> {
            System.out.println("combiner was called");
            return a + b;
        });
        System.out.println("i1: " + i1);


        //10 + 1 = 11; 10 + 2 = 12; 10 + 3 = 13;
        //11 + 12 + 13 = 36
        int i2 = Arrays.asList(1, 2, 3).parallelStream()
            .reduce(10, (a, b) -> a + b, (a, b) -> {
                System.out.println("combiner was called");
                return a + b;
            });
        System.out.println("i2:" + i2);
    }

    public void testLambda5() {
        List<Product> list = Arrays.asList(new Product(1, "a"),
            new Product(2, "b"), new Product(3, "c"),
            new Product(4, "d"), new Product(5, "e"));

        List<String> rlt = list.stream().map(Product::getName).collect(Collectors.toList());
        System.out.println("collect: " + rlt);
        String listToString = list.stream().map(Product::getName).collect(Collectors.joining(",", "[", "]"));
        System.out.println("list2String: " + listToString);

    }

    public static void main(String[] args) {
        StreamTest obj = new StreamTest();
        // obj.testLambda1();
        // obj.testLambda2();
        // obj.testLambda3();
        // obj.testLambda4();
        obj.testLambda5();
    }
}
