package com.example.sibanez.miner;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class Buscaminas extends AppCompatActivity {
    private static final String EASY = "EASY";
    private static final int EASY_GAME_ROWS = 4;
    private static final int EASY_GAME_COLUMS = 4;
    private static final int EASY_MINES = 3;

    private static final String MEDIUM = "MEDIUM";
    private static final int MEDIUM_GAME_ROWS = 5;
    private static final int MEDIUM_GAME_COLUMS = 6;
    private static final int MEDIUM_MINES = 10;

    private static final String HARD = "HARD";
    private static final int HARD_GAME_ROWS = 6;
    private static final int HARD_GAME_COLUMS = 7;
    private static final int HARD_MINES = 15;

    private static final int WHITE = 0x0000FF00;

    private Board[][] board;
    private BoardOperations init_game;
    private Level level;
    private TableLayout box;
    private ImageButton newGameButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscaminas);

        levelSelect(EASY);

        newGameButton = (ImageButton) findViewById(R.id.reset);
        newGameButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Buscaminas.this.drawSmile();
            }
        });

        initGame();
        drawBoard();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.easy:
                Toast.makeText(getApplicationContext(), "Easy Selected", Toast.LENGTH_LONG).show();
                levelSelect(EASY);
                break;
            case R.id.medium:
                Toast.makeText(getApplicationContext(), "Medium Selected", Toast.LENGTH_LONG).show();
                levelSelect(MEDIUM);
                break;
            case R.id.hard:
                Toast.makeText(getApplicationContext(), "Hard Selected", Toast.LENGTH_LONG).show();
                levelSelect(HARD);
                break;
            case R.id.help:
                Toast.makeText(getApplicationContext(), "Andeandaran las minas", Toast.LENGTH_LONG).show();
                return true;
            case R.id.playMachine:
                drawPlayMachine();
                Toast.makeText(getApplicationContext(), "La maquina juega sola", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

        box.removeAllViews();
        initGame();
        drawBoard();
        return true;
    }

    private void levelSelect(String level) {
        if (level.equals(EASY)) {
            this.level = new Level(EASY_GAME_ROWS, EASY_GAME_COLUMS, EASY_MINES, EASY_GAME_ROWS * EASY_GAME_COLUMS);
        } else if (level.equals(MEDIUM)) {
            this.level = new Level(MEDIUM_GAME_ROWS, MEDIUM_GAME_COLUMS, MEDIUM_MINES, MEDIUM_GAME_ROWS * MEDIUM_GAME_COLUMS);
        } else if (level.equals(HARD)) {
            this.level = new Level(HARD_GAME_ROWS, HARD_GAME_COLUMS, HARD_MINES, HARD_GAME_ROWS * HARD_GAME_COLUMS);
        }
    }

    private void drawSmile() {
        box.removeAllViews();
        this.newGameButton.setImageResource(R.drawable.playing);
        initGame();
        drawBoard();
    }

    private void initGame() {
        boolean push_click = false;
        init_game = new BoardOperations(level.getRow(), level.getCol(), level.getMines(), push_click);
        board = init_game.initGame();
    }

    private int sizeWindows(String type) {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        if (type.equals("width")) {
            return size.x / level.getCol();
        } else {
            return size.y / level.getRow();
        }
    }

    private TableRow initTableRow() {
        int width = sizeWindows("width") * level.getCol();
        int height = sizeWindows("height");

        TableRow table_row = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(width, height);
        table_row.setLayoutParams(lp);

        return table_row;
    }

    private void drawMines() {
        if (box != null) {
            box.removeAllViews();
        }
        box = (TableLayout) findViewById(R.id.board);

        for (int row = 0; row < level.getRow(); row++) {
            TableRow table_row = initTableRow();

            for (int col = 0; col < level.getCol(); col++) {
                ImageButton imgbut = new ImageButton(this);
                imgbut.setBackgroundColor(WHITE);
                if (board[row][col].isMine()) {
                    imgbut.setImageResource(R.mipmap.mine);
                } else {
                    if (board[row][col].getNeighborMinesCount() != 0) {

                        drawNeighborMinesCount(imgbut, row, col);
                        board[row][col].setNeighborMinesCount(0);
                    } else {
                        imgbut.setImageResource(R.mipmap.block);
                    }
                }
                table_row.addView(imgbut);
            }
            table_row.setGravity(Gravity.CENTER);
            box.addView(table_row);
        }
    }

    private void drawBoard() {
        box = (TableLayout) findViewById(R.id.board);

        for (int row = 0; row < level.getRow(); row++) {
            TableRow table_row = initTableRow();

            for (int col = 0; col < level.getCol(); col++) {
                ImageButton imgbut = new ImageButton(this);
                imgbut.setBackgroundColor(WHITE);
                imgbut.setImageResource(R.mipmap.block);
                imgbut.setOnClickListener(new Box(new Board(row, col, board[row][col].isMine(), board[row][col].isClick()), level));
                table_row.addView(imgbut);
            }
            table_row.setGravity(Gravity.CENTER);

            box.addView(table_row);
        }
    }

    private void drawNeighborMinesCount(ImageButton imgbut, int row, int col) {
        switch (board[row][col].getNeighborMinesCount()) {
            case 0:
                imgbut.setImageResource(R.mipmap.cero);
                break;
            case 1:
                imgbut.setImageResource(R.mipmap.one);
                break;
            case 2:
                imgbut.setImageResource(R.mipmap.two);
                break;
            case 3:
                imgbut.setImageResource(R.mipmap.three);
                break;
            case 4:
                imgbut.setImageResource(R.mipmap.four);
                break;
            case 5:
                imgbut.setImageResource(R.mipmap.five);
                break;
            case 6:
                imgbut.setImageResource(R.mipmap.six);
                break;
            case 7:
                imgbut.setImageResource(R.mipmap.seven);
                break;
            case 8:
                imgbut.setImageResource(R.mipmap.eight);
                break;
        }

    }

    private void drawPlayMachine() {
        boolean win = true;

        if (box != null) {
            box.removeAllViews();
        }
        box = (TableLayout) findViewById(R.id.board);

        for (int row = 0; row < level.getRow(); row++) {
            TableRow table_row = initTableRow();

            for (int col = 0; col < level.getCol(); col++) {
                ImageButton imgbut = new ImageButton(this);
                imgbut.setBackgroundColor(WHITE);
                if (board[row][col].isMine()) {
                    imgbut.setImageResource(R.mipmap.mine);
                } else {
                    board[row][col].setNeighborMinesCount(0);
                    board[row][col].setNeighborMinesCount(init_game.getNeighbor(row, col));
                    drawNeighborMinesCount(imgbut, row, col);
                }
                table_row.addView(imgbut);
            }
            table_row.setGravity(Gravity.CENTER);
            box.addView(table_row);
        }
        winner(win);
    }

    private void winner(boolean win) {
        if (win)
            Toast.makeText(getApplicationContext(), "YOU WIN", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), "YOU LOSE", Toast.LENGTH_LONG).show();
    }

    private class Box implements View.OnClickListener {
        private Board tablero;
        private int flag_count;
        private Board flag[][];
        private Level level;
        private boolean is_winner = false;

        public Box(Board board, Level level) {
            this.tablero = board;
            this.level = level;
        }

        @Override
        public void onClick(View v) {
            ImageButton imgbut = (ImageButton) v;
            imgbut.setBackgroundColor(WHITE);
            uncover(imgbut);
        }

        private void uncover(ImageButton imgbut) {
            if (tablero.isMine()) {
                imgbut.setImageResource(R.mipmap.mine);
                winner(is_winner);
                drawMines();
                finish();
            } else {
                recursiveUncover(tablero.getRow(), tablero.getColumn(), imgbut);
            }
        }

        private void recursiveUncover(int row, int col, ImageButton imgbut) {

            if (!tablero.isMine()) {
                //tablero.setNeighborMinesCount(0);
                board[row][col].setNeighborMinesCount(0);
                tablero.setNeighborMinesCount(init_game.getNeighbor(row, col));
                drawNeighborMinesCount(imgbut, row, col);
            }

            if (!board[row][col].isClick())
                level.setCovered_blocks_count(level.getCovered_blocks_count() - 1);

            if (level.getCovered_blocks_count() == level.getMines()) {
                is_winner = true;
                winner(is_winner);
                finish();
            }
            board[row][col].setClick(true);
        }

        private void flag(int row, int col) {
            if (!flag[row][col].isFlagged()) {
                this.flag_count++;
                flag[row][col].setFlagged(true);
            }
        }

        private void unflag(int row, int col) {
            if (this.flag_count >= 0 && flag[row][col].isFlagged()) {
                this.flag_count--;
                flag[row][col].setFlagged(false);
            }
        }
    }
}