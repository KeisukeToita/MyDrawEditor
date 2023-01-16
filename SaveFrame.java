import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SaveFrame extends JFrame {
	
	DrawManager dr;
	
	SaveFrame(DrawManager d){
		dr = d;
		
		this.add(new SavePanel(dr, this), BorderLayout.CENTER);
		
		this.setTitle("Save");
		this.setSize(300, 300);
		this.setVisible(true);
	}

}

class SavePanel extends JPanel implements ActionListener{
	
	DrawManager dr;
	SaveFrame s;
	JTextField ngf;
	JButton Finb;
	JButton Canb;
	
	SavePanel(DrawManager d, SaveFrame s ){
		dr = d;
		this.s = s;
		
		init();//部品のはめ込みについて
		
		this.setSize(300, 300);
	}
	
	void init() {
		
		this.setLayout(new GridLayout(4, 1));
		
		JLabel exp = new JLabel("<html><p><span style = 'font-size:15pt;'>1. please write the picture's name<br>2. please add '.png' in name of tail</span></p></html>");
		this.ngf = new JTextField();//name get field
		Finb = new JButton("Finish");
		Canb = new JButton("Cancel");
		
		this.add(exp);
		this.add(ngf);
		this.add(Finb);
		this.add(Canb);
		
		ngf.setFont(new Font("Arial", Font.PLAIN, 24));
		
		Finb.addActionListener(this);
		Canb.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Finb) {
			if(ngf.getText().indexOf(".png") == -1) {
				ngf.setText("add '.png' in name of tail!!");
				return;
			}
			dr.finish(ngf.getText());
			s.dispose();
		}else if(e.getSource() == Canb) {
			s.dispose();
		}
	}
	
}
