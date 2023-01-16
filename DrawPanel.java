import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;



public class DrawPanel extends JPanel implements Observer{
	public DrawManager d;
	public DrawController cont;

    public DrawPanel(DrawManager dr){
        d = dr;
        cont = new DrawController(d, this);
        
        d.addObserver(this);
        
        this.addMouseListener(cont);
        this.addMouseMotionListener(cont);
        this.setBackground(Color.WHITE);
        
    }
    
    @Override
    public void paintComponent(Graphics g){
    	super.paintComponent(g);
    	
    	d.redraw(g);
    }

    public void update(Observable o, Object arg) {
    	repaint();
    }
    
    
}
