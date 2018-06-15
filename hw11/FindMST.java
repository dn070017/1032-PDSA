import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

/////////////////
//    Union    //
/////////////////
class Connect{
    public int num;
    public int[] size;
    public int[] index;
    public HashMap<Point2D, Integer> nodes;
    
    public Connect(int num){
        this.num = num;
        this.size = new int[num];
        this.index = new int[num];
        this.nodes = new HashMap<Point2D, Integer>();
        for(int i = 0; i < num; i++){
            size[i] = 1;
            index[i] = i;
        }
    }
    
    public void setNode(int i, Point2D node){
        this.nodes.put(node, i);
    }
    
    public int root(int i){
        while(i != this.index[i]){
            i = this.index[i];
        }
        return i;
    }
    
    public boolean connected(Point2D a, Point2D b){
        int i = this.root(nodes.get(a));
        int j = this.root(nodes.get(b));
        return this.root(i) == this.root(j);
    }
    
    public void union(Point2D a, Point2D b){
        int i = this.root(nodes.get(a));
        int j = this.root(nodes.get(b));
        if(i != j){
            if(this.size[i] <  this.size[j]){
                this.size[j] += this.size[i];
                this.index[i] = j;
            }
            else{
                this.size[i] += this.size[j];
                this.index[j] = i;
            }
        }
    }
}


public class FindMST {
    
    private static int count = 0;
    private static Node root = null;
    private static PriorityQueue queue;
    
    ///////////////////
    //    2D Tree    //
    ///////////////////
    private static class Node{
        private Point2D point;
        private Node left;
        private Node right;
        private int level;
        
        public Node(Point2D point, int level){
            this.point = point;
            this.level = level;
        }
    }
    
    private static void insert(Point2D point){
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
    
    private static void print(Node node){
        System.out.printf("%d: %3.3f %3.3f\n", node.level, node.point.x(), node.point.y());
        if(node.left != null)
            print(node.left);
        if(node.right != null)
            print(node.right);
    }
    
    
    //////////////////////////
    //    Priority Queue    //
    //////////////////////////
    private static class Pair implements Comparable<Pair>{
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
    
    private static class PriorityQueue{
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
    public static void init(Point2D[] points){
        for(int i = 0; i < points.length; i++){
            //StdDraw.setPenColor(StdDraw.BLACK);
            //StdDraw.filledCircle(points[i].x(), points[i].y(), 0.005);
            insert(points[i]);
        }
        //print(root);
    }
    
    private static void check(Node node, Point2D point, int k){
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
    
    public static Point2D[] query(Point2D point, int k){
        
        count = 0;
        //StdDraw.setPenColor(StdDraw.MAGENTA);
        //StdDraw.filledCircle(point.x(), point.y(), 0.01);
        
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
    
    public static void accept(Point2D a, Point2D b){
        /*StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(a.x(), a.y(), 0.005);
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.line(a.x(), a.y(), b.x(), b.y());*/
    }
    
    static Point2D[] points;
    static int number;
    static Connect connect;
    static MinPQ<Edge> minpq = new MinPQ<Edge>(number*number*2);
    
    public static void main(String[] argv) throws Exception{
        double startTime = System.currentTimeMillis();
        String[] tmp;
        File file = new File(argv[0]);
        Scanner scanner = new Scanner(file);
        tmp = scanner.nextLine().split(" ");
        
        number = Integer.parseInt(tmp[0]);
        
        points = new Point2D[number];
        HashMap<Point2D, Integer> map = new HashMap<Point2D, Integer>();
        
        connect = new Connect(number);
        for(int i = 0; i < number; i++){
            tmp = scanner.nextLine().split(" ");
            double x = Double.parseDouble(tmp[0]);
            double y = Double.parseDouble(tmp[1]);
            Point2D point = new Point2D(x, y);
            map.put(point, i);
            connect.setNode(i, point);
            points[i] = point;
        }
        
        init(points);
        
        // Homework 11 start from here
        /* Brute Force
        for(int i = 0; i < number - 1; i++){
            for(int j = i + 1; j < number; j++){
                Edge edge = new Edge(map.get(points[i]), map.get(points[j]), points[i].distanceTo(points[j]));
                minpq.insert(edge);
            }
        }
        int edges = 0;
        while(edges != number - 1){
            Edge edge = minpq.delMin();
            int idxA = edge.either();
            int idxB = edge.other(idxA);
            Point2D a = points[idxA];
            Point2D b = points[idxB];
            if(!connect.connected(a, b)){
                edges++;
                connect.union(a, b);
                accept(a, b);
            }
        }*/
        
        
        //Nearest Neighbor
        double total = 0;
        int edges = 0;
        HashMap<Point2D, Integer> count = new HashMap<Point2D, Integer>();
        for(int i = 0; i < number; i++){
            Point2D point = query(points[i], 2)[1];
            count.put(points[i], 2);
            if(!connect.connected(point, points[i])){
                edges++;
                connect.union(point, points[i]);
                total += point.distanceTo(points[i]);
                accept(point, points[i]);
            }
        }
        
        while(edges != number - 1){
            if(minpq.size() == 0){
                for(int i = 0; i < number; i++){
                    if(count.get(points[i]) + 1 <= number){
                        Point2D point = query(points[i], count.get(points[i]) + 1)[count.get(points[i])];
                        count.put(points[i], count.get(points[i]) + 1);
                        Edge edge = new Edge(map.get(points[i]), map.get(point), points[i].distanceTo(point));
                        minpq.insert(edge);
                    }
                }
            }
            else{
                Edge edge = minpq.delMin();
                int idxA = edge.either();
                int idxB = edge.other(idxA);
                Point2D a = points[idxA];
                Point2D b = points[idxB];
                Point2D point;
                if(count.get(a) + 1 <= number){
                    point = query(a, count.get(a) + 1)[count.get(a)];
                    count.put(a, count.get(a) + 1);
                    edge = new Edge(map.get(a), map.get(point), a.distanceTo(point));
                    minpq.insert(edge);
                }
                if(count.get(b) + 1 <= number){
                    point = query(b, count.get(b) + 1)[count.get(b)];
                    count.put(b, count.get(b) + 1);
                    edge = new Edge(map.get(b), map.get(point), b.distanceTo(point));
                    minpq.insert(edge);
                }
                if(!connect.connected(a, b)){
                    edges++;
                    connect.union(a, b);
                    accept(a, b);
                    total += a.distanceTo(b);
                }
            }
        }
        System.out.printf("%5.5f\n", total);
        double estimatedTime = System.currentTimeMillis() - startTime;
        //System.out.printf("%3.3f\n", estimatedTime / 1000);
    }
}

