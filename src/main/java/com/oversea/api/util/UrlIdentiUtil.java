package com.oversea.api.util;


/**
 * 识别文本中的url
 * 
 * @author yaohb
 *
 */
public class UrlIdentiUtil {
	
	private static final String _STR1 = "[link:";

	private static final String _STR2 = ":link]";
	
	private static final String[] HEADS = new String[4];
	
	static {
		HEADS[0] = new String("http://");
		HEADS[1] = new String("https://");
		HEADS[2] = new String("www.");
		HEADS[3] = new String("nvshen-bridge://");
	}
	
	private HeadStatus[] headStatus = null;
	
	public UrlIdentiUtil(){
		headStatus = new HeadStatus[HEADS.length];
		for(int i = 0 ; i < HEADS.length ; i++){
			headStatus[i] = new HeadStatus(HEADS[i]);
		}
	}
	
	public StringBuffer identifyUrlFromText(int start , StringBuffer txt){
		if(txt == null || txt.length() == 0){
			throw new IllegalArgumentException("参数不允许为空.");
		}
		int		end			= 0;
		int		headSize	= 0;
		boolean	findStart	= false;
		char	currentChar	= 0;
		
		for(HeadStatus h : headStatus){
			h.reset();
		}
		
		for( ; start < txt.length() && !findStart; start++){
			for(HeadStatus h : headStatus){
				if(txt.charAt(start) >='A' && txt.charAt(start) <= 'Z'){
					currentChar = (char)((int)txt.charAt(start) + 32);
				}else{
					currentChar = txt.charAt(start);
				}
				if(h.findChar(currentChar)){
					findStart = true;
					headSize = h.getHeadSize();
					break;
				}
			}
		}
		
		if(start >= txt.length()){
			return txt;
		}
		
		for(end = start ; end < txt.length() ; end++){
			if(
					(txt.charAt(end) >= 'a' && txt.charAt(end) <= 'z') ||
					(txt.charAt(end) >= 'A' && txt.charAt(end) <= 'Z') ||
					(txt.charAt(end) >= '0' && txt.charAt(end) <= '9') ||
					txt.charAt(end) == '+' || 
					txt.charAt(end) == '/' || 
					txt.charAt(end) == '?' || 
					txt.charAt(end) == '&' ||
					txt.charAt(end) == '%' || 
					txt.charAt(end) == '#' || 
					txt.charAt(end) == '=' || 
					txt.charAt(end) == '-' || 
					txt.charAt(end) == '_' || 
					txt.charAt(end) == '\\' || 
					txt.charAt(end) == '.' || 
					txt.charAt(end) == '\"' || 
					txt.charAt(end) == '{' || 
					txt.charAt(end) == '}' || 
					txt.charAt(end) == ',' || 
					txt.charAt(end) == ':'){
				// 合规字符
			}else{
				break;
			}
		}
		
		if(end < txt.length()){
			txt = identifyUrlFromText(end , txt);
		}
		
		if(findStart){
			return txt.insert(end, _STR2).insert(start-headSize, _STR1);
		}else{
			return txt;
		}
	}
	
	class HeadStatus{
		private char[] chars;
		private int point = 0;
		
		public HeadStatus(String urlHead){
			chars = urlHead.toCharArray();
		}
		
		public boolean findChar(char c){
			if(chars[point] == c){
				if(++point == chars.length){
					point = 0;
					return true;
				}
			}
			return false;
		}
		
		public String getHead(){
			return new String(chars);
		}
		
		public void reset(){
			point = 0;
		}
		
		public int getHeadSize(){
			return chars.length;
		}
		
	}
}
