package pack4;

class Base{
	public void baseMethod() {
		System.out.println("base method");
	}
}
class Derived extends Base{
	public void derivedmethod() {
		System.out.println("Derived Method");
	}
}



public class TC_inheritance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Derived derived=new Derived();
		derived.baseMethod();
		derived.derivedmethod();
		

	}

}
