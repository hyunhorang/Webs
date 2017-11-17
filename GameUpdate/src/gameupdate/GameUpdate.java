package gameupdate;

import gameupdate.database.Database;
import gameupdate.database.GameDatabaseTable;
import gameupdate.lib.MouseLocker;
import gameupdate.lib.WindowsShell;
import gameupdate.view.UpdatingSign;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameUpdate {
  
  public static void main(String[] args) {
    GameUpdate gameUpdate = new GameUpdate(args[0], args[1]);
    GameDatabaseTable gameDatabaseTable = GameUpdate.DB.gameDatabaseTable();
    gameUpdate.run(gameDatabaseTable);
  }
  
  public static Database DB;
  public static String GAME_NAME;
  public static String COMPUTER_NUMBER;
  
  public GameUpdate(String gameName, String computerNumber) {
    GameUpdate.DB = new Database();
    GameUpdate.GAME_NAME = gameName;
    GameUpdate.COMPUTER_NUMBER = computerNumber;
  }
  
  public void run(GameDatabaseTable gameDatabaseTable) {
    WindowsShell windowsShell = new WindowsShell(gameDatabaseTable);
    // 실행 block 이 걸린 상황
    // 간단한 싸인만 띄워주면 된다
    if(gameDatabaseTable.getIs_blocked().toLowerCase().equals("true")) {
      windowsShell.start(gameDatabaseTable.getAutohotkey_exe_path() + "game_blocked.exe", false, false);
      return;
    }
    // 게임 업데이트가 없는 경우 바로 게임을 실행하면 된다
    if(gameDatabaseTable.getIs_updated().toLowerCase().equals("false")) {
      windowsShell.start(
        gameDatabaseTable.getAutohotkey_exe_path() + 
        GameUpdate.GAME_NAME + ".exe", false, false
      );
      return;
    }
    // 게임 업데이트가 있는경우
    // 자바 프레임을 띄워준다
    // 이 프레임은 종료불가능
    // 이 프레임이 떠서 업데이트가 진행중인 동안에는 모든 게임이 블럭이 걸린다
    GameUpdate.DB.setBlock("true");
    String[] net_use_path_split = gameDatabaseTable.getNet_use_path().split(";");
    for(int i = 0; i < net_use_path_split.length; i++) {
      windowsShell.netPermission(
        net_use_path_split[i], 
        gameDatabaseTable.getServer_username(), 
        gameDatabaseTable.getServer_password()
      );
    }
    UpdatingSign updatingSign = new UpdatingSign();
    windowsShell.setView(updatingSign);
    windowsShell.robocopy(
      gameDatabaseTable.getFilepath_server(), 
      gameDatabaseTable.getFilepath_client(), 
      5
    );
    for(int i = 0; i < net_use_path_split.length; i++) {
      windowsShell.cancelNetPermission(net_use_path_split[i]);
    }
    windowsShell.start(
      gameDatabaseTable.getAutohotkey_exe_path() + 
      GameUpdate.GAME_NAME + ".exe", false, false
    );
    GameUpdate.DB.setIs_updated("false");
    GameUpdate.DB.setBlock("false");
    // windowsShell.start("taskkill /IM updating_sign.exe", false, false);
  }
  
}