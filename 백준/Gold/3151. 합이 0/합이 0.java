import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if(n<3){
            System.out.println(0);
            return;
        }

        String[] strs = br.readLine().split(" ");
        int[] arr = new int[n];

        for(int i=0;i<n;i++){
            arr[i] =Integer.parseInt(strs[i]);
        }



        long answer = 0;
        Arrays.sort(arr);
//        System.out.println(Arrays.toString(arr));
        for(int i=1;i<n-1;i++){

            int left = 0;
            int right = n - 1;

            while(true) {

                if(left==i||right==i){
                    break;
                }

                int sum = arr[left] + arr[right] + arr[i];
                if (sum < 0) {
                    left++;
                }
                if (sum > 0) {
                    right--;
                }
                if (sum == 0) {
                    int leftCnt = 0;
                    for(int j=left;j<i;j++){
                        if(arr[left] == arr[j]){
                            leftCnt++;
                        }else{
                            break;
                        }
                    }
                    int rightCnt = 0;
                    for(int j=right;j>i;j--){
                        if(arr[right] == arr[j]){
                            rightCnt++;
                        }else{
                            break;
                        }
                    }
                    answer += leftCnt * rightCnt;
                    left+=leftCnt;
                    right-=rightCnt;
                }
                if (left + 1 >= right) {
                    break;
                }
            }

        }
        System.out.println(answer);

    }
}