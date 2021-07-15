package com.bol.mancala.dto;

import com.bol.mancala.helper.Player;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PlayerCommand {

    @NotNull(message = "Game ID cannot be null!")
    private Long id;

    @NotNull(message = "Player ID cannot be null!")
    private Player player;

    @Min(value = 0, message = "Pit index should be 0 or higher!")
    private int pitIndex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getPitIndex() {
        return pitIndex;
    }

    public void setPitIndex(int pitIndex) {
        this.pitIndex = pitIndex;
    }
}
