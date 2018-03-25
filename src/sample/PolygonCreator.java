package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.ArrayList;

public class PolygonCreator {
    private Controller controller;
    public ArrayList<Point> points = new ArrayList<>();

    public PolygonCreator(Controller controller){
        this.controller = controller;
    }

    public void createPolygon() {
        Polygon polygon = new Polygon();
        polygon = setPoint(polygon, 24, 24);
        polygon = setPoint(polygon, 55, 0);
        polygon = setPoint(polygon, 112, 45);
        polygon = setPoint(polygon, 112, 108);
        polygon = setPoint(polygon, 189, 169);
        polygon = setPoint(polygon, 226, 169);
        polygon = setPoint(polygon, 226, 198);
        polygon = setPoint(polygon, 172, 198);
        polygon = setPoint(polygon, 106, 146);
        polygon = setPoint(polygon, 40, 197);
        polygon = setPoint(polygon, 40, 222);
        polygon = setPoint(polygon, 0, 222);
        polygon = setPoint(polygon, 0, 197);
        polygon = setPoint(polygon, 58, 152);
        polygon = setPoint(polygon, 58, 50);
        polygon.setFill(Color.valueOf("#999999"));
        polygon.setStroke(Color.valueOf("#333333"));
        polygon.setStrokeWidth(2);
        controller.pane.getChildren().add(polygon);
    }

    public void breakPolygon() {
        Point point1 = points.get(points.size() - 1);
        Point point2 = points.get(0);

        for (int i = 1; i < points.size(); i++){
            System.out.println(getDistance(point1, point2));
            if (getDistance(point1, point2) < getDistance(point2, points.get(i))){
                point1 = point2;
                point2 = points.get(i);
            }
        }

        System.out.println("max" + getDistance(point1, point2));
    }

    private double getDistance(Point point1, Point point2){
        return Math.sqrt(Math.pow(point1.x - point2.x, 2) + Math.pow(point1.y - point2.y, 2));
    }

    private Polygon setPoint(Polygon polygon, double pointX, double pointY){
        points.add(new Point(pointX, pointY));

        polygon.getPoints().add(pointX);
        polygon.getPoints().add(pointY);
        return polygon;
    }

    private class Point {
        public double x;
        public double y;

        Point(double x, double y){
            this.x = x;
            this.y = y;
        }
    }
}
