package com.freedcamp.testdata.utils;

import com.freedcamp.api.controllers.GroupController;
import com.freedcamp.api.controllers.ProjectController;
import com.freedcamp.api.controllers.TaskController;
import com.freedcamp.api.models.groups.CreateGroupResponseDto;
import com.freedcamp.api.models.projects.createProjectResponse.CreateProjectResponseDto;
import com.freedcamp.api.models.projects.getAllProjects.GetAllProjectsDto;
import com.freedcamp.api.models.tasks.CreateTaskResponseDto;
import com.freedcamp.testdata.CreatedProject;
import com.freedcamp.testdata.CreatedProjectFromTemplate;
import com.freedcamp.testdata.CreatedTask;
import com.freedcamp.utils.ProjectCreationWaiter;
import io.restassured.response.Response;

import static com.freedcamp.testdata.utils.TestDataFactory.validTaskDto;
import static org.assertj.core.api.Assertions.assertThat;

public class TestDataProvider {

    private final ProjectController projectController;
    private final TaskController taskController;
    private final ProjectCreationWaiter projectCreationWaiter;
    private final GroupController groupController;

    public TestDataProvider(ProjectController projectController, TaskController taskController,GroupController groupController,
                            ProjectCreationWaiter projectCreationWaiter) {
        this.projectController = projectController;
        this.taskController = taskController;
        this.groupController = groupController;
        this.projectCreationWaiter = projectCreationWaiter;
    }

    /**
     * Creates a project and a task inside it. Returns task ID and original DTO for later use.
     */
    public CreatedTask createTask(String projectId) {
        var taskDto = validTaskDto(projectId);
        var response = taskController.createTask(taskDto);

        assertThat(response.statusCode()).isEqualTo(200);

        var taskId = response.as(CreateTaskResponseDto.class)
                .getData().getTasks().get(0).getId();

        return new CreatedTask(taskId, taskDto);
    }

    /**
     * Deletes the created project by ID.
     */
    public void deleteCreatedProject(String projectId) {
        Response response = projectController.deleteProject(projectId);
        assertThat(response.statusCode()).isEqualTo(200);
    }

    /**
     * Creates a group and returns the created groupId.
     */
    public String createGroup() {
        var createGroupDto = TestDataFactory.validCreateGroupDto();
        var response = groupController.createGroup(createGroupDto);
        assertThat(response.statusCode()).isEqualTo(200);
        return response.as(CreateGroupResponseDto.class).getData().getGroups().get(0).getGroupId();
    }

    /**
     * Deletes the created group by ID.
     */
    public void deleteGroup(String groupId) {
        var response = groupController.deleteGroup(Integer.parseInt(groupId));
        assertThat(response.statusCode()).isEqualTo(200);
    }

    /**
     * Creates a project in the specified group and returns the created project and original DTO for later use.
     */
    public CreatedProject createProjectInGroup(String groupId) {
        var projectDto = TestDataFactory.validProjectDto();
        projectDto.setGroupId(groupId);
        var response = projectController.createProject(projectDto);
        assertThat(response.statusCode()).isEqualTo(200);
        return new CreatedProject(response.as(CreateProjectResponseDto.class), projectDto);
    }

    /**
     * Creates a project from a template in the specified group and returns the created project and original DTO for later use.
     */
    public CreatedProjectFromTemplate createdProjectFromTemplateInGroup(String groupId) {
        var projectDto = TestDataFactory.validProjectFromTemplateDto();
        projectDto.setGroupId(groupId);
        var response = projectController.createProjectFromTemplate(projectDto);
        assertThat(response.statusCode()).isEqualTo(200);
        projectCreationWaiter.waitUntilProjectAppears(projectDto.getProjectName());
        var createdProjectDto = projectController.getAllProjects()
                .as(GetAllProjectsDto.class)
                .getData().getProjects().stream()
                .filter(project -> project.getProjectName().equals(projectDto.getProjectName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Created project not found"));
        return new CreatedProjectFromTemplate(createdProjectDto, projectDto);
    }
}
