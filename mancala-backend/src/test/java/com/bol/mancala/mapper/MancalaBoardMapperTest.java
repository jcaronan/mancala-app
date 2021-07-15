package com.bol.mancala.mapper;

import com.bol.mancala.dto.MancalaDto;
import com.bol.mancala.helper.MancalaBoard;
import com.bol.mancala.helper.Player;
import com.bol.mancala.model.GameData;
import com.bol.mancala.util.PitsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class MancalaBoardMapperTest {
    MancalaBoard mancalaBoard;
    String pits;

    @BeforeEach
    public void init() {
        mancalaBoard = new MancalaBoard();
        pits = "[6,6,6,6,6,6,0]";
    }

    @Test
    public void testMapToGameDataInit() {
        GameData result = MancalaBoardMapper.mapToGameData(mancalaBoard);
        assertEquals(pits,result.getP1Pits());
        assertEquals(pits,result.getP2Pits());
        assertEquals(null,result.getWinner());
        assertEquals(null,result.getId());
        assertEquals(null,result.getCurrentPlayer());
    }

    @Test
    public void testMapToGameData() {
        Long id = 1L;
        Player player = Player.P1;
        int pit = 1;
        GameData result = MancalaBoardMapper.mapToGameData(mancalaBoard, id, player, 1);
        assertEquals(pits,result.getP1Pits());
        assertEquals(pits,result.getP2Pits());
        assertEquals(null,result.getWinner());
        assertEquals(player,result.getCurrentPlayer());
        assertEquals(id,result.getId());
    }

    @Test
    public void mapToMancalaDto() {
        GameData gameData = new GameData();
        gameData.setId(1L);
        gameData.setPitIndex(1);
        gameData.setCurrentPlayer(Player.P1);
        gameData.setP1Pits(pits);
        gameData.setP2Pits(pits);
        gameData.setWinner(Player.P2.name());
        MancalaDto result = MancalaBoardMapper.mapToMancalaDto(gameData, mancalaBoard);
        assertEquals(Player.P2.name(),result.getWinner());
        assertEquals(gameData.getId(), result.getId());
        assertEquals(gameData.getCurrentPlayer(), result.getActivePlayer());
        assertEquals(gameData.getWinner(), result.getWinner());
        assertEquals(PitsUtil.toList(gameData.getP1Pits()), result.getP1Pits());
        assertEquals(PitsUtil.toList(gameData.getP2Pits()), result.getP2Pits());


    }
}
