package com.practice.design.chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class ChessBoard {

    Piece[][] board = new Piece[8][8];

    static void initialize() {
        // Initialize army for black and white
    }

    public void movePieceFromTo(int xF, int yF, int xT, int yT) {
        Piece piece = board[xF][yF];
        piece.moveTo(xT, yT);
    }
}
