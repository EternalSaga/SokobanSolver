/**
 * FileFolder class reads files from a given path, and stores file name and file into ArrayList and HashMap.
 * @author:Qijing Yu
 */
package uk.ac.ncl.csc8005.team3.GUI;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


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
	
	// get ArrayList of file name in string format
	public ArrayList<String> getFileNames(){

	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) 
	      nameList.add(listOfFiles[i].getName());
	     
	    }
	    
	return nameList;
	}
	
	//get hashmap of filename and file
	public HashMap<String,File> getFiles(){

		for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) 
		    
		      fileList.put(listOfFiles[i].getName(), listOfFiles[i]);
		    }
		    

		return fileList;
	}
	
	//put extra level into arraylist and hashmap
	public void putInFile(String name,File file){
		nameList.add(name);
		fileList.put(name, file);
	}
}
