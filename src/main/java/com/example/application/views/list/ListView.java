package com.example.application.views.list;

import com.example.application.data.entity.Quiz;
import com.example.application.data.service.QuizService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@Route(value = "quizzes", layout = MainLayout.class)
@PageTitle("Quizzes")
@PermitAll
public class ListView extends VerticalLayout {

    Grid<Quiz> grid = new Grid<>(Quiz.class);

    TextField filterText = new TextField();

    QuizService service;

    public ListView(QuizService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        add(getContent());
        updateList();
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid);
        content.setFlexGrow(2, grid);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureGrid() {
        grid.addClassNames("quiz-grid");
        grid.setSizeFull();
        grid.setColumns("programmingLanguage", "level", "duration");
        grid.addColumn(quiz -> quiz.getScore()).setHeader("Score");
//        grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                startQuiz(event.getValue()));
    }

    private void startQuiz(Quiz value) {
        UI.getCurrent().navigate("quiz");
    }

//    private HorizontalLayout getToolbar() {
//        filterText.setPlaceholder("Filter by name...");
//        filterText.setClearButtonVisible(true);
//        filterText.setValueChangeMode(ValueChangeMode.LAZY);
//        filterText.addValueChangeListener(e -> updateList());
//
//        Button addContactButton = new Button("Add contact");
//
//        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
//        toolbar.addClassName("toolbar");
//        return toolbar;
//    }

    private void updateList() {
        grid.setItems(service.findAllQuizzes());
    }
}