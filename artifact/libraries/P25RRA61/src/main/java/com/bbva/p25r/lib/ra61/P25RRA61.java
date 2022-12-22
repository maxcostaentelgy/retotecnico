package com.bbva.p25r.lib.ra61;

import com.bbva.p25r.dto.entityin.CustomerDTO;
import com.bbva.p25r.dto.entityin.EntityInDTO;

/**
 * The  interface P25RRA61 class...
 */
public interface P25RRA61 {

	/**
	 * The execute method...
	 */
	void execute();

	Integer executeCreateCustomer(EntityInDTO entityInDTO);

	Integer executeUpdateCustomer(EntityInDTO entityInDTO);
}
