<%@ page import="com.news.QLLTC.Utils" %><%--
  Created by IntelliJ IDEA.
  User: Sown
  Date: 12/16/2023
  Time: 1:21 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    var utils = new Utils();
%>
<t:main-layout>
    <jsp:body>

        <body>
        <div class="container">
            <div class="navbar">
                <a href="#">Host a home</a>
                <a href="#">Host an experience</a>
                <a href="#">Help</a>
                <a href="#" class="login">Log In</a>
            </div>
            <div class="main">
                <div class="content">
                    <h1>Plan your next trip</h1>
                    <div class="search">
                        <input type="text" value="Surfing in Los Angeles" />
                        <select>
                            <option>mm/dd/yyyy</option>
                        </select>
                        <select>
                            <option>1 guest</option>
                        </select>
                        <button>Search</button>
                    </div>
                </div>
            </div>
        </div>
        </body>
    </jsp:body>
</t:main-layout>