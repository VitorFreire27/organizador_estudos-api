package com.senac.df.organizador.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.senac.df.organizador.domain.Usuario;
import com.senac.df.organizador.resources.UsuarioResource;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("register")
public class RegisterView extends VerticalLayout {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UsuarioResource resource;

    private final TextField nameField = new TextField("Nome");
    private final EmailField emailField = new EmailField("Email");
    private final PasswordField passwordField = new PasswordField("Senha");
    private final Button registerButton = new Button("Registrar");
    Anchor loginLink = new Anchor("", "Voltar para Login");

    public RegisterView() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(nameField, emailField, passwordField);

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        setSizeFull();

        H1 h1 = new H1("Register");

        formLayout.setWidth("300px");
        formLayout.getStyle().set("margin", "0 auto");
        getStyle().set("background-color", "#f5f5f5");
        getStyle().set("padding", "20px");
        getStyle().set("border-radius", "5px");
        getStyle().set("box-shadow", "0px 0px 10px rgba(0, 0, 0, 0.2)");
        registerButton.getStyle().set("background-color", "#007ba0");
        registerButton.getStyle().set("color", "#fff");
        registerButton.getStyle().set("font-size", "16px");
        loginLink.getStyle().set("text-decoration", "none");
        loginLink.getStyle().set("color", "#007ba0");

        add(h1, formLayout, registerButton, loginLink);

        registerButton.addClickListener(event -> {
            String name = nameField.getValue();
            String email = emailField.getValue();
            String password = passwordField.getValue();

            try {
                Usuario user = new Usuario(null, name, email, password);
                resource.create(user);
                Notification.show("Registro bem-sucedido!", 3000, Position.TOP_END);
            } catch (Exception e) {
                Notification.show("Falha ao registrar!" + e, 10000, Position.TOP_END);
            }
        });
    }
}
