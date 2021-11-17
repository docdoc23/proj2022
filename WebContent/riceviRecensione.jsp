<%@ page import="exception.DeserializzazioneException" %>
<%@ page import="exception.SerializzazioneException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>Perfect Places</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <link href="../css/style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<jsp:useBean id="bl" scope="session" class="boundary.BoundaryLogin"/>
<jsp:useBean id="bgr" scope="page" class="boundary.BoundaryGestioneRecesioni"/>

<%
    String nomeLocazione = request.getParameter("nomeLocazione");
    String tipoLocazione = request.getParameter("tipoLocazione");
    String nomeRecensore = bl.ritornaUsername();
    String numeroStelle =  request.getParameter("numeroStelle");
    String testoRecensione = request.getParameter("testoRecensione");

    int controllo=10;

    try {
        controllo = bgr.inserisciRecensione(nomeLocazione,tipoLocazione,nomeRecensore,numeroStelle,testoRecensione);
    } catch (SerializzazioneException | DeserializzazioneException e) {
        e.printStackTrace();
    }


%>
<div id="menu-wrapper">
    <div id="menu">

    </div>
    <!-- end #menu -->
</div>

<div id="wrapper">
    <div id="header-wrapper">
        <div id="header">
            <div id="logo">
                <h1><a href="#">Recensione</a></h1>

            </div>
        </div>
    </div>
    <!-- end #header -->
    <div id="page">
        <div id="page-bgtop">
            <ul class="topnav" id=myTopnav">
                <li><a href="utente.jsp">HOME</a></li>
                <li><a href="areaViaggiatore.jsp">Area Viaggiatore</a></li>
                <li><a href="areaProprietario.jsp">Area Proprietario</a></li>
                <li><a href="profiloUtente.jsp">Visualizza profilo</a></li>
                <li><a href="posta.jsp">Posta</a></li>
                <li><a href="areaAiuto.jsp">AIUTO</a></li>
                <li><a href="logout.jsp">Esci</a></li>
            </ul>
            <div >
                <%
                    if (!bl.controlloAccesso()) {
                %>

                <p style="font-size: 30px; color: red" > Errore! Sessione scaduta. Accedi di nuovo per continuare. </p>

                <%
                    }
                %>
            </div>
            <div id="page-bgbtm">
                <div id="content">


                    <% if(controllo==0){ %>

                    <div class="post">

                        <h1>Recensione inviata con successo</h1>
                        <br><br><br>
                        <h3><a style="color: #4b7091" href="inserisciRecensione.jsp"> Recensisci un 'altra locazione </a> oppure <a style="color: #4b7091" href="areaViaggiatore.jsp">torna all'area viaggiaore</a></h3>


                    </div>

                    <%} else { %>

                    <div class="post">
                        <h1><b style="color: darkred;">Recensione fallita:</b></h1>
                        <br>
                        <%if(controllo==1){%>
                                <h3>Non hai comletato il campo nome locazione</h3>
                        <%}%>
                        <%if(controllo==2){%>
                            <h3>Non esiste ancora nel sistema una locazione con il nome indicato</h3>
                        <%}%>
                        <%if(controllo==3){%>
                            <h3>Non è possibile recensione una locazione se prima non è stata effetuata una prenotazione</h3>
                        <%}%>
                        <%if(controllo==4){%>
                            <h3>Il testo della recensione deve essere composto da almeno 10 caratteri</h3>
                        <%}%>
                        <%if(controllo==5){%>
                            <h3>Il testo della recensione non può essere composto da più di 500 caratteri</h3>
                        <%}%>
                        <br><br><br>
                        <h3><a style="color: #4b7091" href="inserisciRecensione.jsp">Torna alla pagina di inserimento recensione</a></h3>
                    </div>

                    <% } %>






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
