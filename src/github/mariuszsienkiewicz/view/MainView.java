package github.mariuszsienkiewicz.view;

import github.mariuszsienkiewicz.controller.MainController;
import github.mariuszsienkiewicz.model.Cell;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainView {

    private final String VIEW_TITLE = "Game of Life";

    private Stage root;
    private GraphicsContext graphicsContext;

    public MainView(Stage root) {
        this.root = root;
        addController();
    }

    public void buildView() {
        VBox vBox = new VBox(0);
        Canvas canvas = new Canvas(500, 500);

        vBox.getChildren().addAll(canvas);

        Scene scene = new Scene(vBox);
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.root.setTitle(VIEW_TITLE);
        this.root.setScene(scene);
        this.root.show();
    }

    public void update(Cell[][] cells) {
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells.length; y++) {
                if (cells[x][y].isAlive()) {
                    this.graphicsContext.setFill(cells[x][y].getColor());
                } else {
                    this.graphicsContext.setFill(Color.BLACK);
                }
                this.graphicsContext.fillRect(x * 10, y * 10, 10, 10);
            }
        }
    }

    public void addController() {
        new MainController(this);
    }

    public void show() {
        this.root.show();
    }

    public void display() {
        buildView();
        show();
    }
}
