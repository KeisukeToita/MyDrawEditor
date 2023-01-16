import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ColorChooser extends JButton {
    public JColorChooser colorchooser;
    public DrawManager dr;

    ColorChooser(DrawManager d){  //コンストラクタ生成
	dr = d;
	colorchooser = new JColorChooser();  //初期化

	this.setText("<html><span style = 'font-size:24pt;'>Color</span></html>");
	this.setPreferredSize(new Dimension(120, 50));
    }

    public void ChangeColor(){
    	dr.setColor(colorchooser.showDialog(this, "色の選択", Color.white));
    }
}

class Background extends JButton{
	public JColorChooser colorchooser;
    public DrawManager dr;
    
    Background(DrawManager d){
    	dr = d;
    	colorchooser = new JColorChooser();
    	
    	this.setText("<html><span style = 'font-size:15pt;'>Background</span></html>");
    	this.setPreferredSize(new Dimension(120, 50));
    }
    
    public void ChangeBackground() {
    	dr.setBackground(colorchooser.showDialog(this, "背景色の選択", Color.white));
    }
}

class SizeField extends JTextField implements Observer {
    public String num;
    public int size;
    public DrawManager dr;

    SizeField(DrawManager d){  //コンストラクタ生成
	dr = d;

	this.setFont(new Font("Arial", Font.PLAIN, 24));
	this.setText(Integer.toString(dr.getSize()));
	this.setPreferredSize(new Dimension(120, 50));

	dr.addObserver(this);
    }

    public void update(Observable o, Object arg){
	this.setText(Integer.toString(dr.getSize()));
    }
    
    public void ChangeSize(){
	 num = this.getText();
	 size = Integer.parseInt(num, 10);
	 dr.setSize(size);
    }
}

class SizePlusButton extends JButton {
    public DrawManager dr;

    SizePlusButton(DrawManager d){
	dr = d;
      
	this.setText("<html><span style = 'font-size:24pt;'>＋</span></html>");
	this.setPreferredSize(new Dimension(60, 50));
      
    }
    
    public void SizePlus(){
	dr.setSize(dr.getSize()+5);  //drに入っているsizeを5増やしてdrに再度セット
    }  
}

class SizeMinusButton extends JButton {
    public DrawManager dr;

    SizeMinusButton(DrawManager d){
	dr = d;
      
	this.setText("<html><span style = 'font-size:24pt;'>ー</span></html>");
	this.setPreferredSize(new Dimension(60, 50));
      
    }
    
    public void SizeMinus(){
	if(dr.getSize()<=4){ return; }  //sizeが小さくなりすぎると描けなくなるので、その予防
	dr.setSize(dr.getSize()-5);  //drに入っているsizeを5減らしてdrに再度セット
    }  
}


class ColorPanel extends JPanel implements Observer{//現在の色を表示
	DrawManager dr;
	
	ColorPanel(DrawManager d){
		dr = d;
		dr.addObserver(this);
		this.setPreferredSize(new Dimension(120, 50));
		this.setBackground(dr.getColor());
	}
	
	public void update(Observable o, Object arg) {
		this.setBackground(dr.getColor());
	}
}

class ModeChoice extends JComboBox{
	String[] modedata = {"<html><span style = 'font-size:24pt;'>Pen</span></html>", 
						 "<html><span style = 'font-size:24pt;'>2point</span></html>", 
						 "<html><span style = 'font-size:24pt;'>Stamp</span></html>",
						 "<html><span style = 'font-size:24pt;'>eraser</span></html>"};
	DefaultComboBoxModel model;
	
	DrawManager dr;
	
	ModeChoice(DrawManager d){
		
		dr = d;
		
		model = new DefaultComboBoxModel(modedata);
		this.setModel(model);
		this.setPreferredSize(new Dimension(120, 50));
	}
	
	public void changeMode() {
		if(model.getSelectedItem() == "<html><span style = 'font-size:24pt;'>Pen</span></html>") {
			dr.setMode(0);
		}else if(model.getSelectedItem() == "<html><span style = 'font-size:24pt;'>2point</span></html>") {
			dr.setMode(1);
		}else if(model.getSelectedItem() == "<html><span style = 'font-size:24pt;'>Stamp</span></html>"){
			dr.setMode(2);
		}else {
			dr.setMode(3);
		}
	}
}



