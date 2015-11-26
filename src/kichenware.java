import java.io.Serializable;


public class kichenware implements Serializable
{
	private static final long serialVersionUID = 1L;
	public int id;
	public String name;
	public int ability;
	
	public kichenware()
	{}
	public kichenware(int id, String name, int ability)
	{
		this.id=id;
		this.name=name;
		this.ability=ability;
	}
	public int getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	public int getAbility()
	{
		return ability;
	}
}