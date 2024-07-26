import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //7453
        //(1,2)나눠 가능한 모든 합을 구한뒤, 두포인터로 접근하여 모든 원소를 visit ( boolean, cnt) 하면 return;
        // 배열이 4000개니까.. 4000^2 = 1600만..??? 너무큰데용...
        int n =Integer.parseInt(br.readLine());
        long[] A = new long[n];
        long[] B = new long[n];
        long[] C = new long[n];
        long[] D = new long[n];

        for(int i=0;i<n;i++){
            String[] split = br.readLine().split(" ");
            A[i] = Long.parseLong(split[0]);
            B[i] = Long.parseLong(split[1]);
            C[i] = Long.parseLong(split[2]);
            D[i] = Long.parseLong(split[3]);
        }

        //case1
        long answer =0;
        answer+=case1(A,B,C,D);
        System.out.println(answer);
    }

    public static long case1(long[] A,long[] B,long[] C,long[] D){
        long[] arr1 = subSumArr(A,B);
        long[] arr2 = subSumArr(C,D);
        return getAns(arr1,arr2);
    }

    public static long[] subSumArr(long[] a, long[] b){
        int size = a.length;
        long[] nArr = new long[size*size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                nArr[i*size+j] = Long.sum(a[i], b[j]);
            }
        }
        return nArr;
    }

    public static long getAns(long[] a, long[] b){
        Arrays.sort(a);
        Arrays.sort(b);
//        System.out.println("a = " + Arrays.toString(a));
//        System.out.println("b = " + Arrays.toString(b));
        int size = b.length;
        int aIndex = 0;
        int bIndex = size -1;
        long subAns = 0;
        // A에서 0이 나올떄까지, 찾고 그뒤론 B에서 0이나올떄까찌 찾는다....
        while(true){
            if(aIndex>=size || bIndex<0){
//                System.out.println(aIndex +" "+ bIndex);
                break;
            }
//            if(a[aIndex]>0 && b[bIndex]>0){
//                bIndex--;
//            }else if(a[aIndex]<0 && b[bIndex]<0){
//                aIndex++;
//            }else
            if(Long.sum(a[aIndex],b[bIndex])<0){
                aIndex++;
            }else if(Long.sum(a[aIndex],b[bIndex])>0){
                bIndex--;
            }else{ //(a[aIndex] + b[bIndex]==0)
                long sameACnt = 1;
                long sameBCnt = 1;
                // 인덱스 어케할래?
                // 각각의 다음 인덱스가 존재하는지 확인하고, 같은만큼 점프
                while(true){
                    if(aIndex == size-1){
                        break;
                    }
                    if(a[aIndex] == a[aIndex+1]){
                        aIndex++;
                        sameACnt++;
                    }else{
                        break;
                    }
                }
                while(true){
                    if(bIndex==0){
                        break;
                    }
                    if(b[bIndex] == b[bIndex-1]){
                        bIndex--;
                        sameBCnt++;
                    }else{
                        break;
                    }
                }
                aIndex++;
                bIndex--;
                subAns += Math.multiplyExact(sameACnt,sameBCnt);
            }
        }
        return subAns;
    }
}