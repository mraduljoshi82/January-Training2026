package pack5;
import java.util.regex.*;
public class TC_regular_expression {
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String input ="123456789555555555";
				String regex="\\d+";
		
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(input);
		boolean result=m.matches();
		if(result==true)
		{
			System.out.println("Valid number");
		}
				
		else
		{
			System.out.println("Invalid Number");
		}
		
		
		String input1="today is monday and tomorrow is tuesday and";
		Pattern pattern=Pattern.compile("and");
		Matcher match=pattern.matcher(input1);
		System.out.println(match.matches());
		
		while(match.find())
				{
			System.out.println(match.group()+":"+match.start()+":"+match.end());
				}
		
		
		
		
		
	}
 
}
