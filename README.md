# Game of Life - Java/JavaFX

## Information

![visualization](https://github.com/mariuszsienkiewicz/game-of-life-javafx/raw/master/assets/visualization.gif "Visualization")

Basic and easy implementation of the Game of Life cellular automaton. It uses the JavaFX library for the visual presentation and it uses some of the MVC rules.

## About the Game of Life

> The Game of Life is a cellular automaton devised by the British mathematician John Horton Conway in 1970. It is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. One interacts with the Game of Life by creating an initial configuration and observing how it evolves.

### Rules

1. Any live cell with fewer than two live neighbours dies, as if by underpopulation.
1. Any live cell with two or three live neighbours lives on to the next generation.
1. Any live cell with more than three live neighbours dies, as if by overpopulation.
1. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

## Requirements

This project was created using the JavaFX 11.0.2 and Java 15.