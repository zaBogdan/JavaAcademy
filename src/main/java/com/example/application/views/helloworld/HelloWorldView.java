package com.example.application.views.helloworld;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.svg.Svg;
import com.vaadin.flow.component.svg.elements.Path;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import javax.annotation.security.PermitAll;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class HelloWorldView extends VerticalLayout {

    private TextField name;
    private Button sayHello;

    public HelloWorldView() {
//        name = new TextField("Your name");
//        sayHello = new Button("Say hello");
//        sayHello.addClickListener(e -> {
//            Notification.show("Hello " + name.getValue());
//        });
//        sayHello.addClickShortcut(Key.ENTER);

//        setMargin(true);
//        setVerticalComponentAlignment(Alignment.END, name, sayHello);
//
//        add(name, sayHello);

        H1 header = new H1("Create, code, and learn together");
        header.getStyle().set("font-size", "80px")
                .set("font-weight", "500px")
                .set("color", "white")
                .set("text-shadow", "2px 2px 60px #0079f2")
                .set("max-width", "720px")
                .set("margin-top", "550px");

        add(header);

        H2 subheader = new H2("Use our free, collaborative, in-browser IDE to code in 50+ languages — without spending a second on setup.");
        subheader.getStyle().set("color", "#C2C8CC")
                .set("font-size", "20px")
                .set("font-weight", "400")
                .set("line-height", "1.25em")
                .set("max-width", "720px");

        add(subheader);

        Button button = new Button("<> Start coding");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickListener(this::navigate);

        button.getStyle().set("margin-top", "26px")
                .set("padding", "20px 14px")
                .set("background-color", "#0053A6");

        add(button);

        H2 heading = new H2("Features for all skill levels");
        heading.getStyle().set("font-size", "56px")
                .set("font-weight", "500")
                .set("color", "white");

        add(heading);

        Div itemContainer = new Div();
        itemContainer.getStyle().set("display", "flex")
                .set("flex-direction", "row")
                .set("justify-content", "space-around")
                .set("gap", "25px")
                .set("padding", "24px");

        Div[] featureItem = new Div[3];

        for (int i = 0; i < 3; i++) {
            featureItem[i] = new Div();
            featureItem[i].getStyle().set("display", "flex")
                    .set("flex-direction", "column")
                    .set("justify-content", "flex-start")
                    .set("padding", "24px")
                    .set("background-color", "#1C2333")
                    .set("border", "2px solid #004182")
                    .set("height", "200px")
                    .set("width", "200px")
                    .set("border-radius", "8px")
                    .set("text-align", "left");

            itemContainer.add(featureItem[i]);
        }

        H1[] headings = new H1[3];

        headings[0] = new H1("In-browser IDE");
        headings[1] = new H1("50+ languages");
        headings[2] = new H1("Learning resources");

        for (int i = 0; i < 3; i++) {
            headings[i].getStyle().set("font-size", "20px")
                    .set("font-weight", "500")
                    .set("color", "white");
        }

        H2[] subheadings = new H2[3];

        subheadings[0] = new H2("Start coding with your favorite language on any platform, OS, and device.");
        subheadings[1] = new H2("From Python, to C++, to HTML and CSS, stay in one platform to learn and code in any language you want.");
        subheadings[2] = new H2("Learn how to code from 3 million+ passionate programmers, technologists, creatives, and learners of all kinds.");

        for (int i = 0; i < 3; i++) {
            subheadings[i].getStyle().set("font-size", "16px")
                    .set("font-weight", "300")
                    .set("color", "white");
        }

        Svg svg = new Svg();
        Path path = new Path("path", "M8 21H16M12 17V21M4 3H20C21.1046 3 22 3.89543 22 5V15C22 16.1046 21.1046 17 20 17H4C2.89543 17 2 16.1046 2 15V5C2 3.89543 2.89543 3 4 3Z");
        path.setStroke("#ff0066", 20, Path.LINE_CAP.ROUND,
                Path.LINE_JOIN.ROUND);
        path.setFillColor("red");

        svg.setVisible(true);
        svg.add(path);

        add(svg);

        for (int i = 0; i < 3; i++) {
            featureItem[i].add(headings[i], subheadings[i]);
            itemContainer.add(featureItem[i]);
        }

        add(itemContainer);

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
