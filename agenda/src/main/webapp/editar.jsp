<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda de Contatos</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar contato</h1>
	<form action="update" name="frmContato">
		<table>
			<tr>
				<td><input type="text" name="idcon" id="caixa3" readonly="readonly" value="<%out.print(request.getAttribute("idcon"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="name" class="caixa1" value="<%out.print(request.getAttribute("name"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="phone" class="caixa2" value="<%out.print(request.getAttribute("phone"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="email"class="caixa1" value="<%out.print(request.getAttribute("email"));%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="Botao1" onclick="validar()">
	</form>
	<script type="text/javascript" src="scripts/validador.js"></script>
</body>
</html>