
function impFoto(id_corredor){
    idInput="input_img_corredor"+id_corredor;
    idOutput="img_corredor"+id_corredor;
    
    value = document.getElementById(idInput).files[0];
    var reader = new FileReader();
    reader.onloadend = function (e) {
        fotoCodif = `data:${value.type};base64,`+e.target.result.split("base64,")[1];
        document.getElementById(idOutput).value = fotoCodif || "";
    };
    reader.readAsDataURL(value);
}

function getCarreras(url, id_corredor){
    url = url+'?accion=getCarreras&id_corredor='+id_corredor;
    var http = new XMLHttpRequest();
    http.open("GET", url);
    http.onload = function() {
      document.getElementById("modal_Carreras").innerHTML = 
      this.responseText;
    };
    http.send();
}


function agregarFila_Carrera(id_corredor, url){
    url2 = url+'?accion=getCarreras&id_corredor='+id_corredor;
    var http = new XMLHttpRequest();
    http.open("GET", url2);
    http.onload = function() {
      document.getElementById("modal_Carreras").innerHTML = 
      this.responseText;
      
        params=id_corredor+",'"+url+"'";
        targetDiv1 = document.getElementById('lista_del_corredor_'+id_corredor);
        divCarrera = '           <div class="row lc_n" id="lista_de_carreras_New">';
        divCarrera = divCarrera+'       <div class="col-1"><input type="number" disabled></div>';
        divCarrera = divCarrera+'       <div class="col-4"><input type="text" required id="tit_carrera_N"></div>';
        divCarrera = divCarrera+'       <div class="col-2"><input type="date" required id="fh_carrera_N"></div>';
        divCarrera = divCarrera+'       <div class="col-2"><input type="number" required id="km_N"></div>';
        divCarrera = divCarrera+'       <div class="col-2"><input type="number" required id="min_N"></div>';
        divCarrera = divCarrera+'       <div class="col-1"> <img src="img/but_confirm.png" alt="Confirmar" onClick="agregarFila_Carrera_Confirm('+params+')"/>';
        divCarrera = divCarrera+'                           <img src="img/but_cancel.png" alt="Cancelar" onClick="agregarFila_Carrera_Cancel('+params+')"/>';
        divCarrera = divCarrera+'       </div>';
        divCarrera = divCarrera+'</div>';

        targetDiv1.insertAdjacentHTML('afterend', divCarrera);
        document.getElementById("agregarCarrera").disabled=true;

        targetDiv1.classList.remove('lc_i');
        targetDiv1.classList.add('lc_n');
    };
    http.send();
    
}

function agregarFila_Carrera_Cancel(id_corredor, url){
    getCarreras(url, id_corredor);
}

function agregarFila_Carrera_Confirm(id_corredor,url){
    //validación de campos completos
    tit_carrera=document.getElementById("tit_carrera_N").value;
    fh_carrera=document.getElementById("fh_carrera_N").value;
    km=document.getElementById("km_N").value;
    min=document.getElementById("min_N").value;

    if(tit_carrera==""||fh_carrera==""||km==""||min==""){
        alert("Completar datos");
    }else{
        //envío para insert de nueva carrera:
        url = url+'?accion=newCarrera&id_corredor='+id_corredor+'&tit_carrera='+tit_carrera+'&fh_carrera='+fh_carrera+'&km='+km+'&min='+min;
        var http = new XMLHttpRequest();
        http.open("POST", url);
        http.onload = function() {
          document.getElementById("modal_Carreras").innerHTML = 
          this.responseText;
        };
        http.send();
    }
}

function iniciarBorrado_Fila_Carrera(id_corredor, id_carrera, url){
    targetDiv1 = document.getElementById('lista_del_corredor_'+id_corredor);
    targetDiv2 = document.getElementById('lista_de_carreras_'+id_carrera);
    
    targetDiv1.classList.remove('lc_i');
    targetDiv1.classList.add('lc_b');
    targetDiv2.classList.remove('lc_i');
    targetDiv2.classList.add('lc_b');
    
    document.getElementById("agregarCarrera").disabled=true;
}

function iniciarBorrado_Fila_Carrera_Cancel(id_corredor, url){
    getCarreras(url, id_corredor);
}

function iniciarBorrado_Fila_Carrera_Confirm(id_carrera, url){
    //envío para delete de carrera:
    url = url+'?accion=delCarrera&id_carrera='+id_carrera;
    var http = new XMLHttpRequest();
    http.open("POST", url);
    http.onload = function() {
      document.getElementById("modal_Carreras").innerHTML = 
      this.responseText;
    };
    http.send();
}

function iniciarModif_Fila_Carrera(id_corredor, id_carrera, url){
    targetDiv1 = document.getElementById('lista_del_corredor_'+id_corredor);
    targetDiv2 = document.getElementById('lista_de_carreras_'+id_carrera);
    
    targetDiv1.classList.remove('lc_i');
    targetDiv1.classList.add('lc_m');
    targetDiv2.classList.remove('lc_i');
    targetDiv2.classList.add('lc_m');
    
    document.getElementById("agregarCarrera").disabled=true;
}

function iniciarModif_Fila_Carrera_Cancel(id_corredor, url){
    getCarreras(url, id_corredor);
}

function iniciarModif_Fila_Carrera_Confirm(id_corredor, id_carrera, url){
    alert("modificación pendiente");
    getCarreras(url, id_corredor);
}

