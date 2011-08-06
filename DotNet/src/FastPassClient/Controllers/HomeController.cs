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
            parms.Add("aa", "1");
            parms.Add("ab", "1");

            //var fastPassScript = FastPass.script("KEY", "SECRET", "EMAIL", "NAME", "UNIQUE-ID", false, parms);
            var fastPassScript = FastPass.script("mifnz320y2fp", "fztpqlgxq5reczdc8el8y3floquqoksk", "jking@darkops.net", "Josh", "kinger", false, parms);
            ViewData["fastPassScript"] = fastPassScript;

            return View();
        }
    }
}
