package com.oversea.api.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.dubbo.common.utils.StringUtils;

public class MallProductCodeUtil {
	
	public static void main(String args[]) {
		// String url = "http://www.gilt.com/sale/women/la-perla-7814/product/1154502223-la-perla-scalloped-trim-balconette-bra?origin=sale";
		// https://www.amazon.de/dp/B017YTGEFY
		String url = "http://www.mybag.com/womens-bags/karl-lagerfeld-women-s-k/pin-closure-satchel-bordeaux/11131735.html";
    	System.out.println(mybag(url));
    }
	
	public static String getCode(String mallName, String url) {
    	String mallProductCode = null;
    	
    	if("amazon".equalsIgnoreCase(mallName)
    			|| "amazon.jp".equalsIgnoreCase(mallName)
    			|| "amazon.de".equalsIgnoreCase(mallName)
    			|| "amazon.uk".equalsIgnoreCase(mallName)) {
    		mallProductCode = amazon(url);
    	} else if("lookfantastic".equalsIgnoreCase(mallName)) {
    		mallProductCode = lookfantastic(url);
    	} else if("gymboree".equalsIgnoreCase(mallName)) {
    		mallProductCode = gymboree(url);
    	} else if("iherb".equalsIgnoreCase(mallName)) {
    		mallProductCode = iherb(url);
    	} else if("diapers".equalsIgnoreCase(mallName)) {
    		mallProductCode = diapers(url);
    	} else if("clinique".equalsIgnoreCase(mallName)) {
    		mallProductCode = clinique(url);
    	} else if("ashford".equalsIgnoreCase(mallName)) {
    		mallProductCode = ashford(url);
    	} else if("origins".equalsIgnoreCase(mallName)) {
    		mallProductCode = origins(url);
    	} else if("asos".equalsIgnoreCase(mallName)) {
    		mallProductCode = asos(url);
    	} else if("walgreens".equalsIgnoreCase(mallName)) {
    		mallProductCode = walgreens(url);
    	} else if("eastbay".equalsIgnoreCase(mallName)) {
    		mallProductCode = eastbay(url);
    	} else if("famousfootwear".equalsIgnoreCase(mallName)) {
    		mallProductCode = famousfootwear(url);
    	} else if("gnc".equalsIgnoreCase(mallName)) {
    		mallProductCode = gnc(url);
    	} else if("ralphlauren".equalsIgnoreCase(mallName)) {
    		mallProductCode = ralphlauren(url);
    	} else if("ninewest".equalsIgnoreCase(mallName)) {
    		mallProductCode = ninewest(url);
    	} else if("katespade".equalsIgnoreCase(mallName)) {
    		mallProductCode = katespade(url);
    	} else if("esteelauder".equalsIgnoreCase(mallName)) {
    		mallProductCode = esteelauder(url);
    	} else if("nordstrom".equalsIgnoreCase(mallName)) {
    		mallProductCode = nordstrom(url);
    	} else if("dsw".equalsIgnoreCase(mallName)) {
    		mallProductCode = dsw(url);
    	} else if("kipling".equalsIgnoreCase(mallName)) {
    		mallProductCode = kipling(url);
    	} else if("victoriassecret".equalsIgnoreCase(mallName)) {
    		mallProductCode = victoriassecret(url);
    	} else if("skinstore".equalsIgnoreCase(mallName)) {
    		mallProductCode = skinstore(url);
    	} else if("footlocker".equalsIgnoreCase(mallName)) {
    		mallProductCode = footlocker(url);
    	} else if("saks".equalsIgnoreCase(mallName)) {
    		mallProductCode = saks(url);
    	} else if("carters".equalsIgnoreCase(mallName)) {
    		mallProductCode = carters(url);
    	} else if("oshkosh".equalsIgnoreCase(mallName)) {
    		mallProductCode = oshkosh(url);
    	} else if("levi".equalsIgnoreCase(mallName)) {
    		mallProductCode = levi(url);
    	} else if("nautica".equalsIgnoreCase(mallName)) {
    		mallProductCode = nautica(url);
    	} else if("bloomingdales".equalsIgnoreCase(mallName)) {
    		mallProductCode = bloomingdales(url);
    	} else if("disneystore".equalsIgnoreCase(mallName)) {
    		mallProductCode = disneystore(url);
    	} else if("bobbibrowncosmetics".equalsIgnoreCase(mallName)) {
    		mallProductCode = bobbibrowncosmetics(url);
    	} else if("brooksbrothers".equalsIgnoreCase(mallName)) {
    		mallProductCode = brooksbrothers(url);
    	} else if("swarovski".equalsIgnoreCase(mallName)) {
    		mallProductCode = swarovski(url);
    	} else if("mankind".equalsIgnoreCase(mallName)) {
    		mallProductCode = mankind(url);
    	} else if("neimanmarcus".equalsIgnoreCase(mallName)) {
    		mallProductCode = neimanmarcus(url);
    	} else if("thehut".equalsIgnoreCase(mallName)) {
    		mallProductCode = thehut(url);
    	} else if("pharmacyonline".equalsIgnoreCase(mallName)) {
    		mallProductCode = pharmacyonline(url);
    	} else if("yodobashi".equals(mallName)) {
    		mallProductCode = yodobashi(url);
    	} else if("backcountry".equals(mallName)) {
    		mallProductCode = backcountry(url);
    	} else if("rei".equals(mallName)) {
    		mallProductCode = rei(url);
    	} else if("hksasa".equalsIgnoreCase(mallName)) {
    		mallProductCode = hksasa(url);
    	} else if("toryburch".equals(mallName)) {
    		mallProductCode = toryburch(url);
    	} else if("coggles".equals(mallName)) {
    		mallProductCode = coggles(url);
    	} else if("cnroyyoungchemist".equals(mallName)) {
    		mallProductCode = cnroyyoungchemist(url);
    	} else if("ssense".equals(mallName)) {
    		mallProductCode = ssense(url);
    	}
    	return mallProductCode;
	}
	
