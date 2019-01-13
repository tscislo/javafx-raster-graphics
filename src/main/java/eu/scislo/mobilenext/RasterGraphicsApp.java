package eu.scislo.mobilenext;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class RasterGraphicsApp extends Application {

    private RasterGraphicsImage rasterGraphicsImage;
    private RasterGraphicsParts rasterGraphicsParts;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.rasterGraphicsImage = new RasterGraphicsImage(primaryStage);
        this.rasterGraphicsParts = new RasterGraphicsParts();

        VBox leftVBox = new VBox();
        leftVBox.getStyleClass().add("left-pane");
        leftVBox.getChildren().addAll(rasterGraphicsImage.canvasScrollPane);

        VBox rightVBox = new VBox();
        rightVBox.setSpacing(10);
        rightVBox.setMaxWidth(300);
        HBox buttonsBox = new HBox();
        rightVBox.getStyleClass().add("right-pane");
        rightVBox.setAlignment(Pos.TOP_LEFT);
        buttonsBox.setSpacing(10);
        buttonsBox.getChildren().addAll(rasterGraphicsImage.read, rasterGraphicsParts.clear);
        rightVBox.getChildren().addAll(buttonsBox, rasterGraphicsParts.flowPane);

        BorderPane rootPane = new BorderPane();
        Scene scene = new Scene(rootPane, 750, 480);
        scene.getStylesheets().add("application.css");
        primaryStage.setTitle("Raster Graphics");
        primaryStage.setScene(scene);

        primaryStage.setResizable(false);
        primaryStage.show();
        rootPane.setRight(rightVBox);
        rootPane.setLeft(leftVBox);

        this.rasterGraphicsImage.changed.subscribe(rasterGraphicsEvent -> {
           System.out.println(rasterGraphicsEvent.type);
        });

    }


    public static void main(String[] args) {
        launch(args);
    }

}
