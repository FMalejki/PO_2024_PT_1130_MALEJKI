package agh.ics.oop.presenter;

import agh.ics.oop.OptionParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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


    public void setWorldMap() {
        this.worldMap = new RectangularMap(10, 10);;
        this.worldMap.addObserver(this);
        drawMap();
    }

    @Override
    public void mapChanged(WorldMap worldMap,String string){
        Platform.runLater(this::drawMap);
    }

    public void drawMap() {
        if (worldMap != null) {
            String mapRepresentation = worldMap.toString();
            infoLabel.setText(mapRepresentation);
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

}
