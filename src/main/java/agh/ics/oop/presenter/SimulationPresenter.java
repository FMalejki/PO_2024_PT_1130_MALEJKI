package agh.ics.oop.presenter;

import agh.ics.oop.OptionParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private WorldMap simulationMap;

    @FXML
    private GridPane gridDisplay;
    @FXML
    private TextField inputMovesField;
    @FXML
    private Label statusLabel;

    private int gridWidth;
    private int gridHeight;
    private int cellWidth = 50;
    private int cellHeight = 50;

    private final int maxGridWidth = 300;
    private final int maxGridHeight = 300;

    public void initializeMap(WorldMap map) {
        this.simulationMap = map;
    }

    private void drawMap() {
        clearGrid();
        calculateGridBounds();
        addLabelsToGrid();
        renderMapElements();
        gridDisplay.setGridLinesVisible(true);
    }

    private void calculateGridBounds() {
        int xStart = simulationMap.getCurrentBounds().start().getX();
        int yStart = simulationMap.getCurrentBounds().start().getY();
        int xEnd = simulationMap.getCurrentBounds().end().getX();
        int yEnd = simulationMap.getCurrentBounds().end().getY();

        gridWidth = xEnd - xStart + 1;
        gridHeight = yEnd - yStart + 1;

        cellWidth = Math.round(maxGridWidth / gridWidth);
        cellHeight = Math.round(maxGridHeight / gridHeight);

        cellHeight = Math.min(cellHeight, cellWidth);
        cellWidth = cellHeight;
    }

    private void addLabelsToGrid() {
        gridDisplay.getColumnConstraints().add(new ColumnConstraints(cellWidth));
        gridDisplay.getRowConstraints().add(new RowConstraints(cellHeight));
        Label cornerLabel = new Label("y/x");
        gridDisplay.add(cornerLabel, 0, 0);
        GridPane.setHalignment(cornerLabel, HPos.CENTER);

        for (int col = 0; col < gridWidth; col++) {
            Label columnLabel = new Label(Integer.toString(col));
            GridPane.setHalignment(columnLabel, HPos.CENTER);
            gridDisplay.getColumnConstraints().add(new ColumnConstraints(cellWidth));
            gridDisplay.add(columnLabel, col + 1, 0);
        }

        for (int row = 0; row < gridHeight; row++) {
            Label rowLabel = new Label(Integer.toString(gridHeight - row - 1));
            GridPane.setHalignment(rowLabel, HPos.CENTER);
            gridDisplay.getRowConstraints().add(new RowConstraints(cellHeight));
            gridDisplay.add(rowLabel, 0, row + 1);
        }
    }

    private void renderMapElements() {
        for (int x = 0; x < gridWidth; x++) {
            for (int y = 0; y < gridHeight; y++) {
                Vector2d position = new Vector2d(x, gridHeight - y - 1);
                String elementLabel = simulationMap.isOccupied(position) ? simulationMap.objectAt(position).toString() : " ";
                Label element = new Label(elementLabel);
                gridDisplay.add(element, x + 1, y + 1);
                GridPane.setHalignment(element, HPos.CENTER);
            }
        }
    }

    private void clearGrid() {
        gridDisplay.getChildren().retainAll(gridDisplay.getChildren().get(0));
        gridDisplay.getColumnConstraints().clear();
        gridDisplay.getRowConstraints().clear();
    }

    @Override
    public void mapChanged(WorldMap updatedMap, String updateMessage) {
        initializeMap(updatedMap);
        Platform.runLater(() -> {
            drawMap();
            statusLabel.setText(updateMessage);
        });
    }

    @FXML
    private void onSimulationStartClicked() {
        String moveSequence = inputMovesField.getText();
        String[] parsedMoves = moveSequence.split(" ");
        List<MoveDirection> moveDirections = OptionParser.refactor(parsedMoves);

        List<Vector2d> initialPositions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        GrassField map = new GrassField(10);
        map.addObserver(this);

        Simulation simulation = new Simulation(initialPositions, moveDirections, map);
        SimulationEngine engine = new SimulationEngine(List.of(simulation));

        statusLabel.setText("Simulation started with moves: " + moveSequence);
        new Thread(engine::runSync).start();
    }
}
