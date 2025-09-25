package com.freedcamp.api.models.groups;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Features {

	@JsonProperty("f_dropbox_installed")
	private boolean fDropboxInstalled;

	@JsonProperty("f_paid_user")
	private boolean fPaidUser;

	@JsonProperty("f_mua_installed")
	private boolean fMuaInstalled;

	@JsonProperty("f_custom_message")
	private boolean fCustomMessage;

	@JsonProperty("f_mile_ch_reason")
	private boolean fMileChReason;

	@JsonProperty("f_email_in")
	private boolean fEmailIn;

	@JsonProperty("f_onedrive_installed")
	private boolean fOnedriveInstalled;

	@JsonProperty("f_mua")
	private boolean fMua;

	@JsonProperty("f_cf_allowed")
	private boolean fCfAllowed;

	@JsonProperty("f_cs_installed")
	private boolean fCsInstalled;

	@JsonProperty("f_ai")
	private boolean fAi;

	@JsonProperty("f_recurrence_allowed")
	private boolean fRecurrenceAllowed;

	@JsonProperty("f_mua_allowed")
	private boolean fMuaAllowed;

	@JsonProperty("f_onedrive")
	private boolean fOnedrive;

	@JsonProperty("f_reports_allowed")
	private boolean fReportsAllowed;

	@JsonProperty("f_cfp_installed")
	private boolean fCfpInstalled;

	@JsonProperty("f_cs_allowed")
	private boolean fCsAllowed;

	@JsonProperty("f_show_dfl")
	private boolean fShowDfl;

	@JsonProperty("f_cf_installed")
	private boolean fCfInstalled;

	@JsonProperty("f_gdrive")
	private boolean fGdrive;

	@JsonProperty("f_duration_picker")
	private boolean fDurationPicker;

	@JsonProperty("f_gdrive_installed")
	private boolean fGdriveInstalled;

	@JsonProperty("f_cfp_allowed")
	private boolean fCfpAllowed;

	@JsonProperty("f_dropbox")
	private boolean fDropbox;
}