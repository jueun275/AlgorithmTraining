package com.algorithmTraining;

import java.util.Arrays;

public class SecretMap {
    public static void main(String[] args) {
        int n = 6;
        int[] arr1 = {46, 33, 33 ,22, 31, 50};
        int[] arr2 = {27 ,56, 19, 14, 14, 10};
        String[] result = solution(n, arr1, arr2);
        System.out.print(Arrays.toString(result));
    }
    public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = {};
        String[] arr3 = new String[n];
        for(int i = 0; i < n; i++){
            arr3[i] = String.format("%0" + (n) + "d", Long.parseLong(Integer.toBinaryString(arr1[i] | arr2[i])))
                    .replace("1","#")
                    .replace("0"," ");
        }
        answer = arr3;
        return answer;
    }
}

