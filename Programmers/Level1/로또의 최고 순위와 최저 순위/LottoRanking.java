package com.algorithmTraining;

import java.util.Arrays;

public class LottoRanking {
    public static void main(String[] args) {
        int[] lottos = {44, 1, 0, 0, 31, 25};
        int[] win_nums = {31, 10, 45, 1, 6, 19};
        System.out.print(Arrays.toString(Solution(lottos, win_nums))); // expected value: 3, 5

    }
    public static int[] Solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int matchCount = 0;
        int zeroCount  = 0;

        for (int lotto : lottos) {
            if(lotto == 0){zeroCount++;}

            for (int win_num : win_nums) {
                if (win_num == lotto) {
                    matchCount++;
                }
            }
        }

        if(matchCount == 0 && zeroCount == 6){
            answer[1] = 6;
            answer[0] = 1;
            return answer;
        }

        if(matchCount == 0 && zeroCount == 0){
            answer[1] = 6;
            answer[0] = 6;
            return answer;
        }

        answer[1] = 7 - matchCount;
        answer[0] = 7 - (matchCount + zeroCount);
        return answer;
    }
}
