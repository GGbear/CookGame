import java.io.Serializable;


public class Player implements Serializable
{
	private static final long serialVersionUID = 1L;
	public int id;
	public String name;
	public kichenware[] cuttingTool;
	public kichenware[] cookingTool;
	public kichenware equipment[];
	public int playerAbility=equipment[0].ability+equipment[1].ability;
	
	
	
	public Player(kichenware[] cuttingTool,kichenware[] cookingTool,kichenware[] equipment)
	{
		//Player temp =new Player();
		//temp=(Player)ObjectFileSystem.readObjectFromFile("data\\"+id+".obj");
		this.name="阿風";
		this.id=0;
		this.cuttingTool=cuttingTool;
		this.cookingTool=cookingTool;
		this.equipment=equipment;
		this.playerAbility=equipment[0].ability+equipment[1].ability;
		/*this.playerAbility=temp.playerAbility;
		for (int i=0;i<cookingTool.length;i++)
			this.cookingTool[i]=temp.cookingTool[i];
		for (int i=0;i<cuttingTool.length;i++)
			this.cuttingTool[i]=temp.cuttingTool[i];
		for (int i=0;i<equipment.length;i++)
			this.equipment[i]=temp.cuttingTool[i];
		for (int i=0;i<myFoodOwned.length;i++)
			this.myFoodOwned[i]=temp.myFoodOwned[i];*/
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getId()
	{
		return id;
	}
	
	public int getPlayerAbility()
	{
		return playerAbility;
	}
	
	public kichenware[] getCuttingTool()
	{
		return cuttingTool;
	}
	
	public kichenware[] getCookingTool()
	{
		return cookingTool;
	}
	
	/*public myFood[] getMyFood()
	{
		return myFood;
	}*/
	
	public void equip (kichenware cooker)
	{
		if (cooker.getId()%10==1)//是刀具
			equipment[0]=cooker;
		if (cooker.getId()%10==2)//是鍋具
			equipment[1]=cooker;
		//算裝備能力值
		int sum=0;
		for(int i=0;i<equipment.length;i++)
			sum+=equipment[i].getAbility();
		playerAbility=sum;
	}
	
	public void addKichenware(kichenware cooker)
	{
		if (cooker.getId()%10==1)//是刀具
		{
			int i=0;
			while(i<cookingTool.length) i++;
			cookingTool[i]=cooker;
		}
		if (cooker.getId()%10==2)//是鍋具
		{
			int i=0;
			while(i<cuttingTool.length) i++;
			cuttingTool[i]=cooker;
		}
	}
	
	
}
