import java.util.ArrayList;

abstract class Value {
    protected String name;

    Value(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double distance(Value alt);

    public abstract String getString();

    public abstract String strVal();
}

class IntValue extends Value {

    private int val;
    private final double ratio = Math.log10(9);

    IntValue(String name, int val) {
        super(name);
        this.val = val;
    }

    @Override
    public double distance(Value alt) {
        double difference = 0;
        try {
            difference = Math.abs(this.val - ((IntValue) alt).getVal());
        } catch (ClassCastException e) {
            return 10; //The types are different
        }
        return Math.log10(difference + 1) / ratio;
    }

    public int getVal() {
        return val;
    }

    @Override
    public String getString() {
        return name + ": " + String.valueOf(val);
    }

    @Override
    public String strVal() {
        return String.valueOf(val);
    }
}

class BooleanValue extends Value {

    private boolean val;

    BooleanValue(String name, boolean val) {
        super(name);
        this.val = val;
    }

    @Override
    public double distance(Value alt) {
        boolean difference;
        try {
            difference = val == ((BooleanValue) alt).getVal();
        } catch (ClassCastException e) {
            return 10; //The types are different
        }
        return difference ? 0 : 10;
    }

    public boolean getVal() {
        return val;
    }
    
    @Override
    public String getString() {
        return name + ": " + String.valueOf(val);
    }

    @Override
    public String strVal() {
        return String.valueOf(val);
    }
}

class StringValue extends Value {

    private String val;
    
    StringValue(String name, String val) {
        super(name);
        this.val = val;
    }

    @Override
    public double distance(Value alt) {
        String altString = "";
        try {
            altString = ((StringValue) alt).getVal();
        } catch (ClassCastException e) {
            return 10; //The types are different
        }
        return LevenshtienDistance(altString, val.length(), altString.length());
    }

    public String getVal() {
        return val;
    }

    public int LevenshtienDistance(String compare, int pos1, int pos2) {
        if (pos1 == 0) return pos2;
        if (pos2 == 0) return pos1;
        
        if (val.charAt(pos1 - 1) == compare.charAt(pos2 - 1)) {
            return LevenshtienDistance(compare, pos1 - 1, pos2 - 1);
        }

        int min = Math.min(
            LevenshtienDistance(compare, pos1, pos2 - 1), //Insert
            LevenshtienDistance(compare, pos1 - 1, pos2) //Delete
        );
        min = Math.min(
            min,
            LevenshtienDistance(compare, pos1 - 1, pos2 - 1) //Replace
        );
        return 1 + min;
    }

    @Override
    public String getString() {
        return name + ": " + val;
    }

    @Override
    public String strVal() {
        return val;
    }
}

class CharValue extends Value {

    private char val;
    private final double ratio = Math.log(1.7);

    CharValue(String name, char val) {
        super(name);
        this.val = val;
    }

    public char getVal() {
        return val;
    }

    @Override
    public double distance(Value alt) {
        double difference = 0;
        try {
            difference = Math.abs(val - ((CharValue) alt).getVal());
        } catch (ClassCastException e) {
            return 10; //The types are different
        }
        return (Math.log(difference + 1) / ratio);
    }

    @Override
    public String getString() {
        return name + ": " + String.valueOf(val);
    }

    @Override
    public String strVal() {
        return String.valueOf(val);
    }
}

class DoubleValue extends Value {

    private double val;
    private final double ratio = Math.log10(84);

    DoubleValue(String name, double val) {
        super(name);
        this.val = val;
    }

    @Override
    public double distance(Value alt) {
        double difference = 0;
        try {
            difference = Math.abs(this.val - ((DoubleValue) alt).getVal());
        } catch (ClassCastException e) {
            return 10; //The types are different
        }
        return (Math.log10(difference + 1) / ratio);
    }

    public double getVal() {
        return val;
    }

    @Override
    public String getString() {
        return name + ": " + String.valueOf(val);
    }

    @Override
    public String strVal() {
        return String.valueOf(val);
    }
}

class LongValue extends Value {

    private long val;
    private final double ratio = Math.log10(84);

    LongValue(String name, long val) {
        super(name);
        this.val = val;
    }

    @Override
    public double distance(Value alt) {
        double difference = 0;
        try {
            difference = Math.abs(this.val - ((LongValue) alt).getVal());
        } catch (ClassCastException e) {
            return 10; //The types are different
        }
        return (Math.log10(difference + 1) / ratio);
    }

    public long getVal() {
        return val;
    }

    @Override
    public String getString() {
        return name + ": " + String.valueOf(val);
    }

    @Override
    public String strVal() {
        return String.valueOf(val);
    }
}

class FloatValue extends Value {

    private float val;
    private final double ratio = Math.log10(9);

    FloatValue(String name, float val) {
        super(name);
        this.val = val;
    }

