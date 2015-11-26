import java.util.Random;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.OverlayLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;


public class Cooking
{
	private double grade = 0;
	
	JFrame myFrame;
	MakeAFood makeAFood;
	JPanel mainPanel;
	JLabel imageLbl;
	JTextArea gradeText;
	JPanel testPanel;
	
	JLabel firstLabel;
	JLabel secondLabel;
	JLabel thirdLabel;
	MyKeyListener myFrameListener;
	int gameTime = 2500;
	Timer startTimer;
	Timer t1, t2, t3, t4, t5, t6;
	
	Font labelFont = new Font(null, Font.BOLD, 80);
	
	public Cooking(JFrame displayFrame, MakeAFood makeAFood)
	{
		myFrame = displayFrame;
		this.makeAFood= makeAFood;
		myFrame.getContentPane().removeAll();
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new OverlayLayout(mainPanel));
		
		Image image = new ImageIcon("mid.jpg").getImage();
		Image resizedImage = image.getScaledInstance(320, 270, Image.SCALE_SMOOTH);
		imageLbl = new JLabel(new ImageIcon(resizedImage));
		imageLbl.setMaximumSize(new Dimension(300, 300));
		imageLbl.setAlignmentX(0.5f);
		imageLbl.setAlignmentY(0.5f);
		/*
		textarea = new JTextArea("press keybord!");
		textarea.setEnabled(false);
		textarea.setDisabledTextColor(Color.BLACK);
		textarea.setMaximumSize(new Dimension(100, 50));
		textarea.setAlignmentX(0.5f);
		textarea.setAlignmentY(0.5f);
		*/
		
		//-------------指令Panel---------------
		testPanel = new JPanel();
		testPanel.setLayout(new GridLayout(1, 3, 20, 20));
		testPanel.setMaximumSize(new Dimension(300, 200));
		//testPanel.setBackground(new Color(0,0,0));
		//testPanel.setOpaque(false);
		testPanel.setAlignmentX(0.5f);
		testPanel.setAlignmentY(0.5f);
		testPanel.setVisible(false);
		
		firstLabel  = new JLabel(".");
		secondLabel = new JLabel(".");
		thirdLabel  = new JLabel(".");
		
		//---------------South---------------------
		gradeText = new JTextArea("Grade:" + grade);
		gradeText.setEnabled(false);
		gradeText.setDisabledTextColor(Color.BLACK);
		
		//--------------Timers-------------------
		setTimers();
		
	}
	public void start()
	{
		myFrame.getContentPane().removeAll();
		myFrameListener = new MyKeyListener();
		myFrame.addKeyListener(myFrameListener);
		
		//沒有這個鍵盤就沒有反應
		myFrame.setVisible(false);
		myFrame.setVisible(true);
		
		mainPanel.add(testPanel);
		mainPanel.add(imageLbl);
		
		
		myFrame.add(mainPanel);
		myFrame.add(gradeText, BorderLayout.SOUTH);
		
		startTimer.start();
		
		myFrame.repaint();
		//validate southPanel and all of its subcomponents
		myFrame.validate();
	}
	public double getGrade()
	{
		return grade;
	}
	public void finish()
	{
		grade *= 2;
		//myFrame.getLayeredPane().removeAll();
		myFrame.removeKeyListener(myFrameListener);
		//呼叫上一層
		makeAFood.resume();
	}
	
	public void showNewOperation()
	{
		testPanel.removeAll();
		
		//隨機選三個字母
		Random randomMaker = new Random();
		int randNum[] = new int[3];
		randNum[0] = randomMaker.nextInt(25);
		randNum[1] = randomMaker.nextInt(25);
		while(randNum[1] == randNum[0])
			randNum[1] = randomMaker.nextInt(25);
		randNum[2] = randomMaker.nextInt(25);
		while(randNum[2] == randNum[0] || randNum[2] == randNum[1])
			randNum[2] = randomMaker.nextInt(25);
		firstLabel  = new JLabel(Character.toString((char) ('A' + randNum[0])));
		secondLabel = new JLabel(Character.toString((char) ('A' + randNum[1])));
		thirdLabel  = new JLabel(Character.toString((char) ('A' + randNum[2])));
		
		firstLabel.setFont(labelFont);
		secondLabel.setFont(labelFont);
		thirdLabel.setFont(labelFont);
		
		firstLabel.setVisible(true);
		secondLabel.setVisible(true);
		thirdLabel.setVisible(true);
		
		testPanel.add(firstLabel);
		testPanel.add(secondLabel);
		testPanel.add(thirdLabel);
		
		testPanel.setVisible(true);
	}

	private void setTimers()
	{
		startTimer = new Timer(1000, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				showNewOperation();
				t1.start();
			}
		});
		startTimer.setRepeats(false);
		//timer1, pause game
		t1 = new Timer(gameTime, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				testPanel.setVisible(false);
				//myFrame.removeKeyListener(myFrameListener);
				t2.start();
			}
		});
		t1.setRepeats(false);
		//timer2, start game
		t2 = new Timer(1000, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				showNewOperation();
				t3.start();
			}
		});
		t2.setRepeats(false);
		//timer3, pause game
		t3 = new Timer(gameTime, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				testPanel.setVisible(false);
				//myFrame.removeKeyListener(myFrameListener);
				t4.start();
			}
		});
		t3.setRepeats(false);
		//timer4, start game
		t4 = new Timer(1000, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				showNewOperation();
				t5.start();
			}
		});
		t4.setRepeats(false);
		//timer5, pause game
		t5 = new Timer(gameTime, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				testPanel.setVisible(false);
				//myFrame.removeKeyListener(myFrameListener);
				t6.start();
			}
		});
		t5.setRepeats(false);
		//timer6, finish cooking phase
		t6 = new Timer(1000, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		t6.setRepeats(false);
	}
	private class MyKeyListener implements KeyListener
	{
		@Override
		public void keyPressed(KeyEvent event) {
			// TODO Auto-generated method stub
		}

		@Override
		public void keyReleased(KeyEvent event) {
			// TODO Auto-generated method stub
		}

		@Override
		public void keyTyped(KeyEvent event) {
			// TODO Auto-generated method stub
			if(Character.toUpperCase(event.getKeyChar()) ==
					firstLabel.getText().charAt(0))
			{//if press first letter
				if(firstLabel.isVisible() && testPanel.isVisible())
					grade++;
				firstLabel.setVisible(false);
			}
			else if(Character.toUpperCase(event.getKeyChar()) ==
					secondLabel.getText().charAt(0))
			{//if press second letter
				if(secondLabel.isVisible() && testPanel.isVisible())
					grade++;
				secondLabel.setVisible(false);
			}
			else if(Character.toUpperCase(event.getKeyChar()) ==
					thirdLabel.getText().charAt(0))
			{//if press third letter
				if(thirdLabel.isVisible() && testPanel.isVisible())
					grade++;
				thirdLabel.setVisible(false);
			}
			else
			{
				if(testPanel.isVisible() && grade > 0)
					grade--;
			}
			
			gradeText.setText("Grade:" + grade);
			
		}
	}
}
