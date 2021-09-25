# level1_비밀지도

## notion에서 보기
https://diligent-persimmon-0f2.notion.site/level1_-a667e614c8824aad9cfae1f17e084e84

## 문제 링크

[https://programmers.co.kr/learn/courses/30/lessons/17681](https://programmers.co.kr/learn/courses/30/lessons/17681)

## 풀이

1. 정수를 이진수로 변환하고 OR연산을 한다 
    1. Integer.toBinaryString은 이진수가 0 으로 시작할 경우 생략하기 때문에 자릿수를 고정시켜야한다
2. OR연산 결과를 1은 #으로, 0은 공백으로 변환한다 
3. 결과를 반환한다

## 코드 1

```java
import java.util.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = {};
        String[] arr3 = new String[n];
        for(int i = 0; i < n; i++){
           arr3[i]= Integer.toBinaryString(arr1[i] | arr2[i])
                    .replace("1","#")
                    .replace("0"," ");
        }
        answer = arr3;
        return answer;
    }
}
```

> 테스트 1   
입력값 〉5, [9, 20, 28, 18, 11], [30, 1, 21, 17, 28]   
기댓값 〉["#####", "# # #", "### #", "#  ##", "#####"]   
실행 결과 〉<span style="color: #0078ff">테스트를 통과하였습니다.</span>   

>테스트 2   
입력값 〉6, [46, 33, 33, 22, 31, 50], [27, 56, 19, 14, 14, 10]   
기댓값 〉["######", "###  #", "##  ##", " #### ", " #####", "### # "]   
실행 결과 〉<span style="color: #ff7369">실행한 결괏값 ["######","###  #","##  ##","#### ","#####","### # "]이(가) 기댓값 ["######","###  #","##  ##"," #### "," #####","### # "]와(과) 다릅니다.
</span>

숫자를 2진수로 변환 후 OR연산을 한뒤, 0은 공백으로 1은 #으로 변횐 해주면 될 것 같아서 위와 같은 코드를 작성 하였지만, 결과는 테스트 코드도 통과하지 못하였다.

아니 내가 직접 숫자를 건드린건 없고, 다 함수를 이용해서 코드를 작성했기 때문에 틀린다면 두개 다 틀려지 왜 첫번째 코드는 통과하고 두번째코드는 통과하지 못했는지, 어디가 잘못된건지 찾지 못했는데 ( 처음에는 결과값이랑 기대값도 같은데 틀리다고 나온 오류인줄 았았다 ㅋㅋ) 결과값과 기댓값을

<span style="color: #ff7369">["######","###  #","##  ##","#### ","#####","### # "]</span>   

<span style="color: #ff7369">["######","###  #","##  ##"," #### "," #####","### # "]</span>   

이렇게 한 줄로 비교 해 보니  3번째 칸에  첫 공백이 없는 것이 보였다.

어디서 이 공백이 생략이 된것일까 이리저리 찾아보니!! 바로 범인은!!  Integer.toBinaryString 함수였다. Integer.toBinaryString함수는 첫글자가 0이면 생략해 준다고 한다....  

생략된 0을 어떻게 채워출수 있을까 고민하다가 찾은 방법이

String.format 메서드를 써서 지정된 문자열길이(n만큼) 공백을 0으로  채워주는 방법이다. 하지만 String 형식에는 0 flag를 사용할 수 없어서 번거롭지만 int 형으로 형변환을 해준다음에 길이 n 만큼 0을 붙였다 .

##코드 2

```java
import java.util.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = {};
        String[] arr3 = new String[n];
        for(int i = 0; i < n; i++){
           arr3[i]=String.format("%0" + (n) + "d", Integer.parseInt(Integer.toBinaryString(arr1[i] | arr2[i])))
                    .replace("1","#")
                    .replace("0"," ");
        }
        answer = arr3;
        return answer;
    }
}
```

## 제출결과 2
>테스트 1 〉	통과 (6.39ms, 60MB)   
테스트 2 〉	<span style="color: #ff7369">실패 (런타임 에러) </span>  
테스트 3 〉	통과 (6.23ms, 67.6MB)   
테스트 4 〉	통과 (6.62ms, 73.3MB)   
테스트 5 〉	통과 (7.89ms, 60.5MB)   
테스트 6 〉	<span style="color: #ff7369">실패 (런타임 에러) </span>  
테스트 7 〉	통과 (6.81ms, 72.7MB)   
테스트 8 〉	통과 (6.65ms, 57.9MB)   

지도의 한변의 길이 n은 최대 16까지 가능하기 떄문에 Integer의 범위를 넘어갈 수 있다는 것을 생각하지 못해서 런타임 에러가 발생하였다. (Integer의 범위는 약 21억(10자리수))   


Integer.parseInt 을 Long.parseLong 으로 변경해 주었다 
## 코드 3

```java
import java.util.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
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
```

##제출결과 3
>테스트 1 〉	통과 (6.75ms, 76.6MB)   
테스트 2 〉	통과 (7.59ms, 70.2MB)   
테스트 3 〉	통과 (6.18ms, 58.5MB)   
테스트 4 〉	통과 (7.39ms, 72.6MB)   
테스트 5 〉	통과 (6.34ms, 72.4MB)   
테스트 6 〉	통과 (6.48ms, 58.9MB)   
테스트 7 〉	통과 (6.69ms, 71.1MB)   
테스트 8 〉	통과 (6.06ms, 60.3MB)   

Integer.parseInt 을 Long.parseLong 으로 변경하니 통과가 되었다 

여기까지 풀고 0을 붙이기 위해 정수형으로 변환하는 과정이 불필요해 보여서 다른 방법이 없을까 고민하던중 어차피 0은 공백으로 변환되니까 0이아니라 공백을 붙여주면 되지않을까!!! 생각해서 방법을 바꿔보았다 

## 코드 4

```java
import java.util.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = {};
        String[] arr3 = new String[n];
        for(int i = 0; i < n; i++){
           arr3[i] = String.format("%" + (n) + "s", Integer.toBinaryString(arr1[i] | arr2[i]))
                    .replace("1","#")
                    .replace("0"," ");
        }
        answer = arr3;
        return answer;
    }
}
```

##제출결과 4
>테스트 1 〉	통과 (9.72ms, 74MB)   
테스트 2 〉	통과 (11.89ms, 73.3MB)   
테스트 3 〉	통과 (9.56ms, 70.5MB)   
테스트 4 〉	통과 (12.44ms, 73MB)   
테스트 5 〉	통과 (10.94ms, 60.1MB)   
테스트 6 〉	통과 (12.30ms, 61.6MB)   
테스트 7 〉	통과 (7.44ms, 59MB)   
테스트 8 〉	통과 (9.32ms, 72.2MB)   

통과는 했는데... 정수형으로 형변환 과정이 생략이 됬음에도 오히려 시간이 늘었다..?

왜 그런지는 그냥 숫자보다 문자열을 format하는것이 시간이 더 많이 걸리나보다... 짐작 할 뿐 정확한 이유는 모르겠다.. 

이번에는 포멧 형식에 변수 n을 사용하는 대신 정수를  두고 문제를 풀어봤다

## 코드 5

```java
import java.util.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = {};
        String[] arr3 = new String[n];
        for(int i = 0; i < n; i++){
           arr3[i]=String.format("%16s", Integer.toBinaryString(arr1[i] | arr2[i]))
                    .substring(16 - n)
                    .replace("1","#")
                    .replace("0"," ");
        }
        answer = arr3;
        return answer;
    }
}
```

##제출결과 5
>테스트 1 〉	통과 (1.26ms, 70.7MB)   
테스트 2 〉	통과 (1.37ms, 58.8MB)   
테스트 3 〉	통과 (0.98ms, 59.7MB)   
테스트 4 〉	통과 (1.15ms, 69.9MB)   
테스트 5 〉	통과 (0.91ms, 72.4MB)   
테스트 6 〉	통과 (1.05ms, 71.5MB)   
테스트 7 〉	통과 (0.94ms, 72.6MB)   
테스트 8 〉	통과 (0.91ms, 75.3MB)   

예상치 못하게 속도가 엄청 개선되었다 !! 

최대길이가 16이기 때문에 16으로 두고 substring으로 문자열을 원래 길이만큼 잘라주는 방식인데

substring이 추가가 되었음에도 불구하고 시간이 엄청 단축되었다...?

위에서 정수포멧을 다루는 것이 더 빨랐으니 혹시 시간이 더 줄어들을까해서 위에 했었던 0 flage 를 사용하되 길이를  n대신 16으로 고정해주고 위 방식을 사용해보았다.

## 코드5

```java
import java.util.*;

class Solution {
   public String[] solution(int n, int[] arr1, int[] arr2) {
      String[] answer = {};
      String[] arr3 = new String[n];
      for(int i = 0; i < n; i++){
         arr3[i]=String.format("%016d", Long.parseLong(Integer.toBinaryString(arr1[i] | arr2[i])))
                 .substring(16 - n)
                 .replace("1","#")
                 .replace("0"," ");
      answer = arr3;
      return answer;
   }
}

```

아까는 정수포멧을 다루는 것이 문자열 포멧을 다루는 것보다 빨랐는데 이번에는 오희려 정수를 다루는 방법이 미세하게 더 늦다... 왜그럴까... 모르겠다... 

이번 문제는 풀이는 간단한데 생각보다 삽질을 했고 의문도 많이 남았다...

## 이문제에서 생각해 볼점

- 진수의 변환

- Integer.toBinarySting() : 이진수가 0 으로 시작할 경우 생략한다 .

- String.format() : 0 flag 는 문자열에는 사용할 수 없다.