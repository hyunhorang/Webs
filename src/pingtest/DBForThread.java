package pingtest;

import java.sql.ResultSet;
import pingtest.lib.MySQL;

public class DBForThread extends MySQL {
  
  public DBForThread() {
    super("localhost", "3306", "websadmin", "terawebs", "terawebs2135");
  }
  
  public ResultSet selectRange(String query) {
    return this.pureSQLSelect(query);
  }
  
  public void updateIsOn(String query) {
    this.pureSQLUpdate(query);
  }
  
  public void selfClose() {
    this.close();
  }
  
}
