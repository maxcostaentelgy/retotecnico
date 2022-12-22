package com.bbva.p25r.dto.entityin;

import java.io.Serializable;

/**
 * The CustomerDTO class...
 */
public class CustomerDTO implements Serializable  {
	private static final long serialVersionUID = 2931699728946643245L;

	/* Attributes section for the DTO */

	private Long id;
	private String firsName;
	private String lastName;
	private DireccionDTO direccion;
	private String contacto;

	public CustomerDTO() {
		super();
	}

	public CustomerDTO(Long id, String firsName, String lastName, DireccionDTO direccion, String contacto) {
		super();
		this.id = id;
		this.firsName = firsName;
		this.lastName = lastName;
		this.direccion = direccion;
		this.contacto = contacto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirsName() {
		return firsName;
	}

	public void setFirsName(String firsName) {
		this.firsName = firsName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public DireccionDTO getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionDTO direccion) {
		this.direccion = direccion;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	@Override
	public String toString() {
		return "CustomerDTO{" +
				"id='" + id + '\'' +
				", firsName='" + firsName + '\'' +
				", lastName='" + lastName + '\'' +
				", direccion=" + direccion +
				", contacto='" + contacto + '\'' +
				'}';
	}
}
