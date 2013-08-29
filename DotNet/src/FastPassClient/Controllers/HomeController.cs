using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace FastPassClient.Controllers
{
    [HandleError]
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            ViewData["Message"] = "Welcome to ASP.NET MVC!";

            return View();
        }

        public ActionResult About()
        {
            return View();
     
        }

        public ActionResult FastPassLogin()
        {
            Dictionary<string, string> parms = new Dictionary<string, string>();
            parms.Add("aaaaaaaaaa", "2");
            parms.Add("ZZZZZZZZZZ", "1"); // Test that sorting is working correctly.  QueryString parameters must be case sensitive sorted prior to signing.

            //var fastPassScript = FastPass.script("KEY", "SECRET", "EMAIL", "NAME", "UNIQUE-ID", false, parms);
            var fastPassScript = FastPass.script("mifnz320y2fp", "fztpqlgxq5reczdc8el8y3floquqoksk", "jking@darkops.net", "Josh", "kinger", true, parms);
            ViewData["fastPassScript"] = fastPassScript;

            return View();
        }
    }
}
