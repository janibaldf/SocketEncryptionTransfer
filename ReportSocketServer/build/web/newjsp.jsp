

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
    <title>Reporte de Paquetes y Tramaas</title>
 <link rel="stylesheet" href="bootstrap-2.3.2/css/bootstrap.css">
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="http://yandex.st/highlightjs/7.3/styles/default.min.css">
    <link rel="stylesheet" href="css/jquery.treegrid.css">

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.treegrid.js"></script>
    <script type="text/javascript">
      $(document).ready(function() {
        $('.tree').treegrid();
        $('.tree').treegrid('collapseAll');
      });
    </script>


    <script src="http://yandex.st/highlightjs/7.3/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
    <script>
      (function(i, s, o, g, r, a, m) {
        i['GoogleAnalyticsObject'] = r;
        i[r] = i[r] || function() {
          (i[r].q = i[r].q || []).push(arguments)
        }, i[r].l = 1 * new Date();
        a = s.createElement(o),
                m = s.getElementsByTagName(o)[0];
        a.async = 1;
        a.src = g;
        m.parentNode.insertBefore(a, m)
      })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

      ga('create', 'UA-43342702-1', 'maxazan.github.io');
      ga('send', 'pageview');

    </script>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <!--[if lt IE 9]>
    <script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
  </head>
  <body>

	  
<%@ page import="com.dbconection.dbManager" %>
<%@ page import="com.bean.Registry" %>
<%@ page import="java.util.List" %>


<%!    dbManager dbmanager = new dbManager();
        dbManager dbmanager2 = new dbManager(); 
List<String> listPaquetes = dbmanager.getListPaquetes();

%>

   <center><h1>Reporte de Paquetes y Tramas</h1></center>
  
 <table class="tree">
       <tr class="treegrid-1">
           <td><%out.println("<b>Listado de Estaciones</b>");%></td>
        </tr>
<%  int i=2;   
    int j=10000;   
    for (String p : listPaquetes) {
       System.out.println(p);
          out.println( "<tr class=\"treegrid-"+i+" treegrid-parent-1 negrita\" > <td>Estacion "+p+"</td></tr>");
               
            List<Registry> listRegistros = dbmanager2.getListRegistros(p);
            for (Registry r : listRegistros) {
                  out.println( "<tr class=\"treegrid-"+j+" treegrid-parent-"+i+"\"> <td>Paquete "+r.getIdpaquete()+" Trama "+r.getIdtrama()+"</td><td>"+r.toString2()+"</td></tr>");
                  j++;
            }
             i++;
        }

%>
     
      </table>	  

        


    <script type="text/javascript">
      var metas = document.getElementsByTagName('meta');
      var i;
      if (navigator.userAgent.match(/iPhone/i)) {
        for (i = 0; i < metas.length; i++) {
          if (metas[i].name == "viewport") {
            metas[i].content = "width=device-width, minimum-scale=1.0, maximum-scale=1.0";
          }
        }
        document.addEventListener("gesturestart", gestureStart, false);
      }
      function gestureStart() {
        for (i = 0; i < metas.length; i++) {
          if (metas[i].name == "viewport") {
            metas[i].content = "width=device-width, minimum-scale=0.25, maximum-scale=1.6";
          }
        }
      }</script>

  </body>
</html>