    @Override
    public double distance(Value alt) {
        double difference = 0;
        try {
            difference = Math.abs(this.val - ((FloatValue) alt).getVal());
        } catch (ClassCastException e) {
            return 10; //The types are different
        }
        return Math.log10(difference + 1) / ratio;
    }

    public float getVal() {
        return val;
    }

    @Override
    public String getString() {
        return name + ": " + String.valueOf(val);
    }

    @Override
    public String strVal() {
        // TODO Auto-generated method stub
        return null;
    }
}

class ShortValue extends Value {

    private short val;
    private final double ratio = Math.log10(3);

    ShortValue(String name, short val) {
        super(name);
        this.val = val;
    }

    @Override
    public double distance(Value alt) {
        double difference = 0;
        try {
            difference = Math.abs(this.val - ((ShortValue) alt).getVal());
        } catch (ClassCastException e) {
            return 10; //The types are different
        }
        return Math.log10(difference + 1) / ratio;
    }

    public short getVal() {
        return val;
    }

    @Override
    public String getString() {
        return name + ": " + String.valueOf(val);
    }

    @Override
    public String strVal() {
        return String.valueOf(val);
    }
}

class ByteValue extends Value {

    private byte val;
    private final double ratio = Math.log10(1.7);

    ByteValue(String name, byte val) {
        super(name);
        this.val = val;
    }

    @Override
    public double distance(Value alt) {
        double difference = 0;
        try {
            difference = Math.abs(this.val - ((ByteValue) alt).getVal());
        } catch (ClassCastException e) {
            return 10; //The types are different
        }
        return Math.log10(difference + 1) / ratio;
    }

    public byte getVal() {
        return val;
    }

    @Override
    public String getString() {
        return name + ": " + String.valueOf(val);
    }

    @Override
    public String strVal() {
        return String.valueOf(val);
    }


}

class ArrayValue<T extends Value> extends Value {

    private T[] val;
    
    ArrayValue(String name, T[] val) {
        super(name);
        this.val = val;
    }

    public T[] getVal() {
        return val;
    }

    public int size() {
        return val.length;
    }

    public T at(int position) {
        return val[position];
    }

    @Override
    public double distance(Value alt) {
        ArrayValue<T> altArray;
        try {
            altArray = (ArrayValue<T>) alt;
        } catch (ClassCastException e) {
            return 10; //The types are different
        }

        double totalLength = 0;
        int minLength = altArray.size();
        int maxLength = val.length;

        if (minLength > maxLength) {
            int temp = maxLength;
            maxLength = minLength;
            minLength = temp;
        }

        for (int i = 0; i < minLength; i++) {
            totalLength += val[i].distance(altArray.at(i));
        }

        totalLength += (maxLength - minLength) * 10; //Max distance between empty and not empty is 10

        return totalLength / maxLength;
    }
    
    @Override
    public String getString() {
        return name + ": " + strVal();
    }

    @Override
    public String strVal() {
        String returnStr = "[";
        for (Value inst : val) {
            if (returnStr.indexOf("[") == returnStr.length() -1) returnStr += inst.strVal();
            else returnStr += ", " + inst.strVal();
        }
        return returnStr + "]";
    }
}

class ObjectValue extends Value {

    private ArrayList<Value> val;

    ObjectValue(String name, ArrayList<Value> val) {
        super(name);
        this.val = val;
    }

    @Override
    public double distance(Value alt) {
        
        ObjectValue altObject;
        try {
            altObject = (ObjectValue) alt;
        } catch (ClassCastException e) {
            return 10; //The types are different
        }

        if (!altObject.getName().equals(name)) {
            return 10; //Objects are not the same are the furthest distance away
        }

        if (altObject.size() == 0) {
            if (val.isEmpty()) return 0; //Both objects are null and therefore distance is zero
            else return 10; //alt is null and this is not null therefore distance is max
        }

        if (val.isEmpty()) return 10; //this is null and alt is not null therefore distance is max

        if (altObject.size() != val.size()) return 10; //The object has different parameters

        double totalDist = 0;
        for (int i = 0; i < val.size(); i++) {
            if (!val.get(i).getName().equals(altObject.get(i).getName())) {
                totalDist += 10;
                continue;
            }
            totalDist += val.get(i).distance(altObject.get(i));
        }

        return totalDist / val.size(); //Average of the distance between each element of the object
    }

    public Value get(int position) {
        return val.get(position);
    }
    
    public int size() {
        return val.size();
    }

    @Override
    public String getString() {
        return name + ": " + strVal();
    }

    @Override
    public String strVal() {
        if (val.isEmpty()) return "Null";
        String objStr = "";
        try {
            for (Value nextVal : val) {
                if (objStr.isEmpty()) objStr += nextVal.getString();
                else objStr += ", " + nextVal.getString();
            }
            return "{ " + objStr + " }";
        } catch (NullPointerException e) {
            System.out.println(objStr);
        }
        return "{ " + objStr + " }";
    }
}