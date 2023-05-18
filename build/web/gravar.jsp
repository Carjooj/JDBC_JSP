<%-- 
    Document   : gravar
    Created on : 17 de mai. de 2023, 20:15:57
    Author     : carjooj
--%>
<%@page import = "classes.Aluno"%>
<%@page import = "dao.AlunoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //captura os dados do formulário do index
    String rgm = request.getParameter("rgm");
    String nome = request.getParameter("nome");
    String nota1 = request.getParameter("nota1");
    String nota2 = request.getParameter("nota2");
    
    //Monta o objeto aluno
    Aluno aluno = new Aluno();
    aluno.setRgm(rgm);
    aluno.setNome(nome);
    aluno.setNota1(Float.parseFloat(nota1));
    aluno.setNota2(Float.parseFloat(nota2));
    
    //Grava o dado
    String resp = new AlunoDAO().insertAluno(aluno);
    
    //Manda de volta para o index com uma mensagem(sucesso)
    response.sendRedirect("index.jsp?status=" + resp);
%>
