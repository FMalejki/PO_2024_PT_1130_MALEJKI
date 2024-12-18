package agh.ics.oop.presenter;

import agh.ics.oop.model.ConsoleMapDisplay;
import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.WorldMap;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SimulationPresenter implements MapChangeListener {
    @FXML
    public Label infoLabel;
    private WorldMap worldMap;

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
        this.worldMap.addObserver(this);
    }

    @Override
    public void mapChanged(WorldMap worldMap,String string){
        drawMap();
    }

    public void drawMap() {
        if (worldMap != null) {
            // Na razie wyświetlamy mapę jako tekst (to później zastąpi siatka kontrolek)
            String mapRepresentation = worldMap.toString(); // Metoda toString() powinna zwrócić reprezentację mapy
            infoLabel.setText(mapRepresentation); // infoLabel to identyfikator kontrolki w pliku FXML
        }
    }
}
