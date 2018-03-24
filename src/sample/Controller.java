package sample;

import javafx.scene.layout.AnchorPane;

public class Controller {
    public AnchorPane pane;

    private CreatePolygon createPoligon;
    public Controller(){
        this.createPoligon = new CreatePolygon(this);
    }

    public void breakPolygon(){
        createPoligon.CreateP();
    }
}
