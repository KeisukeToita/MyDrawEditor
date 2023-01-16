import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditPanel extends JPanel implements ActionListener{
	
	public DrawManager d;
	public GridBagLayout gbl;
	
	public ColorChooser cc;
	public ColorPanel cp;
	public SizeStates ss;
	public SizePlusButton spb;
    public SizeMinusButton smb;
	public SizeField sf;
	public ModeStates ms;
	public FigureStates fs;
	public ModeChoice mc;
	public FigureChoise fc;
	public UndoButton ub;
	public FillButton fb;
	public Background bg;
	
	public KeepButton kb;
	public RestartButton rb;
	public FinishButton finb;
    public AllClearButton acb;
	
	public EditPanel(DrawManager dr){
		
		d = dr;
		gbl = new GridBagLayout();
		
		init(gbl, d);
		
		
		this.setPreferredSize(new Dimension(1200, 150));
		this.setBackground(Color.CYAN);
		
		cc.addActionListener(this);
		sf.addActionListener(this);
		mc.addActionListener(this);
		fc.addActionListener(this);
		ub.addActionListener(this);
		fb.addActionListener(this);
		smb.addActionListener(this);
		spb.addActionListener(this);
		bg.addActionListener(this);
		kb.addActionListener(this);
		rb.addActionListener(this);
		finb.addActionListener(this);
		acb.addActionListener(this);
	}
	
	protected void addComponent(Component c, int x, int y, int w, int h) {
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbl.setConstraints(c, gbc);
		
		
		add(c);
	}
	
	public void init(GridBagLayout gbl, DrawManager d) {
		
		setLayout(gbl);
		
		cc = new ColorChooser(d);//上段
		ss = new SizeStates();
		ms = new ModeStates();
		fs = new FigureStates();
		fb = new FillButton(d);
		bg = new Background(d);
		
		cp = new ColorPanel(d);//下段
		sf = new SizeField(d);
		mc = new ModeChoice(d);
		fc = new FigureChoise(d);
		ub = new UndoButton();
		acb = new AllClearButton();
		
		spb = new SizePlusButton(d);
		smb = new SizeMinusButton(d);
		
		kb = new KeepButton();
		rb = new RestartButton();
		finb = new FinishButton();
		
		addComponent(cc, 0, 0, 2, 1);
		addComponent(cp, 0, 1, 2, 1);
		addComponent(ss, 2, 0, 2, 1);
		addComponent(sf, 2, 1, 2, 1);
		addComponent(spb, 2, 2, 1, 1);
		addComponent(smb, 3, 2, 1, 1);
		addComponent(ms, 4, 0, 2, 1);
		addComponent(fs, 6, 0, 2, 1);
		addComponent(mc, 4, 1, 2, 1);
		addComponent(fc, 6, 1, 2, 1);
		addComponent(ub, 10, 1, 2, 1);
		addComponent(fb, 8, 0, 2, 2);
		addComponent(bg, 12, 0, 2, 1);
		addComponent(kb, 14, 0, 2, 1);
		addComponent(rb, 14, 1, 2, 1);
		addComponent(finb, 14, 2, 2, 1);
		addComponent(acb, 10, 0, 2, 1);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cc) {
			cc.ChangeColor();
		}else if(e.getSource() == ub){
			d.undo();
		}else if (e.getSource() == fb) {
			fb.changeFill();
		}else if(e.getSource() == spb){
		    spb.SizePlus();
		}else if(e.getSource() == smb){
		    smb.SizeMinus();
		}else if(e.getSource() == sf){
		    sf.ChangeSize();
		}else if (e.getSource() == bg) {
			bg.ChangeBackground();
		}else if(e.getSource() == kb) {
			d.keep();
		}else if(e.getSource() == rb) {
			d.restart();
		}else if(e.getSource() == finb) {
			new SaveFrame(d);
		}else if(e.getSource() == acb){
		    d.allclear();
		}
		mc.changeMode();
		fc.changeFigure();
	}
	
}
