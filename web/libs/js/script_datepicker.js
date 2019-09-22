( function( $ ) {
	$( document ).ready(function(){
		$('.date').datetimepicker({
			tooltips: {
				today: 'Hoy',
				clear: 'Limpiar',
				close: 'Cerrar',
				selectMonth: 'Seleccionar Mes',
				prevMonth: 'Mes Anterior',
				nextMonth: 'Mes Proximo',
				selectYear: 'Seleccionar A\u00F1o',
				prevYear: 'A\u00F1o Anterior',
				nextYear: 'A\u00F1o Proximo',
				selectDecade: 'Seleccionar Decada',
				prevDecade: 'Decada Anterior',
				nextDecade: 'Decada Proxima',
				prevCentury: 'Siglo Anterior',
				nextCentury: 'Siglo Proximo'
			}
		});
	});
})( jQuery );