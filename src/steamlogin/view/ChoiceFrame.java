package steamlogin.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ChoiceFrame extends JFrame {
  
  private String iconPath;
  
  private JButton button1;
  private JButton button2;
  private JButton closeButton;
  
  private JLabel label1;
  private JLabel label2;
  
  public ChoiceFrame(String iconPath) {
    this.iconPath = iconPath;
    this.init();
  }
  
  private void init() {
    this.setFrame();
    this.setButton1();
    this.setButton2();
    this.setCloseButton();
    this.setIcon();
    // this.setLabel1();
    // this.setLabel2();
    this.makeVisible();
  }
  
  private void setFrame() {
    this.setTitle("TeraWebstation");
    // this.setSize(420, 150);
    this.setSize(700, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.centerFrame();
    this.getContentPane().setBackground(new Color(32, 36, 44));
    // this.getContentPane().setBackground(new Color(0, 0, 0, 0));
    this.setUndecorated(true); // it removes the window decorations like close maximise and minimise(titlebar). This itself disables the dragging only with mouse events.
    getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
    // AWTUtilities.setWindowOpacity(this, 0);
    this.setLayout(null);
  }
  
  private void centerFrame() {
    this.setLocationRelativeTo(null); // Centers the frame
  }
  
  private void makeVisible() {
    this.setVisible(true);
  }
  
  private void setButton1() {
    // this.button1 = new JButton("I have my own account");
    this.button1 = new JButton(new ImageIcon(this.iconPath + "\\steam1.png"));
    // this.button1.setBounds(20, 45, 180, 35);
    this.button1.setBounds(35, 50, 300, 300);
    this.button1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
    this.button1.setFocusPainted(false);
    this.button1.setMargin(new Insets(0, 0, 0, 0));
    this.button1.setContentAreaFilled(false);
    this.button1.setBorderPainted(false);
    this.button1.setOpaque(false);
    
    this.add(this.button1);
  }
  
  private void setButton2() {
    // this.button2 = new JButton("Use Webs' account");
    this.button2 = new JButton(new ImageIcon(this.iconPath + "\\steam2.png"));
    // this.button2.setBounds(220, 45, 180, 35);
    this.button2.setBounds(365, 50, 300, 300);
    this.button2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
    this.button2.setFocusPainted(false);
    this.button2.setMargin(new Insets(0, 0, 0, 0));
    this.button2.setContentAreaFilled(false);
    this.button2.setBorderPainted(false);
    this.button2.setOpaque(false);
    
    this.add(this.button2);
  }
  
  public void setCloseButton() {
    this.closeButton = new JButton(new ImageIcon(this.iconPath + "\\white_close_button.png"));
    this.closeButton.setBounds(650, 15, 30, 30);
    this.closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
    this.closeButton.setFocusPainted(false);
    this.closeButton.setMargin(new Insets(0, 0, 0, 0));
    this.closeButton.setContentAreaFilled(false);
    this.closeButton.setBorderPainted(false);
    this.closeButton.setOpaque(false);
    
    this.add(this.closeButton);
  }
  
  public JButton getButton1() {
    return this.button1;
  }
  
  public JButton getButton2() {
    return this.button2;
  }
  
  public JButton getCloseButton() {
    return this.closeButton;
  }
  
  public void setLabel1() {
    this.label1 = new JLabel("Use my own account");
    this.label1.setBounds(35, 170, 150, 30);
    this.add(this.label1);
  }
  
  public void setLabel2() {
    this.label2 = new JLabel("Use Webs' account");
    this.label2.setBounds(210, 170, 150, 30);
    this.add(this.label2);
  }
  
  public void setIcon() {
    ImageIcon img = new ImageIcon(this.iconPath + "\\steam.png");
    this.setIconImage(img.getImage());
  }
  
}
