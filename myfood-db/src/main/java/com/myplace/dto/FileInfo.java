package com.myfood.dto;

public class FileInfo {
	private String encode_type;
	private String mimeType = null;
	private String file_path;
	private String file_ext;
	private Integer file_size;
	private String file_id;
	private String file_name;
	private String orig_file_name;
	private String media_type = "image";
	private long advtCode;
	public long getAdvtCode() {
		return advtCode;
	}
	public void setAdvtCode(long advtCode) {
		this.advtCode = advtCode;
	}
	public String getEncode_type() {
		return encode_type;
	}
	public void setEncode_type(String encode_type) {
		this.encode_type = encode_type;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getFile_ext() {
		return file_ext;
	}
	public void setFile_ext(String file_ext) {
		this.file_ext = file_ext;
	}
	public Integer getFile_size() {
		return file_size;
	}
	public void setFile_size(Integer file_size) {
		this.file_size = file_size;
	}
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getOrig_file_name() {
		return orig_file_name;
	}
	public void setOrig_file_name(String orig_file_name) {
		this.orig_file_name = orig_file_name;
	}
	public String getMedia_type() {
		return media_type;
	}
	public void setMedia_type(String media_type) {
		this.media_type = media_type;
	}
	
}
