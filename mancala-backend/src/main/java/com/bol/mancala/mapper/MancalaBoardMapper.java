package com.bol.mancala.mapper;

import com.bol.mancala.dto.MancalaDto;
import com.bol.mancala.helper.MancalaBoard;
import com.bol.mancala.helper.Player;
import com.bol.mancala.model.GameData;
import com.bol.mancala.util.PitsUtil;

public class MancalaBoardMapper {

    public static GameData mapToGameData(MancalaBoard mancalaBoard) {
        GameData gameData = new GameData();
        gameData.setP1Pits(PitsUtil.toString(mancalaBoard.getP1Zone().getPits()));
        gameData.setP2Pits(PitsUtil.toString(mancalaBoard.getP2Zone().getPits()));
        return gameData;
    }

    public static GameData mapToGameData(MancalaBoard mancalaBoard, Long id, Player player, int pit) {
        GameData gameData = mapToGameData(mancalaBoard);
        gameData.setId(id);
        gameData.setCurrentPlayer(player);
        gameData.setPitIndex(pit);
        if(mancalaBoard.isGameOver()) {
           gameData.setWinner(mancalaBoard.getWinner());
        }
        return gameData;
    }

    public static MancalaDto mapToMancalaDto(GameData gameData, MancalaBoard mancalaBoard) {
        MancalaDto mancalaDto = GameDataMapper.mapToMancalaDto(gameData);
        mancalaDto.setGameOver(mancalaBoard.isGameOver());
        mancalaDto.setEndOfTurn(mancalaBoard.isEndOfTurn());
        return mancalaDto;
    }

}
