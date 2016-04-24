/**
 * 
 */
package sokoban_new;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author yqjapple
 *
 */
public class FileFolder {
	private String path;
	private File[] listOfFiles;
	private File folder;
	
	private ArrayList<String> nameList;
	private HashMap<String,File> fileList;
	
	
	public FileFolder(String path){
		
		this.path = path;
		folder = new File(path);
		listOfFiles = folder.listFiles();
		fileList = new HashMap<String,File>();
		nameList = new ArrayList<String>();
	}

	public ArrayList<String> getFileNames(){

	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) 
	      nameList.add(listOfFiles[i].getName());
	     
	    }
	    
	return nameList;
	}
	
	public HashMap<String,File> getFiles(){

		for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) 
		    
		      fileList.put(listOfFiles[i].getName(), listOfFiles[i]);
		    }
		    

		return fileList;
	}
}
