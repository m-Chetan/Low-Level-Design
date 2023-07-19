package SnakeAndLadder;

import java.util.LinkedList;
import java.util.Queue;

public class Game {
    Board board;
    Queue<Player> playerList;
    Player winner;
    Dice dice;

    public Game(){
        playerList = new LinkedList<>();
        initializeGame();
    }

    public void initializeGame(){
        board = new Board(10, 5, 5);
        dice = new Dice(1);
        Player player1 = new Player("Chetan",0);
        Player player2 = new Player("Sohan", 0);
        playerList.add(player1);
        playerList.add(player2);
    }

    public void startGame(){
        while(winner == null){
            Player currentPlayer = playerList.poll();
            System.out.println("Player " + currentPlayer.playerName + "'s turn : " + currentPlayer.playerPosition);
            int diceCount = dice.rollDice();

            int newPlayerPosition = currentPlayer.playerPosition + diceCount;
            int destination = board.boardSize * board.boardSize; 
            if(newPlayerPosition >= destination){
                winner = currentPlayer;
                break;
            }
            
            Cell cell = board.getCell(newPlayerPosition);

            if(cell.jump != null){
                String jumpBy = cell.jump.start < cell.jump.end ? "Ladder" : "Snake";
                System.out.println("Jump done by " + jumpBy +" " + newPlayerPosition);
                newPlayerPosition = cell.jump.end;
            }

            currentPlayer.playerPosition = newPlayerPosition;
            playerList.add(currentPlayer);

        }
        
        System.out.println("Winner is " + winner.playerName);
    }
}
