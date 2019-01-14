package eu.scislo.mobilenext;

import io.reactivex.subjects.BehaviorSubject;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class RasterGraphicsImage implements EventHandler {

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
        canvas.setOnMouseClicked(this);
        this.changed.onNext(new RasterGraphicsEvent(RasterGraphicsEventTypes.IMAGE_LOADED));
    }

    public RasterGraphicsImage(Stage stage) {
        this.stage = stage;
        this.renderImage("colors.png");
        this.configureFileChooser();
        read.setOnAction(this);
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
                        this.renderImage(file.toURI().toString());
                    }
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Nie można sprawdzić typu pliku!");
                    alert.showAndWait();
                }
            }
        } else if (event instanceof MouseEvent) {
            MouseEvent mouseEvent = (MouseEvent) event;
            int clickX = (int) mouseEvent.getX();
            int clickY = (int) mouseEvent.getY();
            PixelReader reader = sourceImage.getPixelReader();

            Color colors[][] = new Color[RasterGraphicsPart.width][RasterGraphicsPart.height];

            int startX = clickX - (RasterGraphicsPart.width - 1) / 2 - 1;
            int startY = clickY - (RasterGraphicsPart.height - 1) / 2 - 1;

            // TODO: Handle case when click on the left or top edge!
            for (int x = startX; x < clickX + (RasterGraphicsPart.width - 1) / 2; x++) {
                for (int y = startY; y < clickY + (RasterGraphicsPart.height - 1) / 2; y++) {
                    colors[x - startX][y - startY] = reader.getColor(x, y);
                }
            }
            RasterGraphicsPart rasterGraphicsPart = new RasterGraphicsPart(colors);
            this.changed.onNext(new RasterGraphicsEventPayload<>(RasterGraphicsEventTypes.NEW_SELECTION, rasterGraphicsPart));
        }
    }


}
