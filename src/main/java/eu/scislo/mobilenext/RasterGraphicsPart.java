package eu.scislo.mobilenext;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class RasterGraphicsPart {
    private Image image;
    public Canvas canvas;
    private GraphicsContext graphicsContext;

    public RasterGraphicsPart() {
        this.canvas = new Canvas(41, 41);
        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillRect(0, 0, 41, 41);
    }

}
