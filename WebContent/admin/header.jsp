<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:lang="pt-br" lang="pt-br">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="robots" content="nofollow" />
	<meta name="googlebot" content="nofollow" />
	<meta name="description" content="" />
	<meta http-equiv="content-language" content="pt-br" />

<link href="<c:url value="/admin/css/AdmStyle.css" />" type="text/css" rel="stylesheet" />
<link href="<c:url value="/admin/css/flexigrid.pack.css" />" type="text/css" rel="stylesheet" />
<link href="<c:url value="/admin/css/jquery-ui-1.8.20.custom.css" />" type="text/css" rel="stylesheet" />

<title>Administracao</title>
</head>
<body>

	<header class="back_header_footer">

        <section>

            <div class="seta_header_footer"></div>

            <a href="<c:url value="/adm"/>"><img id="logo_umake" src="<c:url value="/admin/css/images/logo_umake.png" />" width="205" height="120"/></a>

            <div id="bem_vindo">
                <label>Bem vindo, ${admUserControl.admUser.name}</label>
                <a href="<c:url value="/adm/user/logout" />" id="logout"></a>
            </div>


        </section>

	</header>

    <section>

        <nav>

    		<ul>

    			<li><a href="<c:url value="/" />">Ir para o site</a></li>
    			<li id="usuario">
                    <a href="javascript:void(0)">USUÁRIO</a>
                    <div class="subMenu">
                        <ul>
                            <li><a href="<c:url value="/adm/user/create" />">Cadastrar Usuário</a></li>
                            <li><a href="<c:url value="/adm/user" />">Listar Usuários</a></li>
                        </ul>
                    </div>
                </li>
    			<li id="grupo">
                    <a href="javascript:void(0)">GRUPO</a>
                    <div class="subMenu">
                        <ul>
                            <li><a href="<c:url value="/adm/group/create" />">Cadastrar Grupo</a></li>
                            <li><a href="<c:url value="/adm/group" />">Listar Grupos</a></li>
                        </ul>
                    </div>
                </li>
    			<li id="permissao">
                    <a href="javascript:void(0)">PERMISSÃO</a>
                    <div class="subMenu">
                        <ul>
                            <li><a href="<c:url value="/adm/permission/create" />">Cadastrar Permissão</a></li>
                            <li><a href="<c:url value="/adm/permission" />">Listar Permissões</a></li>
                        </ul>
                    </div>
                </li>
    			<li id="pagina">
                    <a href="javascript:void(0)">PÁGINA</a>
                    <div class="subMenu">
                        <ul>
                            <li><a href="<c:url value="/adm/page/create" />">Cadastrar Página</a></li>
                            <li><a href="<c:url value="/adm/page" />">Listar páginas</a></li>
                        </ul>
                    </div>
                </li>
    			<li id="configuracao"><a href="<c:url value="/adm/settings" />">Configuração</a></li>

    		</ul>

	    </nav>
