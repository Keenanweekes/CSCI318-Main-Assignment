
class Hello{

	private int number;
	private String name;


	public Hello(int number, String name) throws Exception {

		this.number = number;
		this.name = name;

		if(number >= 0){
			throw new Exception("This is a text exception");
		}


	}

}

class Thing{

	private int number2;
	private String name2;
	private double number3;

	public Thing(int number2, String name2, double number3) {

		this.number2 = number2;
		this.name2 = name2;
		this.number3 = number3;

		if(name2.equals("Steve")){


		}

	}


}