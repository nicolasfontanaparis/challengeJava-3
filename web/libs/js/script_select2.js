function iniciar_busqueda_select(nombre){
    if ($('#'+nombre).prop("multiple")) {
        $('#'+nombre).prop('disabled', 'disabled');
        $('#'+nombre).select2('data', null);
        $('#'+nombre).val([]);
        $('#'+nombre).trigger('change');
        $('#'+nombre).html(''); 
        $('#'+nombre).parent().find('.select2-search__field').placeholder = 'Buscando...';
    } else {
        $('#select2-'+nombre+'-container').parent().find('span[role="presentation"]').removeClass("select2-selection__arrow");
        $('#select2-'+nombre+'-container').parent().find('span[role="presentation"]').addClass("gly-spin glyphicon glyphicon-refresh gly-select");
        $('#'+nombre).prop('disabled', 'disabled');
        $('#'+nombre).select2('data', null);
        $('#'+nombre).val([]);
        $('#'+nombre).trigger('change');
        $('#'+nombre).html('');
        //cambiar el placeholder del select
        $('#select2-'+nombre+'-container')[0].children[0].innerHTML = 'Buscando...';
    }
}

function cargar_datos_select(nombre, placeholder, datos){
    if ($('#'+nombre).prop("multiple")) {
        if (datos.length == 0) {
            //cambiar el placeholder del select
            $('#'+nombre).parent().find('.select2-search__field').placeholder = 'No se encontraron resultados';
        } else {
            $('#'+nombre).parent().find('.select2-search__field').placeholder = placeholder;
            for (var i = 0; i < datos.length; i++) {
                $('#'+nombre).append('<option value="' + datos[i].CODIGO + '">' + datos[i].DESCRIPCION + '</option>');
            }
            $('#'+nombre).prop('disabled', false);
        }
        $('#'+nombre).val([]);
    } else {
        if (datos.length == 0) {
            //cambiar el placeholder del select
            $('#select2-'+nombre+'-container')[0].children[0].innerHTML = 'No se encontraron resultados';
        } else {
            $('#select2-'+nombre+'-container')[0].children[0].innerHTML = placeholder;
            for (var i = 0; i < datos.length; i++) {
                $('#'+nombre).append('<option value="' + datos[i].CODIGO + '">' + datos[i].DESCRIPCION + '</option>');
            }
            $('#'+nombre).prop('disabled', false);
        }
        $('#select2-'+nombre+'-container').parent().find('span[role="presentation"]').removeClass("gly-spin glyphicon glyphicon-refresh gly-select");
        $('#select2-'+nombre+'-container').parent().find('span[role="presentation"]').addClass("select2-selection__arrow");
        $('#'+nombre).val([]);
    }
}

function cargar_error_select(nombre, mensaje){
    if ($('#'+nombre).prop("multiple")){
        $('#'+nombre).parent().find('.select2-search__field').placeholder = mensaje;
    } else {
        $('#select2-'+nombre+'-container')[0].children[0].innerHTML = mensaje;
        $('#select2-'+nombre+'-container').find('[role="presentation"]').removeClass("gly-spin glyphicon glyphicon-refresh gly-select");
        $('#select2-'+nombre+'-container').find('[role="presentation"]').addClass("select2-selection__arrow");
    }
}

/*
Ejemplo de Uso de las funciones anteriores

La variable datos debe ser un JSON con el siguiente formato
{"results":[{"CODIGO": "1", "DESCRIPCION": "Primero"},{"CODIGO": "2", "DESCRIPCION": "Segundo"},
{"CODIGO": "3", "DESCRIPCION": "Tercero"},{"CODIGO": "4", "DESCRIPCION": "Cuarto"},...]}
Si se desea devolver algun mensaje de error se devuelve con el un codigo negativo y en 
descripcion el placeholder nuevo que tomara el select, ej:
{"results":[{"CODIGO": "-1", "DESCRIPCION": "No existe ningun Plan disponible"}]}                strJSON = strJSON & "]}"
function get_planes(){
    iniciar_busqueda_select('plan')
    var_cob = $('#cobertura').val();
    if (var_cob){
        $.ajax({
            url: "../ctrl/ctrl_asignacion_redes.asp",
            method: "POST",
            dataType: "json",
            data: {   
                cobertura: var_cob,
                goto: 'add',
                action: 'get_planes'
            }
        }).success(function(data) {
            var datos = $.map(data.results, function(res) {
                return {
                    CODIGO: res.CODIGO,
                    DESCRIPCION: res.DESCRIPCION
                };
            });
            cargar_datos_select('plan', 'Seleccione Plan...', datos);   
        }).fail(function(){
            cargar_error_select('plan','Error en la Consulta')
        });
    } else {
        var datos = new Array();
        cargar_datos_select('plan', 'Seleccione Plan...', datos);
    }
}
*/