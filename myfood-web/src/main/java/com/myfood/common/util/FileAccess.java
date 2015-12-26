package com.myfood.common.util;

import java.io.File;
import java.io.IOException;

public interface FileAccess {
	byte[] 	getBytes(String path) ;
	String 	storeBytes(String path, byte[] content) throws IOException ;
	String	storeBytes(String folder, String filename, byte[] content) throws IOException ;
	void 	storeBytes(File f, byte [] content) throws IOException ;
	boolean isFile(String path) ;
	boolean isFile(File f) ;
	boolean exists(String path) ;
	boolean exists(File f) ;
	long	size(String path) ;
	String 	getSystemPath(String path) ;
	
	boolean	removeFile(String path) ;
	boolean	removeFile(File f) ;
}
