package valoeghese.valoeghesesbe.util;

public class Console
{
	
	/**
	 * Prints to the console the string made by the char array given as a parameter
	 * 
	 * @param chars
	 */
	public static void WriteChars(char...chars)
	{
		
		for (char character : chars)
		{
			
			System.out.print(character);
			
		}
		
		System.out.println();
		
	}
	
	/**
	 * Prints to the console the chars contained in the string given as a parameter
	 * 
	 * @param charsString
	 */
	public static void WriteChars(String charsString)
	{
		
		for (char character : charsString.toCharArray())
		{
			
			System.out.println(character);
			
		}
		
	}
	
	/**
	 * Writes a line of strings to the console.
	 * 
	 * @param strings
	 */
	public static void WriteLine(String...strings)
	{
		
		for (String string : strings)
		{
			
			System.out.print(string);
			
		}
		
		System.out.println();
		
	}
	
	/**
	 * Writes a line of integers to the console, separated by a space.
	 * 
	 * @param ints
	 */
	public static void WriteLine(int...ints)
	{
		
		for (int integer : ints)
		{
			
			System.out.print(integer);
			System.out.print(" ");
			
		}
		
		System.out.println();
		
	}

}
