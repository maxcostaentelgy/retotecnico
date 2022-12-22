package com.bbva.p25r;

import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.TransactionParameter;
import com.bbva.elara.domain.transaction.request.TransactionRequest;
import com.bbva.elara.domain.transaction.request.body.CommonRequestBody;
import com.bbva.elara.domain.transaction.request.header.CommonRequestHeader;
import com.bbva.elara.test.osgi.DummyBundleContext;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import com.bbva.p25r.dto.entityin.CustomerDTO;
import com.bbva.p25r.dto.entityin.DireccionDTO;
import com.bbva.p25r.dto.entityin.EntityInDTO;
import com.bbva.p25r.lib.ra61.P25RRA61;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test for transaction P25RTA6101PETransaction
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/elara-test.xml",
		"classpath:/META-INF/spring/P25RTA6101PETest.xml" })
public class P25RTA6101PETransactionTest {

	@Autowired
	private P25RTA6101PETransaction transaction;

	@Autowired
	private P25RRA61 p25rRA61;

	@Resource(name = "dummyBundleContext")
	private DummyBundleContext bundleContext;

	@Mock
	private CommonRequestHeader header;

	@Mock
	private TransactionRequest transactionRequest;

	@Before
	public void initializeClass() throws Exception {
		// Initializing mocks
		MockitoAnnotations.initMocks(this);
		// Start BundleContext
		this.transaction.start(bundleContext);
		// Setting Context
		this.transaction.setContext(new Context());
		// Set Body
		CommonRequestBody commonRequestBody = new CommonRequestBody();
		commonRequestBody.setTransactionParameters(new ArrayList<>());
		this.transactionRequest.setBody(commonRequestBody);
		// Set Header Mock
		this.transactionRequest.setHeader(header);
		// Set TransactionRequest
		this.transaction.getContext().setTransactionRequest(transactionRequest);
	}

	@Test
	public void testNotNull(){
		// Example to Mock the Header
		// Mockito.doReturn("ES").when(header).getHeaderParameter(RequestHeaderParamsName.COUNTRYCODE);

		EntityInDTO entityInDTO = new EntityInDTO();
		CustomerDTO customerDTO = new CustomerDTO();
		DireccionDTO direccionDTO = new DireccionDTO();
		customerDTO.setDireccion(direccionDTO);
		entityInDTO.setCliente(customerDTO);
		this.addParameter("EntityIn", entityInDTO);

		Assert.assertNotNull(this.transaction);
		this.transaction.execute();

		Mockito.verify(p25rRA61).executeCreateCustomer(Mockito.same(entityInDTO));
	}


	// Add Parameter to Transaction
	private void addParameter(final String parameter, final Object value) {
		final TransactionParameter tParameter = new TransactionParameter(parameter, value);
		transaction.getContext().getParameterList().put(parameter, tParameter);
	}

	// Get Parameter from Transaction
	private Object getParameter(final String parameter) {
		final TransactionParameter param = transaction.getContext().getParameterList().get(parameter);
		return param != null ? param.getValue() : null;
	}
}
