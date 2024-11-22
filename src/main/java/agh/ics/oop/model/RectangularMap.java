package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap{

    public RectangularMap(int width, int height){
        start = new Vector2d(0,0);
        end = new Vector2d(width -1, height -1);
    }

    public String toString() {
        return visualizer.draw(this.start, this.end);
    }
}