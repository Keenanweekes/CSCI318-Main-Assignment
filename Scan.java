
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scan {

    private ArrayList<String> argNames = new ArrayList<String>();
    private ArrayList<String> argTypes = new ArrayList<String>();
    private String constructorName;

    void GenCSV(String fileName) throws FileNotFoundException, IOException {

        File fileIn = new File(fileName);
        Scanner myReader = new Scanner(fileIn);

        FileWriter dataOut = new FileWriter("data.txt");

        String fileData;

        String className = null;

        int argCounter = 0;
        int classCounter = 0;

        while (myReader.hasNextLine()) {

            fileData = myReader.nextLine();
            String classStr = "class";
            if (fileData.contains("abstract")) continue;

            if (fileData.contains(classStr)) {

                if (classCounter != 0) {

                    String classNameTemp;
                    classNameTemp = fileData.substring(fileData.indexOf(classStr) + 6, fileData.length());
                    String classNameSplit[] = classNameTemp.split("\\{");

                    className = classNameSplit[0];
                    className = className.split(" ")[0];

                    dataOut.write("\nClass Name: " + className + ".\n");

                } else {

                    String classNameTemp;
                    classNameTemp = fileData.substring(fileData.indexOf(classStr) + 6, fileData.length());
                    String classNameSplit[] = classNameTemp.split("\\{");

                    className = classNameSplit[0];
                    className = className.split(" ")[0];

                    dataOut.write("Class Name: " + className + ".\n");

                }

                classCounter++;

            }

            ArrayList<String> accessMod = new ArrayList<String>();
            accessMod.add("private");
            accessMod.add("public");
            accessMod.add("protected");
            accessMod.add("");

            for (String accessModifier : accessMod) {

                if (fileData.contains(accessModifier + className + "(") || fileData.contains(accessModifier + className + " (")) {

                    setConstructorName(className);

                    String arguments = fileData.substring(fileData.indexOf("(") + 1, fileData.indexOf(")"));
                    String argumentsSplit[] = arguments.split(" ");

                    for (String argument : argumentsSplit) {

                        String argumentSplit[] = argument.split(",");

                        for (String argNameType : argumentSplit) {

                            argCounter++;

                            if (argCounter % 2 == 0) {

                                dataOut.write("Arg Name: " + argNameType + ".\n");

                                getArgNames().add(argNameType);

                            } else if (argCounter % 2 != 0) {

                                dataOut.write("Arg Type: " + argNameType + ".\n");

                                getArgTypes().add(argNameType);
                            }
                        }
                    }
                }
            }

            if (fileData.contains("if(") || fileData.contains("if (")) {
                for (String argumentNames : getArgNames()) {

                    String pattern = "\\b" + argumentNames + "\\b";
                    Pattern p = Pattern.compile(pattern);

                    Matcher m = p.matcher(fileData);

                    if (m.find() == true) {
                        String checking = fileData.substring(fileData.indexOf(argumentNames) + argumentNames.length() + 1, fileData.length());
                        String comp = " compared to ";

                        ArrayList<String> operator = new ArrayList<String>();
                        operator.add(">=");
                        operator.add("<=");
                        operator.add("==");
                        operator.add("!=");
                        operator.add("equals(");

                        for (String operatorString : operator) {

                            if (checking.contains(operatorString)) {
                                String value = checking.substring(checking.indexOf(operatorString) + operatorString.length() + 1, checking.indexOf(")"));
                                value = value.replaceAll("\"", "");

                                dataOut.write(argumentNames + comp + value + "\n");
             
                            }

                        }
                    }
                }
            }
        }
        dataOut.close();
    }

    public ArrayList<String> getArgNames() {
        return argNames;
    }

    public String getConstructorName() {
        return constructorName;
    }

    public ArrayList<String> getArgTypes() {
        return argTypes;
    }

    public void setConstructorName(String constructorName) {
        this.constructorName = constructorName;
    }
}
