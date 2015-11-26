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

public class Stage2 extends JFrame
{
	public JFrame myFrame;
	public JFrame inputFrame;
	public StageSelect stageSelect;
	
	private JLabel backGround;
	private JLabel labelRival;
	private JTextArea textField_1 = new JTextArea();
	private int clickTimes = 0;
	private ImageIcon img0;
	private ImageIcon img1;
	private ImageIcon imgRival;
	private String fileName = new String("s2-1.dat");
	private FileInputStream fileInputStream;
	private DataInputStream dataInputStream;
	OpeningMusic o;
	public void finish()
	{
		myFrame.setVisible(false);
		//inputFrame.setVisible(true);
		stageSelect.finish();
	}
	
	public Stage2(final JFrame inputFrame,final StageSelect stageSelect)
	{
		o = new OpeningMusic("02.wav");
		o.start();
		this.stageSelect = stageSelect;
		this.inputFrame = inputFrame;
		inputFrame.setVisible(false);
		this.myFrame = this;
		
		this.setTitle("���ؤ@�f�����~���u");
		this.getContentPane().removeAll();
		
		//�]�w�ݭn�Ψ쪺�I��
		img0 = new ImageIcon("sun.jpg"); 
		img1 = new ImageIcon("sunkitchen.jpg"); 
		//�]�w�H���Ϥ�
		imgRival = new ImageIcon("���.png");
		
		((JPanel)this.getContentPane()).setOpaque(false);
		getContentPane().setLayout(null);
		
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
			labelRival = new JLabel(imgRival);
			labelRival.setBounds(0, 0, 600, 450); 
			this.getContentPane().add(labelRival);
			labelRival.setVisible(false);
		
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
		      
		        if(clickTimes<10)
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
		        if(clickTimes==10)	//���ĤG�q�@��
		        {   
		        	o.stop();
			        new MakeAFood(myFrame, 1);
		        	
		        	try
		        	{
		        		fileName = "s2-2.dat";
		        		fileInputStream = new FileInputStream(fileName);
		        		dataInputStream = new DataInputStream(fileInputStream);
		        	}
		        	catch(IOException e)
					{
						e.printStackTrace();
					}
		        }
		        
		        if(clickTimes>=10)
		       	{
		       		try 			//���x����{�btext��
			        {
			        	if(dataInputStream.available()>0)
			        		textField_1.setText(dataInputStream.readUTF());
			        	else
			        	{	
			        		if(StageSelect.currentStage<=2)
			        			StageSelect.currentStage=3;
			        		stageSelect.Stagebutton[2].setVisible(true);
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
		        if(clickTimes>=7)
		        	backGround.setIcon(img1);
		        else
		        	backGround.setIcon(img0);
		        if(clickTimes>=12)
		        	labelRival.setVisible(true);
		        
			}     
		});
		
		myFrame.setResizable(false);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(640,480);
		myFrame.setVisible(true);
	}
}