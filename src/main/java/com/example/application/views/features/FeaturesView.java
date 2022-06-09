package com.example.application.views.features;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PageTitle("Features")
@Route(value = "features", layout = MainLayout.class)
@PermitAll
public class FeaturesView extends VerticalLayout {

    public FeaturesView() {
        setSpacing(false);

        H3 heading = new H3("Instant IDE");
        heading.getStyle().set("font-size", "32px")
                .set("font-weight", "500")
                .set("font-size", "32px")
                .set("color", "#0079F2");

        add(heading);

        H2 subheader = new H2("Code from your browser.");
        subheader.getStyle().set("color", "white")
                .set("font-size", "56px")
                .set("font-weight", "500")
                .set("text-shadow", "2px 2px 60px #0079f2");

        add(subheader);

        Paragraph content = new Paragraph("Collaborative, 0 setup, and Embeddable. Jademy is the best tool for quickly starting, sharing, and " +
                "developing projects in any programming language, right from your browser. ");
        content.getStyle().set("color", "#C2C8CC")
                .set("font-size", "16px")
                .set("max-width", "750px");

        add(content);

        Button button = new Button("<> Start coding");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickListener(this::navigate);

        button.getStyle().set("margin-top", "36px")
                .set("padding", "20px 14px")
                .set("background-color", "#0053A6");

        add(button);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

    private void navigate(ClickEvent<Button> buttonClickEvent) {
        if (MainLayout.getAuthenticatedUser() == null) {
            UI.getCurrent().navigate("login");
        } else {
            UI.getCurrent().navigate("blog");
        }
    }

}
