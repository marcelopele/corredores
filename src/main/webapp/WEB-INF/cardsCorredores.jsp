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
                <a href="">Detalle de carreras</a>
            </div>


            <div class="card-footer border-top-0">
                <div class="row">
                    <div class="col-6">
                        <a href="/app?accion=edit&id=${corredor.id_corredor}" class="btn btn-outline-warning btn-block w-100">Modificar</a>
                    </div>
                    <div class="col-6">
                        <a href="/app?accion=remove&id=${corredor.id_corredor}" class="btn btn-outline-danger btn-block w-100">Borrar</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
                        
</c:forEach>


