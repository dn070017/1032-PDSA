import java.io.File;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

class Pair implements Comparable<Pair>{
    public Node a, b;
    public boolean s, t;
    public double distance;
    
    public Pair(Node a, Node b, boolean s, boolean t){
        this.a = a;
        this.b = b;
        this.s = s;
        this.t = t;
        this.distance = a.point.distanceTo(b.point);
    }
    
    public int compareTo(Pair that){
        if(this.distance > that.distance) return 1;
        else if(this.distance < that.distance) return -1;
        return 0;
    }
}

class Node{
    public int tmp = 0;
    public Point2D point;
    public ArrayList<Node> next;
    
    public Node(Point2D point){
        this.point = point;
        next = new ArrayList<Node>();
    }
    
    public void addEdge(Node that){
        this.next.add(that);
        this.tmp++;
        that.tmp++;
    }
    
    public boolean search(Node target, boolean flag){
        for(int i = 0; i < next.size(); i++){
            if(next.get(i).point == target.point){
                flag = true;
                break;
            }
            flag = next.get(i).search(target, false);
            if(flag == true)
                break;
        }
        return flag;
    }
}

class Connect{
    public int num;
    public int[] size;
    public int[] index;
    public HashMap<Node, Integer> nodes;

    public Connect(int num){
        this.num = num;
        this.size = new int[num];
        this.index = new int[num];
        this.nodes = new HashMap<Node, Integer>();
        for(int i = 0; i < num; i++){
            size[i] = 1;
            index[i] = i;
        }
    }

    public void setNode(int i, Node node){
        this.nodes.put(node, i);
    }
    
    public int root(int i){
        while(i != this.index[i]){
            i = this.index[i];
        }
        return i;
    }
    
    public boolean connected(Node a, Node b){
        int i = this.root(nodes.get(a));
        int j = this.root(nodes.get(b));
        return this.root(i) == this.root(j);
    }
    
    public void union(Node a, Node b){
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

public class CriticalDis {

    public static int number, k;
    public static Node[] points;
    public static Connect connect;
    public static Node source, target;
    public static ArrayList<Pair> pairs = new ArrayList<Pair>();
    
    public static void main(String[] argv) throws Exception{
        double startTime = System.currentTimeMillis();
        
        String[] tmp;
        File file = new File(argv[0]);
        Scanner scanner = new Scanner(file);
        number = Integer.parseInt(scanner.nextLine());

        points = new Node[number];
        connect = new Connect(number);
        
        double min = 2.0;
        double max = 0.0;
        int sidx = 0;
        int tidx = 0;
        
        for(int i = 0; i < number; i++){
            tmp = scanner.nextLine().split(" ");
            double x = Double.parseDouble(tmp[0]);
            double y = Double.parseDouble(tmp[1]);
            Point2D point = new Point2D(x, y);
            Node node = new Node(point);
            if(x + y <= min){
                min = x + y;
                source = node;
                sidx = i;
            }
            if(x + y >= max){
                max = x + y;
                target = node;
                tidx = i;
            }
            //System.out.printf("(%3.3f, %3.3f)\n", x, y);
            points[i] = node;
            /*StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledCircle(point.x(), point.y(), 0.005);*/
            connect.setNode(i, node);
        }
        
        for(int i = 0; i < number - 1; i++){
            for(int j = i + 1; j < number; j++){
                Pair pair = null;
                boolean s = false;
                boolean t = false;
                Node a = points[i];
                Node b = points[j];
                
                if(a.point == source.point || b.point == source.point)
                    s = true;
                if(a.point == target.point || b.point == target.point)
                    t = true;
                if(a.point.x() < b.point.x() && a.point.y() < b.point.y())
                    pair = new Pair(a, b, s, t);
                else if(a.point.x() > b.point.x() && a.point.y() > b.point.y())
                    pair = new Pair(b, a, s, t);
                if(pair != null)
                    pairs.add(pair);
            }
        }
        
        double estimatedTime = System.currentTimeMillis() - startTime;
        // System.out.printf("%3.3f\n", estimatedTime / 1000);
        
        Collections.sort(pairs);
        double count = 0;
        boolean s = false;
        boolean t = false;
        double baseline = pairs.get(pairs.size()-1).distance;
        
        for(int i = 0; i < pairs.size(); i++){
            Node a = pairs.get(i).a;
            Node b = pairs.get(i).b;
            count += pairs.get(i).distance;
            Point2D pa = pairs.get(i).a.point;
            Point2D pb = pairs.get(i).b.point;
            
            if(!(pa.x() < pb.x() && pa.y() < pb.y())){
                System.out.println("Error");
            }
            
            /*StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledCircle(pairs.get(i).a.point.x(), pairs.get(i).a.point.y(), 0.005);
            StdDraw.filledCircle(pairs.get(i).b.point.x(), pairs.get(i).b.point.y(), 0.005);
            StdDraw.setPenColor(StdDraw.GREEN);
            StdDraw.line(pa.x(), pa.y(), pb.x(), pb.y());
            StdDraw.setPenColor(StdDraw.BLUE);*/
            //StdDraw.text((pa.x() + pb.x()) / 2, (pa.y() + pb.y()) / 2, String.valueOf(i + 1));
            
            a.addEdge(b);
            connect.union(a, b);
            
            if(pairs.get(i).s == true && s == false)
                s = true;
            if(pairs.get(i).t == true && t == false)
                t = true;
            
            boolean flag = false;
            if(count >= baseline && s == true && t == true){
                if(flag == true || connect.connected(source, target)){
                    flag = true;
                    if(source.search(target, false)){
                        System.out.printf("%1.3f\n", pairs.get(i).distance);
                        break;
                    }
                }
            }
        }
        
        /*StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(source.point.x(), source.point.y(), 0.005);
        StdDraw.setPenColor(StdDraw.MAGENTA);
        StdDraw.filledCircle(target.point.x(), target.point.y(), 0.005);
        StdDraw.setPenColor(StdDraw.GREEN);*/
        
        estimatedTime = System.currentTimeMillis() - startTime;
        //System.out.printf("%3.3f\n", estimatedTime / 1000);
    }
}

