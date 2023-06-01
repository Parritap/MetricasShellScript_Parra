package org.project.application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application{



    //Antes de correr el programa, asegúrese de que la ruta del archivo en Paths.java sea la correcta
    //Un archivo de prueba, ha sido dejado en resources/data.txt
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/View.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gráficos del sistema");
        primaryStage.show();
    }
}