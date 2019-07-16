package valoeghese.valoeghesesbe.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import valoeghese.valoeghesesbe.util.Console;
import valoeghese.valoeghesesbe.util.exception.SyntaxException;

public class ConfigHandler
{
	
	private Map<String, Map<String, String>> containers = new HashMap<String, Map<String, String>>();
	
	private final String typename = "def_ConfigHandler_type";
	
	//Mode of parser.
	private byte mode;
	
	public ConfigHandler(char[] data, boolean test)
	{
		//Container stuff. 1st index of each HashMap is the type, which will be called def_ConfigHandler_type.
		String containerString = new String();
		String dataString = new String();
		//String currentString = new String();
		
		StringBuilder sb = new StringBuilder();
		
		this.mode = 0;
		
		Map<String, String> tempData = new HashMap<String, String>();
		
		//parse char data into containers. 4 is default container mode; 0 is default external mode.
		for (char c : data)
		{
			
			switch(c)
			{
				
			case ' ':
				
				if (test) {
					System.out.println("SPACE" + Byte.toString(this.mode));
				}
				switch(this.mode)
				{
				
				//checking for container
				case 0:
					
					if (sb.toString().trim().equals("container"))
					{
						
						containerString = new String();
						
						this.mode = 1;
						
					} else {
						
						if (test) {
							Console.WriteChars(sb.toString());
						}
						
					}
					
					sb = new StringBuilder();
					
					break;
				default:
					
					//TODO
					break;
				}
				break;
				
			case '=':
				
				if (test) {
					System.out.println("=" + Byte.toString(this.mode));
				}
				switch(this.mode)
				{
				
				//init vars in container
				case 4:
					
					dataString = sb.toString().trim();
					
					this.mode = 5;
					
					sb = new StringBuilder();
					
					break;
					
				default:
					
					//TODO
					break;
				}
				break;
				
			case ';':
				
				if (test) {
					System.out.println(";" + Byte.toString(this.mode));
				}
				switch(this.mode)
				{
				
				//set var in HashMap container
				
				case 5:
					
					tempData.put(dataString, sb.toString().trim());
					
					//back into var get mode
					this.mode = 4;
					
					sb = new StringBuilder();
					
					break;
				case 0:
					
					sb = new StringBuilder();
					
					break;
				default:
					
					throw new SyntaxException("Unexpected \";\" found in:\n-------------------------\n" + new String(data) + "\n-------------------------\n");
					
				}
				break;
				
			case '{':
				
				if (test) {
					System.out.println("{" + Byte.toString(this.mode));
				}
				switch(this.mode)
				{
				
				//open container
				case 3:
					
					tempData = new HashMap<String, String>();
					this.mode = 4;
					sb = new StringBuilder();
					break;
					
				default:
					
					throw new SyntaxException("Unexpected \"{\" found in:\n-------------------------\n" + new String(data) + "\n-------------------------\n");
				}
				break;
				
			case '}':
				
				if (test) {
					System.out.println("}" + Byte.toString(this.mode));
				}
				switch(this.mode)
				{
				
				//close container
				case 4:
					
					this.containers.put(containerString, tempData);
					if (test) { Console.WriteLine(tempData.get("Saltpeter")); }
					containerString = new String();
					sb = new StringBuilder();
					break;
					
				case 5:
					
					throw new SyntaxException("Unexpected \"}\". Expected \";\". Found in:\n-------------------------\n" + new String(data) + "\n-------------------------\n");
					
				default:
					
					throw new SyntaxException("Unexpected \"{\" found in:\n-------------------------\n" + new String(data) + "\n-------------------------\n");
					
				}
				break;
				
				
			case '(':
				
				if (test) {
					System.out.println("(" + Byte.toString(this.mode));
				}
				switch(this.mode)
				{
				
				//name of container
				case 1:
					
					containerString = sb.toString().trim();
					
					this.mode = 2;
					sb = new StringBuilder();
					break;
					
				default:
					
					//TODO
					break;
				}
				break;
				
			case ')':
				
				if (test) {
					System.out.println(")" + Byte.toString(this.mode));
				}
				switch(this.mode)
				{
				
				//closing datatype
				case 2:
					
					tempData.put(this.typename, sb.toString().trim());
					
					this.mode = 3;
					sb = new StringBuilder();
					break;
					
				default:
					
					//TODO
					break;
				}
				break;
				
			default:
				
				if ( !(c == '\r' || c == '\n') )
				{
					sb.append(c);
				}
				break;
				
			}
			
		}
		
	}
	
	public Map<String, String> getContainer(String name)
	{
		
		return (Map<String, String>)this.containers.get(name);
		
	}
	
	public Map<String, String> getItemFromContainer(String containerName, String item)
	{
		
		Map<String, String> tempMap = new HashMap<>();
		Map<String, String> tempContainerMap = this.containers.get(containerName);
		
		tempMap.put("Type", tempContainerMap.get(this.typename));
		tempMap.put("Value", tempContainerMap.get(item));
		
		return tempMap;
		
	}
	
	public Map<String, Map<String, String>> getContainerList()
	{
		
		return this.containers;
		
	}

}



