
function mueveReloj(){
	var momentoActual = new Date(); 
	var hora = momentoActual.getHours(); 
	var minuto = momentoActual.getMinutes(); 
	var segundo = momentoActual.getSeconds(); 
	var dd = momentoActual.getDate(); 
	//Enero es 0
	var mm = momentoActual.getMonth()+1;
	var yyyy = momentoActual.getFullYear(); 
	if(dd<10){ 
		dd='0'+dd
	}  
	if(mm<10){ 
		mm='0'+mm 
	}  
	var fecha = dd+'/'+mm+'/'+yyyy; 
	str_segundo = new String (segundo); 
	if (str_segundo.length == 1) { 
		segundo = '0' + segundo 
	} 
	str_minuto = new String (minuto); 
	if (str_minuto.length == 1) { 
		minuto = '0' + minuto 
	} 
	str_hora = new String (hora); 
	if (str_hora.length == 1) { 
		hora = '0' + hora 
	} 
	var date = fecha + ' - ' + hora + ':' + minuto;
	if ($('#reloj_js')[0]) {
		$('#reloj_js')[0].innerHTML = date; 
		setTimeout('mueveReloj()',1000);
	}
};

( function( $ ) {
	$( document ).ready(function() {
		mueveReloj();
	});
})( jQuery );