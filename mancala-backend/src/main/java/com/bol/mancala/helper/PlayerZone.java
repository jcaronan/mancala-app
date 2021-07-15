package com.bol.mancala.helper;

import java.util.List;

public class PlayerZone {

    private List<Integer> pits;
    private Player playerSide;

    public PlayerZone(List<Integer> pits, Player playerSide) {
        this.pits = pits;
        this.playerSide = playerSide;
    }

    public List<Integer> getPits() {
        return pits;
    }

    public void setPits(final List<Integer> pits) {
        this.pits = pits;
    }

    public Player getPlayerSide() {
        return playerSide;
    }

    public void setPlayerSide(final Player playerSide) {
        this.playerSide = playerSide;
    }
}
