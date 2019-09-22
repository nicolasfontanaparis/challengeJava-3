/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos.utilidades;

import java.util.Map;

/**
 * Clase para redirigir entre páginas
 * @author Nicolás Fontana
 */
public class Redirect {
    Map<String, String> parametros;
    String url;
    
    public Redirect(Map parametros, String url){
        this.parametros = parametros;
        this.url = url;
    }
    public String stringPost(){
        String form_str = "";
        form_str += "<form method=\"post\" action=\""+this.url +"\" id=\"form_redirect\">";
        for (Map.Entry<String, String> entry : this.parametros.entrySet()) {
            form_str +="<input type=\"hidden\" name=\""+entry.getKey()+"\" value=\""+this.parametros.get(entry.getKey())+"\">";
        }
        form_str +="</form>";
        form_str += "<script>document.getElementById('form_redirect').submit()</script>";
        return form_str;
    }
    
    
}
