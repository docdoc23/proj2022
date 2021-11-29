<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
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
<style>
	input[type=text], select {
		width: 60%;
		padding: 3px 40px;
		margin: 0px 0;
		display: inline-block;
		border: 1px solid #ccc;
		border-radius: 4px;
		box-sizing: border-box;
	}
</style>
<body>
<jsp:useBean id="bl" scope="session" class="boundary.BoundaryLogin"/>
<jsp:useBean id="brl" scope="page" class="boundary.BoundaryRicercaLocazione"/>
<%@ page import="entity.*" %>
<%@ page import="exception.DeserializzazioneException" %>
<%@ page import="exception.SerializzazioneException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%

String nomeLocazione;
String indirizzo;
String userLocatore;
String descrizione;
String parcheggio="";
String wifi="";
String pet="";
String provincia;
String prezzo ;
String dataInizio ;
String dataFine;
String caratteristica="";
boolean control;


provincia = request.getParameter("provincia");
prezzo = request.getParameter("prezzo");
dataInizio=request.getParameter("dataInizio");
dataFine = request.getParameter("dataFine");
String command = request.getParameter("command");
String commandAvanzata = request.getParameter("commandAvanzata");
int commandInt=10;
int commandavAnzataInt=10;

//necessario per determinare se la ricerca sara normale o avanzata
if(command !=null &&commandAvanzata==null){
	commandInt = Integer.parseInt(command);
	commandavAnzataInt=Integer.parseInt("10");
}
if(commandAvanzata!=null){
	commandInt = Integer.parseInt(commandAvanzata);
	commandavAnzataInt = Integer.parseInt(commandAvanzata);
}

	if(commandavAnzataInt!=10){
		parcheggio = request.getParameter("parcheggio");
		wifi = request.getParameter("wifi");
		pet = request.getParameter("pet");
		if(commandavAnzataInt==0) {
			caratteristica = request.getParameter("tipoPensione");
		}
		else if(commandavAnzataInt==1) {
			caratteristica = request.getParameter("numeroStanze");
		}
		else if(commandavAnzataInt==2) {
			caratteristica  = "caratteristica";
		}
		else if(commandavAnzataInt==3) {
			caratteristica= request.getParameter("numeroCamere");
		}
		else if(commandavAnzataInt==4){
			caratteristica = request.getParameter("tipoPensioneOstello");
		}

	}