class FigureChoise extends JComboBox{
	String[] figuredata = {"<html><span style = 'font-size:24pt;'>Circle</span></html>",
			       "<html><span style = 'font-size:24pt;'>Box</span></html>",
			       "<html><span style = 'font-size:24pt;'>Triangle</span></html>",
			       "<html><span style = 'font-size:24pt;'>Pentagon</span></html>",
			       "<html><span style = 'font-size:24pt;'>Star</span></html>"
	};
	DefaultComboBoxModel model;
	
	DrawManager dr;
	
	FigureChoise(DrawManager d){
		dr = d;
		
		model = new DefaultComboBoxModel(figuredata);
		this.setModel(model);
		this.setPreferredSize(new Dimension(120,50));
	}
	
	public void changeFigure() {
		if(model.getSelectedItem() == "<html><span style = 'font-size:24pt;'>Circle</span></html>") {
			dr.setFigure(0);
		}else if(model.getSelectedItem() == "<html><span style = 'font-size:24pt;'>Box</span></html>") {
			dr.setFigure(1);
		}else if(model.getSelectedItem() == "<html><span style = 'font-size:24pt;'>Triangle</span></html>") {
			dr.setFigure(2);
		}else if(model.getSelectedItem() == "<html><span style = 'font-size:24pt;'>Pentagon</span></html>") {
			dr.setFigure(3);
		}else if(model.getSelectedItem() == "<html><span style = 'font-size:24pt;'>Star</span></html>") {
			dr.setFigure(4);
		}
	}
}

class FillButton extends JButton{
    public DrawManager dr;

    FillButton(DrawManager d){
	dr = d;

	this.setText("<html><span style = 'font-size:24pt;'>fill</span></html>");
	this.setPreferredSize(new Dimension(120, 100));
    }

    public void changeFill(){
	if(dr.getFill() == 0){
	    dr.setFill(1);
	    this.setText("<html><span style = 'font-size:24pt;'>fill</span></html>");
	}else{
	    dr.setFill(0);
	    this.setText("<html><span style = 'font-size:24pt;'>not fill</span></html>");
	}
    }
}

class UndoButton extends JButton{
	UndoButton(){
		this.setText("<html><span style = 'font-size:24pt;'>Undo</span></html>");
		this.setPreferredSize(new Dimension(120, 50));
	}
}

class KeepButton extends JButton {
    KeepButton(){
    	this.setText("<html><span style = 'font-size:24pt;'>keep</span></html>");
    	this.setPreferredSize(new Dimension(120, 50));
    }
}

class RestartButton extends JButton {
    RestartButton(){
    	this.setText("<html><span style = 'font-size:24pt;'>Restart</span></html>");
    	this.setPreferredSize(new Dimension(120, 50));
    }
}

class FinishButton extends JButton{
	FinishButton(){
		this.setText("<html><span style = 'font-size:24pt;'>Finish</span></html>");
    	this.setPreferredSize(new Dimension(120, 50));
	}
}

class AllClearButton extends JButton{
        AllClearButton(){
		this.setText("<html><span style = 'font-size:18pt;'>AllCLEAR</span></html>");
    	this.setPreferredSize(new Dimension(120, 50));
	}
}

//表示用のパネル

class SizeStates extends JPanel{//表示のみ
	SizeStates(){
		JLabel l = new JLabel("<html><span style = 'font-size:24pt;'>Size</span></html>");

		this.add(l, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(120,50));
	}
}

class ModeStates extends JPanel{//表示のみ
	ModeStates(){
		JLabel l = new JLabel("<html><span style = 'font-size:24pt;'>Mode</span></html>");
		
		this.add(l, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(120, 50));
	}
}

class FigureStates extends JPanel{//表示のみ
	FigureStates(){
		JLabel l = new JLabel("<html><span style = 'font-size:24pt;'>figure</span></html>");
		
		this.add(l, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(120, 50));
	}
}
