package valoeghese.valoeghesesbe.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Nullable;

import valoeghese.valoeghesesbe.util.Console;
import valoeghese.valoeghesesbe.util.handlers.RegistryHandler;

/**
 * <b>static methods</b>
 * <br/><br/>
 * Helps with manipulating existing ZFG-data files from the directory ./
 */
public class ZfgHelper
{
	
	/**
	 * 
	 * METHODS DON'T WORK<br/><br/><br/>
	 * <b>instanced methods</b><br/><br/>
	 * Helps with manipulating existing ZFG-data configuration files from the directory ./Zoesteria/
	 */
	@Deprecated
	public static class ZfgConfigHelper
	{
		private final String ZfgConfigHelper$container;
		
		private final String rel_path;
		
		/**
		 * @param relative_path from ./Zoesteria/
		 * @param containerIn
		 */
		public ZfgConfigHelper(String relative_path, String containerIn)
		{
			this.ZfgConfigHelper$container = containerIn;
			this.rel_path = "./Zoesteria/" + relative_path;
		}
		
		/**
		 * @param relative_path
		 */
		public ZfgConfigHelper(String relative_path) { this(relative_path, "@Null"); }
		
		public void insertPair(String value, String containerIn, String key) throws IOException
		{
			ZfgHelper.insertPairToContainer(value, this.rel_path, containerIn, key);
		}
		public void insertPair(String value, String key) throws IOException, IllegalAccessException
		{
			if (this.ZfgConfigHelper$container.equals("@Null"))
			{
				throw new IllegalAccessException("Method insertPair(value, key) unapplicable to ZfgConfigHelper instance with no provided container");
			}
			this.insertPair(value, this.ZfgConfigHelper$container, key);
		};
	}
	
	/**
	 * 
	 * DOESN'T WORK
	 * 
	 * @param value
	 * @param fileIn
	 * @param containerIn
	 * @param key
	 * @throws IOException
	 */
	@Deprecated
	public static void insertPairToContainer(String value, String fileIn, String containerIn, String key) throws IOException
	{
		
		char[] charsIn = new char[Short.MAX_VALUE];
		
		FileReader reader = new FileReader(fileIn);
		
		reader.read(charsIn);
		reader.close();
		
		String parser = new String();
		byte mode = 0;
		
		FileWriter writer = new FileWriter(fileIn);
		
		writer.write("");
		
		for (char c : charsIn)
		{
			switch (c)
			{
			case ' ':
				writer.append(" ");
				switch (mode)
				{
				case 0:
					if (parser.trim().equals("container")) mode = 1;
					writer.append(parser.trim());
					parser = new String();
				default:
					break;
				}
				break;
			case '(':
				writer.append("(");
				switch (mode)
				{
				case 1:
					if (parser.trim().equals(containerIn))
					{
						mode = 2;
					} else {
						mode = 3;
					}
					writer.append(parser.trim());
					parser = new String();
				default:
					break;
				}
				break;
			case '{':
				writer.append("{");
				switch (mode)
				{
				case 2:
					mode = 4;
					
					writer.append(parser.trim());
					writer.append("\r\n    " + key + " = " + value + ";");
					
					parser = new String();
				default:
					break;
				}
				break;
			case '}':
				writer.append("}");
				if (mode == 3) mode = 0;
				break;
			default:
				switch (mode)
				{
				case 4:
					if (!Character.isWhitespace(c) || c == '\n' || c == '\r') writer.append(c);
				default:
					if ( !Character.isWhitespace(c) ) parser += Character.toString(c);
					break;
				}
			}
		}
		
		writer.close();
	}
	
	public static void overrideWriteFile(String fileIn, String...newDataLines) throws IOException
	{
		FileWriter writer = new FileWriter(fileIn);
		writer.write("");
		
		for (String s : newDataLines)
		{
			writer.append(s + "\r\n");
		}
		
		writer.close();
	}
	
	public static Map<String, String> getMapOf(File fileIn, String containerIn) throws IOException { return getMapOf(fileIn, containerIn, false); }
	public static Map<String, String> getMapOf(File fileIn, String containerIn, boolean isTest) throws IOException
	{
		FileReader reader = new FileReader(fileIn);
		char[] chars = new char[Short.MAX_VALUE];
		ConfigHandler handler;
		Map<String, String> mapOut;
		
		//Read
		reader.read(chars);
		reader.close();
		
		handler = new ConfigHandler(chars, isTest);
		
		mapOut = handler.getContainer(containerIn);
		
		return mapOut;
	}
}
