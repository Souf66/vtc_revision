package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VehiculeDao;
import model.VehiculeM;

/**
 * Servlet implementation class Vehicule
 */
@WebServlet("/createVehi")
public class Vehicule extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//En Global
	VehiculeDao vehi = new VehiculeDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Vehicule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getServletPath());
		String action = request.getServletPath();
		
		switch (action) {
			case "/createVehi":
				instertVehicule(request, response);
	        break;
	        
			case "/editVehi":
				 editVehicule(request, response);
	             break;
			case "/updateVehi":
				updateVehicule(request, response);
	             break;
			default:
			    listing(request, response);
			    break;
			}
		//}
		// J'appelle ConducteurDao pour avoir la liste
		request.setAttribute("vehicules", vehi.read());
		
	// Ici, je crée la vue: page qui va etre appeller
	request.getRequestDispatcher("/vehicule/vehicule.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void listing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setAttribute("vehicules", vehi.read());
		
	}
	
	public void instertVehicule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String marque = request.getParameter("marque");
		String modele = request.getParameter("modele");
		String immatriculation = request.getParameter("immatriculation");
		String couleur = request.getParameter("couleur");
		
		VehiculeM vehicule = new VehiculeM(marque,modele,immatriculation,couleur);
				
		request.setAttribute("ajout", vehi.create(vehicule));
	}
	
	//Je prepare ma methode edit
	public void editVehicule(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		
		try {
			request.setAttribute("vehicules", vehi.read());
			
			request.setAttribute("vehicule", vehi.findById(id));
			request.getRequestDispatcher("vehicule/vehicule.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateVehicule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ksjdkhsdkj");
        int id = Integer.parseInt(request.getParameter("id"));
        String marque = request.getParameter("marque");
        String modele = request.getParameter("modele");
        String couleur = request.getParameter("couleur");
        String immat = request.getParameter("immatriculation");
        
        VehiculeM vehic = new VehiculeM(id,marque,modele,couleur,immat);
        
        request.setAttribute("editVehi",  vehi.editVehi(vehic));
        request.setAttribute("marque", marque);
		request.setAttribute("modele", modele);
		
		
    }
	
	public void deleteVeh(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String marque = request.getParameter("marque");
        String modele = request.getParameter("modele");
        String couleur = request.getParameter("couleur");
        String immat = request.getParameter("immatriculation");
        
        VehiculeM vehic = new VehiculeM(id,marque,modele,couleur,immat);
        
		//VehiculeM deleteVe = new VehiculeM();
		
		request.setAttribute("deleteV", vehi.deleteVehi(vehic));
		request.getRequestDispatcher("vehicule/vehicule.jsp").forward(request, response);
	}
}
