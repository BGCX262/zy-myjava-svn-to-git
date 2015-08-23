package com.def.springmvc.web;

import javax.servlet.jsp.tagext.Tag;


import com.def.springmvc.entity.identity.User;

import com.def.springmvc.util.Page;

/**
 * 
 * @author Byron
 */
public  abstract  class AttributeKeys {

	public static final String BASE_KEY_READABLE = "base";
	public static final String SITE_DOMAIN_KEY_READABLE = "domain";
	public static final String FEATURE_CODE_KEY_READABLE = "featureCode";
	public static final String CUSTOM_ATTRIBUTES_KEY_READABLE = "customAttributes";
	public static final String TOTAL_COUNT_KEY_READABLE = "totalCount";
	public static final String PARENT_KEY_READABLE = "parent";
	public static final String CHILDREN_KEY_READABLE = "children";
	public static final String APPLICATION_KEY_READABLE = "application";
	public static final String SHOW_ERRORS_KEY_READABLE = "showErrors";
	public static final String REQUEST_PATH_KEY_READABLE = "requestPath";
	public static final String QUERY_STRING_KEY_READABLE = "queryString";
	public static final String REMEMBER_ME_KEY = "_rememberMe_";
	public static final String USERINFO_KEY_READABLE="userinfo";
	public static final String TAG_KEY = "_" + Tag.class.getName();
	public static final String TAG_KEY_READABLE = "tag";
	public static final String TAG_LIST_KEY_READABLE = "tags";

	public static final String PAGE_KEY = "_" + Page.class.getName();
	public static final String PAGE_KEY_READABLE = "page";
	
	public static final String USER_ID_KEY = "_" + User.class.getName() + "_ID";
	public static final String USER_KEY = "_" + User.class.getName();
	public static final String USER_KEY_READABLE = "user";

	public static int COOKIE_REMEBER_ME_PERIOD=14*24*60*60;
	public static final String KEY_FILE_NAME = "myapp";
	public static final String USER_INDEX_URL = "/";
}
