package github.mariuszsienkiewicz.controller;

import github.mariuszsienkiewicz.model.Cell;
import github.mariuszsienkiewicz.view.MainView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class MainController {

    private final MainView view;

    private Cell[][] cells;
    private final int timeBetweenStepsInMilliseconds = 50;
    private final int numberOfCells = 100;
    private final double aliveStateProbability = 0.25;

    public MainController(MainView view) {
        this.view = view;

        populateCells();
        createTimeline();
    }

    public void createTimeline() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(this.timeBetweenStepsInMilliseconds), e->nextStep()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void populateCells() {
        this.cells = new Cell[this.numberOfCells][this.numberOfCells];
        for (int x = 0; x < this.numberOfCells; x++) {
            for (int y = 0; y < this.numberOfCells; y++) {
                this.cells[x][y] = new Cell(Math.random() < this.aliveStateProbability, Cell.getRandomColor());
            }
        }
    }

    public void nextStep() {
        generateNextStep();
        updateView();
    }

    public void generateNextStep() {
        Cell[][] tmp = Cell.clone2DCellArray(this.cells);

        for (int x = 0; x < this.numberOfCells; x++) {
            for (int y = 0; y < this.numberOfCells; y++) {
                Cell[] neighbours = Cell.getNeighbours(tmp, x, y);
                int numberOfAliveNeighbours = Cell.aliveCells(neighbours);

                if (tmp[x][y].isAlive()) {
                    this.cells[x][y].setAlive(numberOfAliveNeighbours == 2 || numberOfAliveNeighbours == 3);
                } else {
                    this.cells[x][y].setAlive(numberOfAliveNeighbours == 3);
                }
            }
        }
    }

    private void updateView() {
        this.view.update(this.cells);
    }
}
