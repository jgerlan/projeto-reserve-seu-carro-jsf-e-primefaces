package com.projetoes.ecommerce.controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetoes.ecommerce.respository.UsuarioDAO;


@Named
@SessionScoped
public class RecuperarSenhaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	

    @Inject
    private UsuarioDAO usuarioDAO;

    private String login;
    private String dataNasc;

	public static String getSenha(String login, String dataNasc) {
	        String senha = null;
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;

	        try {
	            Class.forName("org.postgresql.Driver");
	            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projetoes", "postgres", "Anar20102010!");

	            String query = "SELECT senha FROM usuarios WHERE login = ? AND data_nascimento = ?";
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setString(1, login);
	            preparedStatement.setString(2, dataNasc);

	            resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                senha = resultSet.getString("senha");
	            }

	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (resultSet != null) {
	                    resultSet.close();
	                }
	                if (preparedStatement != null) {
	                    preparedStatement.close();
	                }
	                if (connection != null) {
	                    connection.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        return senha;
	    }
	
	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getDataNasc() {
		return dataNasc;
	}


	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

}
