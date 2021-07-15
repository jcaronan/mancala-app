package com.bol.mancala;

import com.bol.mancala.helper.Player;

public class GameResultCriteria {

    private Player player;
    private String winner;
    private boolean isGameOver;
    private boolean isTurnOver;

    public GameResultCriteria(Player player, String winner, boolean isGameOver, boolean isTurnOver) {
        this.player = player;
        this.winner = winner;
        this.isGameOver = isGameOver;
        this.isTurnOver = isTurnOver;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(final Player player) {
        this.player = player;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(final String winner) {
        this.winner = winner;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(final boolean gameOver) {
        isGameOver = gameOver;
    }

    public boolean isTurnOver() {
        return isTurnOver;
    }

    public void setTurnOver(final boolean turnOver) {
        isTurnOver = turnOver;
    }

}
