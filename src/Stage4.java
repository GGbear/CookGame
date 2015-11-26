import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public class Stage4 extends JFrame
{ 	public JFrame myFrame;
	public JFrame inputFrame;
	public StageSelect stageSelect;
	
	private JFrame frame;
	private JLabel backGround;
	private JLabel labelRival;
	private JLabel labelTeacher;
	private JTextArea textField_1 = new JTextArea();
	private int clickTimes = 0;
	private ImageIcon img0;
	private ImageIcon img1;
	private ImageIcon img2;
	private ImageIcon imgRival;
	private	ImageIcon imgTeacher;
	private String fileName = new String("s4-1.dat");
	private FileInputStream fileInputStream;
	private DataInputStream dataInputStream;
	OpeningMusic o;
	
	public void finish()
	{
		myFrame.setVisible(false);
		//inputFrame.setVisible(true);
		stageSelect.finish();
	}
	
	public Stage4(final JFrame inputFrame,final StageSelect stageSelect)
	{
		o = new OpeningMusic("04.wav");
		o.start();
		this.stageSelect = stageSelect;
		this.inputFrame = inputFrame;
		inputFrame.setVisible(false);
		this.myFrame = this;
	
		this.setTitle("���ؤ@�f�����~���u");
		this.getContentPane().removeAll();
		
		//�]�w�ݭn�Ψ쪺�I��
		img0 = new ImageIcon("sunkitchen.jpg"); 
		img1 = new ImageIcon("sun.jpg"); 
		
		
		((JPanel)this.getContentPane()).setOpaque(false);
		this.setLayout(null);
		
		//��ܮ�
			textField_1.setRows(6);
			textField_1.setEditable(false);
			textField_1.setFont(new Font("�з���", Font.BOLD, 16));
			textField_1.setBounds(10, 330, 610, 120);
			textField_1.setBackground(Color.CYAN);
			Font font = textField_1.getFont();
			float size = font.getSize() + 2.0f;
			textField_1.setFont( font.deriveFont(size) );
			textField_1.setLineWrap(true);
			textField_1.setWrapStyleWord(true);
			this.getContentPane().add(textField_1);
		
			//���Ϥ�
			imgRival = new ImageIcon("���.png");
			labelRival = new JLabel(imgRival);
			labelRival.setBounds(0, 0, 600, 450); 
			this.getContentPane().add(labelRival);
			labelRival.setVisible(false);
			//�p��a�Ϥ�
			imgTeacher = new ImageIcon("teacher.png");
			labelTeacher = new JLabel(imgTeacher);
			labelTeacher.setBounds(0,0,640, 480); 
			this.getContentPane().add(labelTeacher);
			labelTeacher.setVisible(false);	
			
		//�I��
		backGround = new JLabel(img0); 
		this.getLayeredPane().add(backGround, new Integer(Integer.MIN_VALUE)); 
		backGround.setBounds(0, 0,640,480); 
		
		//�x���}��
			try
			{
			fileInputStream = new FileInputStream(fileName);
			dataInputStream = new DataInputStream(fileInputStream);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		
		this.addMouseListener(new MouseAdapter()			//�إ߷ƹ��I��adapter
		{
			public void mousePressed(MouseEvent event)
			{
		        clickTimes++;
		        if(clickTimes<8)
		        {
			        try 			//���x����{�btext��
			        {
			        	if(dataInputStream.available()>0)
			        		textField_1.setText(dataInputStream.readUTF());
			        }
			        catch(EOFException e)
			        {//readUTF()���ɮ�Ū���F(EOF)
			        	System.out.printf("Ū�ɵ���");
			        }
			        catch(Exception e)
			        {
			        	e.printStackTrace();
			        }
		        }   
		        if(clickTimes==8)	//���ĤG�q�@��
		        {   
		        	o.stop();
			        new MakeAFood(myFrame, 3);
		        	
		        	try
		        	{
		        		fileName = "s4-2.dat";
		        		fileInputStream = new FileInputStream(fileName);
		        		dataInputStream = new DataInputStream(fileInputStream);
		        	}
		        	catch(IOException e)
					{
						e.printStackTrace();
					}
		        }
		        
		        if(clickTimes>=8)
		       	{
		       		try 			//���x����{�btext��
			        {
			        	if(dataInputStream.available()>0)
			        		textField_1.setText(dataInputStream.readUTF());
			        	else
			        	{
			        		if(StageSelect.currentStage<=4)
			        			StageSelect.currentStage=5;
			        		stageSelect.Stagebutton[4].setVisible(true);
			        		finish();
			        	}
			        }
			        catch(EOFException e)
			        {//readUTF()���ɮ�Ū���F(EOF)
			        	System.out.printf("Ū�ɵ���");
			        }
			        catch(Exception e)
			        {
			        	e.printStackTrace();
			        }
		       	}   	
		        
		        
		        //�̷ӥx���󴫭I��
		        if(clickTimes>=14)
		        	backGround.setIcon(img1);
		        else
		        	backGround.setIcon(img0);
		       //�H���Ϥ��X�{�ɶ�
		        if(clickTimes==4||clickTimes==5||clickTimes==6||clickTimes==7||clickTimes==14||clickTimes==15||clickTimes==16||clickTimes==17||clickTimes==18)
		        	labelRival.setVisible(true);
		        else 
		        	labelRival.setVisible(false);
		        if(clickTimes==9||clickTimes==10||clickTimes==11||clickTimes==12
		        		||clickTimes==13)
		        	labelTeacher.setVisible(true);
		        else 
		        	labelTeacher.setVisible(false);
		   }     
		});
		myFrame.setResizable(false);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(640,480);
		myFrame.setVisible(true);
	}
}
