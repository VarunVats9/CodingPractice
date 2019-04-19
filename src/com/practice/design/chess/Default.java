package com.practice.design.chess;

public class Default extends Piece {

    public Default(final int x, final int y, final int color, final Piece[][] board) {
        super(x, y, color, board);
        this.space = Space.EMPTY;
    }
}
