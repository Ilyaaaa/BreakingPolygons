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
        controller.pane.getChildren().add(polygon);
    }

    public void breakPolygon() {
//        Point point1 = points.get(points.size() - 1);
//        Point point2 = points.get(0);
//
//        for (int i = 1; i < points.size(); i++){
//            if (point1.distance(point2) > point2.distance(points.get(i))){
//                point1 = point2;
//                point2 = points.get(i);
//            }
//        }

        for(int num1 = 0; num1 < points.size(); num1++){
            double x1 = points.get(num1).getX();
            double y1 = points.get(num1).getY();

            int num2 = num1 + 1;
            if(num2 >= points.size()) num2 -= points.size();
            double x2 = points.get(num2).getX();
            double y2 = points.get(num2).getY();

            int num3 = num1 + 2;
            if(num3 >= points.size()) num3 -= points.size();
            double x3 = points.get(num3).getX();
            double y3 = points.get(num3).getY();

            double midPointX = (x1 + x3)/2;
            double midPointY = (y1 + y3)/2;

            double distance = Math.sqrt(Math.pow(x2 - midPointX, 2) + Math.pow(y2 - midPointY, 2));
            double speedX = Math.abs(x2 - midPointX)/distance;
            double speedY = Math.abs(y2 - midPointY)/distance;
            double movePointX = midPointX, movePointY = midPointY;

            int distanceCounter;
            if((int)distance == distance) distanceCounter = (int)distance - 1;
            else distanceCounter = (int)distance;

            if(midPointX > x2) movePointX -= speedX * distanceCounter;
            else movePointX += speedX * distanceCounter;

            if(midPointY > y2) movePointY -= speedY * distanceCounter;
            else movePointY += speedY * distanceCounter;

            points.get(num1).setAngleCorrect(!polygon.contains(movePointX, movePointY));

            /*Circle test = new Circle();
            test.setRadius(1);
            test.setCenterX(movePointX);
            test.setCenterY(movePointY);
            controller.pane.getChildren().add(test);*/
        }

        for (int i = 0; i < points.size(); i++){
            System.out.println("1");
            Point point = points.get(i);

            Point nextPoint;
            if (i == points.size() - i) nextPoint = points.get(0);
            else nextPoint = points.get(i + 1);

            if (!(point.getAngleCorrect() && !nextPoint.getAngleCorrect())) continue;

            double vectorX = nextPoint.getX() - point.getX();
            double vectorY = nextPoint.getY() - point.getY();

            double c = (vectorX * point.getX()) + (vectorY * point.getY());

            Point2D p1 = new Point2D(point.getX() + 1, ((-vectorX * point.getX() + 1) + c) / vectorY);
            Point2D p2 = new Point2D(point.getX() - 1, ((-vectorX * point.getX() - 1) + c) / vectorY);
            int counter;
            double x;
            if (polygon.contains(p1)){
                counter = 1;
                x = p1.getX();
            } else if (polygon.contains(p2)){
                counter = -1;
                x = p2.getX();
            } else {
                System.out.println("Error");
                break;
            }

            double y = ((-vectorX * x - 1) + c) / vectorY;
            while (!polygon.contains(x, y)) {
                x += counter;
                y = ((-vectorX * x - 1) + c) / vectorY;

                Circle test = new Circle();
                test.setRadius(1);
                test.setCenterX(x);
                test.setCenterY(y);
                test.setFill(Color.valueOf("#F41219"));
                controller.pane.getChildren().add(test);
                if(x < 0 || y < 0) {
                    System.out.println("Error2");
                    break;
                }
            }

            point.setAngleCorrect(false);
        }
        System.out.println("Done");
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
