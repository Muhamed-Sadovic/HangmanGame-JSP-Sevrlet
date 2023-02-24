<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="game">          
            <%if(((String)session.getAttribute("rezultat")).equals("neutralno")){%>           
                <h3>Hangman game</h3>
                <h2>Pokusaji <%=session.getAttribute("pokusaj")%> od <%=session.getAttribute("pokusajMax")%></h2>
                <h3>
                    <b><%=session.getAttribute("skrivenaRec")%></b> 
                    <sub>(<%=((String)session.getAttribute("skrivenaRec")).length()%>)</sub>
                   <p>Pogresna slova: <%=session.getAttribute("pogresnaSlova")%></p>
                </h3><br>
                <form action="Game" method="post">
                    Ukucajte slovo: <input type="text" name="slovo"><br><br>
                    <input type="submit">
                </form>
      
            <%}else if (((String)session.getAttribute("rezultat")).equals("pobeda")){%>
                <h3>Hangman Game</h3>
                <h2>Bravo! Rec je bila: <%=session.getAttribute("rec")%></h2>
            
            <%}else if (((String)session.getAttribute("rezultat")).equals("poraz")){%>           
                <h3>Hangman Game</h3>
                <h2>Izgubili ste! Rec je bila: <%=session.getAttribute("rec")%></h2>
            <%}%>
        </div>
    </body>
</html>

