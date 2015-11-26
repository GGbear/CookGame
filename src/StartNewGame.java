import javax.swing.JFrame;


public class StartNewGame {
	public StartNewGame(JFrame upperFrame)
	{
		upperFrame.setVisible(false);
		PlayerFrame playerFrame = new PlayerFrame(upperFrame, new Record());
		playerFrame.c.stop();
		Stage1 stage1 = new Stage1(playerFrame.myFrame, playerFrame.stageSelect);
	}

}
