
public class RecipeSystem {
	private static Ingredient[] ingredients;
	private static Seasoning[] seasonings;
	private Recipe[] recipes;
	private String dataLocation;
	private String text;
	public RecipeSystem(String dataLocation) {
		
		//«Øºc­¹§÷ ½Õ¨ý®Æ ­¹ÃÐ		
		ingredients = (new ReadFile()).readIngredient();
		seasonings = (new ReadFile()).readSeasoning();
		
		setDataLocation(dataLocation);
		
		recipes = (new ReadFile().readRecipe(getDataLocation(), ingredients, seasonings));
		 
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
		for(Recipe temp : recipes)
			System.out.println(temp);
	}
	
	public void printRecipe(int indexOfRecipe) {
		System.out.println(this.recipes[indexOfRecipe]);
	}
	
	public Recipe getRecipe(int indexOfRecipe) {
		try
		{
			
			return recipes[indexOfRecipe];
		}catch(Exception e)
		{
			return recipes[recipes.length-1];
		}
	}
	public int recipeNumber()
	{
		return recipes.length;
	}
	
}
