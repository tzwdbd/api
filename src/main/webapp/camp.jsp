<%@ page isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="no-cache" http-equiv="Cache-control">
<title>${pageTitle }</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="http://g.alicdn.com/mtb/??lib-flexible/0.3.2/flexible_css.js,lib-flexible/0.3.2/flexible.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/gotoClient.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/css/haitao_zt.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/css/menutab.css" rel="stylesheet">
</head>

<body>
	<p style="font-size:0;"><img src="${banner }" style="width:100%;" /></p>
    <p class="ht-pre fontSize12">${comments }</p>
    <p class="top-banner"><img src="../images/ht_h31.png" class="w100" /></p>
    <ul class="goods-list">
     <c:forEach var="goodsAbridged" items="${goodsAbridgedList}" >
    	<li>
    	  	<div  onclick="gotoBuy('${goodsAbridged.goods_id}','${goodsAbridged.selected_sku.seq}')">
	            <p class="goods-img"><img src="${goodsAbridged.selected_sku.image_url}" /></p>
	            <p class="goods-name fontSize12">${goodsAbridged.title}</p>
	            <p class="goods-go">
	            <span class="s1 fontSize14">￥${goodsAbridged.selected_sku.fixed_price}</span>
	                <span class="s2">购买</span>
	            </p>
            </div>
        </li>
    </c:forEach>    
    </ul>
	<div class="groupMainTab">
		<div class="tabNormal floatLeft" onclick="window.location='http://m.haihu.com/index.html';"><img src="${pageContext.request.contextPath}/images/homenor@2x.png" class="tabImg"/><div class="samlltitle">海狐首页</div></div>
		<div class="tabNormal floatLeft" onclick="window.location='http://m.haihu.com/group.html';"><img src="${pageContext.request.contextPath}/images/pinnor@2x.png" class="tabImg"/><div class="samlltitle">极速拼团</div></div>
		<div class="tabNormal floatLeft" onclick="window.location='http://m.haihu.com/shoppingCar.html';"><img src="${pageContext.request.contextPath}/images/shoppingnor@2x.png" class="tabImg"/><div class="samlltitle">购物车</div></div>
		<div class="tabNormal floatLeft" onclick="window.location='http://m.haihu.com/mine.html';"><img src="${pageContext.request.contextPath}/images/minenor@2x.png" class="tabImg"/><div class="samlltitle">我的海狐</div></div>
		<div class="clearFloat"></div>
	</div>
</body>
</html>