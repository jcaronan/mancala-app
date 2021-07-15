package com.mancala.api.service;

import com.mancala.api.dto.MancalaDto;
import com.mancala.api.game.Player;

public interface GameService {

    public MancalaDto initGame();

    public MancalaDto moveStone(Long id, Player player, int pit);

    public MancalaDto resetGame(Long id);

}
