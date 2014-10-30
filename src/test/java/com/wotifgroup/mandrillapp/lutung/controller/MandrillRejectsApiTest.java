/**
 * 
 */
package com.wotifgroup.mandrillapp.lutung.controller;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.wotifgroup.mandrillapp.lutung.MandrillTestCase;
import com.wotifgroup.mandrillapp.lutung.model.MandrillApiError;

/**
 * @author rschreijer
 * @since Mar 21, 2013
 */
public final class MandrillRejectsApiTest extends MandrillTestCase {
	
	@Test(expected=MandrillApiError.class)
	public final void testAdd() throws IOException, MandrillApiError {
		mandrillApi.rejects().add(null, null, null);
		Assert.fail();
	}
	
	@Test
	public final void testList() throws IOException, MandrillApiError {
		Assert.assertNotNull( mandrillApi.rejects().list(null, null) );
		
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testDelete() throws IOException, MandrillApiError {
		mandrillApi.rejects().delete(null);
		Assert.fail();
	}

}
