package pingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pingtest.lib.MySQL;

public class PingTest extends MySQL {
  
  public static void main(String[] args) throws InterruptedException, SQLException {
    PingTest pingTest = new PingTest();
    pingTest.run();
  }
  
  public static int lineCount = 0;
  public static ArrayList<String> pingResults = new ArrayList<>();

  public PingTest() {
    super("127.0.0.1", "3306", "websadmin", "terawebs", "terawebs2135");
  }
  
  private void run() throws SQLException {
    new PingTestMainThread(1, 18).start();
    new PingTestMainThread(19, 38).start();
    new PingTestMainThread(39, 62).start();
    new PingTestMainThread(63, 88).start();
    /*
    ResultSet rs = this.pureSQLSelect("SELECT pc_number, ipv4 FROM client_computer");
    while(rs.next()) {
      int pcNum = rs.getInt("pc_number");
      for(String line : this.runCommand("ping " + rs.getString("ipv4") + " -c 1 -W 1", true)) {
        if(line.contains("100% packet loss")) {
          System.out.print("PC" + pcNum + "(OFF) ");
          PingTest.pingResults.add(pcNum + ":off");
          //this.pureSQLUpdate("UPDATE client_computer SET is_on = 'false' WHERE pc_number = " + pcNum);
        } else if(line.contains("0% packet loss")) {
          System.out.print("PC" + pcNum + "(ON) ");
          PingTest.pingResults.add(pcNum + ":on");
        }
      }
    }
    System.out.println();
    this.close();
    for(String pingResult : PingTest.pingResults) {
      System.out.println("UPDATING DATABASE");
      String[] splited = pingResult.split(":");
      this.pureSQLUpdate("UPDATE client_computer SET is_on = '" + splited[1] + "' WHERE pc_number = " + splited[0]);
    }
    */
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
            // System.out.println(line);
            processResult.add(line);
          }
        }
      }
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
      System.exit(1);
    }
    return processResult;
  }
  
}
