/*
    In this class, I will be creating the board for our battleship game, I will define the individual cells inside of the of the board.
    As well as, creating the board itself, with multiple VBoxes and HBoxes. I will also be defining the placing of the ships
 */

import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Board {
    private VBox board = new VBox();
    public int ships = 5;
    private boolean enemy = false;

    //In this constructor, I am first defining whether the board is the enemy board or the player board.
    //I am also making it so, we must define the events that will take place, when clicking one of the cells
    // We are also creating a loop that creates a new HBox, and adds it to our main VBox, this is how we are separating every cell on our board.
    public Board(boolean en, EventHandler<? super MouseEvent> eventHandler){
        this.enemy = en;

        for (int a = 0; a < 10; a++){
            HBox row = new HBox();

            for(int x = 0; x < 10; x++){
                Cell rowCell = new Cell();
                rowCell.setOnMouseClicked(eventHandler);

                row.getChildren().add(rowCell);
            }

            board.getChildren().add(row);
        }

        getChildren().add(board)
    }

    //Here I am creating a class for the cell, to specify how cells behave and essentially, whether or not that cell is part of a ship.
    //I will be defining the color of each cell and also defining what happens when you do sucessfully find a ship cell.
    public class Cell extends Rectangle{
        public int x;
        public int y;
        private Ship ship = null;
        public Board board;
        public boolean wasShot = false;

        public Cell(Board board, int x, int y) {
            super(30,30);
            this.board = board;
            this.x = x;
            this.y = y;
            setFill(Color.GRAY);
            setStroke(Color.BLACK);

        }
    }


}
