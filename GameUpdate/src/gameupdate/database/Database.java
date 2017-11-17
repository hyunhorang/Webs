package gameupdate.database;

import gameupdate.GameUpdate;
import gameupdate.lib.FileIO;
import gameupdate.lib.MySQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database extends MySQL {
  
  public Database() {
    super(
      DatabaseConfig.URL,
      DatabaseConfig.PORT,
      DatabaseConfig.DATABASE_NAME,
      DatabaseConfig.USERNAME,
      DatabaseConfig.PASSWORD
    );
  }
  
  public GameDatabaseTable gameDatabaseTable() {
    GameDatabaseTable gameDatabaseTable = null;
    ResultSet rs = this.pureSQLSelect(
      "SELECT * FROM " + GameUpdate.GAME_NAME + " " + 
      "WHERE computer_number = " + GameUpdate.COMPUTER_NUMBER
    );
    try {
      while(rs.next()) {
        gameDatabaseTable = new GameDatabaseTable(
          rs.getInt("id"),
          rs.getInt("computer_number"),
          rs.getString("is_updated"),
          rs.getString("is_blocked"),
          rs.getString("filepath_client"),
          rs.getString("filepath_server"),
          rs.getString("server_username"),
          rs.getString("server_password"),
          rs.getString("net_use_path"),
          rs.getInt("timeout"),
          new FileIO().truePath() + "\\" + rs.getString("autohotkey_exe_path"),
          rs.getInt("total_size")
        );
      }
      this.close();
    } catch (SQLException ex) {
      Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
      System.exit(1);
    }
    return gameDatabaseTable;
  }
  
  public void setIs_updated(String value) {
    this.pureSQLUpdate(
      "UPDATE " + GameUpdate.GAME_NAME + 
      " SET is_updated = '" + value + 
      "' WHERE computer_number = " + GameUpdate.COMPUTER_NUMBER
    );
  }

  public void setBlock(String value) {
    String query = "UPDATE " + GameUpdate.GAME_NAME + " ";
    query += "SET is_blocked = '" + value + "' ";
    query += "WHERE computer_number = " + GameUpdate.COMPUTER_NUMBER;
    this.pureSQLUpdate(query);
  }
  
}
