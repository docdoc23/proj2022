
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="bl" scope="session" class="boundary.BoundaryLogin"/>
<jsp:useBean id="bposta" scope="session" class="boundary.BoundaryPosta"/>
<jsp:useBean id="blingua" scope="session" class="boundary.BoundaryLingua"/>


<%
    Locale locale = blingua
            .riceviLingua(bl.ritornaUsername());
    ResourceBundle bundle = blingua
            .riceviBundle(locale);

    String param = request.getParameter("Msg");
    String dest = request.getParameter("Dest");
    String mittente = bl.ritornaUsername();
    String destinatario = dest == null ? "" : dest;
    String oggetto = "";
    String contenuto = "";
    int controllo = -1;
    if (param != null && param.equals("1")) {
        oggetto = request.getParameter("oggetto");
        destinatario = request.getParameter("destinatario");
        contenuto = request.getParameter("contenuto");

        controllo = bposta.inviaMessaggio(oggetto,
                mittente,
                destinatario,
                contenuto
        );
    }
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
</style>

<body>
<div id="wrapper">

	<div id="header-wrapper">
		<div id="header">
			<div id="logo">
				<h1><%=bundle.getString("posta_scrivi")%></h1>
				<h2> <%=bundle.getString("profiloUtente_registratoCome")%> <%=bl.ritornaUsername() %> </h2>
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
                        <h2>
                            <strong> <%=bundle.getString("scriviMessaggio_msg")%> </strong>

                            <%
                                switch (controllo) {
                                    case 0:
                            %>

                                        <font color="green"> <%=bundle.getString("scriviMessaggio_msgInviato")%> </font>

                            <%
                                        break;

                                    case 1:
                            %>

                                        <font color="red"> <%=bundle.getString("scriviMessaggio_erroreDest")%> </font>

                            <%
                                        break;

                                    case 2:
                            %>

                                        <font color="red"> <%=bundle.getString("scriviMessaggio_erroreObj")%> </font>

                            <%
                                        break;

                                    case 3:
                            %>

                                        <font color="red"> <%=bundle.getString("scriviMessaggio_erroreCont")%> </font>

                            <%
                                        break;

                                    case 4:
                            %>

                                        <font color="red"> <%=bundle.getString("scriviMessaggio_erroreDestNonValido")%> </font>

                            <%
                                        break;

                                    case 5:
                            %>

                                        <font color="red"> <%=bundle.getString("modificaProfiloUtente_sessioneScaduta")%> </font>

                            <%
                                        break;

                                    case 6:
                            %>

                                        <font color="red"> <%=bundle.getString("scriviMessaggio_erroreMitDest")%> </font>

                            <%
                                        break;
                                }
                            %>

                        </h2>
                    </div>
					
					<div class="post">
						<form action="scriviMessaggio.jsp?Msg=1" enctype="application/x-www-form-urlencoded" method="post">
							<table width="100%">
                                <tr>
                                    <td>
                                        <label>
                                            <%=bundle.getString("scriviMessaggio_mitt")%> <font color="#1268b3"> <%=mittente%> </font>
                                        </label>
                                    </td>
                                </tr>

								<tr>
                                    <td>
                                        <label for="destinatario"><%=bundle.getString("scriviMessaggio_dest")%>:</label>
                                    </td>
								</tr>
								<tr>
									<td>
                                        <input id="destinatario" type="text" placeholder="<%=bundle.getString("scriviMessaggio_dest")%>" name="destinatario" class="btn"

                                               <%
                                                   if (destinatario != null) {
                                               %>

                                                        value="<%= destinatario %>"

                                               <% } %>

                                        />
                                    </td>
								</tr>

								<tr>
									<td>
                                        <label for="oggetto"><%=bundle.getString("scriviMessaggio_obj")%>:</label>
                                    </td>
								</tr>
								<tr>	
									<td>
                                        <input id="oggetto" type="text" placeholder="<%=bundle.getString("scriviMessaggio_obj")%>" name="oggetto" class="btn"

                                        <%
                                            if (param != null && oggetto != null) {
                                        %>
                                                value="<%= oggetto %>"

                                                <% } %>

                                        />
                                    </td>
								</tr>

								<tr>
								    <td>
                                        <label for="contenuto"><%=bundle.getString("scriviMessaggio_cont")%>:</label>
                                    </td>
								</tr>
								<tr>
									<td>
                                        <textarea id="contenuto" name="contenuto" placeholder="<%=bundle.getString("scriviMessaggio_cont")%>" class="btn" rows="10" cols="100%"><%
                                                if (param != null && contenuto != null) {
                                                    out.println(contenuto);
                                                }
                                            %></textarea>
                                    </td>
								</tr>

								<tr>
									<center>
									    <td style="text-align: center">
									        <input class="btn_2" type="submit" value="<%=bundle.getString("scriviMessaggio_invia")%>" />
									    </td>
                                    </center>
								</tr>
							</table>
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

    <div id="footer"></div>
    <!-- end #footer -->

</body>
</html>
