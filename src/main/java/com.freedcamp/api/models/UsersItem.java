package com.freedcamp.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersItem {

	@JsonProperty("f_ghost")
	private boolean fGhost;

	@JsonProperty("full_name")
	private String fullName;

	@JsonProperty("avatar_url")
	private String avatarUrl;

	@JsonProperty("user_id")
	private String userId;

	@JsonProperty("timezone")
	private String timezone;

	@JsonProperty("last_name")
	private String lastName;

	@JsonProperty("language")
	private String language;

	@JsonProperty("first_name")
	private String firstName;

	@JsonProperty("default_board")
	private String defaultBoard;

	@JsonProperty("email")
	private String email;

	@JsonProperty("cal_week_start")
	private int calWeekStart;
}