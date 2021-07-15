package com.bol.mancala.mapper;

import com.bol.mancala.util.PitsUtil;
import com.bol.mancala.dto.MancalaDto;
import com.bol.mancala.model.GameData;

public class GameDataMapper {

    public static MancalaDto mapToMancalaDto(GameData gameData) {
        MancalaDto mancalaDto = new MancalaDto();
        mancalaDto.setId(gameData.getId());
        mancalaDto.setP1Pits(PitsUtil.toList(gameData.getP1Pits()));
        mancalaDto.setP2Pits(PitsUtil.toList(gameData.getP2Pits()));
        mancalaDto.setActivePlayer(gameData.getCurrentPlayer());
        mancalaDto.setWinner(gameData.getWinner());

        return mancalaDto;
    }
}
