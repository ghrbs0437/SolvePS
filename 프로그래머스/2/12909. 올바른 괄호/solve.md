
# [올바른 괄호](https://school.programmers.co.kr/learn/courses/30/lessons/12909)

> 링크: <https://school.programmers.co.kr/learn/courses/30/lessons/12909>   
> 레벨: `2`   
> 태그: `스택`, `큐`    
> 날짜: 2024-05-10

## 풀이

- 문자열 s의 길이 : 100,000 이하의 자연수이므로, n^2미만의 알고리즘 요구

괄호의 열고 닫음은 `Stack` 자료구조와 유사함

> 1. 문자의 가장 앞부터 뒤로 순차적 탐색
> 2. 문자가 `'('`,`'{'`인경우 Stack에 Push
> 3. 문자가 `')'`,`'}'`인경우, Stack이 비지 않았다면 Stack에서 Pop
> 4. 문자열을 끝까지 탐색했을 때, Stack이 비어있다면 true 이외의 모든경우는 false

- 시간복잡도: O(n) 
- 최종점수: 100/100

### 코드
```java
class Solution {
    public boolean solution(String s) {
        int length = s.length();
        Stack<Character> st = new Stack<>();
        for(int i=0;i<length;i++){
            char cur = s.charAt(i);
            if(cur=='{' ||cur=='('){
                st.add(cur);
            }else if(cur==')'){
                if(st.empty() || st.peek()!='('){
                    return false;
                }else{
                    st.pop();
                }
            }else if(cur=='}'){
                 if(st.empty() ||st.peek()!='{'){
                    return false;
                }else{
                    st.pop();
                }
            }
        }
        if(st.empty()){
            return true;
        }
        return false;
    }
}
```