<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
	FastPass
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

    <h2>FastPass</h2>

    <%= ViewData["fastPassScript"] %>

    <a href="#" onclick="GSFN.goto_gsfn();">Login</a>
</asp:Content>
