package com.practice.design.chess;

public class Pawn extends Piece {

    @Override
    boolean movePossible(final int x, final int y) {
        return false;
    }

    @Override
    void showMessage(final int x, final int y) {

    }
}
