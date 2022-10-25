package guava;

import com.google.common.collect.BoundType;
import com.google.common.collect.TreeMultiset;

public class TreemultiSetTest {
    public static void main(String[] args) {
        TreeMultiset<String> set = TreeMultiset.create();
        set.add("a", 1);
        set.add("b", 2);
        set.add("c", 3);

        System.out.println(set.tailMultiset("a", BoundType.CLOSED).headMultiset("c", BoundType.CLOSED).size());
    }
}
