package ART;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class Generate {

	private HashMap<String, Options> options;
	private String orgClassName;
	
	public void readCSV() {
		
		String className = null;
		String type = null;
		ArrayList<String> argNames = new ArrayList<>();
	
		try  {
			 Scanner scanner = new Scanner(new File("data.txt"));
	            
	            while (scanner.hasNextLine()) {
	                String data = scanner.nextLine();

	                if (data.substring(0, 10).equals("Class Name")) {
	                   
	                    className = data.substring(12, data.length() - 1);
	                
	                    data = scanner.nextLine();
	                    while (scanner.hasNextLine() && !data.equals("")) {
	                        if (data.substring(0, 8).equals("Arg Name")) {
	                        	argNames.add(data.substring(10, data.length() - 1));
	                        	data = scanner.nextLine();
	                        }
	                        if (data.substring(0, 8).equals("Arg Type")) 
	                        	type = data.substring(10, data.length() - 1);
	                        	data = scanner.nextLine();
	                        }
	                    }
	                Options option = new Options(className, type, argNames);
	                options.put(className, option);
	                System.out.println(option.toString());
	            }
	            
	            scanner.close();

	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
}
	
