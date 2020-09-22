import java.util.ArrayList;

public class ART {
    private ArrayList<ObjectValue> objects;
    private Generate gen;

    public ART(String className) {
        objects = new ArrayList<>();
        gen = new Generate(className);
    }

    public ObjectValue create() {
        if (objects.isEmpty()) return gen.Gen();
        double[] distances = {0, 0, 0};
        ObjectValue[] newVals = {gen.Gen(), gen.Gen(), gen.Gen()};
        for (ObjectValue val : objects) {
            for (int i = 0; i < 3; i++) {
                double newDist = val.distance(newVals[i]);
                if (distances[i] < newDist) distances[i] = newDist;
            }
        }
        int smallestIndex = 0;
        double smallestDist = 12;
        for (int i = 0; i < 3; i++) {
            if (distances[i] < smallestDist) {
                smallestIndex = i;
                smallestDist = distances[i];
            }
        }
        return newVals[smallestIndex];
    }

    public void addObj(String response, ObjectValue newVal) {
        if (response.equals("Success")) {
            objects.add(newVal);
        }
    }
}
