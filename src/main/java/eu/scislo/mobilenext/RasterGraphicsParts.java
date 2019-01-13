package eu.scislo.mobilenext;

import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

public class RasterGraphicsParts {
    public Button clear = new Button("Czyść");
    public FlowPane flowPane = new FlowPane();

    public RasterGraphicsParts() {
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        for (int i = 0; i < 25; i++) {
            RasterGraphicsPart rasterGraphicsPart = new RasterGraphicsPart();
            flowPane.getChildren().add(rasterGraphicsPart.canvas);
        }
    }

}
