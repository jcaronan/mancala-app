package com.mancala.api.service.impl;

import com.mancala.api.dto.MancalaDto;
import com.mancala.api.game.MancalaBoard;
import com.mancala.api.game.Player;
import com.mancala.api.game.PlayerZone;
import com.mancala.api.mapper.GameDataMapper;
import com.mancala.api.mapper.MancalaBoardMapper;
import com.mancala.api.model.GameData;
import com.mancala.api.repository.GameRepository;
import com.mancala.api.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    @Transactional
    public MancalaDto initGame() {
        GameData gameData = gameRepository.save(MancalaBoardMapper.mapToGameData(new MancalaBoard()));
        return GameDataMapper.mapToMancalaDto(gameData);
    }

    @Override
    @Transactional
    public MancalaDto moveStone(Long id, Player player, int pit) {
        GameData gameData = gameRepository.findById(id)
                                            .orElseThrow( () -> new IllegalArgumentException(id + " game id is invalid."));
        MancalaDto mancalaDto = GameDataMapper.mapToMancalaDto(gameData);

        PlayerZone p1Zone = new PlayerZone(mancalaDto.getP1Pits(), Player.P1);
        PlayerZone p2Zone = new PlayerZone(mancalaDto.getP2Pits(), Player.P2);

        MancalaBoard mancalaBoard = new MancalaBoard(p1Zone, p2Zone);
        mancalaBoard.move(player, pit);
        GameData updatedGame = updateGame(MancalaBoardMapper.mapToGameData(mancalaBoard, id, player, pit));
        return MancalaBoardMapper.mapToMancalaDto(updatedGame, mancalaBoard);

    }

    @Override
    @Transactional
    public MancalaDto resetGame(Long id) {
        GameData gameData = MancalaBoardMapper.mapToGameData(new MancalaBoard());
        gameData.setId(id);
        gameRepository.save(gameData);
        return GameDataMapper.mapToMancalaDto(gameData);

    }
    private GameData updateGame(GameData gameData) {
        return gameRepository.save(gameData);
    }

}
