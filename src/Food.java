
public class Food {
	private Ingredient[]  ingredients;
	private Seasoning[] seasonings;
	private int[] seaosoningAmount;
	private int cookingTime;
	private String imageLocation;
	public Food(int myCookingTime, String myImageLocation, Ingredient[]  myIngredients, Seasoning[] mySeasonings, int[] mySeaosoningAmount){
		this.ingredients = myIngredients ;
		this.seasonings = mySeasonings;
		this.seaosoningAmount = mySeaosoningAmount;
		setCookingTime(myCookingTime);
		setImageLocation(myImageLocation);
	}
	
	public int getCookingTime() {
		
		return this.cookingTime;
	}
	public Ingredient[] getIngredient() {
		
		return this.ingredients;
	}
	
	public Seasoning[] getSeasoning() {
		
		return this.seasonings;
	}
	
	public int[] getSeasoningAmount() {
		
		return this.seaosoningAmount;
	}	
	
	
	
	
	public String getImageLocation() {
		
		return this.imageLocation;
	}
	public void setCookingTime(int cookingTime) {
		this.cookingTime = cookingTime;
	}
	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}
	public int getIngredientsNumber() {
		return this.ingredients.length;
	}
	public int getSeasoningsNumber() {
		return this.seasonings.length;
	}
	public int getSeasoningTotal() {
		 
		int totalSeaosoningAmount = 0;
		for(int temp : this.seaosoningAmount)
			totalSeaosoningAmount += temp;
		
		return totalSeaosoningAmount;
	}
	
	public String ingredientToString() {
		String out = new String();
		out += this.ingredients.length;
		for(Ingredient temp : ingredients)
			out += " " + temp.getId();
		return out;
		
	}
	
	
	public String seasoningToString() {
		String out = new String();
		out += this.seasonings.length + "\r\n";
		for(int i = 0; i < this.getSeasoningsNumber(); i++)
			out += this.seasonings[i].getId() +  " " + this.seaosoningAmount[i] + "\r\n";
		return out;
		
	}
	
	public String toString() {
		
		
		
		String output ;
		output = "Cooking Time: " + getCookingTime();
		
		output += "\nIngredient: ";
		for(Ingredient temp : ingredients)
			output += (temp + " ");
		
		output += "\nSeasoning: ";
		
		for(int count = 0; count < seasonings.length; count++)
			output += (seasonings[count] + "," + seaosoningAmount[count] + " ");
		
		output += "\n";
		
		return output;
		
	}
}
