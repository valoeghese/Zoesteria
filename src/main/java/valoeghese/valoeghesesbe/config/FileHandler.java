package valoeghese.valoeghesesbe.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

import com.google.common.io.Files;

public class FileHandler
{
	
	private final File homeFolder;
	private final File file;
	
	private final String fileLocation;
	
	public FileHandler(String file, String...defaults)
	{
		
		this.homeFolder = new File("./Zoesteria");
		this.fileLocation = "./Zoesteria/" + file;
		
		this.file = new File(this.fileLocation);
		
		if ( this.homeFolder.mkdirs() || this.homeFolder.exists() )
		{
			
			try {
				
				if ( this.file.createNewFile() )
				{
					
					FileWriter tempWriter = new FileWriter(this.file);
					
					writeToFile(tempWriter, defaults);
					
					tempWriter.close();
					
				}
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
	}
	
	public void writeToFile(FileWriter writer, String[] strings)
	{
		
		try
		{
			
			for (String string: strings)
			{

				writer.write(string + "\r\n");

			}

			writer.flush();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public File getFile()
	{
		
		return this.file;
		
	}

}
