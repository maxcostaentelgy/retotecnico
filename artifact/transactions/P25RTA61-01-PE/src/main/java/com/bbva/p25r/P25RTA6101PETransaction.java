package com.bbva.p25r;

import com.bbva.elara.domain.transaction.Severity;
import com.bbva.elara.domain.transaction.response.HttpResponseCode;
import com.bbva.p25r.dto.entityin.CustomerDTO;
import com.bbva.p25r.dto.entityin.EntityInDTO;
import com.bbva.p25r.lib.ra61.P25RRA61;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TRX Customer
 *
 */
public class P25RTA6101PETransaction extends AbstractP25RTA6101PETransaction {

	private static final Logger LOGGER = LoggerFactory.getLogger(P25RTA6101PETransaction.class);

	/**
	 * The execute method...
	 */
	@Override
	public void execute() {
		P25RRA61 p25rRA61 = this.getServiceLibrary(P25RRA61.class);
		LOGGER.info("[APX-1] Instancia P25RRA61  : {}", p25rRA61);

		EntityInDTO oRequest = this.getEntityin();
		LOGGER.info("[APX-2] CustomerDTO : {}", oRequest);

		CustomerDTO customer = oRequest.getCliente();
		Integer idCustomer = p25rRA61.executeCreateCustomer(oRequest);
		LOGGER.info("[APX-3] idCustomer  : {}", idCustomer);

		if (idCustomer == null || !this.getAdviceList().isEmpty()) {
			LOGGER.info("[APX-4] Save Not Success id : {}", customer.getId());
			this.setHttpResponseCode(HttpResponseCode.HTTP_CODE_400, Severity.ENR);
		} else {
			LOGGER.info("[APX-5] Save Success :{}", customer.getId());
			this.setHttpResponseCode(HttpResponseCode.HTTP_CODE_200, Severity.OK);
		}
	}


}
