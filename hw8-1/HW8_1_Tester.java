import java.io.File;
import java.util.Scanner;
import java.util.Iterator;
import java.util.ArrayList;

public class HW8_1_Tester {
    public static void InOrder(Node node){
        if(node.getLeft() != null)
            InOrder(node.getLeft());
        System.out.printf("%d ", node.getKey());
        if(node.getRight() != null)
            InOrder(node.getRight());
    }
    public static void PreOrder(Node node){
        System.out.printf("%d ", node.getKey());
        if(node.getLeft() != null)
            PreOrder(node.getLeft());
        if(node.getRight() != null)
            PreOrder(node.getRight());
    }
    public static void PostOrder(Node node){
        if(node.getLeft() != null)
            PostOrder(node.getLeft());
        if(node.getRight() != null)
            PostOrder(node.getRight());
        System.out.printf("%d ", node.getKey());
    }
    
    public static void main(String[] argv) throws Exception{
        String inString, postString;
        File file = new File(argv[0]);
        Scanner scanner = new Scanner(file);
        inString = scanner.nextLine();
        postString = scanner.nextLine();
        Node node = HW8_1.BTrebuild(inString, postString);
        System.out.printf("In-Order: ");
        InOrder(node);
        System.out.printf("\n");
        System.out.printf("Pre-Order: ");
        PreOrder(node);
        System.out.printf("\n");
        System.out.printf("Post-Order: ");
        PostOrder(node);
        System.out.printf("\n");
    }
}

