package dao;

import classes.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AlunoDAO {
    public String insertAluno (Aluno aluno) {
        String resp = "";

        try {
            Connection con = Conecta.getConexao();
            String sql = "INSERT INTO dados (rgm, nome, nota1, nota2) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, aluno.getRgm());
            ps.setString(2, aluno.getNome());
            ps.setFloat(3, aluno.getNota1());
            ps.setFloat(4, aluno.getNota2());
            ps.execute();
            ps.close();
            con.close();
            resp = "OK";
        } catch (Exception e) {
            resp = "Erro: " + e.toString();
        }
        return resp;
    }

    public ArrayList<Aluno> getAlunos() {
        ArrayList<Aluno> lista = new ArrayList<>();

        try {
            Connection connection = Conecta.getConexao();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM dados ORDER BY nome";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Aluno aluno = new Aluno();
                aluno.setRgm(resultSet.getString("rgm"));
                aluno.setNome(resultSet.getString("nome"));
                aluno.setNota1(resultSet.getFloat("nota1"));
                aluno.setNota2(resultSet.getFloat("nota2"));

                lista.add(aluno);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Aluno consultarAluno (String rgm) {
        Aluno aluno = new Aluno();

        try {
            Connection connection = Conecta.getConexao();
            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM dados WHERE rgm = '" + rgm + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                aluno.setRgm(resultSet.getString("rgm"));
                aluno.setNome(resultSet.getString("nome"));
                aluno.setNota1(resultSet.getFloat("nota1"));
                aluno.setNota2(resultSet.getFloat("nota2"));

            } else {
                aluno.setRgm("");
                aluno.setNome("");
                aluno.setNota1(0);
                aluno.setNota2(0);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aluno;
    }

    public String alterarAluno(Aluno aluno) {
        String resp = "";
        try {
            Connection connection = Conecta.getConexao();
            String sql = "UPDATE dados nome = ?, nota1 = ?, nota2 = ? WHERE rgm=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setFloat(2, aluno.getNota1());
            preparedStatement.setFloat(3, aluno.getNota2());
            preparedStatement.setString(4, aluno.getRgm());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
            resp = "OK";
        } catch (Exception e) {
            resp = "Erro: " + e.toString();
        }
        return resp;
    }

    public String excluirAluno(Aluno aluno) {
        String resp = "";
        try {
            Connection connection = Conecta.getConexao();
            String sql = "DELETE FROM dados WHERE rgm=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, aluno.getRgm());

            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
            resp = "OK";
        } catch (Exception e) {
            resp = "Erro: " + e.toString();
        }
        return resp;
    }
}
