package com.example.application.views.quiz;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PageTitle("Quiz")
@Route(value = "quiz", layout = MainLayout.class)
@PermitAll
@Tag("div")
public class QuizView extends VerticalLayout {

    public QuizView() {
        setSpacing(false);

//        AceEditor ace = new AceEditor();
//        ace.setTheme(AceTheme.terminal);
//        ace.setMode(AceMode.sql);
//        ace.setValue("SELECT * FROM DB_01 WHERE ID = 123");
//        ace.setVisible(true);

        Div div = new Div();
        div.setId("editor");
        div.add(new H1("function foo(items) {\n" +
                "    var x = \"All this is syntax highlighted\";\n" +
                "    return x;"));

        add(div);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }
}
