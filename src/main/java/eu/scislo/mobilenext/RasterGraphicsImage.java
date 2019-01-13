package eu.scislo.mobilenext;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class RasterGraphicsImage implements EventHandler {

    private Image sourceImage;
    private FileChooser fileChooser = new FileChooser();
    private Stage stage;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    public Button read = new Button("Wczytaj");
    public Button clearImage = new Button("Wyczyść obrazek");
    public ScrollPane canvasScrollPane = new ScrollPane();


    private void configureFileChooser() {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Wszystkie obrazki", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        fileChooser.setTitle("Przeglądaj zdjęcia...");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
    }

    private void renderImage(String imagePath) {
        this.sourceImage = new Image(imagePath);
        int height = (int) sourceImage.getHeight();
        int width = (int) sourceImage.getWidth();
        this.canvas = new Canvas(width, height);
        this.canvasScrollPane.setPrefSize(256, 600);
        this.canvasScrollPane.setContent(canvas);
        this.canvasScrollPane.getStyleClass().add("source-image");

        graphicsContext = canvas.getGraphicsContext2D();

        graphicsContext.drawImage(sourceImage, 0, 0);

    }

    public RasterGraphicsImage(Stage stage) {
        this.stage = stage;
        this.renderImage("chewie.jpg");
        this.configureFileChooser();
        read.setOnAction(this);
        canvas.setOnMouseClicked(this);
    }


    @Override
    public void handle(Event event) {
        if (event instanceof ActionEvent) {
            System.out.println("Action");
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                // suggests GC to run, because we are rendering new image
                System.gc();
                System.runFinalization();
                this.renderImage(file.toURI().toString());
            }
        } else if (event instanceof MouseEvent) {
            System.out.println("Mouse");
        }
    }


}
