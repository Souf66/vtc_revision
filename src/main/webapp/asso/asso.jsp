<c:import url="/header.jsp"></c:import>


<c:if test="${ajout}">
	<div id="msg">
		<div class="alert alert-success" role="alert" >
		  L'asso a été ajouté.
		</div>
	</div>
</c:if>
 <div class=container>
 
	 	<c:if test="${!empty listeAssos }">
	 		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">id_association</th>
		      <th scope="col">conducteur</th>
		      <th scope="col">véhicule</th>
		      <th scope="col">Modification</th>
		      <th scope="col">Supprimer</th>
		    </tr>
		  </thead>
		  <tbody>
		  <c:forEach items="${listeAssos }" var="asso">
		  <tr>
		  	<td> <c:out value="${asso.id_association }"></c:out> </td>
		  	<td> <c:out value="${asso.conducteur.prenom } ${asso.conducteur.nom }" />
		  	 </td>
		  	<td> <c:out value="${asso.vehicule.marque } --- ${asso.vehicule.modele } "></c:out> 
		  	<c:out value="${asso.vehicule.id }"></c:out> </td>
		  	<td>
	      	<a href="editAsso?id=<c:out  value="${asso.id_association }"/>"> edit</a>
	      </td>
	      <td  data-bs-toggle="modal" data-bs-target="#idAsso-<c:out  value="${asso.id_association }" />">
				<img alt="" src="images/delete.png" style="width: 40px; height: 40px">
			</td>
			</tr>
			
			<!-- Modal -->
				<div class="modal fade" id="idAsso-<c:out value="${asso.id_association }" />" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  				<div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				      	Voulez vous vraiment supprimer l'asso
				        <b><c:out value="${asso.conducteur.prenom } ${asso.conducteur.nom }" /> -
				        <c:out value="${asso.vehicule.marque } --- ${asso.vehicule.modele } "></c:out>
				        </b> ?
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Non</button>
				       
				        <a href="deleteAsso?id=<c:out value="${asso.id_association }" />">
				        	<button type="button" class="btn btn-success">Oui, je confirme</button>
				        </a>
				      </div>
				    </div>
				  </div>
				</div>
		  </c:forEach>
		  </tbody>
		</table>
	 	</c:if>
		
 	 <c:if test="${asso != null}">
            <form action="<% request.getContextPath(); %>update" method="post">
        </c:if>
        
        <c:if test="${asso == null}">
			<form method="post" action="<% request.getContextPath(); %>createAsso">
        </c:if>
        
         <c:if test="${asso != null}">
             <input type="hidden" name="id" size="45" value="<c:out value="${vehicule.id}" />"/>
         </c:if>
	 	<div class="form-group col-md-4">
	      <label for="inputState">Conducteur</label>
	      
	      <select id="inputState" class="form-control" name="id_conducteur">
	      	<option selected>Choose...</option>
	      	<c:forEach items="${conducteurs }" var="user">
	      		  <option value="<c:out value="${user.id }" />"> <c:out value="${user.prenom } ${user.nom }" /></option>
	      	</c:forEach>
	      </select>
	    </div>
	
		<div class="form-group col-md-4">
	      <label for="inputState">Véhicule</label>
	      <select id="inputState" class="form-control" name="id_vehicule">
	        <option selected>Choose...</option>
	        <c:forEach items="${vehicules }" var="vehicule">
	      		  <option value="<c:out value="${vehicule.id }" />"><c:out value="${vehicule.marque } - ${vehicule.immatriculation }" /></option>
	      	</c:forEach>
	      </select>
	    </div>
	
	  <button type="submit" class="btn btn-primary" name="submit">Ajouter cette association</button>
 	</form>
 </div>
 
 <c:import url="/footer.jsp"></c:import>