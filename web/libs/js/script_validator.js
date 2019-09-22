( function( $ ) {
	$( document ).ready(function(){
		$('form').validator({
			alert: 'Existen errores, por favor revise.',
    		checkbox: true,
    		dataErrorMsg:'error',
    		defaultMsg:'Verifique este campo',
    		formGroupErrorClass:'has-error',
    		helpBlockClass:'help-block with-errors',
    		radio: true,
    		validateSelecters:'input,select,textarea',
    		validHandlers: {},
    		validOnBlur: true,
    		validOnKeyUp: false,
    		validRadioCheckOnClick: false
		});

		//check valid before submitting 
		$('form').submit(function(e) {
		    e.preventDefault();

		    if ($('form').validator('check') < 1) {
		        alert('Formulario Valido');
		    }
		});
	});
})( jQuery );