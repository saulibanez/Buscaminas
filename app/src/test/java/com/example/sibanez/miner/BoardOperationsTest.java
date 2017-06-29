package com.example.sibanez.miner;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardOperationsTest {

    @Test
    public void initGameTest() throws Exception {
        BoardOperations init_game;
        Board[][] board;

        int row = 4;
        int col = 4;
        int mines = 3;
        int cont_mines = 0;
        int cont_free = 0;

        init_game = new BoardOperations(row, col, mines, false);
        board = init_game.initGame();

        for (int rows = 0; rows < row; rows++) {
            for (int cols = 0; cols < col; cols++) {
                if (board[rows][cols].isMine()) {
                    cont_mines++;
                } else {
                    cont_free++;
                }

            }
        }
        assertTrue(cont_free == (row * col) - mines);
        assertTrue(cont_mines == mines);

        for (int rows = 0; rows < 2; rows++) {
            for (int cols = 0; cols < 3; cols++) {
                if (rows == 0 && cols == 0) {
                    board[rows][cols].setMine(true);
                }
                board[rows][cols].setMine(false);
            }
        }

        assertTrue(board[0][1].getNeighborMinesCount() == init_game.getNeighbor(0, 1));
    }
}
