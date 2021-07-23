package github.mariuszsienkiewicz;

import github.mariuszsienkiewicz.model.Cell;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    // Cells settings
    private Cell[][] cells;
    double aliveStateProbability = 0.25;
    private final int numberOfCells = 100;

    // Graphics settings
    int timer = 50;
    private GraphicsContext gc;

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox hbox = new VBox(0);
        Canvas canvas = new Canvas(500, 500);

        hbox.getChildren().addAll(canvas);

        Scene scene = new Scene(hbox);
        this.gc = canvas.getGraphicsContext2D();
        primaryStage.setTitle("Game of life");
        primaryStage.setScene(scene);

        populateCells();
        createTimeline();
        primaryStage.show();
    }

    public void populateCells() {
        this.cells = new Cell[numberOfCells][numberOfCells];
        for (int x = 0; x < numberOfCells; x++) {
            for (int y = 0; y < numberOfCells; y++) {
                this.cells[x][y] = new Cell(Math.random() < aliveStateProbability, Cell.getRandomColor());
            }
        }
    }

    public void createTimeline() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(timer), e->run()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void run() {
        generateNextStep();
        draw();
    }

    public void generateNextStep() {
        Cell[][] tmp = Cell.clone2DCellArray(this.cells);

        for (int x = 0; x < numberOfCells; x++) {
            for (int y = 0; y < numberOfCells; y++) {
                Cell[] neighbours = Cell.getNeighbours(tmp, x, y);
                int aliveCounter = Cell.aliveCells(neighbours);

                if (tmp[x][y].isAlive()) {
                    this.cells[x][y].setAlive(aliveCounter == 2 || aliveCounter == 3);
                } else {
                    this.cells[x][y].setAlive(aliveCounter == 3);
                }
            }
        }
    }

    public void draw() {
        for (int x = 0; x < numberOfCells; x++) {
            for (int y = 0; y < numberOfCells; y++) {
                if (this.cells[x][y].isAlive()) {
                    this.gc.setFill(this.cells[x][y].getColor());
                } else {
                    this.gc.setFill(Color.BLACK);
                }
                this.gc.fillRect(x * 10, y * 10, 10, 10);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
