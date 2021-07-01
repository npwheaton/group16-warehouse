import javax.swing.*;
import java.awt.*;
public class ClerkGUI extends JFrame{
private static final long serialVersionUID = 1L;
private JTextArea ja;
private JScrollPane jscp;
private BorderLayout b;

public ClerkGUI(String x){
super(x);
b = new BorderLayout();
setLayout(b);
ja = new JTextArea();
jscp = new JScrollPane(ja);
ja.setEditable(false);
ja.setBackground(Color.WHITE);
ja.setForeground(Color.BLACK);
add(jscp, b.CENTER);
setVisible(false);
}
public String input(String Prompt){
	String ans = JOptionPane.showInputDialog(Prompt);
	return ans;
}

public void output(String touser){
	String temp = touser +"\n";
	ja.append(temp);
}

public void display(){
setBackground(Color.GRAY);
setSize(500,500);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);
toFront();
setVisible(true);
}



}