<#import "/META-INF/spring.ftl" as spring />
<#import "common/common.ftl" as c />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>DWZ Workbench</title>

<link href="${c.getBasePath()}/resources/plugins/dwz/themes/default/style.css" rel="stylesheet" type="text/css" />
<link href="${c.getBasePath()}/resources/plugins/dwz/themes/css/core.css" rel="stylesheet" type="text/css" />
<link href="${c.getBasePath()}/resources/plugins/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />
<!--[if IE]>
<link href="${c.getBasePath()}/resources/plugins/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->

<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/speedup.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/jquery-1.4.4.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/jquery.cookie.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/jquery.validate.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/jquery.bgiframe.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/xheditor/xheditor-zh-cn.min.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/uploadify/scripts/swfobject.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/uploadify/scripts/jquery.uploadify.v2.1.0.js" type="text/javascript"></script>
<!--
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.core.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.util.date.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.validate.method.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.regional.zh.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.barDrag.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.drag.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.tree.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.accordion.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.ui.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.theme.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.switchEnv.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.alertMsg.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.contextmenu.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.navTab.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.tab.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.resize.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.jDialog.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.cssTable.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.stable.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.taskBar.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.ajax.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.pagination.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.datepicker.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.effects.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.panel.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.checkbox.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.history.js" type="text/javascript"></script>
<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.combox.js" type="text/javascript"></script>
-->

<script src="${c.getBasePath()}/resources/plugins/dwz/bin/dwz.min.js" type="text/javascript"></script>

