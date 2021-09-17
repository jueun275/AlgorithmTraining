# level1_크레인 인형뽑기 게임

## 문제 링크
[https://programmers.co.kr/learn/courses/30/lessons/64061](https://programmers.co.kr/learn/courses/30/lessons/64061)

## notion에서 보기   
https://diligent-persimmon-0f2.notion.site/level1_-17d8794cf3db4b1b8c1ef3b68c1cad2a

## 풀이

1. moves에 저장된 위치들에서 숫자를 하나씩 가져온다
    1. 0이면 빈칸이기 때문에 다음칸으로 넘어간다
    2. 0이 아닌 다른 숫자이면 그숫자를 stack에 넣어주고 숫자가 있던 칸에는 0을 넣어준다
        1. 만약 stack에 넣은 숫자가 연속으로 같은 숫자이면 숫자가 터지고 몇번 터지는지 count 한다
2. count한 숫자를 return 한다  

## 코드 1

```java
import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int curNum = 0;
        int preNum = 0;
        
        for(int num : moves){
            preNum = curNum;
            for(int i = 0; i < board.length; i++){
                if(board[i][num - 1] != 0){
                    curNum = board[i][num - 1];
                    board[i][num - 1] = 0;
                    break;
                }
            }
            if(curNum == preNum){
                answer += 2;  //터지는수 카운드
            }
        }
        return answer;
    }
}
```

## 제출결과 1
>테스트 1 〉	실패 (0.03ms, 73.3MB)   
테스트 2 〉	실패 (0.03ms, 71.1MB)   
테스트 3 〉	통과 (0.03ms, 70.9MB)   
테스트 4 〉	통과 (0.55ms, 70MB)   
테스트 5 〉	실패 (0.02ms, 60.5MB)   
테스트 6 〉	실패 (0.03ms, 74.8MB)   
테스트 7 〉	실패 (0.03ms, 59MB)   
테스트 8 〉	실패 (0.13ms, 69.8MB)   
테스트 9 〉	실패 (0.07ms, 59.1MB)   
테스트 10 〉	실패 (0.10ms, 71.5MB)   
테스트 11 〉	실패 (0.17ms, 72.9MB)   
   

예비 테스트 케이스? 를 통과해서 제출을 했었는데 실패했다. 무엇을 놓쳤는지 천천히 생각해보니 바구니 안에 같은 숫자가 겹치면 터지면서 저장되어 있던 숫자도 삭제가 되어야 하는데 위 코드는 단순히 터지는 경우만을 카운트 해주기 때문에  이 부분을 처리해 주지 못했다. 

가장 먼저 생각난 방법은 List사용한 방법이였지만 리스트에 마지막 인덱스를 구하기위해 매번 list.size() -1 하는것 보다 Stack을 사용하면 코드가 깔끔할 것 같아서  Stack을 사용해 문제를 풀어보았다 

## 코드 2

```java
import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0); // 제일 처음에 빈스텍에서 stack.peek() 을 하면 에러가 나서 0을 넣어줬다 
        
        for(int num : moves){
            for(int i = 0; i < board.length; i++){
                if(board[i][num - 1] != 0 ){
                    if(board[i][num - 1] == stack.peek()){
                        stack.pop();
                        answer += 2;
                    }else{
                        stack.push(board[i][num - 1] );
                    }
                    board[i][num - 1] = 0;
                    break;
                }
            }        
        }
                    
        return answer;
    }
}
```

## 코드 제출 결과2
>테스트 1 〉	통과 (0.18ms, 70.3MB)   
테스트 2 〉	통과 (0.13ms, 58MB)   
테스트 3 〉	통과 (0.19ms, 71.1MB)   
테스트 4 〉	통과 (1.01ms, 72.6MB)   
테스트 5 〉	통과 (0.18ms, 60.6MB)   
테스트 6 〉	통과 (0.12ms, 73MB)   
테스트 7 〉	통과 (0.21ms, 60.6MB)   
테스트 8 〉	통과 (0.52ms, 73.7MB)   
테스트 9 〉	통과 (0.55ms, 71MB)   
테스트 10 〉	통과 (0.47ms, 71.9MB)   
테스트 11 〉	통과 (0.82ms, 70.3MB)    
   
   
Stack 대신 리스트를 사용하고 싶으면 다음과 같이 변경해주면 된다 

stack.push → list.add   
stack.peek → list.get(list.size() - 1)   
stack.pop → list.remove(list.size() -1)   

문제를 풀고 다른사람의 풀이 보기를 눌렀을 때 상위에 있는 풀이와 비슷해서 뿌듯했다

## 이문제에서 생각해볼 점

stack 자료구조 이해