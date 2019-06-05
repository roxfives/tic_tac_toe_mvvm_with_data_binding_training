package br.com.dimens.tictactoe.model;

import br.com.dimens.tictactoe.utils.StringUtilities;

public class Cell {
    private static Player mPlayer;

    public Cell(Player player) {
        mPlayer = player;
    }

    public boolean isEmpty() {
        return mPlayer == null
                || StringUtilities.isEmptyOrNull(mPlayer.getValue());
    }

    public Player getPlayer() {
        return mPlayer;
    }
}
