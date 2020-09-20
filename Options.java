
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Options {
    private String name;
    private String type;
    private ArrayList<String> argNames;
    private ArrayList<String> values;
    
    Options(String name, String type, ArrayList<String> argNames, ArrayList<String> values) {
        this.name = name;
        this.type = type;
        this.argNames = argNames;
        this.values = values;
    }
    
    public String getName() {
        return name;
    }

    public Value randValues() { 
        Random rand = new Random();   
        switch(this.type) {
            case "IntValue": // max neg int to max int
                IntValue intValue = new IntValue(argNames.get(0), ThreadLocalRandom.current().nextInt());
                return intValue;
                
            case "BoolValue": // random true or false
                BoolValue boolValue = new BoolValue(argNames.get(0), ThreadLocalRandom.current().nextBoolean());
                return boolValue;
                
            case "StringValue": // length 0 to 10 letters a - z
                String word = "";
                int wordSize = ThreadLocalRandom.current().nextInt(0, 11);
                for (int i = 0; i < wordSize; i++) {
                    word = word + (char)ThreadLocalRandom.current().nextInt(97, 122);
                }
                StringValue stringValue = new StringValue(argNames.get(0), word);
                return stringValue;
                
            case "CharValue": // char a - z
                char character = (char)ThreadLocalRandom.current().nextInt(97, 122);
                CharValue charValue = new CharValue(argNames.get(0), character);
                return charValue;
                
            case "DoubleValue": // double between 0 and 1
                DoubleValue doubleValue = new DoubleValue(argNames.get(0), ThreadLocalRandom.current().nextDouble());
                return doubleValue;
                
            case "LongValue": // max neg long to max long
                LongValue longValue = new LongValue(argNames.get(0), ThreadLocalRandom.current().nextLong());
                return longValue;
                
            case "FloatValue": // float between 0 and 1
                FloatValue floatValue = new FloatValue(argNames.get(0), ThreadLocalRandom.current().nextFloat());
                return floatValue;
                
            case "ShortValue": // max neg short to max short
                ShortValue shortValue = new ShortValue(argNames.get(0), (short)(rand.nextInt(Short.MAX_VALUE + Short.MAX_VALUE) - Short.MAX_VALUE));
                return shortValue;
                
            case "ByteValue": // max neg byte to max short
//                ByteValue byteValue = new ByteValue(argNames.get(0), (byte)(rand.nextInt(Byte.MAX_VALUE + Byte.MAX_VALUE) - Byte.MAX_VALUE));
//                return byteValue;
               
            case "ArrayValue":

                break;
           
        }
 
        IntValue value = new IntValue("test", 5);
        return value; 
        
    }
}
