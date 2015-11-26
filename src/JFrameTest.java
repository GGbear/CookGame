import javax.swing.JFrame;
public class JFrameTest
{	
	
	public static void main(String[] args)
	{
		testKichenwre  a =new testKichenwre();
		try{
			JFrame myFrame = new JFrame();
			Opening openingFrame = new Opening(myFrame);
			myFrame.setSize(640,480);
			myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			myFrame.setVisible(true);
			//new MakeAFood(myFrame);
			
			/*呼叫要測試的class, 並傳入視窗
			new Cutting(myFrame);
			*/
			
		} catch(Exception e){
			e.printStackTrace();
		}
	
	}

}