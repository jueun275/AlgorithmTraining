package com.algorithmTraining;

import java.util.*;

public class CrainGame {
    public static void main(String[] args) {
        int[][] board = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 3},
                {0, 2, 5, 0, 1},
                {4, 2, 4, 4, 2},
                {3, 5, 1, 3, 1}
        };
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
        int result = solution(board, moves);

        System.out.println(result); //expected value: 4
    }

    public static int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);

        for (int num : moves) {
            for (int i = 0; i < board.length; i++) {
                if (board[i][num - 1] != 0) {
                    if (board[i][num - 1] == stack.peek()) {
                        stack.pop();
                        answer += 2;
                    } else {
                        stack.push(board[i][num - 1]);
                    }
                    board[i][num - 1] = 0;
                    break;
                }
            }
        }
        return answer;
    }
}
