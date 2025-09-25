package com.freedcamp.api.models.tasks;

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
public class TasksItem{

	@JsonProperty("start_ts")
	private Object startTs;

	@JsonProperty("status_title")
	private String statusTitle;

	@JsonProperty("parent")
	private Object parent;

	@JsonProperty("f_r_icon")
	private boolean fRIcon;

	@JsonProperty("f_r_disable")
	private boolean fRDisable;

	@JsonProperty("assigned_to_fullname")
	private String assignedToFullname;

	@JsonProperty("planner_order")
	private int plannerOrder;

	@JsonProperty("can_comment")
	private boolean canComment;

	@JsonProperty("status_id")
	private int statusId;

	@JsonProperty("created_ts")
	private int createdTs;

	@JsonProperty("project_id")
	private String projectId;

	@JsonProperty("children")
	private List<Object> children;

	@JsonProperty("can_delete")
	private boolean canDelete;

	@JsonProperty("id")
	private String id;

	@JsonProperty("task_group_name")
	private String taskGroupName;

	@JsonProperty("app_id")
	private String appId;

	@JsonProperty("order")
	private int order;

	@JsonProperty("can_progress")
	private boolean canProgress;

	@JsonProperty("f_adv_subtask")
	private boolean fAdvSubtask;

	@JsonProperty("custom_fields")
	private List<Object> customFields;

	@JsonProperty("can_assign")
	private boolean canAssign;

	@JsonProperty("h_parent_id")
	private String hParentId;

	@JsonProperty("h_level")
	private int hLevel;

	@JsonProperty("priority")
	private int priority;

	@JsonProperty("tags")
	private List<Object> tags;

	@JsonProperty("followers")
	private List<String> followers;

	@JsonProperty("comments_count")
	private int commentsCount;

	@JsonProperty("r_disabled_msg")
	private String rDisabledMsg;

	@JsonProperty("f_matched")
	private boolean fMatched;

	@JsonProperty("files")
	private List<Object> files;

	@JsonProperty("created_by_id")
	private String createdById;

	@JsonProperty("f_visible")
	private boolean fVisible;

	@JsonProperty("status")
	private int status;

	@JsonProperty("f_adv_data")
	private boolean fAdvData;

	@JsonProperty("assigned_to_id")
	private String assignedToId;

	@JsonProperty("priority_title")
	private String priorityTitle;

	@JsonProperty("description_processed")
	private String descriptionProcessed;

	@JsonProperty("f_archived_list")
	private boolean fArchivedList;

	@JsonProperty("description")
	private String description;

	@JsonProperty("f_r_show_create_next")
	private boolean fRShowCreateNext;

	@JsonProperty("cf_tpl_id")
	private String cfTplId;

	@JsonProperty("title")
	private String title;

	@JsonProperty("ms_order")
	private int msOrder;

	@JsonProperty("h_top_id")
	private String hTopId;

	@JsonProperty("r_rule")
	private String rRule;

	@JsonProperty("linked_items")
	private List<Object> linkedItems;

	@JsonProperty("r_next_ts")
	private Object rNextTs;

	@JsonProperty("updated_ts")
	private int updatedTs;

	@JsonProperty("cs_id")
	private Object csId;

	@JsonProperty("files_count")
	private int filesCount;

	@JsonProperty("comments")
	private List<Object> comments;

	@JsonProperty("task_group_id")
	private String taskGroupId;

	@JsonProperty("completed_ts")
	private Object completedTs;

	@JsonProperty("can_edit")
	private boolean canEdit;

	@JsonProperty("f_r_ended")
	private boolean fREnded;

	@JsonProperty("assigned_ids")
	private List<String> assignedIds;

	@JsonProperty("due_ts")
	private Object dueTs;

	@JsonProperty("url")
	private String url;

	@JsonProperty("f_r_hide")
	private boolean fRHide;

	@JsonProperty("progress")
	private int progress;

    @JsonProperty("ms_id")
    private String msId;
}