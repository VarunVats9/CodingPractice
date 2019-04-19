package com.practice.design.chess;

abstract class Piece {

    protected int color;
    protected int currX, currY;
    protected Space space;
    protected Piece[][] board;

    enum Space {
        OCUPPIED,
        EMPTY
    }

    public Piece(int x, int y, int color, Piece[][] board) {
        this.currX = x;
        this.currY = y;
        this.color = color;
        this.board = board;
    }

    void moveTo(int x, int y) {
        if (!isMovePossible(x, y)) {
            showErrorMessage(x, y);
        }
        makeTheMove(x, y);
    }

    boolean isEmpty() {
        return this.space == Space.EMPTY;
    }

    void makeTheMove(int x, int y) {
        // Cannot make any move
    }

    boolean isMovePossible(int x, int y) {
        return false;
    }

    void showErrorMessage(int x, int y) {
        // Cannot move further.
    }
}
