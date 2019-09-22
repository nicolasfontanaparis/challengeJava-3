( function( $ ) {
	$( document ).ready(function(){
		var texto_busqueda;
		var information;
		$('.datatable').each(function(index, el) {
			if (el.dataset.searchText){
				texto_busqueda = el.dataset.searchText;
			} else {
				texto_busqueda = "Buscar :";
			};
			if (el.dataset.information == "false") {
				information = '';
			} else {
				information = 'i';
			}
			if (el.dataset.checkbox == "true") {
				check = "true";
			} else {
				check = "false";
			}
      if (el.dataset.botones) {
        botones = []
        $(el.dataset.botones.split(',')).each(function() {
          switch (this.toString()){
            case 'print':
              botones.push({  extend: 'print', 
                              text: 'Imprimir',
                              title: el.dataset.titulo,
                              exportOptions: {
                                columns: "thead th:not(.noExport)"
                              }
                            });
              break;
            case 'print selected':
              botones.push({  extend: 'print', 
                              text: 'Imprimir Seleccionados',
                              title: el.dataset.titulo,
                              exportOptions: {
                                columns: "thead th:not(.noExport)",
                                modifier: {
                                  selected: true
                                }
                              }
                            });
              break;
            case 'excel':
              botones.push({  extend: 'excelHtml5', 
                              text: 'Excel',
                              title: el.dataset.titulo,
                              exportOptions: {
                                columns: "thead th:not(.noExport)"
                              }
                            });
              break;
            case 'excel selected':
              botones.push({  extend: 'excelHtml5', 
                              text: 'Excel Seleccionados',
                              title: el.dataset.titulo,
                              exportOptions: {
                                columns: "thead th:not(.noExport)",
                                modifier: {
                                  selected: true
                                }
                              }
                            });
              break;
            case 'copy':
              botones.push({  extend: 'copyHtml5', 
                              text: 'Copiar',
                              title: el.dataset.titulo,
                              exportOptions: {
                                columns: "thead th:not(.noExport)"
                              }
                            });
              break;
            case 'copy selected':
              botones.push({  extend: 'copyHtml5', 
                              text: 'Copiar Seleccionados',
                              title: el.dataset.titulo,
                              exportOptions: {
                                columns: "thead th:not(.noExport)",
                                modifier: {
                                  selected: true
                                }
                              }
                            });
              break;
            case 'csv':
              botones.push({  extend: 'csvHtml5', 
                              text: 'CSV',
                              title: el.dataset.titulo,
                              exportOptions: {
                                columns: "thead th:not(.noExport)"
                              }
                            });
              break;
            case 'csv selected':
              botones.push({  extend: 'csvHtml5', 
                              text: 'CSV Seleccionados',
                              title: el.dataset.titulo,
                              exportOptions: {
                                columns: "thead th:not(.noExport)",
                                modifier: {
                                  selected: true
                                }
                              }
                            });
              break;
            case 'pdf':
              botones.push({  extend:'pdfHtml5',
                              text:'PDF',
                              title: el.dataset.titulo,
                              exportOptions: {
                                columns: "thead th:not(.noExport)"
                              }
                            });
              break;
            case 'pdf selected':
              botones.push({  extend:'pdfHtml5',
                              text:'PDF Seleccionados',
                              title: el.dataset.titulo,
                              exportOptions: {
                                columns: "thead th:not(.noExport)",
                                modifier: {
                                  selected: true
                                }
                              }
                            });
              break;
          }
        });
      } else {
        botones = []
      }
			var tabla = $(el).DataTable( {
				"searching": false,
				"lengthMenu": [[10, 25, 50, 100, -1],[10, 25, 50, 100, "Todas"]],
        "buttons": botones,
				"language": {
					"search" : texto_busqueda,
					"lengthMenu": "_MENU_ lineas/pagina",
					"zeroRecords": "No se encontraron Resultados",
					"infoEmpty": "0 registros encontrados",
					"info": "_TOTAL_ registros",
          "thousands": "",
          "decimal": ",",
					"infoFiltered": "de _MAX_ totales",
					"select": {
            	"rows": "%d seleccionados"
          },
					"loadingRecords": "Cargando...",
	    		"processing": "Procesando...",
	    		"emptyTable": "No existen registros que mostrar",
					"paginate": {
						"first":      "<<",
						"last":       ">>",
						"next":       ">",
						"previous":   "<"
					},
					"aria": {
            "first":      "Primera",
            "last":       "Ultima",
            "next":       "Siguiente",
            "previous":   "Anterior",
						"sortAscending":  ": Ordenar ascendente",
						"sortDescending": ": Ordenar descendente"
					}
				},
				"dom": 'r<"row"<"col-xs-3"'+information+'><"col-xs-9"f>>t<"row"<"col-xs-3"l><"col-lg-5 col-md-4 col-xs-3 text-right"B><"col-lg-4 col-md-5 col-xs-6"p>>',
				responsive: {
      		details: {
            renderer: function ( api, rowIdx, columns ) {
              var data = $.map( columns, function ( col, i ) {
                return col.hidden ?
                  '<tr data-dt-row="'+col.rowIndex+'" data-dt-column="'+col.columnIndex+'">'+
                    '<td class="text-right" style="width:1%; padding-left:15px;" nowrap><label>'+col.title+':'+'</label></td> '+
                    '<td class="text-left">'+col.data+'</td>'+
                  '</tr>' :
                   '';
                }).join('');
              return data ?
                  $('<table class="table table-striped table-condensed" style="margin:0px;"/>').append( data ) :
                  false;
            }
        	}
    		},        
				initComplete: function () {
          this.api().columns( '.select-filter' ).each( function () {
            var column = this;
            var select = $('<select style="width:100%;"><option value=""></option></select>')
              .appendTo( $(column.footer()))
              .on( 'change', function () {
                var val = $.fn.dataTable.util.escapeRegex(
                  $(this).val()
                );
                column
                  .search( val ? '^'+val+'$' : '', true, false )
                  .draw();
              });
            column.data().unique().sort().each( function ( d, j ) {
              select.append( '<option value="'+d+'">'+d+'</option>' )
          	});
       		});
       		this.api().columns('.select-checkbox').each( function() {
       			var column = this;
       			var check = $('<input type="checkbox" class="DataTable-head-check"></input>')
       				.appendTo( $(column.header()))
       				.on( 'click', function () {
       					if (this.checked) {
									tabla.rows().select();
								} else {
									tabla.rows().deselect();
								}
       				});
       		});
       		this.api().rows().each( function() {
       			var fila = this;
       			fila.on('select', function () {
       				if (tabla.rows()[0].length	== tabla.rows('.selected')[0].length){
     						$(this).find('.DataTable-head-check').each(function(el) {
     							this.checked = true;
     						});
       				}
       			});
       			fila.on( 'deselect', function () {
     					$(this).find('.DataTable-head-check').each(function() {
   							this.checked = false;
   						});       				 
     				});
       		});
        }
			});
      if (el.dataset.checkbox){
        tabla.select.style('multi');
        tabla.select.selector('td.select-checkbox');
      };
		})
	});
})( jQuery );