import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Opening
{
	public JFrame myFrame;
	private JButton startGame;
	private JButton continueGame;
	private JButton quitGame;
	private JLabel background;
	private OpeningMusic p ;
	public Opening(JFrame myFrame)
	{
		p = new OpeningMusic("opening.wav");
		p.start();
		myFrame.setTitle("中華一番之滿漢全席");
		this.myFrame = myFrame;
		myFrame.setResizable(false);
		//JPanel myPanel = new JPanel();
		JLabel BgLabel  = new JLabel(); 
		
		startGame = new JButton("開始遊戲");
		continueGame = new JButton("繼續遊戲");
		quitGame = new JButton("離開遊戲");
		
		startGame.setBounds(440, 100, 150,60);
		continueGame.setBounds(440, 200, 150,60);
		quitGame.setBounds(440,300, 150,60);
		myFrame.add(startGame);
		myFrame.add(continueGame);
		myFrame.add(quitGame);
		
		ButtonHandler handler = new ButtonHandler();
		startGame.addActionListener(handler);
		continueGame.addActionListener(handler);
		quitGame.addActionListener(handler);
		
		((JPanel)myFrame.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon("zhong_hua_yi_fan_1.jpg"); 
		background = new JLabel(img);
		myFrame.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE)); 
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		myFrame.add(BgLabel);
		
		myFrame.repaint();
	}
	
	private class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if (event.getSource()==startGame)
			{ 
				myFrame.getLayeredPane().remove(background);
				new StartNewGame(myFrame);
			}
			if (event.getSource()==continueGame)
			{
				
				myFrame.getLayeredPane().remove(background);
				Record oldRecord = (Record) ObjectFileSystem.readObjectFromFile("rcd01.dat");
				PlayerFrame playerFrame = new PlayerFrame(myFrame, oldRecord);
				//playerFrame.setSize(640,480);
				//playerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//playerFrame.setVisible(true);
			}
			if (event.getSource()==quitGame)
			{
				System.exit(0);
			}
			p.STOP();
		}
	}
}
