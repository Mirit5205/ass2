import java.util.List;

public class RectangleTest1 {
    public static void main (String[] args) {
        Rectangle r = new Rectangle(new Point(5,5), 10, 10);
        Line l = new Line(5,-5,20,13);
        List<Point> list = r.intersectionPoints(l);
        System.out.println(list);
    }
}
