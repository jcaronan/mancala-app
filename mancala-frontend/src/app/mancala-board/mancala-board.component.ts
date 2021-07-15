import { Component, OnInit } from '@angular/core';
import { Mancala } from '../mancala-model';
import { MancalaService } from '../service/mancala.service';
import { Observable, of } from 'rxjs';

enum Player {
  ONE = 'P1',
  TWO = 'P2'
}

@Component({
  selector: 'app-mancala-board',
  templateUrl: './mancala-board.component.html',
  styleUrls: ['./mancala-board.component.css']
})

export class MancalaBoardComponent implements OnInit {
  player = Player;
  mancala: Mancala;
  playerTurn = null;

  constructor(private api: MancalaService) { }

  ngOnInit(): void {
    this.createGame();
  }

  createGame(): void {
   this.api.initGame().subscribe(mancala => this.mancala = mancala);
  }

  resetGame(id): void {
     this.api.resetGame(id).subscribe(mancala => this.mancala = mancala);
  }

  move(id, currentPlayer, index) {
    if(this.checkTurn(currentPlayer)) {
      const playerCommand  = {id : id, player : currentPlayer, pitIndex: index};
      this.api.updateGame(playerCommand)
      .subscribe(mancala => {
        this.mancala = mancala;
        if(currentPlayer == this.player.ONE && mancala.endOfTurn) {
          this.playerTurn = this.player.TWO;
        }else if(currentPlayer == this.player.TWO && mancala.endOfTurn) {
          this.playerTurn = this.player.ONE;
        } else {
          this.playerTurn = currentPlayer;
        }
      });

    }
  }

  checkTurn(currentPlayer) {
    if(this.mancala.activePlayer == null) {
        this.playerTurn = currentPlayer;
        return true;
    }

    if(currentPlayer == this.playerTurn) {
      return true;
    } else {
      return false;
    }

  }

  getWinner() {

  }

}
