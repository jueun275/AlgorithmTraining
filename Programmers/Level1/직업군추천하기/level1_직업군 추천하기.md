# level1_직업군 추천하기

## notion에서 보기   
https://diligent-persimmon-0f2.notion.site/level1_-23820cea66a541ea8ae1e63a53f4f374   

   
## 문제 링크
https://programmers.co.kr/learn/courses/30/lessons/84325
   
## 풀이

1. table 배열을 하나씩 받아서 공백을 기준으로 문자열을 분리한다
2. for문을 돌면서 직업군별 언어 에서 사용자가 사용하는 언어를 찾는다
3.  개발자가 사용하는 언어의 점수`(직업군별 언어점수 * 언어 선호도)`를 계산해서 더해준다
4. for문이 다 돌면 현재 직업의 점수와 전에 계산했던 직업에 점수를 비교한다
    1. 더 점수가 높은 쪽의 직업을 answer에 저장한다
    2. 만약 점수가 같으면 직업의 이름을 비교해서 사전 순으로 가장 빠른 직업군을 answer에 저장한다
5. answer을 반환한다

   
## 코드 1

```java
import java.util.*;

class Solution {
    public String solution(String[] table, String[] languages, int[] preference) {
        String[] info = {};
        String answer = "";
        int maxSum = 0;
        int curSum = 0;

        for (String s : table) {
            info = s.split(" ");
            String infoName = info[0];
            curSum = 0;

            for (int i = 1; i < 6; i++) {
                for (int j = 0; j < languages.length; j++) {
                    if (info[i].equals(languages[j])) {
                        curSum += (6 - i) * preference[j];
                        break;
                    }
                }
            }
            if (maxSum < curSum) {
                maxSum = curSum;
                answer = infoName;
            }

            if (maxSum == curSum && answer.compareTo(infoName) > 0) {answer = infoName; }

        }
        return answer;
    }
}
```

 

## 코드 제출결과 1
> 테스트 1 〉	통과 (0.11ms, 70.5MB)      
테스트 2 〉	통과 (0.15ms, 69.3MB)   
테스트 4 〉	통과 (0.09ms, 71.5MB)   
테스트 5 〉	통과 (0.10ms, 71.8MB)   
테스트 6 〉	통과 (0.13ms, 62.3MB)   
테스트 7 〉	통과 (0.10ms, 62MB)   
테스트 8 〉	통과 (0.09ms, 72.6MB)   
테스트 9 〉	통과 (0.10ms, 71.5MB)   
테스트 10 〉	통과 (0.09ms, 59.9MB)  
   
위 코드는 조건에 맞는 직업군만 저장하는 코드라서 조건이 바뀌면 ( ex 점수순으로 반환하시오, 두번째로 높은 점수의 직업군을 반환하시오 등)
새로 작성해야할 코드가 많다.   
조건이 바뀌어도 정렬기준만 바꾸면 원하는 답이 나왔으면 해서 점수에 대한 내용을 List에 저장시켰다 
   
## 코드 2
```java
import java.util.*;

class Solution {
    public String solution(String[] table, String[] languages, int[] preference) {
        List<JobScore> jobScores = new ArrayList<>();
        String[] info = {};
        
        for (String s : table) {
            info = s.split(" ");
            String infoName = info[0];
            int curSum = 0;

            for (int i = 1; i < 6; i++) {
                for (int j = 0; j < languages.length; j++) {
                    if (info[i].equals(languages[j])) {
                        curSum += (6 - i) * preference[j];
                        break;
                    }
                }
            }
            jobScores.add(new JobScore(infoName, curSum));
            
            //sort 점수순으로 내림차순 정렬, 이름순으로 오름차순 정렬
            jobScores.sort(Comparator.comparing(JobScore::getScore).reversed().thenComparing(JobScore::getName));
				}
        return jobScores.get(0).getName(); 
    }
}

// VO
class JobScore {
    private String name;
    private int score;

    public JobScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName(){
        return this.name;
    }
    public Integer getScore(){
        return this.score;
    }
}
```

