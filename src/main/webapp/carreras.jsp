<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal-header">
  <h4 class="modal-title">Lista de carreras de ${cardCorredor.nombreCompleto}</h4>
</div>

<div class="modal-body">

            <div class="row">
                <div class="col-1">
                    <input type="text" value="id" readonly style="width: 100%; text-align: center; padding: 0; margin: 0; font-weight: bold; border:hidden;">
                </div>
                <div class="col-4">
                    <input type="text" value="Título" readonly style="width: 100%; text-align: center; padding: 0; margin: 0; font-weight: bold; border:hidden;">
                </div>
                <div class="col-2">
                    <input type="text" value="Fecha" readonly style="width: 100%; text-align: center; padding: 0; margin: 0; font-weight: bold; border:hidden;">
                </div>
                <div class="col-2">
                    <input type="text" value="Km" readonly style="width: 100%; text-align: center; padding: 0; margin: 0; font-weight: bold; border:hidden;">
                </div>
                <div class="col-2">
                    <input type="text" value="Tiempo (m)" readonly style="width: 100%; text-align: center; padding: 0; margin: 0; font-weight: bold; border:hidden;">
                </div>
            </div>
            
    <div id="lista_del_corredor_${cardCorredor.id_corredor}" class="lc_i">
            <c:forEach items="${listCarreras}" var="carrera">  
            <div class="row lc_i" id="lista_de_carreras_${carrera.id_carrera}">
                <div class="col-1">
                    <input type="number" value="${carrera.id_carrera}" readonly disabled" id="id_carrera_${carrera.id_carrera}">
                </div>
                <div class="col-4">
                    <input type="text" value="${carrera.tit_carrera}" id="tit_carrera_${carrera.id_carrera}">
                </div>
                <div class="col-2">
                    <input type="date" value="${carrera.fh_carrera}" id="fh_carrera_${carrera.id_carrera}">
                </div>
                <div class="col-2">
                    <input type="number" value="${carrera.km}" id="km_${carrera.id_carrera}">
                </div>
                <div class="col-2">
                    <input type="number" value="${carrera.min}" id="min_${carrera.id_carrera}">
                </div>
                <div class="col-1">
                    <img class="btn_in" src="img/but_edit.png" alt="Modificar" onclick="iniciarModif_Fila_Carrera(${cardCorredor.id_corredor}, ${carrera.id_carrera},'${pageContext.request.contextPath}/app')"/>    <!-- que se pinte de amarillo la fila, desbloquear inputs y cambiar íconos a cancelar/confirmar -->
                    <img class="btn_in"  src="img/but_menos.png" alt="Borrar" onclick="iniciarBorrado_Fila_Carrera(${cardCorredor.id_corredor}, ${carrera.id_carrera},'${pageContext.request.contextPath}/app')"/>         <!-- que se pinte de rojo la fila y cambiar íconos a cancelar/confirmar -->
                    <img class="btn_bc" src="img/but_confirm.png" alt="Confirmar" onclick="iniciarBorrado_Fila_Carrera_Confirm(${cardCorredor.id_corredor}, ${carrera.id_carrera},'${pageContext.request.contextPath}/app')"/>
                    <img class="btn_bc"  src="img/but_cancel.png" alt="Cancelar" onclick="iniciarBorrado_Fila_Carrera_Cancel(${cardCorredor.id_corredor}, '${pageContext.request.contextPath}/app')"/>
                    <img class="btn_mc" src="img/but_confirm.png" alt="Confirmar" onclick="iniciarModif_Fila_Carrera_Confirm(${cardCorredor.id_corredor}, ${carrera.id_carrera},'${pageContext.request.contextPath}/app')"/>
                    <img class="btn_mc"  src="img/but_cancel.png" alt="Cancelar" onclick="iniciarModif_Fila_Carrera_Cancel(${cardCorredor.id_corredor}, '${pageContext.request.contextPath}/app')"/>
                </div>
            </div>
            </c:forEach>
    </div>
</div>

<div class="modal-footer">
    <input type="button" id="agregarCarrera" class="btn btn-success" value="Agregar carrera" onClick="agregarFila_Carrera(${cardCorredor.id_corredor},'${pageContext.request.contextPath}/app')">                            <!-- agregar fila, que se pinte de amarillo la fila, desbloquear inputs y cambiar íconos a cancelar/confirmar -->
    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cerrar</button>

</div>






