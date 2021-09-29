# level1_아이디 추천

## notion에서 보기
https://diligent-persimmon-0f2.notion.site/level1_-f9e43dc7b2824358afb372dfb6395bf1   

## 문제링크
[https://programmers.co.kr/learn/courses/30/lessons/72410](https://programmers.co.kr/learn/courses/30/lessons/72410)

## 풀이
1. new_id의 모든 대문자를 대응되는 소문자로 치환합니다.   
2.  new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
3.  new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
4.  new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
5.  new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
6.  new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다. 만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
7.  new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.

## 코드

```java
class Solution {
    public String solution(String new_id) {
        String answer ="";
        new_id = new_id.toLowerCase();  //1
        new_id =new_id.replaceAll("[^a-z0-9._-]", "");//2
        new_id = new_id.replaceAll("[.]{2,}", "."); //3
        new_id = new_id.replaceAll("^[.]|[.]$", ""); //4
        new_id = new_id.isEmpty() ? "a" : new_id; //5
        
        if(new_id.length() > 15){ //6
            new_id = new_id.substring(0, 15);
            new_id = new_id.replaceAll("^[.]|[.]$", "");
        }
        
        while (new_id.length() < 3){ //7
            new_id = new_id + new_id.charAt(new_id.length()-1);
        }
        
        answer = new_id;
        return  answer;
    }
}
```

이번 문제는 다른 방법이 있는 문제라기 보단 문제에 제시 되어있는  7단계의 과정을 모두 처리해주기만 하면되는 문제였다 

## 제출결과
>테스트 1 〉	통과 (0.19ms, 70.6MB)   
테스트 2 〉	통과 (9.90ms, 74.1MB)   
테스트 3 〉	통과 (8.95ms, 74.6MB)   
테스트 4 〉	통과 (0.21ms, 73.2MB)   
테스트 5 〉	통과 (0.36ms, 77.7MB)   
테스트 6 〉	통과 (0.18ms, 75.7MB)   
테스트 7 〉	통과 (0.19ms, 73MB)   
테스트 8 〉	통과 (0.30ms, 75.3MB)   
테스트 9 〉	통과 (10.42ms, 76.6MB)   
테스트 10 〉	통과 (0.23ms, 75.4MB)   
테스트 11 〉	통과 (0.20ms, 74.1MB)   
테스트 12 〉	통과 (0.34ms, 76.8MB)   
테스트 13 〉	통과 (9.02ms, 76.2MB)   
테스트 14 〉	통과 (0.26ms, 74.1MB)   
테스트 15 〉	통과 (0.32ms, 73.4MB)   
테스트 16 〉	통과 (0.49ms, 74.2MB)   
테스트 17 〉	통과 (1.27ms, 76.6MB)  

## 이문제에서 생각해볼점

toUpperCase()와 toLowerCase()

replaceAll()

정규 표현식