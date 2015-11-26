
public class Recipe extends Food {
	private int id;
	private String name;
	
	
	public Recipe(int myId, String myName, int myCookingTime, String myImageLocation, Ingredient[]  myIngredients, Seasoning[] mySeasonings, int[] mySeaosoningAmount) {
		super(myCookingTime, myImageLocation, myIngredients, mySeasonings, mySeaosoningAmount);
		setId(myId);
		setName(myName);
		
		
	}
	public int getId() {
		
		return this.id;
	}
	public String getName() {
		
		return this.name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	
	public String toString() {
		
		return String.format("Name: %s\n%s", getName(), super.toString());
		 
	}
}
