package com.myfood.common.constant;

public interface MyFoodBusinessConstant {
	
	String USER_NAME="username";
	String BUSINESS_PROFILE_URL="bUrl";
	//BUSINESS DETAILS

	String OPERATION = "operation";
	String TYPE = "type";
	String FILE_DATA = "fileInfo";
	String USERID="userId";
	String BNAME ="bName";
	String BCONTNAME ="bContName";
	String BADDRESS ="bAddress";
	String BSTATE ="bState";
	String BCITY ="bCity";
	String BCOUNTRY ="bCountry";
	String BZIP ="bZip";
	String BPHONE ="bPhone";
	String BEMAIL="bEmail";
	String BWEB="bWeb";
	String BDESC="bDesc";
	String BSERVICE="bService";
	String BSPECIALITY="bSpeciality";
	String BLATITUDE="bLat";
	String BLONGITUDE ="bLong" ;
	String CATID="catId";
	String SUBCATID="subCatId";
	String BID="bId";
	String BREGDATE="bRegDate";
	String BSTARTYEAR="bStartYear";
	String NEXTURL="nextUrl";
	String FEEDTYPE="feedType";
	Integer NORMALTYPE=1;
	Integer ADVTTYPE=2;
	Integer UPGRADETYPE=3;
	String SLIMIT="sLimit";
	
	//BUSINESS SEARCH
	String TEXT ="text";
	String DISTANCE="dist";
	String CAT_SEARCH_TYPE="1";//category basesd
	String SUB_CAT_SEARCH_TYPE="1_1"; //subcategory bases
	String ZIP_SEARCH_TYPE="2"; //zip bases
	String NEAR_ME_SEARCH_TYPE="3";//long lad bases
	String TEXT_SEARCH_TYPE="4";//by name of business
	Integer DEFAULT_DISTANCE =2;//in km
	
}
