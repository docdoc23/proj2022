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
    <link href="http://127.0.0.1:8887/css/style.css" rel="stylesheet" type="text/css" media="screen" />
    <script type="text/javascript" src="http://127.0.0.1:8887/js/functions.js" ></script>
</head>
<body>
<jsp:useBean id="bl" scope="session" class="boundary.BoundaryLogin"/>
<div id="menu-wrapper">
    <div id="menu">
    </div>
    <!-- end #menu -->
</div>

<div id="wrapper">
    <div id="header-wrapper">
        <div id="header">
            <div id="logo">
                <h1><a href="#">Area Viaggiatore</a></h1>

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
                <div class="post">
                    <h2><strong>Scegli cosa fare</strong></h2>
                 </div>
                    <table width="100%">
                        <tr>
                            <td>
                                <br /><br />
                                <p  class="title">
                                    <strong>
                                        <p style="font-size: 30px">Ricerca Locazione</p>
                                        <a href="ricercaLocazione.jsp" >
                                            <img  class="imgInizio" src="http://127.0.0.1:8887/css/images/iconaLente.png"   width="250" height="250"  alt="ricerca locazione">
                                        </a>
                            </td>
                            <td >
                                <br /><br />
                                <p class="title">
                                    <strong>
                                        <p style="font-size: 30px">Visualizza/Rimuovi Prenotazioni</p>
                                        <a href="visualizzaPrenotazioniPerViaggiatore.jsp" >
                                            <img class="imgInizio" src="http://127.0.0.1:8887/css/images/visualizzaPrenotazioni.jpg" style="margin-left: 90px"  width="250" height="250"  alt="visualizza prenotazioni">
                                        </a>
                            </td>
                            <td >
                                <br /><br />
                                <p class="title">
                                    <strong>
                                <p style="font-size: 30px;margin-left: 30px">Recensisci una locazione </p>
                                <a href="inserisciRecensione.jsp" >
                                    <img class="imgInizio" src="http://127.0.0.1:8887/css/images/iconaInserimento.png" style="margin-left: 50px" width="250" height="250"  alt="inserisci recensioni">
                                </a>
                            </td>
                            <td style="clear: both"></td>
                        </tr>
                        <tr>
                        </tr>
                    </table>
                <div style="clear: both;">&nbsp;</div>
                <div class="post">
                    <p></p>
                </div>
            </div>
            <div style="clear: both;">&nbsp;</div>

        </div>

    </div>

    <div id="footer">
</div>

</div>
</body>
</html>
