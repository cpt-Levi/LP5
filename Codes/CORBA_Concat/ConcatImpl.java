import ConcatModule.ConcatPOA;
import java.lang.String;

public class ConcatImpl extends ConcatPOA
{
	ConcatImpl()
	{
		super();
		System.out.print("Concat object was created");
	};
	
	public String concat_strings(String str1, String str2)
	{
		String result = str1 + str2;
		return (("Server sends " + result));
	}
}
