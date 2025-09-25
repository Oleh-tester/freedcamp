package com.freedcamp.api.models.groups;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupsItem {

	@JsonProperty("f_template_group")
	private boolean fTemplateGroup;

	@JsonProperty("projects")
	private List<Object> projects;

	@JsonProperty("f_can_leave_group")
	private boolean fCanLeaveGroup;

	@JsonProperty("owner_id")
	private String ownerId;

	@JsonProperty("description")
	private String description;

	@JsonProperty("f_sms")
	private boolean fSms;

	@JsonProperty("ms_data")
	private MsData msData;

	@JsonProperty("group_unique_name")
	private String groupUniqueName;

	@JsonProperty("domain_id")
	private int domainId;

	@JsonProperty("features")
	private Features features;

	@JsonProperty("group_id")
	private String groupId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("group_admins")
	private List<Object> groupAdmins;

	@JsonProperty("f_managed")
	private boolean fManaged;

	@JsonProperty("order")
	private int order;

	@JsonProperty("applications")
	private List<Object> applications;
}