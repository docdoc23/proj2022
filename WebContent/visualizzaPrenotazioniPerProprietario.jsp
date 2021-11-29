<%@ page import="entity.Prenotazione" %>
<%@ page import="exception.DeserializzazioneException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>Perfect Places</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>
    <link href="http://127.0.0.1:8887/css/style.css" rel="stylesheet" type="text/css" media="screen" />
    <script type="text/javascript" src="http://127.0.0.1:8887/js/functions.js" ></script>
</head>
<body>
<jsp:useBean id="bl" scope="session" class="boundary.BoundaryLogin"/>
<jsp:useBean id="bvp" scope="page" class="boundary.BoundaryGestionePrenotazioni"/>

<%
    List<Prenotazione> elencoPrenotazione =new ArrayList<>();
    try {
        elencoPrenotazione = bvp.visualizzaPerProprietario(bl.ritornaUsername());
    } catch (DeserializzazioneException e) {
        e.printStackTrace();
    }

    String nomeLocazione;
    String nomeCliente;
    GregorianCalendar dataInizio;
    GregorianCalendar dataFine;
    int numeroPersone;
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
                <h1><a href="#">Prenotazioni</a></h1>

            </div>
        </div>
    </div>
    <!-- end #header -->
    <div id="page">
        <div id="page-bgtop">
            <ul class="topnav" id="myTopnav">
                <li><a href="utente.jsp">HOME</a></li>
                <li><a href="areaViaggiatore.jsp">Area Viaggiatore</a></li>
                <li><a href="areaProprietario.jsp">Area Proprietario</a></li>
                <li><a href="profiloUtente.jsp">Visualizza profilo</a></li>
                <li><a href="posta.jsp">Posta</a></li>
                <li><a href="areaAiuto.jsp">AIUTO</a></li>
                <li><a href="logout.jsp">Esci</a></li>
            </ul>
            <div>
                <%
                    if (!bl.controlloAccesso()) {
                %>

                <p style="font-size: 30px; color: red"> Errore! Sessione scaduta. Accedi di nuovo per continuare. </p>

                <%
                    }
                %>

            </div>
        </div>
        <div id="page-bgbtm">
            <div id="content">
                <h2><strong>Le tue prenotazioni</strong></h2>
                <%
                    if(elencoPrenotazione.size()==0){
                %>
                <p style="font-size: 30px; color: darkred"> Non hai ancora prenotazioni</p>
                <h3><a style="color: #4b7091" href="areaProprietario.jsp">Torna alla area proprietario</a></h3>


                <%
                    }
                %>
                <%
                    for(int i = 0; i< elencoPrenotazione.size(); i++){
                        nomeLocazione = elencoPrenotazione.get(i).getNomeLocazione();
                        nomeCliente = elencoPrenotazione.get(i).getCliente();
                        dataInizio = elencoPrenotazione.get(i).getDataInizio();
                        dataFine = elencoPrenotazione.get(i).getDataFine();
                        int giornoInizio = dataInizio.get(Calendar.DATE);
                        int meseInizio = dataInizio.get(Calendar.MONTH) + 1;
                        int annoInizio = dataInizio.get(Calendar.YEAR);
                        String dal = giornoInizio + "/" + meseInizio +"/"+ annoInizio;
                        int giornoFine = dataFine.get(Calendar.DATE);
                        int meseFine = dataFine.get(Calendar.MONTH) +1;
                        int annoFine = dataFine.get(Calendar.YEAR);
                        String al = giornoFine + "/" + meseFine +"/"+ annoFine;
                        numeroPersone = Integer.parseInt(elencoPrenotazione.get(i).getNumeroPersone());


                %>
                <div class="post">
                    <%
                        if(elencoPrenotazione.get(i).getTipo().equals("Albergo")){
                    %>
                    <img src="http://127.0.0.1:8887/css/images/albergo.png"  style="width:50px;height:50px; float: left">
                    <span class="span">Prenotazione effettuata per: <%out.println(nomeLocazione);%></span>
                    <%}%>
                    <%
                        if(elencoPrenotazione.get(i).getTipo().equals("Appartamento")){
                    %>
                    <img src="http://127.0.0.1:8887/css/images/appartamento.png"  style="width:50px;height:50px;float: left">
                    <span class="span">Prenotazione effettuata per: <%out.println(nomeLocazione);%></span>
                    <%}%>
                    <%
                        if(elencoPrenotazione.get(i).getTipo().equals("Beb")){
                    %>
                    <img src="http://127.0.0.1:8887/css/images/beb.jpeg"  style="width:50px;height:50px;float: left;">
                    <span class="span">Prenotazione effettuata per: <%out.println(nomeLocazione);%></span>

                    <%}%>
                    <%
                        if(elencoPrenotazione.get(i).getTipo().equals("CasaVacanza")){
                    %>
                    <img src="http://127.0.0.1:8887/css/images/casaVacanza.png"  style="width:50px;height:50px;float: left">
                    <span class="span">Prenotazione effettuata per: <%out.println(nomeLocazione);%></span>

                    <%}%>
                    <%
                        if(elencoPrenotazione.get(i).getTipo().equals("Ostello")){
                    %>
                    <img src="http://127.0.0.1:8887/css/images/ostello.jpeg"  style="width:50px;height:50px;float: left">
                    <span class="span">Prenotazione effettuata per: <%out.println(nomeLocazione);%></span>
                    <%}%>
                    <table style="width:100%">
                        <tr>
                            <td><b>Dal cliente: </b> <%out.println(nomeCliente);%></td>
                            <td><b>dal: </b> <%out.println(dal);%></td>
                            <td><b>al: </b> <%out.println(al);%></td>
                            <%
                                if(elencoPrenotazione.get(i).getTipo().equals("Albergo") || elencoPrenotazione.get(i).getTipo().equals("Beb") || elencoPrenotazione.get(i).getTipo().equals("Ostello")){
                            %>
                            <td>Posti prenotati: <%out.println(numeroPersone);%> </td>
                            <%
                                }
                            %>

                        </tr>
                    </table>
                </div>
                <%
                    }
                %>

                <div style="clear: both;">&nbsp;</div>

            </div>
            <div style="clear: both;">&nbsp;</div>
        </div>
    </div>
    <div id="footer">
    </div>

</div>
</body>
</html>

