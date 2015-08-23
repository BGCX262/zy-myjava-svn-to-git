package com.def.springmvc.entity.identity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



import com.def.springmvc.entity.base.IdEntity;
@Entity
@Table(name = "permission")
public class Organization extends IdEntity {

    /**组织名称*/
    @Column(length=64)
    private String name;
    
    /**组织编码*/
    @Column(length=64)
    private String code;
    
    /**父组织*/
    @ManyToOne(optional=true)
    @JoinColumn(name="parent_id")
    private Organization parent;
    
    /**子组织*/
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private List<Organization> children =  new ArrayList<Organization>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Organization getParent() {
		return parent;
	}

	public void setParent(Organization parent) {
		this.parent = parent;
	}

	public List<Organization> getChildren() {
		return children;
	}

	public void setChildren(List<Organization> children) {
		this.children = children;
	}
    

}
