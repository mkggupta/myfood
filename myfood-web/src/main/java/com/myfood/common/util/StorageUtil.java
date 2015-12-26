package com.myfood.common.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myfood.dto.BusinessFileInfo;
import com.myfood.dto.FileInfo;




public class StorageUtil {
	private static Logger logger = LoggerFactory.getLogger(StorageUtil.class);
	private static FileAccess fileAccess = new RepoFileAccess();
	private static String osName = System.getProperty("os.name").toLowerCase();
	private static boolean isLinux = osName.contains("linux") ;

	 public static String getSuitableMediaPath(String mediaPath) {
			if (isLinux) { 
				 MyFoodProperties myfoodProperties = MyFoodProperties.getInstance();
				 String sanRootPath = myfoodProperties.getProperty(MyFoodPropertyKeys.SAN_ROOT);
				try {
					mediaPath = mediaPath.replaceAll("\\\\", "/");
					mediaPath = mediaPath.replaceAll("//", "/");
		
					if (mediaPath.contains("/"+sanRootPath)) {
						mediaPath = mediaPath.replace("/"+sanRootPath, "");
					} else if (mediaPath.contains("//"+sanRootPath)) {
						mediaPath = mediaPath.replace("//"+sanRootPath, "");
					}
				} catch (Exception e) {
					logger.error("getSuitableMediaPath() ,Error while converting path to linux suitable!!");
				}
			}
			return mediaPath;
		}
	 
	 protected static String getLinuxSuitableMediaPath(String mediaPath,String rootPath) {
			try {
				mediaPath = mediaPath.replaceAll("\\\\", "/");
				mediaPath = mediaPath.replaceAll("//", "/");

				if (mediaPath.contains("/"+rootPath)) {
					mediaPath = mediaPath.replace("/"+rootPath, "");
				} else if (mediaPath.contains("//"+rootPath)) {
					mediaPath = mediaPath.replace("//"+rootPath, "");
				}
			} catch (Exception e) {
				logger.error("Error while converting path to linux suitable!!");
			}
			return mediaPath;
		}
	 public static String storeFile(byte[] fileContent, String fileName, String pathToStore) {
		 try {
				if (isLinux) {
					 MyFoodProperties myfoodProperties = MyFoodProperties.getInstance();
					 String sanRootPath = myfoodProperties.getProperty(MyFoodPropertyKeys.SAN_ROOT);
					pathToStore = getLinuxSuitableMediaPath(pathToStore,sanRootPath);
				}
				if (fileContent == null || fileContent.length == 0)
					throw new IllegalArgumentException("The byte array to store was found null @ StorageUtil:storeFile");
				if (fileName == null || fileName.length() == 0)
					throw new IllegalArgumentException("The fileName was found null @ StorageUtil:storeFile");
				if (pathToStore == null || pathToStore.length() == 0)
					throw new IllegalArgumentException("The path to store was found null @ StorageUtil:storeFile");
				logger.info("pathToStore @ " + pathToStore);
				return fileAccess.storeBytes(pathToStore, fileName, fileContent) ;
		 } catch (IOException e) {
				logger.error("Error while storing file", e ) ; 
				throw new RuntimeException(e);
			}
	 }
	 
