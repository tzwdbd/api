<%@ page isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="no-cache" http-equiv="Cache-control">
<script type="text/javascript" src="http://g.alicdn.com/mtb/??lib-flexible/0.3.2/flexible_css.js,lib-flexible/0.3.2/flexible.js"></script>
<title>帮助中心</title>
<style>
body,p,ul{font:normal 100% "微软雅黑" Helvetica, Arial, sans-serif; padding:0px; margin:0px;}
body{background:#f3f3f3;}
.help-title{padding-top:0.46875rem; overflow:hidden;}
.help-title .s2{float:left; color:#f53e7b; margin-left:0.3125rem;}
.help-ul{padding:0.15625rem 0.3125rem 0; list-style-type:none;}
.text-list{border:#e0e0e0 solid 1px; margin-top:0.25rem; border-radius:0.078125rem;background: #fafafa;}
.question{height:1.171875rem; line-height:1.171875rem; border-radius:0.078125rem 0.078125rem 0 0; background:#fff; color:#5d5d5d; border-bottom:#e0e0e0 solid 1px;}
.question i{float:left; display:block; width:0.40625rem; height:0.40625rem; margin:0.03125rem 0.3125rem 0 0.234375rem;}
.answer{line-height:0.578125rem; padding:0.3125rem 0.25rem; color:#909090; text-indent:2em; background:#fafafa; border-radius: 0 0 0.078125rem 0.078125rem;}
.help_h290{margin-top:0.46875rem;}
.help-ts{color:#5d5d5d;line-height:0.578125rem; margin-top:0.1875rem; background:#fff; border:#e0e0e0 solid 1px; border-radius:0.078125rem; padding:0.25rem;}
.help-p1{margin-top:0.25rem; line-height:0.546875rem; text-indent:0;}
.help-a{color:#f53e7b; margin-right:0.25rem; text-decoration:underline;}
.help-a:hover{text-decoration:underline;}
.exp_table{margin:0 0.3125rem 0.4rem 0.3125rem;width:94%;background:#fafafa;}
.exp_table tr td{color:#5d5d5d; padding:0.1875rem;border:1px solid #e0e0e0;}
.exp_table .table_tit{color:#212121;padding-left:0.1875rem;}
.exp_table .tab_tab tr td {border-bottom:1px solid #e0e0e0;border-top:none;border-left:none;border-right:none;}
.exp_table .td_two{padding:0;}
.faq_padd{padding-left:0.5125rem}


[data-dpr="1"] .fontSize11{font-size:11px;}
[data-dpr="1"] .fontSize12{font-size:14px;}
[data-dpr="1"] .fontSize14{font-size:16px;}

[data-dpr="2"] .fontSize11{font-size:22px;}
[data-dpr="2"] .fontSize12{font-size:26px;}
[data-dpr="2"] .fontSize14{font-size:32px;}

[data-dpr="3"] .fontSize11{font-size:33px;}
[data-dpr="3"] .fontSize12{font-size:38px;}
[data-dpr="3"] .fontSize14{font-size:48px;}
</style>
</head>

<body>
<div style="padding-bottom:0.3125rem;">

    <h2 class="help-title" id="help-f1">
        <span class="s2 fontSize14">新手指南</span>
    </h2>
    <ul class="help-ul">
        <li class="text-list">
        	<p class="question fontSize14">
            	<i><img src="images/q_w26.png" style="width:100%;" /></i>
            	<span>我们是谁</span>
            </p>
            <p class="answer fontSize12">我们是海狐全球购，所有商品由海外知名官网直接发货，其中包括美国亚马逊、6PM等知名网站，让您轻松买到2000万种海外超值正品，帮您解决英文下单、美元支付、转运清关、售后繁琐的海淘难题，100%正品。</p>
        </li>
        <li class="text-list">
        	<p class="question fontSize14">
            	<i><img src="images/q_w26.png" style="width:100%;" /></i>
            	<span>结算与支付</span>
            </p>
            <p class="answer fontSize12"> 海狐全球购支持哪些支付方式？<br>目前可以微信支付、支付宝支付两种方式</p>
        </li>
        <li class="text-list">
        	<p class="question fontSize14">
            	<i><img src="images/q_w26.png" style="width:100%;" /></i>
            	<span>支付出现问题</span>
            </p>
            <p class="answer fontSize12">如果您在支付中出现问题，请随时与我们联系，我们会尽快协助您解决问题。</p>
        </li>
    </ul>
    
    <h2 class="help-title" id="help-f2">
        <span class="s2 fontSize14">商品问题</span>
    </h2>
    <!-- <p class="help_h290"><img src="images/help_h290.png" style="width:100%" /></p> -->
    <ul class="help-ul">
        <p class="help-ts fontSize12">海狐全球购全场100%正品!支持专柜验货！所有商品由海外知名网站直接发货，其中包括美国亚马逊、6PM等知名网站，您可以点击“我的”-“全部订单”里，看到海狐全球购订单号和物流信息。</p>
    	<li class="text-list">
        	<p class="question fontSize14">
            	<i><img src="images/q_w26.png" style="width:100%;" /></i>
            	<span>商品尺码</span>
            </p>
            <p class="answer fontSize12">不同品牌的尺码具有一定的差异，我们提供的尺码表仅供参考，您可以对应表中的参数进行选择，如果不确认，可以去品牌中文官方网站查询或者联系我们客服咨询。 </p>
        </li>
        <li class="text-list">
        	<p class="question fontSize14">
            	<i><img src="images/q_w26.png" style="width:100%;" /></i>
            	<span>商品库存</span>
            </p>
            <p class="answer fontSize12">支付成功的订单都是有货的吗？<br>由于海狐全球购提供的商品全部由美国各官网提供，库存由美国各官网控制。因此支付成功后，美国各官方网站会立即处理订单，但可能会出现缺货现象。如发生缺货，我们会第一时间通知您，并进行全额退款。</p>
        </li>
        <li class="text-list">
        	<p class="question fontSize14">
            	<i><img src="images/q_w26.png" style="width:100%;" /></i>
            	<span>为什么同个商品不同型号价格不一样？</span>
            </p>
            <p class="answer fontSize12">因为国外商品有些型号是热卖款，商城标价都是不同的，我们是根据商城标价来进行售卖的，所以会出现价格不一致的情况。</p>
        </li>
        <li class="text-list">
        	<p class="question fontSize14">
            	<i><img src="images/q_w26.png" style="width:100%;" /></i>
            	<span>我付的价格是到手价吗？</span>
            </p>
            <p class="answer fontSize12">我们标注的都是一口价，您不需要额外再支付其他费用。</p>
        </li>
        <li class="text-list">
        	<p class="question fontSize14">
            	<i><img src="images/q_w26.png" style="width:100%;" /></i>
            	<span>售完的商品还能买到吗？</span>
            </p>
            <p class="answer fontSize12">我们的商品与官网同步时时更新，售完的商品如果官网补货，可以再次购买，建议您将商品收藏起来。</p>
        </li>
	</ul>
	
    <h2 class="help-title" id="help-f3">
        <span class="s2 fontSize14">订单问题</span>
    </h2>
    <ul class="help-ul">
        <li class="text-list">
        	<p class="question fontSize14">
            	<i><img src="images/q_w26.png" style="width:100%;" /></i>
            	<span>订单确认</span>
            </p>
            <p class="answer fontSize12">
            	<span style="display:block">下单前如何确认订单？
            		<span style="display:block">下单前要确认好商品的尺码、颜色、材质等信息，保证所购买的商品是正确无误的。非质量问题一般不接受取消订单、修改订单及退货。</span>
            	</span>
            	<span style="display:block;margin-top:0.3rem">为什么要填写身份证号码？
            		<span style="display:block">由于海淘过程中涉及到进口清关问题，需要核对用户身份证号码确认相关信息。所提交的身份证号码，海狐及合作的物流公司承诺所有信息均进行加密存储，直接提交给海关时进行查验、绝不做其他用途，其他任何人均无法查看。</span>
            	</span>
            </p>
        </li>
        <li class="text-list">
        	<p class="question fontSize14">
            	<i><img src="images/q_w26.png" style="width:100%;" /></i>
            	<span>订单查询</span>
            </p>
            <p class="answer fontSize12">
	            <span style="display:block">怎样查询订单信息？
	            	<span style="display:block">成功下单之后，可以在个人页面的订单页面查询订单信息。也可以查询订单的详细物流信息。</span>
	            </span>
        </li>
        <li class="text-list">
        	<p class="question fontSize14">
            	<i><img src="images/q_w26.png" style="width:100%;" /></i>
            	<span>取消订单</span>
            </p>
            <p class="answer fontSize12">
            <span style="display:block">能否取消订单？
            	<span style="display:block">依照大多数海外官网的规定，订单如果已受理，则不允许无条件取消订单。如果用户想要取消订单，请尽快联系客服查看订单状态，如果海外官网已处理此订单，订单是无法取消的，请您谅解。</span>
            </span>
        </li>
    </ul>
    
    <h2 class="help-title" id="help-f4">
        <span class="s2 fontSize14">物流问题</span>
    </h2>
    <ul class="help-ul">
        <li class="text-list">
        	<p class="question fontSize14">
            	<i><img src="images/q_w26.png" style="width:100%;" /></i>
            	<span>查询物流</span>
            </p>
            <p class="answer fontSize12">
            	<span style="display:block">为什么反馈的运单号没有物流信息？
            		<span style="display:block">这可能是因为您的商品包裹刚由快递公司接收，物流信息的展示可能会1-2天延迟，请等待一定时间后再次查询。</span>
            	</span>
        </li>
        <li class="text-list">
        	<p class="question fontSize14">
            	<i><img src="images/q_w26.png" style="width:100%;" /></i>
            	<span>为什么一个订单被拆分成了多个包裹？</span>
            </p>
            <p class="answer fontSize12">由于海狐全球购的商品均来自于我们合作的不同海外官方网站，如果您同一个订单下选购的商品来自不同的合作网站或者同一官网的不同仓库，由于网站的发货时间不同，因此您的订单会被拆分成为多个包裹。</p>
        </li>
        <li class="text-list">
        	<p class="question fontSize14">
            	<i><img src="images/q_w26.png" style="width:100%;" /></i>
            	<span>付款后大概多久能收到货？</span>
            </p>
            <p class="answer fontSize12">从您下单开始一般来说14-20个工作日能收到货，但不排除特殊情况以及节假日或不可抗力等因素造成的延迟。</p>
        </li>
	</ul>
	
    <h2 class="help-title" id="help-f5">
        <span class="s2 fontSize14">售后服务</span>
    </h2>
    <ul class="help-ul">
    	<p class="help-ts fontSize12">海狐全球购用心为您创造售后无忧的购物体验，如果遇到售后问题，海狐团队都会尽快与您联系，为您服务。微信公众号：<a href="#/" target="_blank" class="help-a">haihugou</a></p>
    	<li class="text-list">
        	<p class="question fontSize14">
            	<i><img src="images/q_w26.png" style="width:100%;" /></i>
            	<span>售后受理期限</span>
            </p>
            <p class="answer fontSize12">如果发现商品有问题，请在您签收商品后5天内联系客服反馈，超过时效恕不办理。</p>
        </li>
        <li class="text-list">
        	<p class="question fontSize14">
            	<i><img src="images/q_w26.png" style="width:100%;" /></i>
            	<span>售后范围</span>
            </p>
            <p class="answer fontSize12">
            	<span style="display:block">质量问题如何退货？
            		<span style="display:block">您收到货后，最好当着快递的面检查物品，如果出现运输途中的损坏需要快递开具开箱证明或者开箱照片及物品损坏照片。您签收后5天内及时联系海狐客服，我们审核后为您联系官网办理退货。质量问题包括：商品破损，残次丢件少件，实物与订单信息不符等。</span>
            	</span>
            </p>
        </li>
        <li class="text-list">
        	<p class="question fontSize14">
            	<i><img src="images/q_w26.png" style="width:100%;" /></i>
            	<span>如何退款？</span>
            </p>
            <p class="answer fontSize12">
            	<span style="display:block">在客户退货之后，海狐全球购如何退款？
            		<span style="display:block">海狐全球购确认收到客户退货商品后，客服人员会联系客户退款。</span>
            	</span>
            	<span style="display:block;margin-top:0.3rem">在客户退款之后，退款金额多长时间可以返还给客户？
            		<span style="display:block">在海狐全球购操作了退款之后，视不同的支付途径，款项会在5-7个工作日退回原支付账号。</span>
            	</span>
        	</p>
        </li>
        <li class="text-list">
        	<p class="question fontSize14">
            	<i><img src="images/q_w26.png" style="width:100%;" /></i>
            	<span>退货产生的运费问题谁来承担？</span>
            </p>
            <p class="answer fontSize12">
            	若商品海狐全球购判断为质量问题需要退货，跟客服协商一致后，退回邮费由海狐全球购承担。
            </p>
        </li>
        <li class="text-list">
        	<p class="question fontSize14">
            	<i><img src="images/q_w26.png" style="width:100%;" /></i>
            	<span>是否支持换货？</span>
            </p>
            <p class="answer fontSize12">
            	由于海狐海淘上购买的商品全部由海外官网直接发货，如果换货会产生高昂的往返物流费用，所以目前海狐海淘上购买商品不支持换货。
            </p>
        </li>
        <li class="text-list">
        	<p class="question fontSize14">
            	<i><img src="images/q_w26.png" style="width:100%;" /></i>
            	<span>海狐全球购上购买的商品如何保修？</span>
            </p>
            <p class="answer fontSize12">
            	目前购买的海外商品暂时不支持保修。
            </p>
        </li>
    </ul>
</div>
</body>
</html>
