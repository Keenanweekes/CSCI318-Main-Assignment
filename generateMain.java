import java.util.ArrayList;

public class generateMain {
    public static void main(String[] args) {
        int runTimes = Integer.parseInt(args[0]);
        ArrayList<Value> object = new ArrayList<>();
        IntValue arg0 = new IntValue("age", 11);
        object.add(arg0);
        StringValue arg1 = new StringValue("name", "John");
        object.add(arg1);
        DoubleValue arg2 = new DoubleValue("balance", 87.36);
        object.add(arg2);

        ArrayList<Value> petArr = new ArrayList<>();
        StringValue arg11 = new StringValue("fName", "John");
        petArr.add(arg11);
        DoubleValue arg12 = new DoubleValue("weight", 87.36);
        petArr.add(arg12);
        String arrName = "limbSizes";
        IntValue[] arr = { new IntValue(arrName, 1), new IntValue(arrName, 4), new IntValue(arrName, 3),
                new IntValue(arrName, 3) };
        ArrayValue<IntValue> arg13 = new ArrayValue<>(arrName, arr);
        petArr.add(arg13);
        ObjectValue pet = new ObjectValue("Pet", petArr);

        object.add(pet);
        ObjectValue person = new ObjectValue("person", object);

        System.out.println(GenerateClass.generate(person));
    }
}
