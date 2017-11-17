package gameupdate.lib;

import gameupdate.GameUpdate;
import gameupdate.database.GameDatabaseTable;
import gameupdate.view.UpdatingSign;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WindowsShell {
  
  private ProcessBuilder processBuilder;
  private ArrayList<String> processResult;
  
  private UpdatingSign updatingSign;
  private GameDatabaseTable gameDatabaseTable;
  
  public WindowsShell(GameDatabaseTable gameDatabaseTable) {
    this.processResult = null;
    this.updatingSign = null;
    this.gameDatabaseTable = gameDatabaseTable;
  }
  
  public ArrayList<String> getProcessResult() {
    return this.processResult;
  }
  
  public void start(String command, boolean result, boolean recordCounter) {
    System.out.println("Executing command = '" + command + "'");
    this.processBuilder = new ProcessBuilder("CMD", "/C", command);
    try {
      Process process = this.processBuilder.start();
      boolean waitFor = false;
      if(waitFor) {
        try {
          int resultCode = process.waitFor();
        } catch (InterruptedException ex) {
          Logger.getLogger(WindowsShell.class.getName()).log(Level.SEVERE, null, ex);
          System.exit(1);
        }
      }
      if(result) {
        this.processResult = new ArrayList<>();
        try(
          BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(process.getInputStream())
          )
        ) {
          String line;
          double counter = 0;
          if(this.updatingSign != null) {
            this.updatingSign.setProgressBarValue((int)counter);
          }
          while((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            this.processResult.add(line);
            if(this.updatingSign != null) {
              double total = 0;
              if(GameUpdate.GAME_NAME.equals("league_of_legends")) {
                total = this.gameDatabaseTable.getTotal_size();
                this.updatingSign.setProgressBarValue((int)(counter / total * 100));
              }
            }
            counter++;
          }
          if(recordCounter) GameUpdate.DB.pureSQLUpdate("UPDATE " + GameUpdate.GAME_NAME + " SET total_size = " + counter);
          System.out.println("Counter: " + counter);
          if(this.updatingSign != null) this.updatingSign.dispose();
        }
      }
    } catch (IOException ex) {
      Logger.getLogger(WindowsShell.class.getName()).log(Level.SEVERE, null, ex);
      System.exit(1);
    }
  }
  
  public void robocopy(String source, String destination, int waitTime) {
    String command = "robocopy \"" + source + "\" " + "\"" + destination + "\" /MIR /w:" + waitTime;
    this.start(command, true, true);
  }
  
  public void netPermission(String serverAddress, String username, String password) {
    String command = "net use \"" + serverAddress + "\" " + password + " /user:" + username;
    this.start(command, true, false);
  }
  
  public void cancelNetPermission(String serverAddress) {
    String command = "net use \"" + serverAddress + "\" /d";
    this.start(command, true, false);
  }
  
  public void setView(UpdatingSign updatingSign) {
    this.updatingSign = updatingSign;
  }
  
}
