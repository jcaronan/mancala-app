package com.bol.mancala.service.impl;

import com.bol.mancala.GameResultCriteria;
import com.bol.mancala.helper.Player;
import com.bol.mancala.model.GameData;
import com.bol.mancala.repository.GameRepository;
import com.bol.mancala.service.GameService;
import com.bol.mancala.util.PitsUtil;
import com.bol.mancala.dto.MancalaDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GameServiceImplTest {

    @Autowired
    GameService gameServiceToTest;

    @Autowired
    GameRepository gameRepository;

    @Test
    void initGame() {
        final GameResultCriteria gameResultCriteria = new GameResultCriteria(null, null, false, false);
        final MancalaDto resultMancalaDto = gameServiceToTest.initGame();
        final GameData expectedGameData = gameRepository.findById(resultMancalaDto.getId()).orElseThrow();
        assertResult(resultMancalaDto, expectedGameData, gameResultCriteria);
    }

    @Test
    void moveGame_P1TurnNotOver() {
        final GameResultCriteria gameResultCriteria = new GameResultCriteria(Player.P1, null, false, false);
        final MancalaDto createdGame = gameServiceToTest.initGame();
        final MancalaDto resultMancalaDto = gameServiceToTest.moveStone(createdGame.getId(), Player.P1, 0);
        final GameData expectedGameData = gameRepository.findById(resultMancalaDto.getId()).orElseThrow();
        assertResult(resultMancalaDto, expectedGameData, gameResultCriteria);
    }

    @Test
    void moveGame_P1TurnOver() {
        final GameResultCriteria gameResultCriteria = new GameResultCriteria(Player.P1, null, false, true);
        final MancalaDto createdGame = gameServiceToTest.initGame();
        final MancalaDto resultMancalaDto = gameServiceToTest.moveStone(createdGame.getId(), Player.P1, 5);
        final GameData expectedGameData = gameRepository.findById(resultMancalaDto.getId()).orElseThrow();
        assertResult(resultMancalaDto, expectedGameData, gameResultCriteria);
    }

    @Test
    void moveGame_P2TurnNotOver() {
        final GameResultCriteria gameResultCriteria = new GameResultCriteria(Player.P2, null, false, false);
        final MancalaDto createdGame = gameServiceToTest.initGame();
        final MancalaDto resultMancalaDto = gameServiceToTest.moveStone(createdGame.getId(), Player.P2, 0);
        final GameData expectedGameData = gameRepository.findById(resultMancalaDto.getId()).orElseThrow();
        assertResult(resultMancalaDto, expectedGameData, gameResultCriteria);
    }

    @Test
    void moveGame_P2TurnOver() {
        final GameResultCriteria gameResultCriteria = new GameResultCriteria(Player.P2, null, false, true);
        final MancalaDto createdGame = gameServiceToTest.initGame();
        final MancalaDto resultMancalaDto = gameServiceToTest.moveStone(createdGame.getId(), Player.P2, 5);
        final GameData expectedGameData = gameRepository.findById(resultMancalaDto.getId()).orElseThrow();
        assertResult(resultMancalaDto, expectedGameData, gameResultCriteria);
    }

    @Test
    void moveGame_CaptureStone() {
        final GameResultCriteria gameResultCriteria = new GameResultCriteria(Player.P2, null, false, true);
        final MancalaDto createdGame = gameServiceToTest.initGame();
        //setup board
        final GameData newGame = new GameData();
        newGame.setId(createdGame.getId());
        newGame.setP1Pits("[4,6,6,6,6,8,10]");
        newGame.setP2Pits("[6,6,6,6,1,0,0]");
        gameRepository.save(newGame);
        final MancalaDto resultMancalaDto = gameServiceToTest.moveStone(createdGame.getId(), Player.P2, 4);
        final GameData expectedGameData = gameRepository.findById(resultMancalaDto.getId()).orElseThrow();
        assertResult(resultMancalaDto, expectedGameData, gameResultCriteria);
        assertEquals(5, resultMancalaDto.getP2Pits().get(6));
    }

    @Test
    void moveGame_GameOverWinnerP2() {
        final GameResultCriteria gameResultCriteria = new GameResultCriteria(Player.P1, "P2", true, true);
        final MancalaDto createdGame = gameServiceToTest.initGame();
        //setup board
        final GameData newGame = new GameData();
        newGame.setId(createdGame.getId());
        newGame.setP1Pits("[0,0,0,0,0,1,10]");
        newGame.setP2Pits("[5,5,5,5,1,0,4]");
        gameRepository.save(newGame);
        final MancalaDto resultMancalaDto = gameServiceToTest.moveStone(createdGame.getId(), Player.P1, 5);
        final GameData expectedGameData = gameRepository.findById(resultMancalaDto.getId()).orElseThrow();
        assertResult(resultMancalaDto, expectedGameData, gameResultCriteria);
        assertEquals(11, resultMancalaDto.getP1Pits().get(6));
        assertEquals(25, resultMancalaDto.getP2Pits().get(6));
    }

    @Test
    void moveGame_GameOverDraw() {
        final GameResultCriteria gameResultCriteria = new GameResultCriteria(Player.P1, "DRAW", true, true);
        final MancalaDto createdGame = gameServiceToTest.initGame();
        //setup board
        final GameData newGame = new GameData();
        newGame.setId(createdGame.getId());
        newGame.setP1Pits("[0,0,0,0,0,1,24]");
        newGame.setP2Pits("[5,5,5,5,1,0,4]");
        gameRepository.save(newGame);
        final MancalaDto resultMancalaDto = gameServiceToTest.moveStone(createdGame.getId(), Player.P1, 5);
        final GameData expectedGameData = gameRepository.findById(resultMancalaDto.getId()).orElseThrow();
        assertResult(resultMancalaDto, expectedGameData, gameResultCriteria);
        assertEquals(25, resultMancalaDto.getP1Pits().get(6));
        assertEquals(25, resultMancalaDto.getP2Pits().get(6));
    }

    @Test
    void pickNoStone() {
        final GameResultCriteria gameResultCriteria = new GameResultCriteria(Player.P1, null, false, true);
        final MancalaDto createdGame = gameServiceToTest.initGame();
        //setup board
        final GameData newGame = new GameData();
        newGame.setId(createdGame.getId());
        newGame.setP1Pits("[0,0,0,0,0,0,0]");
        newGame.setP2Pits("[0,0,0,0,0,0,0]");
        gameRepository.save(newGame);
        final MancalaDto resultMancalaDto = gameServiceToTest.moveStone(createdGame.getId(), Player.P1, 1);
        final GameData expectedGameData = gameRepository.findById(resultMancalaDto.getId()).orElseThrow();
        assertResult(resultMancalaDto, expectedGameData, gameResultCriteria);
    }

    @Test
    void resetGame() {
        final GameResultCriteria gameResultCriteria = new GameResultCriteria(null, null, false, false);
        final MancalaDto createdGame = gameServiceToTest.initGame();
        final MancalaDto resultMancalaDto = gameServiceToTest.resetGame(createdGame.getId());
        final GameData expectedGameData = gameRepository.findById(resultMancalaDto.getId()).orElseThrow();
        assertResult(resultMancalaDto, expectedGameData, gameResultCriteria);
    }

    private void assertResult(MancalaDto resultMancalaDto, GameData expectedGameData, GameResultCriteria gameResultCriteria) {
        assertEquals(expectedGameData.getId(), resultMancalaDto.getId());
        Assertions.assertEquals(expectedGameData.getP1Pits(), PitsUtil.toString(resultMancalaDto.getP1Pits()));
        assertEquals(expectedGameData.getP2Pits(), PitsUtil.toString(resultMancalaDto.getP2Pits()));
        assertEquals(gameResultCriteria.getPlayer(), resultMancalaDto.getActivePlayer());
        assertEquals(gameResultCriteria.getWinner(), resultMancalaDto.getWinner());
        assertEquals(gameResultCriteria.isGameOver(), resultMancalaDto.isGameOver());
        assertEquals(gameResultCriteria.isTurnOver(), resultMancalaDto.isEndOfTurn());

    }

}
