package com.bol.mancala.controller;

import com.bol.mancala.dto.MancalaDto;
import com.bol.mancala.dto.PlayerCommand;
import com.bol.mancala.helper.Player;
import com.bol.mancala.service.GameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class GameControllerTest {

    @MockBean
    private GameService mockGameService;

    @Autowired
    private GameController gameControllerToTest;

    @Test
    void init() {
        gameControllerToTest.createGame();
        verify(mockGameService).initGame();
    }

    @Test
    void moveStone() {
        final PlayerCommand playerCommand = new PlayerCommand();
        playerCommand.setId(anyLong());
        playerCommand.setPlayer(any(Player.class));
        playerCommand.setPitIndex(anyInt());
        gameControllerToTest.moveStone(playerCommand);
        verify(mockGameService).moveStone(playerCommand.getId(), playerCommand.getPlayer(), playerCommand.getPitIndex());
    }

    @Test
    void moveStone_BadRequest() {
        final PlayerCommand playerCommand = new PlayerCommand();
        playerCommand.setId(null);
        playerCommand.setPlayer(null);
        playerCommand.setPitIndex(-1);
        ResponseEntity<MancalaDto> response = gameControllerToTest.moveStone(playerCommand);
        verify(mockGameService).moveStone(playerCommand.getId(), playerCommand.getPlayer(), playerCommand.getPitIndex());
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    void reset() {
        gameControllerToTest.reset(anyLong());
        verify(mockGameService).resetGame(anyLong());
    }

    @Test
    void reset_BadRequest() {
        ResponseEntity<MancalaDto> response = gameControllerToTest.reset(null);
        verify(mockGameService).resetGame(anyLong());
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


}
