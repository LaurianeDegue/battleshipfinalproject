package com.example.battleshipfinalproject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Random;

public class HelloApplication extends Application {
    private boolean running = false;
    private boolean AITurn = false;
    private int totalShipsPerBoard = 5;
    private Random random = new Random();
    private Board playerBoard, AIBoard;


    private Parent createGame(){
        BorderPane gameStage = new BorderPane();
        gameStage.setPrefSize(900, 600);

        AIBoard = new Board(true, event -> {

            if(!running){
                return;
            }

            Board.Cell cell = (Board.Cell) event.getSource();
            if(cell.wasShot){
                return;
            }

            AITurn = !cell.successfulShot();

            if(AIBoard.ships == 0){
                System.out.println("Woohoo");
                System.exit(0);
            }

            if(AITurn){
                AIMove();
            }

        });

        playerBoard = new Board(false, event -> {

            if(running){
                return;
            }

            Board.Cell cell = (Board.Cell) event.getSource();

            if(playerBoard.placeShip(new Ship(totalShipsPerBoard, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)){
                if(--totalShipsPerBoard == 0){
                    startGame();
                }
            }
        });

        VBox vBox = new VBox(50, AIBoard, playerBoard);

        vBox.setAlignment(Pos.CENTER_LEFT);
        gameStage.setCenter(vBox);


        return gameStage;
    }


    private void AIMove(){

        while(AITurn){
            int randomX = random.nextInt(10);
            int randomY = random.nextInt(10);

            Board.Cell cell = playerBoard.getCell(randomX, randomY);

            if(cell.wasShot){
                continue;
            }

            AITurn = cell.successfulShot();

            if(playerBoard.ships == 0){
                System.out.println("Boohoo");
                System.exit(0);
            }

        }
    }


    private void startGame(){
        int enemyShips = 5;

        while (enemyShips > 0) {
            int randomX = random.nextInt(10);
            int randomY = random.nextInt(10);

            if (AIBoard.placeShip(new Ship(enemyShips, Math.random() < 0.5), randomX, randomY)){
                enemyShips--;
            }
        }

        running = true;

    }


    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createGame());
        stage.setTitle("JavaFX Battleship");

        VBox boards = new VBox();
        VBox userMenu = new VBox();
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}