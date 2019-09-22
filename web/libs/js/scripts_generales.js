function MM_goToURL() { //v3.0
	var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
	for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
}

function soloEsto(caracteres){
	if(caracteres.indexOf(String.fromCharCode(event.keyCode)) == -1) {
	event.preventDefault();}
}

function soloNumeros(e){
	var keynum = window.event ? window.event.keyCode : e.which;
	if ((keynum == 8) || (keynum == 46))
		return true;
	return /\d/.test(String.fromCharCode(keynum));
}

function formatoDecimal(sVal, nDec) { 
    var n = parseFloat(sVal); 
    var s = "0.00"; 
    if (!isNaN(n)) {
		n = Math.round(n * Math.pow(10, nDec)) / Math.pow(10, nDec); 
		s = String(n); 
		s += (s.indexOf(".") == -1? ".": "") + String(Math.pow(10, nDec)).substr(1); 
		s = s.substr(0, s.indexOf(".") + nDec + 1); 
    } 
    return s; 
}

function formatoFecha(date,formato){
	var res = formato;
	var fecha=new Date(date);
	var strdia = "00" + fecha.getDate();
	res = res.replace("dd",strdia.substring(strdia.length-2,strdia.length));
	//res = res.replace("dd",Right("00" + fecha.getDate(),2));
	res = res.replace("d",fecha.getDate());
	var strmes = "00" + (fecha.getMonth()+1);
	res = res.replace("mm",strmes.substring(strmes.length-2,strmes.length));
	//res = res.replace("mm",Right("00" + month(fecha),2));
	res = res.replace("m",fecha.getMonth()+1);
	res = res.replace("yyyy", fecha.getFullYear());
	res = res.replace("yy", fecha.getFullYear());
	return res;
}

function string2date(val){
	//formato
	var res=true
	//var myMa=
	var myAt=val.split("/"); 
	if(myAt){
	    var myD=quitarCerosIzq(myAt[0]) 
	    var myM=quitarCerosIzq(myAt[1])-1;
	    var myY=(myAt[2]); 
	    if(myY.length==1){
			myY='200' + myY
	    }
	    if(myY.length==2){
			myY='20' + myY
	    }
	    res=esNumEntero(myD)?res:false;
	    res=esNumEntero(myM)?res:false;
	    res=esNumEntero(myY)?res:false;
	    if (!res){
			return res
	    }
	    var myDate=new Date(myY,myM,myD);
	}
	return myDate
}

function esNumEntero(num){
	return(num==parseInt(num,10).toString())
}

function quitarCerosIzq(val){
	try{
		while(val.length>0){
			if(val.charAt(0)=='0'){
				val=val.substr(1)
			}else{
				break;
			}
		}
	}catch(er){
	}
	return val
}

function addCerosIzq(input){
  if (input.value!="") {
    var n = 0;
    n =  eval(input.maxLength - input.value.length);
    for (i = 0; i < n; i++) {
      input.value = '0' + input.value
    }
  }
}

function getDatoTipificado(valor,tipo){
	var res
	switch(tipo){
		case 'N':
			if(esNumEntero(valor)){
				res=parseInt(valor,10);
			}
			break;
		case 'ND':
			if(esNumFloat(valor)){
				res=parseFloat(valor);
			}
			break;
		case 'D'://fecha
			if(esFechaValida(valor)){
				res=string2date(valor);
			}
			break;
		case 'S':
			res=valor.toString()
			break;
		case 'SE':
			var b=false
			for(var x=0;x<aryCaracteresEspecialesHTML.length;x++){
				//alert(aryCaracteresEspecialesHTML[x])
				if(valor.indexOf(aryCaracteresEspecialesHTML[x])>=0){
					//alert(valor.indexOf(aryCaracteresEspecialesHTML[x]))
					b=true
					break;
				}
			}
			if(b){
				res=null;
			}else{
				res=valor.toString()
			}
			break;
	}
	return res;
}

function esFechaValida(val){
	//formato
	var res=true
	//var myMa=
	if(val.length > 0){
		var myAt=val.split("/"); 
		if(myAt){
			
		    var myD=quitarCerosIzq(myAt[0]) 
		    var myM=quitarCerosIzq(myAt[1])-1; 
		    var myY=quitarCerosIzq(myAt[2]);
		    if(esNumEntero(myD) && esNumEntero(myM) && esNumEntero(myY)){
				var myDate=new Date(myY,myM,myD);
				if((myDate.getYear()!=myY&&myDate.getFullYear()!=myY)||myDate.getDate()!=myD||myDate.getMonth()!=myM){
					res=false;
				}
			}else{
				res=false;
			}
		}else{
			res=false
		}
	}else{
		res=false
	}
	return res
}
function validar_periodo(periodo) {
	var val = periodo;
	var anio, mes;
	var mesValido = new RegExp("^0[1-9]|1[0-2]$");
	anio = val.substring(0,4);
	mes = val.substring(4,6);
	if(!(mesValido.test(mes))){ 
		return false;
	} else{
		anioValido = /19[0-9]|20[0-9]/;
		if(!(anio.match(anioValido))){
			return false;
		}
	}
	return true;         
}

( function( $ ) {
	$( document ).ready(function(){
		$.each($('[data-type="number"]'), function() {
			$(this).on('keypress', function (event) {
				if (event.which == 13){
					return true;
				} else {
					return soloNumeros(event);
				}
			});
		});
		$.each($('[data-type="nombre"]'), function() {
			$(this).on('keypress', function (event) {
				if (event.which == 13){
					return true;
				} else {
					return soloEsto("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ \u00F1\u00D1 \u000A");
				}
			});
		});
		$("#openModal").hide();

		$('[data-toggle="tooltip"]').tooltip({
			container : 'body'
		});

	});

	$(window).bind('beforeunload', function(){
    $("#openModal").show();
  });

})( jQuery );