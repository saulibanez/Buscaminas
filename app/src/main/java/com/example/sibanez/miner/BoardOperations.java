package com.example.sibanez.miner;

import java.util.Random;

public class BoardOperations {
    private int mines_count, rows, cols;
    private boolean click;
    private Board[][] cell;


    public BoardOperations(int rows, int cols, int mines_count, boolean click) {
        this.rows = rows;
        this.cols = cols;
        this.mines_count = mines_count;
        this.click = click;
    }

    public Board[][] initGame() {

        cell = new Board[rows][cols];
        int rndrow, rndcolumn;
        Random rnd_column = new Random();
        Random rnd_row = new Random();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cell[row][col] = new Board(row, col, false, false);
            }
        }

        for (int mine = 0; mine < mines_count; mine++) {
            rndrow = rnd_row.nextInt(rows - 1);
            rndcolumn = rnd_column.nextInt(cols - 1);
            if (cell[rndrow][rndcolumn].isMine()) {
                mine--;
            }
            cell[rndrow][rndcolumn].setMine(true);
        }

        return cell;
    }

    public int getNeighbor(int row, int col) {
        Board tablero = cell[row][col];
        if (row > 0) {
            if (col > 0 && cell[row - 1][col - 1].isMine()) {
                tablero.increaseNeighborMines();
            }

            if (cell[row - 1][col].isMine()) {
                tablero.increaseNeighborMines();
            }

            if (col < this.cols - 1 && cell[row - 1][col + 1].isMine()) {
                tablero.increaseNeighborMines();
            }
        }

        if (col > 0 && cell[row][col - 1].isMine()) {
            tablero.increaseNeighborMines();
        }
        if (col < this.cols - 1 && cell[row][col + 1].isMine()) {
            tablero.increaseNeighborMines();
        }

        if (row < this.rows - 1) {
            if (col > 0 && cell[row + 1][col - 1].isMine()) {
                tablero.increaseNeighborMines();
            }
            if (cell[row + 1][col].isMine()) {
                tablero.increaseNeighborMines();
            }
            if (col < this.cols - 1 && cell[row + 1][col + 1].isMine()) {
                tablero.increaseNeighborMines();
            }
        }
        return tablero.getNeighborMinesCount();
    }

}
