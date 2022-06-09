package com.example.application.views.careers;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PageTitle("Careers")
@Route(value = "careers", layout = MainLayout.class)
@PermitAll
public class CareersView extends VerticalLayout {

    public CareersView() {
        setSpacing(false);

        H1 title = new H1("Careers at Jademy");
        title.getStyle().set("font-size", "62px")
                .set("font-weight", "500px")
                .set("color", "white")
                .set("text-shadow", "0 0 20px #0053A6")
                .set("margin-bottom", "16px");

        add(title);

        Paragraph content = new Paragraph("We're on a mission to bring the next billion software creators online. " +
                "Creating the future of computing is a team effort though.");

        content.getStyle().set("font-size", "24px")
                .set("color", "#C2C8CC")
                .set("text-shadow", "0 0 20px #0E1525")
                .set("max-width", "550px")
                .set("text-align", "left")
                .set("margin-bottom", "16px");

        add(content);

        Anchor anchor = new Anchor("https://www.linkedin.com/home", "See open positions");
        anchor.getStyle().set("color", "#F5F9FC")
                .set("background-color", "#0053A6")
                .set("box-shadow", "none")
                .set("padding", "8px")
                .set("border-radius", "8px")
                .set("font-size", "16px")
                .set("font-weight", "500")
                .set("padding", "8px 12px");

        add(anchor);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.START);
        getStyle().set("text-align", "center")
                .set("margin-left", "100px");
    }

}
