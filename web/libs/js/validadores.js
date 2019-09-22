( function( $ ) {
    $(document).ready(function() {
        FormValidation.Validator.valoresSi = {
            validate: function(validator, $field, options) {
                var value = $field.val();
                if (value === '') {
                    return true;
                } else {
                    if (eval(options.condicion)) {
                        if ($.inArray(value,options.valores)>=0){
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return true;
                    }
                }
            }
        };

        FormValidation.Validator.cuil = {   
            validate: function(validator, $field, options) {
                sCUIT = $field.val();
                var aMult = '5432765432'; 
                var aMult = aMult.split('');
                sCUIT = sCUIT.replace(/\-/g,'');
                sCUIT = sCUIT.replace(/\_/g,'');
                if (sCUIT.length != 0){
                    if (sCUIT && sCUIT.length == 11) { 
                        aCUIT = sCUIT.split(''); 
                        var iResult = 0; 
                        for(i = 0; i <= 9; i++) { 
                            iResult += aCUIT[i] * aMult[i]; 
                        } 
                        iResult = (iResult % 11); 
                        iResult = 11 - iResult; 
                        if (iResult == 11) iResult = 0; 
                        if (iResult == 10) iResult = 9; 
                        if (iResult == aCUIT[10]) { 
                            return true; 
                        } 
                    }     
                    return false; 
                } else {
                    return true;
                }
            } 
        }

        FormValidation.Validator.docDelCuil = {
            validate: function(validator, $field, options){
                doc = parseInt($field.val());
                cuil = eval(options.cuil).replace(/\-/g,'')
                docCuil = parseInt(cuil.substring(2,10))
                if (doc){
                    if (doc == docCuil){
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return true;
                }
            }
        }

        FormValidation.Validator.tamanioNroDoc = {
            validate: function(validator, $field, options){
                doc = $field.val();
                tipo = parseInt(eval(options.tipo_doc));
                if (doc){
                    if ( tipo == 3 || tipo == 4 ) {
                        if (doc.length <= parseInt(options.tamanio)) {
                            return true;
                        } else {
                            return false;
                        }
                    }else{
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        FormValidation.Validator.CBU = {
            validate: function(validator, $field, options){
                cbu = $field.val();
                return validarLargoCBU(cbu) && validarCodigoBanco(cbu.substr(0,8)) && validarCuenta(cbu.substr(8,14))
            }
        }
    });
})( jQuery );

function validarLargoCBU(cbu) {
    if (cbu.length != 22) { 
        return false 
    } else {
        return true
    }
}

function validarCodigoBanco(codigo) {
    if (codigo.length != 8) {
        return false
    } else {
        var banco = codigo.substr(0,3)
        var digitoVerificador1 = codigo[3]
        var sucursal = codigo.substr(4,3)
        var digitoVerificador2 = codigo[7]
        var suma = banco[0] * 7 + banco[1] * 1 + banco[2] * 3 + digitoVerificador1 * 9 + sucursal[0] * 7 + sucursal[1] * 1 + sucursal[2] * 3
        var diferencia = 10 - (suma % 10)
        return diferencia == digitoVerificador2
    }
}

function validarCuenta(cuenta) {
    if (cuenta.length != 14) { 
        return false 
    } else {
        var digitoVerificador = cuenta[13]
        var suma = cuenta[0] * 3 + cuenta[1] * 9 + cuenta[2] * 7 + cuenta[3] * 1 + cuenta[4] * 3 + cuenta[5] * 9 + cuenta[6] * 7 + cuenta[7] * 1 + cuenta[8] * 3 + cuenta[9] * 9 + cuenta[10] * 7 + cuenta[11] * 1 + cuenta[12] * 3
        var diferencia = 10 - (suma % 10)
        return diferencia == digitoVerificador
    }
}

function validarCBU(cbu) {
    
}