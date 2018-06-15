import java.io.File;
import java.util.Scanner;
// import java.awt.geom.Point2D;

public class Tester{
    public static void main(String[] argv) throws Exception{
        File file = new File(argv[0]);
        Scanner scanner = new Scanner(file);
        int n = Integer.parseInt(scanner.nextLine());
        if(n < 0){
            n = Integer.parseInt(scanner.nextLine());
            Point2D[] a = new Point2D[n];
            int idx = 0;
            while(scanner.hasNext()){
                String[] tmp = scanner.nextLine().split(" ");
                double x = Double.parseDouble(tmp[0]);
                double y = Double.parseDouble(tmp[1]);
                Point2D add = new Point2D(x, y);
                a[idx] = add;
                idx++;
            }
            int[] result = MyConvexHull.ConvexHullVertex(a);
            for(int i = 0; i < result.length; i++){
                System.out.printf("%d ", result[i]);
            }
            System.out.printf("\n");
        }
        else{
            String[] arr = new String[1];
            arr[0] = argv[0];
            MyConvexHull.main(arr);
        }
    }
}