## 코드 제출결과 2
>테스트 1 〉	통과 (3.70ms, 72.7MB)   
테스트 2 〉	통과 (2.73ms, 72.2MB)   
테스트 3 〉	통과 (5.09ms, 72.3MB)   
테스트 4 〉	통과 (3.25ms, 70.6MB)   
테스트 5 〉	통과 (4.82ms, 59.1MB)   
테스트 6 〉	통과 (3.56ms, 65.7MB)   
테스트 7 〉	통과 (3.61ms, 68.8MB)   
테스트 8 〉	통과 (7.93ms, 71.4MB)   
테스트 9 〉	통과 (5.86ms, 61.6MB)   
테스트 10 〉	통과 (4.08ms, 56.9MB)   
    
위 코드로 제출 결과 테스트 통과는 했으나 `jobScores.sort(Comparator.comparing(JobScore::getScore).reversed().thenComparing(JobScore::getName))`코드 때문인지 처음 코드에 비해 속도가  너무 느려져서 속도 개선을 위해 Comparable 인터페이스의  compareTo 를 Override 하여 정렬하였다 .
   
## 코드3
```java
import java.util.*;

class Solution {
    public String solution(String[] table, String[] languages, int[] preference) {
        List<JobScore> jobScores = new ArrayList<>();
        String[] info = {};
        
        for (String s : table) {
            info = s.split(" ");
            String infoName = info[0];
            int curSum = 0;

            for (int i = 1; i < 6; i++) {
                for (int j = 0; j < languages.length; j++) {
                    if (info[i].equals(languages[j])) {
                        curSum += (6 - i) * preference[j];
                        break;
                    }
                }
            }
            jobScores.add(new JobScore(infoName, curSum));

            //sort 점수순으로 내림차순 정렬, 이름순으로 오름차순 정렬
            Collections.sort(jobScores);
            //jobScores.sort(Comparator.comparing(JobScore::getScore).reversed().thenComparing(JobScore::getName));
			}
        return jobScores.get(0).getName(); 
    }
}

// VO
class JobScore implements Comparable<JobScore> {
    private String name;
    private int score;

    public JobScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName(){
        return this.name;
    }
    public Integer getScore(){
        return this.score;
    }
    
    @Override
    public int compareTo(JobScore o) {
        if (this.score < o.getScore()) {//점수
            return 1;
        } else if (this.score > o.getScore()) {
            return -1;
        }else { //점수가 같으면 이름순으로 정렬
            if (this.name.compareTo(o.getName()) > 0) {
                 return 1;
            }else if(this.name.compareTo(o.getName()) < 0) {
                return -1;
            }
            return 0;
        }
    }      
}
```

## 코드 제출결과 3 
>테스트 1 〉	통과 (0.60ms, 73.4MB)   
테스트 2 〉	통과 (0.44ms, 71.2MB)   
테스트 3 〉	통과 (0.58ms, 70.9MB)   
테스트 4 〉	통과 (0.67ms, 60.2MB)   
테스트 5 〉	통과 (0.51ms, 70.3MB)   
테스트 6 〉	통과 (0.49ms, 69.9MB)   
테스트 7 〉	통과 (0.53ms, 72.2MB)   
테스트 8 〉	통과 (0.45ms, 59.5MB)   
테스트 9 〉	통과 (0.44ms, 71.7MB)   
테스트 10 〉	통과 (0.44ms, 72MB)   
   
   

처음코드 보다는 시간이 더 걸리지만 두번째 코드 보다는 빨라졌다.   
어떤 코드가 좋은 코드인지는 아직  잘 모르겠지만, 
좋은 코드를 작성 할 수 있도록 같은 문제를 풀더라도 여러 방법의 해결법을 찾아봐야겠다 .

   
## 이문제에서 생각해볼 점   
- 다중조건정렬
- Comparable와 Comparator , 람다
