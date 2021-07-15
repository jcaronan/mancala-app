package com.bol.mancala.controller;

import com.bol.mancala.dto.MancalaDto;
import com.bol.mancala.dto.PlayerCommand;
import com.bol.mancala.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestControllerAdvice
@RequestMapping("api/v1/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity<MancalaDto> createGame() {
        MancalaDto newGame = gameService.initGame();
        return ResponseEntity.ok(newGame);
    }

    @PostMapping("/move")
    public ResponseEntity<MancalaDto> moveStone(@Valid @RequestBody PlayerCommand playerCommand, BindingResult result) throws BindException {
        if (result.hasErrors()) {
            throw new BindException(result);
        }
        MancalaDto mancalaDto = gameService.moveStone(playerCommand.getId(),playerCommand.getPlayer(), playerCommand.getPitIndex());
        return ResponseEntity.ok(mancalaDto);
    }

    @PostMapping("reset")
    public ResponseEntity<MancalaDto> reset(@Valid @NotNull @RequestParam Long id, BindingResult result) throws BindException {
        if (result.hasErrors()) {
            throw new BindException(result);
        }
        MancalaDto mancalaDto = gameService.resetGame(id);
        return ResponseEntity.ok(mancalaDto);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Map<String, String> handleValidationExceptions(BindException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
