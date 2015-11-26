import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.Timer;


public class Cutting
{
	JFrame myFrame;
	Cooking cookingPhase;
	private double grade = 0;
	
	JPanel mainPanel;
	JButton objectToCut;
	JButton objectToCut2;
	JButton objectToCut3;
	JTextArea textarea;
	int clickCount = 0;
	int clickCount2 = 0;
	int clickCount3 = 0;
	
	int throwed=3;
	Timer startTimer;
	Timer stayTimer;
	Timer fallingTimer;

	Timer startTimer2;
	Timer stayTimer2;
	Timer fallingTimer2;
	
	Timer startTimer3;
	Timer stayTimer3;
	Timer fallingTimer3;
	
	
	public Cutting(JFrame displayFrame, Cooking cooking)
	{
		myFrame = displayFrame;
		cookingPhase = cooking;
		myFrame.getContentPane().removeAll();
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		objectToCut = new JButton("click this!");
		Image testImage = new ImageIcon("¦×.png").getImage();
		Image newTestImage = testImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		objectToCut = new JButton(new ImageIcon(newTestImage));
		objectToCut.addMouseListener(new CutActionListener());
		objectToCut.setSize(100, 100);
		objectToCut.setBackground(new Color(0,0,0,0));
		objectToCut.setBorderPainted(false);
		//objectToCut.setAlignmentX(0.5f);
		//objectToCut.setAlignmentY(0.5f);
		objectToCut.setLocation(270, 430);
		
		
		//
		objectToCut2 = new JButton("click this!");
		Image testImage2 = new ImageIcon("¥d³q½µ.png").getImage();
		Image newTestImage2 = testImage2.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		objectToCut2 = new JButton(new ImageIcon(newTestImage2));
		objectToCut2.addMouseListener(new CutActionListener());
		objectToCut2.setSize(100, 100);
		objectToCut2.setBackground(new Color(0,0,0,0));
		objectToCut2.setBorderPainted(false);
		//objectToCut.setAlignmentX(0.5f);
		//objectToCut.setAlignmentY(0.5f);
		objectToCut2.setLocation(300, 430);
		
		//
		objectToCut3 = new JButton("click this!");
		Image testImage3 = new ImageIcon("½­µæ.png").getImage();
		Image newTestImage3 = testImage3.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		objectToCut3 = new JButton(new ImageIcon(newTestImage3));
		objectToCut3.addMouseListener(new CutActionListener());
		objectToCut3.setSize(100, 100);
		objectToCut3.setBackground(new Color(0,0,0,0));
		objectToCut3.setBorderPainted(false);
		//objectToCut.setAlignmentX(0.5f);
		//objectToCut.setAlignmentY(0.5f);
		objectToCut3.setLocation(100, 430);
		
		//----------------set timers-----------------
		setTimers();
		setTimers2();
		setTimers3();
		/*
		*/
		
		/*
		//test
		JPanel testButton = new JPanel();
		testButton.setMaximumSize(new Dimension(300, 200));
		testButton.setOpaque(false);
		testButton.setAlignmentX(0.5f);
		testButton.setAlignmentY(0.5f);
		panel.add(testButton);
		*/
		
		textarea = new JTextArea("0 hit!");
		textarea.setEnabled(false);
		textarea.setFont(new Font(null, Font.BOLD, 60));
	}
	public void start()
	{
		myFrame.getContentPane().removeAll();
		
		grade = 0;
		
		mainPanel.add(objectToCut);
		mainPanel.add(objectToCut2);
		mainPanel.add(objectToCut3);
		mainPanel.add(textarea);

		myFrame.add(mainPanel);
		startTimer.start();
		startTimer2.start();
		startTimer3.start();
		
		myFrame.repaint();
		//validate southPanel and all of its subcomponents
		myFrame.validate();
	}
	public double getGrade()
	{
		return grade;
	}
	private void calculateGrade()
	{
		grade = Math.abs(20 - (Math.abs(clickCount-10) + Math.abs(clickCount2-10) + Math.abs(clickCount3-10)));
	}
	public void finish()
	{
		calculateGrade();
		cookingPhase.start();
	}
	public void afterFall()
	{
		throwed--;
		if(throwed <= 0)
			finish();
	}
	private void setTimers()
	{
		startTimer = new Timer(30, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				if(objectToCut.getY() > 100)
					objectToCut.setLocation(objectToCut.getX(), objectToCut.getY() - 10);
				else
				{
					startTimer.stop();
					stayTimer.start();
				}
			}
		});
		startTimer.setRepeats(true);
		//stay
		stayTimer = new Timer(3000, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				fallingTimer.start();
			}
		});
		stayTimer.setRepeats(false);
		//falling
		fallingTimer = new Timer(100, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				if(objectToCut.getY() < 420)
					objectToCut.setLocation(objectToCut.getX(), objectToCut.getY() + 10);
				else
				{
					fallingTimer.stop();
					afterFall();
				}
			}
		});
		fallingTimer.setRepeats(true);
	}
	private void setTimers2()
	{
		startTimer2 = new Timer(30, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				if(objectToCut2.getY() > 100)
					objectToCut2.setLocation(objectToCut2.getX(), objectToCut2.getY() - 20);
				else
				{
					startTimer2.stop();
					stayTimer2.start();
				}
			}
		});
		startTimer2.setInitialDelay(2000);
		startTimer2.setRepeats(true);
		//stay
		stayTimer2 = new Timer(3000, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				fallingTimer2.start();
			}
		});
		stayTimer2.setRepeats(false);
		//falling
		fallingTimer2 = new Timer(100, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				if(objectToCut2.getY() < 420)
					objectToCut2.setLocation(objectToCut2.getX(), objectToCut2.getY() + 10);
				else
				{
					fallingTimer2.stop();
					afterFall();
				}
			}
		});
		fallingTimer2.setRepeats(true);
	}
	private void setTimers3()
	{
		startTimer3 = new Timer(30, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				if(objectToCut3.getY() > 100)
					objectToCut3.setLocation(objectToCut3.getX(), objectToCut3.getY() - 10);
				else
				{
					startTimer3.stop();
					stayTimer3.start();
				}
			}
		});
		startTimer3.setInitialDelay(580);
		startTimer3.setRepeats(true);
		//stay
		stayTimer3 = new Timer(3000, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				fallingTimer3.start();
			}
		});
		stayTimer3.setRepeats(false);
		//falling
		fallingTimer3 = new Timer(100, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				if(objectToCut3.getY() < 420)
					objectToCut3.setLocation(objectToCut3.getX(), objectToCut3.getY() + 10);
				else
				{
					fallingTimer3.stop();
					afterFall();
				}
			}
		});
		fallingTimer3.setRepeats(true);
	}
	private class CutActionListener implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			clickCount++;
			textarea.setText("" + clickCount + " hit!");
			objectToCut.repaint();
			objectToCut.validate();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	private class CutActionListener2 implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			clickCount2++;
			textarea.setText("" + clickCount + clickCount2 + clickCount3 + " hit!");
			objectToCut2.repaint();
			objectToCut2.validate();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	private class CutActionListener3 implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			clickCount3++;
			textarea.setText("" + clickCount + clickCount2 + clickCount3 + " hit!");
			objectToCut3.repaint();
			objectToCut3.validate();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
