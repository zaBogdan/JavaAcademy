package com.example.application.views.blog;

import com.example.application.data.service.QuizService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("Dashboard")
@PermitAll
public class DashBoardView extends VerticalLayout {
    private final QuizService service;

    public DashBoardView(QuizService service) {
        this.service = service;
        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("background-color", "white");
        add(getQuizStats(), getCompaniesChart());
    }

    private Component getQuizStats() {
        Span stats = new Span(service.countQuizzes() + " quizzes");
        stats.addClassNames("text-xl", "mt-m");
        return stats;
    }

    private Chart getCompaniesChart() {
        Chart chart = new Chart(ChartType.PIE);

        String[] programmingLanguages = new String[]{"Java", "PHP", "JavaScript"};

        DataSeries dataSeries = new DataSeries();
        for (String string : programmingLanguages) {
            dataSeries.add(new DataSeriesItem(string, service.getProgrammingLanguageCount(string)));
        }

        chart.getConfiguration().setSeries(dataSeries);
        return chart;
    }
}