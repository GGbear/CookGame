import java.io.Serializable;


public class Record implements Serializable
{
	private static final long serialVersionUID = 1L;
	int currentStage;

	public Record()
	{
		currentStage=1;
	}
	public void setCurrentStage(int newStage)
	{
		currentStage = newStage;
	}
	public int getCurrentStage()
	{
		return currentStage;
	}
	public void save(String fileName)
	{
		ObjectFileSystem.writeObjectToFile(this, fileName);
	}
	public static Record load(String fileName)
	{
		return (Record) ObjectFileSystem.readObjectFromFile(fileName);
	}
}
