
package readjavafile;

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

    void GenCSV(String fileName) throws FileNotFoundException, IOException{
       
           
            File fileIn = new File(fileName);
            Scanner myReader = new Scanner(fileIn);
            
            FileWriter dataOut = new FileWriter("data.txt");
            
            String fileData;
            
            String className = null;
            
            int argCounter = 0;
            int classCounter = 0;
            
            while(myReader.hasNextLine()){
                               
                fileData = myReader.nextLine();
                                               
                if(fileData.contains("class")){
                    
                    
                    if(classCounter != 0){
                        
                        String classNameTemp;
                        classNameTemp = fileData.substring(fileData.indexOf("class") + 6, fileData.length());
                        String classNameSplit[] = classNameTemp.split("\\{");
                    
                        className = classNameSplit[0];
                    
                        System.out.println("\nClass Name: " + className);
                        dataOut.write("\nClass Name: " + className + ".\n");
                        
                        
                    }else{
                    
                        String classNameTemp;
                        classNameTemp = fileData.substring(fileData.indexOf("class") + 6, fileData.length());
                        String classNameSplit[] = classNameTemp.split("\\{");
                    
                        className = classNameSplit[0];
                    
                        System.out.println("Class Name: " + className);
                        dataOut.write("Class Name: " + className + ".\n");
                    
                    }
                    
                    classCounter++;
                    
                }
                
             
                
                if(fileData.contains("private " + className + "(") || fileData.contains("private " + className + " (")){
                    
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
                

                
                if(fileData.contains("protected " + className + "(") || fileData.contains("protected " + className + " (")){
                     
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
                
               
                
                if(fileData.contains("if(") || fileData.contains("if (")){
                    
                      
                    for(String argumentNames : getArgNames()){
                        
                        String pattern = "\\b" +argumentNames+ "\\b";
                        Pattern p = Pattern.compile(pattern);
                        
                        Matcher m = p.matcher(fileData);
                     
                       if(m.find() == true){
                           
                     
                           
                           String checking = fileData.substring(fileData.indexOf(argumentNames) + argumentNames.length() + 1, fileData.length());
                           
                           if(checking.contains(">=")){
                               
                               String value = checking.substring(checking.indexOf(">=") + 3, checking.indexOf(")"));
                               
                               System.out.println(argumentNames + " compared to " + value);
                               dataOut.write(argumentNames + " compared to " + value + "\n");
                               
                               
                           }
                           
                           if(checking.contains("<=")){
                               
                               String value = checking.substring(checking.indexOf("<=") + 3, checking.indexOf(")"));
                               
                               System.out.println(argumentNames + " compared to " + value);
                               dataOut.write(argumentNames + " compared to " + value + "\n");
                           }
                           
                           if(checking.contains("==")){
                               
                               String value = checking.substring(checking.indexOf("==") + 3, checking.indexOf(")"));
                               
                               System.out.println(argumentNames + " compared to " + value);
                               dataOut.write(argumentNames + " compared to " + value + "\n");
                           }
                           
                           if(checking.contains("!=")){
                               
                               String value = checking.substring(checking.indexOf("!=") + 3, checking.indexOf(")"));
                               
                               System.out.println(argumentNames + " compared to " + value);
                               dataOut.write(argumentNames + " compared to " + value + "\n");
                           }
                           
                           if(checking.contains("equals(")){
                                                            
                               
                               String value = checking.substring(checking.indexOf("equals("), checking.indexOf(")"));
                               
                               String valueEquals = value.substring(value.indexOf("\"") + 1, value.lastIndexOf("\""));
                               
                               System.out.println(argumentNames + " compared to " + valueEquals);
                             
                               dataOut.write(argumentNames + " compared to " + valueEquals + "\n");
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
