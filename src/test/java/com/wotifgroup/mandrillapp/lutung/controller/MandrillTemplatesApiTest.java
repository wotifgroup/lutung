/**
 * 
 */
package com.wotifgroup.mandrillapp.lutung.controller;

import com.wotifgroup.mandrillapp.lutung.MandrillTestCase;
import com.wotifgroup.mandrillapp.lutung.model.MandrillApiError;
import com.wotifgroup.mandrillapp.lutung.view.MandrillMessage;
import com.wotifgroup.mandrillapp.lutung.view.MandrillTemplate;
import com.wotifgroup.mandrillapp.lutung.view.MandrillTimeSeries;
import junit.framework.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author rschreijer
 * @since Mar 22, 2013
 */
public final class MandrillTemplatesApiTest extends MandrillTestCase {
	private static String templateName = 
			"lutung_templatename_unit_test_" +System.currentTimeMillis(); 
	
	@Test(expected=MandrillApiError.class)
	public final void testAddWithoutName() throws IOException, MandrillApiError {
		mandrillApi.templates().add(null, 
				"<html><body><h1>Hello World!</h1></body></html>", null);
		Assert.fail();
	}
	
	@Test
	public final void testAdd() throws IOException, MandrillApiError {
		final MandrillTemplate t = mandrillApi.templates().add(templateName, 
				"<html><body><h1>Hello World!</h1></body></html>", false);
		Assert.assertNotNull(t);
		Assert.assertNotNull(t.getName());
		Assert.assertNotNull( mandrillApi.templates().delete(t.getName()) );
	}
	
	@Test
	public final void testList() throws IOException, MandrillApiError {
		final MandrillTemplate[] templates = mandrillApi.templates().list();
		Assert.assertNotNull(templates);
		for(MandrillTemplate t : templates) {
			Assert.assertNotNull(t.getName());
			Assert.assertNotNull(t.getCreatedAt());
		}
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testInfoWithoutName() throws IOException, MandrillApiError {
		mandrillApi.templates().info(null);
		Assert.fail();
	}
	
	@Test
	public final void testInfo() throws IOException, MandrillApiError {
		final MandrillTemplate[] templates = mandrillApi.templates().list();
		Assert.assertNotNull(templates);
		if(templates.length > 0) {
			final MandrillTemplate t = 
					mandrillApi.templates().info(templates[0].getName());
			Assert.assertNotNull(t);
			Assert.assertNotNull(t.getName());
			Assert.assertNotNull(t.getCode());
			Assert.assertNotNull(t.getCreatedAt());
		}
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testUpdateWithoutName() throws IOException, MandrillApiError {
		mandrillApi.templates().update(null, 
				"<html><body><h1>Hello World!</h1></body></html>", false);
		Assert.fail();
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testUpdateWithoutCode() throws IOException, MandrillApiError {
		mandrillApi.templates().update(templateName, null, false);
		Assert.fail();
	}
	
	@Test
	public final void testUpdate() throws IOException, MandrillApiError {
		MandrillTemplate t = mandrillApi.templates().add(templateName, 
				"<html><body><h1>Hello World!</h1></body></html>", false);
		Assert.assertNotNull(t);
		final String updatedCode = "<html><body><h1>Hello World! UPDATED</h1></body></html>";
		t = mandrillApi.templates().update(t.getName(), updatedCode, false);
		Assert.assertNotNull(t);
		Assert.assertNotNull(t.getName());
		Assert.assertEquals(updatedCode, t.getCode());
		Assert.assertNotNull(t.getCreatedAt());
		Assert.assertNotNull( mandrillApi.templates().delete(t.getName()) );
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testPublishWithoutName() 
			throws IOException, MandrillApiError {
		
		mandrillApi.templates().publish(null);
		Assert.fail();
	}
	
	@Test
	public final void testPublish() throws IOException, MandrillApiError {
		MandrillTemplate t = mandrillApi.templates().add(
				templateName, 
				"<html><body><h1>Hello World!</h1></body></html>",
				false);
		Assert.assertNotNull(t);
		Assert.assertNull(t.getPublishedAt());
		t = mandrillApi.templates().publish(t.getName());
		Assert.assertNotNull(t);
		Assert.assertNotNull(t.getPublishedAt());
		Assert.assertNotNull( mandrillApi.templates().delete(t.getName()) );
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testDeleteWithoutName() 
			throws IOException, MandrillApiError {
		
		mandrillApi.templates().delete(null);
		Assert.fail();
	}
	
	@Test
	public final void testDelete() throws IOException, MandrillApiError {
		MandrillTemplate t = mandrillApi.templates().add(
				templateName, 
				"<html><body><h1>Hello World!</h1></body></html>",
				false);
		Assert.assertNotNull(t);
		t = mandrillApi.templates().delete(t.getName());
		Assert.assertNotNull(t);
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testTimeSeriesWithoutName() 
			throws IOException, MandrillApiError {
		
		mandrillApi.templates().timeSeries(null);
		Assert.fail();
	}
	
	@Test
	public final void testTimeSeries() 
			throws IOException, MandrillApiError {
		
		final MandrillTemplate[] templates = mandrillApi.templates().list();
		Assert.assertNotNull(templates);
		if(templates.length > 0) {
			final MandrillTimeSeries[] series = 
					mandrillApi.templates().timeSeries(templates[0].getName());
			Assert.assertNotNull(series);
			for(MandrillTimeSeries t : series) {
				Assert.assertNotNull(t.getTime());
			}
		}
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testRenderWithoutName()
			throws IOException, MandrillApiError {
		
		mandrillApi.templates().render(
				null, 
				new HashMap<String,String>(), 
				null);
		Assert.fail();
		
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testRenderWithoutContent()
			throws IOException, MandrillApiError {
		
		mandrillApi.templates().render(templateName, null, null);
		Assert.fail();
		
	}
	
	@Test
	public final void testRender() throws IOException, MandrillApiError {
		final String code = "<html><body><h1>Hello *|merge1|*!</h1></body></html>";
		MandrillTemplate t = mandrillApi.templates().add(templateName, 
				code, false);
		Assert.assertNotNull(t);
		final HashMap<String, String> content = new HashMap<String,String>();
        content.put("", "");
        final MandrillMessage message = prepareMessage();

		final String rendered = mandrillApi.templates().render(
				t.getName(), content, message.getMergeVars().get(0).getVars());
		Assert.assertNotNull(rendered);
		Assert.assertEquals(code.replace("*|merge1|*", "this is overwritten"), rendered);
	}

    private MandrillMessage prepareMessage() throws MandrillApiError {
        final MandrillMessage message = new MandrillMessage();
        message.setAutoText(true);
        message.setTo(new ArrayList<MandrillMessage.Recipient>());
        message.setMergeVars(new ArrayList<MandrillMessage.MergeVarBucket>());
        String testEmail = "test@testemail.com";
        message.addRecipient( testEmail);
        message.addMergeVar("field", "content", testEmail);
        message.addMergeVar("merge1", "this is overwritten", testEmail);
        message.addMergeVar("objectField", new ExampleClass(), testEmail);

        List<ExampleClass> examples = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            examples.add(new ExampleClass());
        }
        message.addMergeVar("arrayOfObjects", examples, testEmail);

        return message;
    }
	
}
