package ART;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class Generate {

	private HashMap<String, Options> options;
	private String orgClassName;
	
	Generate(String ocn) {
		this.orgClassName = ocn;
		readCSV();
	}
	
	private void readCSV() {
	
		try {
			 Scanner scanner = new Scanner(new File("data.txt"));
	            
	            while (scanner.hasNextLine()) {
	                String data = scanner.nextLine();

	                if (data.substring(0, 10).equals("Class Name")) {
	                	
	                	ArrayList<String> argNames = new ArrayList<>();
	                	String className = data.substring(12, data.length() - 1);
	            
	                    data = scanner.nextLine();
	                    while (scanner.hasNextLine() && !data.equals("")) {
	                        if (data.substring(0, 8).equals("Arg Name")) {
	                        	String argName = data.substring(10, data.length() - 1);
	                        	
	                        	argNames.add(argName);
	                        	data = scanner.nextLine();
	                        	
	                        	String type = data.substring(10, data.length() - 1);
	                        	Options option = new Options(argName, type, new ArrayList<String>(), new ArrayList<String>());
	                        	options.put(argName, option);
	                        	
	                        	data = scanner.nextLine();
	                        }
	                        
	                    }
	                
	                Options class_obj = new Options(className, "object", argNames, new ArrayList<String>());
	                options.put(className, class_obj);
	               
	                }
	            }
	            
	            scanner.close();

	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	
	}
	
	
	public ObjectValue Gen() {
		
		return (ObjectValue)genObject(orgClassName);
		
	}
	
	private Value genObject(String argName) {
		
		Options opt = options.get(argName);
		
		if(opt.argSize() != 0) {
			ArrayList<Value> values = new ArrayList<Value>();
			ArrayList<String> argNames = opt.getArgs();
			
			for(String an : argNames) {
				Value param = genObject(an);
				values.add(param);
			}
			
			return new ObjectValue(argName, values);
			
		} else {
			
			return opt.randValues();
			
		}
		
	}
	
}
	
