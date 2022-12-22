package com.bbva.p25r.lib.ra61.impl;

import com.bbva.apx.exception.db.DuplicateKeyException;
import com.bbva.apx.exception.db.NoResultException;
import com.bbva.p25r.dto.entityin.CustomerDTO;
import com.bbva.p25r.dto.entityin.DireccionDTO;
import com.bbva.p25r.dto.entityin.EntityInDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static com.bbva.p25r.dto.util.CustomerConst.*;

/**
 * The P25RRA61Impl class...
 */
public class P25RRA61Impl extends P25RRA61Abstract {

    private static final Logger LOGGER = LoggerFactory.getLogger(P25RRA61Impl.class);

    /**
     * The execute method...
     */
    @Override
    public void execute() {
        // TODO - Implementation of business logic
    }

    @Override
    public Integer executeCreateCustomer(final EntityInDTO entityInDTO) {
        LOGGER.info("[APX] call method executeCreateAccount");
        LOGGER.info("[APX-R1] data new customer : {}", entityInDTO);
        int iResultCreateSQL = 0;
        if (this.getAdviceList().isEmpty()) {
            Map<String, Object> requestSQL = buildMapIn(entityInDTO);

            LOGGER.info("[APX-R2] Map Save Record customer: {}", requestSQL);

            try {
                LOGGER.info("[APX-R3] Access jdbcUtils ");
                iResultCreateSQL = this.jdbcUtils.update(SQL_INSERT_CUSTOMER, requestSQL);
            } catch (DuplicateKeyException keyException) {
                LOGGER.info(String.format("[APX-R4] Error, customer id is already registered : {}", keyException.getMessage()));
                this.addAdvice(ERR_EXISTE_DATA);
            }
            LOGGER.info("[APX-R5] Customer registration result  : {}", iResultCreateSQL);
            return iResultCreateSQL;
        }
        return null;
    }

    @Override
    public Integer executeUpdateCustomer(EntityInDTO entityInDTO) {
        LOGGER.info("[APX] call method executeUpdateAccount");
        LOGGER.info("[APX-R1] data update customer : {}", entityInDTO);
        int iResultUpdateSQL = 0;
        if (this.getAdviceList().isEmpty()) {
            Map<String, Object> requestSQL = buildMapIn(entityInDTO);
            LOGGER.info("[APX-R2] Map Save Record customer: {}", requestSQL);

            try {
                if (this.verifyCustomer(requestSQL) > 0) {
                    LOGGER.info("[APX-R3] Access jdbcUtils ");
                    iResultUpdateSQL = this.jdbcUtils.update(SQL_UPDATE_CUSTOMER, requestSQL);
                } else
                    throw new NoResultException("Customer id " + requestSQL.get("customerId") + "is not registered");
            } catch (Exception keyException) {
                LOGGER.info(String.format("[APX-R4] Error: {}", keyException.getMessage()));
                this.addAdvice(ERR_EXISTE_DATA);
            }
            LOGGER.info("[APX-R5] Customer update operation result  : {}", iResultUpdateSQL);
            return iResultUpdateSQL;
        }
        return null;
    }

    public Integer verifyCustomer(Map entityInDTO) {
        LOGGER.info("[APX] call method verifyCustomer");
        int iResultVerifySQL = 0;
        if (entityInDTO.get("customerId") != null) {
            try {
                LOGGER.info("[APX-R3] Access jdbcUtils ");
                iResultVerifySQL = this.jdbcUtils.update(SQL_VERIFY_CUSTOMER, entityInDTO);
            } catch (Exception keyException) {
            }
            LOGGER.info("[APX-R5] Customer validation result  : {}", iResultVerifySQL);
        }
        return iResultVerifySQL;
    }

    public Map buildMapIn(EntityInDTO entityInDTO) {
        Map<String, Object> requestSQL = new HashMap<>();
        CustomerDTO customer = entityInDTO.getCliente();
        DireccionDTO direccion = customer.getDireccion();
        if (entityInDTO != null)
            requestSQL.put("customerId", customer.getId());
        requestSQL.put("firsName", customer.getFirsName());
        requestSQL.put("lastName", customer.getLastName());
        requestSQL.put("calle", direccion.getCalle());
        requestSQL.put("casa", direccion.getCasa());
        requestSQL.put("contacto", customer.getContacto());
        requestSQL.put("fecha", entityInDTO.getFecha());

        return requestSQL;
    }

}
