package eu.scislo.mobilenext;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class RasterGraphicsParts {
    public Button clear = new Button("Czyść");
    public FlowPane flowPane = new FlowPane();
    private int limit = 25;
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
        if (this.rasterGraphicsParts.size() == this.limit + 1) {
            this.rasterGraphicsParts.remove(this.limit);
        }
        this.flowPane.getChildren().clear();
        for (RasterGraphicsPart rasterGraphicsPartItem : rasterGraphicsParts) {
            VBox vbox = new VBox();
            vbox.getChildren().add(rasterGraphicsPartItem.render());
            vbox.getStyleClass().add("part");
            this.flowPane.getChildren().add(vbox);
        }
    }

    public void clear() {
        this.rasterGraphicsParts.clear();
        this.flowPane.getChildren().clear();
    }

}
