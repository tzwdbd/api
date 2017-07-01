
(function(){
	
	var _hmt = _hmt || [];
    var hm = document.createElement("script");
    hm.src = "//hm.baidu.com/hm.js?3d7bfcbbacf674d963b11d64e8d7008d";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
    
    _hmt.push(['_trackEvent', 'haihu_oversea', window.location.href]);
    
	
    if (window.jsBridge) { return }
    var webMessageSender;
    var messageScheme = 'ht-bridge';
    var callbacks={};
    var _messageForward ;
    
    function init(message){
        _messageForward  = message;
		
    }
	
	

    function _createWebMessageSender(doc){
        webMessageSender = doc.createElement('iframe')
        webMessageSender.style.display = 'none'
		doc.documentElement.appendChild(webMessageSender) 
    }
    
    function send(type,data,callback){		
		//data='';
        if(callback){
            var cbId = new Date().getTime()
            callbacks[cbId] = callback
            webMessageSender.src = messageScheme+'://'+type+'/p?d='+data+'&cb='+cbId
        }
        else{
            webMessageSender.src = messageScheme+'://'+type+'/p?d='+data
        }    
    }
	
    function fetchAndExcuteCallbacks(cbId,data){
        callback = callbacks[cbId]
        callback(data)
        delete callback[cbId]    
    }
    
    function navtiveMessage(data){
        if(_messageForward) {
	 _messageForward(data)
        }
    }

    window.jsBridge = {
		init: init,
		send: send,
		navtiveMessage: navtiveMessage,
		fetchAndExcuteCallbacks: fetchAndExcuteCallbacks
	}
   var doc = document
   _createWebMessageSender(doc)
   var readyEvent = doc.createEvent('Events')
   readyEvent.initEvent('jsBridgeReady')
   readyEvent.bridge = jsBridge
   doc.dispatchEvent(readyEvent)
})();


function gotoBuy(goods_id, sku_seq){
	var sUserAgent = navigator.userAgent;
	var inHaihu = (sUserAgent.toLowerCase().match("haihu") == "haihu");
	
	if(inHaihu){
		var params = "{\"method\":\"ht.ginza.goodsDetail\",\"goods_id\":\""+goods_id+"\", \"sku_seq\":\""+sku_seq+"\"}";
		window.jsBridge.send('controller',params);
	}
	else{
		window.sessionStorage.setItem("scrollTop",document.body.scrollTop);
		window.location = "http://m.haihu.com/productDetails.html?goodsId="+goods_id+"&sku_seq="+sku_seq;
	}
	
}

function resetImageUrl() {
    if (dpr===3) {
        var nodeList = document.querySelectorAll(".goods-list .goods-img>img");
        for (var i = 0; i < nodeList.length; i++) {
            var node = nodeList[i];
            var url = node.src.replace("_250w", "_480w");
            nodeList[i].src = url;
        }
    }
}

function isHaihu() {
	var sUserAgent = navigator.userAgent;
	return sUserAgent.toLowerCase().match("haihu") == "haihu";
};

$(function(){
	resetImageUrl();
	if(isHaihu()){
		$(".groupMainTab").hide();
		$(".goods-list").addClass("noBottomMenu");
	}
    var scrollTop = window.sessionStorage.getItem("scrollTop");
    if(!!scrollTop&&parseInt(scrollTop)>0){
  	  document.body.scrollTop=parseInt(scrollTop);
  	  window.sessionStorage.setItem("scrollTop","");
    }
});
