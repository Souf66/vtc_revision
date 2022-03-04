package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ConducteurDao;

/**
 * Servlet implementation class Conducteur
 */
@WebServlet("/") // Chemin d'accés à mon application
public class Conducteur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//En Global
	ConducteurDao conD = new ConducteurDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Conducteur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * lorsque ce que j'appelle laroute dans mon URL, c'est le doGet qui est apellé
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Je get les segments de mon URL pour rester sur la mm servlet
		System.out.println(request.getServletPath());
		String action = request.getServletPath();
		
		switch (action) {
			case "/create":
				 instertConducteur(request, response);
	            break;
			
			case "/edit":
				 edit(request, response);
	             break;
			case "/update":
				update(request, response);
	             break;
			case "/delete":
				delete(request, response);
	             break;
		}
		// J'appelle ConducteurDao pour avoir la liste
		
		request.setAttribute("list", conD.read());
		
		// Ici, je crée la vue: page qui va etre appeller
		request.getRequestDispatcher("/conducteur/ajout.jsp").forward(request, response);
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		
		model.Conducteur  cond = new model.Conducteur(nom,prenom);
		
		
		conD.create(cond);*/
		
		doGet(request, response);
	}
	
	public void instertConducteur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		// Recuperation saisie USER
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		
		model.Conducteur  cond = new model.Conducteur(nom,prenom);
		
		request.setAttribute("ajout", conD.create(cond));
		request.setAttribute("prenom", prenom);
		request.setAttribute("nom", nom);
			
	}
	//Je prepare ma methode edit
	public void edit(HttpServletRequest request, HttpServletResponse response) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		request.setAttribute("conducteur", conD.findById(id));
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ksjdkhsdkj");
		int id = Integer.parseInt(request.getParameter("id"));
		String prenom = request.getParameter("prenom");
        String nom = request.getParameter("nom");
		
		model.Conducteur  cond = new model.Conducteur(id,prenom,nom);
		
        request.setAttribute("editCond",  conD.editConducteur(cond));
        request.setAttribute("prenom", prenom);
		request.setAttribute("nom", nom);
    }
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		model.Conducteur deleteCond = new model.Conducteur(id, request.getParameter("prenom"),request.getParameter("nom"));
		
		request.setAttribute("deleteCond", conD.delete(deleteCond));
		request.setAttribute("prenom", request.getParameter("prenom"));
		request.setAttribute("nom", request.getParameter("nom"));
	}

}
