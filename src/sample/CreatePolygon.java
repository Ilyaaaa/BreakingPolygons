package sample;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class CreatePolygon {
    private Controller controller;
    public CreatePolygon(Controller controller){
        this.controller = controller;
    }

    public void CreateP() {

        Polygon polygon = new Polygon();
        polygon = setPoint(polygon, 24, 24);
        polygon = setPoint(polygon, 55, 0);
        polygon = setPoint(polygon, 111, 45);
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
        setPolygon(polygon, controller.pane);
    }

    public void  setPolygon(Polygon polygon, AnchorPane rootPane){
        setDefaultStyle(polygon);
        Polygon tempPolygon = polygon;
        polygon.setOnMouseClicked(e -> test(tempPolygon));
        rootPane.getChildren().add(polygon);
    }

    public Polygon setPoint(Polygon polygon, double pointX, double pointY){
        polygon.getPoints().add(pointX);
        polygon.getPoints().add(pointY);
        return polygon;
    }

    public Polygon setDefaultStyle(Polygon polygon){
        polygon.setFill(Color.valueOf("#999999"));
        polygon.setStroke(Color.valueOf("#333333"));
        polygon.setStrokeWidth(2);
        return polygon;
    }

    public Polygon setClickedStyle(Polygon polygon){
        polygon.setStroke(Color.valueOf("#ee6f00"));
        polygon.setStrokeWidth(2);
        return polygon;
    }

    public void test(Polygon polygon){
        if(clickedPolygon == polygon){
            clickedPolygon = null;
            setDefaultStyle(polygon);
        }else{
            if(clickedPolygon != null) setDefaultStyle(clickedPolygon);
            clickedPolygon = polygon;
            setClickedStyle(polygon);
        }
    }

    Polygon clickedPolygon = null;
}