<script src="${c.getBasePath()}/resources/plugins/dwz/javascripts/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("${c.getBasePath()}/resources/plugins/dwz/dwz.frag.xml", {
//		loginUrl:"${c.getBasePath()}/resources/plugins/dwz/loginsub.html", loginTitle:"登录",	// 弹出登录对话框
		loginUrl:"${c.getBasePath()}/resources/plugins/dwz/login.html",	// 跳到登录页面
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"${c.getBasePath()}/resources/plugins/dwz/themes"});
		}
	});
});
//清理浏览器内存,只对IE起效,FF不需要
if ($.browser.msie) {
	window.setInterval("CollectGarbage();", 10000);
}
</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="javascript:void(0)">标志</a>
				<ul class="nav">
					<li id="switchEnvBox"><a href="javascript:">（<span>北京</span>）切换角色</a>
						<ul>
							<li><a href="${c.getBasePath()}/resources/plugins/dwz/sidebar_bj.html">北京</a></li>
							<li><a href="${c.getBasePath()}/resources/plugins/dwz/sidebar_sh.html">上海</a></li>
							<li><a href="${c.getBasePath()}/resources/plugins/dwz/sidebar_sh.html">南京</a></li>
							<li><a href="${c.getBasePath()}/resources/plugins/dwz/sidebar_sh.html">深圳</a></li>
							<li><a href="${c.getBasePath()}/resources/plugins/dwz/sidebar_sh.html">广州</a></li>
							<li><a href="${c.getBasePath()}/resources/plugins/dwz/sidebar_sh.html">天津</a></li>
							<li><a href="${c.getBasePath()}/resources/plugins/dwz/sidebar_sh.html">杭州</a></li>
						</ul>
					</li>
					<li><a href="${c.getBasePath()}/resources/plugins/dwz/changepwd.html" target="dialog">设置</a></li>
					<!--<li><a href="javascript:void(0)">反馈</a></li>-->
					<li><a href="http://bbs.dwzjs.com" target="_blank">论坛</a></li>
					<li><a href="${c.getBasePath()}/resources/plugins/dwz/login.html">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
				</ul>
			</div>
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>界面组件</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="${c.getBasePath()}/resources/plugins/dwz/tabsPage.html" target="navTab">主框架面板</a>
								<ul>
									<li><a href="${c.getBasePath()}/resources/plugins/dwz/main.html" target="navTab" rel="main">我的主页</a></li>
									<li><a href="http://www.baidu.com" target="navTab" rel="page1">页面一(外部页面)</a></li>
									<li><a href="${c.getBasePath()}/" target="navTab" rel="page1" flesh="false">替换页面一</a></li>
									<li><a href="${c.getBasePath()}/resources/plugins/dwz/demo_page2.html" target="navTab" rel="page2">页面二</a></li>
									<li><a href="${c.getBasePath()}/resources/plugins/dwz/demo_page4.html" target="navTab" rel="page3" title="页面三（自定义标签名）">页面三</a></li>
									<li><a href="${c.getBasePath()}/resources/plugins/dwz/demo_page4.html" target="navTab" rel="page4" flesh="false">测试页面（flesh="false"）</a></li>
								</ul>
							</li>
							
							<li><a>常用组件</a>
								<ul>
									<li><a href="${c.getBasePath()}/resources/plugins/dwz/w_panel.html" target="navTab" rel="w_panel">面板</a></li>
									<li><a href="${c.getBasePath()}/resources/plugins/dwz/w_tabs.html" target="navTab" rel="w_tabs">选项卡面板</a></li>
									<li><a href="${c.getBasePath()}/resources/plugins/dwz/w_dialog.html" target="navTab" rel="w_dialog">弹出窗口</a></li>
									<li><a href="${c.getBasePath()}/resources/plugins/dwz/w_alert.html" target="navTab" rel="w_alert">提示窗口</a></li>
									<li><a href="${c.getBasePath()}/resources/plugins/dwz/w_list.html" target="navTab" rel="w_list">CSS表格容器</a></li>
									<li><a href="${c.getBasePath()}/resources/plugins/dwz/demo_page1.html" target="navTab" rel="w_table">表格容器</a></li>
									<li><a href="${c.getBasePath()}/resources/plugins/dwz/w_tree.html" target="navTab" rel="w_tree">树形菜单</a></li>
									<li><a href="${c.getBasePath()}/resources/plugins/dwz/w_accordion.html" target="navTab" rel="w_accordion">滑动菜单</a></li>
									<li><a href="${c.getBasePath()}/resources/plugins/dwz/w_editor.html" target="navTab" rel="w_editor">编辑器</a></li>
									<li><a href="${c.getBasePath()}/resources/plugins/dwz/w_datepicker.html" target="navTab" rel="w_datepicker">日期控件</a></li>
								</ul>
							</li>
									
							<li><a>表单组件</a>
								<ul>
									<li><a href="${c.getBasePath()}/resources/plugins/dwz/w_validation.html" target="navTab" rel="w_validation">表单验证</a></li>
									<li><a href="${c.getBasePath()}/resources/plugins/dwz/w_button.html" target="navTab" rel="w_button">按钮</a></li>
									<li><a href="${c.getBasePath()}/resources/plugins/dwz/w_textInput.html" target="navTab" rel="w_textInput">文本框/文本域</a></li>
									<li><a href="${c.getBasePath()}/resources/plugins/dwz/w_combox.html" target="navTab" rel="w_combox">下拉菜单</a></li>
									<li><a href="${c.getBasePath()}/resources/plugins/dwz/w_checkbox.html" target="navTab" rel="w_checkbox">多选框/单选框</a></li>
									<li><a href="${c.getBasePath()}/resources/plugins/dwz/demo_upload.html" target="navTab" rel="demo_upload">iframeCallback表单提交</a></li>
									<li><a href="${c.getBasePath()}/resources/plugins/dwz/w_uploadify.html" target="navTab" rel="w_uploadify">uploadify多文件上传</a></li>
								</ul>
							</li>
							<li><a href="${c.getBasePath()}/resources/plugins/dwz/dwz.frag.xml" target="_blank">dwz.frag.xml</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>典型页面</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder treeCheck">
							<li><a href="${c.getBasePath()}/resources/plugins/dwz/demo_page1.html" target="navTab" rel="demo_page1">查询我的客户</a></li>
							<li><a href="${c.getBasePath()}/resources/plugins/dwz/demo_page1.html" target="navTab" rel="demo_page2">表单查询页面</a></li>
							<li><a href="${c.getBasePath()}/resources/plugins/dwz/demo_page4.html" target="navTab" rel="demo_page4">表单录入页面</a></li>
							<li><a href="${c.getBasePath()}/resources/plugins/dwz/demo_page5.html" target="navTab" rel="demo_page5">有文本输入的表单</a></li>
							<li><a href="javascript:void(0)">有提示的表单输入页面</a>
								<ul>
									<li><a href="javascript:void(0)">页面一</a></li>
									<li><a href="javascript:void(0)">页面二</a></li>
								</ul>
							</li>
							<li><a href="javascript:void(0)">选项卡和图形的页面</a>
								<ul>
									<li><a href="javascript:void(0)">页面一</a></li>
									<li><a href="javascript:void(0)">页面二</a></li>
								</ul>
							</li>
							<li><a href="javascript:void(0)">选项卡和图形切换的页面</a></li>
							<li><a href="javascript:void(0)">左右两个互动的页面</a></li>
							<li><a href="javascript:void(0)">列表输入的页面</a></li>
							<li><a href="javascript:void(0)">双层栏目列表的页面</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>流程演示</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree">
							<li><a href="${c.getBasePath()}/resources/plugins/dwz/newPage1.html" target="dialog" rel="dlg_page">列表</a></li>
							<li><a href="${c.getBasePath()}/resources/plugins/dwz/newPage1.html" target="dialog" rel="dlg_page">列表</a></li>
							<li><a href="${c.getBasePath()}/resources/plugins/dwz/newPage1.html" target="dialog" rel="dlg_page2">列表</a></li>
							<li><a href="${c.getBasePath()}/resources/plugins/dwz/newPage1.html" target="dialog" rel="dlg_page2">列表</a></li>
							<li><a href="${c.getBasePath()}/resources/plugins/dwz/newPage1.html" target="dialog" rel="dlg_page2">列表</a></li>
						</ul>
					</div>
				</div>

			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:void(0)"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:void(0)">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent">
					<div>
						<div class="accountInfo">
							<div class="alertInfo">
								<h2><a href="${c.getBasePath()}/resources/plugins/dwz/doc/dwz-user-guide.pdf" target="_blank">DWZ框架使用手册(PDF)</a></h2>
								<a href="${c.getBasePath()}/resources/plugins/dwz/doc/dwz-user-guide.swf" target="_blank">DWZ框架演示视频</a>
							</div>
							<div class="right">
								<p><a href="${c.getBasePath()}/resources/plugins/dwz/doc/dwz-user-guide.zip" target="_blank" style="line-height:19px">DWZ框架使用手册(CHM)</a></p>
								<p><a href="${c.getBasePath()}/resources/plugins/dwz/doc/dwz-ajax-develop.swf" target="_blank" style="line-height:19px">DWZ框架Ajax开发视频教材</a></p>
							</div>
							<p><span>DWZ富客户端框架</span></p>
							<p><a href="${c.getBasePath()}/resources/plugins/dwz/demo_page2.html" target="dialog">DWZ研发组</a></p>
						</div>
						<div class="pageFormContent" layoutH="80">
							<p>在线演示地址 http://demo.dwzjs.com</p>
							<p>DWZ框架使用手册 <a href="${c.getBasePath()}/resources/plugins/dwz/doc/dwz-user-guide.pdf" target="_blank">http://demo.dwzjs.com/doc/dwz-user-guide.pdf</a></p>
							<p>Ajax开发视频教材 <a href="${c.getBasePath()}/resources/plugins/dwz/doc/dwz-ajax-develop.swf" target="_blank">http://demo.dwzjs.com/doc/dwz-ajax-develop.swf</a></p>
							<p>DWZ框架演示视频 <a href="${c.getBasePath()}/resources/plugins/dwz/doc/dwz-user-guide.swf" target="_blank">http://demo.dwzjs.com/doc/dwz-user-guide.swf</a></p>
							<p>Google Code下载 <a href="${c.getBasePath()}/resources/plugins/dwz/http://code.google.com/p/dwz/" target="_blank">http://code.google.com/p/dwz/</a></p>

