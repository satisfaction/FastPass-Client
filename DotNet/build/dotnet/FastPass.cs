using System;
using System.Collections.Generic;
using System.Text;
using System.Web;

using OAuth;

public class FastPass {
  private static string domain = "getsatisfaction.com";
  
  public static string Domain {
    get{ return domain; }
    set{ domain = value; }
  }
  
  public static string url(string key, string secret, string email, string name, string uid) {
    return url(key, secret, email, name, uid, false);
  }
  
  public static string url(string key, string secret, string email, string name, string uid, bool isSecure) {
    return url(key, secret, email, name, uid, isSecure, new Dictionary<string, string>());
  }
  
  public static string url(string key, string secret, string email, string name, string uid, bool isSecure, Dictionary<string, string> additionalFields) {
    var oauth       = new OAuthBase();
    var coreParams  = new Dictionary<string, string>();
    var fastpassUri = "";
    var timestamp   = oauth.GenerateTimeStamp();
    var nonce       = oauth.GenerateNonce();
    var normUrl     = "";
    var normParms   = "";
    
    coreParams.Add("email", email);
    coreParams.Add("name", name);
    coreParams.Add("uid", uid);
    
    var parms = MergeParams(additionalFields, coreParams);
    
    if(isSecure) {
      fastpassUri = String.Format("https://{0}/fastpass?{1}", Domain, GetQueryString(parms));
    } else {
      fastpassUri = String.Format("http://{0}/fastpass?{1}", Domain, GetQueryString(parms));     
    }
    
    Uri uri = new Uri(fastpassUri);
  
		var signature = oauth.GenerateSignature( uri, key, secret, null, null, "GET", timestamp, nonce, out normUrl, out normParms);         

    return String.Format("{0}?{1}&oauth_signature={2}", normUrl, normParms, OAuthBase.UrlEncode(signature));
    
  }
  
  public static string image(string key, string secret, string email, string name, string uid) {
    return image(key, secret, email, name, uid, false);
  }
  
  public static string image(string key, string secret, string email, string name, string uid, bool isSecure) {
    return image(key, secret, email, name, uid, isSecure, new Dictionary<string, string>());
  }
  
  public static string image(string key, string secret, string email, string name, string uid, bool isSecure, Dictionary<string, string> additionalFields) {
    string result = url(key, secret, email, name, uid, isSecure, additionalFields);    
    return "<img src=\"" + HttpUtility.HtmlEncode(result) + "\" alt=\"\" />";
  }
 
  
  public static string script(string key, string secret, string email, string name, string uid) {
    return script(key, secret, email, name, uid, false);
  }
  
  public static string script(string key, string secret, string email, string name, string uid, bool isSecure) {
    return script(key, secret, email, name, uid, isSecure, new Dictionary<string, string>());
  }
  
  public static string script(string key, string secret, string email, string name, string uid, bool isSecure, Dictionary<string, string> additionalFields) {
    string fastpass = url(key, secret, email, name, uid, isSecure, additionalFields);
    
    string result = "<script type=\"text/javascript\">\n" +
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
                    "    add_js(\"fastpass_common\", document.location.protocol + \"//getsatisfaction.com/javascripts/fastpass.js\");\n" +
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
   
  private static Dictionary<string, string> MergeParams(Dictionary<string, string> first, Dictionary<string, string> second) {
    
    Dictionary<string, string> result = new Dictionary<string, string>();
    foreach(KeyValuePair<string, string> kvp in first) {
      result[kvp.Key] = kvp.Value;
    }
    
    foreach(KeyValuePair<string, string> kvp in second) {
      result[kvp.Key] = kvp.Value;
    }
    return result;
  }
  
  private static string GetQueryString(Dictionary<string, string> parms) {
    var sb = new StringBuilder();
    
    foreach(KeyValuePair<string,string> kp in parms) {
      sb.AppendFormat("{0}={1}&", OAuthBase.UrlEncode(kp.Key), OAuthBase.UrlEncode(kp.Value));
    }
    
    var result = sb.ToString();
    
    return result.Remove(result.Length - 1 , 1);
  }
}