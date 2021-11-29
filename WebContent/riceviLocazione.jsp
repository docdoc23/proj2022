<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
<%@page import="entity.*" %>
<%@page import="exception.DeserializzazioneException" %>
<%@page import="exception.SerializzazioneException" %>
<jsp:useBean id="bl" scope="session" class="boundary.BoundaryLogin"/>
<jsp:useBean id="b" scope="request" class="boundary.BoundaryGestioneLocazioni"/>
<jsp:setProperty name="b" property="nomeLocazione"/>
<jsp:setProperty name="b" property="command"/>
<jsp:setProperty name="b" property="postiTotali"/>
<jsp:setProperty name="b" property="provincia"/>
<jsp:setProperty name="b" property="indirizzo"/>
<jsp:setProperty name="b" property="userLocatore"/>
<jsp:setProperty name="b" property="prezzo"/>
<jsp:setProperty name="b" property="descrizione"/>
<jsp:setProperty name="b" property="wifi"/>
<jsp:setProperty name="b" property="parcheggio"/>
<jsp:setProperty name="b" property="pet"/>
<%

	String username = bl.ritornaUsername();

	
	boolean control=false;

    Locazione locazione= null;
    try {
        locazione = b.chiamaControlloreInserimento();
    } catch (Exception e) {
        e.printStackTrace();
    }
    locazione.setUserLocatore(username);

    switch (b.getCommand()) {
        case "0": {
            // Caso Albergo

            ((Albergo)locazione).setTipoPensione(request.getParameter("tipoPensione"));
            ((Albergo)locazione).setOrarioColazione(request.getParameter("orarioColazione"));
            ((Albergo)locazione).setOrarioPranzo(request.getParameter("orarioPranzo"));
            ((Albergo)locazione).setOrarioCena(request.getParameter("orarioCena"));

            try {
                control = b.verificaInserimento(locazione);
            } catch (SerializzazioneException | DeserializzazioneException e) {
                e.printStackTrace();
            }
            break;
        }
        case "1": {
            //Caso Appartamento

            ((Appartamento)locazione).setNumeroLetti(request.getParameter("numeroLetti"));
            ((Appartamento)locazione).setNumeroStanze(request.getParameter("numeroStanze"));
            ((Appartamento)locazione).setNumeroBagni(request.getParameter("numeroBagni"));
            String giardino = request.getParameter("giardino");
            if (giardino.equals("true")) {
                ((Appartamento)locazione).setGiardino(true);
            }

            locazione.setPostiTotali("1");
            try {
                control = b.verificaInserimento(locazione);
            } catch (SerializzazioneException | DeserializzazioneException e) {
                e.printStackTrace();
            }
            break;
        }
        case "2": {
            // Caso Beb

            ((Beb)locazione).setOrarioColazione(request.getParameter("orarioColazione"));

            try {
                control = b.verificaInserimento(locazione);
            } catch (SerializzazioneException | DeserializzazioneException e) {
                e.printStackTrace();
            }
            break;
        }
        case "3": {
            // Caso Casa Vacanze

            ((CasaVacanza)locazione).setNumeroLetti(request.getParameter("numeroLetti"));
            ((CasaVacanza)locazione).setNumeroCamere(request.getParameter("numeroCamere"));
            ((CasaVacanza)locazione).setNumeroBagni(request.getParameter("numeroBagni"));
            String giardino = request.getParameter("giardino");

            if (giardino.equals("true")) {
                ((CasaVacanza)locazione).setGiardino(true);
            }
            locazione.setPostiTotali("1");
            try {
                control = b.verificaInserimento(locazione);
            } catch (SerializzazioneException | DeserializzazioneException e) {
                e.printStackTrace();
            }
            break;
        }
        case "4": {
            // Caso Ostello
            ((Ostello)locazione).setTipoPensione(request.getParameter("tipoPensione"));

            try {
                control = b.verificaInserimento(locazione);
            } catch (SerializzazioneException | DeserializzazioneException e) {
                e.printStackTrace();
            }
            break;
        }
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
				<h1><a href="#">Conferma Inserimento</a></h1>
				
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
            <div >
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
				
					<% if(control){
                        try {
                            b.registraLocazione(locazione);
                        } catch (SerializzazioneException e) {
                            e.printStackTrace();
                        } catch (DeserializzazioneException e) {
                            e.printStackTrace();
                        }
                    %>

					<div class="post">
							<h2><strong>Inserimento completato</strong></h2>
					</div>

					<% }else{ %>

					<div class="post">
							<h2><strong>Hai lasciato qualche campo vuoto</strong></h2>
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