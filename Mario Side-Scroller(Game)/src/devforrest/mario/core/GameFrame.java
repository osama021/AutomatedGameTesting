package devforrest.mario.core;

import javax.swing.JFrame;

import devforrest.mario.FileWriter.WriteInFile;

// This is the main entry point..

public class GameFrame extends JFrame {
	
	private static WriteInFile writer=new WriteInFile();
	
	public GameFrame() {	
		writer.WriteToFile("GameFrame::GameFrame()");
		int w = 420;
		int h = 330;       
		setSize(w, h);
		setResizable(false);
		setTitle("Game Frame"); 
		GamePanel panel = new GamePanel(w, h);
		add(panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);	
	}
	
	public static void main(String[] args) {
		//writer.WriteToFile("GameFrame::main(String[] args)");
		StartGame st = new StartGame();
		st.start();
	}

}
