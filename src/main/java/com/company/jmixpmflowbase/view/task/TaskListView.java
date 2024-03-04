package com.company.jmixpmflowbase.view.task;

import com.company.jmixpmflowbase.entity.Project;
import com.company.jmixpmflowbase.entity.Task;

import com.company.jmixpmflowbase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;

@Route(value = "tasks", layout = MainView.class)
@ViewController("Task_.list")
@ViewDescriptor("task-list-view.xml")
@LookupComponent("tasksDataGrid")
@DialogMode(width = "64em")
public class TaskListView extends StandardListView<Task> {
    @ViewComponent
    private CollectionLoader<Task> tasksDl;

    public void withProject(Project project){
        if(project != null){
            tasksDl.setParameter("project", project);
        }
        else {
            tasksDl.removeParameter("project");
        }

        tasksDl.load();
    }
}