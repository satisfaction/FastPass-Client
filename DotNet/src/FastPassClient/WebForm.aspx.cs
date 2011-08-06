using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace FastPassClient
{
    public partial class WebForm : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            Dictionary<string, string> parms = new Dictionary<string, string>();
            parms.Add("aa", "1");
            parms.Add("ab", "1");

            // var fastPassScript = FastPass.script("KEY", "SECRET", "EMAIL", "NAME", "UNIQUE-ID", false, parms);
            var fastPassScript = FastPass.script("mifnz320y2fp", "fztpqlgxq5reczdc8el8y3floquqoksk", "jking@darkops.net", "Josh", "kinger", false, parms);

            Page.ClientScript.RegisterStartupScript(this.GetType(), "fastpass", fastPassScript);
        }
    }
}