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

public class Stage1 extends JFrame
{
	public JFrame myFrame;
	public JFrame inputFrame;
	public final StageSelect stageSelect;
	
	private JLabel backGround;
	private JTextArea textField_1 = new JTextArea();
	private int clickTimes = 0;
	private ImageIcon img0;
	private ImageIcon img1;
	private ImageIcon img2;
	private String fileName = new String("s1-1.dat");
	private FileInputStream fileInputStream;
	private DataInputStream dataInputStream;
	OpeningMusic o;
	
	public void finish()
	{
		//o.stop();
		myFrame.setVisible(false);
		//inputFrame.setVisible(true);
		stageSelect.finish();
	}
	
	public Stage1(final JFrame inputFrame,final StageSelect stageSelect) 
	{
		o = new OpeningMusic("01.wav");
		o.start();
		this.stageSelect = stageSelect;
		this.inputFrame = inputFrame;
		inputFrame.setVisible(false);
		this.myFrame = this;
	
		this.setTitle("中華一番之滿漢全席");
		this.getContentPane().removeAll();
		
		//設定需要用到的背景
		img0 = new ImageIcon("black.jpg"); 
		img1 = new ImageIcon("home.jpg"); 
		
		
		((JPanel)this.getContentPane()).setOpaque(false);
		getContentPane().setLayout(null);
		
		//對話框
			textField_1.setRows(6);
			textField_1.setEditable(false);
			textField_1.setFont(new Font("標楷體", Font.BOLD, 16));
			textField_1.setBounds(10, 330, 610, 120);
			textField_1.setBackground(Color.CYAN);
			Font font = textField_1.getFont();
			float size = font.getSize() + 2.0f;
			textField_1.setFont( font.deriveFont(size) );
			textField_1.setLineWrap(true);
			textField_1.setWrapStyleWord(true);
			this.getContentPane().add(textField_1);
		
		//背景
		backGround = new JLabel(img0); 
		this.getLayeredPane().add(backGround, new Integer(Integer.MIN_VALUE)); 
		backGround.setBounds(0, 0,640,480); 
		
		//台詞開檔
			try
			{
			fileInputStream = new FileInputStream(fileName);
			dataInputStream = new DataInputStream(fileInputStream);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		
			this.addMouseListener(new MouseAdapter()			//建立滑鼠點擊adapter
		{
			public void mousePressed(MouseEvent event)
			{
		        clickTimes++;
		        if(clickTimes<10)
		        {
			        try 			//讓台詞顯現在text內
			        {
			        	if(dataInputStream.available()>0)
			        		textField_1.setText(dataInputStream.readUTF());
			        }
			        catch(EOFException e)
			        {//readUTF()把檔案讀完了(EOF)
			        	System.out.printf("讀檔結束");
			        }
			        catch(Exception e)
			        {
			        	e.printStackTrace();
			        }
		        }   
		        if(clickTimes==10)	//換第二段劇本
		        {   
		        	o.stop();
			        new MakeAFood(myFrame, 0);
		        	
		        	try
		        	{
		        		fileName = "s1-2.dat";
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
		        
		       		try 			//讓台詞顯現在text內
			        {
			        	if(dataInputStream.available()>0)
			        		textField_1.setText(dataInputStream.readUTF());
			        	else
			        	{
			        		if(StageSelect.currentStage<=1)
			        			StageSelect.currentStage=2;
			        		stageSelect.Stagebutton[1].setVisible(true);
			        		finish();
			        	}
			        }
			        catch(EOFException e)
			        {//readUTF()把檔案讀完了(EOF)
			        	System.out.printf("讀檔結束");
			        }
			        catch(Exception e)
			        {
			        	e.printStackTrace();
			        }
		       	}   	
		        
		        //依照台詞更換背景
		        if(clickTimes>=9)
		        	backGround.setIcon(img1);
		        else
		        	backGround.setIcon(img0);
			}     
		});

		myFrame.setResizable(false);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(640,480);
		myFrame.setVisible(true);
	}
}
