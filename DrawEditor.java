import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawEditor extends JFrame{
	private DrawPanel dpanel;
	private EditPanel epanel;
	private DrawManager draw;
	
	public DrawEditor() {
		draw = new DrawManager();
		dpanel = new DrawPanel(draw);
		epanel = new EditPanel(draw);
		
		draw.setDrawPanel(dpanel);
		
		this.add(dpanel, BorderLayout.CENTER);
		this.add(epanel, BorderLayout.NORTH);
		this.setTitle("DrawEditor");
		this.setSize(1200,1200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new DrawEditor();
	}
}
