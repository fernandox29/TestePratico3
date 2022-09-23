package com.advantageonlineshopping.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConectarBancoTest {

    public static String resultado = null;
    static Connection con = null;
    private static Statement stmt;
    public static String DB_URL = "jdbc:mysql://localhost/banco_teste_automacao";
    public static String DB_USER = "root";
    public static String DB_PASSWORD = "123456";

    public void conexaoBanco(){
        try {
            String dbClass = "com.mysql.cj.jdbc.Driver";
            Class.forName(dbClass).newInstance();

            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println("Driver do banco de dados não localizado");
            e.printStackTrace();
        }
    }

    public String pesquisarBancoDados(String nomeColuna) throws Exception {
        conexaoBanco();
        try {
            String query = "select * from banco_teste_automacao.massas";
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                resultado = res.getString(nomeColuna);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro na conexão com o banco!");
        }
        if (con != null) {
            con.close();
        }
        return resultado;
    }

    public void alterarBancoDados(String nomeColuna, String alteracao) throws Exception {
        conexaoBanco();
        try {
            String query = "update banco_teste_automacao.massas set "+nomeColuna+"= '"+alteracao+"'";
            stmt.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro na conexão com o banco!");
        }
        if (con != null) {
            con.close();
        }
    }
}