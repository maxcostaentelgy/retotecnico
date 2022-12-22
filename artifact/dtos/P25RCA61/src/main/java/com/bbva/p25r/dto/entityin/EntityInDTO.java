package com.bbva.p25r.dto.entityin;

import java.io.Serializable;
import java.util.Date;

/**
 * The EntityInDTO class...
 */
public class EntityInDTO implements Serializable  {
	private static final long serialVersionUID = 2931699728946643245L;

	/* Attributes section for the DTO */

	private CustomerDTO cliente;
	private Date fecha;

	public EntityInDTO() {
		super();
	}

	public EntityInDTO(CustomerDTO cliente, Date fecha) {
		super();
		this.cliente = cliente;
		this.fecha = fecha;
	}

	public CustomerDTO getCliente() {
		return cliente;
	}

	public void setCliente(CustomerDTO cliente) {
		this.cliente = cliente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "EntityInDTO{" +
				"cliente=" + cliente +
				", fecha=" + fecha +
				'}';
	}
}
