package com.def.springmvc.entity.identity;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.def.springmvc.entity.base.IdEntity;
@Entity
@Table(name = "permission")
public class Permission extends IdEntity {
	
	@Column(unique=true,nullable=false)
	private String name;
	private String description;
	
	@ManyToMany(cascade={CascadeType.PERSIST},fetch=FetchType.LAZY)     
	@JoinTable(name="role_permission_map", 
	joinColumns={@JoinColumn(name="permission_id",referencedColumnName="id")   },    
	inverseJoinColumns={@JoinColumn(name="role_id",referencedColumnName="id")})
	private List<Role> roles = new ArrayList<Role>();
	
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
	
}
