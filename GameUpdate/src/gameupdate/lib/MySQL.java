package gameupdate.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQL {
  
  protected String url;
  protected String port;
  protected String database;
  protected String username;
  protected String password;

  protected Connection connection;
  protected Statement statement;
  protected PreparedStatement prepared;
  protected ResultSet resultSet;
  
  public MySQL(String url, String port, String database, String username, String password) {
    this.url = url;
    this.port = port;
    this.database = database;
    this.username = username;
    this.password = password;
    this.connection = null;
    this.statement = null;
    this.prepared = null;
    this.resultSet = null;
    this.loadDriver();
  }
  
  private boolean loadDriver() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch(ClassNotFoundException ex) {
      Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
      System.exit(1);
    }
    return true;
  }
  
  protected boolean connect() {
    try {
      String metaData = "";
      metaData += "jdbc:mysql://";
      metaData += this.url + ":";
      metaData += this.port + "/";
      metaData += this.database;
      this.connection = DriverManager.getConnection(metaData, this.username, this.password);
    } catch(SQLException ex) {
      Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
      System.exit(1);
    }
    return this.connection != null;
  }
  
  protected boolean close() {
    try {
      if(this.connection != null) {
        this.connection.close();
        this.connection = null;
      }
      if(this.statement != null) {
        this.statement.close();
        this.statement = null;
      }
      if(this.prepared != null) {
        this.prepared.close();
        this.prepared = null;
      }
      if(this.resultSet != null) {
        this.resultSet.close();
        this.resultSet = null;
      }
    } catch(SQLException ex) {
      Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
    return true;
  }
  
  protected void changeSetting(String url, String port, String database, String username, String password) {
    this.url = url;
    this.port = port;
    this.database = database;
    this.username = username;
    this.password = password;
  }
  
  protected synchronized boolean checkConnectivity() {
    return this.connect();
  }
  
  protected synchronized ResultSet pureSQLSelect(String query) {
    if(this.connect()) {
      try {
        this.statement = this.connection.createStatement();
        this.resultSet = this.statement.executeQuery(query);
      } catch(SQLException ex) {
        Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        this.resultSet = null;
        System.exit(1);
      }
    }
    return this.resultSet;
  }
  
  protected synchronized void pureSQLUpdate(String query) {
    if(this.connect()) {
      try {
        this.statement = this.connection.createStatement();
        this.statement.executeUpdate(query);
      } catch(SQLException ex) {
        Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        System.exit(1);
      } finally {
        this.close();
      }
    }
  }
  
  protected synchronized void pureSQLInsert(String query) {
    if(this.connect()) {
      try {
        this.statement = this.connection.createStatement();
        this.statement.executeUpdate(query);
      } catch(SQLException ex) {
        Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        System.exit(1);
      } finally {
        this.close();
      }
    }
  }
  
}
