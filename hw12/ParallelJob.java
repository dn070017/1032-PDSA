import java.io.File;
import java.util.Scanner;
import java.util.Iterator;

public class ParallelJob{
    private static int n;
    private static int[] start;
    private static int[] end;
    private static AcyclicSP ASP;
    private static EdgeWeightedDigraph graph;
    
    public static void main(String[] argv) throws Exception{
        File file = new File(argv[0]);
        Scanner scanner = new Scanner(file);
        n = Integer.parseInt(scanner.nextLine());
        /* 2 * n = source
           2 * n + 1 = target
           For ID 5:
             Job Start = 5 * 2 = 10,
             Job End = 5 * 2 + 1 = 11 */
        graph = new EdgeWeightedDigraph(2 * n + 2);
        start = new int[n];
        end   = new int[n];
        for(int i = 0; i < n; i++){
            start[i] = 0;
            end[i]   = 0;
        }
        
        for(int i = 0; i < n; i++){
            String[] line = scanner.nextLine().split("\t");
            int job = Integer.parseInt(line[0]);
            double duration = Double.parseDouble(line[1]);
            DirectedEdge edge = new DirectedEdge(2 * job, 2 * job + 1, -1 * duration);
            graph.addEdge(edge);
            //System.out.printf("%d, %3.3f", job, duration);
            if(line.length == 3){
                String[] nextJobs = line[2].split(" ");
                for(int j = 0; j < nextJobs.length; j++){
                    int next = Integer.parseInt(nextJobs[j]);
                    end[job] = 1;
                    start[next] = 1;
                    edge = new DirectedEdge(2 * job + 1, 2 * next, 0.0);
                    graph.addEdge(edge);
                    //System.out.printf(", %d", next);
                }
            }
            //System.out.printf("\n");
        }
        
        for(int i = 0; i < n; i++){
            if(start[i] == 0){
                DirectedEdge edge = new DirectedEdge(2 * n, 2 * i, 0.0);
                graph.addEdge(edge);
            }
            if(end[i] == 0){
                DirectedEdge edge = new DirectedEdge(2 * i + 1, 2 * n + 1, 0.0);
                graph.addEdge(edge);
            }
        }
        //System.out.println("\n" + graph.toString());
        ASP = new AcyclicSP(graph, 2 * n);
        Iterable<DirectedEdge> path = ASP.pathTo(2 * n + 1);
        Iterator<DirectedEdge> it = path.iterator();
        double result = 0;
        while(it.hasNext()){
            DirectedEdge edge = it.next();
            if(edge.from() % 2 == 1 || edge.from() >= 2 * n) continue;
            result += edge.weight();
            //System.out.printf("%d ", edge.from() / 2);
        }
        result *= (-1);
        System.out.printf("%4.2f\n", result);
        //System.out.printf("\n");
    }
}