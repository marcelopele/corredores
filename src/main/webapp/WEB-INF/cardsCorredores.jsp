<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>Listado de Corredores:</h3>
<c:forEach items="${cardsCorredores}" var="corredor">  
                        
    <div class="col">
        <div class="card">
            
            <c:choose>
                <c:when test="${corredor.img_corredor!=null}">
                    <img src="img/corredor-abstracto.jpg" alt="${corredor.nom_corredor}" />
                </c:when>
                <c:otherwise>
                    <img src="${corredor.img_corredor}" alt="${corredor.nom_corredor}" />
                </c:otherwise>
            </c:choose>
            
            
            <div class="card-body">
                <h4 class="card-title">${corredor.nom_corredor}</h4>
                <p>Carreras: xx</p>
                <p>Velocidad Promedio: yyKm/h</p>
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


