package com.bbva.p25r.dto.entityin;

import java.io.Serializable;



/**
 * The DireccionDTO class...
 */
public class DireccionDTO implements Serializable  {
	private static final long serialVersionUID = 2931699728946643245L;

	/* Attributes section for the DTO */

	private String calle;
	private String casa;


	public DireccionDTO() {
		super();
	}

	public DireccionDTO(String calle, String casa) {
		super();
		this.calle = calle;
		this.casa = casa;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCasa() {
		return casa;
	}

	public void setCasa(String casa) {
		this.casa = casa;
	}

	@Override
	public String toString() {
		return "DireccionDTO{" +
				"calle='" + calle + '\'' +
				", casa='" + casa + '\'' +
				'}';
	}
}
