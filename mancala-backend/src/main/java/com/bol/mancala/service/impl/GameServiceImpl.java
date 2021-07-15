package com.bol.mancala.service.impl;

import com.bol.mancala.dto.MancalaDto;
import com.bol.mancala.helper.MancalaBoard;
import com.bol.mancala.helper.Player;
import com.bol.mancala.helper.PlayerZone;
import com.bol.mancala.mapper.GameDataMapper;
import com.bol.mancala.mapper.MancalaBoardMapper;
import com.bol.mancala.model.GameData;
import com.bol.mancala.repository.GameRepository;
import com.bol.mancala.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
