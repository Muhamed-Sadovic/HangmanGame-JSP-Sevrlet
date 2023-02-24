package hangman;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
@WebServlet(name = "Game", urlPatterns = {"/Game"})

public class Potez extends HttpServlet {
    int pokusaj = 0;
    ArrayList<String> pogresnaSlova = new ArrayList<String>();
    public void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {    
        HttpSession mySession = request.getSession();    
        pokusaj = (int)mySession.getAttribute("pokusaj");
        int pokusajMax = (int)mySession.getAttribute("pokusajMax");
        String rec  = (String)mySession.getAttribute("rec");
        String skrivenaRec = (String)mySession.getAttribute("skrivenaRec");
        String slovo = request.getParameter("slovo");
        String rezultat = "neutralno";
        pogresnaSlova = (ArrayList<String>)mySession.getAttribute("pogresnaSlova");
        
        skrivenaRec = checkStr(rec, skrivenaRec, slovo);
                
        if(pokusaj == pokusajMax+1){
            rezultat = "poraz";
        }      
        if(skrivenaRec.equals(rec)){
            rezultat = "pobeda";
        }   
        mySession.setAttribute("pogresnaSlova", pogresnaSlova);
        mySession.setAttribute("rezultat", rezultat);
        mySession.setAttribute("pokusaj", pokusaj);
        mySession.setAttribute("skrivenaRec", skrivenaRec);
        response.sendRedirect("Hangman.jsp");
    }

    public String checkStr(String rec, String sakriven, String slovo){
        if(slovo.equals("")){
            return sakriven;  
        }       
        if(slovo.length()>1){
            if(slovo.toLowerCase().equals(rec.toLowerCase())){
                return rec;  
            }
        }              
        char[] res = sakriven.toLowerCase().toCharArray();
        char c = slovo.charAt(0);
        boolean found = false;
        for(int i = 0, l = rec.length(); i < l; i++){
            if(rec.toLowerCase().charAt(i) == c){
                res[i] = c;
                found = true;
            }
        }
        if(!found){
            pokusaj++;
            if(pogresnaSlova.size() == 0){
                pogresnaSlova.add(slovo);
            }
            else{
                for(int j=0;j<pogresnaSlova.size();j++){
                    String item = pogresnaSlova.get(j);
                    if(!item.equals(slovo)){
                        pogresnaSlova.add(slovo);
                        break;
                    }
                }
            }

        }
        res[0] = Character.toUpperCase(res[0]);
        return new String(res);
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
