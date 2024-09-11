
# [더 맵게](https://school.programmers.co.kr/learn/courses/30/lessons/42626)

> 링크: <https://school.programmers.co.kr/learn/courses/30/lessons/42626>   
> 레벨: `2`   
> 태그: `힙`
> 날짜: 2024-05-17

 ## 풀이
- 배열의 크기가 1,000,000수준으로, 최소O(nlogn)이하의 알고리즘이 요구됨
- 정렬된 리스트에서 삽입 / 제거 -> n을 n번반복 -> O(n^2) 
- Tree구조를 활용하여, 삽입 / 제거 -> logn , n번 반복 -> O(nlogn) ✔

> 1. scoville 수치를 minHeap구조로 변환
> 2. heap의 요소 두개를 꺼내고, `${1} + ${2}*2`를 heap에 다시 넣음
> 3. 2를 반복하며, 반복횟수인 `count`를 기록
> 4. min이 목표치 이상인 경우 return `count`
> 5. min이 목표치 미달이나, heap size가 1이하인경우 -> return -1;

- 시간복잡도: O(nlogn) 
- 최종점수: 100/100

### 코드
```java

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i:scoville){
            pq.add(i);
        }
        
        while(true){    
            if(pq.peek()>=K){
                return answer;
            }
            if(pq.size()<2){
                break;
            }
            answer++;
            int min = pq.poll();
            int min2 = pq.poll();
            pq.add(min + min2*2);
        }
        
        return -1;
    }
}
```