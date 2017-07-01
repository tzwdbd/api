<%@ page isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta content="no-cache" http-equiv="Cache-control">
<title>尺码对照表</title>
<script type="text/javascript" src="js/jquery-1.5.2.min.js"></script>
<link type="text/css" href="css/haitao.css" rel="stylesheet">
<style type="text/css">
.ht_cm_left{float:left; display:block; width:25%; background:#f8f8f8; margin:1px 1px 0 0; color:#9e9e9e; font-size:0.75em; text-align:center;}
.ht_cm_right{float:left; width:74%;}
.cm_list{overflow:hidden;}
</style>
</head>

<body>
<% String categoryId = request.getParameter("category_id"); %>
<!-- 142,141 same size chart -->
<% categoryId = ("142".equals(categoryId) || "169".equals(categoryId))?"141":categoryId; %>
<div class="max_width">
	<div style="height:32px;">
        <ul class="ht_nav" id="JS_ht_nav">
            <li id="categoryId_136" <%if("136".equals(categoryId)){%>class="on"<%}%>>女装</li>
            <li id="categoryId_137" <%if("137".equals(categoryId)){%>class="on"<%}%>>男装</li>
            <li id="categoryId_141" <%if("141".equals(categoryId)){%>class="on"<%}%>>鞋子</li>
            <li id="categoryId_143" <%if("143".equals(categoryId)){%>class="on"<%}%>>童鞋</li>
            <li id="categoryId_138" <%if("138".equals(categoryId)){%>class="on"<%}%>>童装</li>
        </ul>
    </div>
    <div id="JS_list">
        <div value="0" class="list_f" style="display:block;">
            <div>
                <p class="ht_title">
                    <span class="title_img"><img src="images/ht_img1.png" /></span>
                    <span class="title_text">女装上衣（仅供参考）</span>
                </p>
                <div style="background:#ededed; height:35px;">
                    <div class="ht_h35 w25">
                        <div class="w42">
                            <span class="s1"><img src="images/ht_img2.png" /></span>
                            <span class="s2">国际</span>
                        </div>
                    </div>
                    <div class="ht_h35 w25">
                        <div class="w42 w33">
                            <span class="s2">欧洲</span>
                        </div>
                    </div>
                    <div class="ht_h35 w25">
                        <div class="w42 w33">
                            <span class="s1"><img src="images/ht_img3.png" /></span>
                            <span class="s2">美国</span>
                        </div>
                    </div>
                    <div class="ht_h35 w25">
                        <div class="w42">
                            <span class="s1"><img src="images/ht_img4.png" /></span>
                            <span class="s2">中国</span>
                        </div>
                    </div>
                </div>
                <div class="ht_size">
                    <p class="ht_size_list">
                        <span class="w25">XXXS</span>
                        <span class="w25">30~32</span>
                        <span class="w25">0</span>
                        <span class="w25">145/73A</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w25">XXS</span>
                        <span class="w25">32~34</span>
                        <span class="w25">0</span>
                        <span class="w25">150/76A</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w25">XS</span>
                        <span class="w25">34</span>
                        <span class="w25">2</span>
                        <span class="w25">155/80A</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w25">S</span>
                        <span class="w25">34~36</span>
                        <span class="w25">4~6</span>
                        <span class="w25">160/84A</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w25">M</span>
                        <span class="w25">38~40</span>
                        <span class="w25">8~10</span>
                        <span class="w25">165/88A</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w25">L</span>
                        <span class="w25">42</span>
                        <span class="w25">12~14</span>
                        <span class="w25">170/92A</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w25">XL</span>
                        <span class="w25">44</span>
                        <span class="w25">16~18</span>
                        <span class="w25">175/96A</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w25">XXL</span>
                        <span class="w25">46</span>
                        <span class="w25">20~22</span>
                        <span class="w25">180/100A</span>
                    </p>
                </div>
            </div>
            <div>
                <p class="ht_title">
                    <span class="title_img"><img src="images/ht_img5.png" /></span>
                    <span class="title_text">女装裤子（仅供参考）</span>
                </p>
                <div style="background:#ededed; height:35px;">
                    <div class="ht_h35 w33">
                        <div class="w42">
                            <span class="s1"><img src="images/ht_img2.png" /></span>
                            <span class="s2">国际</span>
                        </div>
                    </div>
                    <div class="ht_h35 w33">
                        <div class="w42">
                            <span class="s1"><img src="images/ht_img3.png" /></span>
                            <span class="s2">美国</span>
                        </div>
                    </div>
                    <div class="ht_h35 w33">
                        <div class="w42">
                            <span class="s1"><img src="images/ht_img4.png" /></span>
                            <span class="s2">中国</span>
                        </div>
                    </div>
                </div>
                <div class="ht_size">
                    <p class="ht_size_list">
                        <span class="w33">XXXS</span>
                        <span class="w33">0</span>
                        <span class="w33">23</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">XXS</span>
                        <span class="w33">0</span>
                        <span class="w33">24</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">XS</span>
                        <span class="w33">2</span>
                        <span class="w33">25</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">S</span>
                        <span class="w33">4</span>
                        <span class="w33">26</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">M</span>
                        <span class="w33">6</span>
                        <span class="w33">27</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">L</span>
                        <span class="w33">8</span>
                        <span class="w33">28</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">XL</span>
                        <span class="w33">10</span>
                        <span class="w33">29</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">XXL</span>
                        <span class="w33">12</span>
                        <span class="w33">30</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">XXXL</span>
                        <span class="w33">14</span>
                        <span class="w33">31</span>
                    </p>
                </div>
            </div>
        </div>
        <div value="1" class="list_f">
            <div>
                <p class="ht_title">
                    <span class="title_img"><img src="images/ht_img6.png" /></span>
                    <span class="title_text">男装上衣（仅供参考）</span>
                </p>
                <div style="background:#ededed; height:35px;">
                    <div class="ht_h35 w25">
                        <div class="w42">
                            <span class="s1"><img src="images/ht_img2.png" /></span>
                            <span class="s2">国际</span>
                        </div>
                    </div>
                    <div class="ht_h35 w25">
                        <div class="w42">
                            <span class="s2">欧洲</span>
                        </div>
                    </div>
                    <div class="ht_h35 w25">
                        <div class="w42">
                            <span class="s1"><img src="images/ht_img3.png" /></span>
                            <span class="s2">美国</span>
                        </div>
                    </div>
                    <div class="ht_h35 w25">
                        <div class="w42">
                            <span class="s1"><img src="images/ht_img4.png" /></span>
                            <span class="s2">中国</span>
                        </div>
                    </div>
                </div>
                <div class="ht_size">
                    <p class="ht_size_list">
                        <span class="w25">S</span>
                        <span class="w25">46</span>
                        <span class="w25">36</span>
                        <span class="w25">165/80A</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w25">M</span>
                        <span class="w25">48</span>
                        <span class="w25">38</span>
                        <span class="w25">170/84A</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w25">L</span>
                        <span class="w25">50</span>
                        <span class="w25">40</span>
                        <span class="w25">175/88A</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w25">XL</span>
                        <span class="w25">52</span>
                        <span class="w25">42</span>
                        <span class="w25">180/92A</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w25">XXL</span>
                        <span class="w25">54</span>
                        <span class="w25">42</span>
                        <span class="w25">185/96A</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w25">XXXL</span>
                        <span class="w25">56</span>
                        <span class="w25">44</span>
                        <span class="w25">190/100A</span>
                    </p>
                </div>
            </div>
            <div>
                <p class="ht_title">
                    <span class="title_img"><img src="images/ht_img7.png" /></span>
                    <span class="title_text">男装裤子（仅供参考）</span>
                </p>
                <div style="background:#ededed; height:35px;">
                    <div class="ht_h35 w33">
                        <div class="w42">
                            <span class="s1"><img src="images/ht_img2.png" /></span>
                            <span class="s2">国际</span>
                        </div>
                    </div>
                    <div class="ht_h35 w33">
                        <div class="w42">
                            <span class="s1"><img src="images/ht_img3.png" /></span>
                            <span class="s2">美国</span>
                        </div>
                    </div>
                    <div class="ht_h35 w33">
                        <div class="w42">
                            <span class="s1"><img src="images/ht_img4.png" /></span>
                            <span class="s2">中国</span>
                        </div>
                    </div>
                </div>
                <div class="ht_size">
                    <p class="ht_size_list">
                        <span class="w33">&nbsp;</span>
                        <span class="w33">26</span>
                        <span class="w33">26</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">&nbsp;</span>
                        <span class="w33">27</span>
                        <span class="w33">27</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">XXS</span>
                        <span class="w33">28</span>
                        <span class="w33">28</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">XS</span>
                        <span class="w33">29</span>
                        <span class="w33">29</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">S</span>
                        <span class="w33">30</span>
                        <span class="w33">30</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">M</span>
                        <span class="w33">31</span>
                        <span class="w33">31</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">L</span>
                        <span class="w33">32</span>
                        <span class="w33">32</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">XL</span>
                        <span class="w33">33</span>
                        <span class="w33">33</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">XLL</span>
                        <span class="w33">34</span>
                        <span class="w33">34</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">XLL</span>
                        <span class="w33">36</span>
                        <span class="w33">36</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">XXXL</span>
                        <span class="w33">38</span>
                        <span class="w33">38</span>
                    </p>
                </div>
            </div>
        </div>
        <div value="2" class="list_f">
            <div>
                <p class="ht_title">
                    <span class="title_img"><img src="images/ht_img13.png" /></span>
                    <span class="title_text">女鞋（仅供参考）</span>
                </p>
                <div style="background:#ededed; height:35px;">
                    <div class="ht_h35 w33">
                        <div class="w42">
                            <span class="s1"><img src="images/ht_img3.png" /></span>
                            <span class="s2">美国</span>
                        </div>
                    </div>
                    <div class="ht_h35 w33">
                        <div class="w42">
                            <span class="s1"><img src="images/ht_img4.png" /></span>
                            <span class="s2">中国</span>
                        </div>
                    </div>
                    <div class="ht_h35 w33">
                        <div class="w42" style="width:72%;">
                            <span class="s2">脚长(CM)</span>
                        </div>
                    </div>
                </div>
                <div class="ht_size">
                    <p class="ht_size_list">
                        <span class="w33">4</span>
                        <span class="w33">35</span>
                        <span class="w33">20.8</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">4.5</span>
                        <span class="w33">35</span>
                        <span class="w33">21.3</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">5</span>
                        <span class="w33">35.5</span>
                        <span class="w33">21.6</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">5.5</span>
                        <span class="w33">36</span>
                        <span class="w33">21.6</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">6</span>
                        <span class="w33">36.5</span>
                        <span class="w33">22.5</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">6.5</span>
                        <span class="w33">37</span>
                        <span class="w33">23</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">7</span>
                        <span class="w33">37.5</span>
                        <span class="w33">23.5</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">7.5</span>
                        <span class="w33">38</span>
                        <span class="w33">23.8</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">8</span>
                        <span class="w33">38.5</span>
                        <span class="w33">24.1</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">8.5</span>
                        <span class="w33">39</span>
                        <span class="w33">24.6</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">9</span>
                        <span class="w33">39.5</span>
                        <span class="w33">25.1</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">9.5</span>
                        <span class="w33">40</span>
                        <span class="w33">25.4</span>
                    </p>
                    
                    <p class="ht_size_list">
                        <span class="w33">10</span>
                        <span class="w33">40.5</span>
                        <span class="w33">25.9</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">10.5</span>
                        <span class="w33">41</span>
                        <span class="w33">26.2</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">11</span>
                        <span class="w33">41.5</span>
                        <span class="w33">26.7</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">11.5</span>
                        <span class="w33">42</span>
                        <span class="w33">27.1</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">12</span>
                        <span class="w33">42.5</span>
                        <span class="w33">27.6</span>
                    </p>
                </div>
            </div>
            <div>
                <p class="ht_title">
                    <span class="title_img"><img src="images/ht_img12.png" /></span>
                    <span class="title_text">男鞋（仅供参考）</span>
                </p>
                <div style="background:#ededed; height:35px;">
                    <div class="ht_h35 w33">
                        <div class="w42">
                            <span class="s1"><img src="images/ht_img3.png" /></span>
                            <span class="s2">美国</span>
                        </div>
                    </div>
                    <div class="ht_h35 w33">
                        <div class="w42">
                            <span class="s1"><img src="images/ht_img4.png" /></span>
                            <span class="s2">中国</span>
                        </div>
                    </div>
                    <div class="ht_h35 w33">
                        <div class="w42" style="width:72%;">
                            <span class="s1"></span>
                            <span class="s2">脚长(CM)</span>
                        </div>
                    </div>
                </div>
                <div class="ht_size">
                    <p class="ht_size_list">
                        <span class="w33">6</span>
                        <span class="w33">39</span>
                        <span class="w33">23.5</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">6.5</span>
                        <span class="w33">39</span>
                        <span class="w33">24.1</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">7</span>
                        <span class="w33">40</span>
                        <span class="w33">24.4</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">7.5</span>
                        <span class="w33">40~41</span>
                        <span class="w33">24.8</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">8</span>
                        <span class="w33">41</span>
                        <span class="w33">25.4</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">8.5</span>
                        <span class="w33">41~42</span>
                        <span class="w33">25.7</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">9</span>
                        <span class="w33">42</span>
                        <span class="w33">26</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">9.5</span>
                        <span class="w33">42~43</span>
                        <span class="w33">26.7</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">10</span>
                        <span class="w33">43</span>
                        <span class="w33">27</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">10.5</span>
                        <span class="w33">43~44</span>
                        <span class="w33">27.3</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">11</span>
                        <span class="w33">44</span>
                        <span class="w33">27.9</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">11.5</span>
                        <span class="w33">44~45</span>
                        <span class="w33">28.3</span>
                    </p>
                    
                    <p class="ht_size_list">
                        <span class="w33">12</span>
                        <span class="w33">45</span>
                        <span class="w33">28.6</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">13</span>
                        <span class="w33">46</span>
                        <span class="w33">29.4</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">14</span>
                        <span class="w33">47</span>
                        <span class="w33">30.2</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">15</span>
                        <span class="w33">48</span>
                        <span class="w33">31</span>
                    </p>
                    <p class="ht_size_list">
                        <span class="w33">16</span>
                        <span class="w33">49</span>
                        <span class="w33">31.8</span>
                    </p>
                </div>
            </div>
        </div>
        <div value="3" class="list_f">
            <div>
            	<p class="ht_title">
                    <span class="title_img"><img src="images/ht_img14.png" /></span>
                    <span class="title_text">童鞋（仅供参考）</span>
                </p>
                
                <div style="background:#ededed; height:35px;">
                    <div class="ht_h35 w25">
                        <div class="w42">
                            <span class="s2">年龄</span>
                        </div>
                    </div>
                    <div class="ht_h35 w25">
                        <div class="w42">
                            <span class="s1"><img src="images/ht_img3.png" /></span>
                            <span class="s2">美国</span>
                        </div>
                    </div>
                    <div class="ht_h35 w25">
                        <div class="w42">
                            <span class="s1"><img src="images/ht_img4.png" /></span>
                            <span class="s2">中国</span>
                        </div>
                    </div>
                    <div class="ht_h35 w25">
                        <div class="w42" style="width:78%;">
                            <span class="s2">脚长(CM)</span>
                        </div>
                    </div>
                </div>
                <div class="ht_size">
                    <div class="cm_list">
                        <span class="ht_cm_left">0-12个月</span>
                        <div class="ht_cm_right">
                            <p class="ht_size_list">
                                <span class="w33">0.5</span>
                                <span class="w33">16</span>
                                <span class="w33">8.3</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">1</span>
                                <span class="w33">16</span>
                                <span class="w33">8.9</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">1.5</span>
                                <span class="w33">17</span>
                                <span class="w33">9.2</span>
                            </p>
                            
                            <p class="ht_size_list">
                                <span class="w33">2</span>
                                <span class="w33">17</span>
                                <span class="w33">9.5</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">2.5</span>
                                <span class="w33">18</span>
                                <span class="w33">10.2</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">3</span>
                                <span class="w33">18</span>
                                <span class="w33">10.5</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">3.5</span>
                                <span class="w33">19</span>
                                <span class="w33">10.8</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">4</span>
                                <span class="w33">19</span>
                                <span class="w33">11.4</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">4.5</span>
                                <span class="w33">20</span>
                                <span class="w33">11.7</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">5</span>
                                <span class="w33">20</span>
                                <span class="w33">12.1</span>
                            </p>
                        </div>
                    </div>
                   <div class="cm_list">
                        <span class="ht_cm_left">1-5岁</span>
                        <div class="ht_cm_right">
                            <p class="ht_size_list">
                                <span class="w33">5.5</span>
                                <span class="w33">21</span>
                                <span class="w33">12.7</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">6</span>
                                <span class="w33">22</span>
                                <span class="w33">13</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">6.5</span>
                                <span class="w33">22</span>
                                <span class="w33">13.3</span>
                            </p>
                            
                            <p class="ht_size_list">
                                <span class="w33">7</span>
                                <span class="w33">23</span>
                                <span class="w33">14</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">7.5</span>
                                <span class="w33">23</span>
                                <span class="w33">10.2</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">8</span>
                                <span class="w33">24</span>
                                <span class="w33">14.6</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">8.5</span>
                                <span class="w33">25</span>
                                <span class="w33">15.2</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">9</span>
                                <span class="w33">25</span>
                                <span class="w33">15.6</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">9.5</span>
                                <span class="w33">26</span>
                                <span class="w33">15.9</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">10</span>
                                <span class="w33">27</span>
                                <span class="w33">16.5</span>
                            </p>
                            
                            <p class="ht_size_list">
                                <span class="w33">10.5</span>
                                <span class="w33">27</span>
                                <span class="w33">16.8</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">11</span>
                                <span class="w33">28</span>
                                <span class="w33">17.1</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">11.5</span>
                                <span class="w33">29</span>
                                <span class="w33">17.8</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">12</span>
                                <span class="w33">30</span>
                                <span class="w33">18.1</span>
                            </p>
                        </div>
                    </div>
                    <div class="cm_list">
                        <span class="ht_cm_left">4-12岁</span>
                        <div class="ht_cm_right">
                            <p class="ht_size_list">
                                <span class="w33">12.5</span>
                                <span class="w33">30</span>
                                <span class="w33">18.4</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">13</span>
                                <span class="w33">31</span>
                                <span class="w33">19.1</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">13.5</span>
                                <span class="w33">31</span>
                                <span class="w33">19.4</span>
                            </p>
                            
                            <p class="ht_size_list">
                                <span class="w33">1</span>
                                <span class="w33">32</span>
                                <span class="w33">19.7</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">1.5</span>
                                <span class="w33">33</span>
                                <span class="w33">20.3</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">2</span>
                                <span class="w33">33</span>
                                <span class="w33">20.6</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">2.5</span>
                                <span class="w33">34</span>
                                <span class="w33">21</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">3</span>
                                <span class="w33">34</span>
                                <span class="w33">21.6</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">3.5</span>
                                <span class="w33">35</span>
                                <span class="w33">21.9</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">4</span>
                                <span class="w33">36</span>
                                <span class="w33">22.2</span>
                            </p>
                            
                            <p class="ht_size_list">
                                <span class="w33">4.5</span>
                                <span class="w33">36</span>
                                <span class="w33">22.9</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">5</span>
                                <span class="w33">37</span>
                                <span class="w33">23.2</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">5.5</span>
                                <span class="w33">37</span>
                                <span class="w33">23.5</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">6</span>
                                <span class="w33">38</span>
                                <span class="w33">24.1</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">6.5</span>
                                <span class="w33">38</span>
                                <span class="w33">24.4</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">7</span>
                                <span class="w33">39</span>
                                <span class="w33">24.8</span>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div value="4" class="list_f">
            <div>
            	<p class="ht_title">
                    <span class="title_img"><img src="images/ht_img15.png" /></span>
                    <span class="title_text">童装（仅供参考）</span>
                </p>
                
                <div style="background:#ededed; height:35px;">
                    <div class="ht_h35 w25">
                        <div class="w42">
                            <span class="s2">年龄</span>
                        </div>
                    </div>
                    <div class="ht_h35 w25">
                        <div class="w42">
                            <span class="s1"><img src="images/ht_img3.png" /></span>
                            <span class="s2">美国</span>
                        </div>
                    </div>
                    <div class="ht_h35 w25">
                        <div class="w42" style="width:78%;">
                            <span class="s2">身高(cm)</span>
                        </div>
                    </div>
                    <div class="ht_h35 w25">
                        <div class="w42" style="width:78%;">
                            <span class="s2">重量(kg)</span>
                        </div>
                    </div>
                </div>
                <div class="ht_size">
                    <div class="cm_list">
                        <span class="ht_cm_left">0-2岁</span>
                        <div class="ht_cm_right">
                            <p class="ht_size_list">
                                <span class="w33">Preemie(P)</span>
                                <span class="w33">0~43</span>
                                <span class="w33">0~2.3</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">NewBORN(NB)</span>
                                <span class="w33">0~55</span>
                                <span class="w33">2.3~3.6</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">3M</span>
                                <span class="w33">55~61</span>
                                <span class="w33">3.6~5.7</span>
                            </p>
                            
                            <p class="ht_size_list">
                                <span class="w33">6M</span>
                                <span class="w33">61~67</span>
                                <span class="w33">5.7~7.5</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">9M</span>
                                <span class="w33">67~72</span>
                                <span class="w33">7.5~9.3</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">12M</span>
                                <span class="w33">72~78</span>
                                <span class="w33">9.3~11.1</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">18M</span>
                                <span class="w33">78~83</span>
                                <span class="w33">11.2~12.5</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">24M</span>
                                <span class="w33">83~86</span>
                                <span class="w33">12.5~13.6</span>
                            </p>
                        </div>
                    </div>
                   <div class="cm_list">
                        <span class="ht_cm_left">2-5岁</span>
                        <div class="ht_cm_right">
                            <p class="ht_size_list">
                                <span class="w33">2T</span>
                                <span class="w33">88~93</span>
                                <span class="w33">13.2~14.1</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">3T</span>
                                <span class="w33">93~98</span>
                                <span class="w33">14.1~15.4</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">4T</span>
                                <span class="w33">98~105</span>
                                <span class="w33">15.4~17.2</span>
                            </p>
                            
                            <p class="ht_size_list">
                                <span class="w33">5T</span>
                                <span class="w33">105~111</span>
                                <span class="w33">17.2~19.1</span>
                            </p>
                        </div>
                    </div>
                    <div class="cm_list">
                        <span class="ht_cm_left">4-12岁</span>
                        <div class="ht_cm_right">
                            <p class="ht_size_list">
                                <span class="w33">4</span>
                                <span class="w33">102~108</span>
                                <span class="w33">16.8~17.7</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">5</span>
                                <span class="w33">108~114</span>
                                <span class="w33">17.7~20</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">6</span>
                                <span class="w33">114~122</span>
                                <span class="w33">20~22.2</span>
                            </p>
                            
                            <p class="ht_size_list">
                                <span class="w33">6X</span>
                                <span class="w33">122~128</span>
                                <span class="w33">22.2~25</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">7</span>
                                <span class="w33">122~128</span>
                                <span class="w33">22.2~25</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">8</span>
                                <span class="w33">128~135</span>
                                <span class="w33">25~28</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">10</span>
                                <span class="w33">135~140</span>
                                <span class="w33">28.1~33.6</span>
                            </p>
                            <p class="ht_size_list">
                                <span class="w33">12</span>
                                <span class="w33">140~147</span>
                                <span class="w33">33.6~39</span>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
$(function(){
    var itemChange = function(){
        var index = $(this).index(),len = $("#JS_list").children().length;
        $(this).addClass("on").siblings().removeClass("on");
        for(var i=0;i<len;i++ ){
            $("#JS_list").children().eq(i).hide();
        }
        $("#JS_list").children().eq(index).show();

        //列表左右等高
        $("div.cm_list").each(function() {
            var height = $(this).children(".ht_cm_right").height()-1;
            $(this).children(".ht_cm_left").css({"height":height,"line-height":height+"px"});
        });
    };
	//导航条切换
	$("#JS_ht_nav").children("li").click(itemChange);
    $("#categoryId_<%=categoryId%>").click();
})
</script>
</body>
</html>