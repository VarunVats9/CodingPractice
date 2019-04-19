package com.practice.design.chess;

public class ChessBoard {

    Piece[][] board = new Piece[8][8];

    static void initialize() {
        // Initialize army for black and white
    }

    public void movePieceFromTo(int xF, int yF, int xT, int yT) {
        Piece piece = board[xF][yF];
        if (piece.isEmpty()) {
            // Wrong move, cannot move an empty position;
        }
        piece.moveTo(xT, yT);
    }
}
