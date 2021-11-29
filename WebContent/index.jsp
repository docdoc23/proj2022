
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="constants.Constants" %>
<%@ page import="exception.DeserializzazioneException" %>
<%@ page import="exception.SerializzazioneException" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<jsp:useBean id="br" scope="session" class="boundary.BoundaryRegistrazione"/>
<jsp:useBean id="blingua" scope="session" class="boundary.BoundaryLingua"/>

<%
    String lang = request.getParameter("lang");
    Locale selectedLang;
    if (lang != null) {
        selectedLang = blingua.riceviLocaleDaString(lang);
    } else {
        selectedLang = blingua.riceviLinguaDefault();
        lang = blingua.riceviStringDaLocale(selectedLang);
    }

    ResourceBundle bundle = blingua
            .riceviBundle(selectedLang);
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title> Perfect Places </title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>
    <link href="http://127.0.0.1:8887/css/style.css" rel="stylesheet" type="text/css" media="screen" />
    <script type="text/javascript" src="http://127.0.0.1:8887/js/functions.js"></script>
</head>

<body>
<div id="logo">
    <img src="http://127.0.0.1:8887/css/images/logo.png" alt="Logo"/>
</div>

<div class="language">
    <table>
        <tr>
            <td>
                <strong>
                    <label for="select"> <%=bundle.getString("index_lingua")%> </label>
                </strong>
            </td>
            <td>

                <%
                    if (lang != null) {
                        if (lang.equals(Constants.EN)) {
                %>

                            <select id="select" class="btn" onchange="changeLang(this)">
                                <option value="<%=Constants.EN%>"><%=bundle.getString("index_inglese")%></option>
                                <option value="<%=Constants.IT%>"><%=bundle.getString("index_italiano")%></option>
                            </select>

                    <%
                        } else if (lang.equals(Constants.IT)) {
                    %>

                            <select id="select" class="btn" onchange="changeLang(this)">
                                <option value="<%=Constants.IT%>"><%=bundle.getString("index_italiano")%></option>
                                <option value="<%=Constants.EN%>"><%=bundle.getString("index_inglese")%></option>
                            </select>

                <%
                        }
                    } else {
                %>

                        <select id="select" class="btn" onchange="changeLang(this)">
                            <option value="<%=Constants.LANG_DEFAULT%>"><%=bundle.getString("index_italiano")%></option>
                            <option value="<%=Constants.EN%>"><%=bundle.getString("index_inglese")%></option>
                        </select>

                <%
                    }
                %>

            </td>
        </tr>
    </table>
</div>

<div id="page">
    <table id="table_main">
        <tr>
            <td>
                <p id="p_1"> <%=bundle.getString("index_accoglienza")%> </p>
            </td>
        </tr>
    </table>

    <div id="page-bgtop">
        <div id="page-bgbtm">
            <!-- end #content -->
            <table > 
                <tr >

                    <td  id="login">

                        <%
                            String paramLog = request.getParameter("errLog");
                            if (paramLog != null && paramLog.equals("1")) {
                        %>

                            <h4 class="main"> <font color="red"> <%=bundle.getString("index_erroreLogin")%> </font> </h4>

                        <% } %>

                        <h2 class="main"> <strong> <%=bundle.getString("index_accedi")%> </strong> </h2>
                        <form action="utente.jsp?lang=<%=lang%>" enctype="application/x-www-form-urlencoded" method="post">
                            <table>
                                <tr>
                                    <td>
                                        <label for="username"> <%=bundle.getString("index_nomeUtente")%> </label>
                                    </td>
                                    <td>
                                        <input class="btn" type="text" name="username" id="username"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="password"> <%=bundle.getString("index_password")%> </label>
                                    </td>
                                    <td>
                                        <input class="btn" type="password" name="password" id="password"/>
                                    </td>
                                </tr>
                            </table>
                            <br />
                            <input class="btn_2" type="submit" value="<%=bundle.getString("index_accedi")%>" /><br /><br />
                            <br />
                        </form>
                    </td>

                    <td >

                        <%
                            String paramReg = request.getParameter("Reg");
                            if (paramReg != null && paramReg.equals("1")) {
                                String un = request.getParameter("username_2");
                                String pw = request.getParameter("password");
                                String pw2 = request.getParameter("password2");
                                String nome = request.getParameter("nome");
                                String cognome = request.getParameter("cognome");
                                String email = request.getParameter("email");

                                int caseReg = 0;
                                try {
                                    caseReg = br.registrazione(un, pw, pw2, nome,
                                            cognome, email, selectedLang, null, null);
                                } catch (DeserializzazioneException | SerializzazioneException e) {
                                    e.printStackTrace();
                                }
                                switch (caseReg) {
                                    case 0:
                        %>

                                        <h4 class="main"> <font color="green"> <%=bundle.getString("index_registrazioneEffettuata")%> </font> </h4>

                        <%
                                        break;

                                    case 1:
                        %>

                                        <h4 class="main"> <font color="red"> <%=bundle.getString("index_campiVuoti")%> </font> </h4>

                        <%
                                        break;

                                    case 2:
                        %>

                                        <h4 class="main"> <font color="red"> <%=bundle.getString("index_passwordDiverse")%> </font> </h4>

                        <%
                                        break;

                                    case 3:
                        %>

                                        <h4 class="main"> <font color="red"> <%=bundle.getString("index_usernameUtilizzato")%> </font> </h4>

                        <%
                                        break;
                                }
                            }
                        %>

                        <h2 class="main"><strong><%=bundle.getString("index_registrazione")%></strong></h2>
                        <form action="index.jsp?Reg=1&lang=<%=lang%>" enctype="application/x-www-form-urlencoded" method="post">
                            <table>
                                <tr>
                                    <td>
                                        <label for="username_2"> <%=bundle.getString("index_nomeUtente")%> </label>
                                    </td>
                                    <td>
                                        <input class="btn" type="text" name="username_2" id="username_2" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="password_2"> <%=bundle.getString("index_password")%> </label>
                                    </td>
                                    <td><input class="btn" type="password" name="password" id="password_2"/></td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="conf_psw"> <%=bundle.getString("index_confermaPsw")%> </label>
                                    </td>
                                    <td>
                                        <input class="btn" type="password" name="password2" id="conf_psw"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="nome"> <%=bundle.getString("index_nome")%> </label>
                                    </td>
                                    <td>
                                        <input class="btn" type="text" name="nome" id="nome"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="cognome"> <%=bundle.getString("index_cognome")%> </label>
                                    </td>
                                    <td>
                                        <input class="btn" type="text" name="cognome" id="cognome"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="email"> <%=bundle.getString("index_email")%> </label>
                                    </td>
                                    <td>
                                        <input class="btn" type="text" name="email" id="email"/>
                                    </td>
                                </tr>
                            </table>
                            <br />
                            <input class="btn_2" type="submit" value="<%=bundle.getString("index_registrati")%>" />
                            <br />
                        </form>
                    </td>
                </tr>
            </table>
            <!-- end #bar -->
            <!-- to keep space -->
            <div style="clear: both;">&nbsp;</div>
        </div>
    </div>
</div>
<!-- end #page -->
<div id="footer">
    <p ><a href="areaAiuto.jsp" style="font-size: large"><%=bundle.getString("index_aiuto")%></a></p>
</div>
<!-- end #footer -->
</body>
</html>
