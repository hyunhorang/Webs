package gameupdate.database;

public class GameDatabaseTable {
  
  private int id;
  private int computer_number;
  private String is_updated;
  private String is_blocked;
  private String filepath_client;
  private String filepath_server;
  private String server_username;
  private String server_password;
  private String net_use_path;
  private int timeout;
  private String autohotkey_exe_path;
  private int total_size;
  
  public GameDatabaseTable(
    int id, 
    int computer_number,
    String is_updated, 
    String is_blocked, 
    String filepath_client, 
    String filepath_server, 
    String server_username, 
    String server_password, 
    String net_use_path, 
    int timeout, 
    String autohotkey_exe_path,
    int total_size
  ) {
    this.id = id;
    this.computer_number = computer_number;
    this.is_updated = is_updated;
    this.is_blocked = is_blocked;
    this.filepath_client = filepath_client;
    this.filepath_server = filepath_server;
    this.server_username = server_username;
    this.server_password = server_password;
    this.net_use_path = net_use_path;
    this.timeout = timeout;
    this.autohotkey_exe_path = autohotkey_exe_path;
    this.total_size = total_size;
  }

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return the computer_number
   */
  public int getComputer_number() {
    return computer_number;
  }

  /**
   * @param computer_number the computer_number to set
   */
  public void setComputer_number(int computer_number) {
    this.computer_number = computer_number;
  }

  /**
   * @return the is_updated
   */
  public String getIs_updated() {
    return is_updated;
  }

  /**
   * @param is_updated the is_updated to set
   */
  public void setIs_updated(String is_updated) {
    this.is_updated = is_updated;
  }

  /**
   * @return the is_blocked
   */
  public String getIs_blocked() {
    return is_blocked;
  }

  /**
   * @param is_blocked the is_blocked to set
   */
  public void setIs_blocked(String is_blocked) {
    this.is_blocked = is_blocked;
  }

  /**
   * @return the filepath_client
   */
  public String getFilepath_client() {
    return filepath_client;
  }

  /**
   * @param filepath_client the filepath_client to set
   */
  public void setFilepath_client(String filepath_client) {
    this.filepath_client = filepath_client;
  }

  /**
   * @return the filepath_server
   */
  public String getFilepath_server() {
    return filepath_server;
  }

  /**
   * @param filepath_server the filepath_server to set
   */
  public void setFilepath_server(String filepath_server) {
    this.filepath_server = filepath_server;
  }

  /**
   * @return the server_username
   */
  public String getServer_username() {
    return server_username;
  }

  /**
   * @param server_username the server_username to set
   */
  public void setServer_username(String server_username) {
    this.server_username = server_username;
  }

  /**
   * @return the server_password
   */
  public String getServer_password() {
    return server_password;
  }

  /**
   * @param server_password the server_password to set
   */
  public void setServer_password(String server_password) {
    this.server_password = server_password;
  }

  /**
   * @return the net_use_path
   */
  public String getNet_use_path() {
    return net_use_path;
  }

  /**
   * @param net_use_path the net_use_path to set
   */
  public void setNet_use_path(String net_use_path) {
    this.net_use_path = net_use_path;
  }

  /**
   * @return the timeout
   */
  public int getTimeout() {
    return timeout;
  }

  /**
   * @param timeout the timeout to set
   */
  public void setTimeout(int timeout) {
    this.timeout = timeout;
  }

  /**
   * @return the autohotkey_exe_path
   */
  public String getAutohotkey_exe_path() {
    return autohotkey_exe_path;
  }

  /**
   * @param autohotkey_exe_path the autohotkey_exe_path to set
   */
  public void setAutohotkey_exe_path(String autohotkey_exe_path) {
    this.autohotkey_exe_path = autohotkey_exe_path;
  }

  /**
   * @return the total_size
   */
  public int getTotal_size() {
    return total_size;
  }

  /**
   * @param total_size the total_size to set
   */
  public void setTotal_size(int total_size) {
    this.total_size = total_size;
  }
  
}
