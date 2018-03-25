package sample;

import javafx.scene.layout.AnchorPane;

public class Controller {
    public AnchorPane pane;

    private PolygonCreator polygonCreator;
    public Controller(){
        polygonCreator = new PolygonCreator(this);
    }

    public void breakPolygon(){
        polygonCreator.createPolygon();
        polygonCreator.breakPolygon();
    }
}
