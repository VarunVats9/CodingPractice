package com.practice.design.chess;

abstract public class Piece {

    private int currX, currY, color;

    void moveTo(int x, int y) {
        if (!movePossible(x, y)) {
            showMessage(x, y);
        }

        currX = x;
        currY = y;
    }

    abstract boolean movePossible(int x, int y);

    abstract void showMessage(int x, int y);
}
