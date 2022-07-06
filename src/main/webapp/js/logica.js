
function impFoto(id_corredor){
    idInput="input_img_corredor"+id_corredor;
    idOutput="img_corredor"+id_corredor;
    
    //Toma el archivo elegido por el input
    
    value = document.getElementById(idInput).files[0];

    //Este objeto FileReader te permite leer archivos
    var reader = new FileReader();

    //Esta función se ejecuta cuando el reader.readAsDataURL termina 
    reader.onloadend = function (e) {
        fotoCodif = `data:${value.type};base64,`+e.target.result.split("base64,")[1];
        document.getElementById(idOutput).value = fotoCodif || "";
    }

    //Aqui comienza a leer el archivo para posteriormente ejecutar la función onloadend
    reader.readAsDataURL(value);
    
}

