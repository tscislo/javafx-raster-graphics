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
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class RasterGraphicsApp extends Application {

    private RasterGraphicsImage rasterGraphicsImage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.rasterGraphicsImage = new RasterGraphicsImage(primaryStage);

        VBox leftVBox = new VBox();
        leftVBox.getStyleClass().add("left-pane");
        leftVBox.getChildren().addAll(rasterGraphicsImage.canvasScrollPane);

        VBox rightVBox = new VBox();
        rightVBox.getStyleClass().add("right-pane");
        rightVBox.setAlignment(Pos.TOP_LEFT);
        rightVBox.getChildren().addAll(rasterGraphicsImage.read);

        BorderPane rootPane = new BorderPane();
        Scene scene = new Scene(rootPane, 700, 400);
        scene.getStylesheets().add("application.css");
        primaryStage.setTitle("Raster Graphics");
        primaryStage.setScene(scene);

        primaryStage.show();
        rootPane.setRight(rightVBox);
        rootPane.setLeft(leftVBox);

    }


    public static void main(String[] args) {
        launch(args);
    }

}
