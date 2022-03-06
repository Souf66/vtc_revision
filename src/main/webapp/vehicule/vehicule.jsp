<c:import url="/header.jsp"></c:import>
<c:if test="${rrt}">
	<div id="msg">
		<div class="alert alert-success" role="alert" >
		  Le vehicule a été ajouté.
		</div>
	</div>
</c:if>

<div class=container>
	 <c:if test="${empty vehicules}">
	 	<div class="alert alert-info" role="alert">
		 	Pas de vehicules pour le moment 
		</div>
	 </c:if>
	 	<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">id_vehicule</th>
		      <th scope="col">Marque</th>
		      <th scope="col">Modéle</th>
		       <th scope="col">Couleur</th>
		        <th scope="col">Immatriculation</th>
		      <th scope="col">Modification</th>
		      <th scope="col">Supprimer</th>
		    </tr>
		  </thead>
		  <tbody>
		  <c:forEach items="${vehicules }" var="vehicule">
		  <tr>
		  	<td> <c:out value="50${vehicule.id }"></c:out> </td>
		  	<td> <c:out value="${vehicule.marque }"></c:out> </td>
			<td> <c:out value="${vehicule.modele }"></c:out> </td>
			<td> <c:out value="${vehicule.couleur }"></c:out> </td>
			<td> <c:out value="${vehicule.immatriculation }"></c:out> </td>
			<td>
	      	<a href="editVehi?id=<c:out value="${vehicule.id }"/>"> edit</a>
	      </td>
	      <td  data-bs-toggle="modal" data-bs-target="#idVehi-<c:out value="${vehicule.id }" />">
				<img alt="" src="images/delete.png" style="width: 40px; height: 40px">
			</td>
			
			</tr>
			 <!-- Modal -->
				<div class="modal fade" id="idVehi-<c:out value="${vehicule.id }" />" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  				<div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				      	Voulez vous vraiment supprimer le vehicule
				        <b><c:out value="${vehicule.marque } ${vehicule.modele }" /></b> ?
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Non</button>
				       
				        <a href="deleteVehi?id=<c:out value="${vehicule.id }" />">
				        	<button type="button" class="btn btn-success">Oui, je confirme</button>
				        </a>
				      </div>
				    </div>
				  </div>
				</div>
		  </c:forEach>
		  </tbody>
		</table>
	 
	 <%-- AJOUT USER --%>
 	<!-- 
			Je garde le meme formulaire  
		 -->
		 <c:if test="${vehicule != null}">
            <form action="<% request.getContextPath(); %>updateVehi" method="post">
        </c:if>
        
        <c:if test="${vehicule == null}">
			<form method="post" action="<% request.getContextPath(); %>createVehi">
        </c:if>
        
         <c:if test="${vehicule != null}">
             <input type="hidden" name="id" size="45" value="<c:out value="${vehicule.id}" />"/>
         </c:if>
		  <div class="form-group">
	    <label>Marque</label>
	    <input type="text" class="form-control" name="marque" required placeholder="Marque" value="<c:out value="${vehicule.marque}" />"
                        /> 
	  </div>
	
	  <div class="form-group">
	    <label>Modéle</label>
	    <input type="text" class="form-control" name="modele" required placeholder="Modéle" value="<c:out value="${vehicule.modele}" />"
                        /> 
	  </div>
	
	  <div class="form-group">
	    <label>Couleur</label>
	    <input type="text" class="form-control" name="couleur" required value="<c:out value="${vehicule.couleur}" />"
                        /> 
	  </div>
	
	  <div class="form-group">
	    <label>Immatriculation</label>
	    <input type="text" class="form-control" name="immatriculation" required value="<c:out value="${vehicule.immatriculation}" />"
                        /> 
	  </div>
	
	 <c:if test="${vehicule == null}">
	  	<button type="submit" class="btn btn-primary">Ajout ce vehicule</button>
	  </c:if>
	  
	  <c:if test="${vehicule != null}">
	  	<button type="submit" class="btn btn-primary">Modifier ce vehicule</button>
	  </c:if>
	</form>
 
 </div>
<c:import url="/footer.jsp"></c:import>