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

    public int solution(int[] ranks) {
        Arrays.sort(ranks);
        int reported = 0, prev = ranks[0], sameRank = 1;
        for (int i = 1; i < ranks.length; i++) {
            int curr = ranks[i];
            if (curr == prev) {
                sameRank++;
            } else {
                if (curr == prev + 1) {
                    reported += sameRank;
                }
                sameRank = 1;
                prev = curr;
            }
        }
        return reported;
    }

    public static void main(String[] args) {

        System.out.println(ChessBoard.solution(4, 9));

    }

    public static int solution(int A, int B) {
        if (B <= 3) {
            return 0;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 2; i*i <= 1000000000; i++) {
            int p = i*i;
            arrayList.add(p);
            if (map.containsKey(i)) {
                map.put(p, 1 + map.get(i));
            } else {
                map.put(p, 1);
            }
        }

        int idxA = Collections.binarySearch(arrayList, A);
        System.out.println("idxA : " + idxA);
        if (idxA < 0) {
            idxA = Math.abs(idxA + 1);
        }
        int idxB = Collections.binarySearch(arrayList, B);
        System.out.println("idxB : " + idxB);
        if (idxB < 0) {
            idxB = Math.abs(idxB + 2);
        }

        int max = 0;
        for (int i = idxA; i <= idxB; i++) {
            final Integer key = arrayList.get(i);
            System.out.println("Key : " + key);
            if (map.get(key) > max) {
                max = map.get(key);
            }
        }

        return max;
    }

}
