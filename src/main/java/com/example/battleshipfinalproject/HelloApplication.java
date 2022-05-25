package com.example.battleshipfinalproject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    private Alert alert;
    private VBox boards = new VBox();
    private VBox userMenu = new VBox();


    private Parent createGame(){
        BorderPane gameStage = new BorderPane();
        gameStage.setPrefSize(800, 700);

        AIBoard = new Board(true, event -> {

            if(!(running)){
                return;
            }

            Board.Cell cell = (Board.Cell) event.getSource();
            if(cell.wasShot){
                return;
            }

            AITurn = !cell.successfulShot();

            if(AIBoard.ships == 0){
                alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("JavaFx Battleship Message");
                alert.setHeaderText("You Win!");
                alert.setContentText("You have successfully beaten your opponent.");
                alert.showAndWait();
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

                    alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("JavaFx Battleship Message");
                    alert.setHeaderText("Commence Battle!");
                    alert.setContentText("You may now start your turn.");
                    alert.showAndWait();

                    startGame();
                }
            }
        });

        VBox vBox = new VBox(50, AIBoard, playerBoard);

        vBox.setAlignment(Pos.CENTER);
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
                alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("JavaFx Battleship Message");
                alert.setHeaderText("You Lose!");
                alert.setContentText("It seems you were defeated.");
                alert.showAndWait();
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
        stage.setTitle("JavaFX Battleship");

        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> {
            System.exit(0);
        });

        quitButton.getStyleClass().addAll("btn", "btn-success");
        Group gameBoard = new Group(createGame());
        Group buttons = new Group(quitButton);
        quitButton.setAlignment(Pos.CENTER_LEFT);

        Label enemyBoard = new Label("Enemy Board: ");
        enemyBoard.setAlignment(Pos.CENTER_LEFT);
        enemyBoard.setMaxWidth(200);
        enemyBoard.setMinHeight(350);

        Label yourBoard = new Label("Your Board: ");
        yourBoard.setAlignment(Pos.CENTER_LEFT);
        yourBoard.setMinHeight(350);
        yourBoard.setMaxWidth(200);

        VBox boardTitles = new VBox();
        boardTitles.getChildren().addAll(enemyBoard, yourBoard);

        HBox board = new HBox();

        board.getChildren().addAll(boardTitles, gameBoard, buttons);


        Scene scene = new Scene(board);
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}