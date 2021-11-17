<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC >
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
<jsp:useBean id="bl" scope="session" class="boundary.BoundaryLogin"/>
<%@ page import="entity.Prenotazione" %>
<%@ page import="exception.DeserializzazioneException" %>
<%@ page import="exception.SerializzazioneException" %>
<%@ page import="java.util.ArrayList" %>
<jsp:useBean id="bp" scope="page" class="boundary.BoundaryGestionePrenotazioni"/>
<%



    String id = request.getParameter("id");
    id = id.substring(0,1);
    System.out.println("id  " + id);


    ArrayList<Prenotazione> elencoPrenotazione = (ArrayList<Prenotazione>) request.getSession().getAttribute("pre");
    System.out.println("size elenco abstractFactoryPrenotazione "+ elencoPrenotazione.size());
    try {
        bp.avvioRimozione(elencoPrenotazione.get(Integer.parseInt(id)));
    } catch (DeserializzazioneException | SerializzazioneException e) {
        e.printStackTrace();
    }

    request.getSession().removeAttribute("pre");

%>

<body>

<div id="menu-wrapper">
    <div id="menu">

    </div>
    <!-- end #menu -->
</div>

<div id="wrapper">
    <div id="header-wrapper">
        <div id="header">
            <div id="logo">
                <h1><a href="#">Rimozione Locazioni</a></h1>

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

            <div id="page-bgbtm">
                <div id="content">


                    <div class="post">
                        <h2><strong>La tua prenotazione è stata rimossa con successo</strong></h2>
                        <h3><a style="color: #4b7091" href="utente.jsp">Torna alla home</a></h3>


                    </div>

                    <div style="clear: both;">&nbsp;</div>
                </div>
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
