<%@ page isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>${pageData.activityPage.title}</title>
    <meta name="apple-mobile-web-app-title" content="海狐H5" />
    <meta name="format-detection" content="telephone=no" />
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,user-scalable=no" media="(device-height: 568px)" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="grey" />
    <link rel="stylesheet" type="text/css" href="/css/base_welcome.css?v=TIMESTAMP" media="all" />
	<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.lazyload.mini.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.cookie.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/gotoClient.js" type="text/javascript"></script>
    <script language="javascript">
		//创建页码，每次获取ajax 页码+1，达到指定页码后停止加载
		
					var praiseClick = false;
		 
					$(document).ready(function(){
						$("img").lazyload({
							failurelimit : 30,
							effect: "fadeIn",
							threshold : 600
						});
						
						 getScreenWidth();
						 var currentpage = 1;
						 var maxpage = document.getElementById("totalPage").value;
						 var categoryCode = document.getElementById("categoryCode").value;
						 
						$(window).scroll(function(){
							if($(window).scrollTop() > 100){
								$("#gotop").fadeIn(1000);//一秒渐入动画
							}else{
								$("#gotop").fadeOut(1000);//一秒渐隐动画
							}
						});
						 
						$("#gotop").click(function(){
							$('body,html').animate({scrollTop:0},1000);
						});
						
						$(".header_list>li>a").click(function(){
							$(".header_list>li>a").each(function() {
								$(this).css("border-bottom","#fff 2px solid");
								$(this).css("color","#3d4245");    
							});
							$(this).css("border-bottom","#f53e7b 2px solid");
							$(this).css("color","#f53e7b");
							
						});
						$(".nav_menu>li>a").click(function(){
							$(".nav_menu>li>a").each(function() {
								$(this).css("border","#575757 1px solid");
								$(this).css("color","#575757");   
							});
							$(this).css("border","#e62e8b 1px solid");
							$(this).css("color","#e62e8b");
							
						});
						$(".header_right").click(function(){
							$(".ment_list").css("display","block");
						});
						$(".ment_list_head_img").click(function(){
							$(".ment_list").css("display","none");
						});
						
					});

function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
} 

function getScreenWidth(){
	$("#wapBanner").attr("src","${pageData.activityPage.actBanner}");
}

function isPkClick(obj) {
	var pkClickCookie = $.cookie("pkClickCookie");
	if(pkClickCookie && pkClickCookie.indexOf('id' + $(obj).attr('pkid')) >= 0) {
		return true;
	}
	return false;
}

function showRule() {
	$('#JS_tank_rule').show();
	$('#xr_opacity').show();
}

function closeRule() {
	if($('#JS_tank_rule').is(':visible')) {
		$('#xr_opacity').hide();
	}
	$('#JS_tank_rule').hide();
}

