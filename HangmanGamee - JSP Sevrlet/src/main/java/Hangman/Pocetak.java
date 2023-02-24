package hangman;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Random;
import java.util.ArrayList;
@WebServlet(name = "Init", urlPatterns = {"/Init"})

public class Pocetak extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {   
        HttpSession mySession = request.getSession();
        ArrayList<String> pogresnaSlova = new ArrayList<String>();
        int pokusajMax = 6;
        int pokusaj = 1;
        String rec = "";
        String skrivenaRec = "";
        String[] niz = new String[]{
            "Jabuka","Kruska", "Kajsija"          
        };

        rec = niz[new Random().nextInt(niz.length)];              
        for(int i = 0; i < rec.length(); i++){
            skrivenaRec += "*";
        }
        
        mySession.setAttribute("pogresnaSlova", pogresnaSlova);
        mySession.setAttribute("skrivenaRec", skrivenaRec);
        mySession.setAttribute("rec", rec);
        mySession.setAttribute("pokusaj", pokusaj);
        mySession.setAttribute("pokusajMax", pokusajMax);
        mySession.setAttribute("rezultat", "neutralno");
        response.sendRedirect("Hangman.jsp");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Nista";
    }
}
