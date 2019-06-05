package br.com.dimens.tictactoe.model;

public class Player {
    private String mName;
    private String mValue;

    public Player(String name, String value) {
        mName = name;
        mValue = value;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setValue(String value) {
        mValue = value;
    }

    public String getName() {
        return mName;
    }

    public String getValue() {
        return mValue;
    }
}
