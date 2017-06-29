package com.example.sibanez.miner;

/**
 * Created by sibanez on 14/02/17.
 */

public class Level {
    private int row;
    private int col;
    private int mines;
    private int covered_blocks_count;

    public Level(int row, int col, int mines, int covered_blocks_count) {
        this.row = row;
        this.col = col;
        this.mines = mines;
        this.covered_blocks_count = covered_blocks_count;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getMines() {
        return mines;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public int getCovered_blocks_count() {
        return covered_blocks_count;
    }

    public void setCovered_blocks_count(int covered_blocks_count) {
        this.covered_blocks_count = covered_blocks_count;
    }
}
