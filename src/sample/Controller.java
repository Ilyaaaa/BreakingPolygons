package sample;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class Controller {
    public AnchorPane pane;

    private  CreatePoligon createPoligon;
    public Controller(){
        this.createPoligon = new CreatePoligon(this);
    }

    public void breakPolygon(){
        createPoligon.CreateP();
    }
}
