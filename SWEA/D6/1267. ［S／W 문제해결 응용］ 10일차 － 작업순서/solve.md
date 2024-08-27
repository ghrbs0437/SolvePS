# [작업 순서](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV18TrIqIwUCFAZN)

> 링크: <https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV18TrIqIwUCFAZN>  
> 레벨: `D6`  
> 태그: `그래프, 위상정렬`  
> 날짜: 2024-08-28

### 제약사항

- 테스트케이스 : 10
- 배열크기 : 1000 ~ 1000^2
- MAX 값 : 1000
- 메모리 256mb
- 시간 : 20초

### 문제요약

V개의 작업과 이들 간의 `선행 관계`가 주어질 때, 일을 끝낼 수 있는 `작업 순서`를 찾는 프로그램을 작성하라.

가능한 작업 순서가 `여러 가지`일 경우, 여러분은 이들 중 하나만 제시하면 된다.

### 풀이

작업의 선행관계는 그 작업의 `진입차수`로 확인할 수 있다.

1. 진입차수가 0인 정점들을 선택
2. 해당 정점을 시작점으로 갖는 간선 제거
3. 제거한 간선의 목적지의 진입차수가 0이라면, 그 정점을 선택한다.
4. 더이상 선택할 수 있는 정점이 없을때 까지 반복한다.

위상정렬 알고리즘을 바로 적용하여 해결.

참고로 `선행 관계` 와 `작업 순서`, `복수 정답` 은 위상정렬 알고리즘의 대표적인 키워드이다.

### 코드

```java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class Solution {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            String[] strs = br.readLine().split(" ");

            int maxNumber = Integer.parseInt(strs[0]);
            int lineCnt = Integer.parseInt(strs[1]);

            strs = br.readLine().split(" ");
            HashMap<Integer, ArrayList<Integer>> startMap = new HashMap<>();
            int[] indgree = new int[maxNumber + 1];
            boolean[] visits = new boolean[maxNumber+1];

            for (int i = 0; i < lineCnt; i++) {
                int start = Integer.parseInt(strs[i * 2]);
                int end = Integer.parseInt(strs[i * 2 + 1]);

                if (startMap.get(start) == null) {
                    startMap.put(start, new ArrayList<>());
                }

                if (startMap.get(end) == null) {
                    startMap.put(end, new ArrayList<>());
                }
                indgree[end]++;
                startMap.get(start).add(end);
            }

            Queue<Integer> queue = new ArrayDeque<>();

			// 간선에 등장한, 진입차수가 0인 정점
            for (int i = 1; i <= maxNumber; i++) {
                if (indgree[i] == 0&&startMap.get(i)!=null) {
                    queue.add(i);
                    visits[i] = true;
                }
            }

            sb.append("#" + tc);

            while (!queue.isEmpty()) {
                int current = queue.poll();
                sb.append(" " + current);
                for (int next : startMap.get(current)) {
                    indgree[next]--;
                    if (indgree[next] == 0) {
                        queue.add(next);
                        visits[next] = true;
                    }
                }
            }
			// 간선에 등장하지 않은 정점
            for(int i=1;i<visits.length;i++) {
                if(!visits[i]) {
                    sb.append(" "+i);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}


```
