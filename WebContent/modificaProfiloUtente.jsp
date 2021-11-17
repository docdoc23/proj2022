
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="bl" scope="session" class="boundary.BoundaryLogin"/>
<jsp:useBean id="bprofilo" scope="session" class="boundary.BoundaryProfilo"/>
<jsp:useBean id="blingua" scope="session" class="boundary.BoundaryLingua"/>
<%@ page import="constants.Constants" %>
<%@ page import="entity.Utente" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>

<%
    String lang = request.getParameter("lang");

    Locale currentLocale;
    if (lang != null) {
        currentLocale = blingua.riceviLocaleDaString(lang);
    } else {
        lang = Constants.LANG_DEFAULT;
        currentLocale = blingua.riceviLinguaDefault();
    }

    if (bl.controlloAccesso()) {
        String pref = request.getParameter("pref");
        if (pref != null && Integer.parseInt(pref) == 1) {
            currentLocale = blingua
                    .aggiornaPreferenze(bl.ritornaUsername(), lang);
        } else {
            currentLocale = blingua
                    .riceviLingua(bl.ritornaUsername());
        }
    }

    ResourceBundle bundle = blingua
            .riceviBundle(currentLocale);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>Perfect Places</title>
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>
	<link href="../css/style.css" rel="stylesheet" type="text/css" media="screen" />
    <script type="text/javascript" src="../js/functions.js"></script>
</head>

<style>

    input[type=text], select {
        width: 60%;
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }
    input[type=password], select {
        width: 60%;
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }
</style>

