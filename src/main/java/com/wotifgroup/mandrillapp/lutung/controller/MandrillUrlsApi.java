/**
 * 
 */
package com.wotifgroup.mandrillapp.lutung.controller;

import com.wotifgroup.mandrillapp.lutung.model.MandrillApiError;
import com.wotifgroup.mandrillapp.lutung.view.MandrillTimeSeries;
import com.wotifgroup.mandrillapp.lutung.view.MandrillUrl;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author rschreijer
 * @since Mar 19, 2013
 */
public class MandrillUrlsApi {
	private final String rootUrl;
	private final String key;
	
	public MandrillUrlsApi(final String key) {
        this.rootUrl = MandrillUtil.rootUrl;
		this.key = key;
	}
	
	/**
	 * <p>Get the 100 most clicked URLs.</p>
	 * @return
	 * @throws MandrillApiError
	 * @throws IOException
	 * @since Mar 19, 2013
	 */
	public MandrillUrl[] list() 
			throws MandrillApiError, IOException {
		
		return MandrillUtil.query(
				rootUrl+ "urls/list.json", 
				MandrillUtil.paramsWithKey(key), 
				MandrillUrl[].class);
		
	}
	
	/**
	 * <p>Get the 100 most clicked URLs that match 
	 * the search query given.</p>
	 * @param query A search query.
	 * @return An array of {@link MandrillUrl} objects with 
	 * the 100 most clicked URLs matching the search query.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillUrl[] search(final String query) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("q", query);
		return MandrillUtil.query(rootUrl+ "urls/search.json", 
				params, MandrillUrl[].class);
		
	}
	
	/**
	 * <p>Get the recent history (hourly stats for the 
	 * last 30 days) for a url.</p>
	 * @param url An existing URL.
	 * @return An array of {@link MandrillTimeSeries} objects.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillTimeSeries[] timeSeries(final String url) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("url", url);
		return MandrillUtil.query(rootUrl+ "urls/time-series.json", 
				params, MandrillTimeSeries[].class);
		
	}
}
