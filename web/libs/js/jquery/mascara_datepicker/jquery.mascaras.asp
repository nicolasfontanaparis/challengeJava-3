<!--#include file="../../../../funcionesApp.asp" -->
(function($) {
    var ruta = 'http://<%=(Request.ServerVariables("server_name"))%>:<%=(Request.ServerVariables("server_port"))%>/<%=SITE_NAME%>/';
    var lib_1 = 'lib/js/jquery/mascara/jquery.inputmask.js';
    var lib_2 = 'lib/js/jquery/mascara/jquery.inputmask.extensions.js';
    jQuery.cachedScript = function( url, options ) {
      options = $.extend( options || {}, {
        dataType: "script",
        cache: true,
        async:false,
        url: url
      });
      return jQuery.ajax( options );
    };
    $.cachedScript( ruta+lib_1 );  
    $.cachedScript( ruta+lib_2 );  
    $.fn.extend({
        mascara:function(texto){
            $.extend($.inputmask.defaults.definitions, {
                'M': { 
                    validator: "[0-5][0-9]|60",
                    cardinality: 2,
                    prevalidator: [{ validator: "[0-6]", cardinality: 1}]
                },
                'h': { 
                    validator: "0[0-9]:[0-5][0-9]|1[0-2]:[0-5][0-9]",
                    cardinality: 5,
                    prevalidator: [
                                { validator: "[01]", cardinality: 1 },
                                { validator: "0[0-9]|1[0-2]", cardinality: 2 },
                                { validator: "0[0-9]|1[0-2]:", cardinality: 3 },
                                { validator: "0[0-9]:[0-5]|1[0-2]:[0-5]", cardinality: 4 }
                                ]
                },                
                'H': { 
                    validator: "[01][0-9]:[0-5][0-9]|2[0-3]:[0-5][0-9]",
                    cardinality: 5,
                    prevalidator: [
                                { validator: "[012]", cardinality: 1 },
                                { validator: "[01][0-9]|2[0-3]", cardinality: 2 },
                                { validator: "[01][0-9]|2[0-3]:", cardinality: 3 },
                                { validator: "[01][0-9]:[0-5]|2[0-3]:[0-5]", cardinality: 4 }
                                ]
                },
                'F': { 
                    validator: "((0[1-9]\/(0[1-9]|1[0-2])|1[0-9]\/(0[1-9]|1[0-2])|2[0-9]\/(0[1,3-9]|1[0-2])|30\/(0[1,3-9]|1[0-2])|31\/(0[1,3,5,7,8]|1[0,2]))\/(1[9]|2[0-5])[0-9][0-9])|(2[0-8]\/02\/(1[9]|2[0-5])[0-9][0-9])|(29\/02\/190[48]|19[2468][048]|19[13579][26]|2[04][02468][048]|2[04][13579][26]|2[1235][02468][48]|2[1235][13579][26])",
                    cardinality: 10,
                    prevalidator: [
                                { validator: "[0-3]", cardinality: 1 },
                                { validator: "0[1-9]|[12][0-9]|3[01]", cardinality: 2 },
                                { validator: "(0[1-9]|[12][0-9]|3[01])\/", cardinality: 3 },
                                { validator: "(0[1-9]|[12][0-9]|3[01])\/[01]", cardinality: 4 },
                                { validator: "(0[1-9]\/(0[1-9]|1[0-2])|[12][0-9]\/(0[1-9]|1[0-2])|30\/(0[1,3-9]|1[0-2])|31\/(0[1,3,5,7,8]|1[0,2]))", cardinality: 5 },
                                { validator: "(0[1-9]\/(0[1-9]|1[0-2])|[12][0-9]\/(0[1-9]|1[0-2])|30\/(0[1,3-9]|1[0-2])|31\/(0[1,3,5,7,8]|1[0,2]))\/", cardinality: 6 },
                                { validator: "(0[1-9]\/(0[1-9]|1[0-2])|[12][0-9]\/(0[1-9]|1[0-2])|30\/(0[1,3-9]|1[0-2])|31\/(0[1,3,5,7,8]|1[0,2]))\/[12]", cardinality: 7 },
                                { validator: "(0[1-9]\/(0[1-9]|1[0-2])|[12][0-9]\/(0[1-9]|1[0-2])|30\/(0[1,3-9]|1[0-2])|31\/(0[1,3,5,7,8]|1[0,2]))\/(1[9]|2[0-5])", cardinality: 8 },
                                { validator: "(0[1-9]\/(0[1-9]|1[0-2])|[12][0-9]\/(0[1-9]|1[0-2])|30\/(0[1,3-9]|1[0-2])|31\/(0[1,3,5,7,8]|1[0,2]))\/(1[9]|2[0-5])[0-9]", cardinality: 9 }
                                ]
                }
            });
            $.extend($.inputmask.defaults.aliases, {
                'fecha': {
                    mask: "F"
                },
                'minutos': {
                    mask: "M"
                },
                'h24': {
                    mask: "H"
                },
                'h12': {
                    mask: "h"
                }
            });
            $(this).each(function(){
                if(texto == "fecha"){
                $(this).inputmask("fecha",{ 
                    "placeholder": "__/__/____"
                });
                $(this).keypress(function(){
                    var a =$(this).val();
                    var contador = 0;
                    while(a.indexOf("_") != -1){
                         a = a.replace("_","");
                         a = a.replace("/","");
                        contador = contador + 1;
                        if(contador >15){
                            break;
                        }

                    }
                    if((a.length == 2) || (a.length == 4)){
                        if(a.length == 2){
                             $(this).val(a+"/");
                        }else{
                            $(this).val(a.substring(0,2)+"/"+ a.substring(2,5)+"/");
                        }
                    }
                });
                $(this).focusout(function(){
                    var a =$(this).val();
                    while(a.indexOf("_") != -1){
                         a = a.replace("_","");
                         a = a.replace("/","");
                    }
                    if(a.length < 9){
                             $(this).val("");
                    }
                });
            }else if((texto == "h12")||(texto == "h24")){
                if(texto == "h12"){
                    $(this).inputmask("h12",{ 
                        "placeholder": "__:__"
                    });
                }else{
                    $(this).inputmask("h24",{ 
                        "placeholder": "__:__"
                    });
                }
                $(this).keypress(function(){
                    var a =$(this).val();
                    var contador = 0;
                    while(a.indexOf("_") != -1){
                         a = a.replace("_","");
                         a = a.replace(":","");
                        contador = contador + 1;
                        if(contador >15){
                            break;
                        }
                    }
                    if(a.length == 2){
                             $(this).val(a+":");    
                    }
                    if(a.length == 3){
                             $(this).val(a.substring(0,2)+":"+a.substring(2,3));   
                    }   
                });
                $(this).focusout(function(){                
                    var a =$(this).val();
                    while(a.indexOf("_") != -1){
                         a = a.replace("_","");
                         a = a.replace(":","");
                    }
                    if(a.length < 4){
                             $(this).val("");                   
                    }
                });           
            }else if(texto == "minutos"){
                $(this).inputmask("minutos",{ 
                    "placeholder": "__"
                });
                $(this).keypress(function(){
                    var a =$(this).val();
                    var contador = 0;
                    while(a.indexOf("_") != -1){
                         a = a.replace("_","");
                         a = a.replace(":","");
                        contador = contador + 1;
                        if(contador >15){
                            break;
                        }
                    }
                });
                $(this).focusout(function(){
                    var a =$(this).val();
                    while(a.indexOf("_") != -1){
                         a = a.replace("_","");
                    }
                    if(a.length < 2){
                             $(this).val("");
                    }
                });
            }
            });
        }
    });
})(jQuery)
