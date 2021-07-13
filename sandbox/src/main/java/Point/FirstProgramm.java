package Point;

public class FirstProgramm {

    public static void main(String[] args) {

        Point p1 = new Point(1, 3);
        Point p2 = new Point(10, 9);


        System.out.println("Distance between them is " + Point.distance(p1, p2));

    }
}
