/**
 * 
 */
package com.wotifgroup.mandrillapp.lutung.view;

import java.util.Date;

import com.wotifgroup.mandrillapp.lutung.view.StatsBucket.Stats;

/**
 * @author rschreijer
 *
 */
public class MandrillSubaccountInfo {
	private String id, name, notes;
	private Integer custom_quota;
	private String status;
	private Integer reputation;
	private Date created_at, first_sent_at;
	private Integer sent_weekly, sent_monthly, sent_total, sent_hourly;
	private Integer hourly_quota;
	private Stats last_30_days;
	
	
	/**
	 * @return A unique indentifier for the subaccount.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return An optional display name for the subaccount.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return Optional extra text to associate with the subaccount.
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @return An optional manual hourly quota for the 
	 * subaccount. If not specified, the hourly quota 
	 * will be managed based on reputation.
	 */
	public Integer getCustomQuota() {
		return custom_quota;
	}
	/**
	 * @return The current sending status of the subaccount, 
	 * one of 'active' or 'paused'.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @return The subaccount's current reputation on a 
	 * scale from 0 to 100.
	 */
	public Integer getReputation() {
		return reputation;
	}
	/**
	 * @return The date and time that the subaccount was created.
	 */
	public Date getCreatedAt() {
		return created_at;
	}
	/**
	 * @return The date and time that the subaccount first sent.
	 */
	public Date getFirstSentAt() {
		return first_sent_at;
	}
	/**
	 * @return The number of emails the subaccount has sent 
	 * so far this week (weeks start on midnight Monday, UTC).
	 */
	public Integer getSentWeekly() {
		return sent_weekly;
	}
	/**
	 * @return The number of emails the subaccount has sent 
	 * so far this month (months start on midnight of the 1st, UTC).
	 */
	public Integer getSentMonthly() {
		return sent_monthly;
	}
	/**
	 * @return The number of emails the subaccount has sent 
	 * since it was created. 
	 */
	public Integer getSentTotal() {
		return sent_total;
	}
	/**
	 * @return The number of emails the subaccount has sent 
	 * in the last hour. 
	 */
	public Integer getSentHourly() {
		return sent_hourly;
	}
	/**
	 * @return The current hourly quota for the subaccount, 
	 * either manual or reputation-based.
	 */
	public Integer getHourlyQuota() {
		return hourly_quota;
	}
	/**
	 * @return Stats for this subaccount in the last 30 days.
	 */
	public Stats getLast30Days() {
		return last_30_days;
	}
	
}
