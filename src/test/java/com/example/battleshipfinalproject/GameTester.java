package com.example.battleshipfinalproject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTester {

    @Test
    public void ShipTester(){

        Ship ship = new Ship(0, true);
        assertEquals(false, ship.isAlive());
    }

    @Test
    public void BoardTester(){

        Board board = new Board(false, mouseEvent -> {

        });
        assertEquals(true, board.isValidCell(8, 7));
    }

    @Test
    public void MainMethodTester(){

    }


}