<div class="divider"></div>
<h2>常见问题及解决:</h2>
<pre style="margin:5px;line-height:1.4em">
Error loading XML document: dwz.frag.xml
直接用IE打开index.html弹出一个对话框：Error loading XML document: dwz.frag.xml
原因：没有加载成功dwz.frag.xml。IE ajax laod本地文件有限制, 是ie安全级别的问题, 不是框架的问题。
解决方法：用firefox打开或部署到apache下。

IIS不能使用Ajax解决方案
IIS下firebug调试报错ajax 405 Method Not Allowed。
Http 405原因是IIS不允许ajax访问*.htm或是*.html后缀的网页，这是IIS的问题，不是框架bug。
</pre>

<div class="divider"></div>
<h2>有偿服务请联系:</h2>
<p style="color:red">support@dwzjs.com</p>
						</div>

					</div>
				</div>
			</div>
		</div>

		<div id="taskbar" style="left:0px; display:none;">
			<div class="taskbarContent">
				<ul></ul>
			</div>
			<div class="taskbarLeft taskbarLeftDisabled" style="display:none;">taskbarLeft</div>
			<div class="taskbarRight" style="display:none;">taskbarRight</div>
		</div>
		<div id="splitBar"></div>
		<div id="splitBarProxy"></div>
	</div>

	<div id="footer">Copyright &copy; 2010 <a href="${c.getBasePath()}/resources/plugins/dwz/demo_page2.html" target="dialog">DWZ研发组</a></div>

<!--拖动效果-->
	<div class="resizable"></div>
<!--阴影-->
	<div class="shadow" style="width:508px; top:148px; left:296px;">
		<div class="shadow_h">
			<div class="shadow_h_l"></div>
			<div class="shadow_h_r"></div>
			<div class="shadow_h_c"></div>
		</div>
		<div class="shadow_c">
			<div class="shadow_c_l" style="height:296px;"></div>
			<div class="shadow_c_r" style="height:296px;"></div>
			<div class="shadow_c_c" style="height:296px;"></div>
		</div>
		<div class="shadow_f">
			<div class="shadow_f_l"></div>
			<div class="shadow_f_r"></div>
			<div class="shadow_f_c"></div>
		</div>
	</div>
	<!--遮盖屏幕-->
	<div id="alertBackground" class="alertBackground"></div>
	<div id="dialogBackground" class="dialogBackground"></div>

	<div id='background' class='background'></div>
	<div id='progressBar' class='progressBar'>数据加载中，请稍等...</div>

<script type="text/javascript">
/*
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-16716654-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? ' https://ssl' : ' http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
  */
</script>

</body>
</html>