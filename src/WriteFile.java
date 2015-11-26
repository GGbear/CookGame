import java.io.*;

public class WriteFile {
	public void writeRecipe(MyRecipeSystem recipe) {

		try	{
			
			FileWriter reader = new FileWriter("123.txt");
			
			int i = 0;
			
			reader.write(recipe.recipeNumber() + "\r\n");
			
			for(i = 0; i < recipe.recipeNumber(); i++) {
				reader.write(recipe.getRecipe(i).getRecipeId() + "\r\n");
				reader.write(recipe.getRecipe(i).ingredientToString() + "\r\n");
				reader.write(recipe.getRecipe(i).seasoningToString());
				reader.write(recipe.getRecipe(i).getCookingTime() + "\r\n");
				reader.write(recipe.getRecipe(i).getImageLocation() + "\r\n");
			}
			
			
			System.out.println("========================�g�ɦ��\===========================");
			reader.close(); 
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
	}
}
