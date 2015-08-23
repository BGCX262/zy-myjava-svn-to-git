
<#function getBasePath >
	<#--local path = path + req.getContextPath()-->
	<#return "http://127.0.0.1:8080/springmvc">
</#function>

<#macro layoutDefault>
<!DOCTYPE html PUBLIC “-//W3C//DTD XHTML 1.0 Transitional//EN” “http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd”>
<html>
<head>
		<title> ${title!"Home Page"} </title>
		<link  href="${getBasePath()}/resources/css/style.css?v=1" type="text/css" rel="stylesheet">
</head>
	<body>  
  <div class="layout_base">
		<div class="layout_header"> <#include "header.ftl"> </div>  
		<div class="layout_body">
			<div class="layout_body_left"> 
				<#include "left-menu.ftl">
			</div>
			<div class="layout_body_right">
				<div class="content_nav" > Currrent positin </div>
				<div class="content_main">
				<#nested>
				</div>
			</div>
			<div class="clear"></div>
		</div>
			 
		<div class="layout_footer"> 
			footer   text
		</div>
	</div> 
	</body> 
</html>
	
</#macro>