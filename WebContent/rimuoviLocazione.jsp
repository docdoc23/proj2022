<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<head>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>Perfect Places</title>
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
	<link href="http://127.0.0.1:8887/css/style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<jsp:useBean id="bl" scope="session" class="boundary.BoundaryLogin"/>
<jsp:useBean id="b" scope="request" class="boundary.BoundaryGestioneLocazioni"/>
<%@page import="entity.*" %>
<%@ page import="exception.DeserializzazioneException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%


String username = bl.ritornaUsername();
List<Locazione> locazioni = new ArrayList<Locazione>();
	try {
		locazioni = b.chiamaControlloreVisualizza(username);
	} catch (DeserializzazioneException e) {
		e.printStackTrace();
	}

	request.getSession().setAttribute("locDaRimuovere", locazioni);

	String nomeLocazione;
String provincia;
String indirizzo;
String prezzo; 
String descrizione;


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
				<h1><a href="#">Visualizza le tue locazioni</a></h1>
				
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

					<div class="post">
							<h2><strong>Queste sono le tue locazioni</strong></h2>
							
					</div>

					<% for(int i=0;i<locazioni.size();i++){
						 nomeLocazione = locazioni.get(i).getNomeLocazione();
						 provincia = locazioni.get(i).getProvincia();
						 indirizzo = locazioni.get(i).getIndirizzo();
						 prezzo = locazioni.get(i).getPrezzo();
						 descrizione = locazioni.get(i).getDescrizione();
					%>
					<div class="post">
						<%

							if(locazioni.get(i).getClass()== Albergo.class){
						%>
						<img src="http://127.0.0.1:8887/css/images/albergo.png"  style="width:50px;height:50px; float: left">
						<span class="span">Nome albergo: <%out.println(nomeLocazione);%></span>
						<%}%>
						<%
							if(locazioni.get(i).getClass()== Appartamento.class){
						%>
						<img src="http://127.0.0.1:8887/css/images/appartamento.png"  style="width:50px;height:50px;float: left">
						<span class="span">Nome appartamento: <%out.println(nomeLocazione);%></span>
						<%}%>
						<%
							if(locazioni.get(i).getClass()== Beb.class){
						%>
						<img src="http://127.0.0.1:8887/css/images/beb.jpeg"  style="width:50px;height:50px;float: left;">
						<span class="span">Nome b&b: <%out.println(nomeLocazione);%></span>

						<%}%>
						<%
							if(locazioni.get(i).getClass()== CasaVacanza.class){
						%>
						<img src="http://127.0.0.1:8887/css/images/casaVacanza.png"  style="width:50px;height:50px;float: left">
						<span class="span">Nome casa vacanza: <%out.println(nomeLocazione);%></span>

						<%}%>
						<%
							if(locazioni.get(i).getClass()== Ostello.class){
						%>
						<img src="http://127.0.0.1:8887/css/images/ostello.jpeg"  style="width:50px;height:50px;float: left">
						<span class="span">Nome ostello: <%out.println(nomeLocazione);%></span>
						<%}%>




						<table style="width:100%">
							<tr>
								<td>
									<h1>Provincia: <%out.println(provincia); %></h1>
									<h1>Indirizzo: <%out.println(indirizzo); %></h1>
									<h1>Prezzo: <%out.println(prezzo); %></h1>
									<h1>Descrizione: <%out.println(descrizione); %></h1>

								</td>
								<td align="right">

										<form method="get" action="rimuoviLocazione2.jsp" enctype="text/plain">
											<input type="hidden" name="id" value="<%out.println(i);%>">
											<input type="image" src="http://127.0.0.1:8887/css/images/delete.jpg" style="width: 80px; height: 50px" value="rimuovi">
										</form>
									<!--<a href="rimuoviLocazione2.jsp ?id=<% //out.println(idNumber); %>">
									<img src="http://127.0.0.1:8887/delete.jpg" width="150" height="100" alt="Press image to remove">
									</a>  -->

								</td>
							</tr>
						</table>
					</div>
					
					<% }
					%>



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