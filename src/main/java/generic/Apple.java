package generic;

import java.util.ArrayList;
import java.util.List;

public class Apple extends Fruit {

    Apple() {
        super("apple", "red", "north");
    }

    public String toString()  {
        String str = "name:" + getName() + " color:" + getColor() + " area:" + getArea();
        System.out.println(str);
        return str;
    }

    public static void main(String[] args) {
        MmTable<Apple> table = new MmTable<>();

        List<Apple> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Apple());
        }
        table.create(list);
        List<Apple> l = table.getList();
        for (Apple apple : l) {
            System.out.println(apple.getName());
        }
    }
}
