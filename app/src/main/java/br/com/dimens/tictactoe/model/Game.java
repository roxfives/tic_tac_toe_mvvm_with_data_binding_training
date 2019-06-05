package br.com.dimens.tictactoe.model;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import br.com.dimens.tictactoe.utils.StringUtilities;

public class Game {
    private static final String TAG = Game.class.getSimpleName();
    private static final int BOARD_SIZE = 3;
    private static final String PLAYER_ONE_MARK = "x";
    private static final String PLAYER_TWO_MARK = "o";

    private Player mPlayer1;
    private Player mPlayer2;

    private Player mCurrentPlayer = mPlayer1;
    private Cell[][] mCells;

    private MutableLiveData<Player> winner = new MutableLiveData<>();

    public Game(String playerOne, String playerTwo) {
        mCells = new Cell[BOARD_SIZE][BOARD_SIZE];

        mPlayer1 = new Player(playerOne, PLAYER_ONE_MARK);
        mPlayer2 = new Player(playerTwo, PLAYER_TWO_MARK);

        mCurrentPlayer = mPlayer1;
    }

    public void switchPlayer() {
        mCurrentPlayer = (mCurrentPlayer == mPlayer1)? mPlayer2 : mPlayer1;
    }

    public boolean hasGameEnded() {
        if(hasThreeSameHorizontalCells()
                || hasThreeSameVerticalCells()
                    || hasThreeSameDiagonalCells()) {
            winner.setValue(mCurrentPlayer);
            return true;
        }

        if(isBoardFull()) {
            winner.setValue(null);
            return true;
        }

        return false;
    }

    public boolean hasThreeSameHorizontalCells() {
        try {
            for(int i = 0; i < BOARD_SIZE; i++) {
                if (areEqual(mCells[i][0], mCells[i][1], mCells[i][2])) {
                    return true;
                }
            }

            return false;
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    public boolean hasThreeSameVerticalCells() {
        try {
            for(int i = 0; i < BOARD_SIZE; i++) {
                if (areEqual(mCells[0][i], mCells[1][i], mCells[2][i])) {
                    return true;
                }
            }

            return false;
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    public boolean hasThreeSameDiagonalCells() {
        try {
            return areEqual(mCells[0][0], mCells[1][1], mCells[2][2]) ||
                    areEqual(mCells[0][2], mCells[1][1], mCells[2][0]);
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }


    public boolean isBoardFull() {
        for(Cell[] row: mCells) {
            for (Cell cell: row) {
                if (cell == null || cell.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void reset() {
        mPlayer1 = null;
        mPlayer2 = null;
        mCurrentPlayer = null;
        mCells = null;
    }

    /**
     * 2 mCells are equal if:
     * - Both are none null
     * - Both have non null values
     * - both have equal values
     *
     * @param cells: Cells to check if are equal
     * @return
     */
    private boolean areEqual(Cell... cells) {
        if(cells == null || cells.length == 0) {
            return false;
        }

        for(Cell cell: cells) {
            if (cell == null ||
                    StringUtilities.isEmptyOrNull(cell.getPlayer().getValue())) {
                return false;
            }
        }

        Cell comparisonBase = cells[0];
        for(int i = 1; i < cells.length; i++) {
            if (!comparisonBase.getPlayer().getValue()
                    .equals(cells[i].getPlayer().getValue())) {
                return false;
            }
        }

        return true;
    }
}
