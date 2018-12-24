package gui;

import javafx.application.Application;

import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Stage stage = primaryStage;
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, 1280, 720);
        MainCanvas mainCanvas = new MainCanvas(1280, 720);
        root.getChildren().add(mainCanvas);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("sample.fxml").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {//关闭窗口结束所有线程
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}



