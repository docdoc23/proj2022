
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="bl" scope="session" class="boundary.BoundaryLogin"/>
<jsp:useBean id="bprofilo" scope="session" class="boundary.BoundaryProfilo"/>
<jsp:useBean id="bposta" scope="session" class="boundary.BoundaryPosta"/>
<jsp:useBean id="blingua" scope="session" class="boundary.BoundaryLingua"/>
<%@ page import="entity.Messaggio" %>
<%@ page import="entity.Utente" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>

<%
    Locale locale = blingua.riceviLingua(bl.ritornaUsername());
    ResourceBundle bundle = blingua.riceviBundle(locale);
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
				<h1><%=bundle.getString("visualizzaPosta_msgs")%></h1>
				<h2> <%=bundle.getString("profiloUtente_registratoCome")%> <%=bl.ritornaUsername() %> </h2>
			</div>
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
            <!-- Menu -->
            <ul class="topnav" id="myTopnav">
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
							<h2><strong><%=bundle.getString("visualizzaPosta_msgSelezionato")%></strong>

                                <%
                                    String strCodice = request.getParameter("Cod");
                                    Messaggio messaggio = null;
                                    if (bl.controlloAccesso()) {
                                        if (strCodice != null) {
                                            int codice = Integer.parseInt(strCodice);
                                            messaggio = bposta.ritornaMessaggioCod(codice);
                                        }

                                        if (messaggio == null) {
                                %>

                                            <h4>
                                                <font color="red"> <%=bundle.getString("visualizzaPosta_erroreEliminato")%> </font>
                                            </h4>

                                <%
                                        }
                                    } else {
                                %>

                                        <font color="red"> <%=bundle.getString("visualizzaPosta_riaccediPerVisual")%> </font>

                                <% } %>

                            </h2>
					</div>
					
					<div class="post">
						<table width="100%">
							<tr>
								<td><label>Mittente:</label></td>

                                <%
                                    if (bl.controlloAccesso() && strCodice != null && messaggio != null) {
                                %>

								        <td><%=messaggio.getMittente()%></td>

                                <% } %>

							</tr>

							<tr>
								<td><label>Oggetto:</label></td>

                                <%
                                    if (bl.controlloAccesso() && strCodice != null && messaggio != null) {
                                %>

								        <td><%=messaggio.getOggetto()%></td>

                                <% } %>

							</tr>
                        </table>

                        <br />
						<label>Contenuto:</label><br /><br />

                        <%
                            if (bl.controlloAccesso() && strCodice != null && messaggio != null) {
                        %>

                                <div class="break-word">
                                    <%=messaggio.getContenuto()%>
                                </div>

                        <% } %>

                        <br /><br />

                        <table align="center">
                            <tr>
                                <td>
                                    <form action="visualizzaPosta.jsp" method="post">
                                        <div>
                                            <input class="btn_2" type="submit" value="<%=bundle.getString("visualizzaPosta_indietro")%>" />
                                        </div>
                                    </form>
                                </td>
                                <td>
                                    <form action="scriviMessaggio.jsp?Dest=<%= messaggio == null ? "" : messaggio.getMittente() %>" enctype="application/x-www-form-urlencoded" method="post">
                                        <div>
                                            <input class="btn_2" type="submit" value="<%=bundle.getString("visualizzaPosta_rispondi")%>"/>
                                        </div>
                                    </form>
                                </td>
                                <td>
                                    <%
                                        Utente des = null;
                                        if (messaggio != null) {
                                            des = bprofilo.ritornaUtente(messaggio.getMittente());
                                        }
                                    %>
                                    <form action="mailto:<%= des == null ? "" : des.getEmail() %>?subject=<%= messaggio == null ? "" : messaggio.getOggetto() %>" enctype="application/x-www-form-urlencoded" method="post">
                                        <div>
                                            <input style="width: 300px" class="btn_2" type="submit" value="<%=bundle.getString("visualizzaPosta_rispondiClientExt")%>" />
                                        </div>
                                    </form>
                                </td>
                            </tr>
                        </table>
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
