<%@ page isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>海狐11.11</title> 
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<meta http-equiv="Pragma" content="no-cache" /> 
	<meta http-equiv="Cache-Control" content="no-cache" /> 
	<meta http-equiv="Expires" content="0" /> 
   
  	<link type="text/css" rel="stylesheet" href="css/common.css" />
	<link type="text/css" rel="stylesheet" href="css/1111.css" />
	<script type="text/javascript" src="js/common/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="js/common/global.js"></script>
	<script type="text/javascript" src="js/1111.js"></script>
	<script type="text/javascript" src="js/common/mustache.js"></script>
	<script type="text/javascript" src="js/common/jquery.lazyload.mini.js"></script>
	<script type="text/javascript" src="js/common/md5.js"></script>
</head>
<body>
<div id="_body">
		<div class="_head font-yahei">
		    <table>
				<tr>
					<td class="m1"><a href="javascript:window.location=location.protocol+'//'+location.host+'/1111'">海狐全球购</a></td>
					<td class="m2"><a href="javascript:window.location=location.protocol+'//'+location.host+'/order/orderMergedList'">我的订单</a></td>
					<td class="m4"><div class="horizonLine"></div></td>
					<td class="m3"><a href="http://www.taofen8.com">返回淘粉吧</a></td>
				</tr>
			</table>
		</div>
	<div class="haihuBackGround"><img src="images/1111_bg.png"></div>
	<div class="root">
		<div class="content">
			<!--徐浩 TODO-->

			<div class="rushBuyTable">
				<div id = "rushBuyHeaderId" class="rushBuyHeader"></div>
				<div id = "rushBuyId" class="rushBuy floatLeft"></div>
			</div>

			<!--傅圆 TODO-->
			<div class="goodsList">
				<div class="hotBigBang" id="hot_label"><!--海外最热爆款尖货-->
					<div class="hotTitle">
						<img src="images/ssy_hot_bang.png">
					</div>
					<div class="hotItem"><!--for every goods item-->
					 	<c:forEach var="topGoodsEntity" items="${topGoodsList}" >
						<c:if test="${topGoodsEntity.type==0}"> <!-- 尖货区直邮商品  -->
							<div class="hotProinfo">
								<div class="hotTitleImg">
									<div class="hotImgContain">
										<div class="hotPic">
											<a href="/detail/global/${topGoodsEntity.globalGoods.goods_id}.html" target="_blank">
												<img class="hotImgLazyload"
												 style="height:30%"
												 original="${topGoodsEntity.globalGoods.selected_sku.image_url}" 
												 src="images/ssy_moren.png" 
												 onload="global.changeImageSize1111(this)"  
												 title="${topGoodsEntity.globalGoods.title}"><!--goods Picture-->
												<img class="hotBrandImg" 
												src="${topGoodsEntity.globalGoods.pc_country_icon}"><!--goods Brand-->
											</a>
										</div>
										<div class="hotTit">
										    <a title="${topGoodsEntity.globalGoods.title}">
										    <b>${topGoodsEntity.globalGoods.forend_discount}折</b>
										    | ${topGoodsEntity.globalGoods.title}
										    </a>
										</div>
									</div>
								</div>
								<div class="hotPriceAbout">
									<div class="hotPriceData">
										<span class="hotPriceSign">&yen;</span>
										<span class="hotPiriceNumber">${topGoodsEntity.globalGoods.selected_sku.fixed_price}</span>
									</div>
									<div class="hotChangePriceImg">
										<img src="images/ssy_arrow_rate.jpg">
									</div>
									<div class="hotFirstOrder">首单立减&yen;${topGoodsEntity.discount}</div>
								</div>
	
								<div class="hotPriceAbout2">
									<div class="hotTmallPrice">天猫价<s class="hotTmallPriceValue">&yen;${topGoodsEntity.globalGoods.tmall_price}</s></div>
									<div class="hotFromWebSite">${topGoodsEntity.globalGoods.forend_dispatch}</div>
									
								</div>
								<div class="hotGoBuyNow" onclick="location.href='/detail/global/${topGoodsEntity.globalGoods.goods_id}.html'">
									<img class="hotGoBuyNowIcon" src="images/ssy_buy_color.png">
									<span>立即抢购</span>
									<img class="hotGoBuyNowIcon" src="images/ssy_buy_color.png">
								</div>
							</div>
						</c:if>
						
						<c:if test="${topGoodsEntity.type==1}"> <!-- 尖货区保税区商品  -->
							<div class="hotProinfo">
								<div class="hotTitleImg">
									<div class="hotImgContain">
										<div class="hotPic">
											<a href="/detail/group/${topGoodsEntity.bondedGoods.package_id}.html" target="_blank"> <img
												style="height:30%"
												class="hotImgLazyload"
												src="${topGoodsEntity.bondedGoods.image_url}"
												onload="global.changeImageSize1111(this)"
												title="${topGoodsEntity.bondedGoods.title}">
											<!--goods Picture--> <img class="hotBrandImg"
												src="${topGoodsEntity.bondedGoods.forend_pc_country_icon}">
											<!--goods Brand-->
											</a>
										</div>
										<div class="hotTit">
											<a title="${topGoodsEntity.bondedGoods.title}"> 
											<b>${topGoodsEntity.bondedGoods.forend_discount}折</b>
											| ${topGoodsEntity.bondedGoods.title}
											</a>
										</div>
									</div>
								</div>
								<div class="hotPriceAbout">
									<div class="hotPriceData">
										<span class="hotPriceSign">&yen;</span> <span
											class="hotPiriceNumber">${topGoodsEntity.bondedGoods.fixed_price}</span>
									</div>
									<div class="hotChangePriceImg">
										<img src="images/ssy_arrow_rate.jpg">
									</div>
									<div class="hotFirstOrder">首单立减&yen;${topGoodsEntity.discount}</div>
								</div>

								<div class="hotPriceAbout2">
									<div class="hotTmallPrice">
										天猫价格<s class="hotTmallPriceValue">${topGoodsEntity.bondedGoods.tmall_price}</s>
									</div>
									<div class="hotFromWebSite">${topGoodsEntity.bondedGoods.forend_dispatch}</div>

								</div>
								<div class="hotGoBuyNow" onclick="location.href='/detail/group/${topGoodsEntity.bondedGoods.package_id}.html'" target="_blank">
									<img class="hotGoBuyNowIcon" src="images/ssy_buy_color.png">
									<span>立即抢购</span> <img class="hotGoBuyNowIcon"
										src="images/ssy_buy_color.png">
								</div>
							</div>
							</c:if>
						</c:forEach>   
					</div>

				</div>
				
				
				<div class="bigBrandHappy" id="big_label"><!--海外直邮大牌狂欢-->
					<div class="bigTitle">
						<img src="images/ssy_big_bang.png">
					</div>

					
						<c:forEach var="globalTagGoodsEntity" items="${globalTagGoodsList}" >
						
						<div class="bigItem"
						<c:if test="${globalTagGoodsEntity.tag_id==1}">
							id="big_0"
						</c:if>
						 <c:if test="${globalTagGoodsEntity.tag_id==2}">
							id="big_1"
						</c:if>
						<c:if test="${globalTagGoodsEntity.tag_id==3}">
							id="big_2"
						</c:if>
						<c:if test="${globalTagGoodsEntity.tag_id==4}">
							id="big_3"
						</c:if>
						 <c:if test="${globalTagGoodsEntity.tag_id==5}">
							id="big_4"
						</c:if>
						<c:if test="${globalTagGoodsEntity.tag_id==6}">
							id="big_5"
						</c:if>
						<c:if test="${globalTagGoodsEntity.tag_id==8}">
							id="big_6"
						</c:if>
						<c:if test="${globalTagGoodsEntity.tag_id==9}">
							id="big_7"
						</c:if>
						<c:if test="${globalTagGoodsEntity.tag_id==10}">
							id="big_8"
						</c:if>
						>
						<div class="bigNav">
							
							<c:choose>
						       <c:when test="${globalTagGoodsEntity.tag_id==1}">
						             <a class="bigNavSeleted" href="#big_0"><img class="bigSeletedPlane" src="images/ssy_plane.png">女鞋</a>
						       </c:when>
						       <c:otherwise>
						              <a class="bigNavUnSeleted" href="#big_0">女鞋</a>
						       </c:otherwise>
							</c:choose>
							
							<c:choose>
						       <c:when test="${globalTagGoodsEntity.tag_id==2}">
						             <a class="bigNavSeleted" href="#big_1"><img class="bigSeletedPlane" src="images/ssy_plane.png">箱包</a>
						       </c:when>
						       <c:otherwise>
						              <a class="bigNavUnSeleted" href="#big_1">箱包</a>
						       </c:otherwise>
							</c:choose>
							
							<c:choose>
						       <c:when test="${globalTagGoodsEntity.tag_id==3}">
						             <a class="bigNavSeleted" href="#big_2"><img class="bigSeletedPlane" src="images/ssy_plane.png">女装</a>
						       </c:when>
						       <c:otherwise>
						              <a class="bigNavUnSeleted" href="#big_2">女装</a>
						       </c:otherwise>
							</c:choose>
							
							<c:choose>
						       <c:when test="${globalTagGoodsEntity.tag_id==4}">
						             <a class="bigNavSeleted" href="#big_3"><img class="bigSeletedPlane" src="images/ssy_plane.png">婴童专区</a>
						       </c:when>
						       <c:otherwise>
						              <a class="bigNavUnSeleted" href="#big_3">婴童专区</a>
						       </c:otherwise>
							</c:choose>
							
							<c:choose>
						       <c:when test="${globalTagGoodsEntity.tag_id==5}">
						             <a class="bigNavSeleted" href="#big_4"><img class="bigSeletedPlane" src="images/ssy_plane.png">生活用品</a>
						       </c:when>
						       <c:otherwise>
						              <a class="bigNavUnSeleted" href="#big_4">生活用品</a>
						       </c:otherwise>
							</c:choose>
							
							<c:choose>
						       <c:when test="${globalTagGoodsEntity.tag_id==6}">
						             <a class="bigNavSeleted" href="#big_5"><img class="bigSeletedPlane" src="images/ssy_plane.png">美妆个护</a>
						       </c:when>
						       <c:otherwise>
						              <a class="bigNavUnSeleted" href="#big_5">美妆个护</a>
						       </c:otherwise>
							</c:choose>
							
							
							<c:choose>
						       <c:when test="${globalTagGoodsEntity.tag_id==8}">
						             <a class="bigNavSeleted" href="#big_6"><img class="bigSeletedPlane" src="images/ssy_plane.png">男鞋</a>
						       </c:when>
						       <c:otherwise>
						              <a class="bigNavUnSeleted" href="#big_6">男鞋</a>
						       </c:otherwise>
							</c:choose>
							
							<c:choose>
						       <c:when test="${globalTagGoodsEntity.tag_id==9}">
						             <a class="bigNavSeleted" href="#big_7"><img class="bigSeletedPlane" src="images/ssy_plane.png">男装</a>
						       </c:when>
						       <c:otherwise>
						              <a class="bigNavUnSeleted" href="#big_7">男装</a>
						       </c:otherwise>
							</c:choose>
							
							<c:choose>
						       <c:when test="${globalTagGoodsEntity.tag_id==10}">
						             <a class="bigNavSeleted" href="#big_8"><img class="bigSeletedPlane" src="images/ssy_plane.png">运动鞋</a>
						       </c:when>
						       <c:otherwise>
						              <a class="bigNavUnSeleted" href="#big_8">运动鞋</a>
						       </c:otherwise>
							</c:choose>
							
						</div>
						<c:forEach var="globalGoodsEntity" items="${globalTagGoodsEntity.goods_list}" >
						<div class="bigProinfo">
							<div class="bigTitleImg">
								<div class="bigImgContain">
									<div class="bigPic">
										<a href="/detail/global/${globalGoodsEntity.goods_id}.html" target="_blank">
											<img class="bigImgLazyload"
											style="height:30%" 
											onload="global.changeImageSize1111(this)" 
											original="${globalGoodsEntity.selected_sku.image_url}" 
											src="images/ssy_moren.png"
											title="${globalGoodsEntity.title}"><!--goods Picture-->
											<img class="bigBrandImg" 
											src="${globalGoodsEntity.pc_country_icon}"><!--goods Brand-->
										</a>
									</div>
									<div class="bigTit">
										<a href="/detail/global/${globalGoodsEntity.goods_id}.html" title="${globalGoodsEntity.title}">
											<b>${globalGoodsEntity.forend_discount}折</b>
											 | ${globalGoodsEntity.title}
										</a>
									</div>
								</div>
							</div>
							<div class="bigPriceAbout">
								<div class="bigPriceData">
									<span class="bigPriceSign">&yen;</span>
									<span class="bigPiriceNumber">${globalGoodsEntity.selected_sku.fixed_price}</span>
									<span class="bigTmallPrice">天猫价   &yen;${globalGoodsEntity.tmall_price}</span>
								</div>

							</div>
							<div class="bigBottom">
								<div class="bigFromWebSite"><div class="bigFromWebSiteSub">${globalGoodsEntity.forend_dispatch}</div></div>
								<div class="bigGoBuyNow" onclick="location.href='/detail/global/${globalGoodsEntity.goods_id}.html'" target="_blank">
									<span>立即抢购</span>
									<img class="bigGoBuyNowIcon" src="images/ssy_triangle.png">
								</div>
							</div>
						</div>
						</c:forEach>
						</div>
						</c:forEach>
				</div>
				

				<div class="niceFreeTaxes" id="nice_label"><!--海外好货保税专场-->
					<div class="niceTitle">
						<img src="images/ssy_nice_bang.png">
					</div>

					<div class="niceItem"><!--for every goods item-->
						<c:forEach var="bondedGoodsEntity" items="${bondedGoodsList}" >
							<div class="niceProinfo">
								<div class="niceTitleImg">
									<div class="niceImgContain">
										<div class="nicePic">
											<a href="/detail/group/${bondedGoodsEntity.package_id}.html" target="_blank">
												<img class="niceImgLazyload"
												style="height:30%"
												src="images/ssy_moren.png" 
												onload="global.changeImageSize1111(this)"
												 original="${bondedGoodsEntity.image_url}"
												  title="${bondedGoodsEntity.title}"><!--goods Picture-->
												<img class="niceBrandImg" 
												src="${bondedGoodsEntity.forend_pc_country_icon}"><!--goods Brand-->
											</a>
										</div>
										<div class="niceTit">
											<a title="${bondedGoodsEntity.title} ">
												<b>${bondedGoodsEntity.forend_discount}折</b>
												 | ${bondedGoodsEntity.title} 
											</a>
										</div>
									</div>
								</div>
								<div class="nicePriceAbout">
									<div class="nicePriceData">
										<span class="nicePriceSign">&yen;</span>
										<span class="nicePiriceNumber">${bondedGoodsEntity.fixed_price}</span>
										<span class="niceTmallPrice">天猫价   &yen;${bondedGoodsEntity.tmall_price}</span>
									</div>
									
								</div>
								<div class="niceGoBuyNow" onclick="location.href='/detail/group/${bondedGoodsEntity.package_id}.html'" target="_blank">
									<span >立即抢购</span>
									<img class="niceGoBuyNowIcon" src="images/ssy_triangle.png">
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="navigationGuide"><!--右侧导航-->
		<div class="guideImg">
			<img src="images/ssy_nav_guide_top.png">
		</div>
		<div class="unIncludeImg">
			<a  href="#hot_label">
				<div class="hotGuide">
					<img src="images/ssy_nav_guide_star.png">
					<span>&nbsp;爆款尖货</span>
				</div>
			</a>
			<a  href="#big_label">
				<div class="bigGuide">
					<img src="images/ssy_nav_guide_star.png">
					<span>&nbsp;海外直邮</span>
				</div>
			</a>
			<a  href="#nice_label">
				<div class="niceGuide">
					<img src="images/ssy_nav_guide_star.png">
					<span>&nbsp;保税专场</span>
				</div>
			</a>
			<a href="#">
				<div class="backToTop">
					<img src="images/ssy_top.png">
					<div class="subToTop">回顶部</div>
				</div>
			</a>

		</div>
	</div>
	
	<div class="clearFloat">
		<div class="addDistance"></div>
		<div class="indexBottom">
			copyright©2011-2015 haihu.com 杭州雷讯网络技术股份有限公司︱浙ICP备12042948号-1
		</div>
	</div>
	
	</div>

	<script type="text/html" id="rushBuyHeader">
		{{#one_punch_list}}
			<div class="{{divTitle}} rushBuyBar groupTitle floatLeft" gIndex="{{groupIndex}}">
				<div class="buyDate floatLeft">{{day}}</div>
				<div class="rushBuyBarLine floatLeft"></div>
				<div class="rushBuyBarArrow"></div>
			</div>
		{{/one_punch_list}}

	</script>




	<script type="text/html" id="leftRushBuySelect">
		{{#goods_list}}
		<div class="{{goodsGroupName}} selectGoodsImageDiv alignCenter floatLeft" gIndex = "{{goodsGroupIndex}}">
			<div class="leftRushBuySelectGoodsImage">
				<img src="http://img.haihu.com/01151010d60555fa55a146d38196537eae701237.jpg@99q_1c_1e_250w.jpg" onload="global.changeImageSize(this)" style="width:100%;"/>
			</div>
			<div class="selectGoodsCircle">
				<div class="selectGoodsMask"><div class="leftRushBuySelectTime">{{gTime}}</div></div>

			</div>
		</div>
		{{/goods_list}}

	</script>


	<script type = "text/html" id="rushBuy">
		{{#one_punch_list}}
		<div class="{{groupName}} group hidden">

			<div id = "rushBuyGoodsDivId" class="{{rushBuyGoodsDivName}} rushBuyGoodsDiv floatLeft">
				<div class="leftRushBuyDiv floatLeft">
					<div class="leftRushBuyContent">
							<img src={{showGoodsImage}}  onload="global.changeImageSize(this)" class="rushBuyGoodsImage" style="width:100%;"/>
					</div>

				</div>

				<div class="middleRushBuyDiv floatLeft">
					<div class="middleRushBuyTitle">
						{{title}}
					</div>
					<div class="middleRushBuyPrice">
						<div class="middleRushBuyCurrentPrice floatLeft">{{one_punch_fixed_price}}</div>
						<div class="middleRushBuyUnit floatLeft">元/件</div>
						<div class="middleRushBuyOriginalPriceDiv floatLeft">
							<span>市场价</span>
							<span class="middleRushBuyOriginalPrice">{{one_punch_tag_price}}元</span>
						</div>
						<div class="clearFloat"></div>
					</div>

					<div class="middleRushBuyStock">
						限量{{one_punch_storage}}件
					</div>
				</div>
				<div class="clearFloat"></div>

			</div>

			<div class="leftRushBuySelect ">
				{{#goods_list}}
				<div class="{{goodsGroupName}} selectGoodsImageDiv alignCenter floatLeft" gIndex = "{{goodsGroupIndex}}">
					<div class="leftRushBuySelectGoodsImage">
						<img src="{{image_url}}" onload="global.changeImageSize(this)" style="width:100%;"/>
					</div>
					<div class="selectGoodsCircle">
						<div class="selectGoodsMask"></div>
						<div class="leftRushBuySelectTime">{{gTime}}</div>
					</div>
				</div>
				{{/goods_list}}
			</div>

			<div id = "rightRushBuyDivId" class="{{rightRushBuyDivName}} rightRushBuyDiv floatLeft">
				<div class="rightRushBuyDivContent">
					<div class="rightRushBuyDivStcok alignCenter">{{totalStorage}}份</div>
					<div class="rightRushBuyDivPrice alignCenter">{{one_punch_fixed_price}}元商品准点开抢</div>
					<div class="rightRushBuyDivPrompt">扫码抢货</div>
					<div class="rightRushBuyDivGrab">
						<div class="rightRushBuyLeftLine floatLeft"></div>
						<div class="rightRushBuyDivGrabTitle floatLeft">距离本场活动结束</div>
						<div class="rightRushBuyRightLine floatLeft"></div>
						<div class="clearFloat"></div>
					</div>

					<div class="rightRushBuyDivTime alignCenter">
						<div class="rushBuyTimeHour floatLeft"><span class="hour"></span></div><div class="timeSegment floatLeft">:</div>
						<div class="rushBuyTimeMinute floatLeft"><span class="minute"></span></div><div class="timeSegment floatLeft">:</div>
						<div class="rushBuyTimeSecond floatLeft"><span class="second"></span></div>
						<div class="clearFloat"></div>
					</div>
					<div class="QBCodeImage alignCenter">
						<img src="{{barcode}}" onload="global.changeImageSize(this)" style="width:100%;"/>
					</div>
				</div>

			</div>


			<div class="clearFloat"></div>


		</div>
		{{/one_punch_list}}
	</script>



	<script type="text/html" id="rushBuyGoodsDiv0">
			<div class="tobeTips hidden">
				<div class="timeTips">{{gHour}}点开始</div>
			</div>
			<div class="rushBuyFinishedImage hidden"></div>
			<div class="leftRushBuyDiv floatLeft">
				<div class="leftRushBuyContent">
						<img src={{image_url}}  onload="global.changeImageSize(this)" class="rushBuyGoodsImage" style="height:100%;"/>
				</div>

			</div>

			<div class="middleRushBuyDiv floatLeft">
				<div class="middleRushBuyTitle">
					{{title}}
				</div>
				<div class="middleRushBuyPrice">
					<div class="middleRushBuyCurrentPrice floatLeft">{{one_punch_fixed_price}}</div>
					<div class="middleRushBuyUnit floatLeft">元/件</div>
					<div class="middleRushBuyOriginalPriceDiv floatLeft">
						<span>市场价</span>
						<span class="middleRushBuyOriginalPrice">{{one_punch_tag_price}}元</span>
					</div>
					<div class="clearFloat"></div>
				</div>

				<div class="middleRushBuyStock">
					限量{{one_punch_storage}}件
				</div>

			</div>
			<div class="clearFloat"></div>

	</script>


	<script type="text/html" id="rushBuyGoodsDiv1">
			<div class="tobeTips hidden">
				<div class="timeTips">{{gHour}}点开始</div>
			</div>
			<div class="rushBuyFinishedImage hidden"></div>
			<div class="leftRushBuyDiv floatLeft">
				<div class="leftRushBuyContent">
						<img src={{image_url}}  onload="global.changeImageSize(this)" class="rushBuyGoodsImage" style="height:100%;"/>
				</div>

			</div>

			<div class="middleRushBuyDiv floatLeft">
				<div class="middleRushBuyTitle">
					{{title}}
				</div>
				<div class="middleRushBuyPrice">
					<div class="middleRushBuyCurrentPrice floatLeft">{{one_punch_fixed_price}}</div>
					<div class="middleRushBuyUnit floatLeft">元/件</div>
					<div class="middleRushBuyOriginalPriceDiv floatLeft">
						<span>市场价</span>
						<span class="middleRushBuyOriginalPrice">{{one_punch_tag_price}}元</span>
					</div>
					<div class="clearFloat"></div>
				</div>

				<div class="middleRushBuyStock">
					限量{{one_punch_storage}}件
				</div>

			</div>
			<div class="clearFloat"></div>

	</script>
	<script type="text/html" id="rushBuyGoodsDiv2">
			<div class="tobeTips hidden">
				<div class="timeTips">{{gHour}}点开始</div>
			</div>
			<div class="rushBuyFinishedImage hidden"></div>
			<div class="leftRushBuyDiv floatLeft">
				<div class="leftRushBuyContent">
						<img src={{image_url}} onload="global.changeImageSize(this)" class="rushBuyGoodsImage" style="height:100%;"/>
				</div>

			</div>

			<div class="middleRushBuyDiv floatLeft">
				<div class="middleRushBuyTitle">
					{{title}}
				</div>
				<div class="middleRushBuyPrice">
					<div class="middleRushBuyCurrentPrice floatLeft">{{one_punch_fixed_price}}</div>
					<div class="middleRushBuyUnit floatLeft">元/件</div>
					<div class="middleRushBuyOriginalPriceDiv floatLeft">
						<span>市场价</span>
						<span class="middleRushBuyOriginalPrice">{{one_punch_tag_price}}元</span>
					</div>
					<div class="clearFloat"></div>
				</div>

				<div class="middleRushBuyStock">
					限量{{one_punch_storage}}件
				</div>

			</div>
			<div class="clearFloat"></div>

	</script>


	<script type="text/html" id="rightRushBuyDiv0">
		<div class="rightRushBuyDivContent">
			<div class="rightRushBuyDivStcok alignCenter">{{totalStorage}}份</div>
			<div class="rightRushBuyDivPrice alignCenter">{{one_punch_fixed_price}}元商品准点开抢</div>
			<div class="rightRushBuyDivPrompt">扫码抢货</div>
			<div class="rightRushBuyDivGrab">
				<div class="rightRushBuyLeftLine floatLeft"></div>
				<div class="rightRushBuyDivGrabTitle floatLeft">距离本场活动结束</div>
				<div class="rightRushBuyRightLine floatLeft"></div>
				<div class="clearFloat"></div>
			</div>

			<div class="rightRushBuyDivTime alignCenter">
				<div class="rushBuyTimeHour floatLeft"><span class="hour"></span></div><div class="timeSegment floatLeft">:</div>
				<div class="rushBuyTimeMinute floatLeft"><span class="minute"></span></div><div class="timeSegment floatLeft">:</div>
				<div class="rushBuyTimeSecond floatLeft"><span class="second"></span></div>
				<div class="clearFloat"></div>
			</div>
			<div class="QBCodeImage alignCenter">
				<img src="{{barcode}}" onload="global.changeImageSize(this)" style="width:100%;"/>
			</div>
		</div>

	</script>
	<script type="text/html" id="rightRushBuyDiv1">
		<div class="rightRushBuyDivContent">
			<div class="rightRushBuyDivStcok alignCenter">{{totalStorage}}份</div>
			<div class="rightRushBuyDivPrice alignCenter">{{one_punch_fixed_price}}元商品准点开抢</div>
			<div class="rightRushBuyDivPrompt">扫码抢货</div>
			<div class="rightRushBuyDivGrab">
				<div class="rightRushBuyLeftLine floatLeft"></div>
				<div class="rightRushBuyDivGrabTitle floatLeft">距离本场活动结束</div>
				<div class="rightRushBuyRightLine floatLeft"></div>
				<div class="clearFloat"></div>
			</div>

			<div class="rightRushBuyDivTime alignCenter">
				<div class="rushBuyTimeHour floatLeft"><span class="hour"></span></div><div class="timeSegment floatLeft">:</div>
				<div class="rushBuyTimeMinute floatLeft"><span class="minute"></span></div><div class="timeSegment floatLeft">:</div>
				<div class="rushBuyTimeSecond floatLeft"><span class="second"></span></div>
				<div class="clearFloat"></div>
			</div>
			<div class="QBCodeImage alignCenter">
				<img src="{{barcode}}" onload="global.changeImageSize(this)" style="width:100%;"/>
			</div>
		</div>

	</script>
	<script type="text/html" id="rightRushBuyDiv2">
		<div class="rightRushBuyDivContent">
			<div class="rightRushBuyDivStcok alignCenter">{{totalStorage}}份</div>
			<div class="rightRushBuyDivPrice alignCenter">{{one_punch_fixed_price}}元商品准点开抢</div>
			<div class="rightRushBuyDivPrompt">扫码抢货</div>
			<div class="rightRushBuyDivGrab">
				<div class="rightRushBuyLeftLine floatLeft"></div>
				<div class="rightRushBuyDivGrabTitle floatLeft">距离本场活动结束</div>
				<div class="rightRushBuyRightLine floatLeft"></div>
				<div class="clearFloat"></div>
			</div>

			<div class="rightRushBuyDivTime alignCenter">
				<div class="rushBuyTimeHour floatLeft"><span class="hour"></span></div><div class="timeSegment floatLeft">:</div>
				<div class="rushBuyTimeMinute floatLeft"><span class="minute"></span></div><div class="timeSegment floatLeft">:</div>
				<div class="rushBuyTimeSecond floatLeft"><span class="second"></span></div>
				<div class="clearFloat"></div>
			</div>
			<div class="QBCodeImage alignCenter">
				<img src="{{barcode}}" onload="global.changeImageSize(this)" style="width:100%;"/>
			</div>
		</div>

	</script>
	
	
	<script>
		var __onePunchRep = ${__onePunchRep};
	</script>
</body>
</html> 