package com.def.springmvc.dao.base;

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * Prefix Support Naming Strategy extends from hibernate ImprovedNamingStrategy
 */
public class PrefixImprovedNamingStrategy extends ImprovedNamingStrategy {
	private static final long serialVersionUID = 3097086534970692039L;
	
	private String tablePrefix = "";
	private String columnSuffix = "";
	
	//private String tablePrefix = "osf_";
	//private String columnPrefix = "_";
	
	public PrefixImprovedNamingStrategy() {
	}

	@Override
	public String tableName(String tableName) {
		return tablePrefix + super.tableName(tableName);
	}
	
	@Override
	public String columnName(String columnName) {
		//return columnPrefix + super.columnName(columnName);
		return super.columnName(columnName)+columnSuffix;
	}
	
	@Override
	public String propertyToColumnName(String propertyName) {
		//return columnPrefix + super.propertyToColumnName(propertyName);
		return  super.propertyToColumnName(propertyName)+columnSuffix;
	}
	
}
