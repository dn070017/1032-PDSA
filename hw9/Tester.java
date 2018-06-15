import java.io.File;
import java.util.Scanner;

public class Tester {
    
    public static void main(String[] argv) throws Exception{
        Point2D[] points;
        int number, k;

        String[] tmp;
        File file = new File(argv[0]);
        Scanner scanner = new Scanner(file);
        tmp = scanner.nextLine().split(" ");
        
        k = Integer.parseInt(tmp[1]);
        number = Integer.parseInt(tmp[0]);
        
        points = new Point2D[number];
        
        for(int i = 0; i < number; i++){
            tmp = scanner.nextLine().split(" ");
            double x = Double.parseDouble(tmp[0]);
            double y = Double.parseDouble(tmp[1]);
            Point2D point = new Point2D(x, y);
            points[i] = point;
        }
        
        FindNeighbors fn = new FindNeighbors();
        fn.init(points);
        
        while(scanner.hasNext()){
            tmp = scanner.nextLine().split(" ");
            double x = Double.parseDouble(tmp[0]);
            double y = Double.parseDouble(tmp[1]);
            Point2D point = new Point2D(x, y);
            Point2D[] result = fn.query(point, k);
            for(int i = 0; i < result.length; i++)
                System.out.printf("(%3.3f, %3.3f) ", result[i].x(), result[i].y());
            System.out.printf("\n");
        }
    }
    
}

