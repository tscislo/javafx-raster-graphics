package eu.scislo.mobilenext;

import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;

public class RasterGraphicsParts {
    public Button clear = new Button("Czyść");
    public FlowPane flowPane = new FlowPane();
    private ArrayList<RasterGraphicsPart> rasterGraphicsParts = new ArrayList<>();

    public RasterGraphicsParts() {
        flowPane.setHgap(10);
        flowPane.setVgap(10);
    }

    public void add(RasterGraphicsPart rasterGraphicsPart) {
        this.rasterGraphicsParts.add(rasterGraphicsPart);
        this.flowPane.getChildren().add(rasterGraphicsPart.render());
    }

}
