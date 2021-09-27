# level1_로또의 최고 순위와 최저 순위

## 노션에서 보기
https://diligent-persimmon-0f2.notion.site/level1_-1ef7137509bb478eb63bbbfc182eaa8a

## 문제 링크

[https://programmers.co.kr/learn/courses/30/lessons/77484](https://programmers.co.kr/learn/courses/30/lessons/77484)

## 풀이

1. for문을 돌면서 맞은 숫자의 갯수와 0의 갯수를 찾는다
2. 최소 순위와, 최고 순위를 계산해서반환한다 

## 코드 1

```java
import java.util.*;
import java.util.stream.IntStream;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int min = 0;
        int max = 0;

        for(int i = 0; i < win_nums.length; i++){
            int index = i;
            if(IntStream.of(lottos).anyMatch(x -> x == win_nums[index])){
                min++;
                max++;
            }
        }

        for(int i = 0; i< win_nums.length; i++){
            if(lottos[i] == 0){
                max++;
            }
        }
        
        if(min == 0 && max == 6){
            min = 1;
        }
        
        if(min == 0 && max == 0){
            min = 1;
            max = 1;
        }
        
        int[] answer = { 7 - max, 7 - min}; 
        return answer;
    }
}
```

## 코드 제출결과
>테스트 1 〉	통과 (3.77ms, 74.9MB)
테스트 2 〉	통과 (2.12ms, 59.8MB)
테스트 3 〉	통과 (2.23ms, 59.9MB)
테스트 4 〉	통과 (3.75ms, 73.6MB)
테스트 5 〉	통과 (2.01ms, 72MB)
테스트 6 〉	통과 (1.77ms, 62.8MB)
테스트 7 〉	통과 (2.04ms, 75.1MB)
테스트 8 〉	통과 (2.55ms, 73MB)
테스트 9 〉	통과 (2.21ms, 59.1MB)
테스트 10 〉	통과 (1.80ms, 74.5MB)
테스트 11 〉	통과 (1.90ms, 74.5MB)
테스트 12 〉	통과 (3.69ms, 70.2MB)
테스트 13 〉	통과 (6.11ms, 72.9MB)
테스트 14 〉	통과 (2.61ms, 59.9MB)
테스트 15 〉	통과 (2.16ms, 74.4MB)

위 코드는 테스트 통과는 했지만 일단 생각나는대로 작성하고 틀린케에스에 대해 예외를 처리한 코드라 잘짜여진(구조적인?) 코드는 아닌 것 같고, 시간도 생각보다 오래걸렸다. 

그냥 뭔가 이중 for문을 사용하는게 별로라 stream을 사용해서 코드를 작성했는데 이 streem 때문에 속도가 느려졌나 싶어서 그냥 for문을 이용해서 코드를 작성해 보았다 

## 코드 2

```jsx
import java.util.*;
import java.util.stream.IntStream;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int min = 0;
        int max  = 0;

        for(int i = 0; i < win_nums.length; i++){
            for(int j = 0; j < lottos.length; j++){
                if(win_nums[i] == lottos[j]){
                    min ++;
                    max ++;
                }
            }
        }

        for(int i = 0; i< win_nums.length; i++){
            if(lottos[i] == 0){
                max++;
            }
        }
        
        if(min == 0 && max == 6){
            min = 1;
        }
        
        if(min == 0 && max == 0){
            min = 1;
            max = 1;
        }
        
        int[] answer = { 7 - max, 7 - min}; 
        return answer;
    }
}
```

## 코드 제출결과
>테스트 1 〉	통과 (0.02ms, 72.4MB)
테스트 2 〉	통과 (0.01ms, 70.6MB)
테스트 3 〉	통과 (0.02ms, 71.8MB)
테스트 4 〉	통과 (0.01ms, 68.7MB)
테스트 5 〉	통과 (0.02ms, 84MB)
테스트 6 〉	통과 (0.03ms, 78.1MB)
테스트 7 〉	통과 (0.02ms, 68.8MB)
테스트 8 〉	통과 (0.02ms, 73.7MB)
테스트 9 〉	통과 (0.01ms, 77.3MB)
테스트 10 〉	통과 (0.01ms, 74.2MB)
테스트 11 〉	통과 (0.03ms, 75.3MB)
테스트 12 〉	통과 (0.03ms, 76MB)
테스트 13 〉	통과 (0.01ms, 77.5MB)
테스트 14 〉	통과 (0.01ms, 74.5MB)
테스트 15 〉	통과 (0.02ms, 74.5MB)

다른 코드는 안 건들이고  stream 을 for문으로 바꾸기만 한건데 처음코드보다 속도가 훨씬 빨라졌다!  사실 stream이 나름 최신 api이기때문에 이중for문을 사용하기 보다 IntStream을 사용하는게 더 좋은 성능을 낼 것이라고 생각 했었는데 아니였다. 

 

지금 보니 두번쨰 for문은 첫번째에서 같이 처리 해도 되어서 수정하였다 

수정하는 김에 min, max의 변수명도 수정하였다 

## 코드 3

```java
class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
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
```

제출결과는 위에 결과랑 비슷해서 생략하였다

### 이문제에서 생각해 볼점

- 보다 최근에 등장 했다고 해서 무조건 좋은 성능을 발휘하는 것은 아니다 . 상황에 따라 적절히 사용하는것이 중요할것 같다

---