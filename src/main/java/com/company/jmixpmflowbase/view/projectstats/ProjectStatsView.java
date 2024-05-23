package com.company.jmixpmflowbase.view.projectstats;


import com.company.jmixpmflowbase.app.ProjectStatsService;
import com.company.jmixpmflowbase.entity.ProjectStats;
import com.company.jmixpmflowbase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "project-stats-view", layout = MainView.class)
@ViewController("ProjectStatsView")
@ViewDescriptor("project-stats-view.xml")
public class ProjectStatsView extends StandardView {

    @Autowired
    private ProjectStatsService projectStatService;

    @Install(to = "projectStatsDl", target = Target.DATA_LOADER)
    private List<ProjectStats> projectStatsDlLoadDelegate(LoadContext<ProjectStats> loadContext) {
        return projectStatService.fetchProjectStatistics();
    }
}