Object elencoLocazioni = new Object();
	if(brl.controlloDati(provincia,prezzo,dataInizio,dataFine)){
		try {
			elencoLocazioni=brl.ricerca(commandInt,commandavAnzataInt,provincia,prezzo,parcheggio,wifi,pet,caratteristica,dataInizio,dataFine);
		} catch (DeserializzazioneException | ClassNotFoundException | SerializzazioneException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}
		control=true;
	} else {
		control = false;
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
				<h1><a href="#">Ecco le locazioni disponibili</a></h1>
				
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

				<p style="font-size: 30px; color: red;"> Errore! Sessione scaduta. Accedi di nuovo per continuare. </p>

				<%
					}
				%>

			</div>
			<div id="page-bgbtm">
				<div id="content">
				
				
				<% if(!control){ %>
				
					<div class="post">
						<h2>
						Il prezzo,le date inserite o la provincia non sono accettabili
						</h2>
						<br><br>
						<h4 style="font-size: 100%">Ricorda di completare tutti i campi e di inserire date nel formato gg/mm/aaaa (es. 10/10/2016)</h4>
						<br>
						<h2><a style="color: #4b7091" href="ricercaLocazione.jsp">Torna alla ricerca</a></h2>
					</div>
							
					<% } else {
						if(commandInt==100){
							ArrayList<Locazione> elencoGlobale = (ArrayList<Locazione>) elencoLocazioni;
							if(elencoGlobale.size()==0){ %>
								<p style="font-size: 30px; color: darkred"> Non sono presenti locazioni che soddisfano i parametri richiesti </p>
								<h3><a style="color: #4b7091" href="ricercaLocazione.jsp">Torna alla pagina di ricerca</a></h3>


					<% }%>
							<%
							for(int i=0; i<elencoGlobale.size();i++) {
								nomeLocazione = elencoGlobale.get(i).getNomeLocazione();
								prezzo = elencoGlobale.get(i).getPrezzo();
								provincia = elencoGlobale.get(i).getProvincia();
								indirizzo = elencoGlobale.get(i).getIndirizzo();
								userLocatore = elencoGlobale.get(i).getUserLocatore();
								descrizione = elencoGlobale.get(i).getDescrizione();
								String sParcheggio;
								String sWifi;
								String sPet;

								if (elencoGlobale.get(i).isParcheggio()) {
									sParcheggio = "Disponibile";
								} else {
									sParcheggio = "Non disponibile";
								}
								if (elencoGlobale.get(i).isWifi()) {
									sWifi = "Disponibile";
								} else {
									sWifi = "Non disponibile";
								}
								if (elencoGlobale.get(i).isPet()) {
									sPet = "Disponibile";
								} else {
									sPet = "Non disponibile";
								}
								request.getSession().setAttribute("loc", elencoGlobale);
								%>
					<div class="post">
                        <%
                            if(elencoGlobale.get(i).getClass()==Albergo.class){
                        %>
                        <img src="http://127.0.0.1:8887/css/images/albergo.png"  style="width:50px;height:50px; float: left">
                        <span class="span">Nome albergo: <%out.println(nomeLocazione);%></span>
						<%}%>
                        <%
                            if(elencoGlobale.get(i).getClass()==Appartamento.class){
                        %>
                        <img src="http://127.0.0.1:8887/css/images/appartamento.png"  style="width:50px;height:50px;float: left">
                        <span class="span">Nome appartamento: <%out.println(nomeLocazione);%></span>
                        <%}%>
                        <%
                            if(elencoGlobale.get(i).getClass()==Beb.class){
                        %>
                        <img src="http://127.0.0.1:8887/css/images/beb.jpeg"  style="width:50px;height:50px;float: left;">
                        <span class="span">Nome b&b: <%out.println(nomeLocazione);%></span>

                        <%}%>
                        <%
                            if(elencoGlobale.get(i).getClass()==CasaVacanza.class){
                        %>
                        <img src="http://127.0.0.1:8887/css/images/casaVacanza.png"  style="width:50px;height:50px;float: left">
                        <span class="span">Nome casa vacanza: <%out.println(nomeLocazione);%></span>

                        <%}%>
                        <%
                            if(elencoGlobale.get(i).getClass()==Ostello.class){
                        %>
                        <img src="http://127.0.0.1:8887/css/images/ostello.jpeg"  style="width:50px;height:50px;float: left">
                        <span class="span">Nome ostello: <%out.println(nomeLocazione);%></span>
                        <%}%>
							<br><br><br>
                            <div>
								<form method="get" action="visualizzaPrezzi.jsp" enctype="text/plain">
									<input type="radio" style="display:none" name="SerParcheggio" value="" checked="checked" >
									<input type="radio" style="display:none" name="SerWifi" value="" checked="checked" >
									<input type="radio" style="display:none" name="SerPet" value="" checked="checked" >
									<input type="radio" style="display:none" name="tipoPensione" value="" checked="checked" >
									<table style="width: 100%;">
										<tr>
											<%
												if(elencoGlobale.get(i).getClass()==Albergo.class){
													Albergo albergo = (Albergo) elencoGlobale.get(i);
													if(albergo.getTipoPensione().equals("completa")){%>
											<td><b>Tipo Pensione</b>
												completa<input type="radio" name="tipoPensione" value="completa" />
												mezza pensione<input type="radio" name="tipoPensione" value="mezza pensione" /><br /><br />
											</td>
											<%
													}
												}%>
											<%
												if(elencoGlobale.get(i).getClass()==Ostello.class){
													Ostello ostello = (Ostello) elencoGlobale.get(i);
													if(ostello.getTipoPensione().equals("completa")){%>
											<td><b>Tipo Pensione</b>
												completa<input type="radio" name="tipoPensione" value="completa" />
												mezza pensione<input type="radio" name="tipoPensione" value="mezza pensione" /><br /><br />
											</td>
											<%
													}
												}%>

										</tr>
							<%       if(elencoGlobale.get(i).isParcheggio()){
									%>
									 <tr>
										 <td><b>Servizi disponibili: </b></td>
									 </tr>
                                     <tr>
                                            <td style="padding-right: 30px">
                                                <div >
                                                    <b>Parcheggio</b>
                                                    S&igrave;<input type="radio" name="SerParcheggio" value="true" />
                                                    No<input type="radio" name="SerParcheggio" value="false" /><br /><br />
												</div>
                                            </td>
                                            <%}
											%>
                                            <%
                                                if(elencoGlobale.get(i).isWifi()){

											%>
                                            <td style="padding-right: 30px">
                                                <div >
                                                    <b>Wifi</b>
													S&igrave;<input type="radio" name="SerWifi" value="true"   />
                                                    No<input type="radio" name="SerWifi" value="false" /><br /><br />
                                                </div>
                                            </td>
                                            <%}%>
                                            <%	if(elencoGlobale.get(i).isPet()){
											%>
                                            <td style="padding-right: 30px">
                                                <div >
                                                    <b>Animali ammessi</b>
													S&igrave;<input type="radio" name="SerPet" value="true"  />
                                                    No<input type="radio" name="SerPet" value="false" /><br /><br />

												</div>
                                            </td>
                                            <%}%>

										 	<input type="hidden" name="id" value="<%out.println(i);%>">
										 	<input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
										 	<input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
                                            <td style="float: right"><input type="submit" class="btnBlueborder" value="Info&Dettagli"></td>
									 </tr>
									</table>

								</form>
                            </div>

                        <table style="width:100%">

							<tr>
								<td><b>Username locatore:</b> <%out.println(userLocatore);%></td>
								<td><b>Prezzo Pernottamento:</b> <%out.println(prezzo);%></td>
							</tr>
							<tr>
								<td><b>Provincia:</b> <%out.println(provincia);%></td>
								<td><b>Indirizzo:</b> <%out.println(indirizzo);%></td>

							</tr>
							<tr>
								<td><b>Parcheggio:</b> <%out.println(sParcheggio);%></td>
								<td><b>Wifi:</b> <%out.println(sWifi);%></td>
								<td><b>Animali ammessi:</b> <%out.println(sPet);%></td>
							</tr>
						</table>
						<div class="break-word">
							<p style="font-size: large"><b>Descrizione:</b> <%out.println(descrizione);%></p>
						</div>
						<form method="get" action="prenotaLocazione.jsp" enctype="text/plain">
							<div>
								<input type="hidden" name="id" value="<%out.println(i);%>">
								<input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
								<input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
								<%if(elencoGlobale.get(i).getClass()==Albergo.class || elencoGlobale.get(i).getClass()==Beb.class ||elencoGlobale.get(i).getClass()==Ostello.class ){
							%>
								<div style="float:left; width: 100px"><p style="font-size: 15px"><b>Prenota per: </b></p> </div>

							<div style="float: left;">
								<select style="width: 200px" name="prenota per">
									<option value="1">1 persona</option>
									<option value="2">2 persone</option>
									<option value="3">3 persone</option>
									<option value="4">4 persone</option>
									<option value="5">5 persone</option>
								</select>

								</div>
								<div style="clear: both"></div>
								<br>
								<%}else {%>
									<input type="hidden" name="prenota per" value="1">
								<%}%>
								<input type="submit" class="btnBlue200" value="PRENOTA">

							</div>
						</form>
					</div>
                    <% }%>
					<% }else if(commandInt==0){
								ArrayList<Albergo> elencoAlberghi = (ArrayList<Albergo>) elencoLocazioni;
								if(elencoAlberghi.size()==0){ %>
									<p style="font-size: 30px; color: darkred"> Non sono presenti alberghi che soddisfano i parametri richiesti </p>
									<h3><a style="color: #4b7091" href="ricercaLocazione.jsp">Torna alla pagina di ricerca</a></h3>

					<% }%>
								<%
								for(int i=0;i<elencoAlberghi.size();i++){
									nomeLocazione = elencoAlberghi.get(i).getNomeLocazione();
                                    prezzo = elencoAlberghi.get(i).getPrezzo();
									provincia = elencoAlberghi.get(i).getProvincia();
									indirizzo = elencoAlberghi.get(i).getIndirizzo();
									userLocatore = elencoAlberghi.get(i).getUserLocatore();
									descrizione = elencoAlberghi.get(i).getDescrizione();
									String sParcheggio;
									String sWifi;
									String sPet;
																		
									if(elencoAlberghi.get(i).isParcheggio()){
										sParcheggio = "Disponibile";
									} else {
										sParcheggio = "Non disponibile";
									}
									if(elencoAlberghi.get(i).isWifi()){
										sWifi = "Disponibile";
									} else {
										sWifi = "Non disponibile";
									}
									if(elencoAlberghi.get(i).isPet()){
										sPet = "Disponibile";
									} else {
										sPet = "Non disponibile";
									}
									String tipoPensione = elencoAlberghi.get(i).getTipoPensione();
									String orarioColazione = elencoAlberghi.get(i).getOrarioColazione();
									String orarioPranzo = elencoAlberghi.get(i).getOrarioPranzo();
									String orarioCena = elencoAlberghi.get(i).getOrarioCena();
									request.getSession().setAttribute("loc", elencoAlberghi);
								%>
								
								<div class="post">
                                    <img src="http://127.0.0.1:8887/css/images/albergo.png"  style="width:50px;height:50px; float: left">
                                    <span class="span">Nome albergo: <%out.println(nomeLocazione);%></span>
									<br><br><br>
									<div>
										<form method="get" action="visualizzaPrezzi.jsp" enctype="text/plain">
											<input type="radio" style="display:none" name="SerParcheggio" value="" checked="checked" >
											<input type="radio" style="display:none" name="SerWifi" value="" checked="checked" >
											<input type="radio" style="display:none" name="SerPet" value="" checked="checked" >
											<input type="radio" style="display:none" name="tipoPensione" value="" checked="checked" >
											<table style="width: 100%;">
												<tr>
													<%

															if(elencoAlberghi.get(i).getTipoPensione().equals("completa")){%>
													<td><b>Tipo Pensione</b>
														completa<input type="radio" name="tipoPensione" value="completa" />
														mezza pensione<input type="radio" name="tipoPensione" value="mezza pensione" /><br /><br />
													</td>
													<%}%>


												</tr>
												<%       if(elencoAlberghi.get(i).isParcheggio()){
												%>
												<tr>
													<td><b>Servizi disponibili: </b></td>
												</tr>
												<tr>
													<td style="padding-right: 30px">
														<div >
															<b>Parcheggio</b>
															S&igrave;<input type="radio" name="SerParcheggio" value="true" />
															No<input type="radio" name="SerParcheggio" value="false" /><br /><br />
														</div>
													</td>
													<%}
													%>
													<%
														if(elencoAlberghi.get(i).isWifi()){

													%>
													<td style="padding-right: 30px">
														<div >
															<b>Wifi</b>
															S&igrave;<input type="radio" name="SerWifi" value="true"   />
															No<input type="radio" name="SerWifi" value="false" /><br /><br />
														</div>
													</td>
													<%}%>
													<%	if(elencoAlberghi.get(i).isPet()){
													%>
													<td style="padding-right: 30px">
														<div >
															<b>Animali ammessi</b>
															S&igrave;<input type="radio" name="SerPet" value="true"  />
															No<input type="radio" name="SerPet" value="false" /><br /><br />

														</div>
													</td>
													<%}%>

													<input type="hidden" name="id" value="<%out.println(i);%>">
													<input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
													<input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
													<td style="float: right"><input type="submit" class="btnBlueborder" value="Info&Dettagli"></td>
												</tr>
											</table>

										</form>
									</div>
									<table style="width:100%">
                                        <tr>
                                            <td><b>Username locatore:</b> <%out.println(userLocatore);%></td>
                                            <td><b>Prezzo pernottamento :</b> <%out.println(prezzo);%></td>
                                        </tr>
										<tr>
											<td><b>Provincia:</b> <%out.println(provincia);%></td>
											<td><b>Indirizzo:</b> <%out.println(indirizzo);%></td>

										</tr>
                                        <tr>
                                            <td><b>Tipo pensione:</b> <%out.println(tipoPensione);%></td>

                                        </tr>
										<tr>
											<td><b>Orario colazione:</b> <%out.println(orarioColazione);%></td>
											<td><b>Orario pranzo:</b> <%out.println(orarioPranzo);%></td>
											<td><b>Orario cena:</b> <%out.println(orarioCena);%></td>
										</tr>
										<tr>
											<td><b>Parcheggio:</b> <%out.println(sParcheggio);%></td>
											<td><b>Wifi:</b> <%out.println(sWifi);%></td>
											<td><b>Animali ammessi:</b> <%out.println(sPet);%></td>
										</tr>
									</table>
									<div class="break-word">
									<p style="font-size: large"><b>Descrizione:</b> <%out.println(descrizione);%></p>
									</div>
									<form method="get" action="prenotaLocazione.jsp" enctype="text/plain">
									<div>
										<input type="hidden" name="id" value="<%out.println(i);%>">
										<input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
										<input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
										<div style="float:left; width: 100px"><p style="font-size: 15px"><b>Prenota per: </b></p> </div>

										<div style="float: left;">
											<select style="width: 200px" name="prenota per">
												<option value="1">1 persona</option>
												<option value="2">2 persone</option>
												<option value="3">3 persone</option>
												<option value="4">4 persone</option>
												<option value="5">5 persone</option>
											</select>

										</div>
										<div style="clear: both"></div>
										<br>
										<input type="submit" class="btnBlue200" value="PRENOTA">
									</div>
									</form>
								</div>	
								
								<%
								}
						} else if(commandInt==1){
							ArrayList<Appartamento> elencoAppartamenti = (ArrayList<Appartamento>) elencoLocazioni;
									if(elencoAppartamenti.size()==0){ %>
										<p style="font-size: 30px; color: darkred"> Non sono presenti appartamenti che soddisfano i parametri richiesti </p>
										<h3><a style="color: #4b7091" href="ricercaLocazione.jsp">Torna alla pagina di ricerca</a></h3>

					<% }%>
							<%
							for(int i=0;i<elencoAppartamenti.size();i++){
								nomeLocazione = elencoAppartamenti.get(i).getNomeLocazione();
                                prezzo =elencoAppartamenti.get(i).getPrezzo();
								provincia = elencoAppartamenti.get(i).getProvincia();
								indirizzo = elencoAppartamenti.get(i).getIndirizzo();
								userLocatore = elencoAppartamenti.get(i).getUserLocatore();
								descrizione = elencoAppartamenti.get(i).getDescrizione();
								String sParcheggio;
								String sWifi;
								String sPet;
																	
								if(elencoAppartamenti.get(i).isParcheggio()){
									sParcheggio = "Disponibile";
								} else {
									sParcheggio = "Non disponibile";
								}
								if(elencoAppartamenti.get(i).isWifi()){
									sWifi = "Disponibile";
								} else {
									sWifi = "Non disponibile";
								}
								if(elencoAppartamenti.get(i).isPet()){
									sPet = "Disponibile";
								} else {
									sPet = "Non disponibile";
								}
								String numeroStanze = elencoAppartamenti.get(i).getNumeroStanze();
								String numeroBagni = elencoAppartamenti.get(i).getNumeroBagni();
								String sGiardino;
								if(elencoAppartamenti.get(i).isGiardino()){
									sGiardino = "Si";
								} else {
									sGiardino = "No";
								}
								String numeroLetti = elencoAppartamenti.get(i).getNumeroLetti();
								request.getSession().setAttribute("loc", elencoAppartamenti);
							%>
							
							<div class="post">
                                <img src="http://127.0.0.1:8887/css/images/appartamento.png"  style="width:50px;height:50px;float: left">
                                <span class="span">Nome appartamento: <%out.println(nomeLocazione);%></span>
								<br><br><br>
								<div>
									<form method="get" action="visualizzaPrezzi.jsp" enctype="text/plain">
										<input type="radio" style="display:none" name="SerParcheggio" value="" checked="checked" >
										<input type="radio" style="display:none" name="SerWifi" value="" checked="checked" >
										<input type="radio" style="display:none" name="SerPet" value="" checked="checked" >
										<input type="radio" style="display:none" name="tipoPensione" value="" checked="checked" >
										<table style="width: 100%;">
											<%       if(elencoAppartamenti.get(i).isParcheggio()){
											%>
											<tr>
												<td><b>Servizi disponibili: </b></td>
											</tr>
											<tr>
												<td style="padding-right: 30px">
													<div >
														<b>Parcheggio</b>
														S&igrave;<input type="radio" name="SerParcheggio" value="true" />
														No<input type="radio" name="SerParcheggio" value="false" /><br /><br />
													</div>
												</td>
												<%}
												%>
												<%
													if(elencoAppartamenti.get(i).isWifi()){

												%>
												<td style="padding-right: 30px">
													<div >
														<b>Wifi</b>
														S&igrave;<input type="radio" name="SerWifi" value="true"   />
														No<input type="radio" name="SerWifi" value="false" /><br /><br />
													</div>
												</td>
												<%}%>
												<%	if(elencoAppartamenti.get(i).isPet()){
												%>
												<td style="padding-right: 30px">
													<div >
														<b>Animali ammessi</b>
														S&igrave;<input type="radio" name="SerPet" value="true"  />
														No<input type="radio" name="SerPet" value="false" /><br /><br />

													</div>
												</td>
												<%}%>

												<input type="hidden" name="id" value="<%out.println(i);%>">
												<input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
												<input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
												<td style="float: right"><input type="submit" class="btnBlueborder" value="Info&Dettagli"></td>
											</tr>
										</table>

									</form>
								</div>
									<table style="width:100%">
                                        <tr>
                                            <td><b>Username locatore:</b> <%out.println(userLocatore);%></td>
                                            <td><b>Prezzo :</b> <%out.println(prezzo);%></td>
                                        </tr>
										<tr>
											<td><b>Provincia:</b> <%out.println(provincia);%></td>
											<td><b>Indirizzo:</b> <%out.println(indirizzo);%></td>
										</tr>
										<tr>
                                            <td><b>Parcheggio:</b> <%out.println(sParcheggio);%></td>
											<td><b>Wifi:</b> <%out.println(sWifi);%></td>
                                            <td><b>Animali ammessi:</b> <%out.println(sPet);%></td>
										</tr>
										<tr>
                                            <td><b>Numero stanze:</b> <%out.println(numeroStanze);%></td>
											<td><b>Numero letti:</b> <%out.println(numeroLetti);%></td>
                                            <td><b>Numero bagni:</b> <%out.println(numeroBagni);%></td>
										</tr>
										<tr>
                                            <td><b>Giardino:</b> <%out.println(sGiardino);%></td>
										</tr>
									</table>
									<div class="break-word">
									<p style="font-size: large"><b>Descrizione:</b> <%out.println(descrizione);%></p>
									</div>
									<form method="get" action="prenotaLocazione.jsp" enctype="text/plain">
									<div>
										<input type="hidden" name="id" value="<%out.println(i);%>">
										<input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
										<input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
										<input type="hidden" name="prenota per" value="1">
										<input type="submit" class="btnBlue200" value="PRENOTA">
										</div>
									</form>
								</div>	
								
							
							<%
							}
							
						} else if(commandInt == 2){
								ArrayList<Beb> elencoBeb = (ArrayList<Beb>) elencoLocazioni;
								if(elencoBeb.size()==0){ %>
									<p style="font-size: 30px; color: darkred"> Non sono presenti b&b che soddisfano i parametri richiesti </p>
									<h3><a style="color: #4b7091" href="ricercaLocazione.jsp">Torna alla pagina di ricerca</a></h3>

					<% }%>
								<%
								for(int i=0;i<elencoBeb.size();i++){
								nomeLocazione = elencoBeb.get(i).getNomeLocazione();
                                prezzo = elencoBeb.get(i).getPrezzo();
								provincia = elencoBeb.get(i).getProvincia();
								indirizzo = elencoBeb.get(i).getIndirizzo();
								userLocatore = elencoBeb.get(i).getUserLocatore();
								descrizione = elencoBeb.get(i).getDescrizione();
								String sParcheggio;
								String sWifi;
								String sPet;
																	
								if(elencoBeb.get(i).isParcheggio()){
									sParcheggio = "Disponibile";
								} else {
									sParcheggio = "Non disponibile";
								}
								if(elencoBeb.get(i).isWifi()){
									sWifi = "Disponibile";
								} else {
									sWifi = "Non disponibile";
								}
								if(elencoBeb.get(i).isPet()){
									sPet = "Disponibile";
								} else {
									sPet = "Non disponibile";
								}
								String orarioColazione = elencoBeb.get(i).getOrarioColazione();
								request.getSession().setAttribute("loc", elencoBeb);
							%>
							
							<div class="post">
                                <img src="http://127.0.0.1:8887/css/images/beb.jpeg"  style="width:50px;height:50px;float: left;">
                                <span class="span">Nome b&b: <%out.println(nomeLocazione);%></span>
								<br><br><br>
								<div>
									<form method="get" action="visualizzaPrezzi.jsp" enctype="text/plain">
										<input type="radio" style="display:none" name="SerParcheggio" value="" checked="checked" >
										<input type="radio" style="display:none" name="SerWifi" value="" checked="checked" >
										<input type="radio" style="display:none" name="SerPet" value="" checked="checked" >
										<input type="radio" style="display:none" name="tipoPensione" value="" checked="checked" >
										<table style="width: 100%;">

											<%       if(elencoBeb.get(i).isParcheggio()){
											%>
											<tr>
												<td><b>Servizi disponibili: </b></td>
											</tr>
											<tr>
												<td style="padding-right: 30px">
													<div >
														<b>Parcheggio</b>
														S&igrave;<input type="radio" name="SerParcheggio" value="true" />
														No<input type="radio" name="SerParcheggio" value="false" /><br /><br />
													</div>
												</td>
												<%}
												%>
												<%
													if(elencoBeb.get(i).isWifi()){

												%>
												<td style="padding-right: 30px">
													<div >
														<b>Wifi</b>
														S&igrave;<input type="radio" name="SerWifi" value="true"   />
														No<input type="radio" name="SerWifi" value="false" /><br /><br />
													</div>
												</td>
												<%}%>
												<%	if(elencoBeb.get(i).isPet()){
												%>
												<td style="padding-right: 30px">
													<div >
														<b>Animali ammessi</b>
														S&igrave;<input type="radio" name="SerPet" value="true"  />
														No<input type="radio" name="SerPet" value="false" /><br /><br />

													</div>
												</td>
												<%}%>

												<input type="hidden" name="id" value="<%out.println(i);%>">
												<input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
												<input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
												<td style="float: right"><input type="submit" class="btnBlueborder" value="Info&Dettagli"></td>
											</tr>
										</table>

									</form>
								</div>
								
								<table style="width:100%">
                                         <tr>
                                             <td><b>Username locatore:</b> <%out.println(userLocatore);%></td>
                                             <td><b>Prezzo pernottamento :</b> <%out.println(prezzo);%></td>
                                         </tr>
										<tr>
											<td><b>Provincia:</b> <%out.println(provincia);%></td>
											<td><b>Indirizzo:</b> <%out.println(indirizzo);%></td>
										</tr>
										<tr>
                                            <td><b>Parcheggio:</b> <%out.println(sParcheggio);%></td>
											<td><b>Wifi:</b> <%out.println(sWifi);%></td>
											<td><b>Animali ammessi:</b> <%out.println(sPet);%></td>
										</tr>
                                        <tr>
                                            <td><b>Orario colazione:</b> <%out.println(orarioColazione);%></td>
                                        </tr>
									</table>
									<div class="break-word">
									<p style="font-size: large"><b>Descrizione:</b> <%out.println(descrizione);%></p>
									</div>
									<form method="get" action="prenotaLocazione.jsp" enctype="text/plain">
									<div>
										<input type="hidden" name="id" value="<%out.println(i);%>">
										<input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
										<input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
										<div style="float:left; width: 100px"><p style="font-size: 15px"><b>Prenota per: </b></p> </div>

										<div style="float: left;">
											<select style="width: 200px" name="prenota per">
												<option value="1">1 persona</option>
												<option value="2">2 persone</option>
												<option value="3">3 persone</option>
												<option value="4">4 persone</option>
												<option value="5">5 persone</option>
											</select>

										</div>
										<div style="clear: both"></div>
										<br>
										<input type="submit" class="btnBlue200" value="PRENOTA">
										</div>
									</form>
								</div>	
							
							<%
							}
					} else if(commandInt == 3){
						ArrayList<CasaVacanza> elencoCasaVacanze = (ArrayList<CasaVacanza>) elencoLocazioni;
								if(elencoCasaVacanze.size()==0){ %>
									<p style="font-size: 30px; color: darkred"> Non sono presenti case vacanza che soddisfano i parametri richiesti </p>
									<h3><a style="color: #4b7091" href="ricercaLocazione.jsp">Torna alla pagina di ricerca</a></h3>

								<% }%>
						<%
						for(int i=0;i<elencoCasaVacanze.size();i++){
							nomeLocazione = elencoCasaVacanze.get(i).getNomeLocazione();
                            prezzo = elencoCasaVacanze.get(i).getPrezzo();
							provincia = elencoCasaVacanze.get(i).getProvincia();
							indirizzo = elencoCasaVacanze.get(i).getIndirizzo();
							userLocatore = elencoCasaVacanze.get(i).getUserLocatore();
							descrizione = elencoCasaVacanze.get(i).getDescrizione();
							String sParcheggio;
							String sWifi;
							String sPet;
																
							if(elencoCasaVacanze.get(i).isParcheggio()){
								sParcheggio = "Disponibile";
							} else {
								sParcheggio = "Non disponibile";
							}
							if(elencoCasaVacanze.get(i).isWifi()){
								sWifi = "Disponibile";
							} else {
								sWifi = "Non disponibile";
							}
							if(elencoCasaVacanze.get(i).isPet()){
								sPet = "Disponbile";
							} else {
								sPet = "Non disponibile";
							}
							String numeroCamere = elencoCasaVacanze.get(i).getNumeroCamere();
							String numeroBagni = elencoCasaVacanze.get(i).getNumeroBagni();
							String sGiardino;
							if(elencoCasaVacanze.get(i).isGiardino()){
								sGiardino = "Si";
							} else {
								sGiardino = "No";
							}
							String numeroLetti = elencoCasaVacanze.get(i).getNumeroLetti();
							request.getSession().setAttribute("loc", elencoCasaVacanze);
							
						%>
						
						<div class="post">
                            <img src="http://127.0.0.1:8887/css/images/casaVacanza.png"  style="width:50px;height:50px;float: left">
                            <span class="span">Nome casa vacanza: <%out.println(nomeLocazione);%></span>
							<br><br><br>
							<div>
								<form method="get" action="visualizzaPrezzi.jsp" enctype="text/plain">
									<input type="radio" style="display:none" name="SerParcheggio" value="" checked="checked" >
									<input type="radio" style="display:none" name="SerWifi" value="" checked="checked" >
									<input type="radio" style="display:none" name="SerPet" value="" checked="checked" >
									<input type="radio" style="display:none" name="tipoPensione" value="" checked="checked" >
									<table style="width: 100%;">
										<%       if(elencoCasaVacanze.get(i).isParcheggio()){
										%>
										<tr>
											<td><b>Servizi disponibili: </b></td>
										</tr>
										<tr>
											<td style="padding-right: 30px">
												<div >
													<b>Parcheggio</b>
													S&igrave;<input type="radio" name="SerParcheggio" value="true" />
													No<input type="radio" name="SerParcheggio" value="false" /><br /><br />
												</div>
											</td>
											<%}
											%>
											<%
												if(elencoCasaVacanze.get(i).isWifi()){

											%>
											<td style="padding-right: 30px">
												<div >
													<b>Wifi</b>
													S&igrave;<input type="radio" name="SerWifi" value="true"   />
													No<input type="radio" name="SerWifi" value="false" /><br /><br />
												</div>
											</td>
											<%}%>
											<%	if(elencoCasaVacanze.get(i).isPet()){
											%>
											<td style="padding-right: 30px">
												<div >
													<b>Animali ammessi</b>
													S&igrave;<input type="radio" name="SerPet" value="true"  />
													No<input type="radio" name="SerPet" value="false" /><br /><br />

												</div>
											</td>
											<%}%>

											<input type="hidden" name="id" value="<%out.println(i);%>">
											<input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
											<input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
											<td style="float: right"><input type="submit" class="btnBlueborder" value="Info&Dettagli"></td>
										</tr>
									</table>

								</form>
							</div>
									<table style="width:100%">
                                        <tr>
                                            <td><b>Username locatore:</b> <%out.println(userLocatore);%></td>
                                            <td><b>Prezzo :</b> <%out.println(prezzo);%></td>
                                        </tr>
										<tr>
											<td><b>Provincia:</b> <%out.println(provincia);%></td>
											<td><b>Indirizzo:</b> <%out.println(indirizzo);%></td>
										</tr>
										<tr>
                                            <td><b>Parcheggio:</b> <%out.println(sParcheggio);%></td>
											<td><b>Wifi:</b> <%out.println(sWifi);%></td>
                                            <td><b>Animali ammessi:</b> <%out.println(sPet);%></td>

										</tr>
										<tr>
                                            <td><b>Numero camere:</b> <%out.println(numeroCamere);%></td>
											<td><b>Numero letti:</b> <%out.println(numeroLetti);%></td>
                                            <td><b>Numero bagni:</b> <%out.println(numeroBagni);%></td>
										</tr>
										<tr>
                                            <td><b>Giardino:</b> <%out.println(sGiardino);%></td>
										</tr>
									</table>
									<div class="break-word">
									<p style="font-size: large"><b>Descrizione:</b> <%out.println(descrizione);%></p>
									</div>
									<form method="get" action="prenotaLocazione.jsp" enctype="text/plain">
									<div>
										<input type="hidden" name="id" value="<%out.println(i);%>">
										<input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
										<input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
										<input type="hidden" name="prenota per" value="1">
										<input type="submit" class="btnBlue200" value="PRENOTA">
										</div>
									</form>
								</div>	
						
					
						<%
						}
					} else if(commandInt == 4){
						ArrayList<Ostello> elencoOstelli = (ArrayList<Ostello>) elencoLocazioni;
							if(elencoOstelli.size()==0){ %>
								<p style="font-size: 30px; color: darkred"> Non sono presenti ostelli che soddisfano i parametri richiesti </p>
								<h3><a style="color: #4b7091" href="ricercaLocazione.jsp">Torna alla pagina di ricerca</a></h3>

					<% }%>
						<%
						for(int i=0;i<elencoOstelli.size();i++){
							nomeLocazione = elencoOstelli.get(i).getNomeLocazione();
                            prezzo = elencoOstelli.get(i).getPrezzo();
							provincia = elencoOstelli.get(i).getProvincia();
							indirizzo = elencoOstelli.get(i).getIndirizzo();
							userLocatore = elencoOstelli.get(i).getUserLocatore();
							descrizione = elencoOstelli.get(i).getDescrizione();
							String sParcheggio;
							String sWifi;
							String sPet;
																
							if(elencoOstelli.get(i).isParcheggio()){
								sParcheggio = "Disponibile";
							} else {
								sParcheggio = "Non disponibile";
							}
							if(elencoOstelli.get(i).isWifi()){
								sWifi = "Disponibile";
							} else {
								sWifi = "Non disponibile";
							}
							if(elencoOstelli.get(i).isPet()){
								sPet = "Disponibile";
							} else {
								sPet = "Non disponibile";
							}
							request.getSession().setAttribute("loc", elencoOstelli);
						%>
						
						<div class="post">
                            <img src="http://127.0.0.1:8887/css/images/ostello.jpeg"  style="width:50px;height:50px;float: left">
                            <span class="span">Nome ostello: <%out.println(nomeLocazione);%></span>
							<br><br><br>
							<div>
								<form method="get" action="visualizzaPrezzi.jsp" enctype="text/plain">
									<input type="radio" style="display:none" name="SerParcheggio" value="" checked="checked" >
									<input type="radio" style="display:none" name="SerWifi" value="" checked="checked" >
									<input type="radio" style="display:none" name="SerPet" value="" checked="checked" >
									<input type="radio" style="display:none" name="tipoPensione" value="" checked="checked" >
									<table style="width: 100%;">
										<tr>
											<%

													if(elencoOstelli.get(i).getTipoPensione().equals("completa")){%>
											<td><b>Tipo Pensione</b>
												completa<input type="radio" name="tipoPensione" value="completa" />
												mezza pensione<input type="radio" name="tipoPensione" value="mezza pensione" /><br /><br />
											</td>
											<%
													}%>

										</tr>
										<%       if(elencoOstelli.get(i).isParcheggio()){
										%>
										<tr>
											<td><b>Servizi disponibili: </b></td>
										</tr>
										<tr>
											<td style="padding-right: 30px">
												<div >
													<b>Parcheggio</b>
													S&igrave;<input type="radio" name="SerParcheggio" value="true" />
													No<input type="radio" name="SerParcheggio" value="false" /><br /><br />
												</div>
											</td>
											<%}
											%>
											<%
												if(elencoOstelli.get(i).isWifi()){

											%>
											<td style="padding-right: 30px">
												<div >
													<b>Wifi</b>
													S&igrave;<input type="radio" name="SerWifi" value="true"   />
													No<input type="radio" name="SerWifi" value="false" /><br /><br />
												</div>
											</td>
											<%}%>
											<%	if(elencoOstelli.get(i).isPet()){
											%>
											<td style="padding-right: 30px">
												<div >
													<b>Animali ammessi</b>
													S&igrave;<input type="radio" name="SerPet" value="true"  />
													No<input type="radio" name="SerPet" value="false" /><br /><br />

												</div>
											</td>
											<%}%>

											<input type="hidden" name="id" value="<%out.println(i);%>">
											<input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
											<input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
											<td style="float: right"><input type="submit" class="btnBlueborder" value="Info&Dettagli"></td>
										</tr>
									</table>

								</form>
							</div>
									<table style="width:100%">
                                        <tr>
                                            <td><b>Username locatore:</b> <%out.println(userLocatore);%></td>
                                            <td><b>Prezzo pernottamento:</b> <%out.println(prezzo);%></td>
                                        </tr>
										<tr>
											<td><b>Provincia:</b> <%out.println(provincia);%></td>
											<td><b>Indirizzo:</b> <%out.println(indirizzo);%></td>
										</tr>
										<tr>
                                            <td><b>Parcheggio:</b> <%out.println(sParcheggio);%></td>
											<td><b>Wifi:</b> <%out.println(sWifi);%></td>
                                            <td><b>Animali ammessi:</b> <%out.println(sPet);%></td>
										</tr>
									</table>
									<div class="break-word">
									<p style="font-size: large"><b>Descrizione:</b> <%out.println(descrizione);%></p>
									</div>
									<form method="get" action="prenotaLocazione.jsp" enctype="text/plain">
									<div>
										<input type="hidden" name="id" value="<%out.println(i);%>">
										<input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
										<input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
										<div style="float:left; width: 100px"><p style="font-size: 15px"><b>Prenota per: </b></p> </div>

										<div style="float: left;">
											<select style="width: 200px" name="prenota per">
												<option value="1">1 persona</option>
												<option value="2">2 persone</option>
												<option value="3">3 persone</option>
												<option value="4">4 persone</option>
												<option value="5">5 persone</option>
											</select>

										</div>
										<div style="clear: both"></div>
										<br>
										<input type="submit" class="btnBlue200" value="PRENOTA">
										</div>
									</form>
								</div>	
												
						<%
						}
					}

				}
						
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