	 public static FileInfo saveMediaFromBytes(byte[] data, String fileName ) throws Exception{
		 
		 if (data == null || data.length == 0) {
             logger.error("The data to store was found null");
             throw new IllegalArgumentException("The data to store was found null");
         }
		   FileInfo fileInfo = new FileInfo();
			StringBuilder pathToStore = new StringBuilder("");
			 MyFoodProperties myfoodProperties = MyFoodProperties.getInstance();
			 logger.info(myfoodProperties.getProperty(MyFoodPropertyKeys.REPO_PATH));
			 //String repositoryPath ="D:\\banner\\";
			String repositoryPath =myfoodProperties.getProperty(MyFoodPropertyKeys.REPO_PATH);
			logger.info("fileName @ " +fileName);
			String fileExt = MediaUtil.getDefaultExtension(data);
			/*String fileExt = getFileExt(fileName);
			if(StringUtils.isBlank(fileExt)){
				fileExt ="jpg";
			}*/
			if (fileExt.equalsIgnoreCase("raw")) {
                throw new Exception("An unknown file type was found for Media:");
            }
			String fileToStore = createFileName(fileExt);
			String relativePath =computeDirToWrite("");
			 pathToStore.append(getSuitableMediaPath(repositoryPath));
			 pathToStore.append(relativePath);
	         pathToStore.append("/");
	         logger.info("Will store file @ " + pathToStore.toString());
	         String file_path = storeFile(data, fileToStore, pathToStore.toString());
	         logger.info("file_path @ " +file_path);
	         File f = new File( pathToStore.toString() + fileToStore);
	         if (!f.exists()) {
	             throw new RuntimeException("File was not persisted to Storage System");
	         }
	      
	         fileInfo.setFile_ext(fileExt);
	         fileInfo.setOrig_file_name(fileName);
	         fileInfo.setFile_name(fileToStore);
	         fileInfo.setFile_id(fileToStore.substring(0,fileToStore.indexOf(".")));
	         fileInfo.setFile_size(data.length);
	         fileInfo.setMedia_type("image");
	         fileInfo.setFile_path(file_path);
			return fileInfo;
		}
		
	 
	 public static BusinessFileInfo saveBusinessMediaFromBytes(byte[] data, String fileName ) throws Exception{
		 
		 if (data == null || data.length == 0) {
             logger.error("The data to store was found null");
             throw new IllegalArgumentException("The data to store was found null");
         }
		   BusinessFileInfo fileInfo = new BusinessFileInfo();
			StringBuilder pathToStore = new StringBuilder("");
			 MyFoodProperties myfoodProperties = MyFoodProperties.getInstance();
			 logger.info(myfoodProperties.getProperty(MyFoodPropertyKeys.REPO_PATH));
			 //String repositoryPath ="D:\\banner\\";
			String repositoryPath =myfoodProperties.getProperty(MyFoodPropertyKeys.REPO_PATH);
			logger.info("fileName @ " +fileName);
			String fileExt = MediaUtil.getDefaultExtension(data);
			/*String fileExt = getFileExt(fileName);
			if(StringUtils.isBlank(fileExt)){
				fileExt ="jpg";
			}*/
			if (fileExt.equalsIgnoreCase("raw")) {
                throw new Exception("An unknown file type was found for Media:");
            }
			String fileToStore = createFileName(fileExt);
			String relativePath =computeDirToWrite("");
			 pathToStore.append(getSuitableMediaPath(repositoryPath));
			 pathToStore.append(relativePath);
	         pathToStore.append("/");
	         logger.info("Will store file @ " + pathToStore.toString());
	         String file_path = storeFile(data, fileToStore, pathToStore.toString());
	         logger.info("file_path @ " +file_path);
	         File f = new File( pathToStore.toString() + fileToStore);
	         if (!f.exists()) {
	             throw new RuntimeException("File was not persisted to Storage System");
	         }
	      
	         fileInfo.setFileExt(fileExt);
	         if(StringUtils.isNotBlank(fileName)){
	            fileInfo.setOrigFName(fileName);
	         }else{
	        	 fileInfo.setOrigFName(fileToStore); 
	         }
	         fileInfo.setFileId(fileToStore.substring(0,fileToStore.indexOf(".")));
	         fileInfo.setFileSize(data.length);
	         fileInfo.setMediaType("image");
	         fileInfo.setFilePath(file_path);
			return fileInfo;
		}

		/**
		 * Computes which directory in the file system partition where the file has
		 * to be written to.
		 * 
		 * @param String
		 *            - the base path of the file obtained from the database
		 * @return String - the directory resulting
		 */
		public static String computeDirToWrite(String repositoryURL) {
			try {
				String currentTime = TimeUtils.getCurrentGMT();
				//String amOrPm = TimeUtils.getIfAMorPM();
				// Wait for 2 secs and restart if the time ends with 59:59, in order
				// to
				// avoid a wrong computation of the dir to which we need to write to
				if (currentTime.endsWith("59:59")) {
					Thread.sleep(2000);
					currentTime = TimeUtils.getCurrentGMT();
					//amOrPm = TimeUtils.getIfAMorPM();
				}
				StringBuilder dirToWrite = new StringBuilder(repositoryURL);
				dirToWrite.append("/");
				dirToWrite.append(currentTime.substring(0, 4));
				dirToWrite.append("/");
				dirToWrite.append(currentTime.substring(5, 7));
				dirToWrite.append("/");
				dirToWrite.append(currentTime.substring(8, 10));
				dirToWrite.append("/");
				dirToWrite.append(currentTime.substring(11, 13));
				// dirToWrite.append(amOrPm);
				return dirToWrite.toString();
			} catch (InterruptedException exception) {
				logger.error("Exception computeDirToWrite " + exception.getLocalizedMessage(), exception);
				throw new RuntimeException(exception);
			}
		}
		
		public static String getFileExt(String fileName) {
			logger.debug("getFileExt entered "+fileName);
			String fileExt = null;
			fileExt = fileName.substring(fileName.lastIndexOf('.')+1).toLowerCase();
			logger.debug("getFileExt --"+fileExt);
			return fileExt;
			
		}
		
		public static String createFileName(String suffix) {
			StringBuffer handle = new StringBuffer(createRandomLongInLimit(1, Long.MAX_VALUE).toString());
			handle.append(".");
			handle.append(suffix);
			return handle.toString();
		}
		
		 public static Long createRandomLongInLimit(long lowerLimit, long upperLimit) {

			    java.util.Random generator = new java.util.Random();
			    long range = upperLimit - lowerLimit + 1;
			    double fraction = range * generator.nextDouble();
			    return Long.valueOf(Math.round(fraction + lowerLimit));

		 }
}
