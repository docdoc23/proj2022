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
<jsp:useBean id="bgp" scope="page" class="boundary.BoundaryGestionePrenotazioni"/>
<%@ page import="entity.Locazione" %>
<%@ page import="exception.DeserializzazioneException" %>
<%@ page import="exception.SerializzazioneException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%


String dataInizio = request.getParameter("dataInizio");
String dataFine = request.getParameter("dataFine");
String id = request.getParameter("id");
String numeroPersone = request.getParameter("prenota per");
id = id.substring(0,1);
boolean controllo = false;

	List<Locazione> elencoLocazioni= (ArrayList<Locazione>) request.getSession().getAttribute("loc");
	try {
		controllo= bgp.effettuaPrenotazione(elencoLocazioni.get(Integer.parseInt(id)), bl.ritornaUsername(),dataInizio,dataFine,numeroPersone);
	} catch (DeserializzazioneException | SerializzazioneException e) {
		e.printStackTrace();
	}
	request.getSession().removeAttribute("loc");

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
				<h1><a href="#">Prenotazione</a></h1>
				
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
				
					
					<% if(controllo){ %>
				
					<div class="post">
						
						<h1>Prenotazione effettuata con successo</h1>
						<br><br><br>
						<h3><a style="color: #4b7091" href="visualizzaPrenotazioniPerViaggiatore.jsp">Visualizza le tue prenotazioni </a> oppure <a style="color: #4b7091" href="_it_utente.jsp">torna alla home</a></h3>

						
					</div>

					<%} else { %>
						
						<div class="post">
						<h1><b style="color: darkred;">Prenotazione fallita:</b>Non &egrave; pi&ugrave; possibile prenotare nella data selezionata</h1>
							<br><br><br>
						<h3><a style="color: #4b7091" href="ricercaLocazione.jsp">Torna alla pagina di ricerca</a></h3>
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