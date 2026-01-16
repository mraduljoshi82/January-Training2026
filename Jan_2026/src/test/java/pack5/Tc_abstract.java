package pack5;

abstract class shape{
	abstract void draw();
}

class rectangle extends shape{
	void draw() {
		System.out.println("Drawing the rectangle");
	}
}








public class Tc_abstract {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		rectangle obj = new rectangle();
		obj.draw();
		

	}

}
