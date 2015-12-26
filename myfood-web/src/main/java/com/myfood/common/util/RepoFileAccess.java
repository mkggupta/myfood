/**
 * 
 */
package com.myfood.common.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Hp
 *
 */
public class RepoFileAccess implements FileAccess {
	private static Logger logger = LoggerFactory.getLogger(RepoFileAccess.class) ;
	//private PathGenerator pathGenerator = null ;

	public byte[] getBytes(String path) {
		//String newPath = pathGenerator.getPath(path) ;
		//if(!exists(newPath)) newPath = path ;
		try {
			return FileUtils.readFileToByteArray( new File(path) ) ;
		} catch (IOException e) {
			logger.error("Error while reading file contents"+ e.getLocalizedMessage(), e );
		}
		return null ; // new byte[0] ;
	}

	public String storeBytes(String folder, String filename, byte[] content) throws IOException {
		return storeBytes(FilenameUtils.concat(folder, filename), content) ;
	}
	public String storeBytes(String path, byte[] content) throws IOException {
		logger.info("path to save="+path);
		String fullPath = FilenameUtils.getFullPathNoEndSeparator(path) ; 
		logger.info("full path to save="+fullPath);
		File file = new File(fullPath);
		file.mkdirs();
		storeBytes(new File(path), content) ;
		
		return fullPath;
	}
	public void storeBytes(File f, byte[] content) throws IOException {
		FileUtils.writeByteArrayToFile(f, content) ;
	}
	
	public boolean isFile(String path) {
		return isFile(new File(path));
	}
	public boolean isFile(File f) {
		return (f != null && f.isFile()) ;
	}
	public boolean exists(String path) {
		return exists(new File(path));
	}
	public boolean exists(File f) {
		return (f != null && f.exists()) ;
	}
	public long size(String path) {
		return (exists(path) ? (new File(path).length()) : 0);
	}
	
	/*public void setPathGenerator(PathGenerator pathGenerator) {
		this.pathGenerator =  pathGenerator ;
	}*/

	public String getSystemPath(String path) {
		return null;
	}

	public boolean removeFile(String path) {
		return removeFile(new File(path)) ;
	}
	public boolean removeFile(File f) {
		return (f != null && isFile(f)) ? f.delete() : false ;
	}
}
