package com.mancala.api.dto;

import com.mancala.api.game.Player;
import com.mancala.api.game.PlayerZone;

import java.util.List;

public class MancalaDto {

    private Long id;
    private List<Integer> p1Pits;
    private List<Integer> p2Pits;
    private Player activePlayer;
    private boolean endOfTurn;
    private boolean gameOver;
    private String winner;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public List<Integer> getP1Pits() {
        return p1Pits;
    }

    public void setP1Pits(final List<Integer> p1Pits) {
        this.p1Pits = p1Pits;
    }

    public List<Integer> getP2Pits() {
        return p2Pits;
    }

    public void setP2Pits(final List<Integer> p2Pits) {
        this.p2Pits = p2Pits;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(final Player activePlayer) {
        this.activePlayer = activePlayer;
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

    public String getWinner() {
        return winner;
    }

    public void setWinner(final String winner) {
        this.winner = winner;
    }
}
