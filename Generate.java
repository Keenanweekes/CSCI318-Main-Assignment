import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class Generate {

	private HashMap<String, Options> options;
	private String orgClassName;
	
	Generate(String ocn) {
        this.orgClassName = ocn;
        options = new HashMap<>();
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
                        if (data.substring(0, 8).equals("Arg Type")) {
                            String type = data.substring(10, data.length() - 1);
                            data = scanner.nextLine();
                            String argName = data.substring(10, data.length() - 1);
                            
                            argNames.add(argName);    
                            Options option = new Options(argName, type, new ArrayList<String>(), new ArrayList<String>());
                            options.put(argName, option);
                            
                        }
                        if (scanner.hasNextLine()) data = scanner.nextLine();
                    }
                
                    Options class_obj = new Options(className, "object", argNames, new ArrayList<String>());
                    options.put(className, class_obj);
                
                }
            }
            
            scanner.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	
	public ObjectValue Gen() {
		
		return (ObjectValue)genObject(orgClassName);
		
	}
	
	private Value genObject(String argName) {
		
		Options opt = options.get(argName);

        //Gets straight type
        String type = opt.getType().indexOf("]") == opt.getType().length() - 1
        ? opt.getType().substring(0, opt.getType().length() - 2)
        : opt.getType();
        
        //Checks if the type is a create class
        if (options.containsKey(type)) {
            //If class type is an array
            if (opt.getType().indexOf("]") == opt.getType().length() - 1) {
                int num = (int) Math.ceil(Math.random() * 10);
                ArrayList<ObjectValue> array = new ArrayList<>();
                for (int i = 0; i < num; i++) array.add((ObjectValue)genObject(type));
                return new ArrayValue<ObjectValue>(argName, array.toArray(new ObjectValue[0]));
            }
            //Type is not an array
            return genObject(opt.getType());
        }

        //Check if the option has arguments and if so treats as an object
		if(opt.argSize() != 0) {
			ArrayList<Value> values = new ArrayList<Value>();
			ArrayList<String> argNames = opt.getArgs();
            
            //Treats it as part of its arguments
			for(String an : argNames) {
				Value param = genObject(an);
				values.add(param);
			}

			return new ObjectValue(argName, values);
			
		} else {
            
            //Primitive like argument, therefore can generate basic value
			return opt.randValues();
			
		}
		
	}
	
}