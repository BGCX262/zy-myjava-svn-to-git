<#function getBasePath >
	<#--local path = path + req.getContextPath()-->
	<#return "http://127.0.0.1:8080/springmvc">
</#function>
<#--取得头像上传地址-->
<#function getHeadPhotoUploadUrl>
	<#local path = "http://localhost/connect-upload/profile/logo/form-action">
	<#return path>
</#function>

<#--取得相片上传地址-->
<#function getAlbumPhotoUploadUrl>
	<#local path = "http://localhost/connect-upload/gallery/photo/form-action">
	<#return path>
</#function>

<#--取得文件上传地址-->
<#function getDocFileUploadUrl>
	<#local path = "http://localhost/connect-upload/document/file/form-action">
	<#return path>
</#function>
<#--取得文件的系统的地址，包括，头像，文档，相片-->
<#function getReadFSDomain>
	<#local path = "http://localhost/connect-upload">
	<#return path>
</#function>


<#-- 图服务器域名 -->
<#function getPhotoDomain>
	<#local path =  getBasePath() + "/connect">
	<#return path>
</#function>



<#--静态服务器域名 资源包含 页面用的image,js,css-->
<#function getStaticResourceBaseDomain >
	<#local path = getBasePath() + "/resource">
	<#return path>
</#function>

<#function getStaticImgDomain>
	<#local path =  getStaticResourceBaseDomain() + "/themes/default">
	<#return path>
</#function>



<#macro stylesheet href,media="">
<#assign v=2>
<link rel="stylesheet" type="text/css" href="${getStaticResourceBaseDomain()}/themes/default${href}?v=${v}" <#if media?exists>media="${media}"</#if> ></link>
</#macro>

<#macro stylesheetIcon href>
<#assign v=2>
<link rel="shortcut icon" type="image/x-icon" href="${getStaticResourceBaseDomain()}/themes/default${href}?v=${v}" ></link>
</#macro>


<#macro script src>
<#assign v=2>
<script language="JavaScript" type="text/javascript" src="${getStaticResourceBaseDomain()}/themes/default${src}?v=${v}"></script>
</#macro>

