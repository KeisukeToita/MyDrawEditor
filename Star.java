import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class Star extends Figure{
    Star(int x, int y, int w, int h, Color c, int fi, int e) {
	super(x, y, w, h, c, fi, e);
	}

    public void reshape(int x1, int y1, int x2, int y2) {
		int neww, newh;
		
		neww = x2-x1;
		newh = y2-y1;
		
		setSize(neww, newh);
	}
	
	public void draw(Graphics g) {
		g.setColor(this.color);
		int xPoints[]={x+width/2, x+width/3, x, x+width/4, x+width/7, x+width/2, x+6*width/7, x+3*width/4, x+width, x+2*width/3};
		int yPoints[]={y, y+height/3, y+height/3, y+3*height/5, y+height, y+4*height/5, y+height, y+3*height/5, y+height/3, y+height/3};
		if(fill == 0) {
		    g.drawPolygon(xPoints, yPoints, 10);
		}else if(fill == 1){
			g.fillPolygon(xPoints, yPoints, 10);
		}
	}
}

class Pentagon extends Figure{
    Pentagon(int x, int y, int w, int h, Color c, int fi, int e) {
	super(x, y, w, h, c, fi, e);
	}

    public void reshape(int x1, int y1, int x2, int y2) {
		int neww, newh;
		
		neww = x2-x1;
		newh = y2-y1;
		
		setSize(neww, newh);
	}
	
	public void draw(Graphics g) {
		g.setColor(this.color);
		int xPoints[] ={x+width/2, x, x+3*width/14, x+11*width/14, x+width};
		int yPoints[] ={y, y+2*height/5, y+height, y+height, y+2*height/5};
		if(fill == 0) {
		    g.drawPolygon(xPoints, yPoints, 5);
		}else if(fill == 1){
		    g.fillPolygon(xPoints, yPoints, 5);
		}
	}
}
