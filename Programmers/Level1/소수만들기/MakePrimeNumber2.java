package com.algorithmTraining;

public class MakePrimeNumber2 {
    public static void main(String[] args) {
        int[] nums = {1,2,7,6,4};
        System.out.println(solution(nums)); //expected value:4
    }
    public static int solution(int[] nums) {
        int count = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int z = j + 1; z < nums.length; z++) {
                    sum = nums[i] + nums[j] + nums[z];
                    if(isPrime(sum)) count++;
                }
            }
        }
        return count;
    }
    public static Boolean isPrime(int num){
        if(num < 2 || num%2 == 0) return false;
        for(int i = 3; i*i <= num; i+=2){
            if(num%i == 0) return false;
        }
        return true;
    }
}
