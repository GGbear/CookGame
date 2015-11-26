import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.GridLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PlayerFrame
{
	public JFrame myFrame;
	PlayerFrame thisFrame=this;
	Record record;
	
	private JButton save;
	private JLabel equipment;
	private JButton recipe;
	private JButton create;
	private JButton battle;
	private JComboBox Eknife;
	private JComboBox Epot;
	JTextField text;
	public OpeningMusic c ;
	
	private Player player;
	private RecipeSystem recipeSystem;
	private MyRecipeSystem myRecipeSystem;

	StageSelect stageSelect;
	public PlayerFrame(JFrame inputFrame, Record record)
	{
		this.record = record;
		c = new OpeningMusic("playerFrame.wav");
		c.start();
		
		player=(Player)ObjectFileSystem.readObjectFromFile("data\\0.obj");
		//stage=(int)ObjectFileSystem.readObjectFromFile("data\\stage.obj");
		//System.out.printf("[[%d]]",stage );
		//stageSelect.currentStage=stage;
		recipeSystem = new RecipeSystem("食譜.txt");
		myRecipeSystem = new MyRecipeSystem("123.txt", recipeSystem);
		
		//myRecipeSystem = (MyRecipeSystem)ObjectFileSystem.readObjectFromFile("data\\myRecipeSystem.obj");
		//System.out.println(myRecipeSystem.getDataLocation());
		
		String[] knifeName={player.cuttingTool[0].name,player.cuttingTool[1].name,player.cuttingTool[2].name,player.cuttingTool[3].name};
		final Icon[] knifeIcon={
				new ImageIcon(knifeName[0]+".png"),
				new ImageIcon(knifeName[1]+".png"),
				new ImageIcon(knifeName[2]+".png"),
				new ImageIcon(knifeName[3]+".png")};
		String[] potName={player.cookingTool[0].name,player.cookingTool[1].name,player.cookingTool[2].name,player.cookingTool[3].name};
		
		final Icon[] potIcon={
				new ImageIcon(potName[0]+".png"),
				new ImageIcon(potName[1]+".png"),
				new ImageIcon(potName[2]+".png"),
				new ImageIcon(potName[3]+".png")};
		
		myFrame = inputFrame;
		myFrame.setTitle("人物介面");
		myFrame.getContentPane().removeAll();
		myFrame.setResizable(false);
		
		//建立stageSelect, 再用start()開始
		stageSelect = new StageSelect(myFrame, record,this);
		stageSelect.currentStage = record.currentStage;
		
		JPanel SPanel = new JPanel();
		JPanel EPanel = new JPanel();
		JLabel BgLabel  = new JLabel(); 
		text= new JTextField(Integer.toString(player.playerAbility));
		
		final JLabel knife = new JLabel(new ImageIcon(player.equipment[0].name+".png"));
		final JLabel pot = new JLabel(new ImageIcon(player.equipment[1].name+".png"));
		
		save = new JButton("存檔");
		equipment = new JLabel("裝備能力值");
		recipe = new JButton("我的食譜");
		create = new JButton("料理研發");
		battle = new JButton("繼續旅程");
		
		SPanel.setLayout(new GridLayout(1,4,50,50));
		SPanel.add(save);
		SPanel.add(recipe);
		SPanel.add(create);
		SPanel.add(battle);
		
		knife.setBounds( 350, 50, 150, 150 );
		pot.setBounds(350, 220, 150, 150 );
		
		EPanel.setLayout(new GridLayout(3,1,80,80));
		equipment.setForeground (Color.white);
		equipment.setSize(50, 50);
		equipment.setVisible(true);
		EPanel.add(equipment);
		
		text.setBounds(560,50, 50, 20);
		myFrame.add(text);
		text.setHorizontalAlignment(SwingConstants.RIGHT);
		
		Eknife = new JComboBox(knifeName);
		Eknife.setSelectedItem(player.equipment[0].name);
		Eknife.setMaximumRowCount(3);
		Eknife.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent event)
					{
						if(event.getStateChange()==ItemEvent.SELECTED)
						{
							knife.setIcon(knifeIcon[Eknife.getSelectedIndex()]);
							player.equip(player.cuttingTool[Eknife.getSelectedIndex()]);
							text.setText(Integer.toString(player.getPlayerAbility()));
						}
					}
				}
				);
		EPanel.add(Eknife);
		Epot = new JComboBox(potName);
		Epot.setSelectedItem(player.equipment[1].name);
		Epot.setMaximumRowCount(3);
		Epot.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent event)
					{
						if(event.getStateChange()==ItemEvent.SELECTED)
						{
							pot.setIcon(potIcon[Epot.getSelectedIndex()]);
							player.equip(player.cookingTool[Epot.getSelectedIndex()]);
							text.setText(Integer.toString(player.getPlayerAbility()));
						}
					}
				}
				);
		EPanel.add(Epot);
		
		ButtonHandler handler = new ButtonHandler();
		save.addActionListener(handler);
		recipe.addActionListener(handler);
		create.addActionListener(handler);
		battle.addActionListener(handler);
		
		SPanel.setOpaque(false);
		EPanel.setOpaque(false);
		((JPanel)myFrame.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon("人物介面.png"); 
		JLabel background = new JLabel(img);
		myFrame.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE)); 
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		
		myFrame.setLayout(new BorderLayout(50,50));
		myFrame.add(knife);
		myFrame.add(pot);
		myFrame.add(SPanel,BorderLayout.SOUTH);
		myFrame.add(EPanel,BorderLayout.EAST);
		myFrame.add(BgLabel);
		myFrame.repaint();
		myFrame.validate();
	}
	
	public void resume()
	{
		c =new OpeningMusic("playerFrame.wav");
		c.start();
	}
	
	private class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if (event.getSource()==save)
			{

				ObjectFileSystem.writeObjectToFile(player, "data\\0.obj");
				record.setCurrentStage(StageSelect.currentStage);
				ObjectFileSystem.writeObjectToFile(record, "rcd01.dat");
				//stage=stageSelect.currentStage;
				//System.out.printf("[[%d]]",StageSelect.currentStage );
				//ObjectFileSystem.writeObjectToFile(stage, "data\\stage.obj");
				
				myRecipeSystem.writeData();
			}
			if (event.getSource()==recipe)
			{
				
				c.stop();
				RecipeGUI recipeGUI = new RecipeGUI(myFrame,thisFrame);
				
				
							/*
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							recipeGUI.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});*/
			}
			if (event.getSource()==create)
			{
				c.stop();
				new MakeAFood(myFrame, thisFrame);
			}
			if (event.getSource()==battle)
			{
				c.stop();
				stageSelect.start();
			}
		}
	}
}