	public static String amazon(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		if(url.contains("?")) {
			url = url.substring(0, url.indexOf("?"));
		}
		
		Matcher matcher = Pattern.compile("(B|b)0([A-Za-z0-9]){8}").matcher(url);
		String uri = "";
		int s = 0;
		
		while(matcher.find()) {
			s += 1;
			uri = matcher.group(0);
			break;
		}
		
		if(s != 1) {
			return null;
		} else {
			return uri;
		}
	}
	
	public static String lookfantastic(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("\\d+(.html)").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(0, mallProductCode.length() - 5);
    	}
    	
    	return mallProductCode;
	}
	
	public static String gymboree(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		if(url.contains("?")) {
			url = url.substring(0, url.indexOf("?"));
		}
		
		Matcher matcher = Pattern.compile("-\\d+").matcher(url);
    	
		// 取最后一个
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(1);
    	}
    	
    	return mallProductCode;
	}
	
	public static String iherb(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		if(url.contains("?")) {
			url = url.substring(0, url.indexOf("?"));
		}
		
		Matcher matcher = Pattern.compile("/\\d+").matcher(url);
    	
		// 取最后一个
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		System.out.println(mallProductCode);
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(1);
    	}
    	
    	return mallProductCode;
	}
	
	public static String diapers(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		if(url.contains("?")) {
			url = url.substring(0, url.indexOf("?"));
		}
		
		Matcher matcher = Pattern.compile("-\\d+").matcher(url);
    	
		// 取最后一个
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		System.out.println(mallProductCode);
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(1);
    	}
    	
    	return mallProductCode;
	}
	
	public static String clinique(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("\\d+").matcher(url);
    	int idx = 0;
		
    	while(matcher.find()) {
    		idx++;
    		mallProductCode = matcher.group();
    		System.out.println(mallProductCode);
    		
    		if(idx == 2) {
    			break;
    		}
    	}
    	
    	return mallProductCode;
	}
	
	public static String ashford(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("([a-zA-Z0-9]+)(.pid)").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
		
		if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(0, mallProductCode.length() - 4);
    	}
    	
    	return mallProductCode;
	}
	
	public static String origins(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("\\d+").matcher(url);
    	int idx = 0;
		
    	while(matcher.find()) {
    		idx++;
    		mallProductCode = matcher.group();
    		
    		if(idx == 2) {
    			break;
    		}
    	}
    	
    	return mallProductCode;
	}
	
	public static String asos(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		if(url.contains("?")) {
			url = url.substring(0, url.indexOf("?"));
		}
		
		Matcher matcher = Pattern.compile("/\\d+").matcher(url);
    	
		// 取最后一个
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		System.out.println(mallProductCode);
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(1);
    	}
    	
    	return mallProductCode;
	}
	
	public static String walgreens(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		if(url.contains("?")) {
			url = url.substring(0, url.indexOf("?"));
		}
		
		Matcher matcher = Pattern.compile("ID=prod\\d+").matcher(url);
    	
		// 取最后一个
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		System.out.println(mallProductCode);
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(7);
    	}
    	
    	return mallProductCode;
	}
	
	public static String eastbay(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		if(url.contains("?")) {
			url = url.substring(0, url.indexOf("?"));
		}
		
		Matcher matcher = Pattern.compile("model:\\d+").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		System.out.println(mallProductCode);
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(6);
    	}
    	
    	return mallProductCode;
	}
	
	public static String famousfootwear(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		if(url.contains("?")) {
			url = url.substring(0, url.indexOf("?"));
		}
		
		Matcher matcher = Pattern.compile("-\\d+").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		System.out.println(mallProductCode);
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(1);
    	}
    	
    	return mallProductCode;
	}
	
	public static String gnc(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("productId=\\d+").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		System.out.println(mallProductCode);
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(10);
    	}
    	
    	return mallProductCode;
	}
	
	public static String ralphlauren(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("productId=\\d+").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		System.out.println(mallProductCode);
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(10);
    	}
    	
    	return mallProductCode;
	}
	
	public static String ninewest(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("/\\d+,").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
		
		if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(1, mallProductCode.length() - 1);
    	}
    	
    	return mallProductCode;
	}
	
	public static String katespade(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("([a-zA-Z0-9]+)(.html)|([a-zA-Z0-9]+)[-]([a-zA-Z0-9]+)(.html)").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
		
		if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(0, mallProductCode.length() - 5);
    	}
    	
    	return mallProductCode;
	}
	
	public static String esteelauder(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("\\d+").matcher(url);
    	int idx = 0;
		
    	while(matcher.find()) {
    		idx++;
    		mallProductCode = matcher.group();
    		System.out.println(mallProductCode);
    		
    		if(idx == 2) {
    			break;
    		}
    	}
    	
    	return mallProductCode;
	}
	
	public static String nordstrom(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		if(url.contains("?")) {
			url = url.substring(0, url.indexOf("?"));
		}
    	
    	Matcher matcher = Pattern.compile("\\d+").matcher(url);
    	
    	// 取最后一个
    	while(matcher.find()) {
    		mallProductCode = matcher.group();
    	}
    	
    	return mallProductCode;
	}
	
	public static String dsw(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("prodId=\\w+").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		System.out.println(mallProductCode);
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode) && mallProductCode.length() > 17) {
    		mallProductCode = mallProductCode.substring(mallProductCode.length() - 10);
    	}
    	
    	return mallProductCode;
	}
	
	public static String kipling(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("\\w+(.html)").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(0, mallProductCode.length() - 5);
    	}
    	
    	return mallProductCode;
	}
	
	public static String victoriassecret(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("ProductID=\\d+").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		System.out.println(mallProductCode);
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(10);
    	}
    	
    	return mallProductCode;
	}
	
	public static String skinstore(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("\\d+(.html)").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(0, mallProductCode.length() - 5);
    	}
    	
    	return mallProductCode;
	}
	
	public static String footlocker(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		if(url.contains("?")) {
			url = url.substring(0, url.indexOf("?"));
		}
		
		Matcher matcher = Pattern.compile("model:\\d+").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		System.out.println(mallProductCode);
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(6);
    	}
    	
    	return mallProductCode;
	}
	
	public static String saks(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("Eprd_id=\\d+").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		System.out.println(mallProductCode);
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(8);
    	}
    	
    	return mallProductCode;
	}
	
	public static String carters(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("\\w+(.html)").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(0, mallProductCode.length() - 5);
    	}
    	
    	return mallProductCode;
	}
	
	public static String oshkosh(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("\\w+(.html)").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(0, mallProductCode.length() - 5);
    	}
    	
    	return mallProductCode;
	}
	
	public static String levi(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		if(url.contains("?")) {
			url = url.substring(0, url.indexOf("?"));
		}
		
		Matcher matcher = Pattern.compile("/\\d+").matcher(url);
    	
		// 取最后一个
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		System.out.println(mallProductCode);
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(1);
    	}
    	
    	return mallProductCode;
	}
	
	public static String nautica(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("\\w+(.html)").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(0, mallProductCode.length() - 5);
    	}
    	
    	return mallProductCode;
	}
	
	public static String bloomingdales(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("(ID=)\\d+").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
		
		if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(3);
    	}
    	
    	return mallProductCode;
	}
	
	public static String disneystore(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("/\\d+/").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		System.out.println(mallProductCode);
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(1, mallProductCode.length() - 1);
    	}
    	
    	return mallProductCode;
	}
	
	public static String bobbibrowncosmetics(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("\\d+").matcher(url);
    	int idx = 0;
		
    	while(matcher.find()) {
    		idx++;
    		mallProductCode = matcher.group();
    		
    		if(idx == 2) {
    			break;
    		}
    	}
    	
    	return mallProductCode;
	}
	
	public static String brooksbrothers(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("/\\w+,").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
		
		if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(1, mallProductCode.length() - 1);
    	}
    	
    	return mallProductCode;
	}
	
	public static String swarovski(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("/\\d+").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(1);
    	}
    	
    	return mallProductCode;
	}
	
	public static String mankind(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		if(url.contains("?")) {
			url = url.substring(0, url.indexOf("?"));
		}
		
		Matcher matcher = Pattern.compile("\\d+(.html)").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(0, mallProductCode.length() - 5);
    	}
    	
    	return mallProductCode;
	}
	
	public static String neimanmarcus(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		if(url.contains("?")) {
			url = url.substring(0, url.indexOf("?"));
		}
		
		Matcher matcher = Pattern.compile("/prod\\d+").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(5);
    	}
    	
    	return mallProductCode;
	}
	
	public static String thehut(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("\\d+(.html)").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(0, mallProductCode.length() - 5);
    	}
    	
    	return mallProductCode;
	}
	
	public static String pharmacyonline(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("\\d+(.html)").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(1, mallProductCode.length() - 5);
    	}
    	
    	return mallProductCode;
	}
	
	public static String yodobashi(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		if(url.contains("?")) {
			url = url.substring(0, url.indexOf("?"));
		}
		
		Matcher matcher = Pattern.compile("/\\d+").matcher(url);
    	
		// 取最后一个
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(1);
    	}
    	
    	return mallProductCode;
	}
	
	public static String backcountry(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("skid=\\w+").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(5);
    	}
    	
    	return mallProductCode;
	}
	
	public static String rei(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("/\\d+").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(1);
    	}
    	
    	return mallProductCode;
	}
	
	public static String hksasa(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("(itemno=)\\d+").matcher(url);
    	
    	while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(7);
    	}
    	
    	return mallProductCode;
	}
	
	public static String toryburch(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("\\w+(.html)").matcher(url);
    	
    	while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(0, mallProductCode.length() - 5);
    	}
    	
    	return mallProductCode;
	}
	
	public static String coggles(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("\\d+(.html)").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(0, mallProductCode.length() - 5);
    	}
    	
    	return mallProductCode;
	}
	
	public static String cnroyyoungchemist(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		Matcher matcher = Pattern.compile("\\d+(.html)").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(1, mallProductCode.length() - 5);
    	}
    	
    	return mallProductCode;
	}
	
	public static String ssense(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		if(url.contains("?")) {
			url = url.substring(0, url.indexOf("?"));
		}
		
		Matcher matcher = Pattern.compile("/\\d+").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(1);
    	}
    	
    	return mallProductCode;
	}
	
	public static String mybag(String url) {
		if(StringUtils.isEmpty(url)) {
			return null;
		}
		
		String mallProductCode = null;
		
		if(url.contains("?")) {
			url = url.substring(0, url.indexOf("?"));
		}
		
		Matcher matcher = Pattern.compile("\\d+(.html)").matcher(url);
    	
		while(matcher.find()) {
    		mallProductCode = matcher.group();
    		break;
    	}
    	
    	if(StringUtils.isNotEmpty(mallProductCode)) {
    		mallProductCode = mallProductCode.substring(0, mallProductCode.length() - 5);
    	}
    	
    	return mallProductCode;
	}
}
