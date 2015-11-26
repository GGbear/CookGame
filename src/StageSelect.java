import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;


import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StageSelect
{
	public StageSelect thisSelect = this;
	public JFrame upperFrame;
	public JFrame thisFrame;
	Record record;
	public static int currentStage=1;
	private	static final String []	Stagenumbers=
		{"1","2","3","4","5","6","7","8","9","10","11","12"};
	private JLabel label1;
	private JLabel labelspaceleft;
	private JLabel labelspaceright;
	private JLabel labelspacebelow;
	private BorderLayout layout;
	//private int stageNumber;
	public final JButton [] Stagebutton;
	private JLabel background;
	PlayerFrame player;
	public StageSelect(JFrame upperFrame, Record record,PlayerFrame player)
	{
		this.player=player;
		this.upperFrame = upperFrame;
		this.record = record;
		currentStage = record.getCurrentStage();
		//myFrame.getContentPane().removeAll();
		
		thisFrame = new JFrame();
		thisFrame.setTitle("中華一番之滿漢全席");
		thisFrame.setSize(640, 480);
		thisFrame.setResizable(false);
		thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 //JButton [] Stagebutton;
		layout = new BorderLayout(60,60);
		thisFrame.setLayout(layout);
		
		label1 = new JLabel("     請   選   擇    關   卡");
		label1.setFont(new Font("標楷體", Font.BOLD, 35));
		labelspaceleft = new JLabel("");
		labelspaceright = new JLabel("");
		labelspacebelow = new JLabel("");
		JPanel p1 = new JPanel(new GridLayout(3,4,70,70));
		p1.setOpaque(false);
		
		Stagebutton = new JButton[Stagenumbers.length];
		for(int count=0;count<Stagenumbers.length;count++)
		{
			Stagebutton[count] = new JButton(Stagenumbers[count]);
			p1.add(Stagebutton[count]);
		}
		thisFrame.add(p1,BorderLayout.CENTER);
		thisFrame.add(label1,BorderLayout.NORTH);
		thisFrame.add(labelspaceleft,BorderLayout.WEST);
		thisFrame.add(labelspaceright,BorderLayout.EAST);
		thisFrame.add(labelspacebelow,BorderLayout.SOUTH);
		//請選擇關卡的位置
		(label1).setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
		((JPanel)thisFrame.getContentPane()).setOpaque(false);
		//背景設置
		ImageIcon img = new ImageIcon("StateSelect_bg.jpg"); 
		background = new JLabel(img);
		thisFrame.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE)); 
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight()); 
	
		ButtonHandler handler = new ButtonHandler();
		for(int	count=0 ; count < Stagenumbers.length ; count++)
			{
			Stagebutton[count].addActionListener(handler);	
			if(count < currentStage)
				Stagebutton[count].setVisible(true);
			else
				Stagebutton[count].setVisible(false);
			}
		//Stagebutton[0].setVisible(true);

	}
	public void start()
	{
		upperFrame.setVisible(false);
		thisFrame.setVisible(true);
	}
	public void finish()
	{
		player.resume();
		record.setCurrentStage(currentStage);
		upperFrame.setVisible(true);
		thisFrame.setVisible(false);
	}

	private class ButtonHandler implements ActionListener
	{
		Stage1 stage1;
		Stage2 stage2;
		Stage3 stage3;
		Stage4 stage4;
		Stage5 stage5;
		Stage6 stage6;
		Stage7 stage7;
		Stage8 stage8;
		Stage9 stage9;
		Stage10 stage10;
		Stage11 stage11;
		Stage12 stage12;
		public void actionPerformed(ActionEvent event)
		{
			
			if(event.getSource() == Stagebutton[0])
			{
				thisFrame.setVisible(false);
				stage1 = new Stage1(thisFrame, thisSelect);
				//myFrame.setVisible(true);
			}	
			else if(event.getSource()==Stagebutton[1])
			{	
				thisFrame.setVisible(false);
				stage2 = new Stage2(thisFrame, thisSelect);
				//myFrame.setVisible(true);
			}
			else if(event.getSource()==Stagebutton[2])
			{	
				thisFrame.setVisible(false);
				stage3 = new Stage3(thisFrame, thisSelect);
				//myFrame.setVisible(true);
			}
			else if(event.getSource()==Stagebutton[3])
			{
				thisFrame.setVisible(false);
				stage4 = new Stage4(thisFrame, thisSelect);
				//myFrame.setVisible(true);
			}
			else if(event.getSource()==Stagebutton[4])
			{
				thisFrame.setVisible(false);
				stage5 = new Stage5(thisFrame, thisSelect);
				//myFrame.setVisible(true);
			}
			else if(event.getSource()==Stagebutton[5])
			{	
				thisFrame.setVisible(false);
				stage6 = new Stage6(thisFrame, thisSelect);
				//myFrame.setVisible(true);
			}
			else if(event.getSource()==Stagebutton[6])
			{
				thisFrame.setVisible(false);
				stage7 = new Stage7(thisFrame, thisSelect);
				//myFrame.setVisible(true);
			}
			else if(event.getSource()==Stagebutton[7])
			{	
				thisFrame.setVisible(false);
				stage8 = new Stage8(thisFrame, thisSelect);
				//myFrame.setVisible(true);
			}
			else if(event.getSource()==Stagebutton[8])
			{	
				thisFrame.setVisible(false);
				stage9 = new Stage9(thisFrame, thisSelect);
				//myFrame.setVisible(true);
			}
			else if(event.getSource()==Stagebutton[9])
			{	
				thisFrame.setVisible(false);
				stage10 = new Stage10(thisFrame, thisSelect);
				//myFrame.setVisible(true);
			}
			else if(event.getSource()==Stagebutton[10])
			{
				thisFrame.setVisible(false);
				stage11 = new Stage11(thisFrame, thisSelect);
				//myFrame.setVisible(true);
			}
			else if(event.getSource()==Stagebutton[11])
			{	
				thisFrame.setVisible(false);
				stage12 = new Stage12(thisFrame, thisSelect);
				//myFrame.setVisible(true);
			}	
		}

	}  
}