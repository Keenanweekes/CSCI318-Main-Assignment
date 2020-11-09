import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class generateMain {
    public static void main(String[] args) {
        String orgClassName = args[0];
        int runTimes = Integer.parseInt(args[1]);
        ART art = new ART(orgClassName);
        for (int i = 0; i < runTimes; i++) {
            ObjectValue newVal = art.create();
            String response = GenerateClass.generate(newVal);
            System.out.println(newVal.getString() + " - " + response);
            art.addObj(response, newVal);
        }
    }
}
