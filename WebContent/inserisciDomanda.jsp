
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>Frequently asked questions</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <link href="../css/style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<jsp:useBean id="bl" scope="session" class="boundary.BoundaryLogin"/>
<%@ page import="boundary.BoundaryAiuto" %>
<%@ page import="exception.DeserializzazioneException" %>
<%@ page import="exception.SerializzazioneException" %>

<%

    BoundaryAiuto bf= null;
    try {
        bf = new BoundaryAiuto();
    } catch (DeserializzazioneException e) {
        e.printStackTrace();
    }

    String nuovaDomanda = request.getParameter("nuovaDomanda");
    String type=request.getParameter("tipo");
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
                <h1><a href="#">AIUTO</a></h1>

            </div>
        </div>
    </div>
    <!-- end #header -->
    <div id="page">
        <div id="page-bgtop">
            <ul class="topnav" id=myTopnav">
                <li><a href="utente.jsp">HOME</a></li>
                <% if (bl.controlloAccesso()){ %>
                    <li><a href="areaViaggiatore.jsp">Area Viaggiatore</a></li>
                    <li><a href="areaProprietario.jsp">Area Proprietario</a></li>
                    <li><a href="profiloUtente.jsp">Visualizza profilo</a></li>
                    <li><a href="posta.jsp">Posta</a></li>
                    <li><a href="areaAiuto.jsp">AIUTO</a></li>
                    <li><a href="logout.jsp">Esci</a></li>
                <% } else {%>
                    <li><a href="areaAiuto.jsp">AIUTO</a></li>

                <% } %>

            </ul>

            <%if (nuovaDomanda.equals("")){ %>


            <div id="page-bgbtm">
                <div id="content_">



                    <div  class="post">

                        <h1>Spiacente, non è possibile inserire domande vuote</h1>

                    </div>


                    <div >

                        <% if(type.equals("0")){ %>
                            <a href="aiutoViaggiatore.jsp">Torna alla pagina precedente</a>
                        <% } else { %>
                            <a href="aiutoProprietario.jsp">Torna alla pagina precedente</a>
                        <% } %>

                    </div>




                    <div style="clear: both;">&nbsp;</div>
                </div>
                <!-- end #content -->
                <div style="clear: both;">&nbsp;</div>
            </div>


            <%} else {


                try {
                    bf.inserisciDomanda(nuovaDomanda, type); //domadaViaggiatore
                } catch (DeserializzazioneException | SerializzazioneException e) {
                    e.printStackTrace();
                }




            %>
            <div id="page-bgbtm">
                <div id="content">



                    <div class="post">

                        <h1>La tua domanda è stata inviata</h1>

                    </div>


                    <div >
                        <% if (type.equals("0")) {%>
                                <a href="aiutoViaggiatore.jsp">Torna alla pagina precedente</a>
                        <% } else { %>
                                <a href="aiutoProprietario.jsp">Torna alla pagina precedente</a>
                        <% } %>

                    </div>




                    <div style="clear: both;">&nbsp;</div>
                </div>
                <!-- end #content -->
                <div style="clear: both;">&nbsp;</div>
            </div>
            <% } %>
        </div>
    </div>
    <!-- end #page -->
</div>
<div id="footer">

</div>
<!-- end #footer -->
</body>
</html>
