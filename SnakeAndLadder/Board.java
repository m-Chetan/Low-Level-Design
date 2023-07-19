package SnakeAndLadder;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
    Cell[][] cells;
    int boardSize;

    public Board(int boardSize, int numberOfSnakes, int numberOfLadders){
        this.boardSize = boardSize;
        initializeCells(boardSize);
        addSnakesAndLadders(cells,numberOfSnakes,numberOfLadders);
    }

    public void initializeCells(int boardSize){
        cells = new Cell[boardSize][boardSize];
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                cells[i][j] = new Cell();
            }
        }
    }

    public void addSnakesAndLadders(Cell[][] cells, int numberOfSnakes, int numberOfLadders){
        while(numberOfSnakes > 0){
            int snakeHead = ThreadLocalRandom.current().nextInt(1,boardSize*boardSize);
            int snakeTail = ThreadLocalRandom.current().nextInt(1,boardSize*boardSize);
            if(snakeTail >= snakeHead) continue;
            Jump jump = new Jump();
            jump.start = snakeHead;
            jump.end = snakeTail;
            Cell cell = getCell(snakeHead);            
            cell.jump = jump;
            numberOfSnakes--;
        }

        while(numberOfLadders > 0){
            int ladderStart = ThreadLocalRandom.current().nextInt(1,boardSize*boardSize);
            int ladderEnd = ThreadLocalRandom.current().nextInt(1,boardSize*boardSize);
            if(ladderStart >= ladderEnd) continue;
            Cell cell = getCell(ladderStart);  
            if(cell.jump!=null) continue;  

            Jump jump = new Jump();
            jump.start = ladderStart;
            jump.end = ladderEnd;
                    
            cell.jump = jump;
            numberOfLadders--;
        }
    }


    public Cell getCell(int playerPosition){
        int row = playerPosition/boardSize;
        int col = playerPosition%boardSize;
        return cells[row][col];
    }


}
