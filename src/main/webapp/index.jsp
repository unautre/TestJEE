<%@ page import="tk.poneycorp.training.MyUtils" %>
<%@ page import="tk.poneycorp.training.data.AuthorBean" %><%--
  Created by IntelliJ IDEA.
  User: unautre
  Date: 22/04/16
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <style type="text/css">
      table {
        border-collapse: collapse;
      }
      td {
        border: 1px solid black;
      }
    </style>
  </head>
  <body><table>
  <tr><td>QueryString:</td><td><%= request.getQueryString() %></td></tr>
  <tr><td>PathInfo: </td><td><%= request.getPathInfo() %></td></tr>
  <% for(String name: MyUtils.iterableFromEnumeration(session.getAttributeNames())){ %>
    <tr><td>Clef: <%= name %></td><td>Valeur: <%= session.getAttribute(name) %></td></tr>
  <% } %>
  </table>
  <script type="text/javascript">
    function addRow(nick, msg){
      table = document.querySelector("table[id=messageHolder]");
      table.innerHTML += "<tr><td>" + nick + "</td><td>" + msg + "</td></tr>";
    }
    function refreshMessages() {
      xhr = new XMLHttpRequest();
      xhr.open("GET", "api", false);
      xhr.send(null);
      data = xhr.responseXML;
    }
  </script>
  <input type="button" value="Refresh..." onclick="refreshMessages();" />
  <table id="messageHolder">
    <tr><td>Nom</td><td>Message</td></tr>
  </table>
  <% AuthorBean author = (AuthorBean)(session.getAttribute("author")); %>
  <% if(session.getAttribute("author") == null){ %>
    <form action="registerservlet" method="post">
      <input type="text" value="Votre nom" name="name" />
      <input type="password" name="pass" />
      <input type="checkbox" name="connexion" value="true" />
      <input type="submit" value="Envoyer" />
    </form>
  <% }else{ %>
    <p>Votre nom est <%= ((AuthorBean)session.getAttribute("author")).getNickname() %></p>
    <script type="text/javascript">
      function sendMessage(form){
        msg = form.querySelector("input[name=message]").value
        xhr = new XMLHttpRequest();
        xhr.open("POST", "api/message", false);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send("message=" + encodeURIComponent(msg));
      }
    </script>
    <form id="sendMessage" onsubmit="sendMessage(this); return false"><% /* TODO: use XHR and JAX-RS to post/fetch messages */ %>
      <input type="text" value="Votre message" name="message" />
      <input type="submit" value="Envoyer" />
    </form>
  <% } %>
  </body>
</html>