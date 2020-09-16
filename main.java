
package readjavafile;

import java.io.IOException;


public class main {
    
    public static void main(String args[]) throws IOException{
        
        Scan scan = new Scan();
        
        scan.GenCSV("test.java");   
        
        for(String thing : scan.getArgNames()){
            
            System.out.println(thing);
            
        }
        
        for(String thing2 : scan.getArgTypes()){
            
            System.out.println(thing2);
            
        }
        
    }
    
}
