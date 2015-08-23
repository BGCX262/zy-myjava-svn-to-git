<#import "/META-INF/spring.ftl" as spring />
<#import "common/common.ftl" as c />
<#--assign title="Dynamic home page title" in c -->
<@c.layoutDefault>
<div class="content_detail">
<h1>
	Hello world!  
</h1>
<P>  The time on the server is ${serverTime}. </P>
</div>
</@c.layoutDefault>
