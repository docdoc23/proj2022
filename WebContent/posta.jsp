
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="bl" scope="session" class="boundary.BoundaryLogin"/>
<jsp:useBean id="blingua" scope="session" class="boundary.BoundaryLingua"/>


<%
    Locale locale = blingua
            .riceviLingua(bl.ritornaUsername());
    ResourceBundle bundle = blingua
            .riceviBundle(locale);
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
    <link href="http://127.0.0.1:8887/css/style.css" rel="stylesheet" type="text/css" media="screen" />
</head>

<body>
<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<div id="logo">
				<h1> <%=bundle.getString("posta_posta")%> </h1>
				<h2> <%=bundle.getString("profiloUtente_registratoCome") + bl.ritornaUsername() %> </h2>
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
							<h2><strong> <%=bundle.getString("posta_azioni")%> </strong>

                                <%
                                    if (!bl.controlloAccesso()) {
                                %>

                                        <font size="4px" color="red"> <%=bundle.getString("modificaProfiloUtente_sessioneScaduta")%> </font>

                                <%
                                    }
                                %>

                            </h2>
					</div>
					
					<center>
					<div class="post">
					<table width="100%">
						<tr>
                            <td><h1><center><%=bundle.getString("posta_clientEsterno")%></center></h1></td>
							<td><h1><center><%=bundle.getString("posta_scrivi")%></center></h1></td>
							<td><h1><center><%=bundle.getString("posta_leggi")%></center></h1></td>
						</tr>

						<tr>
                            <td>
                                <h2 class="title">
                                    <center>
                                        <!--
                                             Per evitare di trasmettere in chiaro gli indirizzi e-mail degli utenti
                                               si preferisce aprire direttamente il client esterno e comporre ed inviare
                                               il messaggio direttamente da questo
                                        -->
                                        <a href="mailto:">
                                            <img src="http://127.0.0.1:8887/css/images/email_client.png" width="250" height="250" alt="Img apri clien email"/>
                                        </a>
                                    </center>
                                </h2>
                            </td>
							<td>
                                <h2 class="title">
                                    <center>
                                        <a href="scriviMessaggio.jsp">
                                            <img src="http://127.0.0.1:8887/css/images/write_email.png" width="250" height="250" alt="Img scrivi msg"/>
                                        </a>
                                    </center>
                                </h2>
                            </td>
                            <td>
                                <h2 class="title">
                                    <center>
                                        <a href="visualizzaPosta.jsp">
                                            <img src="http://127.0.0.1:8887/css/images/read_email.png" width="250" height="250" alt="Img leggi msg"/>
                                        </a>
                                    </center>
                                </h2>
                            </td>
                        </tr>
						</table>
					</div>
					</center>

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
