/**
 * 
 */
package com.wotifgroup.mandrillapp.lutung;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * @author rschreijer
 *
 */
public abstract class MandrillTestCase {
	private static final Log log = LogFactory.getLog(MandrillApiTest.class);
	
	protected static MandrillApi mandrillApi;

	
	@BeforeClass
	public static final void runBeforeClass() {
		final String key = "testKeyHere";
		if(key != null) {
			mandrillApi = new MandrillApi(key);
		} else {
			mandrillApi = null;
		}
	}
	
	@Before
	public final void runBefore() {
		Assume.assumeNotNull(mandrillApi);
	}

}
