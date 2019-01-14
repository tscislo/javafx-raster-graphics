package eu.scislo.mobilenext;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * TODO: Implement comparator!
 */
public class RasterGraphicsPart {
    public static int width = 41; // must be odd
    public static int height = 41; // must be odd
    public Canvas canvas;
    public Color[][] colors;
    private GraphicsContext graphicsContext;

    public RasterGraphicsPart(Color[][] colors) {
        this.colors = colors;
        this.canvas = new Canvas(width, height);
        graphicsContext = canvas.getGraphicsContext2D();
    }

    public Canvas render() {
        WritableImage writableImage = new WritableImage(height, width);
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixelWriter.setColor(x, y, Color.color(
                        colors[x][y].getRed(),
                        colors[x][y].getGreen(),
                        colors[x][y].getBlue()
                ));
            }
        }
        graphicsContext.drawImage(writableImage, 0, 0);
        return this.canvas;
    }

}
