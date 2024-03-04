package com.company.jmixpmflowbase.view.project;

import com.company.jmixpmflowbase.entity.Project;

import com.company.jmixpmflowbase.view.main.MainView;

import com.company.jmixpmflowbase.view.task.TaskListView;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "projects", layout = MainView.class)
@ViewController("Project.list")
@ViewDescriptor("project-list-view.xml")
@LookupComponent("projectsDataGrid")
@DialogMode(width = "64em")
public class ProjectListView extends StandardListView<Project> {
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private DialogWindows dialogWindows;

    @Supply(to = "projectsDataGrid.showTasks", subject = "renderer")
    private Renderer<Project> projectsDataGridShowTasksRenderer() {
        return new ComponentRenderer<> (project -> {
            JmixButton linkButton = uiComponents.create(JmixButton.class);
            linkButton.setText("Show Tasks");
            linkButton.addClickListener(clickEvent -> {
                openTasksDialog(project);
            });
            return linkButton;
        });
    }

    private void openTasksDialog(Project project) {
        DialogWindow<TaskListView> tasksDialog = dialogWindows.view(this, TaskListView.class)
                .build();
        tasksDialog.getView().withProject(project);
        tasksDialog.open();

    }
}