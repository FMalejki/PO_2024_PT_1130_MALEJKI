package agh.ics.oop.presenter;

import agh.ics.oop.OptionParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    @FXML
    public Label infoLabel;
    @FXML
    public Button startButton;
    @FXML
    private TextField movementTextField;
    private WorldMap worldMap;
    @FXML
    private Label movementDescriptionLabel;
    @FXML
    private GridPane mapGrid;


    public void setWorldMap() {
        this.worldMap = new GrassField(10);
        this.worldMap.addObserver(this);
        drawMap();
    }

    @Override
    public void mapChanged(WorldMap worldMap,String string){
        Platform.runLater(this::drawMap);
    }

    public void drawMap() {
        if (worldMap == null) return;

        clearGrid();

        Vector2d lowerLeft = worldMap.getCurrentBounds().start();
        Vector2d upperRight = worldMap.getCurrentBounds().end();

        int rows = upperRight.getY() - lowerLeft.getY() + 1;
        int cols = upperRight.getX() - lowerLeft.getX() + 1;

        for (int i = 0; i < rows; i++) {
            mapGrid.getRowConstraints().add(new RowConstraints(40));
        }
        for (int j = 0; j < cols; j++) {
            mapGrid.getColumnConstraints().add(new ColumnConstraints(40));
        }

        for (int j = 0; j < cols-1; j++) {
            Label columnLabel = new Label(String.valueOf(lowerLeft.getX() + j));
            GridPane.setHalignment(columnLabel, HPos.CENTER);
            mapGrid.add(columnLabel, j + 1, 0); // Nagłówki w pierwszym wierszu
        }

        // Dodawanie nagłówków wierszy (Y)
        for (int i = 0; i < rows-1; i++) {
            Label rowLabel = new Label(String.valueOf(upperRight.getY() - i));
            GridPane.setHalignment(rowLabel, HPos.CENTER);
            mapGrid.add(rowLabel, 0, i + 1); // Nagłówki w pierwszej kolumnie
        }

        for (int y = lowerLeft.getY(); y <= upperRight.getY(); y++) {
            for (int x = lowerLeft.getX(); x <= upperRight.getX(); x++) {
                Vector2d position = new Vector2d(x, y);
                String mapElement = String.valueOf(worldMap.objectAt(position));
                Label cell = new Label(mapElement != null ? mapElement : "");

                cell.setStyle("-fx-border-color: black; -fx-alignment: center;");
                mapGrid.add(cell, x - lowerLeft.getX(), upperRight.getY() - y);
            }
        }
    }

    public List<MoveDirection> getMoveDirections() {
        String input = movementTextField.getText();
        try {
            return OptionParser.refactor(input.split("\\s+"));
        } catch (IllegalArgumentException e) {
            System.out.println("Error parsing moves:"+e.getMessage());
            return List.of();
        }
    }

    @FXML
    private void onSimulationStartClicked() {
        List<MoveDirection> directions = getMoveDirections();
        if (directions.isEmpty()) {
            movementDescriptionLabel.setText("Nieprawidłowe ruchy. Spróbuj ponownie.");
        } else {
            movementDescriptionLabel.setText("Uruchamianie symulacji z ruchami: " + directions);
            System.out.println("Ruchy: " + directions);

            List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
            Simulation simulation = new Simulation(positions, directions, worldMap);
            SimulationEngine engine = new SimulationEngine(List.of(simulation));
            engine.runAsyncThreadPool();
        }
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0));
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }



}
