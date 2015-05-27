import javax.swing.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		
		LoginGUI window = new LoginGUI();
		
		ImageIcon logo = new ImageIcon("src/NS_Icon.png");
		
		window.setIconImage(logo.getImage());
		window.setSize(300, 130);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		
	}
}