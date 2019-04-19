package com.practice.design.chess;

public class Pawn extends Piece {

    @Override
    void makeTheMove(final int x, final int y) {
    }

    @Override
    boolean isMovePossible(final int x, final int y) {
        return false;
    }

    @Override
    void showErrorMessage(final int x, final int y) {
    }

    public Pawn(final int x, final int y, final int color, final Piece[][] board) {
        super(x, y, color, board);
    }
}
