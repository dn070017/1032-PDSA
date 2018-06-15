import java.util.Iterator;
import java.util.ArrayList;

public class HW8_1{
    
    private static ArrayList<Integer> inOrder = new ArrayList<Integer>();
    private static ArrayList<Integer> postOrder = new ArrayList<Integer>();
    
    private static Node root = null;
    private static Node parent = null;
    
    private static void Construct(int start, int end, int postEnd, Node parent, int status){
        boolean left = false;
        boolean right = false;
        Integer leftChild = null;
        Integer rightChild = null;
        Integer key = postOrder.get(postEnd);
        int pivot = inOrder.indexOf(key);
        Node node = new Node(key, key);
        
        if(parent == null)
            root = node;
        else if(status == 0)
            parent.setLeft(node);
        else if(status == 1)
            parent.setRight(node);
        
        for(int i = postEnd - 1; i >= 0; i--){
            key = postOrder.get(i);
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
            Construct(start, pivot - 1, postOrder.indexOf(leftChild), node, 0);
        if(right == true)
            Construct(pivot + 1, end, postOrder.indexOf(rightChild), node, 1);
    }
    
    public static Node BTrebuild(String inString, String postString) {
        String data = inString;
        String[] tmp = data.split(" ");
        for(int i = 0; i < tmp.length; i++)
            inOrder.add(i, Integer.parseInt(tmp[i]));
        data = postString;
        tmp = data.split(" ");
        for(int i = 0; i < tmp.length; i++)
            postOrder.add(i, Integer.parseInt(tmp[i]));
        
        Construct(0, inOrder.size() - 1, postOrder.size() - 1, null, 2);
        return root;
    }
}

