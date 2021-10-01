# level1_소수만들기

## 문제링크

## 풀이1

1. for문을 돌면서 3개의 수를 더하고 값을 리스트에 넣는다 
2. 최대수까지의 소수를 전부 구하고 리스트의 넣는다 
3. 두개의 리스트의 교집합 리스트를 만든다 
4. 리스트의 개수를 반환한다

## 풀이2

1. for문을 돌면서 3개의 수를 더하고 그수가 소수인지 확인다 
2. 소수이면 count를 한다 
3. for문을 모두 돌고 count를 반환한다

처음에 떠올린 방법은 풀이1 이였다. 하지만 n의 개수가 최대 50개인 것에 비해 n이 가질수 있는 최대값은 1000이라 나올수 있는 최대수 2997이(1000 + 999+ 998) 를 설정해 주는 것이 비효율 합을구하고 그수가 소수인지 아닌지 바로 판별하는 방식으로 바꾸었다 

n의 갯수 n이 가질수 있는 최대값에 따라 어느 방법이 효율적인지는 다르겠지만 이번 문제에 조건에서는 풀이 2의 방법이 훨씬 빨랐다 

## 코드1

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(int[] nums) {
        int count = 0;
        List<Integer> sumList = new ArrayList<>();
        List<Integer> decimalList = new ArrayList<>();
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            for(int j = i+1; j < nums.length; j++){
                for(int z = j+1; z< nums.length; z++){
                    sum = nums[i] + nums[j] + nums[z];
                    sumList.add(sum);
                }
            }
        }
        decimalList = getDecimal();

        sumList.retainAll(decimalList);
        count = sumList.size();
        return  count;
    }

    private List<Integer> getDecimal(){
        int num = 3000;
        List<Integer> decimalList = new ArrayList<>();

        boolean[] arr = new boolean[num+1];  
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
        System.out.println();
        return decimalList;
    }

}
```

## 제출결과1
>테스트 1 〉	통과 (11.90ms, 90.1MB)   
테스트 2 〉	통과 (14.94ms, 83.8MB)   
테스트 3 〉	통과 (4.60ms, 77.3MB)   
테스트 4 〉	통과 (5.15ms, 72.5MB)   
테스트 5 〉	통과 (11.78ms, 70.1MB)   
테스트 6 〉	통과 (13.67ms, 73.6MB)   
테스트 7 〉	통과 (3.20ms, 79.4MB)   
테스트 8 〉	통과 (20.22ms, 89.5MB)   
테스트 9 〉	통과 (6.28ms, 79MB)   
테스트 10 〉	통과 (14.86ms, 79.6MB)   
테스트 11 〉	통과 (2.35ms, 73.6MB)   
테스트 12 〉	통과 (3.40ms, 79.4MB)   
테스트 13 〉	통과 (3.48ms, 72.9MB)   
테스트 14 〉	통과 (1.43ms, 76.9MB)   
테스트 15 〉	통과 (1.52ms, 78.3MB)   
테스트 16 〉	통과 (15.79ms, 89.9MB)   
테스트 17 〉	통과 (27.25ms, 85.4MB)   
테스트 18 〉	통과 (1.98ms, 77.1MB)   
테스트 19 〉	통과 (0.70ms, 76.3MB)   
테스트 20 〉	통과 (20.18ms, 76.7MB)      
테스트 21 〉	통과 (17.07ms, 78.4MB)   
테스트 22 〉	통과 (11.46ms, 78.6MB)  
테스트 23 〉	통과 (0.87ms, 72.8MB)   
테스트 24 〉	통과 (17.74ms, 80.1MB)   
테스트 25 〉	통과 (20.47ms, 87.1MB)   
테스트 26 〉	통과 (0.63ms, 71.7MB) 



   

소수찾는 방법은 에라토스테네스의 체 를 이용하여 구하였고
>3개 이상의 소수로 구성된 합성수는 그 수의 제곱근보다 작거나 같은 약수를 갖는다. 

라는 정의 를 이용하여 실행 속도를 단축시켰다 위 정의에 대한 증명은   
>N이 합성수일 때, N = AB이고 1 < A, B < N 이다.  만약 A와 B가 둘 다 N의 제곱근보다 크다면 AB > N이 되어서 모순이 된다. 따라서 A, B 중 적어도 하나는 N의 제곱근보다 작거나 같다.

따라서 1부터 N까지의 모두 구하지 않고 1부터 N의 제곱근 + 1 까지 구하여도 같은값을 얻을수 있다

## 코드2

```java
class Solution {

    public int solution(int[] nums) {
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                for (int z = j+1; z < nums.length; z++) {
                    int sum = nums[i]+nums[j]+nums[z];
                    if (isPrime(sum)) count++;
                }
            }
        }

        return count;
    }

    private boolean isPrime(int num) {
        if(num < 2 || num%2 == 0) return false;
        for(int i = 3; i*i <= num; i+=2){
            if(num%i == 0) return false;
        }
        return true;
    }
}
```

## 제출결과2
>테스트 1 〉	통과 (0.34ms, 76.3MB)   
테스트 2 〉	통과 (0.36ms, 72.4MB)   
테스트 3 〉	통과 (0.11ms, 71MB)   
테스트 4 〉	통과 (0.10ms, 82.2MB)   
테스트 5 〉	통과 (0.63ms, 67.6MB)   
테스트 6 〉	통과 (0.96ms, 77.2MB)   
테스트 7 〉	통과 (0.04ms, 75.5MB)   
테스트 8 〉	통과 (1.67ms, 80.7MB)   
테스트 9 〉	통과 (0.25ms, 79.2MB)   
테스트 10 〉	통과 (1.03ms, 72.8MB)   
테스트 11 〉	통과 (0.03ms, 69.8MB)   
테스트 12 〉	통과 (0.02ms, 76.6MB)   
테스트 13 〉	통과 (0.03ms, 77.4MB)   
테스트 14 〉	통과 (0.03ms, 77.8MB)   
테스트 15 〉	통과 (0.04ms, 80MB)   
테스트 16 〉	통과 (1.33ms, 74.8MB)   
테스트 17 〉	통과 (1.12ms, 71.9MB)   
테스트 18 〉	통과 (0.05ms, 79MB)   
테스트 19 〉	통과 (0.02ms, 79.7MB)   
테스트 20 〉	통과 (1.54ms, 68.1MB)   
테스트 21 〉	통과 (2.15ms, 71.9MB)   
테스트 22 〉	통과 (0.40ms, 73.9MB)   
테스트 23 〉	통과 (0.02ms, 75.9MB)   
테스트 24 〉	통과 (1.87ms, 75.8MB)   
테스트 25 〉	통과 (1.35ms, 77.6MB)   
테스트 26 〉	통과 (0.02ms, 76.6MB)   

## 이문제에서 생각 해볼점

에라토스테네스의 체

리스트의 교집합