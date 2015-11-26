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

public class Stage10 extends JFrame
{
	public JFrame myFrame;
	public JFrame inputFrame;
	public StageSelect stageSelect;
	
	private JFrame frame;
	private JLabel backGround;
	private JLabel labelRival;
	private JLabel labelSnackMaster;
	private JTextArea textField_1 = new JTextArea();
	private int clickTimes = 0;
	private ImageIcon img0;
	private ImageIcon img1;
	private ImageIcon img2;
	private ImageIcon imgRival;
	private ImageIcon imgSnackMaster;
	private String fileName = new String("s10-1.dat");
	private FileInputStream fileInputStream;
	private DataInputStream dataInputStream;
	OpeningMusic o;
	public void finish()
	{
		myFrame.setVisible(false);
		//inputFrame.setVisible(true);
		stageSelect.finish();
	}
	
	public Stage10(final JFrame inputFrame,final StageSelect stageSelect) 
	{
		o = new OpeningMusic("10.wav");
		o.start();
		this.stageSelect = stageSelect;
		this.inputFrame = inputFrame;
		inputFrame.setVisible(false);
		this.myFrame = this;
	
		this.setTitle("中華一番之滿漢全席");
		this.getContentPane().removeAll();
		
		//設定需要用到的背景
		img0 = new ImageIcon("black.jpg"); 
		img1 = new ImageIcon("ship.jpg"); 
		img2 = new ImageIcon("dark.jpg"); 
		
		((JPanel)this.getContentPane()).setOpaque(false);
		this.setLayout(null);
		
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
			
			imgRival = new ImageIcon("對手.png");
			labelRival = new JLabel(imgRival);
			labelRival.setBounds(0, 0, 600, 450); 
			this.getContentPane().add(labelRival);
			labelRival.setVisible(false);
			
			imgSnackMaster = new ImageIcon("snackmaster.png");
			labelSnackMaster = new JLabel(imgSnackMaster);
			labelSnackMaster.setBounds(0, 0, 600, 450); 
			this.getContentPane().add(labelSnackMaster);
			labelSnackMaster.setVisible(false);
		
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
		        if(clickTimes<14)
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
		        if(clickTimes==14)	//換第二段劇本
		        {   
		        	o.stop();
			        new MakeAFood(myFrame, 9);
		        	
		        	try
		        	{
		        		fileName = "s10-2.dat";
		        		fileInputStream = new FileInputStream(fileName);
		        		dataInputStream = new DataInputStream(fileInputStream);
		        	}
		        	catch(IOException e)
					{
						e.printStackTrace();
					}
		        }
		        
		        if(clickTimes>=14)
		       	{
		       		try 			//讓台詞顯現在text內
			        {
			        	if(dataInputStream.available()>0)
			        		textField_1.setText(dataInputStream.readUTF());
			        	else
			        	{
			        		if(StageSelect.currentStage<=10)
			        			StageSelect.currentStage=11;
			        		stageSelect.Stagebutton[10].setVisible(true);
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
		        if(clickTimes==5)
		        	backGround.setIcon(img1);
		        else if(clickTimes > 5)
		        	backGround.setIcon(img2);
		        if(clickTimes==3||clickTimes==4)
		        	labelSnackMaster.setVisible(true);
		        else
		        	labelSnackMaster.setVisible(false);
		        if(clickTimes>7)
		        	labelRival.setVisible(true);
		        else
		        	labelRival.setVisible(false);
			}     
		});
			myFrame.setResizable(false);
			myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			myFrame.setSize(640,480);
			myFrame.setVisible(true);
	}
}