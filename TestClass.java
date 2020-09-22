class TestClass {
    private int age;
    private String name;
    private double balance;
    private Pet pet;

    TestClass(int age, String name, double balance, Pet pet) {
        this.age = age;
        this.name = name;
        this.balance = balance;
        this.pet = pet;
    }
}

class Pet {
    private String breed;
    private double weight;
    private int[] limbSizes;

    Pet(String breed, double weight, int[] limbSizes) {
        this.breed = breed;
        this.weight = weight;
        this.limbSizes = limbSizes;
    }
}