package com.bol.mancala.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MancalaBoard {
    private PlayerZone p1Zone;
    private PlayerZone p2Zone;
    private boolean endOfTurn;
    private boolean gameOver;

    public static final int PIT_SIZE = 6;
    public static final int BIG_PIT = 6;
    public static final int DEFAULT_NUMBER_OF_STONES = 6;
    public static final int EMPTY_PIT = 0;
    public static final int CAPTURE_PIT = 1;
    public static final String DRAW = "DRAW";

    public MancalaBoard() {
        this.p1Zone = new PlayerZone(initializePits(), Player.P1);
        this.p2Zone = new PlayerZone(initializePits(), Player.P2);
        this.endOfTurn = true;
        this.gameOver = false;
    }

    public MancalaBoard(PlayerZone p1Zone, PlayerZone p2Zone) {
        this.p1Zone = p1Zone;
        this.p2Zone = p2Zone;
        this.endOfTurn = true;
        this.gameOver = false;
    }

    public PlayerZone getP1Zone() {
        return p1Zone;
    }

    public void setP1Zone(final PlayerZone p1Zone) {
        this.p1Zone = p1Zone;
    }

    public PlayerZone getP2Zone() {
        return p2Zone;
    }

    public void setP2Zone(final PlayerZone p2Zone) {
        this.p2Zone = p2Zone;
    }

    public boolean isEndOfTurn() {
        return endOfTurn;
    }

    public void setEndOfTurn(final boolean endOfTurn) {
        this.endOfTurn = endOfTurn;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(final boolean gameOver) {
        this.gameOver = gameOver;
    }

    public List<Integer> initializePits() {
        List<Integer> playerPits = new ArrayList<>();

        for(int i = 0; i < PIT_SIZE; i++) {
            playerPits.add(DEFAULT_NUMBER_OF_STONES);
        }
        //lastly set big pit
        playerPits.add(EMPTY_PIT);

        return playerPits;
    }

    public void move(Player player, int start) {
        PlayerZone activeZone = getPlayerZone(player);
        int stones = activeZone.getPits().get(start);
        activeZone.getPits().set(start, EMPTY_PIT);
        int endPit = startDropping(player, activeZone, stones, start);

        //capture stones
        if(player == activeZone.getPlayerSide()
                && CAPTURE_PIT == activeZone.getPits().get(endPit)
                    && endPit != BIG_PIT) {
            PlayerZone opponentZone = getOpponentSide(player);
            int oppositePitIndex = PIT_SIZE - endPit - 1;
            int oppositePitStones = opponentZone.getPits().get(oppositePitIndex);
            if(oppositePitStones > EMPTY_PIT) {
                int capturedStones = CAPTURE_PIT + oppositePitStones;
                int bigPitStones = activeZone.getPits().get(BIG_PIT);
                activeZone.getPits().set(BIG_PIT, bigPitStones + capturedStones);
                activeZone.getPits().set(endPit, EMPTY_PIT);
                opponentZone.getPits().set(oppositePitIndex, EMPTY_PIT);
            }
            this.endOfTurn = true;
        } else if (endPit == BIG_PIT) {
            this.endOfTurn = false;
        }
        checkIfGameOver(activeZone,player);

    }

    public int startDropping(Player player, PlayerZone activeZone, int stones, int start) {
        int currentPit = start;
        while(stones != EMPTY_PIT) {
            if(BIG_PIT == currentPit) {
                currentPit = 0;
                activeZone = getOpponentSide(activeZone.getPlayerSide());
            } else {
                currentPit++;
            }
            if(BIG_PIT == currentPit && player != activeZone.getPlayerSide()) {
                continue;
            }
            dropStone(activeZone.getPits(), currentPit);
            stones--;
        }
        return currentPit;
    }

    public PlayerZone getOpponentSide(Player playerSide) {
        if(Player.P1 == playerSide) {
            return this.getP2Zone();
        } else {
            return this.getP1Zone();
        }
    }

    public PlayerZone getPlayerZone(Player player) {
        if(Player.P1 == player) {
            return this.getP1Zone();
        } else {
            return this.getP2Zone();
        }
    }

    public void dropStone(List<Integer> pits, int index) {
        Integer value = pits.get(index);
        value = value + 1;
        pits.set(index, value);
    }

    public void checkIfGameOver(PlayerZone activeZone, Player player) {
        if(player == activeZone.getPlayerSide()
                && (Collections.frequency(activeZone.getPits(), EMPTY_PIT) == activeZone.getPits().size() - 1)
                    && activeZone.getPits().get(BIG_PIT) != EMPTY_PIT) {
            collectStonesLeftOnBoard(player);
            this.endOfTurn = true;
            this.gameOver = true;
        }
    }

    public void collectStonesLeftOnBoard(Player player) {
        PlayerZone opponentSide = getOpponentSide(player);
        int sum = opponentSide.getPits().stream().mapToInt(Integer::intValue).sum();
        for(int i = 0; i < PIT_SIZE; i++) {
            opponentSide.getPits().set(i, EMPTY_PIT);
        }
        opponentSide.getPits().set(BIG_PIT, sum);
    }

    public String getWinner() {
        if(this.getP1Zone().getPits().get(PIT_SIZE) == this.getP2Zone().getPits().get(PIT_SIZE)) {
            return DRAW;
        }
        if(this.getP1Zone().getPits().get(PIT_SIZE) > this.getP2Zone().getPits().get(PIT_SIZE)) {
            return Player.P1.toString();
        } else {
            return Player.P2.toString();
        }
    }

}
