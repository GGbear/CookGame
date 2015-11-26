import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JScrollBar;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.JToolBar;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JLayeredPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class RecipeGUI {
	OpeningMusic p;
	private OpeningMusic r ;
	public PlayerFrame playerFrame;
	public JFrame upperFrame;
	private JFrame frmRecipe;
	final ImageIcon imageIcon = new ImageIcon("¨÷¶b.png");
	private JTextArea foodDetail = new JTextArea()
	{
		
		
		Image image = imageIcon.getImage();  
		  
	     // Image grayImage = GrayFilter.createDisabledImage(image);  
	      {  
	        setOpaque(false);  
	      } // instance initializer  
	  
	  
	      public void paint(Graphics g) {  
	    	  g.drawImage(imageIcon.getImage(), -45, -45, this); 
	        super.paint(g);  
	      }  
	    };  
	private JScrollPane scroll=new JScrollPane(foodDetail,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	private JLayeredPane layeredPane_1 = new JLayeredPane();
	private JLayeredPane foodLayer = new JLayeredPane();
	private JLabel foodPicture = new JLabel();
	private JButton lastButton = new JButton("\u4E0A\u4E00\u9801");
	private JButton nextButton = new JButton("\u4E0B\u4E00\u9801");

	MyRecipeSystem myRecipe;
	int indexOfRecipe = 0;
	private final JLayeredPane layeredPane = new JLayeredPane();
	public void setVisible(boolean visibility) {
		frmRecipe.setVisible(visibility);
	}
	
	
	public RecipeGUI(JFrame inputFrame, PlayerFrame playerFrame) {
		
		
		RecipeSystem recipeSystem = new RecipeSystem("­¹ÃÐ.txt");
		 
		
		upperFrame=inputFrame;
		upperFrame.setVisible(false);
		this.myRecipe = new MyRecipeSystem("123.txt", recipeSystem);
		this.playerFrame = playerFrame;
		initialize();
		
	}
	
	private void initialize() {
		r = new OpeningMusic("recipe.wav");
		r.start();
		
		
		frmRecipe = new JFrame();
		frmRecipe.setResizable(false);
		frmRecipe.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Pictures\\xoZIY.jpg"));
		frmRecipe.setTitle("myRecipe");
		frmRecipe.setBounds(100, 100, 965, 600);
		frmRecipe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRecipe.getContentPane().setLayout(new CardLayout(0, 0));
		frmRecipe.getContentPane().add(layeredPane, "name_6081906571973");
		
		
		lastButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(indexOfRecipe > 0) {
					
					indexOfRecipe--;
					System.out.println("------------- " + indexOfRecipe + " --------------");
					foodDetail.setText(myRecipe.getRecipe(indexOfRecipe).toString());
					foodPicture.setIcon(new ImageIcon(myRecipe.getRecipe(indexOfRecipe).getImageLocation()));	
					nextButton.setEnabled(true);
					if(indexOfRecipe == 0)
						lastButton.setEnabled(false);
				}
			}

				
			
		});
		lastButton.setBounds(242, 483, 126, 44);
		layeredPane.setLayer(layeredPane_1, 1);
		layeredPane_1.setBounds(0, 0, 959, 572);
		layeredPane.add(layeredPane_1);
		layeredPane_1.add(lastButton);
		
		
		
		
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(indexOfRecipe < myRecipe.recipeNumber() - 1) {
					
					
					indexOfRecipe++;
					System.out.println("+++++++++++++ " + indexOfRecipe + " ++++++++++++++");
					foodDetail.setText(myRecipe.getRecipe(indexOfRecipe).toString());
					foodPicture.setIcon(new ImageIcon(myRecipe.getRecipe(indexOfRecipe).getImageLocation()));	
					lastButton.setEnabled(true);
					if(indexOfRecipe == myRecipe.recipeNumber() - 1)
						nextButton.setEnabled(false);
				}
				
				
			
				
			}
			
		});
		
		
		
		nextButton.setBounds(606, 483, 122, 44);
		layeredPane_1.add(nextButton);
		
		JButton lastUpperPage = new JButton("\u56DE\u4E0A\u4E00\u9801");
		lastUpperPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frmRecipe.setVisible(false);
				upperFrame.setVisible(true);
				r.STOP();
				playerFrame.resume();
				
			}
		});
		
		
		
		lastUpperPage.setBounds(10, 10, 126, 44);
		layeredPane_1.add(lastUpperPage);
		layeredPane.setLayer(foodLayer, 1);
		layeredPane_1.setLayer(foodLayer, 2);
		foodLayer.setBounds(275, 39, 402, 396);
		layeredPane_1.add(foodLayer);
		foodLayer.setLayout(new BorderLayout(0, 0));
		foodLayer.setLayer(foodPicture, 2);
		foodLayer.add(foodPicture, BorderLayout.CENTER);
		foodPicture.setHorizontalAlignment(SwingConstants.CENTER);
		
		//System.out.println(myRecipe.getDataLocation());
		
		foodPicture.setIcon(new ImageIcon(myRecipe.getRecipe(indexOfRecipe).getImageLocation()));
		
		foodLayer.setLayer(foodDetail, 2);
		foodDetail.setRows(6);
		foodDetail.setEditable(false);
		
		foodDetail.setFont(new Font("¼Ð·¢Åé", Font.BOLD, 13));
		foodLayer.add(scroll, BorderLayout.SOUTH);
		foodDetail.setText(myRecipe.getRecipe(indexOfRecipe).toString());
		
		scroll.setSize(220,200);
		
		JLabel backgroundPic = new JLabel("");
		backgroundPic.setHorizontalAlignment(SwingConstants.CENTER);
		layeredPane.setLayer(backgroundPic, 0);
		backgroundPic.setBounds(0, 0, 959, 572);
		layeredPane.add(backgroundPic);
		backgroundPic.setBackground(Color.WHITE);
		backgroundPic.setIcon(new ImageIcon("BG.png"));
		
		if(indexOfRecipe == 0)
			lastButton.setEnabled(false);
		
		Font font = foodDetail.getFont();
		float size = font.getSize() + 2.0f;
		foodDetail.setFont( font.deriveFont(size) );
		foodDetail.setLineWrap(true);
		foodDetail.setWrapStyleWord(true);
		
		
		
		frmRecipe.setVisible(true);
	}
}
