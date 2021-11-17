
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="bl" scope="session" class="boundary.BoundaryLogin"/>
<jsp:useBean id="blingua" scope="session" class="boundary.BoundaryLingua"/>
<%@page import="boundary.BoundaryPosta" %>
<%@ page import="entity.Messaggio" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Perfect Places</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <link href="../css/style.css" rel="stylesheet" type="text/css" media="screen"/>
</head>

<%
    Locale locale = blingua
            .riceviLingua(bl.ritornaUsername());
    ResourceBundle bundle = blingua
            .riceviBundle(locale);

    String username = bl.ritornaUsername();
    boolean loggedin = bl.controlloAccesso();
    int eliminazione = -1;
    BoundaryPosta boundaryPosta = new BoundaryPosta();
    List<Messaggio> elencoMessaggiUser;

    String codeMsg = request.getParameter("Cod");
    // Per maggiore robustezza
    String codeDel = request.getParameter("Del");
    if (codeMsg != null && codeDel != null) {
        if (bl.controlloAccesso() && Integer.parseInt(codeDel) == 1) {
            int intMsg = Integer.parseInt(codeMsg);

            eliminazione = boundaryPosta.cancellaMessaggio(intMsg);
        }
    }

    elencoMessaggiUser = boundaryPosta.ritornaMessaggi(username);

%>

<body>
<div id="wrapper">
    <div id="header-wrapper">
        <div id="header">
            <div id="logo">
                <h1><%=bundle.getString("visualizzaPosta_visualizzaMsg")%></h1>
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
                        <h2><strong> <%=bundle.getString("visualizzaPosta_postaEntrata")%> </strong></h2>

                        <%  if (eliminazione != -1) {
                                if (eliminazione == 0) { %>

                                <h5>
                                    <font color="red"> <%=bundle.getString("visualizzaPosta_erroreEliminazione")%> </font>
                                </h5>

                            <% } else if (eliminazione == 1){ %>

                                <h4>
                                    <font color="green"> <%=bundle.getString("visualizzaPosta_eliminazioneSuccesso")%> </font>
                                </h4>

                        <% }
                        }
                        %>

                    </div>

                    <div class="post">
                        <table width="100%">
                            <tr>
                                <td><label><%=bundle.getString("scriviMessaggio_mitt")%></label></td>
                                <td><label><%=bundle.getString("scriviMessaggio_obj")%></label></td>
                                <td><label><%=bundle.getString("visualizzaPosta_data")%></label></td>
                                <td><label><%=bundle.getString("scriviMessaggio_cont")%></label></td>
                            </tr>

                            <%

                                if (loggedin) {
                                    if (elencoMessaggiUser != null && elencoMessaggiUser.size() != 0) {
                                        for (Messaggio anElencoMessaggiUser : elencoMessaggiUser) {

                            %>

                                            <tr>
                                                <td>
                                                    <%=anElencoMessaggiUser.getMittente()%>
                                                </td>
                                                <td>
                                                    <%=anElencoMessaggiUser.getOggetto()%>
                                                </td>
                                                <td>
                                                    <%=anElencoMessaggiUser.getData()%>
                                                </td>
                                                <td>
                                                    <form action="visualizzaPosta2.jsp?Cod=<%= String.valueOf(anElencoMessaggiUser.getCodice()) %>" enctype="application/x-www-form-urlencoded" method="post">
                                                        <div>
                                                            <input class="btn" type="submit" value="<%=bundle.getString("visualizzaPosta_leggi")%>"/>
                                                        </div>
                                                    </form>
                                                </td>

                                                <td>
                                                    <form action="visualizzaPosta.jsp?Cod=<%= String.valueOf(anElencoMessaggiUser.getCodice()) %>&Del=1" enctype="application/x-www-form-urlencoded" method="post">
                                                        <div>
                                                            <input class="btnDel" type="submit" value="<%=bundle.getString("visualizzaPosta_cancella")%>"/>
                                                        </div>
                                                    </form>
                                                </td>
                                            </tr>

                            <%
                                        }
                                    } else {

                                        %> <tr><td><font size="4px"> <%=bundle.getString("visualizzaPosta_noMsg")%> </font></td></tr> <%

                                    }
                                } else {

                                        %> <tr><td><font size="4px" color="red"> <%=bundle.getString("visualizzaPosta_sessioneScaduta")%> </font></td></tr> <%

                                }
                            %>

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
    <div id="footer"></div>
<!-- end #footer -->
</body>
</html>