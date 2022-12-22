package com.bbva.p25r;

import com.bbva.elara.transaction.AbstractTransaction;
import com.bbva.p25r.dto.entityin.EntityInDTO;

/**
 * In this class, the input and output data is defined automatically through the setters and getters.
 */
public abstract class AbstractP25RTA7101PETransaction extends AbstractTransaction {

	public AbstractP25RTA7101PETransaction(){
	}


	/**
	 * Return value for input parameter EntityIn
	 */
	protected EntityInDTO getEntityin(){
		return (EntityInDTO)this.getParameter("EntityIn");
	}
}
