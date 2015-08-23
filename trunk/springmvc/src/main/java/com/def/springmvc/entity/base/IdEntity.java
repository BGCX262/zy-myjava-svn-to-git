package com.def.springmvc.entity.base;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.search.annotations.DocumentId;

/**
 * Byron Zeng
 */
@MappedSuperclass
public abstract class IdEntity implements Serializable {

	private static final long serialVersionUID = -2020105896329726846L;

	protected Long id;

	public IdEntity() {
	}

	@Id@DocumentId
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
