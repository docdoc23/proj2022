<%@ page import="boundary.BoundaryAiuto" %>
<%@ page import="exception.DeserializzazioneException" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="bl" scope="session" class="boundary.BoundaryLogin"/>
<html>
<head>
    <title>Aiuto</title>
    <script type="text/javascript" language="javascript">
        function visualizza(id){
            if (document.getElementById){
                if(document.getElementById(id).style.visibility == 'hidden'){
                    document.getElementById(id).style.visibility = 'visible';
                }else{
                    document.getElementById(id).style.visibility = 'hidden';
                }
            }
        }
    </script>

</head>
<style>@import url('http://127.0.0.1:8887/css/style.css');
.bf{
    font-family: 'Lato', sans-serif;
    font-weight:300;
    margin: 0;
    padding: 0;
    background: #fff none;
    font-size: 16px;
    color: #555555;
}
</style>

<link href='http://fonts.googleapis.com/css?family=Lato:400,100,300,700' rel='stylesheet' type='text/css'>
<%
    String type="0"; //domandaViaggiatore
    BoundaryAiuto bf = null;
    try {
        bf = new BoundaryAiuto();
    } catch (DeserializzazioneException e) {
        e.printStackTrace();
    }

    List<String> domande = null;
    try {
        domande = bf.ritornaDomande(type);
    } catch (DeserializzazioneException e) {
        e.printStackTrace();
    }

%>

<body class="bf" >
<div class="faq" id="header" >
    <h1>Viaggiatore</h1>
    <p><a href="areaAiuto.jsp">Torna all'area generale</a></p>

</div>

<div class="content">
    <% if(domande==null) {%>
        <div class="box">

            <div ><p>Al momento in questa sezione non sono presenti domande con annesse risposte</p></div>


        </div>


    <% } else {%>



            <%
                for(int i=0; i<domande.size(); ++i){
            %>
            <div class="box">

                <div id="testo"><a href="#" onclick="visualizza('<%out.print(i);%>')"><%out.println(Integer.toString(i+1)+". "+domande.get(i));%></a></div>
                <div id="<%out.print(i);%>" style="visibility: hidden"><%
                    try {
                        out.println(bf.ritornaRisposta(domande.get(i)));
                    } catch (DeserializzazioneException e) {
                        e.printStackTrace();
                    }
                %></div>

            </div>
            <%
                }
            %>

    <% } %>

    <% if (bl.controlloAccesso()) { %>
                <form action="inserisciDomanda.jsp" method="get" >
                    <input name="nuovaDomanda" type="text" placeholder="Scrivi qui la tua domanda" value=""  size="60" maxlength="200" />
                    <input type="hidden" name="tipo" value="<%out.print(type);%>">
                    <input  class="btn_2" name="inserisciDomanda" type="submit" value="Invia Domanda" style="display: block; margin: 0 auto; width: 170px;"/>

                </form>
    <% } %>



</div><!--content-->

</body >
</html>
