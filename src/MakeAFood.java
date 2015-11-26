import java.util.ArrayList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.OverlayLayout;


public class MakeAFood
{
	JFrame pastFrame;
	JFrame myFrame;
	double grade;
	Player player;
	Ingredient[] allIngredient;
	Ingredient[] selectedIngredientArray;
	ArrayList<Ingredient> selectedIngredient;
	Seasoning[] allSeasoning;
	Seasoning[] selectedSeasoningArray;
	ArrayList<Seasoning> selectedSeasoning;
	int[] seasoningAmountArray;
	int[] seasoningAmount;
	MyFood newFood;
	Recipe request;
	
	Cutting cuttingPhase;
	Cooking cookingPhase;
	
	JPanel mainPanel;
	JPanel southPanel;
	JLabel background;
	JButton[] ingredientButton;
	JButton[] seasoningButton;
	JButton[] selectedSsnButton;
	OpeningMusic m;
	PlayerFrame play;
	public MakeAFood(JFrame mainFrame, PlayerFrame play)
	{
		this.play = play;
		pastFrame = mainFrame;

		RecipeSystem recipeSystem = new RecipeSystem("食譜.txt");
		request = recipeSystem.getRecipe(15);
		
		myFrame = new JFrame();
		myFrame.setSize(640, 480);
		myFrame.setResizable(false);
		//關掉視窗結束程式
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
		pastFrame.setVisible(false);

		
		selectedIngredientArray = new Ingredient[1];
		selectedIngredientArray[0] = new Ingredient(1, "asdxvxv", "asdlkajsd");
		selectedSeasoningArray = new Seasoning[1];
		selectedSeasoningArray[0] = new Seasoning(1, "salt", "asdlkajsd");
		seasoningAmountArray = new int[1];
		seasoningAmountArray[0] = 1;
		
		cookingPhase = new Cooking(myFrame, this);
		cuttingPhase = new Cutting(myFrame, cookingPhase);
		
		//播音樂
		m = new OpeningMusic("cutting.wav");
		m.start();
		selectIngredients(myFrame);
	}
	public MakeAFood(JFrame mainFrame, int recipeId)
	{
		RecipeSystem recipeSystem = new RecipeSystem("食譜.txt");
		request = recipeSystem.getRecipe(recipeId);
		
		pastFrame = mainFrame;
		
		myFrame = new JFrame();
		myFrame.setSize(640, 480);
		myFrame.setResizable(false);
		//關掉視窗結束程式
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
		pastFrame.setVisible(false);
		 
		//((JPanel)myFrame.getContentPane()).setOpaque(false);
		 
		cookingPhase = new Cooking(myFrame, this);
		cuttingPhase = new Cutting(myFrame, cookingPhase);
		
		//播音樂
		m = new OpeningMusic("cutting.wav");
		m.start();
		selectIngredients(myFrame);
	}
	public void resume()
	{//結束料理
		if(request == null)
			System.out.println("request is null!");
		else
			System.out.println(request);
		
		seasoningAmountArray = new int[selectedSeasoning.size()];
		for(int i=0; i<selectedSeasoning.size(); i++)
		{
			seasoningAmountArray[i] = seasoningAmount[selectedSeasoning.get(i).getId()];
		}
		
		selectedIngredientArray= new Ingredient[selectedIngredient.size()];
		selectedSeasoningArray = new Seasoning[selectedSeasoning.size()];
		newFood =
				new MyFood(request.getId(), request.getCookingTime(),
						request.getImageLocation(), selectedIngredient.toArray(selectedIngredientArray),
						selectedSeasoning.toArray(selectedSeasoningArray), seasoningAmountArray);

		calculateGrade();
		
		//顯示分數
		myFrame.getContentPane().removeAll();
		//myFrame.getLayeredPane().removeAll();
		
		
		Font gradeFont = new Font(null, Font.BOLD, 60);
		JLabel leftLbl = new JLabel("你的分數:");
		leftLbl.setMaximumSize(new Dimension(300, 100));
		//leftLbl.setAlignmentX(0.3f);
		//leftLbl.setAlignmentY(0.3f);
		leftLbl.setFont(gradeFont);
		leftLbl.setBounds(100, 100, 300, 100);
		JLabel gradeLbl = new JLabel(Double.toString(grade));
		gradeLbl.setMaximumSize(new Dimension(100, 100));
		//gradeLbl.setAlignmentX(0.5f);
		//gradeLbl.setAlignmentY(0.5f);
		gradeLbl.setFont(gradeFont);
		gradeLbl.setBounds(450, 250, 200, 100);
		
		JButton leave = new JButton("離開");
		leave.addActionListener(new leaveListener());
		leave.setBounds(200, 350, 100, 30);
		JPanel gradePanel = new JPanel();
		gradePanel.setLayout(null);
		gradePanel.add(leftLbl);
		gradePanel.add(gradeLbl);
		gradePanel.add(leave);
		
		
		myFrame.add(gradePanel);
		/*
		((JPanel)myFrame.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon("zhong_hua_yi_fan_1.jpg"); 
		background = new JLabel(img);
		myFrame.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE)); 
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		//myFrame.add(background);
		*/
		myFrame.repaint();
		//validate southPanel and all of its subcomponents
		myFrame.validate();
		
		//判斷贏或輸
		//finish();
	}
	public void finish()
	{
		m.stop();
		
		RecipeSystem recipeSystem = new RecipeSystem("食譜.txt");
		MyRecipeSystem myRecipeSystem = new MyRecipeSystem("123.txt", recipeSystem);
		
		
		/*
		
		Ingredient[] testInt = new Ingredient[1];
		testInt[0] = new Ingredient(1, "asdxvxv", "asdlkajsd");
		Seasoning[] testSea = new Seasoning[1];
		int[] mySeasoningAmount = {1};
		testSea[0] = new Seasoning(1, "fuck", "asdlkajsd");
		MyFood testMyFood = new MyFood(1, 30, recipeSystem.getRecipe(1).getImageLocation(),
				testInt, testSea, mySeasoningAmount
				);
		*/
		
		myRecipeSystem.addFood(newFood);
		newFood.setGrade(grade);
		myRecipeSystem.writeData();
		pastFrame.setVisible(true);
		myFrame.setVisible(false);
		if(play!=null)
			play.resume();
	}
	public double calculateGrade()
	{
		if(newFood != null)
			newFood.score(request);
		else
			System.out.println("newFood is null");
		System.out.println("newFood grade:" + newFood.getGrade());
		grade = newFood.getGrade() + (double)cuttingPhase.getGrade() + (double)cookingPhase.getGrade();
		return grade;
	}
	public void selectIngredients(JFrame selectIngredientsFrame)
	{
		selectIngredientsFrame.getContentPane().removeAll();
		//selectIngredientsFrame.getLayeredPane().removeAll();
		/*
		//((JPanel)myFrame.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon("zhong_hua_yi_fan_1.jpg"); 
		background = new JLabel(img);
		myFrame.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE)); 
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		myFrame.add(background);
		*/
		
		
		selectedIngredient = new ArrayList<Ingredient>();
		
		JButton summitBtn = new JButton("選擇調味料");
		summitBtn.addActionListener(new SeasoningPhaseListener());
		myFrame.add(summitBtn, BorderLayout.NORTH);
		
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(610, 330));
		
