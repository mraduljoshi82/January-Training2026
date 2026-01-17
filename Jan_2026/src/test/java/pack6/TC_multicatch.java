package pack6;

public class TC_multicatch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String str="hello";
			int a=str.length();
			int b=42/a;
			int c[]= {1};
			c[42]=99;
			
		}
		catch(ArithmeticException e) {
			System.out.println("Divide by zero:"+e.getMessage());
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("ArrayoutofboundsException"+e.getMessage());
		}
		finally {
			System.out.println("Finally");
		}
		System.out.println();

	}

}
