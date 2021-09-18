# level1_예산

## 문제링크
[https://programmers.co.kr/learn/courses/30/lessons/12982](https://programmers.co.kr/learn/courses/30/lessons/12982)   

## notion에서 보기
https://diligent-persimmon-0f2.notion.site/level1_-6ef2c488a5724ac1b75b784115499c9e

## 풀이
1. 배열을 정렬한다 (오름차순)
2. 남은 예산과 배열에 들어있는 부서의 신청금액을 비교한다
    1. 예산이 신청금액 보다 많을경우 예산에서 신청금액을 뺴고 answer에 + 1 해준다
    2. 예산이 더 작을경우 break 한다
3. answer 을 return 한다

## 코드

```java
import java.util.*;

class Solution {
    public int solution(int[] d, int budget) {
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
```

## 제출결과 
>테스트 1 〉	통과 (0.34ms, 60.7MB)   
테스트 2 〉	통과 (0.33ms, 72.2MB)   
테스트 3 〉	통과 (0.32ms, 72.1MB)   
테스트 4 〉	통과 (0.32ms, 73.8MB)   
테스트 5 〉	통과 (0.33ms, 72.8MB)   
테스트 6 〉	통과 (0.45ms, 59.6MB)   
테스트 7 〉	통과 (0.54ms, 70.7MB)   
테스트 8 〉	통과 (0.36ms, 71MB)   
테스트 9 〉	통과 (0.34ms, 59.4MB)   
테스트 10 〉	통과 (0.48ms, 59.4MB)   
테스트 11 〉	통과 (0.47ms, 60.9MB)   
테스트 12 〉	통과 (0.42ms, 60.4MB)   
테스트 13 〉	통과 (0.41ms, 70.7MB)   
테스트 14 〉	통과 (0.35ms, 59.2MB)    
테스트 15 〉	통과 (0.36ms, 59.4MB)   
테스트 16 〉	통과 (0.36ms, 73.5MB)   
테스트 17 〉	통과 (0.42ms, 73.9MB)   
테스트 18 〉	통과 (0.33ms, 60.3MB)   
테스트 19 〉	통과 (0.37ms, 59.4MB)   
테스트 20 〉	통과 (0.34ms, 70.7MB)   
테스트 21 〉	통과 (0.35ms, 59.1MB)      
테스트 22 〉	통과 (0.33ms, 58.4MB)   
테스트 23 〉	통과 (0.33ms, 60.2MB)      



