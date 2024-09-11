
# [기능개발](https://school.programmers.co.kr/learn/courses/30/lessons/42586)

> 링크: <https://school.programmers.co.kr/learn/courses/30/lessons/42586>   
> 레벨: `2`   
> 태그: `스택`, `큐`    
> 날짜: 2024-05-10

 ## 풀이
- 배열의 크기가 100이며, 작업진도,속도가 100이하의 자연수이기 떄문에, 효율성은 크게 고려하지 않아도 됨.  

> 1. 현재 index의 `progresses[index] + speed[index] * ${단위일}` 이 100이상이 될때까지, 단위일을 증가시킴
> 2. progresses[index]가 100이상인 경우, progresses[index]가 100미만일때까지, index를 증가시키며 `Count`를 증가시킴
> 3. progresses[index]가 100미만인 경우, 해당 Count를 배포건수로 저장하며 1부터 다시 진행
> 4. index가 배열의 끝에 도달하면, 프로그램 종료

- 시간복잡도: O(n^2) 
- 최종점수: 100/100

### 코드
```java
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int currentIndex = 0;
        ArrayList<Integer> alist = new ArrayList<>();
        while(true){
            // 릴리즈일 구하기..
            if(currentIndex==progresses.length){
                System.out.println(alist);
                int[] arr = new int[alist.size()];
                for(int i=0;i<alist.size();i++){
                    arr[i] = alist.get(i);
                }
                return arr;
            }
            int restJob = 100 - progresses[currentIndex];
            int day = 0;
            day += restJob/speeds[currentIndex];
            if(restJob%speeds[currentIndex]!=0){
                day+=1;
            }    
            // 릴리즈단위 구하기..
            int count =0;
            for(;currentIndex<progresses.length;currentIndex++){
                if(progresses[currentIndex] + speeds[currentIndex] * day <100){
                    break;
                }else{
                    count++;
                }
            }
            alist.add(count);
        }
        
    }
}
```