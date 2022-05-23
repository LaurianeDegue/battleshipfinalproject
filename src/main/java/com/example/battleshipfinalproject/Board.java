package com.example.battleshipfinalproject;

/*
    In this class, I will be creating the board for our battleship game, I will define the individual cells inside of the of the board.
    As well as, creating the board itself, with multiple VBoxes and HBoxes. I will also be defining the placing of the ships
 */

import javafx.scene.effect.Light;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.geometry.Point2D;

import java.util.ArrayList;


public class Board {
    private VBox board = new VBox();
    public int ships = 5;
    private boolean enemy = false;

    //In this constructor, I am first defining whether the board is the enemy board or the player board.
    //I am also making it so, we must define the events that will take place, when clicking one of the cells
    // We are also creating a loop that creates a new HBox, and adds it to our main VBox, this is how we are separating every cell on our board.
    public Board(boolean en, EventHandler<? super MouseEvent> eventHandler){
        this.enemy = en;

        for (int y = 0; y < 10; y++){
            HBox row = new HBox();

            for(int x = 0; x < 10; x++){
                Cell rowCell = new Cell(this, x, y);
                rowCell.setOnMouseClicked(eventHandler);

                row.getChildren().add(rowCell);
            }

            board.getChildren().add(row);
        }

        getChildren().add(board);
    }

    //In this method, we are getting the children of our VBox, essentially all of our rows, and then we are casting it to an HBox.
    //We cast it to an HBox, in order to be able to our x inside of our rows, and after getting both our x an our y.
    //We cast everything to our class Cell.
    public Cell getCell(int x, int y){

        return (Cell)((HBox)board.getChildren().get(y)).getChildren().get(x);
    }

    //In this method we are selecting our cell and then, calling our other isValidCell method, and getting both the x and the y, to be able to check whether its valid or not.
    public boolean isValidCell(Point2D cell){

        return isValidCell((int)cell.getX(), (int)cell.getY());
    }

    //In this method, we will be making sure that both the x and the y of the cell that we are trying to select, are between 0 and 10.
    //0 and 10 being the length of our board, both horizontally and vertically. We are checking to see if the cell is indeed within the board's parameters.
    public boolean isValidCell(int x, int y){

        return x >= 0 && x <= 10 && y >= 0 && y <= 10;
    }


    public Cell[] getNeighbours(int x, int y){
        Point2D[] cells = new Point2D[]{
            new Point2D(x, y -1),
            new Point2D(x, y + 1),
            new Point2D(x -1, y),
            new Point2D(x +1, y)

        };

        ArrayList<Cell> neighbours = new ArrayList<>();

        for(Point2D point : cells){

            if(isValidCell(point)){
                neighbours.add(getCell((int)point.getX(), (int)point.getY()));
            }
        }

        return neighbours.toArray(new Cell[0]);
    }



    private boolean canPlaceShip(Ship ship, int x, int y){
        int shipLength = ship.length;

        if(ship.orientationVertical) {

            for (int b = y; b < y + shipLength; b++) {

                if (!isValidCell(x, b)) {

                    return false;
                }

                Cell cell = getCell(x, b);

                if (cell.ship != null) {

                    return false;
                }

                for (Cell neighbour : getNeighbours(x, b)) {

                    if (!isValidCell(x, b)) {

                        return false;
                    }

                    if (neighbour.ship != null) {

                        return false;
                    }
                }
            }
        }

        else {

            for(int b = x; b < x + shipLength; b++){

                if(!isValidCell(b, y)){

                    return false;
                }

                Cell cell = getCell(b, y);

                if(cell.ship != null){

                    return false;
                }

                for (Cell neighbour : getNeighbours(b, y)) {

                    if(!isValidCell(b, y)){

                        return false;
                    }

                    if(neighbour.ship != null){

                        return false;
                    }

                }
            }
        }

        return true;
    }

    public boolean placeShip(Ship ship, int x, int y){

        if(canPlaceShip(ship, x, y)){

            int shipLength = ship.length;

            if (ship.orientationVertical){

                for(int a = y; a < y + shipLength; a++){

                    Cell cell = getCell(x, a);
                    cell.ship = ship;

                    if(!enemy){
                        cell.setFill(Color.LIGHTBLUE);
                        cell.setStroke(Color.DARKBLUE);
                    }
                }
            }

            else {
                for(int i = x; i < x + shipLength; i++){

                    Cell cell = getCell(i, y);
                    cell.ship = ship;

                    if(!enemy){
                        cell.setFill(Color.LIGHTBLUE);
                        cell.setStroke(Color.DARKBLUE);
                    }
                }
            }

            return true;
        }

        return false;
    }


    //Here I am creating a class for the cell, to specify how cells behave and essentially, whether or not that cell is part of a ship.
    //I will be defining the color of each cell and also defining what happens when you do sucessfully find a ship cell.
    public class Cell extends Rectangle{
        public int x;
        public int y;
        private Ship ship = null;
        public Board board;
        public boolean wasShot = false;


        //In this construction, I'm setting the size of the cell, using our super class Rectangle. I am also setting the color and outline of each cell.
        public Cell(Board board, int x, int y) {
            super(30,30);
            this.board = board;
            this.x = x;
            this.y = y;
            setFill(Color.GRAY);
            setStroke(Color.BLACK);

        }

        //In this method, we are defining what happens when we take a shot or when we try and guess the enemy ship.
        //We are changing the color of the cell to black, to show that it was already chosen and we are checking to see if there is a ship part in that cell.
        //If there is a ship part, and we successfully hit a ship, we call the shipHit method in ship, to decrease the health and then we check to see if the ship is still alive or not.
        //If the ship is no longer alive, we decrease the amount of ships on the board by 1.
        //We are also changing the color of the cell to red, if we successfully hit a ship.
        public boolean successfulShot(){
            wasShot = true;
            setFill(Color.BLACK);

            if (ship != null){
                ship.shipHit();
                setFill(Color.RED);

                if(!(ship.isAlive())){
                    board.ships--;
                }

                return true;
            }

            return false;
        }
    }


}
