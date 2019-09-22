
//window.onfocus = focoEnModal;
//window.onblur=focoEnModal;
//document.onclick = focoEnModal;
//document.onkeypress = kpress;
Date.prototype.AddDias=function(dias){
	return this.setTime(this.getTime()+ (dias*24*60*60*1000))
	//return true;
}
String.prototype.Trim=function(){
	return this.replace(/^\s*|\s*$/g,"");
}
String.prototype.Right=function (n){
	return Right(this,n);	
}
function Right(str,n){
	return str.substring(str.length-n,str.length)	;
}
function formatoFecha(fecha,formato){
	var res=formato
	res=res.replace('dd',Right('00' + fecha.getDate().toString(),2))
	res=res.replace('d',fecha.getDate().toString())
	res=res.replace('mm',('00' + (fecha.getMonth() + 1).toString()).Right(2))
	res=res.replace('m',(fecha.getMonth() + 1).toString())
	res=res.replace('yyyy',fecha.getFullYear().toString())
	res=res.replace('yy',Right(fecha.getFullYear().toString()),2)
	res=res.replace('hh',Right('00' + (fecha.getHours()+1).toString()),2)
	res=res.replace('h',(fecha.getHours()+1).toString())
	res=res.replace('nn',Right('00' + (fecha.getMinutes()+1).toString()),2)
	res=res.replace('n',(fecha.getMinutes()+1).toString())
	res=res.replace('ss',Right('00' + (fecha.getSeconds()+1).toString()),2)
	//res=res.replace('s',(fecha.getSeconds()+1).toString())
	return res;	
}
function kpress(){
	if (MODALWINDOW.event.keyCode==27){
		releaseDisabled()
		MODALWINDOW.close()
	}
}
function focoEnModal(){
	try{
		//MODALWINDOW.event=window.event;
		MODALWINDOW.focus()
		//window.event.returnValue=false;
		//window.event.cancelBubble=true;
	}catch(er){
	}
}
function modalWin(page, name, params){
	try{
		/*
		MODALWINDOW.onblur = focoEnModal;
		MODALWINDOW.document.onkeypress = kpress;
		MODALWINDOW.document.onclick = focoEnModal;
		*/
		MODALWINDOW = window.open(page, name, params);
		window.onfocus = focoEnModal;
		window.onblur=focoEnModal;
		document.onclick = focoEnModal;
		MODALWINDOW.document.onkeypress = kpress;
	}
	catch(er){
	}
}
function MostrarCalendario(el){
	try{
		//window.document.body.disabled=true
		//MODALWINDOW = window.open('/Pewman/Calendario.asp', 'calendario', 'status=0,width=250,height=250');
		
		//alert(el.value)
		window.frameCalendario.Show(event,el)
		MODALWINDOW.elDest=el;
		MODALWINDOW.onblur = focoEnModal;
		MODALWINDOW.elDest=el;
	}
	catch(er){
	}
}
function releaseDisabled(){
	try{
		window.document.body.disabled=false
		window.opener.document.body.disabled=false
	}catch(er){
		
	}
}

function oAjax(){
	var result=''
	this.query=new Array()
	this.enviar=oAjaxEnviar
	this.add=oAjaxAdd;
	this.enviarAsync=oAjaxEnviarAsync;
	this.xmlHTTP=null
	this.onStateChange = function(state) { };
}

function oAjaxAdd(item,valor){
	this.query.push(item + '=' + valor)
}

function oAjaxEnviarAsync(url){
	this.result=''
	this.xmlHTTP=null; 
	var self = this;
	try { 
		this.xmlHTTP = new ActiveXObject("Msxml2.XMLHTTP"); 
	} 
	catch (e) { 
		try { 
			this.xmlHTTP = new ActiveXObject("Microsoft.XMLHTTP"); 
		} 
		catch (E) { 
			this.xmlHTTP = false; 
		} 
	}
	if (!this.xmlHTTP && typeof XMLHttpRequest!='undefined') { 
		this.xmlHTTP = new XMLHttpRequest();
	}
	this.xmlHTTP.onreadystatechange=this.onStateChange;
	this.xmlHTTP.open ("GET",url + '?' + this.query.join('&'),true )
	this.xmlHTTP.send(null)
	
}
function oAjaxEnviar(url){
	this.result=''
	var xmlHTTP=false; 
	try { 
		xmlHTTP = new ActiveXObject("Msxml2.XMLHTTP"); 
	} 
	catch (e) { 
		try { 
			xmlHTTP = new ActiveXObject("Microsoft.XMLHTTP"); 
		} 
		catch (E) { 
			xmlHTTP = false; 
		} 
	}
	if (!xmlHTTP && typeof XMLHttpRequest!='undefined') { 
		xmlHTTP = new XMLHttpRequest();
	}
	xmlHTTP.open ("GET",url + '?' + this.query.join('&'),false )
	xmlHTTP.send(null)
	this.result=xmlHTTP.responseText
}
function getBrowserWidthHeight() {
	var intH = 0;
	var intW = 0;
   
	if(typeof window.innerWidth  == 'number' ) {
	   intH = window.innerHeight;
	   intW = window.innerWidth;
	} 
	else if(document.documentElement && (document.documentElement.clientWidth || document.documentElement.clientHeight)) {
		intH = document.documentElement.clientHeight;
		intW = document.documentElement.clientWidth;
	}
	else if(document.body && (document.body.clientWidth || document.body.clientHeight)) {
		intH = document.body.clientHeight;
		intW = document.body.clientWidth;
	}

	return { width: parseInt(intW), height: parseInt(intH) };
}  

function PopupTimerOnSubmit(frm){	
	var d = document.createElement('div');
	d.style.position="absolute";
	d.style.top="0px";
	d.style.left="0px";
	d.style.opacity="0.6";
	d.style.filter="alpha(opacity=60)";
	d.style.backgroundColor="#000000";
	d.id="divPopupOnSubmit";	
	var bws = getBrowserWidthHeight();
	d.style.width = bws.width + "px";
	d.style.height = bws.height + "px";
	document.body.appendChild(d);
	
	var d = document.createElement('div');
	d.style.position="absolute";
	d.style.top="0px";
	d.style.left="0px";
	d.style.zIndex="1001";
	//d.style.border="2px solid black";
	d.style.textalign="center";
	d.style.verticalalign="middle";
	//d.className="cssDivFlotanteVisible";
	d.id="divPopupOnSubmitTimer";	
	//d.style.width="100px";
	//d.style.height="100px";
	var i = document.createElement('image');
	i.src='/' + document.location.pathname.split('/')[1] + '/imagenes/Iconos/Cargando.gif';
	i.alt="Enviando datos...";
	i.onload=function(){window.setTimeout(function(){frm.submit();},200);}
	d.appendChild(i);
	var bws = getBrowserWidthHeight();
	var divW=100;
	var divH=100;
	d.style.left = parseInt((bws.width - divW) / 2).toString() + "px";
	d.style.top = parseInt((bws.height - divH) / 2).toString() + "px";
	document.body.appendChild(d);
	//window.setTimeout(function(){frm.submit();},100);
	return false;
}
function SpinnerCargado(){
	alert("loaded");
}