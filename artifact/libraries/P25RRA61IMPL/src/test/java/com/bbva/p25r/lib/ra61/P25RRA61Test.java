package com.bbva.p25r.lib.ra61;

import com.bbva.apx.exception.db.DuplicateKeyException;
import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import javax.annotation.Resource;

import com.bbva.elara.utility.jdbc.JdbcUtils;
import com.bbva.p25r.dto.entityin.CustomerDTO;
import com.bbva.p25r.dto.entityin.DireccionDTO;
import com.bbva.p25r.dto.entityin.EntityInDTO;
import com.bbva.p25r.lib.ra61.impl.P25RRA61Impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.aop.framework.Advised;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static com.bbva.p25r.dto.util.CustomerConst.ERR_EXISTE_DATA;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/P25RRA61-app.xml",
		"classpath:/META-INF/spring/P25RRA61-app-test.xml",
		"classpath:/META-INF/spring/P25RRA61-arc.xml",
		"classpath:/META-INF/spring/P25RRA61-arc-test.xml" })
public class P25RRA61Test {

	@Spy
	private Context context;

	@InjectMocks
	private P25RRA61Impl p25rRA61;

	@Mock
	private ApplicationConfigurationService applicationConfigurationService;

	@Mock
	private JdbcUtils jdbcUtils;


	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		context = new Context();
		ThreadContext.set(context);
		getObjectIntrospection();
	}

	private Object getObjectIntrospection() throws Exception{
		Object result = this.p25rRA61;
		if(this.p25rRA61 instanceof Advised){
			Advised advised = (Advised) this.p25rRA61;
			result = advised.getTargetSource().getTarget();
		}
		return result;
	}

	@Test
	public void executeTest(){
		p25rRA61.execute();
		Assert.assertEquals(0, context.getAdviceList().size());
	}

	@Test
	public void testExecuteCreateCustomerOk(){
		Mockito.when(jdbcUtils.update(Mockito.anyString(),Mockito.anyMap())).thenReturn(1);

		EntityInDTO entityInDTO = new EntityInDTO();
		entityInDTO.setFecha(new Date());

		CustomerDTO customer = new CustomerDTO();
		customer.setFirsName("Macgyver");
		customer.setLastName("Barrientos");
		customer.setContacto("123456789");

		DireccionDTO direccionDTO = new DireccionDTO();
		direccionDTO.setCalle("Av. Villareal");
		direccionDTO.setCasa("123");

		customer.setDireccion(direccionDTO);

		entityInDTO.setCliente(customer);

		Integer result = p25rRA61.executeCreateCustomer(entityInDTO);
		Assert.assertNotNull(result);
		Assert.assertEquals(1,result.intValue());
	}

	@Test
	public void testExecuteUpdateCustomerOk(){
		Mockito.when(jdbcUtils.update(Mockito.anyString(),Mockito.anyMap())).thenReturn(1);

		EntityInDTO entityInDTO = new EntityInDTO();
		entityInDTO.setFecha(new Date());

		CustomerDTO customer = new CustomerDTO();
		customer.setId(Long.valueOf(22));
		customer.setFirsName("Macgyver");
		customer.setLastName("Barrientos");
		customer.setContacto("123456789");

		DireccionDTO direccionDTO = new DireccionDTO();
		direccionDTO.setCalle("Av. Villareal");
		direccionDTO.setCasa("123");

		customer.setDireccion(direccionDTO);

		entityInDTO.setCliente(customer);

		Integer result = p25rRA61.executeUpdateCustomer(entityInDTO);
		Assert.assertNotNull(result);
		Assert.assertEquals(1,result.intValue());
	}
}
