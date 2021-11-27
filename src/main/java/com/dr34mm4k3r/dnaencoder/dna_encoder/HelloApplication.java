package com.dr34mm4k3r.dnaencoder.dna_encoder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setResizable(true);
        stage.setTitle("DNA Encoder!");
        stage.setScene(scene);
        stage.show();






    }

    public static void main(String[] args) {
        launch();
    }
}