package gameupdate.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class UpdatingSign extends JFrame {
  
  private JProgressBar progressBar;
  private JLabel labelEng;
  private JLabel lableKor;
  
  public UpdatingSign() {
    this.initFrame();
    this.setProgressBar();
    this.setLabelEng();
    this.setLabelKor();
    this.makeVisible();
  }
  
  public void setProgressBarValue(int percentage) {
    this.progressBar.setValue(percentage);
  }
  
  private void initFrame() {
    this.setTitle("TeraWebstation");
    this.setSize(350, 150);
    // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.preventFromExit();
    this.setResizable(false);
    this.centerFrame();
    this.setLayout(null);
  }
  
  private void setLabelKor() {
    this.lableKor = new JLabel();
    this.lableKor.setText("게임이 업데이트 중입니다. 잠시만 기다려주세요.");
    this.lableKor.setBounds(5, 28, 350, 20);
    this.add(this.lableKor);
  }
  
  private void setLabelEng() {
    this.labelEng = new JLabel();
    this.labelEng.setText("Please wait. The game is updating.");
    this.labelEng.setBounds(5, 5, 200, 20);
    this.add(this.labelEng);
  }
  
  private void setProgressBar() {
    this.progressBar = new JProgressBar();
    this.progressBar.setBounds(75, 65, 200, 20);
    this.progressBar.setValue(0);
    this.progressBar.setStringPainted(true);
    this.add(this.progressBar);
  }
  
  private void makeVisible() {
    this.setVisible(true);
  }
  
  private void centerFrame() {
    this.setLocationRelativeTo(null); // Centers the frame
  }
  
  private void preventFromExit() {
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent ev) {
         //frame.dispose();
      }
    });
  }
  
}
