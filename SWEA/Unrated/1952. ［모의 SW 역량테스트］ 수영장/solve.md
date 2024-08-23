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

### 풀이

배열의 크기가 크지 않고, 탐색 경우의 수가 많지 않으니 완전탐색을 통해 진행한다.
4^12 = 16,777,216

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
