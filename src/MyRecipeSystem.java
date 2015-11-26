import java.io.Serializable;


public class MyRecipeSystem {
	

	private static Ingredient[] ingredients;
	private static Seasoning[] seasonings;
	private MyFood[] recipes;
	private String dataLocation;
	public MyRecipeSystem(String dataLocation, RecipeSystem recipeSystem) {
		
		//«Øºc­¹§÷ ½Õ¨ý®Æ ­¹ÃÐ	
		
		ingredients = (new ReadFile()).readIngredient();
		seasonings = (new ReadFile()).readSeasoning();
		
		setDataLocation(dataLocation);
		
		
		recipes = (new ReadFile().readMyRecipe(getDataLocation(), ingredients, seasonings));
		
		
		for( MyFood temp : recipes)
			temp.score(recipeSystem.getRecipe(temp.getRecipeId()));
		
		
		 
	}
	public void setDataLocation(String dataLocation) {
		
		this.dataLocation = dataLocation;
	}
	
	public String getDataLocation() {
		
		return this.dataLocation;
	}
	
	public void printIngredient() {
		for(Ingredient temp : ingredients)
			System.out.println(temp);
	}
	
	
	
	public void printSeasoning() {
		for(Seasoning temp : seasonings)
			System.out.println(temp);
	}
	public void printRecipe() {
		for(MyFood temp : recipes) {
			System.out.println(temp);
			
		}
	}
	
	
	
	public void printRecipe(int indexOfRecipe) {
		System.out.println(this.recipes[indexOfRecipe]);
	}
	
	public MyFood getRecipe(int indexOfRecipe) {
		return recipes[indexOfRecipe];
	}
	public int recipeNumber()
	{
		return recipes.length;
	}
	public void writeData() {
		WriteFile write = new WriteFile();
		write.writeRecipe(this);
	}
	
	public void addFood(MyFood newFood) {
		MyFood[] newRecipe = new MyFood[this.recipes.length+1];
		int i = 0;
		for(i = 0; i < this.recipes.length; i++)
			newRecipe[i] = this.recipes[i];
		newRecipe[i] = newFood;
		this.recipes = newRecipe;
	}
	
	

}
