package com.freedcamp.api.models.groups.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupUsersItem {

	@JsonProperty("role_type")
	private String roleType;

	@JsonProperty("id")
	private String id;
}