package com.senac.df.organizador.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Route("home")
public class HomeView extends VerticalLayout {
    private static final long serialVersionUID = 1L;

    public HomeView() {
        List<Materia> materias = generateRandomMaterias(10); // Gere uma lista de 10 matérias fictícias

        // Crie um layout horizontal para os botões
        HorizontalLayout buttonsLayout = new HorizontalLayout();
        Button incluirButton = new Button("Incluir");
        Button editarButton = new Button("Editar");
        Button excluirButton = new Button("Excluir");

        incluirButton.getStyle().set("color", "#fff");
        editarButton.getStyle().set("color", "#fff");
        excluirButton.getStyle().set("color", "#fff");
        incluirButton.getStyle().set("background-color", "#007ba2");
        editarButton.getStyle().set("background-color", "#007ba2");
        excluirButton.getStyle().set("background-color", "#007ba2");
        buttonsLayout.add(incluirButton, editarButton, excluirButton);

        Grid<Materia> grid = new Grid<>(Materia.class);
        grid.setItems(materias);

        // Configure as colunas da tabela
        Column<Materia> nomeColuna = grid.addColumn(Materia::getNome).setHeader("Nome");
        Column<Materia> descricaoColuna = grid.addColumn(Materia::getDescricao).setHeader("Descrição");

        // Estilo para centralizar e dimensionar a tabela
        grid.setHeight("89vh");
        grid.setWidth("100%");

        // Adicione os botões e a tabela à HomeView
        add(buttonsLayout, grid);
    }

    private List<Materia> generateRandomMaterias(int numberOfMaterias) {
        List<Materia> materias = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numberOfMaterias; i++) {
            int id = i + 1;
            String nome = "Matéria " + id;
            String descricao = "Descrição da Matéria " + id;
            materias.add(new Materia(nome, descricao));
        }

        return materias;
    }

    public static class Materia {
        private String nome;
        private String descricao;

        public Materia(String nome, String descricao) {
            this.nome = nome;
            this.descricao = descricao;
        }

        public String getNome() {
            return nome;
        }

        public String getDescricao() {
            return descricao;
        }
    }
}
