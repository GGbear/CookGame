import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class ReadFile {
	
	int ind[]= new int[31];
	String line[]= new String[10000];
	Scanner input = new Scanner(System.in);
	char[] pt;
	
	public String readImageLocation(int index)
	{
		int lineCount = 0;
		
		try	{
			
			FileReader reader = new FileReader("����.txt");
			BufferedReader br = new BufferedReader(reader);
			for(lineCount = 0; (line[lineCount]=br.readLine())!=null; lineCount++);
				//System.out.println(line[count]);
			 
			
			
			System.out.println("========================Ū�צ��\===========================");
			
		}
		catch(FileNotFoundException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("======================�䤣���ɮ�==================================");
		}
		catch(IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("======================Ū�ץ���==================================");
		}
		
		String imageLocationOfRecipe = null;
		
		String[] temp = line[lineCount].split(" ");
		int recipeNumber = Integer.parseInt(temp[0]);
		for(int indexOfRecipe = 0 ; indexOfRecipe < recipeNumber; indexOfRecipe++) {
			
			// Ū���ЦW�ٸ�index
			String[] tokens = line[lineCount].split(" ");
			String nameOfRecipe = tokens[1];
			
			
			
			lineCount++;
			for(int i = 0; i < tokens.length; i++)
				tokens[i] = null;
			
			
			// Ū�����ƶq�ΦU����index
			tokens = line[lineCount].split(" ");
			
			int numberOfIngredient = Integer.parseInt(tokens[0]);
			int[] indexOfInredients = new int[numberOfIngredient];
			
			int ingredientCount = 0;
			for(ingredientCount = 0; ingredientCount < numberOfIngredient; ingredientCount++)
				indexOfInredients[ingredientCount] = Integer.parseInt(tokens[ingredientCount+1]);
		        
			
			 
			lineCount++;
			for(int i = 0; i < tokens.length; i++)
					tokens[i] = null;
			
			// Ū�ը��Ƽƶq�ΦU�ը���index
			tokens = line[lineCount].split(" "); 
			int numberOfSeasoning = Integer.parseInt(tokens[0]);
			int[] indexOfSeasonings = new int[numberOfSeasoning];
			int[] seasoningAmount = new int[numberOfSeasoning];
			
			
			lineCount++;
			for(int i = 0; i < tokens.length; i++)
					tokens[i] = null;
			
			
			
			int seasoningCount = 0;
			for(seasoningCount = 0; seasoningCount < numberOfSeasoning; seasoningCount++) {
				tokens = line[lineCount].split(" "); 
				indexOfSeasonings[seasoningCount] = Integer.parseInt(tokens[0]);
				seasoningAmount[seasoningCount] = Integer.parseInt(tokens[1]);
				lineCount++;
				for(int i = 0; i < tokens.length; i++)
						tokens[i] = null;
				
			}
			
			 
			
			
			
			// Ū�i�ծɶ�
			int cookingTime = Integer.parseInt(line[lineCount]);
			lineCount++;
			
			// Ū�Ϥ���m
			imageLocationOfRecipe = line[lineCount];
			
			
			
			
			
			
			
		}
		return imageLocationOfRecipe;
		
	}
	public Ingredient[] readIngredient() {
		
		
		int count = 0;
		
		try	{
			
			FileReader reader = new FileReader("����.txt");
			BufferedReader br = new BufferedReader(reader);
			for(count = 0; (line[count]=br.readLine())!=null; count++);
				//System.out.println(line[count]);
			 
			
			
			System.out.println("========================Ū�צ��\===========================");
			
		}
		catch(FileNotFoundException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("======================�䤣���ɮ�==================================");
		}
		catch(IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("======================Ū�ץ���==================================");
		}
	
		Ingredient[] ingredients = new Ingredient[count];
	
		for(int indexOfIgredient = 0; indexOfIgredient < count; indexOfIgredient++)
		{
			String[] tokens = line[indexOfIgredient].split(" ");
			String nameOfIngredient = tokens[1];
			String imageLocationOfIngredient = tokens[2];
			ingredients[indexOfIgredient] = new Ingredient(indexOfIgredient, nameOfIngredient,imageLocationOfIngredient);
			
		}
		
		return ingredients;
	}
	
public Seasoning[] readSeasoning() {
		
		
	int count = 0;
		
		try	{
			FileReader reader = new FileReader("�ը���.txt");
			BufferedReader br = new BufferedReader(reader);
			for(count = 0; (line[count]=br.readLine())!=null; count++);
				//System.out.println(line[count]);	
			System.out.println("========================Ū�צ��\===========================");
			
		}
		catch(FileNotFoundException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("======================�䤣���ɮ�==================================");
		}
		catch(IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("======================Ū�ץ���==================================");
		}
		
				
		Seasoning[] seasonings = new Seasoning[count];
		
			
		for(int indexOfSeasoning = 0; indexOfSeasoning < count; indexOfSeasoning++)
		{
			String[] tokens = line[indexOfSeasoning].split(" ");
			String nameOfSeasoning = tokens[1];
			String imageLocationOfSeasoning = tokens[2];
			seasonings[indexOfSeasoning] = new Seasoning(indexOfSeasoning, nameOfSeasoning, imageLocationOfSeasoning);
			
			
		}
		
		return seasonings;
	}
	public Recipe[] readRecipe(String dataLocation, Ingredient[] ingredients, Seasoning[] seasonings) {
	
	
		int count = 0;
		
		try	{
			FileReader reader = new FileReader(dataLocation);
			BufferedReader br = new BufferedReader(reader);
			for(count = 0; (line[count]=br.readLine())!=null; count++);
				//System.out.println(line[count]);	
			System.out.println("========================Ū�צ��\===========================");
			
		}
		catch(FileNotFoundException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("======================�䤣���ɮ�==================================");
		}
		catch(IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("======================Ū�ץ���==================================");
		}
		
		
		
		String[] temp = line[0].split(" ");
		int recipeNumber = Integer.parseInt(temp[0]);
		Recipe[] recipes = new Recipe[recipeNumber];
		int indexOfRecipe = 0;
		int lineCount = 1;
		for(indexOfRecipe = 0 ; indexOfRecipe < recipeNumber; indexOfRecipe++) {
			
			// Ū���ЦW�ٸ�index
			String[] tokens = line[lineCount].split(" ");
			int index = Integer.parseInt(tokens[0]);
			String nameOfRecipe = tokens[1];
			
			
			
			lineCount++;
			for(int i = 0; i < tokens.length; i++)
				tokens[i] = null;
			
			
			// Ū�����ƶq�ΦU����index
			tokens = line[lineCount].split(" ");
			
			int numberOfIngredient = Integer.parseInt(tokens[0]);
			int[] indexOfInredients = new int[numberOfIngredient];
			
			int ingredientCount = 0;
			for(ingredientCount = 0; ingredientCount < numberOfIngredient; ingredientCount++)
				indexOfInredients[ingredientCount] = Integer.parseInt(tokens[ingredientCount+1]);
		        
			
			 
			lineCount++;
			for(int i = 0; i < tokens.length; i++)
					tokens[i] = null;
			
			// Ū�ը��Ƽƶq�ΦU�ը���index
			tokens = line[lineCount].split(" "); 
			int numberOfSeasoning = Integer.parseInt(tokens[0]);
			int[] indexOfSeasonings = new int[numberOfSeasoning];
			int[] seasoningAmount = new int[numberOfSeasoning];
			
			
			lineCount++;
			for(int i = 0; i < tokens.length; i++)
					tokens[i] = null;
			
			
			
			int seasoningCount = 0;
			for(seasoningCount = 0; seasoningCount < numberOfSeasoning; seasoningCount++) {
				tokens = line[lineCount].split(" "); 
				indexOfSeasonings[seasoningCount] = Integer.parseInt(tokens[0]);
				seasoningAmount[seasoningCount] = Integer.parseInt(tokens[1]);
				lineCount++;
				for(int i = 0; i < tokens.length; i++)
						tokens[i] = null;
				
			}
			
			 
			
			
			
			// Ū�i�ծɶ�
			int cookingTime = Integer.parseInt(line[lineCount]);
			lineCount++;
			
			// Ū�Ϥ���m
			String imageLocationOfRecipe = line[lineCount];
			lineCount++;
			
			Ingredient[] myIngredients = new Ingredient[numberOfIngredient];
			Seasoning[] mySeasonings = new Seasoning[numberOfSeasoning];
			
			for(ingredientCount = 0; ingredientCount < numberOfIngredient; ingredientCount++)
				myIngredients[ingredientCount] = ingredients[indexOfInredients[ingredientCount]];
			
			for(seasoningCount = 0; seasoningCount < numberOfSeasoning; seasoningCount++)
				mySeasonings[seasoningCount] = seasonings[indexOfSeasonings[seasoningCount]];
			
			recipes[indexOfRecipe] = new Recipe(index, nameOfRecipe, cookingTime, imageLocationOfRecipe, myIngredients, mySeasonings, seasoningAmount);
			
			
		}
	
		
		
		return recipes;
	}
	
	public MyFood[] readMyRecipe(String dataLocation, Ingredient[] ingredients, Seasoning[] seasonings) {
		
		
		int count = 0;
		
		try	{
			FileReader reader = new FileReader(dataLocation);
			BufferedReader br = new BufferedReader(reader);
			for(count = 0; (line[count]=br.readLine())!=null; count++);
				//System.out.println(line[count]);	
			System.out.println("========================Ū�צ��\===========================");
			
		}
		catch(FileNotFoundException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("======================�䤣���ɮ�==================================");
		}
		catch(IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("======================Ū�ץ���==================================");
		}
	
		String[] temp = line[0].split(" ");
		int recipeNumber = Integer.parseInt(temp[0]);
		MyFood[] recipes = new MyFood[recipeNumber];
		int indexOfRecipe = 0;
		int lineCount = 1;
		for(indexOfRecipe = 0 ; indexOfRecipe < recipeNumber; indexOfRecipe++) {
		
					
			// Ū���ЦW�ٸ�index
			String[] tokens = line[lineCount].split(" ");
			
			
			int indexOfMyFood = Integer.parseInt(tokens[0]);
			
			
			
			lineCount++;
			for(int i = 0; i < tokens.length; i++)
				tokens[i] = null;
			
			
			// Ū�����ƶq�ΦU����index
			tokens = line[lineCount].split(" ");
			
			int numberOfIngredient = Integer.parseInt(tokens[0]);
			int[] indexOfInredients = new int[numberOfIngredient];
			
			int ingredientCount = 0;
			for(ingredientCount = 0; ingredientCount < numberOfIngredient; ingredientCount++)
				indexOfInredients[ingredientCount] = Integer.parseInt(tokens[ingredientCount+1]);
		        
			
			 
			lineCount++;
			for(int i = 0; i < tokens.length; i++)
					tokens[i] = null;
			
			// Ū�ը��Ƽƶq�ΦU�ը���index
			tokens = line[lineCount].split(" "); 
			int numberOfSeasoning = Integer.parseInt(tokens[0]);
			int[] indexOfSeasonings = new int[numberOfSeasoning];
			int[] seasoningAmount = new int[numberOfSeasoning];
			
			
			lineCount++;
			for(int i = 0; i < tokens.length; i++)
					tokens[i] = null;
			
			
			
			int seasoningCount = 0;
			for(seasoningCount = 0; seasoningCount < numberOfSeasoning; seasoningCount++) {
				tokens = line[lineCount].split(" "); 
				indexOfSeasonings[seasoningCount] = Integer.parseInt(tokens[0]);
				seasoningAmount[seasoningCount] = Integer.parseInt(tokens[1]);
				lineCount++;
				for(int i = 0; i < tokens.length; i++)
						tokens[i] = null;
				
			}
			
			 
			
			
			
			// Ū�i�ծɶ�
			int cookingTime = Integer.parseInt(line[lineCount]);
			lineCount++;
			
			// Ū�Ϥ���m
			String imageLocationOfRecipe = line[lineCount];
			lineCount++;
			
			Ingredient[] myIngredients = new Ingredient[numberOfIngredient];
			Seasoning[] mySeasonings = new Seasoning[numberOfSeasoning];
			
			for(ingredientCount = 0; ingredientCount < numberOfIngredient; ingredientCount++)
				myIngredients[ingredientCount] = ingredients[indexOfInredients[ingredientCount]];
			
			for(seasoningCount = 0; seasoningCount < numberOfSeasoning; seasoningCount++)
				mySeasonings[seasoningCount] = seasonings[indexOfSeasonings[seasoningCount]];
			
			recipes[indexOfRecipe] = new MyFood(indexOfMyFood, cookingTime, imageLocationOfRecipe, myIngredients, mySeasonings, seasoningAmount);
			
			
		}
	
		
		
		return recipes;
	}	
		
	
}

