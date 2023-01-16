import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawController implements MouseMotionListener, MouseListener{
	
	public DrawManager d;
	public DrawPanel dp;
	public int dragStartX, dragStartY, undocount;
	
	DrawController(DrawManager dr, DrawPanel dp0){
		d = dr;
		dp = dp0;
	}
	
	public void mousePressed(MouseEvent e) {
		undocount = 1;
    	if(d.getMode() == 0) {
    		d.add_p_figure(e.getX(), e.getY(), dp);
    	}else if(d.getMode() == 1){
    		dragStartX =e.getX();dragStartY = e.getY();
    		d.createFigure(dragStartX, dragStartY);
    		d.addUndonum(undocount);
    	}else if(d.getMode() == 2) {
    		d.createFigure2(e.getX(), e.getY());
    		d.addUndonum(undocount);
    	}else if(d.getMode() == 3) {
    		d.add_e_figure(e.getX(), e.getY(), dp);
    	}
    }
    public void mouseClicked(MouseEvent e) { }
    public void mouseReleased(MouseEvent e){
    	if(d.getMode() == 0 || d.getMode() == 3) {
    		d.addUndonum(undocount);
    	}else if(d.getMode() == 2) {
    		d.add_p_figure(e.getX(), e.getY(), dp);
    	}
    }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e)  { }
    public void mouseDragged(MouseEvent e){
    	if(d.getMode() == 0) {
    		undocount++;
    		d.add_p_figure(e.getX(), e.getY(), dp);
    	}else if(d.getMode() == 1) {
    		d.reshapeFigure(dragStartX, dragStartY, e.getX(), e.getY());
    	}else if(d.getMode() == 2) {
    		d.reshapeFigure2(e.getX(),  e.getY());
    	}if(d.getMode() == 3) {
    		undocount++;
    		d.add_e_figure(e.getX(), e.getY(), dp);
    	}
    }
    public void mouseMoved(MouseEvent e){}
}