<body>
<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<div id="logo">
				<h1><%=bundle.getString("modificaProfiloUtente_modificaProfilo")%></h1>
				<h2> <%=bundle.getString("profiloUtente_registratoCome")%> <%=bl.ritornaUsername()%> </h2>
			</div>
		</div>
	</div>
	<!-- end #header -->

	<div id="page">
		<div id="page-bgtop">
            <!-- Menu -->
            <ul class="topnav" id=myTopnav">
                <li><a href="utente.jsp"><%=bundle.getString("utente_home")%></a></li>
                <li><a href="areaViaggiatore.jsp"><%=bundle.getString("utente_areaViaggiatore")%></a></li>
                <li><a href="areaProprietario.jsp"><%=bundle.getString("utente_areaProprietario")%></a></li>
                <li><a href="profiloUtente.jsp"><%=bundle.getString("utente_profilo")%></a></li>
                <li><a href="posta.jsp"><%=bundle.getString("utente_posta")%></a></li>
                <li><a href="areaAiuto.jsp"><%=bundle.getString("index_aiuto")%></a></li>
                <li><a href="logout.jsp"><%=bundle.getString("utente_esci")%></a></li>
            </ul>

			<div id="page-bgbtm">
				<div id="content">
					<div class="post">
						
						<h2 class="title"> <%=bundle.getString("modificaProfiloUtente_modificaTuoProfilo")%>

                            <%
                                String username = bl.ritornaUsername();
                                Utente u = null;

                                String param = request.getParameter("Mpu");
                                if (!bl.controlloAccesso()) {
                            %>

                                    <font color="red"> <%=bundle.getString("modificaProfiloUtente_sessioneScaduta")%> </font>

                            <%
                                } else {

                                    u = bprofilo.ritornaUtente(username);

                                    if (param != null) {
                                        String nome = request.getParameter("nome");
                                        String cognome = request.getParameter("cognome");
                                        String email = request.getParameter("email");
                                        String sesso = request.getParameter("sesso");
                                        String nascita = request.getParameter("nascita");
                                        String vecchiaPassword = request.getParameter("vecchiaPassword");
                                        String nuovaPassword = request.getParameter("nuovaPassword");
                                        String confermaNuovaPassword = request.getParameter("confermaNuovaPassword");

                                        int controllo = bprofilo.modificaUtente(username, nome, cognome,
                                                    email, sesso, nascita, vecchiaPassword,
                                                    nuovaPassword, confermaNuovaPassword);

                                        u = bprofilo.ritornaUtente(username);

                                        switch (controllo) {
                                            case 0:
                            %>

                                                <font color="green"> <%=bundle.getString("modificaProfiloUtente_modificaCorretta")%> </font>

                            <%
                                                break;

                                            case 1:
                            %>

                                                <font color="red"> <%=bundle.getString("modificaProfiloUtente_vecchiaPswErrore")%> </font>

                            <%
                                                break;

                                            case 2:
                            %>

                                                <font color="red"> <%=bundle.getString("modificaProfiloUtente_nuovaPswConferma")%> </font>

                            <%
                                                break;

                                            case 3:
                            %>

                                                <font color="red"> <%=bundle.getString("modificaProfiloUtente_nuovaPswMancante")%> </font>

                            <%
                                                break;

                                            case 4:
                            %>

                                                <font color="red"> <%=bundle.getString("modificaProfiloUtente_sessioneScaduta")%> </font>

                            <%
                                                break;

                                            case 5:
                            %>

                                                <font color="red"> <%=bundle.getString("modificaProfiloUtente_sessoErrore")%> </font>

                            <%
                                                break;

                                            case 6:
                            %>

                                                <font color="red"> <%=bundle.getString("modificaProfiloUtente_nascitaErrore")%> </font>

                            <%
                                                break;
                                        }
                                    }
                                }
                            %>

                        </h2>
                        <form action="modificaProfiloUtente.jsp?Mpu=1" enctype="application/x-www-form-urlencoded" method="post">
                            <table>
                                <tr>
                                    <td>
                                        <label for="nome"><%=bundle.getString("index_nome")%></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="btn" id="nome" type="text" name="nome" size="30" value="<%=u == null ? "" : u.getNome()%>"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label for="cognome"><%=bundle.getString("index_cognome")%></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="btn" id="cognome" type="text" name="cognome" size="30" value="<%=u == null ? "" : u.getCognome()%>"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label for="email"><%=bundle.getString("index_email")%></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="btn" id="email" type="text" name="email" size="30" value="<%=u == null ? "" : u.getEmail()%>"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label for="sesso"><%=bundle.getString("profiloUtente_sesso")%></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="btn" id="sesso" type="text" placeholder="M  /  F" name="sesso" size="30" value="<%=u == null ? "" : (u.getSesso() == null ? "" : u.getSesso())%>"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label for="nascita"><%=bundle.getString("profiloUtente_nascita")%></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="btn" id="nascita" type="text" placeholder="<%=bundle.getString("modificaProfiloUtente_data")%>" name="nascita" size="30" value="<%=u == null ? "" : (u.getNascita() == null ? "" : u.getNascita())%>"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label for="vecchia_psw"><%=bundle.getString("modificaProfiloUtente_vecchiaPsw")%></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="btn" id="vecchia_psw" type="password" name="vecchiaPassword" size="30" value=""/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label for="nuova_psw"><%=bundle.getString("modificaProfiloUtente_nuovaPsw")%></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="btn" id="nuova_psw" type="password" name="nuovaPassword" size="30" value=""/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label for="conferma_new_psw"><%=bundle.getString("modificaProfiloUtente_confermaNuovaPsw")%></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="btn" id="conferma_new_psw" type="password" name="confermaNuovaPassword" size="30" value=""/>
                                    </td>
                                </tr>

                                <tr class="break"><td colspan="2"></td></tr>

                                <tr>
                                    <td align="center">
                                        <label for="select"><%=bundle.getString("modificaProfiloUtente_linguaCorrente")%> </label>

                                        <%
                                            if (currentLocale.getDisplayLanguage()
                                                    .equals(Locale.ENGLISH.getDisplayLanguage())) {
                                        %>

                                                <select id="select" class="btn" onchange="changeLang(this, null, null, 1)">
                                                    <option value="<%=Constants.EN%>"><%=bundle.getString("index_inglese")%></option>
                                                    <option value="<%=Constants.IT%>"><%=bundle.getString("index_italiano")%></option>
                                                </select>

                                        <%
                                            } else if (currentLocale.getDisplayLanguage()
                                                    .equals(Locale.ITALIAN.getDisplayLanguage())) {
                                        %>

                                                <select id="select" class="btn" onchange="changeLang(this, null, null, 1)">
                                                    <option value="<%=Constants.IT%>"><%=bundle.getString("index_italiano")%></option>
                                                    <option value="<%=Constants.EN%>"><%=bundle.getString("index_inglese")%></option>
                                                </select>

                                        <%
                                            } else {
                                        %>

                                                <select id="select" class="btn" onchange="changeLang(this, null, null, 1)">
                                                    <option value="<%=Constants.LANG_DEFAULT%>"><%=bundle.getString("index_italiano")%></option>
                                                    <option value="<%=Constants.EN%>"><%=bundle.getString("index_inglese")%></option>
                                                </select>

                                        <%
                                            }
                                        %>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <h4>
                                            <%=bundle.getString("modificaProfiloUtente_nota")%><u><a href="utente.jsp"> <%=bundle.getString("utente_home")%> </a></u>
                                        </h4>
                                    </td>
                                </tr>

                            </table>

                            <center>
                                <br/><br/><br/>
                                <input class="btn_2" type="submit" value="<%=bundle.getString("modificaProfiloUtente_salva")%>"/>
                            </center>
                        </form>
					</div>
										
					<div style="clear: both;">&nbsp;</div>
				</div>
				<!-- end #content -->

				<div style="clear: both;">&nbsp;</div>
			</div>
		</div>
	</div>
	<!-- end #page -->
</div>
<div id="footer">
	
</div>
<!-- end #footer -->
</body>
</html>