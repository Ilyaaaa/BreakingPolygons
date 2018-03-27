package sample;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.ArrayList;

public class PolygonCreator {
    private Controller controller;

    private Polygon polygon = new Polygon();
    private ArrayList<Point> points = new ArrayList<>();

    public PolygonCreator(Controller controller){
        this.controller = controller;
    }

    public void createPolygon() {
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
        for (int i = 0; i < points.size(); i++){
            Point p0;
            if (i == 0) p0 = points.get(points.size() - 1);
            else p0 = points.get(i - 1);
            Point p1 = points.get(i);
            Point p2;
            if (i == points.size() - 1) p2 = points.get(0);
            else p2 = points.get(i + 1);

            Point2D midPoint = p0.midpoint(p2);
            double secondX = p1.getX();
            double secondY = p1.getY();

            int xCounter = 0;
            if (secondX > midPoint.getX()) xCounter = -1;
            else if (secondX < midPoint.getX()) xCounter = 1;

            int yCounter = 0;
            if (secondY > midPoint.getY()) yCounter = -1;
            else if (secondY < midPoint.getY()) yCounter = 1;

            double difference = Math.abs(midPoint.getX()) - secondX/Math.abs(midPoint.getY() - secondY);
            double angle = 100/90 * 0.01 * midPoint.angle(secondX, secondY);
            if (difference < 1) {
                secondY += yCounter * (angle + 2);
                secondX += xCounter * (1 - angle + 2);
            } else if (difference > 1) {
                secondY += yCounter * (1 - angle + 2);
                secondX += xCounter * (angle + 2);
            }
            else {
                secondX += xCounter * 2;
                secondY += yCounter * 2;
            }

            Circle test = new Circle();
                test.setRadius(1);
                test.setCenterX(secondX);
                test.setCenterY(secondY);
                controller.pane.getChildren().add(test);
            System.out.println(!polygon.contains(new Point2D(secondX, secondY)));
        }


        Point point1 = points.get(points.size() - 1);
        Point point2 = points.get(0);

        for (int i = 1; i < points.size(); i++){
            if (point1.distance(point2) > point2.distance(points.get(i))){
                point1 = point2;
                point2 = points.get(i);
            }
        }
    }

    private Polygon setPoint(Polygon polygon, double pointX, double pointY){
        points.add(new Point(pointX, pointY));

        polygon.getPoints().add(pointX);
        polygon.getPoints().add(pointY);
        return polygon;
    }

    private class Point extends Point2D {
        private boolean isAngleCorrect;

        Point(double x, double y){
            super(x, y);
        }

        public boolean getAngleCorrect() { return isAngleCorrect; }

        public void setAngleCorrect(boolean angle) { isAngleCorrect = angle; }
    }
}
