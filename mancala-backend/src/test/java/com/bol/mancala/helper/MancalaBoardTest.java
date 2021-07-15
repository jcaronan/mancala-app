package com.bol.mancala.helper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MancalaBoardTest {

    @Test
    public void testHappyPath() {
        // go straight to the big pit
        final int pit = 0;
        MancalaBoard mancalaBoard = new MancalaBoard();
        mancalaBoard.move(Player.P1, pit);
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P1).getPits().get(pit));
        assertEquals(7, mancalaBoard.getPlayerZone(Player.P1).getPits().get(1));
        assertEquals(7, mancalaBoard.getPlayerZone(Player.P1).getPits().get(2));
        assertEquals(7, mancalaBoard.getPlayerZone(Player.P1).getPits().get(3));
        assertEquals(7, mancalaBoard.getPlayerZone(Player.P1).getPits().get(4));
        assertEquals(7, mancalaBoard.getPlayerZone(Player.P1).getPits().get(5));
        assertEquals(1, mancalaBoard.getPlayerZone(Player.P1).getPits().get(MancalaBoard.BIG_PIT));
        assertEquals(false, mancalaBoard.isEndOfTurn());
        assertEquals(false, mancalaBoard.isGameOver());
    }

    @Test
    public void testGoingOverOpponentsPit() {

        final int pit = 5;
        MancalaBoard mancalaBoard = new MancalaBoard();
        Integer[] array = {6,6,6,6,6,10,0};
        mancalaBoard.getP1Zone().setPits(Arrays.asList(array));
        mancalaBoard.move(Player.P1, pit);
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P1).getPits().get(pit));
        assertEquals(1, mancalaBoard.getPlayerZone(Player.P1).getPits().get(MancalaBoard.BIG_PIT));
        //check opponent's pits
        assertEquals(7, mancalaBoard.getPlayerZone(Player.P2).getPits().get(0));
        assertEquals(7, mancalaBoard.getPlayerZone(Player.P2).getPits().get(1));
        assertEquals(7, mancalaBoard.getPlayerZone(Player.P2).getPits().get(2));
        assertEquals(7, mancalaBoard.getPlayerZone(Player.P2).getPits().get(3));
        assertEquals(7, mancalaBoard.getPlayerZone(Player.P2).getPits().get(4));
        assertEquals(7, mancalaBoard.getPlayerZone(Player.P2).getPits().get(5));
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P2).getPits().get(MancalaBoard.BIG_PIT));

        //check player's pits
        assertEquals(7, mancalaBoard.getPlayerZone(Player.P1).getPits().get(0));
        assertEquals(7, mancalaBoard.getPlayerZone(Player.P1).getPits().get(1));
        assertEquals(7, mancalaBoard.getPlayerZone(Player.P1).getPits().get(2));
        assertEquals(6, mancalaBoard.getPlayerZone(Player.P1).getPits().get(3));
        assertEquals(6, mancalaBoard.getPlayerZone(Player.P1).getPits().get(4));


        assertEquals(true, mancalaBoard.isEndOfTurn());
        assertEquals(false, mancalaBoard.isGameOver());
    }

    @Test
    public void testNoMoreStonesToPick() {
        final int pit = 5;
        MancalaBoard mancalaBoard = new MancalaBoard();
        Integer[] array1 = {0,0,0,0,0,0,10};
        Integer[] array2 = {0,0,0,0,0,0,20};
        mancalaBoard.getP1Zone().setPits(Arrays.asList(array1));
        mancalaBoard.getP2Zone().setPits(Arrays.asList(array2));
        mancalaBoard.move(Player.P1, pit);
        assertEquals(10, mancalaBoard.getPlayerZone(Player.P1).getPits().get(MancalaBoard.BIG_PIT));
        assertEquals(20, mancalaBoard.getPlayerZone(Player.P2).getPits().get(MancalaBoard.BIG_PIT));
        //check if pits are emptied
        assertIfPitsAreEmpty(mancalaBoard);

        assertEquals(true, mancalaBoard.isEndOfTurn());
        assertEquals(true, mancalaBoard.isGameOver());
        assertEquals("P2", mancalaBoard.getWinner());
    }

    @Test
    public void testCapturePitOnly() {

        final int pit = 4;
        MancalaBoard mancalaBoard = new MancalaBoard();
        Integer[] array1 = {5,0,2,0,0,0,10};
        Integer[] array2 = {0,0,3,0,1,0,20};
        mancalaBoard.getP1Zone().setPits(Arrays.asList(array1));
        mancalaBoard.getP2Zone().setPits(Arrays.asList(array2));
        mancalaBoard.move(Player.P2, pit);
        assertEquals(10, mancalaBoard.getPlayerZone(Player.P1).getPits().get(MancalaBoard.BIG_PIT));
        assertEquals(26, mancalaBoard.getPlayerZone(Player.P2).getPits().get(MancalaBoard.BIG_PIT));

        //check player's pits
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P2).getPits().get(0));
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P2).getPits().get(1));
        assertEquals(3, mancalaBoard.getPlayerZone(Player.P2).getPits().get(2));
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P2).getPits().get(3));
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P2).getPits().get(4));
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P2).getPits().get(5));

        //check opponent's pits
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P1).getPits().get(0));
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P1).getPits().get(1));
        assertEquals(2, mancalaBoard.getPlayerZone(Player.P1).getPits().get(2));
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P1).getPits().get(3));
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P1).getPits().get(4));
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P1).getPits().get(5));

        assertEquals(true, mancalaBoard.isEndOfTurn());
        assertEquals(false, mancalaBoard.isGameOver());
    }

    @Test
    public void testCapturePitAndGameOver() {

        final int pit = 4;
        MancalaBoard mancalaBoard = new MancalaBoard();
        Integer[] array1 = {5,0,0,0,0,0,10};
        Integer[] array2 = {0,0,0,0,1,0,20};
        mancalaBoard.getP1Zone().setPits(Arrays.asList(array1));
        mancalaBoard.getP2Zone().setPits(Arrays.asList(array2));
        mancalaBoard.move(Player.P2, pit);
        assertEquals(10, mancalaBoard.getPlayerZone(Player.P1).getPits().get(MancalaBoard.BIG_PIT));
        assertEquals(26, mancalaBoard.getPlayerZone(Player.P2).getPits().get(MancalaBoard.BIG_PIT));
        //check if pits are emptied
        assertIfPitsAreEmpty(mancalaBoard);
        assertEquals(true, mancalaBoard.isEndOfTurn());
        assertEquals(true, mancalaBoard.isGameOver());
        assertEquals("P2", mancalaBoard.getWinner());
    }

    public void assertIfPitsAreEmpty(MancalaBoard mancalaBoard) {
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P1).getPits().get(0));
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P1).getPits().get(1));
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P1).getPits().get(2));
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P1).getPits().get(3));
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P1).getPits().get(4));
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P1).getPits().get(5));
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P2).getPits().get(0));
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P2).getPits().get(1));
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P2).getPits().get(2));
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P2).getPits().get(3));
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P2).getPits().get(4));
        assertEquals(0, mancalaBoard.getPlayerZone(Player.P2).getPits().get(5));
    }
}
