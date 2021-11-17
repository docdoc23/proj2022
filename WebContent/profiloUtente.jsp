
<jsp:useBean id="bgestioneLocazioni" scope="session" class="boundary.BoundaryGestioneLocazioni" />
<jsp:useBean id="bl" scope="session" class="boundary.BoundaryLogin"/>
<jsp:useBean id="bprofilo" scope="session" class="boundary.BoundaryProfilo"/>
<jsp:useBean id="blingua" scope="session" class="boundary.BoundaryLingua"/>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="constants.Constants"%>
<%@page import="entity.Locazione" %>
<%@page import="entity.Utente" %>
<%@page import="exception.DeserializzazioneException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ResourceBundle" %>

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

<body>

<%
    String username = bl.ritornaUsername();
    Utente u = bprofilo.ritornaUtente(username);

    ResourceBundle bundle = blingua
            .riceviBundle(u.getLingua());

    String indirizzoImmagine = Constants.IMGS_PATH_REL_S + u.getImmagine();
    if (u.getSesso() != null) {
        String format = ".png";
        indirizzoImmagine = indirizzoImmagine.substring(0, indirizzoImmagine.length() - format.length());
        if (u.getSesso().equals("M"))
            indirizzoImmagine = indirizzoImmagine.concat("_M" + format);
        else if (u.getSesso().equals("F"))
            indirizzoImmagine = indirizzoImmagine.concat("_F" + format);
    }


    List<Locazione> locazioni = new ArrayList<>();
    try {
        locazioni = bgestioneLocazioni.chiamaControlloreVisualizza(username);
    } catch (DeserializzazioneException e) {
        e.printStackTrace();
    }
%>

<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<div id="logo">
				<h1><%=bundle.getString("profiloUtente_personale")%></h1>
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
						<h2 class="title"><%=bundle.getString("profiloUtente_tuoProfilo")%></h2>

                        <table width="100%">
                            <tr>
                                <td>
                                    <h2 class="title"><strong><%=bundle.getString("profiloUtente_dati")%></strong></h2>
                                </td>
                                <td>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <p><img src="<%= indirizzoImmagine%>" width="150" height="150"
                                            alt="Immagine Profilo"/></p>
                                </td>
                                <td>
                                    <h3 class="blackclass">
                                        <strong><%=bundle.getString("index_nomeUtente")%></strong><%=" " + u.getUsername()%> <br/>
                                        <strong><%=bundle.getString("index_nome")%></strong><%=" " + u.getNome()%> <br/>
                                        <strong><%=bundle.getString("index_cognome")%></strong><%=" " + u.getCognome()%> <br/>
                                        <strong><%=bundle.getString("index_email")%></strong><%=" " + u.getEmail()%> <br/>

                                        <% if (u.getSesso() == null) { %>
                                            <strong><%=bundle.getString("profiloUtente_sesso")%></strong><%=" " + bundle.getString("profiloUtente_nonPervenuto")%> <br/>
                                        <% } else { %>
                                            <strong><%=bundle.getString("profiloUtente_sesso")%></strong><%=" " + u.getSesso() %> <br/>
                                        <% } %>

                                        <% if (u.getNascita() == null) { %>
                                            <strong><%=bundle.getString("profiloUtente_nascita")%></strong><%=" " + bundle.getString("profiloUtente_nonPervenuto")%> <br/>
                                        <% } else { %>
                                            <strong><%=bundle.getString("profiloUtente_nascita")%></strong><%=" " + u.getNascita() %> <br/>
                                        <% } %>
                                    </h3>
                                </td>
                            </tr>
                        </table>

                        <table width="100%">
                            <tr>
                                <td>
                                    <h1 class="title"><strong><%=bundle.getString("profiloUtente_locazioni")%></strong></h1>
                                    <h3 class="blackclass">

                                        <%
                                            if (locazioni.size() == 0) {
                                                out.println(bundle.getString("profiloUtente_nessunaLocazione"));
                                            } else {
                                                int i;
                                                for (i = 0; i < locazioni.size() - 1; ++i) {
                                                    Locazione loc = locazioni.get(i);
                                                    out.println(loc.getNomeLocazione() + " / ");
                                                }
                                                out.println(locazioni.get(i).getNomeLocazione());
                                            }
                                        %>

                                    </h3>
                                    <br/>
                                    <br/>
                                    <h2>
                                        <strong><a href="modificaProfiloUtente.jsp"><%=bundle.getString("profiloUtente_modificaProfilo")%></a></strong>
                                    </h2>
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