package steamlogin.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import steamlogin.database.table.Account;
import steamlogin.lib.MySQL;

public class Database extends MySQL {
  
  public static final String URL = "49.255.145.181";
  public static final String PORT = "3306";
  public static final String DATABASE_NAME = "steam_login";
  public static final String USERNAME = "terawebs";
  public static final String PASSWORD = "terawebs2135";

  public Database() {
    super(Database.URL, Database.PORT, Database.DATABASE_NAME, Database.USERNAME, Database.PASSWORD);
  }
  
  public Account getValidAccount() {
    Account account = null;
    String query = "SELECT * FROM account WHERE is_using = 'false'";
    ResultSet rs = this.pureSQLSelect(query);
    try {
      while(rs.next()) {
        account = new Account(
          rs.getInt("id"),
          rs.getString("username"),
          rs.getString("password"),
          rs.getString("is_using"),
          rs.getString("using_computer")
        );
        break;
      }
      this.close();
    } catch(SQLException ex) {
      Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
      System.exit(1);
    }
    return account;
  }
  
  public void setUsing(String value, int id) {
    String query = "UPDATE account SET is_using = '" + value + "' WHERE id = " + id;
    this.pureSQLUpdate(query);
  }
  
  public void setUsingComputer(String value, int id) {
    String query = "UPDATE account SET using_computer = '" + value + "' WHERE id = " + id;
    this.pureSQLUpdate(query);
  }
  
  public String getSteamExePath() {
    String query = "SELECT steam_exe_path FROM variable";
    ResultSet rs = this.pureSQLSelect(query);
    String result = "";
    try {
      while(rs.next()) {
        result = rs.getString("steam_exe_path");
      }
      this.close();
    } catch (SQLException ex) {
      Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
      System.exit(1);
    }
    return result;
  }
  
  public String getImageIconPath() {
    String query = "SELECT image_icon_path FROM variable";
    ResultSet rs = this.pureSQLSelect(query);
    String result = "";
    try {
      while(rs.next()) {
        result = rs.getString("image_icon_path");
      }
      this.close();
    } catch (SQLException ex) {
      Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
      System.exit(1);
    }
    return result;
  }
  
}
