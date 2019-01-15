package eu.scislo.mobilenext;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;


public class RasterGraphicsPart {
    static int width = 41; // must be odd
    static int height = 41; // must be odd
    private Canvas canvas;
    private Color[][] colors;
    private GraphicsContext graphicsContext;

    RasterGraphicsPart(Color[][] colors) {
        this.colors = colors;
        this.canvas = new Canvas(width, height);
        this.canvas.getStyleClass().add("part");
        graphicsContext = canvas.getGraphicsContext2D();
    }

    public Canvas render() {
        WritableImage writableImage = new WritableImage(height, width);
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color col = colors[x][y];
                if (col != null)
                    pixelWriter.setColor(x, y, Color.color(
                            col.getRed(),
                            col.getGreen(),
                            col.getBlue()
                    ));
            }
        }
        graphicsContext.drawImage(writableImage, 0, 0);
        return this.canvas;
    }

    public String toString() {
        return Double.toString(this.getMeanRedPart());
    }

    double getMeanRedPart() {
        double redPart = 0;
        int all = 0;
        for (Color[] color : colors) {
            for (Color aColor : color) {
                if (aColor != null) {
                    redPart += aColor.getRed();
                    all++;
                }
            }
        }
        return redPart / all;
    }

}
