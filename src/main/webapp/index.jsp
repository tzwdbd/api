<%@ page isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>海狐全球购官网站</title>
<meta name="keywords" content="海狐全球购" /> 
<meta name="Description" content="海狐全球购" />
<script type="text/javascript" src="js/jquery-1.5.2.min.js"></script>
<style type="text/css">
body{padding:0; margin:0;}
p,dl,dd,ul{padding:0; margin:0;}

.haihu{width:1024px; margin-left:70px; padding:51px 0 32px 0; overflow:hidden; zoom:1; font-family:Microsoft YaHei;}
.haihu_l{float:left;}
.haihu_r{float:right; width:705px; position:relative;}
.haihu_r .iphone{float:right; width:419px; height:521px; background:url(../images/index/iphone.png) no-repeat; _background:none; _filter:progid:dximagetransform.microsoft.alphaimageloader(src=../images/iphone.png); position:relative; z-index:1; margin-right:96px; _margin-right:48px;}
.haihu_dl{overflow:hidden; margin-top:70px;}
.haihu_dl dd{float:left; font-size:0; width:133px; text-align:center; margin-right:48px;}
.haihu_dl .p1,.haihu_dl .p2{font-size:15px; color:#fff; margin-top:7px;}
.haihu_dl .p1{letter-spacing:3px;}
.haihu_dl .p1{letter-spacing:1px;}
.hh_banner{width:221px; height:392px; margin:65px 0 0 75px; position:relative;}
.wrapper{height:392px; width:221px; overflow:hidden;}
.wrapper .d1{list-style-type:none; font-size:0; width:2000px; float:left;}
.wrapper .d1 li{list-style-type:none; float:left;}
.hh_footer{height:46px; line-height:46px; text-align:center; background:#f0f0f0; color:#adadad; font-size:12px;}
.hh_prev,.hh_next{width:75px; height:48px; position:absolute; top:50%; margin-top:-18px; display:none; cursor:pointer;}
.hh_prev{left:-75px; background:url(../images/index/arrow_l.png) no-repeat; _background:none; _filter:progid:dximagetransform.microsoft.alphaimageloader(src=../images/arrow_l.png);}
.hh_next{right:-75px; background:url(../images/index/arrow_r.png) no-repeat; _background:none; _filter:progid:dximagetransform.microsoft.alphaimageloader(src=../images/arrow_r.png);}
.iphone_bg{background:url(../images/index/iphone_bg.png) no-repeat; width:718px; height:271px; position:absolute; bottom:-40px; right:0; _background:none; _filter:progid:dximagetransform.microsoft.alphaimageloader(src=../images/iphone_bg.png);}
</style>
<script>
$(function(){
	MoveBanner();
})
//banner轮播
function MoveBanner(){
	var div = $("#JS_hh_banner"),
		div_w = div.find(".wrapper"),
		nav_img = div_w.find('.d1'),
		nav_len = div_w.find(".d1").children("li").length,
		nav_prev = div.find(".hh_prev"), nav_next = div.find(".hh_next"),
		page = 1, nav_w = div_w.width(),
	    is_can_next = true, is_can_prev = true;
	if(nav_len > 1) {
		nav_next.click(function(){
			if(!is_can_next) return;
			is_can_next = false;
			if(page == nav_len){
				page = 1;
				for(i=0;i<nav_len-1;i++){
					nav_img.find('li').eq(0).appendTo(nav_img);	
				}
				nav_img.find('li').eq(0).clone(true).appendTo(nav_img);
				nav_img.css({'margin-left' : 0});
				nav_img.animate({
					'margin-left' : -page*nav_w
				},function(){
					nav_img.css({'margin-left' : 0});
					nav_img.find('li').eq(0).remove();
					is_can_next = true;
				});
			}else{
				nav_img.animate({'margin-left' : -page*nav_w}, function(){is_can_next = true;})
				page++;
			}
		})
		nav_prev.click(function(){
			if(!is_can_prev) return;
			is_can_prev = false;
			if(page == 1){
				page = nav_len;
				for(i=0;i<nav_len-1;i++){
					nav_img.find('li').last().prependTo(nav_img);	
				}
				nav_img.find('li').last().clone(true).prependTo(nav_img);
				nav_img.css({'margin-left' : -page*nav_w});
				nav_img.animate({
					'margin-left' : -(page-1)*nav_w
				},function(){
					nav_img.find('li').last().remove();
					is_can_prev = true;
				});
			}else{
				nav_img.animate( {'margin-left' : -(page-2)*nav_w}, function(){is_can_prev = true;});
				page--;
			}
		})
		var objtime = setInterval(function(){nav_next.click();},3000);
		div.hover(function(){
			clearInterval(objtime);
			nav_prev.show(); nav_next.show();
		}, function(){
			objtime = setInterval(function(){nav_next.click();},3000);
			nav_prev.hide(); nav_next.hide();
		});
	}
}
</script>
</head>

<body>
<div style="background:#fe5f94;">
	<div style="width:1038px; margin:0 auto;">
        <div class="haihu">
            <div class="haihu_l">
                <img src="images/index/ht_text.png" />
                <dl class="haihu_dl">
                    <dd>
                        <img src="images/index/khd_erwm.png" alt="下载海狐客户端" width="133" height="133" />
                        <p class="p1">下载海狐客户端</p>
                    </dd>
                    <dd style="margin:0;">
                        <img src="images/index/wx_erwm.png" alt="关注海狐官方微信" width="133" height="133" />
                        <p class="p2">关注海狐官方微信</p>
                    </dd>
                </dl>
            </div>
            <div class="haihu_r">
                <div class="iphone">
                    <div class="hh_banner" id="JS_hh_banner">
                        <span class="hh_prev" title="上一页">&nbsp;</span>
                        <span class="hh_next" title="下一页">&nbsp;</span>
                        <div class="wrapper">
                            <ul class="d1">
                                <li><img src="images/index/banner1.jpg" width="221" height="392" /></li>
                                <li><img src="images/index/banner2.jpg" width="221" height="392" /></li>
                                <li><img src="images/index/banner1.jpg" width="221" height="392" /></li>
                                <li><img src="images/index/banner2.jpg" width="221" height="392" /></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <p class="iphone_bg">&nbsp;</p>
            </div>
        </div>
    </div>
</div>
<div class="hh_footer">copyright©2011-2015 haihu.com 杭州雷讯网络技术股份有限公司︱浙ICP备12042948号-1 &nbsp&nbsp友情链接：<a href="http://m.kuaidi100.com" target="_blank">快递查询</a></div>
</body>
</html>