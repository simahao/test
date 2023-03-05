package hz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

    List<Bean> getList() {
        Bean b1 = new Bean(1, "1");
        Bean b2 = new Bean(2, "2");
        Bean b3 = new Bean(3, "3");
        List<Bean> list = new ArrayList<>();
        list.add(b1);
        list.add(b2);
        list.add(b3);
        return list;
    }

    public static void main(String[] args) {
        Application al = new Application();
        List<Bean> list = al.getList();
        Map<Integer, Bean> map = new HashMap<>();

        for (Bean node : list) {
            map.put(node.getId(), node);
        }

        Bean tmp = null;
        for (Bean node : list) {
            if (node.getId().equals(1)) {
                tmp = map.get(node.getId());
                tmp.setName("11");
            } else if (node.getId().equals(2)) {
                tmp = map.get(node.getId());
                tmp.setName("22");
            } else {
                tmp = map.get(node.getId());
                tmp.setName("33");
            }
        }

        for (Bean node : list) {
            System.out.println("node id:" + node.getId() + "  node name:" + node.getName());
        }

        for (Map.Entry<Integer, Bean> entry: map.entrySet()) {
            System.out.println("node id:" + entry.getKey() + "  node name:" + entry.getValue().getName());
        }
    }
}
