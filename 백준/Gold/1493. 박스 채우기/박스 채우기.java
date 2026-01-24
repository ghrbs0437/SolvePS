import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    static boolean flag = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] strs = br.readLine().split(" ");
        int length  = Integer.parseInt(strs[0]);
        int width = Integer.parseInt(strs[1]);
        int height = Integer.parseInt(strs[2]);
        int boxCnt = Integer.parseInt(br.readLine());
        int[] quantity = new int[boxCnt];
        for(int i=0;i<boxCnt;i++){
            strs = br.readLine().split(" ");
            quantity[Integer.parseInt(strs[0])] = Integer.parseInt(strs[1]);
        }

        long num = getAns(length,width,height,quantity);

        if(flag){
            System.out.println(-1);
        }else{
            System.out.println(num);
        }

    }

    public static long getAns(long length, long width, long height, int[] quantity){
        // 가로 세로 높이가 전부 같아질 때, quantity를 소모할 수 있다.. quantity는 역순으로 사용하는게 좋아.
//        System.out.println("length = " + length + ", width = " + width + ", height = " + height + ", quantity = " + Arrays.toString(quantity));
        if(length == 0 || width == 0 || height == 0){
            return 0;
        }

        if(flag){
            return 0;
        }

        long size = 1;
        int index = 0;
        while(true){
            long next = size * 2;
            if(length / next >0 && width / next >0 && height / next >0){
                index++;
                size =  next;
            }else{
                break;
            }
        }



//        System.out.println(Arrays.toString(quantity));

        long answer = 0;
        answer += useCube(index,quantity);

        answer += getAns(length-size,size,size,quantity);
        answer += getAns(size,width-size,size,quantity);
        answer += getAns(size,size,height-size,quantity);

        answer += getAns(length-size,width-size,size,quantity);
        answer += getAns(length-size,size,height-size,quantity);
        answer += getAns(size,width-size,height-size,quantity);

        answer += getAns(length-size,width-size,height-size,quantity);

        return answer;
    }

    public static long useCube(int num, int[]quantity){
        long cnt = 0;
        long remain = 1;
        for(int i = num ; i>=0; i--){
            if(i >= quantity.length){
                remain*=8;
                continue;
            }
            if(quantity[i] >= remain){
                cnt += remain;
                quantity[i] -= remain;
                remain = 0;
                break;
            }else{
                cnt += quantity[i];
                remain = (remain - quantity[i])*8;
                quantity[i] = 0;
            }
        }
        if(remain > 0 ){
            flag = true;
        }

        return cnt;
    }

}