package eu.scislo.mobilenext;

import io.reactivex.subjects.BehaviorSubject;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class RasterGraphicsImage extends Node implements EventHandler {

    private Image sourceImage;
    private FileChooser fileChooser = new FileChooser();
    private Stage stage;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    public Button read = new Button("Wczytaj");
    public ScrollPane canvasScrollPane = new ScrollPane();
    public BehaviorSubject<RasterGraphicsEvent> changed = BehaviorSubject.create();

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
        this.canvasScrollPane.setPrefSize(407, 407);
        this.canvasScrollPane.setContent(canvas);
        this.canvasScrollPane.getStyleClass().add("source-image");
        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.drawImage(sourceImage, 0, 0);
        this.changed.onNext(new RasterGraphicsEvent(RasterGraphicsEventTypes.IMAGE_LOADED));
    }

    public RasterGraphicsImage(Stage stage) {
        this.stage = stage;
        this.renderImage("colors.png");
        this.configureFileChooser();
        read.setOnAction(this);
        canvas.setOnMouseClicked(this);
    }


    @Override
    public void handle(Event event) {
        if (event instanceof ActionEvent) {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                try {
                    String fileType = Files.probeContentType(file.toPath());
                    if (!(fileType.equals("image/jpeg") || fileType.equals("image/png"))) {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Nieobsługiwany typ pliku!");
                        alert.showAndWait();
                    } else {
                        // suggests GC to run, because we are rendering new image
                        System.gc();
                        System.runFinalization();
                        this.renderImage(file.toURI().toString());
                    }
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Nie można sprawdzić typu pliku!");
                    alert.showAndWait();
                }
            }
        } else if (event instanceof MouseEvent) {
            System.out.println("Mouse");
        }
    }


}
