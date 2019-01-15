package eu.scislo.mobilenext;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class RasterGraphicsApp extends Application {

    private RasterGraphicsImage rasterGraphicsImage;
    private RasterGraphicsParts rasterGraphicsParts;
    public Button exit = new Button("Zakończ");

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.rasterGraphicsImage = new RasterGraphicsImage(primaryStage);
        this.rasterGraphicsParts = new RasterGraphicsParts();

        VBox leftVBox = new VBox();
        leftVBox.getStyleClass().add("left-pane");
        leftVBox.getChildren().addAll(rasterGraphicsImage.canvasScrollPane);

        VBox rightVBox = new VBox();
        rightVBox.setSpacing(10);
        HBox buttonsBox = new HBox();
        rightVBox.getStyleClass().add("right-pane");
        buttonsBox.setSpacing(10);
        buttonsBox.getChildren().addAll(rasterGraphicsImage.read, rasterGraphicsParts.clear, this.exit);
        rightVBox.getChildren().addAll(buttonsBox, rasterGraphicsParts.flowPane);

        HBox main = new HBox();
        main.setAlignment(Pos.CENTER);
        Scene scene = new Scene(main, 950, 480);
        primaryStage.setMinWidth(550);
        primaryStage.setMinHeight(480);
        scene.getStylesheets().add("application.css");


        primaryStage.setTitle("Raster Graphics");
        primaryStage.setScene(scene);

        primaryStage.initStyle(StageStyle.UNIFIED);
        primaryStage.show();
        main.getChildren().addAll(leftVBox, rightVBox);

        this.rasterGraphicsImage
                .changed
                .filter(rasterGraphicsEvent -> rasterGraphicsEvent.type == RasterGraphicsEventTypes.IMAGE_LOADED)
                .subscribe(rasterGraphicsEvent -> {
                    this.rasterGraphicsParts.clear();
                });

        this.rasterGraphicsImage
                .changed
                .filter(rasterGraphicsEvent -> rasterGraphicsEvent.type == RasterGraphicsEventTypes.NEW_SELECTION)
                .subscribe(rasterGraphicsEvent -> {
                    RasterGraphicsEventPayload<RasterGraphicsPart> rasterGraphicsEventPayload = (RasterGraphicsEventPayload<RasterGraphicsPart>) rasterGraphicsEvent;
                    this.rasterGraphicsParts.add(rasterGraphicsEventPayload.payload);
                });

        this.exit.setOnAction((event) -> {
            Platform.exit();
            System.exit(0);
        });

    }


    public static void main(String[] args) {
        launch(args);
    }

}
