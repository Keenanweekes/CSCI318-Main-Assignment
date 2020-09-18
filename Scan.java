
package readjavafile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Scan {

    private ArrayList<String> argNames = new ArrayList<String>();
    private ArrayList<String> argTypes = new ArrayList<String>();
    private String constructorName;

    void GenCSV(String fileName) throws FileNotFoundException, IOException{
       
           
            File fileIn = new File(fileName);
            Scanner myReader = new Scanner(fileIn);
            
            FileWriter dataOut = new FileWriter("data.txt");
            
            String fileData;
            
            String className = null;
            
            int argCounter = 0;
            
            while(myReader.hasNextLine()){
                
                fileData = myReader.nextLine();
                
                
                
                if(fileData.contains("class")){
                    
                    String classNameTemp;
                    classNameTemp = fileData.substring(fileData.indexOf("class") + 6, fileData.length());
                    String classNameSplit[] = classNameTemp.split("\\{");
                    
                    className = classNameSplit[0];
                    
                    System.out.println("Class Name: " + className);
                    dataOut.write("Class Name: " + className + ".\n");
                    
                }
                
                if(fileData.contains("private " + className + "(") || fileData.contains("private " + className + " (")){
                    
                    System.out.println("Constructor Name: " + className);
                    dataOut.write("Constructor Name: " + className + ".\n");
                    
                    setConstructorName(className);
                    
                    String arguments = fileData.substring(fileData.indexOf("(") + 1, fileData.indexOf(")"));
                    String argumentsSplit[] = arguments.split(" ");
                    
                    for(String argument : argumentsSplit){
                        
                        String argumentSplit[] = argument.split(",");
                        
                        for(String argNameType: argumentSplit){
                                            
                            argCounter++;
                            
                            if(argCounter % 2 == 0){
                                
                                System.out.println("Arg Name: " + argNameType);
                                dataOut.write("Arg Name: " + argNameType + ".\n");
                                
                                getArgNames().add(argNameType);
                                
                            }else if(argCounter % 2 != 0){
                                
                                System.out.println("Arg Type: " + argNameType);
                                dataOut.write("Arg Type: " + argNameType + ".\n");
                                
                                getArgTypes().add(argNameType);
                            }
                            
                        }
                        
                        
                    }
                    
                    
                    
                }
                
                if(fileData.contains("public " + className + "(") || fileData.contains("public " + className + " (")){
                    
                    System.out.println("Constructor Name: " + className);
                    dataOut.write("Constructor Name: " + className + ".\n");
                    
                    setConstructorName(className);
                    
                    String arguments = fileData.substring(fileData.indexOf("(") + 1, fileData.indexOf(")"));
                    String argumentsSplit[] = arguments.split(" ");
                    
                    for(String argument : argumentsSplit){
                        
                        String argumentSplit[] = argument.split(",");
                        
                        for(String argNameType: argumentSplit){
                                            
                            argCounter++;
                            
                            if(argCounter % 2 == 0){
                                
                                System.out.println("Arg Name: " + argNameType);
                                dataOut.write("Arg Name: " + argNameType + ".\n");
                                
                                getArgNames().add(argNameType);
                                
                            }else if(argCounter % 2 != 0){
                                
                                System.out.println("Arg Type: " + argNameType);
                                dataOut.write("Arg Type: " + argNameType + ".\n");
                                
                                getArgTypes().add(argNameType);
                            }
                            
                        }
                        
                        argCounter = 0;
                    }
                    
                    if(argCounter == 0){
                        
                        System.out.println();
                        dataOut.write("\n");
                        
                    }
                    
                }
                
                if(fileData.contains("protected " + className + "(") || fileData.contains("protected " + className + " (")){
                    
                    System.out.println("Constructor Name: " + className);
                    dataOut.write("Constructor Name: " + className + ".\n");
                    
                    setConstructorName(className);
                    
                    String arguments = fileData.substring(fileData.indexOf("(") + 1, fileData.indexOf(")"));
                    String argumentsSplit[] = arguments.split(" ");
                    
                    for(String argument : argumentsSplit){
                        
                        String argumentSplit[] = argument.split(",");
                        
                        for(String argNameType: argumentSplit){
                                            
                            argCounter++;
                            
                            if(argCounter % 2 == 0){
                                
                                System.out.println("Arg Name: " + argNameType);
                                dataOut.write("Arg Name: " + argNameType + ".\n");
                                
                                getArgNames().add(argNameType);
                                
                            }else if(argCounter % 2 != 0){
                                
                                System.out.println("Arg Type: " + argNameType);
                                dataOut.write("Arg Type: " + argNameType + ".\n");
                                
                                getArgTypes().add(argNameType);
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
