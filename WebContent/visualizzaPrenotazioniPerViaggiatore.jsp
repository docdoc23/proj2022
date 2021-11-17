<%@ page import="entity.Prenotazione" %>
<%@ page import="exception.DeserializzazioneException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
    <link href="../css/style.css" rel="stylesheet" type="text/css" media="screen" />
    <script type="text/javascript" src="../js/functions.js" ></script>
</head>
<body>
<jsp:useBean id="bl" scope="session" class="boundary.BoundaryLogin"/>
<jsp:useBean id="bvp" scope="page" class="boundary.BoundaryGestionePrenotazioni"/>

<%
    List<Prenotazione> elencoPrenotazione =new ArrayList<>();
    try {
        elencoPrenotazione = bvp.visualizzaPerViaggiatore(bl.ritornaUsername());
    } catch (DeserializzazioneException e) {
        e.printStackTrace();
    }

    request.getSession().setAttribute("pre", elencoPrenotazione);


    String nomeLocazione;
    String nomeProprietario;
    GregorianCalendar dataInizio;
    GregorianCalendar dataFine;
    int prezzo;
    int numeroPersone;
    int numeroPrenotazioni = elencoPrenotazione.size();
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
            <ul class="topnav" id=myTopnav">
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
                                 <p style="font-size: 30px; color: darkred"> Non hai ancora effettuato nessuna prenotazione </p>
                                 <h3><a style="color: #4b7091" href="areaViaggiatore.jsp">Torna all'area viaggiatore</a></h3>

                       <%
                           }else{
                       %>
                                 <p id="<% out.println(numeroPrenotazioni);%>" class="cliccaQui" onclick="visualizzaRimozione(this.id)"  >Clicca qui se vuoi rimuovere una prenotazione  </p>

                                <%
                               for(int i = 0; i< elencoPrenotazione.size(); i++){
                                   nomeLocazione = elencoPrenotazione.get(i).getNomeLocazione();
                                   nomeProprietario = elencoPrenotazione.get(i).getProprietario();
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
                                   prezzo = Integer.parseInt(elencoPrenotazione.get(i).getPrezzo());
                                   numeroPersone = Integer.parseInt(elencoPrenotazione.get(i).getNumeroPersone());



                                %>
                    <div class="post">
                                    <%
                                        if(elencoPrenotazione.get(i).getTipo().equals("Albergo")){
                                    %>
                                    <img src="../css/images/albergo.png"  style="width:50px;height:50px; float: left">
                                    <span class="span">Prenotazione effettuata per: <%out.println(nomeLocazione);%></span>
                                    <%}%>
                                    <%
                                        if(elencoPrenotazione.get(i).getTipo().equals("Appartamento")){
                                    %>
                                    <img src="../css/images/appartamento.png"  style="width:50px;height:50px;float: left">
                                    <span class="span">Prenotazione effettuata per: <%out.println(nomeLocazione);%></span>
                                    <%}%>
                                    <%
                                        if(elencoPrenotazione.get(i).getTipo().equals("Beb")){
                                    %>
                                    <img src="../css/images/beb.jpeg"  style="width:50px;height:50px;float: left;">
                                    <span class="span">Prenotazione effettuata per: <%out.println(nomeLocazione);%></span>

                                    <%}%>
                                    <%
                                        if(elencoPrenotazione.get(i).getTipo().equals("CasaVacanza")){
                                    %>
                                    <img src="../css/images/casaVacanza.png"  style="width:50px;height:50px;float: left">
                                    <span class="span">Prenotazione effettuata per: <%out.println(nomeLocazione);%></span>

                                    <%}%>
                                    <%
                                        if(elencoPrenotazione.get(i).getTipo().equals("Ostello")){
                                    %>
                                    <img src="../css/images/ostello.jpeg"  style="width:50px;height:50px;float: left">
                                    <span class="span">Prenotazione effettuata per: <%out.println(nomeLocazione);%></span>
                                    <%}%>
                                    <table style="width:100%">
                                        <tr>
                                            <td><b>dal: </b> <%out.println(dal);%></td>
                                            <td><b>al: </b> <%out.println(al);%></td>
                                            <td align="right">
                                                <form method="get" action="rimuoviPrenotazioni.jsp" enctype="text/plain">
                                                    <input type="hidden" name="id" value="<%out.println(i);%>">
                                                    <input  class="btnRimozione" type="image" src="../css/images/delete.jpg" style="display: none; width: 80px; height: 50px" value="rimuovi">
                                                </form>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><b>Proprietario: </b> <%out.println(nomeProprietario);%></td>
                                            <td><b>Prezzo per una notte: </b><%out.println(prezzo);%> </td>
                                        </tr>
                                        <tr>
                                            <%
                                                if(elencoPrenotazione.get(i).getTipo().equals("Albergo") || elencoPrenotazione.get(i).getTipo().equals("Beb") || elencoPrenotazione.get(i).getTipo().equals("Ostello")){
                                            %>
                                                <td><b>Posti prenotati: </b> <%out.println(numeroPersone);%> </td>
                                            <%
                                                }
                                            %>

                                        </tr>
                                    </table>
                            </div>
                           <%
                               }
                           %>
                    <p>*se non trovi una prenotazione effettuata in precedenza vuol dire che la locazione è stata rimossa</p>
                <%}%>

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

