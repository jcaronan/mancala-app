package com.bol.mancala.service;

import com.bol.mancala.dto.MancalaDto;
import com.bol.mancala.helper.Player;

public interface GameService {

    public MancalaDto initGame();

    public MancalaDto moveStone(Long id, Player player, int pit);

    public MancalaDto resetGame(Long id);

}
