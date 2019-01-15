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
        this.clear.setOnAction(event -> {
            this.clear();
        });
    }

    public void add(RasterGraphicsPart rasterGraphicsPart) {
        this.rasterGraphicsParts.add(rasterGraphicsPart);
        this.rasterGraphicsParts.sort(new RasterGraphicsPartComparator());
        for (RasterGraphicsPart rasterGraphicsPartItem : rasterGraphicsParts) {
            this.flowPane.getChildren().add(rasterGraphicsPartItem.render());
        }
    }

    public void clear() {
        this.rasterGraphicsParts.clear();
        this.flowPane.getChildren().clear();
    }

}
