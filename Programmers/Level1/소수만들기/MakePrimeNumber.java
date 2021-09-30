package com.algorithmTraining;

import java.util.ArrayList;
import java.util.List;

public class MakePrimeNumber {
    public static void main(String[] args) {
        int[] nums = {1,2,7,6,4};
        System.out.println(solution(nums)); //expected value:4
    }
    public static int solution(int[] nums){
        int count = 0;
        List<Integer> sumList = new ArrayList<>();
        List<Integer> decimalList;
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            for(int j = i+1; j < nums.length; j++){
                for(int z = j+1; z< nums.length; z++){
                    sum = nums[i] + nums[j] + nums[z];
                    sumList.add(sum);
                }
            }
        }
        decimalList = getPrimeNumber();

        sumList.retainAll(decimalList);  //교집합
        count = sumList.size();
        return  count;
    }

    public static List<Integer> getPrimeNumber(){
        int num = 2997;
        List<Integer> decimalList = new ArrayList<>();

        boolean[] arr = new boolean[num+1];    //true 이면 해당 인덱스 소수.
        arr[0] = arr[1] = false;
        for(int i=2; i<=num; i+=1) {
            arr[i] = true;
        }

        for(int i=2; i*i<=num; i+=1) {
            for(int j=i*i; j<=num; j+=i) {
                arr[j] = false;
            }
        }
        for(int i=0; i<=num; i+=1) {
            if(arr[i]) {
                decimalList.add(i);
            }
        }
        return decimalList;
    }
}
