package com.example.battleshipfinalproject;

/*
    In this class, I will be actually creating the game, as well as the other aspects of my stage, like my user menu,
    with instructions, a scoreboard and two buttons for quit or reset.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import java.io.IOException;
import java.util.Random;

public class HelloApplication extends Application implements ButtonInterface {
    private boolean running = false;
    private boolean AITurn = false;
    private int totalShipsPerBoard = 5;
    private Random random = new Random();
    private Board playerBoard, AIBoard;
    private Alert alert;
    private Group gameBoard;
    private Button resetBtn;
    private Button quitButton;
    private int AIScore = 0;
    private int playerScore = 0;
    private Text enemyScore;
    private Text yourScore;

    /*
        In this method, I am creating the game itself, as well as calling the AIMove method, so that they can generate an action.
        I am also placing the user boats and checking the status of the amount of boats on the board to track the winner.
     */
    private Parent createGame(){
        BorderPane gameStage = new BorderPane();
        gameStage.setPrefSize(800, 700);
        gameStage.setMinSize(800, 700);
        gameStage.setMaxSize(800,700);


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

                playerScore++;

                yourScore.setText(Integer.toString(playerScore));
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

    /*
    In this method, we're randomly generating an x and a y, as coordinates for a cell for the enemy to select, and we are checking
    to see if we hit a boat and if the player board still has ships, to determine whether the player lost or not.
     */
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

                AIScore++;

                enemyScore.setText(Integer.toString(AIScore));
            }

        }
    }


    /*
    In this method, we are generating the enemy ships as well as changing the status of the game to running.
     */
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

    /*
    In this method we are creating a restart button, that will completely clear the board, as well as our previous data
    and let us restart with a clean slate.
     */
    @Override
    public Button refreshButton() {

        resetBtn = new Button("Restart");
        resetBtn.getStyleClass().addAll("btn", "btn-warning");
        resetBtn.setOnAction(e -> {
            gameBoard.getChildren().clear();

            totalShipsPerBoard = 5;
            playerBoard.ships = 5;
            AIBoard.ships = 5;
            running = false;
            gameBoard.getChildren().add(createGame());
        });

        return resetBtn;
    }

    /*
    In this method we are making a button that will close the application.
     */
    @Override
    public Button quitButton() {
        quitButton = new Button("Quit");
        quitButton.setOnAction(e -> {
            System.exit(0);
        });
        quitButton.getStyleClass().addAll("btn", "btn-danger");

        return quitButton;
    }



    /*
    In this method we are styling the stage as well as creating labels and user interfaces.
     */
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("JavaFX Battleship");

        gameBoard = new Group(createGame());

        HBox buttons = new HBox(10, quitButton(), refreshButton());

        Label enemyBoardLbl = new Label("Enemy Board: ");

        Label yourBoardLbl = new Label("Your Board: ");
        HBox enemyBoard = new HBox();
        enemyBoard.getChildren().add(enemyBoardLbl);
        enemyBoard.setAlignment(Pos.CENTER);
        HBox yourBoard = new HBox();
        yourBoard.getChildren().add(yourBoardLbl);
        yourBoard.setAlignment(Pos.CENTER);

        GridPane board = new GridPane();
        board.setAlignment(Pos.CENTER);
        board.addColumn(1, enemyBoard, gameBoard, yourBoard);

        Text instructions = new Text("Basic instructions:\n "
                                    + "Right-click for horizontal ship.\n" +
                                      "Left-click for vertical ship.");

        instructions.setStyle("-fx-font-size: 15px");


        GridPane scoreBoard = new GridPane();

        scoreBoard.setVgap(15);
        scoreBoard.setHgap(15);


        scoreBoard.setAlignment(Pos.CENTER);

        scoreBoard.setStyle("-fx-font-size: 30px");


        Text enemy = new Text("AI");
        Text player = new Text("You");
        enemyScore = new Text(Integer.toString(AIScore));
        yourScore = new Text(Integer.toString(playerScore));
        yourScore.setTextAlignment(TextAlignment.CENTER);

        scoreBoard.add(enemy, 0, 0);
        scoreBoard.add(player, 1, 0);
        scoreBoard.add(enemyScore, 0, 1);
        scoreBoard.add(yourScore, 1,1);


        VBox userMenu = new VBox(25,instructions, scoreBoard, buttons);
        userMenu.setAlignment(Pos.CENTER);
        userMenu.setPadding(new Insets(50));


        HBox finalBoard = new HBox();
        finalBoard.getChildren().addAll(board, userMenu);


        Scene scene = new Scene(finalBoard);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
