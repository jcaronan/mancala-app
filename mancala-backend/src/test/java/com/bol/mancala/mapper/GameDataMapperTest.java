package com.bol.mancala.mapper;


import com.bol.mancala.dto.MancalaDto;
import com.bol.mancala.helper.Player;
import com.bol.mancala.model.GameData;
import com.bol.mancala.util.PitsUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GameDataMapperTest {

    @Test
    public void testMapToMancalaDto() {
        ArrayList<Integer> pits1 = new ArrayList<>();
        pits1.add(1);
        pits1.add(2);
        pits1.add(3);
        pits1.add(4);
        pits1.add(5);

        ArrayList<Integer> pits2 = new ArrayList<>();
        pits2.add(0);
        pits2.add(0);
        pits2.add(0);
        pits2.add(0);
        pits2.add(0);

        String pits1Str = "[1,2,3,4,5]";
        String pits2Str = "[0,0,0,0,0]";

        GameData gameData = new GameData();
        gameData.setId(1L);
        gameData.setPitIndex(1);
        gameData.setCurrentPlayer(Player.P1);
        gameData.setP1Pits(pits1Str);
        gameData.setP2Pits(pits2Str);
        gameData.setWinner(Player.P2.name());

        MancalaDto result = GameDataMapper.mapToMancalaDto(gameData);

        assertEquals(gameData.getId(), result.getId());
        assertEquals(gameData.getCurrentPlayer(), result.getActivePlayer());
        assertEquals(pits1, result.getP1Pits());
        assertEquals(pits2, result.getP2Pits());
        assertEquals(gameData.getWinner(), result.getWinner());

    }
}
