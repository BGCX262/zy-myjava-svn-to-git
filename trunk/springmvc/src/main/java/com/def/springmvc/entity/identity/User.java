package com.def.springmvc.entity.identity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.def.springmvc.entity.base.IdEntity;

/**
 * The persistent class for the ACT_ID_USER database table.
 * 
 */
@Entity
@Table(name = "user")
public class User extends IdEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(unique=true)
	private String userId;
	private String password;	
	private String email;
	private String firstName;
	private String lastName;
	
	@ManyToMany(cascade={CascadeType.PERSIST})     
	@JoinTable(name="user_role_map", 
	joinColumns={@JoinColumn(name="user_id",referencedColumnName="id")   },    
	inverseJoinColumns={@JoinColumn(name="role_id",referencedColumnName="id")})
	private List<Role> roles = new ArrayList<Role>();

	@ManyToOne(optional = true)  
    @JoinColumn(name="organization_id")
	private  Organization organization;



	public User() {
	}
	
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		roles = roles;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}






}