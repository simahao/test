package generic;

public class Fruit {

    private String name;

    private String color;

    private String area;

    Fruit(String name, String color, String area) {
        this.name = name;
        this.color = color;
        this.area = area;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

}
