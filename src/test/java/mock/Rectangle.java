package mock;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Rectangle {
    private double width;

    private double height;

    public double getArea() {
        return width * height;
    }
}


