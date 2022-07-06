            <button type="button" class="btn btn-primary btn-sm btn-marg" onclick="location.href ='${pageContext.request.contextPath}'">Salir</button>
            <button type="button" class="btn btn-primary btn-sm btn-marg" data-bs-toggle="modal" data-bs-target="#newCorredor">Nuevo Corredor</button>

            <div class="modal" id="newCorredor">
              <div class="modal-dialog">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                      <h4 class="modal-title">Datos del corredor</h4>
                      <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>

                    <form id="formNewCorredor" action="${pageContext.request.contextPath}/app" method="post">
                        <!-- Modal body -->
                        <div class="modal-body">
                            <label for="nom_corredor" class="form-label">Nombre</label>
                            <input type="text" class="form-control" id="nom_corredor" name="nom_corredor" required>
                            <label for="lname_user" class="form-label">Apellido</label>
                            <input type="text" class="form-control" name="ape_corredor" id="ape_corredor" required">

                            <label for="input_img_corredor" class="form-label">Foto de perfil</label>
                            <input type="file" class="form-control" id="input_img_corredor0" name="input_img_corredor" onChange="impFoto(0)">
                            <input type="hidden" id="img_corredor0" name="img_corredor">

                            <input type="hidden" name="accion" id="accion" value="newCorredor">
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <button type="reset" class="btn btn-info">Limpiar</button>
                            <button type="submit" class="btn btn-success">Confirmar</button>
                        </div>
                    </form>

                </div>
              </div>
            </div>
        </div>
</nav>
