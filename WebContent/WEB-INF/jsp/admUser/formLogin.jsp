<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:lang="pt-br" lang="pt-br">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="robots" content="nofollow" />
	<meta name="googlebot" content="nofollow" />
	<meta name="description" content="" />
	<meta http-equiv="content-language" content="pt-br" />
    <link href="../../admin/css/AdmStyle.css" rel="stylesheet" />
<title>Administracao</title>
</head>

<body>
	<c:forEach var="error" items="${errors}">
	  ${error.message}<br />
	</c:forEach>
	<section id="backLogin">
        <div id="orderLogin">
            <div id="divLogin">
                <div id="tituloLogin"></div>
                <p>Digite o nome de usuário e senha para logar-se na área de administração do seu site.</p>
        		<form method="post" action="<c:url value="/adm/user/login" />" id="formLogin">
                    <table>
                        <tr>
                            <td><label for="login">Login: </label></td>
                            <td><input name="admUser.login" id="login" required autofocus /></td>
                        </tr>
                        <tr>
                            <td><label for="pwd">Password: </label></td>
                            <td><input type="password" name="admUser.password" id="pwd" required /></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="submit" class="botoes" value=""/></td>
                        </tr>

                    </table>
                </form>
            </div>
        </div>
	</section>
</body>
</html>