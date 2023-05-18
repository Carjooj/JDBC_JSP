<%-- 
    Document   : index
    Created on : 17 de mai. de 2023, 19:24:52
    Author     : carjooj
--%>
<%@page import = "java.util.ArrayList"%>
<%@page import = "classes.Aluno"%>
<%@page import = "dao.AlunoDAO"%>
<%@page import = "dao.Conecta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String status = request.getParameter("status");
    
    if(status != null) {
        if(status.equals("OK")) {
            out.println("Registro Inserido com Sucesso");
        }
        else {
                out.println("ERRO: "+ status);
        }
    }
    
    String rgm = request.getParameter("rgm");
    Aluno aluno = new Aluno();
    if(rgm != null) {
        aluno = new AlunoDAO().consultarAluno(rgm);
    }
    
%>
<html>
    <head>
        <title>Banco de dados</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    </head>

    <body>
        <form name="form1" action="gravar.jsp" method="post">
            <table border="1">
                <tr>
                    <td>RGM:</td>
                    <td><input type="text" name="rgm" value="<%= aluno.getRgm() %>"></td>
                </tr>
                <tr>
                    <td>Nome:</td>
                    <td><input type="text" name="nome" value="<%= aluno.getNome() %>"></td>
                </tr>
                <tr>
                    <td>Nota 1:</td>
                    <td><input type="text" name="nota1" value="<%= aluno.getNota1() %>"></td>
                </tr>
                <tr>
                    <td>Nota 2:</td>
                    <td><input type="text" name="nota2" value="<%= aluno.getNota2() %>"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Gravar"></td>
                </tr>
            </table>
        </form>
        <br>
        <table border="1">
            <tr>
                <td>Nome</td>
                <td>RGM</td>
                <td>Nota 1</td>
                <td>Nota 2</td>
            </tr>
        <%
            ArrayList<Aluno> lista = new AlunoDAO().getAlunos();
            for (int i = 0; i < lista.size(); i++) {
                  out.println("<tr>");
                  out.println("<td><a href='index.jsp?rgm=" + lista.get(i).getRgm() + "'>" + lista.get(i).getRgm() + "</a></td>");
                  out.println("<td>" + lista.get(i).getNome() + "</td>");
                  out.println("<td>" + lista.get(i).getNota1() + "</td>");
                  out.println("<td>" + lista.get(i).getNota2() + "</td>");
                  out.println("</tr>");
                }
        %>
        </table>
    </body>
</html>
