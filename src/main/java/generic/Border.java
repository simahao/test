package generic;

import java.util.ArrayList;
import java.util.List;

class A {}
class B extends A {}
class C extends B {}
public class Border {
    public static void main(String[] args) {
        List<? extends B> list1 = new ArrayList<>();
        List<? super B> list2 = new ArrayList<>();

        // list1.add(new A());
        // list1.add(new B());
        // list1.add(new C());
        // list1.add(new Object());
        // list1.add(null);
        // A a1 = list1.get(0);
        // B b1 = list1.get(0);
        // C c1 = list1.get(0);
        // Object o1 = list1.get(0);


        // list2.add(new A());
        // list2.add(new B());
        // list2.add(new C());
        // list2.add(new Object());
        // list2.add(null);
        // A a2 = list2.get(0);
        // B b2 = list2.get(0);
        // C c2 = list2.get(0);
        // Object o2 = list1.get(0);

    }
}
