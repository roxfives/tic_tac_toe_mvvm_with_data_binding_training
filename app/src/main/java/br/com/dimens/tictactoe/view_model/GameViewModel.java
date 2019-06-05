package br.com.dimens.tictactoe.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import android.databinding.ObservableArrayMap;

import br.com.dimens.tictactoe.model.Cell;
import br.com.dimens.tictactoe.model.Game;
import br.com.dimens.tictactoe.model.Player;

import static br.com.dimens.tictactoe.utils.StringUtilities.stringFromNumbers;

public class GameViewModel extends ViewModel {

    public ObservableArrayMap<String, String> mCells;
    private Game mGame;

    public void init(String player1, String player2) {
        mGame = new Game(player1, player2);
        mCells = new ObservableArrayMap<>();
    }

    public void onClickedCellAt(int row, int column) {
        if (mGame.getCells()[row][column] == null) {
            mGame.getCells()[row][column] = new Cell(mGame.getCurrentPlayer());
            mCells.put(stringFromNumbers(row, column), mGame.getCurrentPlayer().getValue());
            if (mGame.hasGameEnded()) {
                mGame.reset();
            } else {
                mGame.switchPlayer();
            }
        }
    }

    public LiveData<Player> getWinner() {
        return mGame.getWinner();
    }
}
