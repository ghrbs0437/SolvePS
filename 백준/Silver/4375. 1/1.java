import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        
        // EOF까지 입력 받기
        while ((line = br.readLine()) != null) {
            int n = Integer.parseInt(line);

            int num = 1;
            int length = 1;

            // num이 n으로 나누어떨어질 때까지 1을 뒤에 붙여감
            while (num % n != 0) {
                num = (num * 10 + 1) % n; 
                length++;
            }

            System.out.println(length);
        }
    }
}