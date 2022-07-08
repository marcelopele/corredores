<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>Listado de Corredores:</h3>

<c:forEach items="${cardsCorredores}" var="corredor">  
                        
    <div class="col">
        <div class="card">
            
            <c:choose>
                <c:when test="${empty corredor.img_corredor}">
                    <img src="img/corredor-abstracto.jpg" alt="${corredor.nombreCompleto}" />
                </c:when>
                <c:otherwise>
                    <img src="${corredor.img_corredor}" alt="${corredor.nombreCompleto}" />
                </c:otherwise>
            </c:choose>
            
            
            <div class="card-body">
                <h4 class="card-title">${corredor.nombreCompleto}</h4>
                <p>Carreras: ${corredor.q_carreras}</p>
                <p>Velocidad Promedio: ${corredor.vel_promedio}Km/h</p>
                <button type="button" class="btn btn-link" data-bs-toggle="modal" data-bs-target="#modal_Carrerasb" onClick="getCarreras('${pageContext.request.contextPath}/app',${corredor.id_corredor})">Lista de carreras</button>
            </div>

            <div class="card-footer border-top-0">
                <div class="row">
                    <div class="col-6">
                        <button type="button" class="btn btn-outline-warning btn-block w-100" data-bs-toggle="modal" data-bs-target="#updCorredor${corredor.id_corredor}">Modificar</button>
                        <div class="modal" id="updCorredor${corredor.id_corredor}">
                          <div class="modal-dialog">
                            <div class="modal-content">

                                <!-- Modal Header -->
                                <div class="modal-header">
                                  <h4 class="modal-title">Modificar corredor</h4>
                                  <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                </div>

                                <form id="formUpdCorredor" action="${pageContext.request.contextPath}/app" method="post">
                                    <!-- Modal body -->
                                    <div class="modal-body">
                                        <label for="nom_corredor" class="form-label">Nombre</label>
                                        <input type="text" class="form-control" id="nom_corredor" name="nom_corredor" required value="${corredor.nom_corredor}">
                                        <label for="lname_user" class="form-label">Apellido</label>
                                        <input type="text" class="form-control" name="ape_corredor" id="ape_corredor" required value="${corredor.ape_corredor}">

                                        <label for="input_img_corredor" class="form-label">Foto de perfil</label>
                                        
                                        <c:choose>
                                            <c:when test="${empty corredor.img_corredor}">
                                                <img src="img/corredor-abstracto.jpg" alt="${corredor.nombreCompleto}" id="img_corredor2_${corredor.id_corredor}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <img src="${corredor.img_corredor}" alt="${corredor.nombreCompleto}" id="img_corredor2_${corredor.id_corredor}"/>
                                            </c:otherwise>
                                        </c:choose>

                                        <input type="file" class="form-control" id="input_img_corredor${corredor.id_corredor}" name="input_img_corredor" onChange="impFoto(${corredor.id_corredor})">
                                        <input type="hidden" id="img_corredor${corredor.id_corredor}" name="img_corredor" value="${corredor.img_corredor}">

                                        <input type="hidden" name="id_corredor" id="id_corredor" value="${corredor.id_corredor}">
                                        <input type="hidden" name="accion" id="accion" value="updCorredor">
                                    </div>

                                    <!-- Modal footer -->
                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-success">Confirmar</button>
                                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cerrar</button>
                                    </div>
                                </form>

                            </div>
                          </div>
                        </div>
                        
                    </div>
                    <div class="col-6">
                        <button type="button" class="btn btn-outline-danger btn-block w-100" data-bs-toggle="modal" data-bs-target="#delCorredor${corredor.id_corredor}">Borrar</button>
                        <div class="modal" id="delCorredor${corredor.id_corredor}">
                          <div class="modal-dialog">
                            <div class="modal-content">

                                <!-- Modal Header -->
                                <div class="modal-header">
                                  <h4 class="modal-title">Eliminar corredor</h4>
                                  <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                </div>

                                <form id="formUpdCorredor" action="${pageContext.request.contextPath}/app" method="post">
                                    <!-- Modal body -->
                                    <div class="modal-body">
                                        <label for="nom_corredor" class="form-label">Nombre</label>
                                        <input type="text" class="form-control" id="nom_corredor" name="nom_corredor" required value="${corredor.nom_corredor}" readonly>
                                        <label for="lname_user" class="form-label">Apellido</label>
                                        <input type="text" class="form-control" name="ape_corredor" id="ape_corredor" required" value="${corredor.ape_corredor}" readonly>

                                        <input type="hidden" name="id_corredor" id="id_corredor" value="${corredor.id_corredor}">
                                        <input type="hidden" name="accion" id="accion" value="delCorredor">
                                    </div>

                                    <!-- Modal footer -->
                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-success">Confirmar</button>
                                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cerrar</button>
                                    </div>
                                </form>

                            </div>
                          </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
                        
</c:forEach>

