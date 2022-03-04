<c:import url="/header.jsp"></c:import>

<c:if test="${ajout}">
	<div id="msg">
		<div class="alert alert-success" role="alert" >
		  Le conducteur <i> <c:out value="${prenom } - ${nom }"/>  </i> a été ajouté.
		</div>
	</div>
</c:if>

<c:if test="${editCond}">
	<div id="msg">
		<div class="alert alert-success" role="alert" >
		  Le conducteur <i> <c:out value="${prenom } - ${nom }"/>  </i> a été modifié.
		</div>
	</div>
</c:if>

<c:if test="${deleteCond}">
	<div id="msg">
		<div class="alert alert-success" role="alert" >
		  Le conducteur <i> <c:out value="${prenom } - ${nom }"/>  </i> a été supprimé.
		</div>
	</div>
</c:if>

	<div class="container">
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">id_conducteur</th>
		      <th scope="col">Prenom</th>
		      <th scope="col">Nom</th>
		      <th scope="col">Modification</th>
		      <th scope="col">Suppression</th>
		    </tr>
		  </thead>
		  <tbody>
		   <c:forEach items="${list }" var="conducteur">
			    <tr>
			      <th scope="row"> <c:out value="${conducteur.id }"/></th>
			      <td> <c:out value="${conducteur.prenom }"/> </td>
			      <td> <c:out value="${conducteur.nom }"/> </td>
			      <td>
			      	<a href="edit?id=<c:out value="${conducteur.id }"/>"> edit</a>
			      </td>
			       
				<td  data-bs-toggle="modal" data-bs-target="#idConducteur-<c:out value="${conducteur.id }" />">
					<img alt="" src="images/delete.png" style="width: 40px; height: 40px">
				</td>
				
			    </tr>
			    
			    <!-- Modal -->
				<div class="modal fade" id="idConducteur-<c:out value="${conducteur.id }" />" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  				<div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				      	Voulez vous vraiment supprimer le conducteur
				        <b><c:out value="${conducteur.prenom } ${conducteur.nom }" /></b> ?
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Non</button>
				       
				        <a href="delete?id=<c:out value="${conducteur.id }" />">
				        	<button type="button" class="btn btn-success">Oui, je confirme</button>
				        </a>
				      </div>
				    </div>
				  </div>
				</div>
		   </c:forEach>
		  </tbody>
		</table>
		<hr>
		
		<!-- 
			Je garde le meme formulaire  
		 -->
		 <c:if test="${conducteur != null}">
            <form action="<% request.getContextPath(); %>update" method="post">
        </c:if>
        
        <c:if test="${article == null}">
			<form method="post" action="<% request.getContextPath(); %>create">
        </c:if>
        
         <c:if test="${conducteur != null}">
             <input type="hidden" name="id" size="45" value="<c:out value="${conducteur.id}" />"/> 
         </c:if>
                 
		  <div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">Prénom</label>
		    <input type="text" class="form-control" name="prenom" value="<c:out value="${conducteur.prenom}" />"
                        /> 
		  </div>
		  
		  <div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">Nom</label>
		    <input type="text" class="form-control" name="nom" value="<c:out value="${conducteur.nom}" />" /> 
		  </div>
		  
		  <c:if test="${conducteur == null}">
		  	<button type="submit" class="btn btn-primary">Ajout ce conducteur</button>
		  </c:if>
		  
		  <c:if test="${conducteur != null}">
		  	<button type="submit" class="btn btn-primary">Modifier ce conducteur</button>
		  </c:if>
		</form>
	</div>

<c:import url="/footer.jsp"></c:import>