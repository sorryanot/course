public class Point {
    private double x;
    private double y;

    public Point(double x1, double y1) {
        this.x = x1;
        this.y = y1;
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
    }


}
