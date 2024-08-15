import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        ArrayDeque<Character> stack = new ArrayDeque<>();
        String str = br.readLine();
        int subStringCnt = 1;
        HashSet<String> hset = new HashSet<>();
        while(true) {
            if(subStringCnt>str.length()){
                break;
            }
            for (int i = 0; i <= str.length() - subStringCnt; i++) {
                String subStr = str.substring(i, i+subStringCnt);
                hset.add(subStr);
            }
            subStringCnt++;
        }
        System.out.println(hset.size());
    }
}