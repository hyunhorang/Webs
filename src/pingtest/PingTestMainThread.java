package pingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PingTestMainThread implements Runnable {
  
  private Thread thread;
  private boolean running;
  private int[] fromTo;
  private DBForThread db;
  private ArrayList<String> pingResults;
  
  public PingTestMainThread(int from, int to) {
    this.thread = null;
    this.running = false;
    this.fromTo = new int[2];
    this.fromTo[0] = from;
    this.fromTo[1] = to;
    this.db = new DBForThread();
    this.pingResults = new ArrayList<>();
  }

  @Override
  public void run() {
    this.running = true;
    while(this.running) {
      ResultSet rs = this.db.selectRange("SELECT pc_number, ipv4 FROM client_computer WHERE pc_number >= " + this.fromTo[0] + " AND pc_number <= " + this.fromTo[1]);
      try {
        while(rs.next()) {
          int pcNum = rs.getInt("pc_number");
          for(String line : this.runCommand("ping " + rs.getString("ipv4") + " -c 1 -W 1", true)) {
            if(line.contains("100% packet loss")) {
              System.out.print("PC" + pcNum + "(OFF) ");
              this.pingResults.add(pcNum + ":off");
              //this.pureSQLUpdate("UPDATE client_computer SET is_on = 'false' WHERE pc_number = " + pcNum);
            } else if(line.contains("0% packet loss")) {
              System.out.print("PC" + pcNum + "(ON) ");
              this.pingResults.add(pcNum + ":on");
            }
          }
        }
        this.db.selfClose();
      } catch (SQLException ex) {
        System.out.println(ex.getMessage());
      }
      for(String pingResult : this.pingResults) {
        System.out.println("UPDATING DATABASE");
        String[] splited = pingResult.split(":");
        this.db.updateIsOn("UPDATE client_computer SET is_on = '" + splited[1] + "' WHERE pc_number = " + splited[0]);
        //this.sleep(500);
      }
      this.pingResults = null;
      this.pingResults = new ArrayList<>();
      this.sleep(700);
    }
  }
  
  public void start() {
    this.thread = new Thread(this);
    this.thread.start();
  }
  
  public void stop() {
    this.running = false;
  }
  
  private void sleep(int millisecond) {
    try {
      Thread.sleep(millisecond);
    } catch (InterruptedException ex) {
      System.out.println(ex.getMessage());
    }
  }
  
  private ArrayList<String> runCommand(String command, boolean result) {
    ArrayList<String> processResult = null;
    ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "-c", command);
    try {
      Process process = processBuilder.start();
      boolean waitFor = false;
      if(waitFor) {
        try {
          int resultCode = process.waitFor();
        } catch (InterruptedException ex) {
          System.out.println(ex.getMessage());
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
            // System.out.println(line);
            processResult.add(line);
          }
        }
      }
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
    return processResult;
  }
  
}
