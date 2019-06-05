package br.com.dimens.tictactoe.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import android.databinding.ObservableArrayMap;

import br.com.dimens.tictactoe.model.Cell;
import br.com.dimens.tictactoe.model.Game;
import br.com.dimens.tictactoe.model.Player;

import static br.com.dimens.tictactoe.utils.StringUtilities.stringFromNumbers;

public class GameViewModel extends ViewModel {

    public ObservableArrayMap<String, String> cells;
    private Game game;

    public void init(String player1, String player2) {
        game = new Game(player1, player2);
        cells = new ObservableArrayMap<>();
    }

    public void onClickedCellAt(int row, int column) {
        if (game.getCells()[row][column] == null) {
            game.getCells()[row][column] = new Cell(game.getCurrentPlayer());
            cells.put(stringFromNumbers(row, column), game.getCurrentPlayer().getValue());
            if (game.hasGameEnded())
                game.reset();
            else
                game.switchPlayer();
        }
    }

    public LiveData<Player> getWinner() {
        return game.getWinner();
    }
}
