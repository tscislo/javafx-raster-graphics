package eu.scislo.mobilenext;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class RasterGraphicsApp extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Group rootPane = new Group();
        Scene scene = new Scene(rootPane, 512, 256);

        Canvas canvas = new Canvas(512, 256);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();


        System.out.print(getClass());
        Image image = new Image("lenna256px.png");


        graphicsContext.drawImage(image, 0, 0);

        int heigth = (int) image.getHeight();
        int width = (int) image.getWidth();

        PixelReader reader = image.getPixelReader();
        WritableImage writableImage = new WritableImage(heigth, width);

        PixelWriter pixelWriter = writableImage.getPixelWriter();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < heigth; y++) {
                Color color = reader.getColor(x, y);
                pixelWriter.setColor(x, y, Color.color(
                        color.getRed() / 3,
                        color.getGreen() / 3,
                        color.getBlue() / 3
                ));
            }
        }

        graphicsContext.drawImage(writableImage, 256, 0);

        rootPane.getChildren().add(canvas);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
