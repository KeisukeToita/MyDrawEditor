import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;

public class DrawManager extends Observable{
	private int size, figure, mode, fill, Layer;
	
	private Color color;
	
	public ArrayList<Figure> fl;
	public ArrayList<Integer> undol;
	
	public Figure drawingFigure, deleteFigure;
	public DrawPanel dp;
    
    public DrawManager(){
    	
    	size = 50; figure = 0; mode = 0;Layer = 2;
    	color = Color.RED;
    	fill = 1;
    	
    	drawingFigure = null;
    	deleteFigure = null;
    
    	
    	fl = new ArrayList<Figure>();//　新しい構成のためのリスト
    	undol = new ArrayList<Integer>();
    }
    
    public void add_p_figure(int x, int y, DrawPanel d) {//type1 pen用の追加リスト
    	int si = this.size;
    	
    	
    	if(this.mode == 2) {
    		fl.remove(deleteFigure);
    	}
    	fl.add(new_Figure(x-si, y-si, si*2, si*2, this.color, this.fill, 0));  	
    	
    	d.repaint();
    }
    
    public void add_e_figure(int x , int y, DrawPanel d) {
    	int si = this.size;
    	Figure f = new_Figure(x-si, y-si, si*2, si*2, d.getBackground(), 1, 1);
    	
    	fl.add(f);
    	
    	d.repaint();
    	
    }
    
    public void createFigure(int x, int y) {//type2用の記録方法
    	Figure f;
    	
    	f = new_Figure(x, y, 0, 0, color, fill, 0);
    	
    	fl.add(f);
    	drawingFigure = f;
    	
    	setChanged();
    	notifyObservers();
    }
    public void reshapeFigure(int x1, int y1, int x2, int y2) {
    	if(drawingFigure != null) {
    		drawingFigure.reshape(x1, y1, x2, y2);
    		setChanged();
    		notifyObservers();
    	}
    }
    
    public void createFigure2(int x1, int y1) {//type3用の記録方法
    	int si = this.size;
    	
    	deleteFigure = new_Figure(x1-si, y1-si, si*2, si*2, color, fill, 0);
    	
    	fl.add(deleteFigure);
    	
    	setChanged();
    	notifyObservers();
    }
    public void reshapeFigure2(int x1, int y1) {
    	int si = size;
    	
    	if(deleteFigure != null) {
    		
    		fl.remove(fl.indexOf(deleteFigure));
    		deleteFigure = new_Figure(x1-si, y1-si, si*2, si*2, color, fill, 0);
        	
        	fl.add(deleteFigure);
        	
        	setChanged();
        	notifyObservers();
    	}
    }
     
    public void redraw(Graphics g) {
    	g.setColor(dp.getBackground());
    	g.fillRect(0,  0, dp.getWidth(), dp.getHeight());
    	for(Figure fig:fl) {
    			fig.draw(g);
    	}
    }
    
    public void addUndonum(int i) {//undo 操作の部分
    	undol.add(i);
    }
    public void undo() {
    	if(undol.size() == 0)return;
    	int undonum = undol.get(undol.size() - 1);
    	undol.remove(undol.size() - 1);
    	for(int i = 0;i < undonum; i++) {
    		fl.remove(fl.size() - 1);
    	}
    	setChanged();
    	notifyObservers();
    }


    public void allclear(){
	fl = new ArrayList<Figure>();
    	undol = new ArrayList<Integer>();
	
	setChanged();
    	notifyObservers();
    }
    
    public Figure new_Figure(int x, int y, int w, int h, Color c, int fi, int e) {//Figure取得の関数
    	if(figure == 0) {
    		return new Circle(x, y, w, h, c, fi,e);
    	}else if(figure == 1){
    		return new Box(x, y, w, h, c, fi, e);
    	}else if(figure == 2){
    		return new Triangle(x, y, w, h, c, fi, e);
    	}else if(figure == 4){
	    return new Star(x, y, w, h, c, fi, e);
	}else if(figure == 3){
	    return new Pentagon(x, y, w, h, c, fi, e);
	}
	return null;
    }
    
    
    public void keep() {
    	try {
    		FileOutputStream figureFile = new FileOutputStream("figure.txt");
    		ObjectOutputStream figureObject = new ObjectOutputStream(figureFile);
    		
    		FileOutputStream undoFile = new FileOutputStream("undo.txt");
    		ObjectOutputStream undoObject = new ObjectOutputStream(undoFile);
    		
    	
    		figureObject.writeObject(this.fl);
    		undoObject.writeObject(this.undol);

    	
    		figureObject.close(); 
    		figureFile.close();
    		undoObject.close(); 
    		undoFile.close();
    	} catch (IOException e) {
    		e.printStackTrace();
        }
    }
    
    public void restart() {
    	try {
    		FileInputStream figureFile = new FileInputStream("figure.txt");
    		ObjectInputStream figureObject = new ObjectInputStream(figureFile);
		
    		FileInputStream undoFile = new FileInputStream("undo.txt");
    		ObjectInputStream undoObject = new ObjectInputStream(undoFile);
    		
    		this.fl = (ArrayList<Figure>)figureObject.readObject();
    		this.undol = (ArrayList<Integer>)undoObject.readObject(); 
		
    		figureObject.close(); 
    		figureFile.close();
    		undoObject.close(); 
    		undoFile.close();
    		for(Figure f: fl) {
    			if(f.erase == 1) {
    				this.setBackground(f.color);break;
    			}
    		}
    		
    	}catch (IOException e){
    		e.printStackTrace();
    	}catch(ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	this.dp.repaint();
    }
    
    public void finish(String file_name) {
    	try{
    		BufferedImage img = new BufferedImage(dp.getWidth(), dp.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
    		this.redraw(img.getGraphics());
    	
    		ImageIO.write(img, "png", new File(file_name));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    
    

    public void setColor(Color co){//set part;
    	this.color = co;
    	setChanged();
    	notifyObservers();
    }
    public void setSize(int si){
    	this.size = si;
    	setChanged();
    	notifyObservers();
    }
    public void setFigure(int fi){
    	this.figure = fi;
    }
    public void setFill(int fi) {
    	this.fill = fi;
    }
    public void setMode(int mo) {
    	this.mode = mo;
    }
    public void setDrawPanel(DrawPanel dp) {
    	this.dp = dp;
    }
    public void setBackground(Color co) {
    	this.dp.setBackground(co);
    	
    	for(Figure f : fl) {
    		if(f.erase == 1) {
    			f.setColor(co);
    		}
    	}
    	
    	dp.repaint();
    }
    
    
    public int getMode() {// get part
    	return mode;
    }
    public Color getColor() {
    	return color;
    }
    public int getFill() {
    	return fill;
    }
    public ArrayList<Figure> getFigures(){
    	return fl;
    }
    public int getSize() {
    	return size;
    }
 
}
