package com.bol.mancala.controller;

import com.bol.mancala.dto.MancalaDto;
import com.bol.mancala.dto.PlayerCommand;
import com.bol.mancala.helper.Player;
import com.bol.mancala.service.GameService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class GameControllerTest {

    @MockBean
    private GameService mockGameService;

    @Mock
    private BindingResult mockBindingResult;

    @BeforeEach
    public void setupTest() {
        MockitoAnnotations.initMocks(this);
    }


    @Autowired
    private GameController gameControllerToTest;

    @Test
    void init() {
        gameControllerToTest.createGame();
        verify(mockGameService).initGame();
    }

    @Test
    void moveStone() throws BindException {
        final PlayerCommand playerCommand = new PlayerCommand();
        playerCommand.setId(1L);
        playerCommand.setPlayer(Player.P1);
        playerCommand.setPitIndex(1);
        Mockito.when(mockBindingResult.hasErrors()).thenReturn(false);
        gameControllerToTest.moveStone(playerCommand,mockBindingResult);
        verify(mockGameService).moveStone(playerCommand.getId(), playerCommand.getPlayer(), playerCommand.getPitIndex());
    }

    @Test
    void moveStone_BadRequest() {
        final PlayerCommand playerCommand = new PlayerCommand();
        playerCommand.setId(null);
        playerCommand.setPlayer(null);
        playerCommand.setPitIndex(-1);
        Mockito.when(mockBindingResult.hasErrors()).thenReturn(true);
        Assertions.assertThrows(BindException.class, () -> {
            ResponseEntity<MancalaDto> response = gameControllerToTest.moveStone(playerCommand,mockBindingResult);
        });
    }

    @Test
    void reset() throws BindException {
        Mockito.when(mockBindingResult.hasErrors()).thenReturn(false);
        gameControllerToTest.reset(1L, mockBindingResult);
        verify(mockGameService).resetGame(anyLong());
    }

    @Test
    void reset_BadRequest() {
        Mockito.when(mockBindingResult.hasErrors()).thenReturn(true);
        Assertions.assertThrows(BindException.class, () -> {
            ResponseEntity<MancalaDto> response = gameControllerToTest.reset(null, mockBindingResult);
        });
    }


}
