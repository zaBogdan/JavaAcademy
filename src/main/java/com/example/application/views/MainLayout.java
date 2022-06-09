package com.example.application.views;


import com.example.application.data.entity.User;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.blog.DashBoardView;
import com.example.application.views.careers.CareersView;
import com.example.application.views.features.FeaturesView;
import com.example.application.views.helloworld.HelloWorldView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.RouterLink;

import java.util.Optional;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    public static AuthenticatedUser getAuthenticatedUser() {
        return authenticatedUser;
    }

    public static void setAuthenticatedUser(AuthenticatedUser authenticatedUser) {
        MainLayout.authenticatedUser = authenticatedUser;
    }

    /**
     * A simple navigation item component, based on ListItem element.
     */
    public static class MenuItemInfo extends ListItem {

        private final Class<? extends Component> view;

        public MenuItemInfo(String menuTitle, String iconClass, Class<? extends Component> view) {
            this.view = view;
            RouterLink link = new RouterLink();
            // Use Lumo classnames for various styling
            link.addClassNames("flex", "h-m", "items-center", "px-s", "relative", "text-secondary");
            link.setRoute(view);
            link.getStyle().set("color", "white");

            Span text = new Span(menuTitle);
            // Use Lumo classnames for various styling
            text.addClassNames("font-medium", "text-s", "whitespace-nowrap");

            link.add(new LineAwesomeIcon(iconClass), text);
            add(link);
        }

        public Class<?> getView() {
            return view;
        }

        /**
         * Simple wrapper to create icons using LineAwesome iconset. See
         * https://icons8.com/line-awesome
         */
        @NpmPackage(value = "line-awesome", version = "1.3.0")
        public static class LineAwesomeIcon extends Span {
            public LineAwesomeIcon(String lineawesomeClassnames) {
                // Use Lumo classnames for suitable font size and margin
                addClassNames("me-s", "text-l");
                if (!lineawesomeClassnames.isEmpty()) {
                    addClassNames(lineawesomeClassnames);
                }
            }
        }

    }

    public MainLayout() {
        addToNavbar(createHeaderContent());

        getStyle().set("background-color", "rgb(14,21,37)");
    }

    private static AuthenticatedUser authenticatedUser;

    private Component createHeaderContent() {
        Header header = new Header();
        header.addClassNames("bg-base", "border-b", "border-contrast-10", "box-border", "flex", "w-full");
        header.getStyle().set("display", "flex")
                .set("flex-direction", "row")
                .set("justify-content", "space-between")
                .set("align-items", "center")
                .set("background-color", "#0E1525")
                .set("border-color", "#1C2333")
                .set("padding", "10px")
                .set("height", "60px");

        Div layout = new Div();
        layout.addClassNames("flex", "h-xl", "items-center", "px-l");

        H1 appName = new H1("Jademy");
        appName.addClassNames("my-0", "me-auto", "text-l");
        appName.getStyle().set("color", "white");
        layout.add(appName);

        Div div = new Div();
        div.getStyle().set("display", "flex")
                .set("flex-direction", "row")
                .set("gap", "10px");

        Optional<User> maybeUser = null;
        try {
            maybeUser = authenticatedUser.get();
        } catch (NullPointerException exception) {
            System.out.println(exception);
        }

        if (maybeUser != null && maybeUser.isPresent()) {
            User user = maybeUser.get();

//            Avatar avatar = new Avatar(user.getName(), user.getProfilePictureUrl());
//            avatar.addClassNames("me-xs");

            ContextMenu userMenu = new ContextMenu();
            userMenu.setOpenOnClick(true);
            userMenu.addItem("Logout", e -> {
                authenticatedUser.logout();
            });

            Span name = new Span(user.getName());
            name.addClassNames("font-medium", "text-s", "text-secondary");

            div.add(name);
        } else {
            Anchor loginLink = new Anchor("login", "Log in");
            loginLink.getStyle().set("color", "#57ABFF")
                    .set("background-color", "#2B3245")
                    .set("border", "1px solid #4E5569")
                    .set("border-radius", "4px")
                    .set("padding", "8px")
                    .set("font-weight", "500")
                    .set("width", "auto")
                    .set("line-height", "14px")
                    .set("height", "14px");

            div.add(loginLink);

            Anchor signUpLink = new Anchor("signup", "Sign in");
            signUpLink.getStyle().set("color", "white")
                    .set("background-color", "#0079F2")
                    .set("border", "1px solid #0079F2")
                    .set("border-radius", "4px")
                    .set("padding", "8px")
                    .set("font-weight", "500")
                    .set("width", "auto")
                    .set("line-height", "14px")
                    .set("height", "14px");

            div.add(signUpLink);
        }

        Nav nav = new Nav();
        nav.addClassNames("flex", "gap-s", "overflow-auto", "px-m");

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames("flex", "list-none", "m-0", "p-0");
        list.getStyle().set("display", "flex")
                .set("flex-direction", "row")
                .set("gap", "25px");
        nav.add(list);

        for (MenuItemInfo menuItem : createMenuItems()) {
            list.add(menuItem);
        }

        header.add(layout, nav, div);
        return header;
    }

    private MenuItemInfo[] createMenuItems() {
        return new MenuItemInfo[]{ //
                new MenuItemInfo("Hello World", "la la-globe", HelloWorldView.class), //

                new MenuItemInfo("Features", "la la-star-half", FeaturesView.class), //

                new MenuItemInfo("Dashboard", "lab la-free-code-camp", DashBoardView.class), //

                new MenuItemInfo("Careers", "la la-suitcase", CareersView.class), //

        };
    }

}
