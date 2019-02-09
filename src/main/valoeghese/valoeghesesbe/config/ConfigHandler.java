package valoeghese.valoeghesesbe.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import valoeghese.valoeghesesbe.util.Console;
import valoeghese.valoeghesesbe.util.exception.SyntaxException;

public class ConfigHandler
{
	
	private Map<String, Map<String, String>> containers = new HashMap<String, Map<String, String>>();
	
	private final String s_curcn7_typename = "def_ConfigHandler_type";
	
	//Mode of parser.
	private byte b_d_m03e;
	
	public ConfigHandler(char[] data, boolean test)
	{
		//Container stuff. 1st index of each HashMap is the type, which will be called def_ConfigHandler_type.
		String s_curn4m = new String();
		String s_curv4r = new String();
		String s_cur57r = new String();
		
		this.b_d_m03e = 0;
		
		Map<String, String> hs_curcn7 = new HashMap<String, String>();
		
		//parse char data into containers. 4 is default container mode; 0 is default external mode.
		for (char c : data)
		{
			
			switch(c)
			{
				
			case ' ':
				
				if (test) {
					System.out.println("SPACE" + Byte.toString(this.b_d_m03e));
				}
				switch(this.b_d_m03e)
				{
				
				//checking for container
				case 0:
					
					if (s_cur57r.trim().equals("container"))
					{
						
						s_curn4m = new String();
						
						this.b_d_m03e = 1;
						
					} else {
						
						if (test) {
							Console.WriteChars(s_cur57r);
						}
						
					}
					s_cur57r = new String();
					
					break;
				default:
					
					//TODO
					break;
				}
				break;
				
			case '=':
				
				if (test) {
					System.out.println("=" + Byte.toString(this.b_d_m03e));
				}
				switch(this.b_d_m03e)
				{
				
				//init vars in container
				case 4:
					
					s_curv4r = s_cur57r.trim();
					
					this.b_d_m03e = 5;
					
					s_cur57r = new String();
					
					break;
					
				default:
					
					//TODO
					break;
				}
				break;
				
			case ';':
				
				if (test) {
					System.out.println(";" + Byte.toString(this.b_d_m03e));
				}
				switch(this.b_d_m03e)
				{
				
				//set var in HashMap container
				
				case 5:
					
					hs_curcn7.put(s_curv4r, s_cur57r.trim());
					
					//back into var get mode
					this.b_d_m03e = 4;
					
					s_cur57r = new String();
					
					break;
				case 0:
					
					s_cur57r = new String();
					
					break;
				default:
					
					throw new SyntaxException("Unexpected \";\" found in:\n-------------------------\n" + new String(data) + "\n-------------------------\n");
					
				}
				break;
				
			case '{':
				
				if (test) {
					System.out.println("{" + Byte.toString(this.b_d_m03e));
				}
				switch(this.b_d_m03e)
				{
				
				//open container
				case 3:
					
					hs_curcn7 = new HashMap<String, String>();
					this.b_d_m03e = 4;
					s_cur57r = new String();
					break;
					
				default:
					
					throw new SyntaxException("Unexpected \"{\" found in:\n-------------------------\n" + new String(data) + "\n-------------------------\n");
				}
				break;
				
			case '}':
				
				if (test) {
					System.out.println("}" + Byte.toString(this.b_d_m03e));
				}
				switch(this.b_d_m03e)
				{
				
				//close container
				case 4:
					
					this.containers.put(s_curn4m, hs_curcn7);
					if (test) { Console.WriteLine(hs_curcn7.get("Saltpeter")); }
					s_curn4m = new String();
					s_cur57r = new String();
					break;
					
				case 5:
					
					throw new SyntaxException("Unexpected \"}\". Expected \";\". Found in:\n-------------------------\n" + new String(data) + "\n-------------------------\n");
					
				default:
					
					throw new SyntaxException("Unexpected \"{\" found in:\n-------------------------\n" + new String(data) + "\n-------------------------\n");
					
				}
				break;
				
				
			case '(':
				
				if (test) {
					System.out.println("(" + Byte.toString(this.b_d_m03e));
				}
				switch(this.b_d_m03e)
				{
				
				//name of container
				case 1:
					
					s_curn4m = s_cur57r.trim();
					
					this.b_d_m03e = 2;
					s_cur57r = new String();
					break;
					
				default:
					
					//TODO
					break;
				}
				break;
				
			case ')':
				
				if (test) {
					System.out.println(")" + Byte.toString(this.b_d_m03e));
				}
				switch(this.b_d_m03e)
				{
				
				//closing datatype
				case 2:
					
					hs_curcn7.put(this.s_curcn7_typename, s_cur57r.trim());
					
					this.b_d_m03e = 3;
					s_cur57r = new String();
					break;
					
				default:
					
					//TODO
					break;
				}
				break;
				
			default:
				
				if ( !(c == '\r' || c == '\n') )
				{
					
					s_cur57r += Character.toString(c);
					
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
		
		tempMap.put("Type", tempContainerMap.get(this.s_curcn7_typename));
		tempMap.put("Value", tempContainerMap.get(item));
		
		return tempMap;
		
	}
	
	public Map<String, Map<String, String>> getContainerList()
	{
		
		return this.containers;
		
	}

}



