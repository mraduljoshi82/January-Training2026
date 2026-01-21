package pack7;

import java.io.FileInputStream;
import java.io.IOException;
 
public class TC_file_o {
 
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis=new FileInputStream("C:\\Users\\mradul\\git\\CoforgeProject\\Jan2026\\src\\test\\java\\pac1\\sampleinput.txt");
		
		int data;
		while((data=fis.read())!=-1)
		{
			System.out.println((char)data);
		}
		
		fis.close();
		
 
	}
 
}
