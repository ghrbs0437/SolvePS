# [수영장](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpFQaAQMDFAUq)

> 링크: <https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpFQaAQMDFAUq>  
> 레벨: `?`  
> 태그: `완전탐색`
> 날짜: 2024-08-22

### 제약사항

- 테스트케이스 : 50
- 배열크기 : 12
- MAX 값 : 3,000
- 메모리 256mb
- 시간 : 3초

### 문제요약

각 이용권의 요금과 각 달의 이용 계획이 입력으로 주어질 때,

가장 적은 비용으로 수영장을 이용할 수 있는 방법을 찾고 그 비용을 출력

-> 주어진 1일, 1달, 3달, 1년 이용권의 가격과 각 달의 이용 계획을 바탕으로 최적의 이용권 조합을 구하라.

### 생각

어떤 달에 어떤 이용권을 사용하는것이 최적인가??

일일권가격\*이용횟수 < 1달이용권 ?
1달이용권 x3 < 3달이용권?
3달이용권 x 4 < 1년이용권?

1. 비교해가며 그리디한 선택을 할 수도 있겠다.
2. n달까지의 선택의 최선을 기록해나가며 DP 접근도 가능할 것으로 보인다.
3. 경우의 수를 모두 구하는 것도 가능할 것 같다.

### 풀이

배열의 크기가 크지 않고, 탐색 경우의 수가 많지 않으니 완전탐색을 통해 진행한다.
4^12 = 16,777,216
실제로는 탐색을 진행하면서 1년동안의 선택을 모두 마친 경우를 제외하므로 훨씬 더 빠르게 해결가능하다.

### 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

	static int MIN = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for(int tc =1; tc<=testCase;tc++) {
			MIN = Integer.MAX_VALUE;
			String[] strs = br.readLine().split(" ");

			int[] ticketPrices = new int[strs.length];
			for(int i=0;i<strs.length;i++) {
				ticketPrices[i] = Integer.parseInt(strs[i]);
			}

			strs = br.readLine().split(" ");

			int[] countMonth = new int[strs.length];

			for(int i=0;i<strs.length;i++) {
				countMonth[i] = Integer.parseInt(strs[i]);
			}

			getAns(ticketPrices,countMonth,0,0);
			sb.append("#"+tc+" "+MIN+"\n");
		}

		System.out.println(sb);
	}

	//
	public static void getAns(int[] ticketPrices, int[] countMonth, int currentMonth, int sum) {
		if(currentMonth>=12) {
			if(MIN>sum) {
				MIN = sum;
			}
			return;
		}

		getAns(ticketPrices,countMonth,currentMonth+1,sum + ticketPrices[0] * countMonth[currentMonth]);
		getAns(ticketPrices,countMonth,currentMonth+1,sum + ticketPrices[1]);
		getAns(ticketPrices,countMonth,currentMonth+3,sum + ticketPrices[2]);
		getAns(ticketPrices,countMonth,currentMonth+12,sum + ticketPrices[3]);

	}


}
```
