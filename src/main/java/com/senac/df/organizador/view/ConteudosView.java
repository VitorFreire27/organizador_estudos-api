package com.senac.df.organizador.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.senac.df.organizador.domain.Conteudo;
import com.senac.df.organizador.repositories.ConteudoRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("conteudos")
public class ConteudosView extends VerticalLayout {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ConteudoRepository repository;

    private Grid<Conteudo> grid;

    private Button newBtn = new Button("Novo");
    private Button deleteBtn = new Button("Apagar");
    private Button saveBtn = new Button("Salvar");
    private HorizontalLayout btnLayout = new HorizontalLayout();
    private HorizontalLayout fieldsLayout = new HorizontalLayout();
    private final RouterLink materiaLink = new RouterLink("Matérias", MateriasView.class);

    private TextField nome = new TextField("Nome");
    private IntegerField id = new IntegerField("ID");

    private String MAX_WIDTH = "604px";
    private String BUTTON_WIDTH = "191.33px";

    public ConteudosView(ConteudoRepository repo) {
        this.repository = repo;
        this.grid = new Grid<>(Conteudo.class, false);
        grid.addColumn(Conteudo::getId).setHeader("ID").setSortable(true).setWidth("20px");
        grid.addColumn(Conteudo::getName).setHeader("Nome").setSortable(true);
        grid.setMaxWidth(MAX_WIDTH);

        // Configuração de layout
        setAlignItems(FlexComponent.Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        deleteBtn.setEnabled(false);

        H1 h1 = new H1("Conteúdos");

        newBtn.setWidth(BUTTON_WIDTH);
        deleteBtn.setWidth(BUTTON_WIDTH);
        saveBtn.setWidth(BUTTON_WIDTH);

        btnLayout.add(newBtn, deleteBtn, saveBtn);
        btnLayout.setMaxWidth(MAX_WIDTH);
        fieldsLayout.add(nome);

        getStyle().set("background-color", "#f5f5f5");
        getStyle().set("padding", "20px");
        getStyle().set("border-radius", "5px");
        getStyle().set("box-shadow", "0px 0px 10px rgba(0, 0, 0, 0.2)");
        newBtn.getStyle().set("background-color", "#007ba0");
        newBtn.getStyle().set("color", "#fff");
        newBtn.getStyle().set("font-size", "16px");
        deleteBtn.getStyle().set("background-color", "#007ba0");
        deleteBtn.getStyle().set("color", "#fff");
        deleteBtn.getStyle().set("font-size", "16px");
        saveBtn.getStyle().set("background-color", "#007ba0");
        saveBtn.getStyle().set("color", "#fff");
        saveBtn.getStyle().set("font-size", "16px");

        materiaLink.getStyle().set("color", "#007ba2");
        
        // Configuração do layout para o grid ocupar 100% do espaço
        FlexLayout centerLayout = new FlexLayout(grid);
        centerLayout.setSizeFull();
        centerLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        centerLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        add(h1);
        add(fieldsLayout);
        add(btnLayout);
        add(centerLayout); // Use o centroLayout em vez do grid diretamente
        add(materiaLink);
        refreshTableData();
        addButtonsActionListeners();
    }

    private void addButtonsActionListeners() {

        grid.addSelectionListener(selected -> {
            if (selected.getAllSelectedItems().isEmpty()) {
                deleteBtn.setEnabled(false);
                clearInputFields();
            } else {
                deleteBtn.setEnabled(true);
                Conteudo selectedCustomer = selected.getFirstSelectedItem().get();
                nome.setValue(selectedCustomer.getName());
                id.setValue(selectedCustomer.getId());
            }
        });

        newBtn.addClickListener(click -> {
            clearInputFields();
            grid.select(null);
        });

        deleteBtn.addClickListener(click -> {
            try {
                repository.delete(grid.getSelectedItems().stream().toList().get(0));
                Notification.show("Conteúdos excluídos com sucesso.", 3000, Position.TOP_END);
                refreshTableData();
                clearInputFields();
            } catch (Exception e) {
                Notification.show("Não é possível excluir Conteúdos", 3000, Position.TOP_END);
            }

        });

        saveBtn.addClickListener(click -> {
            Conteudo customer = new Conteudo();
            customer.setName(nome.getValue());

            customer.setId(id.getValue());
            repository.save(customer);
            Notification.show("Conteúdos criados com sucesso.", 3000, Position.TOP_END);
            refreshTableData();
        });
    }

    private void refreshTableData() {
        grid.setItems(repository.findAll());
    }

    private void clearInputFields() {
        nome.clear();
        id.clear();
    }

}
