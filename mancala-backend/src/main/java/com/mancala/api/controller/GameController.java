package com.mancala.api.controller;

import com.mancala.api.dto.MancalaDto;
import com.mancala.api.dto.PlayerCommand;
import com.mancala.api.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/mancala")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity<MancalaDto> createGame() {
        MancalaDto newGame = gameService.initGame();
        return ResponseEntity.ok(newGame);
    }

    @PostMapping("/move")
    public ResponseEntity<MancalaDto> moveStone(@Valid @RequestBody PlayerCommand playerCommand) {
        MancalaDto mancalaDto = gameService.moveStone(playerCommand.getId(),playerCommand.getPlayer(), playerCommand.getPitIndex());
        return ResponseEntity.ok(mancalaDto);
    }

    @PostMapping("reset")
    public ResponseEntity<MancalaDto> moveStone(@RequestParam Long id) {
        MancalaDto mancalaDto = gameService.resetGame(id);
        return ResponseEntity.ok(mancalaDto);
    }





}
