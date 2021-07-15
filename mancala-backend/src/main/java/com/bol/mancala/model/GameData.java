package com.bol.mancala.model;

import com.bol.mancala.helper.Player;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
public class GameData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "current_player")
    private Player currentPlayer;

    @Column(name = "pit_index")
    private int pitIndex;

    @NonNull
    @Column(name = "p1_pits")
    private String p1Pits;

    @NonNull
    @Column(name = "p2_pits")
    private String p2Pits;

    @Column(name = "winner")
    private String winner;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(final Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getPitIndex() {
        return pitIndex;
    }

    public void setPitIndex(final int pitIndex) {
        this.pitIndex = pitIndex;
    }

    public String getP1Pits() {
        return p1Pits;
    }

    public void setP1Pits(String p1Pits) {
        this.p1Pits = p1Pits;
    }

    public String getP2Pits() {
        return p2Pits;
    }

    public void setP2Pits(String p2Pits) {
        this.p2Pits = p2Pits;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(final String winner) {
        this.winner = winner;
    }
}
