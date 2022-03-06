package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AssoDao;
import dao.ConducteurDao;
import dao.VehiculeDao;
import model.AssoM;
import model.Conducteur;
import model.VehiculeM;

/**
 * Servlet implementation class Asso
 */
@WebServlet("/asso")
public class Asso extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ConducteurDao conD = new ConducteurDao();
	
	VehiculeDao vehiDao = new VehiculeDao();
	AssoDao assoDao = new AssoDao();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Asso() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recuperation de la liste des conducteurs pour les afficher dans le select du formulaire
		
				request.setAttribute("conducteurs", conD.read());
				
				// Recuperation de la liste des vehicules
				
				request.setAttribute("vehicules", vehiDao.read());
		
		System.out.println(request.getServletPath());
		String action = request.getServletPath();
		
		switch (action) {
			case "/createAsso":
				instertAsso(request, response);
	        break;
			case "/editAsso":
				editAsso(request, response);
	        break;
			
			}
		//}
		// J'appelle ConducteurDao pour avoir la liste
		request.setAttribute("listeAssos", assoDao.read());
		
	// Ici, je crée la vue: page qui va etre appeller
	request.getRequestDispatcher("/asso/asso.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void instertAsso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int conducteur = Integer.parseInt(request.getParameter("id_conducteur"));
		int vehicule = Integer.parseInt(request.getParameter("id_vehicule"));
		
		// CREATTION ASSO
		Conducteur user = new Conducteur();
		VehiculeM v = new VehiculeM();
		
		user.setId(conducteur);
		v.setId(vehicule);
		
		AssoM asso = new AssoM(user, v);
		
		request.setAttribute("ajout", assoDao.create(asso));
		// FIN CREATION
		
		// J'appelle ConducteurDao pour avoir la liste
				request.setAttribute("listeAssos", assoDao.read());
				
			// Ici, je crée la vue: page qui va etre appeller
			request.getRequestDispatcher("/asso/asso.jsp").forward(request, response);
	}
	
	//Je prepare ma methode edit
		public void editAsso(HttpServletRequest request, HttpServletResponse response) {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println(id);
			request.setAttribute("listeAssos", assoDao.read());
			request.setAttribute("assos", assoDao.findById(id));
			try {
				request.getRequestDispatcher("asso/asso.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void updateAsso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        System.out.println("ksjdkhsdkj");
	        int id = Integer.parseInt(request.getParameter("id"));
	       
			
	    }
		
		public void deleteAsso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));

			request.setAttribute("listeAssos", assoDao.read());
			request.setAttribute("deleteA", assoDao.deleteAsso(id));
			request.getRequestDispatcher("/asso/asso.jsp").forward(request, response);
		}

}
