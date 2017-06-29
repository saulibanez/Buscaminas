package com.example.sibanez.miner;

/**
 * Created by sibanez on 13/02/17.
 */

public class Board {
    private int row;
    private int column;
    private boolean mine;
    private int neighborMinesCount;
    private boolean containsMine;
    private boolean covered;
    private boolean flagged;
    private boolean click;

    public Board(int row, int column, boolean mine, boolean click) {
        this.row = row;
        this.column = column;
        this.mine = mine;
        this.click = click;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public void reset() {
        this.mine = false;
    }

    public int getNeighborMinesCount() {
        return neighborMinesCount;
    }

    public void setNeighborMinesCount(int neighborMinesCount) {
        this.neighborMinesCount = neighborMinesCount;
    }

    public void increaseNeighborMines() {
        this.neighborMinesCount++;
    }

    public boolean isContainsMine() {
        return containsMine;
    }

    public void setContainsMine(boolean containsMine) {
        this.containsMine = containsMine;
    }

    public boolean isCovered() {
        return covered;
    }

    public void setCovered(boolean covered) {
        this.covered = covered;
    }

    public boolean isEmpty() {
        return (this.neighborMinesCount == 0);
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }
}