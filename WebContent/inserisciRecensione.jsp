<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8"  />
    <title>Perfect Places</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <link href="http://127.0.0.1:8887/css/style.css" rel="stylesheet" type="text/css" media="screen" />

</head>
<style>
    input[type=text], select {
        width: 60%;
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }
</style>

<body>
<div id="wrapper">

    <div id="header-wrapper">
        <div id="header">
            <div id="logo">
                <h1>Scrivi la tua recensione</h1>
            </div>
        </div>
    </div>
    <!-- end #header -->

    <div id="page">
        <div id="page-bgtop">
            <!-- Menu -->
            <ul class="topnav" id=myTopnav">
                <li><a href="utente.jsp">HOME</a></li>
                <li><a href="areaViaggiatore.jsp">Area Viaggiatore</a></li>
                <li><a href="areaProprietario.jsp">Area Proprietario</a></li>
                <li><a href="profiloUtente.jsp">Visualizza profilo</a></li>
                <li><a href="posta.jsp">Posta</a></li>
                <li><a href="areaAiuto.jsp">AIUTO</a></li>
                <li><a href="logout.jsp">Esci</a></li>
            </ul>

            <div id="page-bgbtm">
                <div id="content">

                    <h2><b>Recensisci una locazione</b></h2>

                    <div class="post">
                        <form method="get"  action="riceviRecensione.jsp" >
                            <table width="100%">

                                <tr>
                                    <td>
                                        <label for="nomeLocazione">Nome Locazione:</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input id="nomeLocazione" name="nomeLocazione" type="text" placeholder="inserisci il nome della locazione da recensire" name="destinatario" class="btn"
                                        />
                                    </td>
                                </tr>
                                <tr>  <td><label for="tipoLocazione">Tipo Locazione: </label></td></tr>

                                <tr>
                                    <td>
                                            <select id="tipoLocazione" name="tipoLocazione">
                                                <option value="Albergo">Albergo</option>
                                                <option value="Appartamento">Appartamento</option>
                                                <option value="Beb">Beb</option>
                                                <option value="CasaVacanza">Casa Vacanza</option>
                                                <option value="Ostello">Ostello</option>

                                            </select>
                                    </td>
                                </tr>
                                <tr> <td><label for="stelle">Inserisci il numero di stelle: </label></td></tr>


                                <tr>
                                    <td>
                                        <select id="stelle" name="numeroStelle">
                                            <option value="0">0</option>
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>

                                        </select>
                                    </td>
                                </tr>

                                <tr><td><label for="recensione">Recensione</label></td></tr>
                                <tr>
                                    <td><textarea id="recensione" class="btn" rows="10" cols="100%"  maxlength="400"   name="testoRecensione" placeholder="Scrivi qui la tua recensione"></textarea></td>
                                </tr>
                                <br><br>

                                <tr>
                                        <td>
                                            <input class="btnBlueRecensione" type="submit" value="INVIA"  />
                                        </td>
                                </tr>
                            </table>
                        </form>
                    </div>

                    <div style="clear: both;">&nbsp;</div>
                </div>
                <!-- end #content -->

                <div style="clear: both;">&nbsp;</div>
            </div>
        </div>
    </div>
    <!-- end #page -->

</div>

<div id="footer"></div>
<!-- end #footer -->

</body>
</html>