</script>      

        
</head>
<body style="background:${pageData.activityPage.background};">
    <input type="hidden" id="totalPage" value="${totalPage}"/>
    <input type="hidden" id="categoryCode" value="${categoryCode}"/>
	<div id="screen_wrap">
    	<!--返回顶部-->
        <div id="gotop"><img src="${pageContext.request.contextPath}/images/baseAct/top.png" /></div>        
		<div id="views">
	    	<div class="homePage" id="homePage" >
                <div id="M_slider" class="swipe" style="overflow : hidden; visibility: visible;" >
                    <c:if test="${fn:length(pageData.activityGroupDataList) gt 1}" >
                    <div class="header_box">
                        <ul class="header_list">
                            <li><a href="${pageContext.request.contextPath}/activity/${actCode}/category-0" class="${categoryCode eq 0 ? 'sp-nav-vist' : ''}" >全部</a></li>
                        	<c:forEach items="${pageData.activityGroupDataList}" var="groupData" varStatus="num">
                        		<li><a href="${pageContext.request.contextPath}/activity/${actCode}/category-${groupData.pageGroup.id}" class="${categoryCode eq groupData.pageGroup.id ? 'sp-nav-vist' : ''}" >${groupData.pageGroup.title}</a></li>
                        	</c:forEach>                        
                        </ul>
                        <div class="header_right"><img src="${pageContext.request.contextPath}/images/baseAct/jt01.jpg" /></div>
                    </div>
                    <!--分类begin-->
                    <div class="ment_list">
                    	<div class="ment_list_head">
                        	<p class="ment_list_head_leimu">切换类目</p>
                            <p class="ment_list_head_img"><img src="${pageContext.request.contextPath}/images/baseAct/jt02.jpg" /></p>
                        </div>
                        <ul class="nav_menu">
                            <li><a href="${pageContext.request.contextPath}/activity/${actCode}/category-0" class="${categoryCode eq 0 ? 'nav-vist' : ''}" >全部</a></li>
                        	<c:forEach items="${pageData.activityGroupDataList}" var="groupData" varStatus="num">
                        		<li><a href="${pageContext.request.contextPath}/activity/${actCode}/category-${groupData.pageGroup.id}" class="${categoryCode eq groupData.pageGroup.id ? 'nav-vist' : ''}" >${groupData.pageGroup.title}</a></li>
                        	</c:forEach>
                        </ul>
                    </div>
                    </c:if>
                    <!--分类end-->
                    
                    <div class="swipe_wrap" >
                        <div data-index="0">
                            <a href="javascript:void(0)" class="swipe_wrap_a" style="margin-top: ${fn:length(pageData.activityGroupDataList) le 1 ? '0' : ''}" >
                                <img class="ui-lazy" id="wapBanner" src="" width="100%" height="auto"/>                                                            
                            </a>
                        </div>
                 </div>
                </div>
                
				<!--弹出框 end-->
                            
                <c:forEach items="${pageData.activityGroupDataList}" var="groupData" varStatus="num">
	                <c:choose>
		                <c:when test="${categoryCode eq '0'}" >
		                    <c:if test="${empty groupData.pageGroup.backImage}" >
			                    <div class="scree_title" id="${groupData.pageGroup.id }_title" >					
			                        <div class="scree_title_div1" style="color:${groupData.pageGroup.fontColor};background:${pageData.activityPage.background};">${groupData.pageGroup.title}</div>
				                    <div class="scree_title_div2" style="border-top:1px solid ${groupData.pageGroup.backgroundColor};background:${pageData.activityPage.background};"></div>
			                    </div>
		                    </c:if>
		                    <c:if test="${not empty groupData.pageGroup.backImage}" >
			                    <div id="${groupData.pageGroup.id }_title" class="zan_banner" >
			                    	<c:if test="${num.index eq 0}" >
			                    		<img original="${groupData.pageGroup.backImage}" src="">
			                    	</c:if>
			                    	<c:if test="${num.index ne 0}" >
			                    		<img original="${groupData.pageGroup.backImage}" src="">
			                    	</c:if>
			                    </div>
		                    </c:if>
		                 </c:when>
		                 <c:otherwise>
		                  	<c:if test="${groupData.pageGroup.id eq categoryCode or groupData.pageGroup.groupType eq 'pinpait' }">
		                  	    <c:if test="${empty groupData.pageGroup.backImage}" >
			                  	    <div class="scree_title" id="${groupData.pageGroup.id }_title" >					
					                    <div class="scree_title_div1" style="color:${groupData.pageGroup.fontColor};background:${pageData.activityPage.background};">${groupData.pageGroup.title}</div>
					                    <div class="scree_title_div2" style="border-top:1px solid ${groupData.pageGroup.backgroundColor};background:${pageData.activityPage.background};"></div>
				                    </div>
			                    </c:if>
			                    <c:if test="${not empty groupData.pageGroup.backImage}" >
				                    <div id="${groupData.pageGroup.id }_title" class="zan_banner"><img original="${groupData.pageGroup.backImage}"></div>
			                    </c:if>
		                  	</c:if>
		                 </c:otherwise>             
	                </c:choose>
						
                       <c:choose>
                        <c:when test="${groupData.pageGroup.groupType eq 'item'}">        
                        		<div class='panel' id="${groupData.pageGroup.id}" pageType="${groupData.pageGroup.groupType}" style="display: ${empty groupData.pageItemList ? 'none' : 'block'};" >
                        		<ul id='wall_wrap' class='wall_wrap'>
                        		   <div class="wall_col" id="item0wall">
                        		   <div class="Pcontainer light">                        		
                        		   <c:if test="${not empty groupData.pageItemList}">
		                       		   <c:forEach items="${groupData.pageItemList}" var="item" varStatus="num">
		                       			    <c:if test="${num.count%2 == 1}">
				                               <li id="wall_item_${item.pageItemId}" class="wall_item" onClick="gotoBuy('${item.pageItemId}','');">  
				                                    <a href="javascript:void(0)" data-navigate="true"> 
				                                        <div class="img-detail" style="padding:51% 0">
				                                            <img class="fadeIn" original="${item.pageItemImageUrl}"/> 
				                                        </div> 
				                                        <div class="title-word">${item.itemTitle} </div>
				                                        <div class="priceattention">
				                                            <div class="priceattention_left">
				                                                <span class="priceattention_price"><span>¥</span>${item.realPrice}&nbsp;&nbsp;<del><span>¥</span>${item.price}</del></span>
				                                            </div>
				                                            <div  class="priceattention_right"><img src="http://img02.taobaocdn.com/imgextra/i2/900134076/TB275.LcFXXXXaMXXXXXXXXXXXX_!!900134076.png" width="41" ></div>
				                                        </div> 
				                                    </a>
				                                </li> 
				                            </c:if>
				                       </c:forEach>
	                               </c:if>        
		                           </div>
		                          </div>                        				
                        			<div class="wall_col" id="item1wall">
                        				<div class="Pcontainer light">
                                        <c:if test="${not empty groupData.pageItemList}">
                                        <c:forEach items="${groupData.pageItemList}" var="item" varStatus="num">
                        			      <c:if test="${num.count%2 == 0}">
			                                <li id="wall_item_${item.pageItemId}" class="wall_item" onClick="gotoBuy('${item.pageItemId}','');">  
			                                    <a href="javascript:void(0)" data-navigate="true"> 
			                                        <div class="img-detail" style="padding:51% 0">
			                                            <img class="fadeIn" original="${item.pageItemImageUrl}" src="https://img.alicdn.com/imgextra/i4/169328611/TB27FHbepXXXXX3XpXXXXXXXXXX_!!169328611.png" /> 
			                                        </div> 
			                                        <div class="title-word">${item.itemTitle} </div>
			                                        <div class="priceattention">
			                                            <div class="priceattention_left">
			                                                <span class="priceattention_price"><span>¥</span>${(item.realPrice)/100}'&nbsp;&nbsp;<del><span>¥</span>${(item.price)/100}</del></span>			         
			                                            </div>
			                                       	    <div  class="priceattention_right"><img src="http://img02.taobaocdn.com/imgextra/i2/900134076/TB275.LcFXXXXaMXXXXXXXXXXXX_!!900134076.png" width="41" ></div>		                                       
			                                        </div> 
			                                    </a>
			                                </li>
			                                </c:if> 
			                              </c:forEach> 
			                              </c:if>
			                           </div>
			                         </div>                        				 
		                         </ul>
		                         </div>                  				
                        		</c:when>
                        	</c:choose>                       
               </c:forEach>

</div>
	    </div> 
  	</div>
</body>
</html>

