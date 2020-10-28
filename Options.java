
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

    public int argSize() {
        return argNames.size();
    }

    public ArrayList<String> getArgs() {
        return argNames;
    }

    public String getType() {
        return type;
    }

    public Value randValues() { 
        if (this.type.substring(this.type.length() - 2, this.type.length()).equals("[]")) {
            int num = (int) Math.ceil(Math.random() * 10);
            ArrayList<Value> array = new ArrayList<>();
            String primType = this.type.substring(0, this.type.length() - 2);
            for (int i = 0; i < num; i++) array.add(createPrim(primType));
            switch(primType) {
                case "int": return new ArrayValue<IntValue>(name, array.toArray(new IntValue[0]));
                case "boolean": return new ArrayValue<BooleanValue>(name, array.toArray(new BooleanValue[0]));
                case "String": return new ArrayValue<StringValue>(name, array.toArray(new StringValue[0]));
                case "char": return new ArrayValue<CharValue>(name, array.toArray(new CharValue[0]));
                case "double": return new ArrayValue<DoubleValue>(name, array.toArray(new DoubleValue[0]));
                case "long": return new ArrayValue<LongValue>(name, array.toArray(new LongValue[0]));
                case "float": return new ArrayValue<FloatValue>(name, array.toArray(new FloatValue[0]));
                case "short": return new ArrayValue<ShortValue>(name, array.toArray(new ShortValue[0]));
                case "byte": return new ArrayValue<ByteValue>(name, array.toArray(new ByteValue[0]));
                default: return null;
            }
        }
        else return createPrim(this.type);
    }

    private Value createPrim(String type) {
        Random rand = new Random();   
        switch(type) {
            case "int": // max neg int to max int
                IntValue intValue = new IntValue(name, ThreadLocalRandom.current().nextInt());
                return intValue;
                
            case "boolean": // random true or false
                BooleanValue boolValue = new BooleanValue(name, ThreadLocalRandom.current().nextBoolean());
                return boolValue;
                
            case "String": // length 0 to 10 letters a - z
                String word = "";
                int wordSize = ThreadLocalRandom.current().nextInt(0, 11);
                for (int i = 0; i < wordSize; i++) {
                    word = word + (char)ThreadLocalRandom.current().nextInt(97, 122);
                }
                StringValue stringValue = new StringValue(name, word);
                return stringValue;
                
            case "char": // char a - z
                char character = (char)ThreadLocalRandom.current().nextInt(97, 122);
                CharValue charValue = new CharValue(name, character);
                return charValue;
                
            case "double": // double between 0 and 1
                DoubleValue doubleValue = new DoubleValue(name, ThreadLocalRandom.current().nextDouble());
                return doubleValue;
                
            case "long": // max neg long to max long
                LongValue longValue = new LongValue(name, ThreadLocalRandom.current().nextLong());
                return longValue;
                
            case "float": // float between 0 and 1
                FloatValue floatValue = new FloatValue(name, ThreadLocalRandom.current().nextFloat());
                return floatValue;
                
            case "short": // max neg short to max short
                ShortValue shortValue = new ShortValue(name, (short)(rand.nextInt(Short.MAX_VALUE + Short.MAX_VALUE) - Short.MAX_VALUE));
                return shortValue;
                
            case "byte": // max neg byte to max short
                ByteValue byteValue = new ByteValue(name, (byte)(rand.nextInt(Byte.MAX_VALUE + Byte.MAX_VALUE) - Byte.MAX_VALUE));
                return byteValue;       
                
            default:
                return null;
        }
    }
}
