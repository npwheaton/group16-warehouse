import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class loginGUI extends JFrame{

private BorderLayout b;
private JTextField jt;
private JButton button0,button1, button2, button3;
private JPanel panel;
private JLabel jl;
private ImageIcon icon;

public loginGUI(){
super("LOGIN MENU");
b = new BorderLayout();
setLayout(b);
jt = new JTextField("Welcome to the Group 16 Warehouse");
button0 = new JButton("CLERK");
button1= new JButton("CLIENT");
button2= new JButton("MANAGER");
button3= new JButton("EXIT");
panel = new JPanel();
icon = new ImageIcon("warehouse.png");
jl = new JLabel(icon,JLabel.CENTER);
button0.setToolTipText("Go to Clerk menu PIN is clerk");
button1.setToolTipText("Go to Client menu");
button2.setToolTipText("Go to Manager menu PIN is mananger");
jt.setEditable(false);
setBackground(Color.WHITE);
panel.setBackground(Color.GRAY);
panel.add(button0);
panel.add(button1);
panel.add(button2);
add(button3, b.SOUTH);
add(panel, b.CENTER);
panel.add(jl,b.CENTER);
add(jt,b.NORTH);
setVisible(false);
}
public String input(String Prompt){
	String ans = JOptionPane.showInputDialog(Prompt);
	return ans;
}

public void output(String touser){
	jt.setText(touser);
}

public void displayB(){
     setSize(500,500);
     setLocationRelativeTo(null);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setVisible(true);
     toFront();
button0.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent event){
        Loginstate.instance().clerk();
            }
        }
    );
button1.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent event){
        Loginstate.instance().client();
            }
        }
    );
button2.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
        Loginstate.instance().manager();
            }
        }
    );
button3.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
        Loginstate.instance().exit();
            }
        }
    );

}




}