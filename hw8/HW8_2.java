import java.io.File;
import java.util.Scanner;
import java.util.Iterator;
import java.util.ArrayList;

public class HW8_2{
    
    private static ArrayList<Integer> inOrder = new ArrayList<Integer>();
    private static ArrayList<Integer> preOrder = new ArrayList<Integer>();
    
    private static Node root = null;
    private static Node parent = null;
    
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
    
    private static void Construct(int start, int end, int preStart, Node parent, int status){
        boolean left = false;
        boolean right = false;
        Integer leftChild = null;
        Integer rightChild = null;
        Integer key = preOrder.get(preStart);
        int pivot = inOrder.indexOf(key);
        Node node = new Node(key, key);
        
        if(parent == null)
            root = node;
        else if(status == 0)
            parent.setLeft(node);
        else if(status == 1)
            parent.setRight(node);
        
        for(int i = preStart; i < preOrder.size() ; i++){
            key = preOrder.get(i);
            if(inOrder.indexOf(key) > pivot && inOrder.indexOf(key) <= end && right == false){
                right = true;
                rightChild = key;
            }
            else if(inOrder.indexOf(key) < pivot && inOrder.indexOf(key) >= start && left == false){
                left  = true;
                leftChild = key;
            }
            if(right == true && left == true)
                break;
        }
        if(left == true)
            Construct(start, pivot - 1, preOrder.indexOf(leftChild), node, 0);
        if(right == true)
            Construct(pivot + 1, end, preOrder.indexOf(rightChild), node, 1);
    }
    
    public static Node BTrebuild(String inString, String postString) {
        String data = inString;
        String[] tmp = data.split(" ");
        for(int i = 0; i < tmp.length; i++)
            inOrder.add(i, Integer.parseInt(tmp[i]));
        data = postString;
        tmp = data.split(" ");
        for(int i = 0; i < tmp.length; i++)
            preOrder.add(i, Integer.parseInt(tmp[i]));
        
        Construct(0, inOrder.size() - 1, 0, null ,2);
        return root;
    }
    
    public static void main(String[] argv) throws Exception{
        String inString, postString;
        File file = new File(argv[0]);
        Scanner scanner = new Scanner(file);
        inString = scanner.nextLine();
        postString = scanner.nextLine();
        Node node = BTrebuild(inString, postString);
        PostOrder(node);
        System.out.printf("\n");
    }
}

