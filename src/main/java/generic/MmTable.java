package generic;

import java.util.List;

public class MmTable<T extends Fruit> implements Table<T> {

    private List<T> data;

    public MmTable<T> order(String field, boolean asc) {
        return this;
    }

    public void create(List<T> data) {
        this.data = data;
    }
    @Override
    public List<T> getList() {
        return data;
    }
}
