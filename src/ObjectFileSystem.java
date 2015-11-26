/*
*可用function:
*void writeObjectToFile(Object obj, String fileName)
*Object readObjectFromFile(String fileName)
*使用readObjectFromFile(fileName)時需要強制轉型
*/
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.ClassNotFoundException;

public abstract class ObjectFileSystem
{
	public static void writeObjectToFile(Object obj, String fileName)
	{
		try
		{
			File file = new File(fileName);
			
			//write object file
			ObjectOutputStream objOutputStream
				= new ObjectOutputStream(
					new FileOutputStream(file));
			objOutputStream.writeObject(obj);
			objOutputStream.flush();
			objOutputStream.close();
		} catch(FileNotFoundException e)
		{
			System.out.println("file not found!");
		} catch(IOException e)
		{
			System.out.println("IOException!");
		}
	}
	public static Object readObjectFromFile(String fileName)
	{//回傳Object型態, 需要強制轉型
		try
		{
			File file = new File(fileName);
			
			//read object file
			if(isFileExists(file))
			{
				ObjectInputStream objInputStream
					= new ObjectInputStream(
						new FileInputStream(file));
				Object obj = objInputStream.readObject();
				objInputStream.close();
				
				return obj;
			}
			else
				System.out.println("File not exists!");
		} catch(FileNotFoundException e)
		{
			System.out.println("file not found!");
		} catch(IOException e)
		{
			System.out.println("IOException!");
		} catch(ClassNotFoundException e)
		{
			System.out.println("Class not found!");
		}
		return null;
	}
	private static boolean isFileExists(File file)
	{
		if(file != null && file.isFile())
			//exists and is a file
			return true;
		else
			return false;
	}
}