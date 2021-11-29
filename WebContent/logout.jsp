
<%@ page import="control.ControlloreLingua" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="bl" scope="session" class="boundary.BoundaryLogin"/>
<jsp:useBean id="blingua" scope="session" class="boundary.BoundaryLingua"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

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
    <script type="text/javascript" src="http://127.0.0.1:8887/js/functions.js"></script>
</head>

<body>

<%
	String username = bl.ritornaUsername();
	Locale locale = blingua
            .riceviLingua(username);
    ResourceBundle bundle = ControlloreLingua
            .getBundle(locale);

    bl.logout();
%>

<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<div id="logo"></div>
		</div>
	</div>
</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div id="content">

					<div class="post">
						<h2 class="title"><%=bundle.getString("logout_effettuato")%></h2>
						<br /><br /><br />
						<h2 class="title">
                            <a href="index.jsp">
                                <font color="#4b7091"><%=bundle.getString("logout_home")%></font>
                            </a>
                        </h2>
				    </div>
				    <!-- end #content -->
					<br/>

		        </div>
	        </div>

	        <!-- end #page -->
        </div>

        <div id="footer"></div>
        <!-- end #footer -->
	
    </div>
<!-- end #footer -->
</body>
</html>