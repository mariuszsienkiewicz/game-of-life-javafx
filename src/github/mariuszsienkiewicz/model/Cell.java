package github.mariuszsienkiewicz.model;

import javafx.scene.paint.Color;

import java.util.concurrent.ThreadLocalRandom;

public class Cell {
    private boolean alive;
    private Color color;

    public Cell(boolean alive, Color color) {
        this.alive = alive;
        this.color = color;
    }

    public Cell(boolean alive) {
        this.alive = alive;
        this.color = Color.BLACK;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static int aliveCells(Cell[] cells) {
        int aliveCounter = 0;
        for (Cell neighbour:
                cells) {
            if (neighbour.isAlive()) {
                aliveCounter++;
            }
        }

        return aliveCounter;
    }

    public static Cell[][] clone2DCellArray(Cell[][] cells) {
        Cell[][] tmp = new Cell[cells.length][cells.length];
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells.length; y++) {
                tmp[x][y] = new Cell(cells[x][y].isAlive(), cells[x][y].getColor());
            }
        }

        return tmp;
    }

    /**
     *  There is some information about the cell positions:
     *  nw n ne
     *  w  # e
     *  sw s se
     */
    public static Cell[] getNeighbours(Cell[][] tmpCells, int x, int y) {
        Cell nwCell = (x - 1 >= 0 && y - 1 >= 0) ? tmpCells[x - 1][y - 1] : new Cell(false);
        Cell nCell = (y - 1 >= 0) ? tmpCells[x][y - 1] : new Cell(false);
        Cell neCell = (x + 1 < tmpCells.length && y - 1 >= 0) ? tmpCells[x + 1][y - 1] : new Cell(false);

        Cell wCell = (x - 1 >= 0) ? tmpCells[x - 1][y] : new Cell(false);
        Cell eCell = (x + 1 < tmpCells.length) ? tmpCells[x + 1][y] : new Cell(false);

        Cell swCell =  (x - 1 >= 0 && y + 1 < tmpCells.length) ? tmpCells[x - 1][y + 1] : new Cell(false);
        Cell sCell = (y + 1 < tmpCells.length) ? tmpCells[x][y + 1] : new Cell(false);
        Cell seCell = (x + 1 < tmpCells.length && y + 1 < tmpCells.length) ? tmpCells[x + 1][y + 1] : new Cell(false);

        return new Cell[]{nwCell, nCell, neCell, wCell, eCell, swCell, sCell, seCell};
    }

    public static Color getRandomColor() {
        return Color.rgb(getRandomValueInRGBRange(), getRandomValueInRGBRange(), getRandomValueInRGBRange());
    }

    private static int getRandomValueInRGBRange() {
        return ThreadLocalRandom.current().nextInt(0, 255 + 1);
    }
}
