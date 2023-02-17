package mock;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public final class Circle {

    private double radius;

    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }
}
