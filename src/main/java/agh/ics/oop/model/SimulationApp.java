package agh.ics.oop.model;

import agh.ics.oop.Simulation;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SimulationApp extends javafx.application.Application {
    public void start(Stage primaryStage) throws IOException {
        primaryStage.show();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot = loader.load();
        SimulationPresenter presenter = loader.getController();
        presenter.setWorldMap();

        //List<MoveDirection> directions = List.of(MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD);
        //List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        //WorldMap rectangularMap = new RectangularMap(10, 10);

        //presenter.setWorldMap(rectangularMap);
        //presenter.drawMap();

        configureStage(primaryStage, viewRoot);
        primaryStage.show();

        //Simulation simulation = new Simulation(positions, directions, rectangularMap);
        //simulation.run();
    }

    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }



}
