import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        // 전위순회  > 루트 왼쪽 오른쪽
        // 후위순회  > 왼쪽 오른쪽 루트
        Tree root = new Tree(sc.nextInt());
        while(sc.hasNext()){

            int value = sc.nextInt();
            Tree tree = new Tree(value);
            root.add(tree);
        }
        root.printBack();
        System.out.println(Tree.resultContainer);
    }

    public static class Tree{
        int value;
        Tree left;
        Tree right;

        static StringBuilder resultContainer = new StringBuilder();
        Tree(int value){
            this.value = value;
        }

        void add(Tree tree){
            if(tree.value < this.value){
                if(this.left==null){
                    this.left = tree;
                }else {
                    this.left.add(tree);
                }
                return;
            }

            if(tree.value > this.value){
                if(this.right==null){
                    this.right = tree;
                }else{
                    this.right.add(tree);
                }
                return;
            }
        }
        void printBack(){
            if(left!=null){
                left.printBack();
            }
            if(right!=null){
                right.printBack();
            }
            resultContainer.append(this.value+"\n");
        }
    }
}