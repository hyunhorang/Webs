package gameupdate.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {
  
  public static String FS = System.getProperty("file.separator");
  
  public FileIO() {
    
  }
  
  public boolean writeFile(String filename, ArrayList<String> lines) {
    try(BufferedWriter bufferedWriter = 
            new BufferedWriter(new FileWriter(filename))) {
      for(String line : lines) {
        bufferedWriter.write(line);
      }
    } catch (IOException ex) {
      return false;
    }
    return true;
  }
  
  public ArrayList<String> readFile(String filename) {
    ArrayList<String> lines = new ArrayList<>();
    try (BufferedReader bufferedReader = 
            new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        lines.add(line);
			}
    } catch (IOException ex) {
      return null;
    }
    return lines;
  }
  
  public boolean fileExist(String filename) {
    File file = new File(filename);
    if(file.exists()) {
      return new File(filename).isFile();
    }
    return false;
  }
  
  public boolean directoryExist(String directory) {
    File file = new File(directory);
    if(file.exists()) {
      return new File(directory).isDirectory();
    }
    return false;
  }
  
  public boolean createDirectory(String directory) {
    File file = new File(directory);
    if(!file.exists()) {
      return file.mkdir();
    }
    return false;
  }
  
  public boolean deleteFile(String filename) {
    return new File(filename).delete();
  }
  
  public double fileSizeMB(String filename) { 
    return (((new File(filename).length()) / 1024) / 1024);
  }
  
  public String truePath() {
    final File f = new File(FileIO.class.getProtectionDomain().getCodeSource().getLocation().getPath());
    return f.getParent();
  }
  
}
