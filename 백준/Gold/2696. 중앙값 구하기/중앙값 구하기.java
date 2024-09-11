import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int tc=0;tc<testCase;tc++){
            int numSize = Integer.parseInt(br.readLine());

            int[] arr = new int[numSize];

            int[] answer = new int[numSize/2+1];
            int index = 1;

            TreeSet<IndexNode> tset = new TreeSet<>((a,b)->{
                if(a.value==b.value){
                    return a.index - b.index;
                }else{
                    return a.value - b.value;
                }
            });
            while(index<=numSize){
                String[] strs = br.readLine().split(" ");
                for(String str : strs){
                    arr[index-1] = Integer.parseInt(str);
                    tset.add(new IndexNode(index,arr[index-1]));

                    if(index%2==1){ // 홀수인경우
                        int tIndex = 0;
                        for(IndexNode t : tset){
                            if(tIndex == tset.size()/2){
                                answer[index/2] = t.value;
                                break;
                            }
                            tIndex++;
                        }
                    }
                    index++;
                }
            }
//            System.out.println(Arrays.toString(answer));
            sb.append(answer.length+"\n");
            for(int i=0;i<answer.length;i++){
                sb.append(answer[i]);
                if(i!=0
                        &&i!=answer.length-1
                        && i%10==9){
                    sb.append("\n");
                }else{
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static class IndexNode{
        int index;
        int value;
        IndexNode(int index,int value){
            this.index = index;
            this.value = value;
        }

        @Override
        public boolean equals(Object o){
            return this.index == ((IndexNode)o).index;
        }
        @Override
        public int hashCode(){
            return index * value;
        }
        @Override
        public String toString(){
            return String.valueOf(value);
        }
    }
}