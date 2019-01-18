package edu.upc.eetac.dsa;

public class Board {
    String data;
    int level;

    public Board(String data, int level) {
        this.data = data;
        this.level = level;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
