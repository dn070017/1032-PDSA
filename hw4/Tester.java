import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.util.NoSuchElementException;

public class Tester<Item>{
    private static Deque<Integer> dequeInt;
    private static Deque<String>  dequeStr;
    private static Iterator it;

    public static void main(String[] argv) throws Exception{
        File file = new File(argv[0]);
        Scanner scanner = new Scanner(file); 
        String type = scanner.nextLine();
        int empty   = Integer.parseInt(scanner.nextLine());
        int next = Integer.parseInt(scanner.nextLine());
        int remove = Integer.parseInt(scanner.nextLine());
        if(type.equals("Integer")){
            dequeInt = new Deque<Integer>();
        }
        else{
            dequeStr = new Deque<String>();
        }
        while(scanner.hasNext()){
            String line   = scanner.nextLine();
            //System.out.println(line);
            String[] tmp  = line.split(" ");

            if(tmp[0].equals("it")){
                if(type.equals("Integer")){
                    it = dequeInt.iterator();
                }
                else{
                    it = dequeStr.iterator();
                }
                 while(it.hasNext()){
                    System.out.println(it.next());
                }
            }

            try{
                if(tmp[0].equals("-")){
                    if(type.equals("Integer")){
                        dequeInt.removeFirst();
                    }
                    else{
                        dequeStr.removeFirst();
                    }
                }
                else if(tmp[0].equals("--")){
                    if(type.equals("Integer")){
                        dequeInt.removeLast();
                    }
                    else{
                        dequeStr.removeLast();
                    }
                }
            }
            catch(NoSuchElementException error){
                System.out.println("NoSuchElementException");
            }

            if(tmp[0].equals("+")){
                //System.out.println("test");
                if(type.equals("Integer")){
                    dequeInt.addFirst(Integer.parseInt(tmp[1]));
                }
                else{
                    dequeStr.addFirst(tmp[1]);
                }   
            }
            else if(tmp[1].equals("+")){
                if(type.equals("Integer")){
                    dequeInt.addLast(Integer.parseInt(tmp[0]));
                }
                else{
                    dequeStr.addLast(tmp[0]);
                }     
            }
        }
        //dequeInt.print();
        try{
            if(empty == 1){
                if(type.equals("Integer")){
                    Integer i = null;
                    dequeInt.addFirst(i);
                }
                else{
                    String i = null;
                    dequeStr.addFirst(i);
                }   
            }
            else if(empty == 2){
                if(type.equals("Integer")){
                    Integer i = null;
                    dequeInt.addLast(i);
                }
                else{
                    String i = null;
                    dequeStr.addFirst(i);
                }  
            }
        }
        catch(NullPointerException error){
            System.out.println("NullPointerException");
        }
        
        if(type.equals("Integer")){
            it = dequeInt.iterator();
            System.out.println(dequeInt.size());
            System.out.println(dequeInt.isEmpty());
        }
        else{
            it = dequeStr.iterator();
            System.out.println(dequeStr.size());
            System.out.println(dequeStr.isEmpty());
        }
        while(it.hasNext()){
            System.out.println(it.next());
        }

        try{
            if(remove != 0){
                it.remove();
            }
        }
        catch(UnsupportedOperationException error){
            System.out.println("UnsupportedOperationException");
        }
        try{
           if(next != 0){
                it.next();
           }
        }
        catch(NoSuchElementException error){
                System.out.println("NoSuchElementException");
        }
   	}
}  