import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        tc:for(int i=0; i<tc; i++){
            int cnt = Integer.parseInt(br.readLine());
            Trie root = new Trie();
            boolean flag = false;
            for(int j=0;j<cnt;j++){
                String str = br.readLine();
                if(flag){
                    continue;
                }
                Trie current = root;
                for(int k=0; k<str.length();k++){
                    int num = str.charAt(k)-'0';
                    if(current.next[num] == null){
                        current.next[num] = new Trie();
                    }
                    current = current.next[num];
                    if(current.isEnd){
                        flag = true;
                        break;
                    }
                    if(k == str.length()-1){
                        current.isEnd = true;
                        for(int z =0 ;z<10;z++){
                            if(current.next[z]!=null){
                                flag = true;
                                break;
                            }
                        }
                    }
                }
            }
            if(flag){
                sb.append("NO").append("\n");
            }else{
                sb.append("YES").append("\n");
            }
        }
        System.out.println(sb);

    }


    public static class Trie{
        boolean isEnd = false;
        Trie[] next = new Trie[10];

    }
}