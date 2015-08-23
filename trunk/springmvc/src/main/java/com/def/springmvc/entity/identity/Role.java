package com.def.springmvc.entity.identity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.def.springmvc.entity.base.IdEntity;

@Entity
@Table(name = "role")
public class Role extends IdEntity {

	private String name;
	private String description;
	private boolean deleted;
	
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "user_role_map", joinColumns = { @JoinColumn(name ="role_id" )}, inverseJoinColumns = { @JoinColumn(name = "user_id") })
    @OrderBy("id")
	private List<User> users = new ArrayList<User>();
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "role_permission_map", joinColumns = { @JoinColumn(name ="role_id" )}, inverseJoinColumns = { @JoinColumn(name = "permission_id") })
    @OrderBy("id")	
	private List<Permission> permistions= new ArrayList<Permission>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Permission> getPermistions() {
		return permistions;
	}

	public void setPermistions(List<Permission> permistions) {
		this.permistions = permistions;
	}
    
}
