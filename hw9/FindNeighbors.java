import java.io.File;
import java.util.Scanner;

public class FindNeighbors {
    
    private int count = 0;
    private Node root = null;
    private PriorityQueue queue;
    
    ///////////////////
    //    2D Tree    //
    ///////////////////
    private class Node{
        private Point2D point;
        private Node left;
        private Node right;
        private int level;
        
        public Node(Point2D point, int level){
            this.point = point;
            this.level = level;
        }
    }
    
    private void insert(Point2D point){
        int level = 1;
        Node node = root;
        if(node == null){
            Node tmp = new Node(point, level);
            root = tmp;
            return;
        }
        while(node != null){
            int oldLevel = level++;
            Node tmp = new Node(point, level);
            if(oldLevel % 2 == 1){
                if(point.x() > node.point.x()){
                    if(node.right == null){
                        node.right = tmp;
                        return;
                    }
                    else
                        node = node.right;
                }
                else{
                    if(node.left == null){
                        node.left = tmp;
                        return;
                    }
                    node = node.left;
                }
            }
            else{
                if(point.y() > node.point.y()){
                    if(node.right == null){
                        node.right = tmp;
                        return;
                    }
                    else
                        node = node.right;
                }
                else{
                    if(node.left == null){
                        node.left = tmp;
                        return;
                    }
                    node = node.left;
                }
            }
        }
    }
    
    private void print(Node node){
        System.out.printf("%d: %3.3f %3.3f\n", node.level, node.point.x(), node.point.y());
        if(node.left != null)
            print(node.left);
        if(node.right != null)
            print(node.right);
    }
    
    
    //////////////////////////
    //    Priority Queue    //
    //////////////////////////
    private class Pair implements Comparable<Pair>{
        public Point2D a;
        public double distance;
        public Pair(Point2D a, Point2D b){
            this.a = a;
            this.distance = a.distanceTo(b);
        }
        public int compareTo(Pair that){
            if(this.distance > that.distance) return 1;
            else if(this.distance < that.distance) return -1;
            else{
                if(this.a.x() > that.a.x()) return 1;
                else if(this.a.x() < that.a.x()) return -1;
                else{
                    if(this.a.y() > that.a.y()) return 1;
                    else if(this.a.y() < that.a.y()) return -1;
                    else return 0;
                }
            }
        }
    }
    
    private class PriorityQueue{
        private int n;
        private Pair[] pairs;
        public PriorityQueue(int n){
            this.n = 0;
            this.pairs = new Pair[n + 2];
        }
        public Pair[] getPairs(){
            return pairs;
        }
        public void insert(Pair p){
            pairs[++n] = p;
            swim(n);
        }
        public Pair Max(){
            return pairs[1];
        }
        public Pair delMax(){
            Pair max = pairs[1];
            Pair tmp = pairs[n];
            pairs[n--] = max;
            pairs[1]   = tmp;
            sink(1);
            pairs[n+1] = null;
            return max;
        }
        private void swim(int k){
            while(k > 1 && pairs[k/2].compareTo(pairs[k]) == -1){
                Pair tmp = pairs[k];
                pairs[k] = pairs[k/2];
                pairs[k/2] = tmp;
                k = k / 2;
            }
        }
        private void sink(int k){
            while(2 * k <= n){
                int j = 2 * k;
                if(j < n && pairs[j].compareTo(pairs[j+1]) == -1) j++;
                if(pairs[k].compareTo(pairs[j]) != -1) break;
                Pair tmp = pairs[j];
                pairs[j] = pairs[k];
                pairs[k] = tmp;
                k = j;
            }
        }
        public void print(){
            for(int i = 1; i <= n; i++){
                System.out.printf("%3.3f ", pairs[i].distance);
            }
            System.out.printf("\n");
        }
    }
    
    ////////////////////
    //    Main API    //
    ////////////////////
    public void init(Point2D[] points){
        for(int i = 0; i < points.length; i++){
            /*StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledCircle(points[i].x(), points[i].y(), 0.005);*/
            insert(points[i]);
        }
        //print(root);
    }
    
    private void check(Node node, Point2D point, int k){
        count++;
        Pair pair = new Pair(node.point, point);
        //System.out.printf("(x: %3.3f, y: %3.3f, d: %3.3f)\n", node.point.x(), node.point.y(), pair.distance);
        
        if(node.level % 2 == 1){
            if(point.x() > node.point.x()){
                if(node.right != null)
                    check(node.right, point, k);
            }
            else{
                if(node.left != null)
                    check(node.left, point, k);
            }
        }
        else{
            if(point.y() > node.point.y()){
                if(node.right != null)
                    check(node.right, point, k);
            }
            else{
                if(node.left != null)
                    check(node.left, point, k);
            }
        }
        
        int flag = 0;
        
        if(queue.n >= k){
            Point2D boundary;
            if(node.level % 2 == 1)
                boundary = new Point2D(node.point.x(), point.y());
            else
                boundary = new Point2D(point.x(), node.point.y());
            
            Pair cmp = queue.Max();
            Pair cmpFlag = new Pair(boundary, point);
            
            if(cmpFlag.compareTo(cmp) == 1)
                flag = 1;
            
            if(pair.compareTo(cmp) != 1){
                queue.delMax();
                queue.insert(pair);
            }
        }
        else
            queue.insert(pair);
        
        if(flag == 1)
            return;
        
        if(node.level % 2 == 1){
            if(point.x() <= node.point.x()){
                if(node.right != null)
                    check(node.right, point, k);
            }
            else{
                if(node.left != null)
                    check(node.left, point, k);
            }
        }
        else{
            if(point.y() <= node.point.y()){
                if(node.right != null)
                    check(node.right, point, k);
            }
            else{
                if(node.left != null)
                    check(node.left, point, k);
            }
        }
    }
    
    public Point2D[] query(Point2D point, int k){
        
        count = 0;
        /*StdDraw.setPenColor(StdDraw.MAGENTA);
        StdDraw.filledCircle(point.x(), point.y(), 0.01);*/
        
        queue = new PriorityQueue(k);
        Point2D[] result = new Point2D[k];
        
        check(root, point, k);
        //System.out.println(count);
        
        for(int i = k - 1; i >= 0; i--){
            Pair pair = queue.delMax();
            result[i] = pair.a;
            /*StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(pair.a.x(), pair.a.y(), 0.005);
            StdDraw.text((pair.a.x() + point.x()) / 2, (pair.a.y() + point.y()) / 2, String.valueOf(i));
            StdDraw.setPenColor(StdDraw.GREEN);
            StdDraw.line(pair.a.x(), pair.a.y(), point.x(), point.y());*/
        }
        return result;
    }
}

