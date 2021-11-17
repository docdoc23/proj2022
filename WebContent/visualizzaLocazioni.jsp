
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
<link href="../css/style.css" rel="stylesheet" type="text/css" media="screen" />
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

                    <font style="font-size: 30px; color: red"> Errore! Sessione scaduta. Accedi di nuovo per continuare. </font>

                    <%
                        }
                    %>
            </div>
			<div id="page-bgbtm">
				<div id="content">
					
					<div class="post">
							<h2><strong>Queste sono le tue locazioni</strong></h2>
							
					</div>
					

					<% for(Locazione locazione:locazioni){
						 nomeLocazione = locazione.getNomeLocazione();
						 provincia = locazione.getProvincia();
						 indirizzo = locazione.getIndirizzo();
						 prezzo = locazione.getPrezzo();
						 descrizione = locazione.getDescrizione();
						 
					
					%>

						<%
							if(locazione.getClass()== Albergo.class){
						%>
						<img src="../css/images/albergo.png"  style="width:50px;height:50px; float: left">
						<span class="span">Nome albergo: <%out.println(nomeLocazione);%></span>
						<%}%>
						<%
							if(locazione.getClass()== Appartamento.class){
						%>
						<img src="../css/images/appartamento.png"  style="width:50px;height:50px;float: left">
						<span class="span">Nome appartamento: <%out.println(nomeLocazione);%></span>
						<%}%>
						<%
							if(locazione.getClass()== Beb.class){
						%>
						<img src="../css/images/beb.jpeg"  style="width:50px;height:50px;float: left;">
						<span class="span">Nome b&b: <%out.println(nomeLocazione);%></span>

						<%}%>
						<%
							if(locazione.getClass()== CasaVacanza.class){
						%>
						<img src="../css/images/casaVacanza.png"  style="width:50px;height:50px;float: left">
						<span class="span">Nome casa vacanza: <%out.println(nomeLocazione);%></span>

						<%}%>
						<%
							if(locazione.getClass()==Ostello.class){
						%>
						<img src="../css/images/ostello.jpeg"  style="width:50px;height:50px;float: left">
						<span class="span">Nome ostello: <%out.println(nomeLocazione);%></span>
						<%}%>

					<div class="post">
                        <h1>Provincia: <%out.println(provincia); %></h1>
						<h1>Indirizzo: <%out.println(indirizzo); %></h1>
						<h1>Prezzo Pernottamento: <%out.println(prezzo); %></h1>
						<h1>Descrizione: <%out.println(descrizione); %></h1>
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
