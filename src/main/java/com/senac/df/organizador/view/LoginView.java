package com.senac.df.organizador.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.senac.df.organizador.domain.Usuario;
import com.senac.df.organizador.service.UsuarioService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("")
public class LoginView extends VerticalLayout {

	@Autowired
	UsuarioService service;

	private static final long serialVersionUID = 1L;

	private final TextField emailField = new TextField("Email");
	private final PasswordField passwordField = new PasswordField("Senha");
	private final Button loginButton = new Button("Login");
	private final RouterLink registerLink = new RouterLink("Registrar", RegisterView.class);

	public LoginView() {
		FormLayout formLayout = new FormLayout();
		formLayout.add(emailField, passwordField);

		setAlignItems(FlexComponent.Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);

		setSizeFull();

		H1 h1 = new H1("Login");
		
		formLayout.setWidth("300px");
		formLayout.getStyle().set("margin", "0 auto"); 

		getStyle().set("background-color", "#f5f5f5");
		getStyle().set("padding", "20px");
		getStyle().set("border-radius", "5px");
		getStyle().set("box-shadow", "0px 0px 10px rgba(0, 0, 0, 0.2)");
		loginButton.getStyle().set("background-color", "#007ba0");
		loginButton.getStyle().set("color", "#fff");
		loginButton.getStyle().set("font-size", "16px");
		registerLink.getStyle().set("color", "#007ba2");

		add(h1, formLayout, loginButton, registerLink);

		registerLink.getElement().getStyle().set("margin-top", "10px");

		setSpacing(true);

		loginButton.addClickListener(event -> {
			String email = emailField.getValue();
			String password = passwordField.getValue();

			if (authenticate(email, password)) {
				Notification.show("Login bem-sucedido!", 3000, Position.TOP_END);
				UI.getCurrent().navigate("home");

			} else {
				Notification.show("Credenciais inválidas. Tente novamente.", 3000, Position.TOP_END);
			}
		});
	}

	private boolean authenticate(String email, String password) {
		Integer id = service.getUserIdByEmail(email);
		if (id == null)
			return false;
		Usuario user = service.findById(id);
		if (user.getSenha().equals(password))
			return true;
		return false;
	}
}
