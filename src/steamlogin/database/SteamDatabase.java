package steamlogin.database;

import steamlogin.lib.MySQL;

public class SteamDatabase extends MySQL {
  
  public static final String URL = "49.255.145.181";
  public static final String PORT = "3306";
  public static final String DATABASE_NAME = "steam_login";
  public static final String USERNAME = "terawebs";
  public static final String PASSWORD = "terawebs2135";
  
  public SteamDatabase() {
    super(SteamDatabase.URL, SteamDatabase.PORT, SteamDatabase.DATABASE_NAME, SteamDatabase.USERNAME, SteamDatabase.PASSWORD);
  }

  public void freeAccount(String pc) {
    String query = "UPDATE account SET is_using = 'false', using_computer = 'none' WHERE using_computer = '" + pc + "'";
    this.pureSQLUpdate(query);
  }
  
  
  
}
