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
				<h1><a href="#">Ricerca la tua locazione</a></h1>
				
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

				<p style="size: 40px; color: red"> Errore! Sessione scaduta. Accedi di nuovo per continuare. </p>

				<%
					}
				%>
            </div>
            <div id="page-bgbtm">
                <div id="content">
                    <h2><b>Effettua una ricerca globale delle locazioni</b> </h2>
                    <form method="get" action="risulatoRicerca.jsp" enctype="text/plain" id="locform" >
                        <div >
                            <input type="hidden" name="command" id="command_0" value="100"/>
                            Prezzo:<input type="text" style="width:200px" placeholder="&euro;" name="prezzo" value="" />
                            Provincia:<input type="text"style="width:240px" placeholder="es. Roma" style="width:180px" name="provincia" value="" />
                            Data Inizio:<input type="date" style="width:200px" name="dataInizio" value="" />
                            Data Fine: <input type="date" style="width:200px" name="dataFine" value="" />
                            <input id =btnRicercaGlobale type="submit" class="btnBlue200" value="Ricerca" /><br />
                        </div>
                    </form>
                    <br><br><br>

                    <h2><b>Oppure clicca sulla locazione desiderata</b></h2>

                    <br><br><br>
                    <div>
                        <div style="float: left; width: 400px" >
                            <h3>Albergo</h3>
                            <input id ="albergo" type="radio" style="display: none" name="command" value="0" onclick= "visualizzaPerViaggiatore('albform','aptform','bebform','cvzform','ostform','btnRicerca0', 'piuOp0','albformAvanzata','aptformAvanzata','bebformAvanzata','cvzformAvanzata','ostformAvanzata'); nascondiBtnAvanzata('piuOp1','piuOp2','piuOp3','piuOp4')" />
                            <label class="drinkcard-cc albergo" for="albergo"></label>
                        </div>
                        <form method="get" action="risulatoRicerca.jsp" enctype="text/plain" id="albform" style="display:none">
                            <div style="float: left; width: 200px">
                                <br><br>
                                <input type="hidden" name="command" id="command_1" value="0"/>
                                Prezzo:<br /><input type="text" placeholder="&euro;" name="prezzo" value="" /><br />
                                Provincia:<br /><input type="text"placeholder="es. Roma" name="provincia" value="" /><br /><br />
                                Data Inizio:<br /><input type="date" name="dataInizio" value="" /><br /><br />
                                Data Fine:<br /><input type="date" name="dataFine" value="" /><br /><br />
                                <input id =btnRicerca0 type="submit" class="btnBlue" value="Ricerca" /><br />
                            </div>
                        </form>
                        <!--  RICERCA AVANZATA -->
                        <div style="float:left; width: 50px">
                        </div>
                        <div style="float: left">
                            <br><br><br>
                            <input id="piuOp0" type="submit" class="btnBlueborder" style="display: none" name = commandAvan value = "avanzata" onclick="visualizzaRicercaAvanzata('btnRicerca0','piuOp0','albform','albformAvanzata')"/>

                            <form method="get" action="risulatoRicerca.jsp" enctype="text/plain" id="albformAvanzata" style="display: none">
                                <div>
                                    <input type="hidden" name="commandAvanzata" id="commandAvanzata_1" value="0"/>
                                    Prezzo:<br /><input type="text" placeholder="&euro;" name="prezzo" value="" /><br />
                                    Provincia:<br /><input type="text" placeholder="es. Roma" name="provincia" value="" /><br /><br />
                                    Data Inizio:<br /><input type="date" name="dataInizio" value="" /><br /><br />
                                    Data Fine:<br /><input type="date" name="dataFine" value="" /><br /><br />
                                    <div>
                                        Parcheggio:<br />
                                        S&igrave;<input type="radio" name="parcheggio" value="true" checked="checked" />
                                        No<input type="radio" name="parcheggio" value="false" /><br /><br />
                                        Wifi:<br />
                                        S&igrave;<input type="radio" name="wifi" value="true"  checked="checked" />
                                        No<input type="radio" name="wifi" value="false" /><br /><br />
                                        Animali ammessi:<br />
                                        S&igrave;<input type="radio" name="pet" value="true" checked="checked" />
                                        No<input type="radio" name="pet" value="false" /><br /><br />
                                        <p>Tipo Pensione</p>
                                        <select name="tipoPensione"  >
                                            <option value="completa">completa</option>
                                            <option value="mezza pensione">mezza pensione</option>
                                        </select>
                                        <br /><br />
                                        <input type="submit"  class="btnBlue" value="Ricerca" /><br />
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div style="clear: both"></div>

                    <div>
                        <div style="float: left; width: 400px">
                            <h3>Appartamento</h3>
                            <input id="appartamento" type="radio" style="display: none" name="command" value="1" onclick="visualizzaPerViaggiatore('aptform','albform','bebform','cvzform','ostform','btnRicerca1','piuOp1','aptformAvanzata','albformAvanzata','bebformAvanzata','cvzformAvanzata','ostformAvanzata') ; nascondiBtnAvanzata('piuOp0','piuOp2','piuOp3','piuOp4')"/>
                            <label class="drinkcard-cc appartamento" for="appartamento"></label>
                        </div>
                        <form method="get" action="risulatoRicerca.jsp" enctype="text/plain" id="aptform" style="display:none">
                            <div style="float: left">
                                <br><br>
                                <input type="hidden" name="command" id="command_2" value="1"/>
                                Prezzo :<br /><input type="text" placeholder="&euro;" name="prezzo" value="" /><br /><br />
                                Provincia:<br /><input type="text" placeholder="es. Roma" name="provincia" value="" /><br /><br />
                                Data Inizio:<br /><input type="date" name="dataInizio" value="" /><br />
                                Data Fine:<br /><input type="date" name="dataFine" value="" /><br /><br />
                                <input id="btnRicerca1" type="submit"class="btnBlue" value="Ricerca" /><br />
                            </div>
                        </form>
                        <!--  RICERCA AVANZATA -->
                        <div style="float: left">
                            <br><br><br>
                            <input id="piuOp1" type="submit" class="btnBlueborder" style="display: none"  name = commandAvan value = "avanzata"  onclick="visualizzaRicercaAvanzata('btnRicerca1','piuOp1','aptform','aptformAvanzata')"/>

                            <form method="get" action="risulatoRicerca.jsp" enctype="text/plain" id="aptformAvanzata" style="display: none">
                                <div>
                                    <input type="hidden" name="commandAvanzata" id="commandAvanzata_2" value="1"/>
                                    Prezzo:<br /><input type="text" placeholder="&euro;" name="prezzo" value="" /><br />
                                    Provincia:<br /><input type="text" placeholder="es. Roma" name="provincia" value="" /><br /><br />
                                    Data Inizio:<br /><input type="date" name="dataInizio" value="" /><br /><br />
                                    Data Fine:<br /><input type="date" name="dataFine" value="" /><br /><br />
                                    <div>
                                        Parcheggio:<br />
                                        S&igrave;<input type="radio" name="parcheggio" value="true" checked="checked" />
                                        No<input type="radio" name="parcheggio" value="false" /><br /><br />
                                        Wifi:<br />
                                        S&igrave;<input type="radio" name="wifi" value="true"  checked="checked" />
                                        No<input type="radio" name="wifi" value="false" /><br /><br />
                                        Animali ammessi:<br />
                                        S&igrave;<input type="radio" name="pet" value="true" checked="checked" />
                                        No<input type="radio" name="pet" value="false" /><br /><br />
                                        <p>Numero Stanze</p>
                                        <select name="numeroStanze"  >
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>
                                            <option value="6">6</option>
                                        </select>
                                        <br/><br/>
                                        <input type="submit"  class="btnBlue" value="Ricerca" /><br />
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div style="clear: both"></div>

                    <div>
                        <div style="float: left; width: 400px">
                            <h3>Bed &amp; Breakfast</h3>
                            <input id="beb"type="radio" style="display:none" name="command" value="2" onclick="visualizzaPerViaggiatore('bebform','aptform','albform','cvzform','ostform','btnRicerca2','piuOp2','bebformAvanzata','albformAvanzata','aptformAvanzata','cvzformAvanzata','ostformAvanzata') ; nascondiBtnAvanzata('piuOp1','piuOp0','piuOp3','piuOp4')"/>
                            <label class=" drinkcard-cc beb" for="beb"></label>
                        </div>
                        <form method="get" action="risulatoRicerca.jsp" enctype="text/plain" id="bebform" style="display:none">

                            <div style="float: left">
                                <br><br>
                                <input type="hidden" name="command" id="command_3" value="2"/>
                                Prezzo:<br /><input type="text" placeholder="&euro;" name="prezzo" value="" /><br /><br />
                                Provincia:<br /><input type="text" placeholder="es. Roma" name="provincia" value ="" /><br /><br />
                                Data Inizio:<br /><input type="date" name="dataInizio" value="" /><br/>
                                Data Fine:<br /><input type="date" name="dataFine" value="" /><br /><br />
                                <input id = "btnRicerca2" type="submit" class="btnBlue" value="Ricerca" /><br />
                            </div>
                        </form>
                        <!--  RICERCA AVANZATA -->
                        <div style="float: left">
                            <br><br><br>
                            <input id="piuOp2" type="submit" class="btnBlueborder" style="display: none"  name = commandAvan value = "avanzata"  onclick="visualizzaRicercaAvanzata('btnRicerca2','piuOp2','bebform','bebformAvanzata')"/>

                            <form method="get" action="risulatoRicerca.jsp" enctype="text/plain" id="bebformAvanzata" style="display: none">
                                <div>
                                    <input type="hidden" name="commandAvanzata" id="commandAvanzata_3" value="2"/>
                                    Prezzo per una notte:<br /><input type="text" placeholder="&euro;" name="prezzo" value="" /><br />
                                    Provincia:<br /><input type="text" placeholder="es. Roma" name="provincia" value="" /><br /><br />
                                    Data Inizio:<br /><input type="date" name="dataInizio" value="" /><br /><br />
                                    Data Fine:<br /><input type="date" name="dataFine" value="" /><br /><br />
                                    <div>
                                        Parcheggio:<br />
                                        S&igrave;<input type="radio" name="parcheggio" value="true" checked="checked" />
                                        No<input type="radio" name="parcheggio" value="false" /><br /><br />
                                        Wifi:<br />
                                        S&igrave;<input type="radio" name="wifi" value="true"  checked="checked" />
                                        No<input type="radio" name="wifi" value="false" /><br /><br />
                                        Animali ammessi:<br />
                                        S&igrave;<input type="radio" name="pet" value="true" checked="checked" />
                                        No<input type="radio" name="pet" value="false" /><br /><br />
                                        <input type="submit"  class="btnBlue" value="Ricerca" /><br />
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div style="clear: both"></div>

                    <div>
                        <div style="float:left; width: 400px">
                            <h3>Casa Vacanza</h3>
                            <br>
                            <input id="casaVacanza" type="radio" style="display:none" name="command" value="3" onclick="visualizzaPerViaggiatore('cvzform','aptform','bebform','albform','ostform','btnRicerca3','piuOp3','cvzformAvanzata','albformAvanzata','aptformAvanzata','bebformAvanzata','ostformAvanzata'); nascondiBtnAvanzata('piuOp1','piuOp2','piuOp0','piuOp4')"/>
                            <label class="drinkcard-cc casaVacanza" for="casaVacanza"></label>
                        </div>
                        <form method="get" action="risulatoRicerca.jsp" enctype="text/plain" id="cvzform" style="display:none">

                            <div style="float:left">
                                <br><br>
                                <input type="hidden" name="command" id="command_4" value="3"/>
                                Prezzo :<br /><input type="text" placeholder="&euro;" name="prezzo" value="" /><br /><br />
                                Provincia:<br /><input type="text" placeholder="es. Roma" name="provincia" value="" /><br /><br />
                                Data Inizio:<br /><input type="date" name="dataInizio" value="" /><br/>
                                Data Fine:<br /><input type="date" name="dataFine" value="" /><br /><br />
                                <input id = "btnRicerca3" type="submit" class="btnBlue" value="Ricerca" /><br />
                            </div>
                        </form>
                        <!--  RICERCA AVANZATA -->
                        <div style="float: left">
                            <br><br><br>
                            <input id="piuOp3" type="submit" class="btnBlueborder" style="display: none"  name = commandAvan value = "avanzata"  onclick="visualizzaRicercaAvanzata('btnRicerca3','piuOp3','cvzform','cvzformAvanzata')"/>

                            <form method="get" action="risulatoRicerca.jsp" enctype="text/plain" id="cvzformAvanzata" style="display: none">
                                <div>
                                    <input type="hidden" name="commandAvanzata" id="commandAvanzata_4" value="3"/>
                                    Prezzo:<br /><input type="text" placeholder="&euro;" name="prezzo" value="" /><br />
                                    Provincia:<br /><input type="text" placeholder="es. Roma" name="provincia" value="" /><br /><br />
                                    Data Inizio:<br /><input type="date" name="dataInizio" value="" /><br /><br />
                                    Data Fine:<br /><input type="date" name="dataFine" value="" /><br /><br />
                                    <div>
                                        Parcheggio:<br />
                                        S&igrave;<input type="radio" name="parcheggio" value="true" checked="checked" />
                                        No<input type="radio" name="parcheggio" value="false" /><br /><br />
                                        Wifi:<br />
                                        S&igrave;<input type="radio" name="wifi" value="true"  checked="checked" />
                                        No<input type="radio" name="wifi" value="false" /><br /><br />
                                        Animali ammessi:<br />
                                        S&igrave;<input type="radio" name="pet" value="true" checked="checked" />
                                        No<input type="radio" name="pet" value="false" /><br /><br />
                                        <p>Numero Camere</p>
                                        <select name="numeroCamere"  >
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>
                                            <option value="6">6</option>
                                        </select>
                                        <br /><br />
                                        <input type="submit"  class="btnBlue" value="Ricerca" /><br />
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div style="clear: both"></div>

                    <div>
                        <div style="float:left; width: 400px">
                            <h3>Ostello</h3>
                            <input id="ostello" type="radio" style="display:none" name="command" value="4" onclick="visualizzaPerViaggiatore('ostform','aptform','bebform','cvzform','albform','btnRicerca4','piuOp4','ostformAvanzata','albformAvanzata','aptformAvanzata','bebformAvanzata','cvzformAvanzata'); nascondiBtnAvanzata('piuOp1','piuOp2','piuOp3','piuOp0')"/>
                            <label class="drinkcard-cc ostello" for="ostello" ></label>
                        </div>
                        <form method="get" action="risulatoRicerca.jsp" enctype="text/plain" id="ostform" style="display:none">

                            <div style="float: left">
                                <br><br>
                                <input type="hidden" name="command" id="command" value="4"/>
                                Prezzo:<br /><input type="text" placeholder="&euro;" name="prezzo" value="" /><br /><br />
                                Provincia:<br /><input type="text" placeholder="es. Roma" name="provincia" value="" /><br /><br />
                                Data Inizio:<br /><input type="date" name="dataInizio" value="" /><br/>
                                Data Fine:<br /><input type="date" name="dataFine" value="" /><br /><br />
                                <input id = "btnRicerca4" type="submit" class="btnBlue" value="Ricerca" /><br />
                            </div>
                        </form>
                        <!--  RICERCA AVANZATA -->
                        <div style="float: left">
                            <br><br><br>
                            <input id="piuOp4" type="submit" class="btnBlueborder" style="display: none"  name = commandAvan value = "avanzata"  onclick="visualizzaRicercaAvanzata('btnRicerca4','piuOp4','ostform','ostformAvanzata')"/>

                            <form method="get" action="risulatoRicerca.jsp" enctype="text/plain" id="ostformAvanzata" style="display: none">
                                <div>
                                    <input type="hidden" name="commandAvanzata" id="commandAvanzata_5" value="4"/>
                                    Prezzo:<br /><input type="text" placeholder="&euro;" name="prezzo" value="" /><br />
                                    Provincia:<br /><input type="text" placeholder="es. Roma" name="provincia" value="" /><br /><br />
                                    Data Inizio:<br /><input type="date" name="dataInizio" value="" /><br /><br />
                                    Data Fine:<br /><input type="date" name="dataFine" value="" /><br /><br />
                                    <div>
                                        Parcheggio:<br />
                                        S&igrave;<input type="radio" name="parcheggio" value="true" checked="checked" />
                                        No<input type="radio" name="parcheggio" value="false" /><br /><br />
                                        Wifi:<br />
                                        S&igrave;<input type="radio" name="wifi" value="true"  checked="checked" />
                                        No<input type="radio" name="wifi" value="false" /><br /><br />
                                        Animali ammessi:<br />
                                        S&igrave;<input type="radio" name="pet" value="true" checked="checked" />
                                        No<input type="radio" name="pet" value="false" /><br /><br />
                                        <p>Tipo Pensione</p>
                                        <select name="tipoPensioneOstello"  >
                                            <option value="completa">completa</option>
                                            <option value="mezza pensione">mezza pensione</option>
                                        </select>
                                        <br /><br />
                                        <input type="submit"  class="btnBlue" value="Ricerca" /><br />
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div style="clear: both"></div>
                    </div>
                </div>
            <div style="clear: both;">&nbsp;</div>
        </div>
        <!-- end #sidebar -->
        <div style="clear: both;">&nbsp;</div>
    </div>
</div>

<div id="footer">

</div>
<!-- end #footer -->
</body>
</html>
