package steamlogin;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import steamlogin.database.Database;
import steamlogin.database.SteamDatabase;
import steamlogin.database.table.Account;
import steamlogin.view.ChoiceFrame;

public class SteamLogin {
  
  public static void main(String[] args) {
    SteamLogin steamLogin = new SteamLogin(Integer.parseInt(args[0]), args[1]);
    steamLogin.run();
  }
  
  private Database db;
  public static int computerNumber;
  public static String appType;
  
  public SteamLogin(int computerNumber, String appType) {
    this.db = new Database();
    SteamLogin.computerNumber = computerNumber;
    SteamLogin.appType = appType;
  }
  
  public void run() {
    
    if(SteamLogin.appType.equals("login")) {
      ChoiceFrame choiceFrame = new ChoiceFrame(this.db.getImageIconPath());
      choiceFrame.getButton1().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // I have my own account
          Database database = new Database();
          String exePath = database.getSteamExePath();
          SteamLogin.this.runCommand("\"" + exePath + "\"", false);
          choiceFrame.dispose();
        }
      });
      choiceFrame.getButton2().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // User webs account
          Database database = new Database();
          Account account  = database.getValidAccount();
          database.setUsing("true", account.getId());
          database.setUsingComputer("pc" + SteamLogin.computerNumber, account.getId());
          String exePath = database.getSteamExePath();
          SteamLogin.this.runCommand("\"" + exePath + "\" -login " + account.getUsername() + " " + account.getPassword(), false);
          choiceFrame.dispose();
        }
      });
      choiceFrame.getCloseButton().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          choiceFrame.dispose();
        }
      });
    } else if(SteamLogin.appType.equals("free")) {
      SteamDatabase steamDatabase = new SteamDatabase();
      steamDatabase.freeAccount("pc" + SteamLogin.computerNumber);
    }
    
    
    // Account validAccount = this.db.getValidAccount();
    
    // 모든 아이디를 사용중인 경우
    /*
    if(validAccount == null) {
      return;
    }
    */
    
    // int id = validAccount.getId();
    // String username = validAccount.getUsername();
    // String password = validAccount.getPassword();
    
    //this.db.setUsing("true", id);
    //this.db.setUsingComputer("pc" + computerNumber , id);
    
    // 실행중 이라는 메세지창
    
    // 스팀실행
    // String command = "\"C:\\Program Files\\Steam\\Steam.exe\" -login " + username + " " + password;
    // this.runCommand(command, false);
    
    // 인풋 2초동안 막는 오토핫키 실행
    
    /*
    Robot robot = null;
    try {
      robot = new Robot();
      robot.setAutoDelay(40);
      robot.setAutoWaitForIdle(true);
    } catch (AWTException ex) {
      Logger.getLogger(SteamLogin.class.getName()).log(Level.SEVERE, null, ex);
      System.exit(1);
    }
    */
    
  }
  
  private void leftClick(Robot robot)
  {
    robot.mousePress(InputEvent.BUTTON1_MASK);
    robot.delay(200);
    robot.mouseRelease(InputEvent.BUTTON1_MASK);
    robot.delay(200);
  }
  
  private void type(Robot robot, int i)
  {
    robot.delay(40);
    robot.keyPress(i);
    robot.keyRelease(i);
  }
  
  private void type(Robot robot, String s)
  {
    byte[] bytes = s.getBytes();
    for (byte b : bytes)
    {
      int code = b;
      // keycode only handles [A-Z] (which is ASCII decimal [65-90])
      if (code > 96 && code < 123) code = code - 32;
      robot.delay(40);
      robot.keyPress(code);
      robot.keyRelease(code);
    }
  }
  
  public ArrayList<String> runCommand(String command, boolean result) {
    ArrayList<String> processResult = null;
    ProcessBuilder processBuilder = new ProcessBuilder("CMD", "/C", command);
    try {
      Process process = processBuilder.start();
      boolean waitFor = false;
      if(waitFor) {
        try {
          int resultCode = process.waitFor();
        } catch (InterruptedException ex) {
          Logger.getLogger(SteamLogin.class.getName()).log(Level.SEVERE, null, ex);
          System.exit(1);
        }
      }
      if(result) {
        try(
          BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(process.getInputStream())
          )
        ) {
          processResult = new ArrayList<>();
          String line;
          while((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            processResult.add(line);
          }
        }
      }
    } catch (IOException ex) {
      Logger.getLogger(SteamLogin.class.getName()).log(Level.SEVERE, null, ex);
      System.exit(1);
    }
    return processResult;
  }
  
}
