export interface Mancala {

  id: number;
  p1Pits: Array<number>;
  p2Pits: Array<number>;
  activePlayer: string;
  endOfTurn: boolean;
  gameOver: boolean;
  winner: string;


}
