
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
    <link href="http://127.0.0.1:8887/css/style.css" rel="stylesheet" type="text/css" media="screen" />
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
                <h1><a href="#">AREA AIUTO</a></h1>

            </div>
        </div>
    </div>
    <!-- end #header -->
    <div id="page">
        <div id="page-bgtop">
            <ul class="topnav" id=myTopnav">

                <% if (bl.controlloAccesso()){ %>
                    <li><a href="utente.jsp">HOME</a></li>
                    <li><a href="areaViaggiatore.jsp">Area Viaggiatore</a></li>
                    <li><a href="areaProprietario.jsp">Area Proprietario</a></li>
                    <li><a href="profiloUtente.jsp">Visualizza profilo</a></li>
                    <li><a href="posta.jsp">Posta</a></li>
                    <li><a href="areaAiuto.jsp">AIUTO</a></li>
                    <li><a href="logout.jsp">Esci</a></li>
                <% } else {%>
                    <li><a href="index.jsp">HOME</a></li>
                    <li><a href="areaAiuto.jsp">AIUTO</a></li>
                <%} %>


            </ul>
            <div id="page-bgbtm">
                <div id="content">


                    <div class="post">
                        <h2><strong>Scegli la sezione di interesse</strong></h2>

                    </div>
                    <div class="post">
                        <table style="margin-left: 350px">
                            <tr>
                                <td><h1>Viaggiatore</h1></td>
                                <td><h1>Proprietario</h1></td>
                            </tr>
                            <tr>
                                <td style="padding-right: 100px"><h2 class="title"><a href="aiutoViaggiatore.jsp"><img src="http://127.0.0.1:8887/css/images/valigia.jpg" width="200" height="200" alt="domandaUtente cliente"/></a></h2></td>
                                <td style="margin-left: 50px"><h2 class="title"><a href="aiutoProprietario.jsp"><img src="http://127.0.0.1:8887/css/images/proprietario.png" width="200" height="200" alt="domandaUtente proprietario"/></a></h2></td>
                            </tr>



                        </table>
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
<div id="footer">

</div>
<!-- end #footer -->
</body>
</html>

