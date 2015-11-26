public class MyFood extends Food {
	private int recipeId;
	private double grade;
	
	public MyFood(int myId, int myCookingTime, String myImageLocation,
			Ingredient[] myIngredients, Seasoning[] mySeasonings,
			int[] mySeasoningAmount) {
		super(myCookingTime, myImageLocation, myIngredients, mySeasonings,
				mySeasoningAmount);
		// TODO Auto-generated constructor stub
		setGrade(0);
		setRecipeId(myId);
		//image
	}
	public MyFood(int myId, int myCookingTime,
			Ingredient[] myIngredients, Seasoning[] mySeasonings,
			int[] mySeasoningAmount) {
		super(myCookingTime, " ", myIngredients, mySeasonings,
				mySeasoningAmount);
		// TODO Auto-generated constructor stub
		ReadFile read = new ReadFile();
		String imageLocation = read.readImageLocation(myId);
		this.setImageLocation(imageLocation);
		
		setGrade(0);
		setRecipeId(myId);
		//image
	}
	
	
	
	public void score(Recipe standardRecipe)
	{
		
		double ingredientScore = 20, seasoningScore = 15, score=0, timeScore = 0, seasoningChooseScore = 0;
		
		double cookingTime = this.getCookingTime();
		
		Ingredient[] myIngredients = this.getIngredient();
		Seasoning[] mySeasonings = this.getSeasoning();
		int[] mySeasoningAmount = this.getSeasoningAmount();
		int mySeasoningTotalAmount = 0;
		int falseSeasoningTotalAmount=0;
		
		Ingredient[] standardRecipeIngredients = standardRecipe.getIngredient();
		Seasoning[] standardRecipeSeasonings = standardRecipe.getSeasoning();
		int[] standardRecipeSeaosoningAmount = standardRecipe.getSeasoningAmount();
		int standardSeaosoningTotalAmount = 0;
		
		int i = 0;
		int j = 0;
		for(i = 0; i < myIngredients.length && ingredientScore > 0; i++) {
			for(j = 0; j < standardRecipeIngredients.length; j++) {
				if(myIngredients[i].getId() == standardRecipeIngredients[j].getId())
					break;
			}
			if(j == standardRecipeIngredients.length)
				ingredientScore -= 20 / standardRecipeIngredients.length;
		}
		
		
		for(i = 0; i < mySeasonings.length && seasoningScore > 0; i++) {
			for(j = 0; j < standardRecipeSeasonings.length; j++) {
				if(mySeasonings[i].getId() == standardRecipeSeasonings[j].getId()) {
					mySeasoningTotalAmount += mySeasoningAmount[i];
					standardSeaosoningTotalAmount += standardRecipeSeaosoningAmount[j];
					break;
				}
			}
			
			if(j == standardRecipeSeasonings.length)
			{
				seasoningScore -= 15 / standardRecipeSeasonings.length;
				falseSeasoningTotalAmount+=mySeasoningAmount[i];
			}
		}
		
		
		
		seasoningChooseScore *= seasoningScore / 15;
		seasoningChooseScore = 10 - 0.08 * Math.abs(mySeasoningTotalAmount - standardSeaosoningTotalAmount)-0.08*falseSeasoningTotalAmount;
		
		
		
		timeScore = 15 - 0.75 * Math.abs(standardRecipe.getCookingTime()-cookingTime);
		timeScore = (timeScore > 0 ? timeScore : 0);
		
		
		System.out.println(ingredientScore + "  " + seasoningScore + "  " + seasoningChooseScore + "  " + timeScore+"  "+falseSeasoningTotalAmount);
		
		score = score + ingredientScore + seasoningScore + seasoningChooseScore + timeScore;
		
		
		/*
		ingredientChoose = Math.abs(ingredientChoose - standardRecipe.getIngredientsNumber());
		seasoningChoose = Math.abs(seasoningChoose - standardRecipe.getSeasoningsNumber());
		
		
		ingredientScore = 20 * (ingredientChoose == 0 ? standardRecipe.getIngredientsNumber() : ingredientChoose) / standardRecipe.getIngredientsNumber();
		seasoningChooseScore = 15 * (seasoningChoose == 0 ? standardRecipe.getSeasoningsNumber() : ingredientChoose) / standardRecipe.getSeasoningsNumber();
		
		int standardSeasoningAmount = standardRecipe.getSeasoningAmount();
		
		
		
		double amount = Math.abs(standardSeasoningAmount - seasoningChooseAmount); 
		amount = Math.pow(1.5, amount);
		
		
		seasoningScore=10 - ( 1 - 0.1 * standardRecipe.getSeasoningsNumber()) * (amount == 1 ? 0 : amount);//多或少X個單位
		timeScore=15-1.5*(standardRecipe.getCookingTime()-cookingTime);
		
		score=score+ingredientScore+seasoningScore+seasoningChooseScore+timeScore;//indScore(食材數量)、seaScore(調味料數量)、seaChoose(各調味料量)、timeScore(烹調時間)
		*/
		
		
		
		
		setGrade(score);
		
		
	}
	
	
	
	public void setGrade(double score) {
		this.grade = score;
	}
	
	public double getGrade() {
		return this.grade;
	}
	
	public int getRecipeId() {
		
		return this.recipeId;
	}
	public void setRecipeId(int id) {
		this.recipeId = id;
	}
public String toString() {
		
	return String.format("Recipe ID:  " + getRecipeId() + "\n" + super.toString() + "Score: " + getGrade() + "\n");
		
		 
	}
	
}