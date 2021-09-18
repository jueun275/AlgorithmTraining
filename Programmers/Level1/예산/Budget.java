package com.algorithmTraining;

import java.util.*;

public class Budget {
    public static void main(String[] args) {
        int[] d = {1,3,2,5,4};
        int budget = 9;

        int result = solution(d, budget);
        System.out.println(result); //expected value: 3
    }

    public static int solution(int[] d, int budget) {
        int answer = 0;
        Arrays.sort(d);

        for(int i = 0; i < d.length; i++){
            if(budget >= d[i]){
                answer += 1;
                budget -= d[i];
            }else{break;}
        }
        return answer;
    }
}