		/*
		//test
		Image testImage = new ImageIcon("雞蛋.png").getImage();
		Image newTestImage = testImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		JButton testButton =
				new JButton(new ImageIcon(newTestImage));
		testButton.setText("test");
		mainPanel.add(testButton);
		*/
		allIngredient = (new ReadFile()).readIngredient();
		ingredientButton = new JButton[allIngredient.length];
		for(int i=0; i<allIngredient.length; i++)
		{
			Image image = new ImageIcon(allIngredient[i].getImageLocation()).getImage();
			Image resizedImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			ingredientButton[i] =
					new JButton(new ImageIcon(resizedImage));
			/*
			*/
			//ingredientButton[i] = new JButton();
			ingredientButton[i].setText(allIngredient[i].getName());
			ingredientButton[i].addActionListener(new selectListener());
			mainPanel.add(ingredientButton[i]);
		}
		
		
		southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(640, 120));
		//southPanel.setBackground(Color.BLACK);
		
		JScrollPane mainScrollPane = new JScrollPane(mainPanel);
		mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		selectIngredientsFrame.add(mainScrollPane);
		selectIngredientsFrame.add(southPanel, BorderLayout.SOUTH);
	
		myFrame.repaint();
		//validate southPanel and all of its subcomponents
		myFrame.validate();
	}
	public void selectSeasonings()
	{
		myFrame.getContentPane().removeAll();
		/*
		ImageIcon img = new ImageIcon("背景.jpg"); 
		background = new JLabel(img);
		myFrame.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE)); 
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight()); 
		*/
		selectedSeasoning = new ArrayList<Seasoning>();
		
		JButton summitBtn = new JButton("進入切菜階段");
		summitBtn.addActionListener(new CuttingPhaseListener());
		myFrame.add(summitBtn, BorderLayout.NORTH);
		
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(610, 290));
		//mainPanel.setOpaque(false);
		//mainPanel.setBackground(new Color(0,0,0,0));
		/*
		//test
		Image testImage = new ImageIcon("D:\\b69d3da5.jpg").getImage();
		Image newTestImage = testImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		JButton testButton =
				new JButton(new ImageIcon(newTestImage));
		testButton.setText("test");
		mainPanel.add(testButton);}
		*/
		allSeasoning = (new ReadFile()).readSeasoning();
		seasoningButton = new JButton[allSeasoning.length];
		seasoningAmount = new int[allSeasoning.length];
		selectedSsnButton = new JButton[allSeasoning.length];
		for(int i=0; i<allSeasoning.length; i++)
		{
			seasoningButton[i] = new JButton();
			seasoningButton[i].setText(allSeasoning[i].getName());
			seasoningButton[i].addActionListener(new selectSsnListener());
			mainPanel.add(seasoningButton[i]);
		}
		
		
		southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(640, 120));
		//southPanel.setBackground(Color.BLACK);
		
		JScrollPane mainScrollPane = new JScrollPane(mainPanel);
		mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		myFrame.add(mainScrollPane);
		myFrame.add(southPanel, BorderLayout.SOUTH);
	
		myFrame.repaint();
		//validate southPanel and all of its subcomponents
		myFrame.validate();
	}
	private class selectListener implements ActionListener
	{
		int index;
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			//if(event.getSource())
			for(int i=0; i<allIngredient.length; i++)
			{
				if(event.getSource().equals(ingredientButton[i]))
				{
					index = i;
					break;
				}
			}
			if(!selectedIngredient.contains(allIngredient[index]))
			{
				selectedIngredient.add(allIngredient[index]);
				JButton selectedButton = new JButton(ingredientButton[index].getIcon());
				//selectedButton = new JButton();
				selectedButton.setText(ingredientButton[index].getText());
				selectedButton.addActionListener(new cancellListener());
				southPanel.add(selectedButton);
				
				myFrame.repaint();
				//validate southPanel and all of its subcomponents
				myFrame.validate();
			}
		}
	}
	private class selectSsnListener implements ActionListener
	{
		int index;
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			//if(event.getSource())
			for(int i=0; i<allSeasoning.length; i++)
			{
				if(event.getSource().equals(seasoningButton[i]))
				{
					index = i;
					break;
				}
			}
			if(!selectedSeasoning.contains(allSeasoning[index]))
			{
				selectedSeasoning.add(allSeasoning[index]);
				seasoningAmount[index] = new Integer(0);
				seasoningAmount[index]++;
				selectedSsnButton[index] = new JButton();
				selectedSsnButton[index].setText(seasoningButton[index].getText() + ": " + seasoningAmount[index]);
				selectedSsnButton[index].addActionListener(new cancellSsnListener());
				southPanel.add(selectedSsnButton[index]);
				
			}
			else
			{
				seasoningAmount[index]++;
				selectedSsnButton[index].setText(seasoningButton[index].getText() + ": " + seasoningAmount[index]);
			}
			myFrame.repaint();
			//validate southPanel and all of its subcomponents
			myFrame.validate();
		}
	}
	private class cancellListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			for(int i=0; i<selectedIngredient.size(); i++)
			{
				if(((JButton) event.getSource()).getText() ==
						selectedIngredient.get(i).getName())
					selectedIngredient.remove(i);
			}
			southPanel.remove((JButton) event.getSource());
			
			myFrame.repaint();
			//validate southPanel and all of its subcomponents
			myFrame.validate();
		}
	}
	private class cancellSsnListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			for(int i=0; i<selectedSeasoning.size(); i++)
			{
				if(((JButton) event.getSource()).getText() ==
						selectedSeasoning.get(i).getName())
				{
					if(seasoningAmount[selectedSeasoning.get(i).getId()] > 1)
					{
						seasoningAmount[selectedSeasoning.get(i).getId()]--;
						selectedSsnButton[selectedSeasoning.get(i).getId()]
								.setText(seasoningButton[selectedSeasoning.get(i).getId()].getText() + ": " + seasoningAmount[selectedSeasoning.get(i).getId()]);
					}
					else
					{
						seasoningAmount[selectedSeasoning.get(i).getId()]--;
						selectedSeasoning.remove(i);
						southPanel.remove((JButton) event.getSource());
					}
				}
			}
			
			myFrame.repaint();
			//validate southPanel and all of its subcomponents
			myFrame.validate();
		}
	}
	private class SeasoningPhaseListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			selectSeasonings();
		}
	}
	private class CuttingPhaseListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			cuttingPhase.start();
		}
	}private class leaveListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			finish();
		}
	}
}
