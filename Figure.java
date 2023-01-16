import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

abstract class Figure implements Serializable{
	public int x, y, width, height, fill, erase,num;
	public Color color;
	
	public Figure(int x, int y, int w, int h, Color c, int fi, int e) {
		this.x = x;this.y = y;
		width = w;height = h;
		color = c;
		fill = fi;
		erase = e;
	}
	
	public void setSize(int w, int h) {
		width = w;height = h;
	}
	public void setLocation(int x, int y) {
		this.x = x;this.y = y;
	}
	
	public void reshape(int x1, int y1, int x2, int y2) {
		int newx = Math.min(x1,x2);
		int newy = Math.min(y1,y2);
		int neww = Math.abs(x1-x2);
		int newh = Math.abs(y1-y2);
		setLocation(newx, newy);
		setSize(neww, newh);
	}
	
	public void setColor(Color c) {
		this.color = c;
	}
	
	abstract public void draw(Graphics g);
}

class Box extends Figure {
	
	public Box(int x, int y, int w, int h, Color c, int fi, int e) {
		super(x,y,w,h,c,fi, e);
		num = 1;
	}
	
	public void draw(Graphics g) {
		g.setColor(this.color);
		if(fill == 0) {
			g.drawRect(x, y, width, height);
		}else if(fill == 1){
			g.fillRect(x, y, width, height);
		}
	}
}

class Circle extends Figure{
	public Circle(int x, int y, int w, int h, Color c, int fi, int e) {
		super(x, y, w, h, c, fi, e);
		num = 0;
	}
	
	public void draw(Graphics g) {
		g.setColor(this.color);
		if(fill == 0) {
			g.drawOval(x, y, width, height);
		}else if(fill == 1){
			g.fillOval(x, y, width, height);
		}
	}
}

class Triangle extends Figure{
	Triangle(int x, int y, int w, int h, Color c, int fi, int e){
		super(x,y,w,h,c,fi, e);
		num = 2;
	}
	
	public void reshape(int x1, int y1, int x2, int y2) {
		int neww, newh;
		
		neww = x2-x1;
		newh = y2-y1;
		
		setSize(neww, newh);
	}
	
	public void draw(Graphics g) {
		g.setColor(this.color);
		int xPoints[] = {x+width/2, x, x+width};
		int yPoints[] = {y, y+height, y+height};
		if(fill == 0) {
			g.drawPolygon(xPoints, yPoints, 3);
		}else {
			g.fillPolygon(xPoints, yPoints, 3);
		}
		
	}
}

