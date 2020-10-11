import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class CreateConstructor {
    private final String[] primitives = {"int", "boolean", "String", "char", "double", "long", "float", "short", "byte"};
    
    public void create(String mainObj) {
        try (
            Scanner scanner = new Scanner(new File("data.txt"));
            FileWriter writer = new FileWriter("Generateclass.java", false);
        ) {
            writer.append("class GenerateClass {\nstatic public String generate(ObjectValue val) {\n");
            writer.append("String success = \"Success\";\ntry {\ngen" + mainObj + "(val);\n} catch (Exception e) {\n");
            writer.append("success = e.toString();\n}\nreturn success;\n}\n");
            
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();

                if (data.substring(0, 10).equals("Class Name")) {
                    writer.append("\n");

                    String className = data.substring(12, data.length() - 1);
                    ArrayList<String> argTypes = new ArrayList<>();

                    data = scanner.nextLine();
                    while (scanner.hasNextLine() && !data.equals("")) {
                        if (data.substring(0, 8).equals("Arg Type")) argTypes.add(data.substring(10, data.length() - 1));
                        data = scanner.nextLine();
                    }

                    writer.append(createObj(Arrays.copyOf(argTypes.toArray(), argTypes.size(), String[].class), className));
                }
            }
            writer.append("}");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String createObj(String[] argTypes, String className) {
        String returnString = "static private " + className + " gen" + className + "(ObjectValue val) throws Exception {\n";
        for (int i = 0; i < argTypes.length; i++) {
            returnString += convertVar(argTypes[i], i) + "\n";
        }

        returnString += "return new " + className + "(";
        for (int i = 0; i < argTypes.length; i++) {
            if (i != 0) returnString += ", ";
            returnString += "arg" + i;
        }
        returnString += ");\n}\n";

        return returnString;
    }

    private boolean isPrimitive(String type) {
        boolean primitive = false;
        for (String val : primitives) {
            if (type.equals(val)) {
                primitive = true;
                break;
            }
        }
        return primitive;
    }

    private String convertVar(String type, int argNum) {
        //if type is in primitives array
        if (isPrimitive(type)) return primitiveVar(type, argNum);
        
        if (type.substring(type.length() - 2, type.length()).equals("[]")) return arrayVar(type, argNum);
        else return objectVar(type, argNum);
    }

    private String getPrimitive(String type, int argNum) {
        String capital = type.substring(0, 1).toUpperCase() + type.substring(1);
        return "((" + capital + "Value) val.get(" + argNum + ")).getVal()";
    }

    private String primitiveVar(String type, int argNum) {
        return type + " arg" + argNum + " = " + getPrimitive(type, argNum) + ";";
    }

    private String getObject(String type, int argNum) {
        return "gen" + type + "((ObjectValue) val.get(" + argNum + "))";
    }

    private String objectVar(String type, int argNum) {
        return type + " arg" + argNum + " = " + getObject(type, argNum) + ";";
    }

    private String arrayVar(String arrType, int argNum) {
        if (isPrimitive(arrType.substring(0, arrType.length() - 2))) {
            String type = arrType.substring(0, arrType.length() - 2);
            String capital = type.substring(0, 1).toUpperCase() + type.substring(1);
            String returnString = capital + "Value[] argArr" + argNum + " = ((ArrayValue<" + capital + "Value>) val.get(" + argNum + ")).getVal();\n";
            returnString += type + "[] arg" + argNum + " = new " + type + "[argArr" + argNum + ".length];\n";
            returnString += "for (int i = 0; i < argArr" + argNum + ".length; i++) arg" + argNum + "[i] = argArr" + argNum + "[i].getVal();";
            return returnString;
        } else {
            String type = arrType.substring(0, arrType.length() - 2);
            String returnString = "ObjectValue[] argArr" + argNum + " = ((ArrayValue<ObjectValue>) val.get(" + argNum + ")).getVal();\n";
            returnString += type + "[] arg" + argNum + " = new " + type + "[argArr" + argNum + ".length];\n";
            returnString += "for (int i = 0; i < argArr" + argNum + ".length; i++) arg" + argNum + "[i] = gen" + type + "(argArr" + argNum + "[i]);";
            return returnString;
        }
    }
}
