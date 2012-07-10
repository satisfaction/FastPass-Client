package com.getsatisfaction.fastpass;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import net.oauth.OAuth;
import net.oauth.OAuthMessage;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthAccessor;
import net.oauth.signature.OAuthSignatureMethod;
import net.oauth.OAuthException;
import java.net.URISyntaxException;
import org.apache.commons.lang.StringEscapeUtils;

public class FastPass {
  private static String domain = "getsatisfaction.com";
  
  public static void setDomain(String newDomain) {
    domain = newDomain;
  }
  
  public static String getDomain() {
    return domain;
  }
  
  
  public static void main(String[] args) throws OAuthException, IOException, URISyntaxException {
    String fastpass = script(
      "xi2vaxgpp06m",
      "ly68der0hk8idfr5c73ozyq56jpwstd1",
      "scott@getsatisfaction.com",
      "Scott",
      "nullstyle");
      
    System.out.println(fastpass);
  }
  
  public static String url(String key, String secret, String email, String name, String uid) throws OAuthException, IOException, URISyntaxException {
    return url(key, secret, email, name, uid, false);
  }
  public static String url(String key, String secret, String email, String name, String uid, boolean isSecure) throws OAuthException, IOException, URISyntaxException {
    return url(key, secret, email, name, uid, isSecure, new HashMap<String, String>());    
  }
  
  public static String url(String key, String secret, String email, String name, String uid, boolean isSecure, HashMap<String, String> additionalFields) throws OAuthException, IOException, URISyntaxException{
    OAuthConsumer consumer  = new OAuthConsumer(null, key, secret, null);
    OAuthAccessor accessor  = new OAuthAccessor(consumer);
    String        url       = isSecure ? "https://" + FastPass.domain + "/fastpass" : "http://" + FastPass.domain + "/fastpass";
    
    HashMap<String, String> params = new HashMap<String, String>();
    params.putAll(additionalFields);
    params.put("email", email);
    params.put("name", name);
    params.put("uid", uid);
    
    ArrayList<Map.Entry<String, String>> paramsList = new ArrayList<Map.Entry<String, String>>(params.entrySet());
    OAuthMessage message = accessor.newRequestMessage("GET", url, paramsList);
    
    return OAuth.addParameters(message.URL, message.getParameters());
  }
  
  public static String image(String key, String secret, String email, String name, String uid) throws OAuthException, IOException, URISyntaxException {
    return image(key, secret, email, name, uid, false);
  }
  
  public static String image(String key, String secret, String email, String name, String uid, boolean isSecure) throws OAuthException, IOException, URISyntaxException {
    return image(key, secret, email, name, uid, false, new HashMap<String, String>());    
  }
  
  public static String image(String key, String secret, String email, String name, String uid, boolean isSecure, HashMap<String, String> additionalFields) throws OAuthException, IOException, URISyntaxException{
    String result = url(key, secret, email, name, uid, isSecure, additionalFields);    
    return "<img src=\"" + StringEscapeUtils.escapeHtml(result) + "\" alt=\"\" />";
  }  
  
  public static String script(String key, String secret, String email, String name, String uid) throws OAuthException, IOException, URISyntaxException {
    return script(key, secret, email, name, uid, false);
  }
  
  public static String script(String key, String secret, String email, String name, String uid, boolean isSecure) throws OAuthException, IOException, URISyntaxException {
    return script(key, secret, email, name, uid, isSecure, new HashMap<String, String>());    
  }
  
  public static String script(String key, String secret, String email, String name, String uid, boolean isSecure, HashMap<String, String> additionalFields) throws OAuthException, IOException, URISyntaxException{
    String fastpass = url(key, secret, email, name, uid, isSecure, additionalFields);    
    String result = "<script type=\"text/javascript\">\n" +
                    "var GSFN;\n" +
                    "if(GSFN == undefined) { GSFN = {}; }\n" +
                    "  (function(){\n" +
                    "    add_js = function(jsid, url) {\n" +
                    "      var head = document.getElementsByTagName(\"head\")[0];\n" +
                    "      script = document.createElement('script');\n" +
                    "      script.id = jsid;\n" +
                    "      script.type = 'text/javascript';\n" +
                    "      script.src = url;\n" +
                    "      head.appendChild(script);\n" +
                    "    }\n" +
                    "\n" +
                    "    add_js(\"fastpass_common\", document.location.protocol + \"//" + FastPass.domain + "/javascripts/fastpass.js\");\n" +
                    "\n" +
                    "    if(window.onload) { var old_load = window.onload; }\n" +
                    "    window.onload = function() {\n" +
                    "      if(old_load) old_load();\n" +
                    "      add_js(\"fastpass\", \"" + fastpass + "\");\n" +
                    "    }\n" +
                    "  })()\n" +
                    "\n" +
                    "</script>\n";
                    
    return result;
  }
  
}

