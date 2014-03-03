package com.jnu.net;


import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class HtmlParse {
	
	/**
	 * 获取信息
	 * @param url
	 * @return
	 */
	public static  ArrayList<String> getHtmlInfo(String url){
		//Content content = new Content();
		 ArrayList<String>  urlList = new ArrayList<String>();
		// 查找特定的网址适配相应的函数 
		if(url.indexOf("xxx")!=-1){
			try {
				urlList=getxxInfo(url);
			} catch (Exception e) {
			}
		
		}else if(url.indexOf("http://jwcweb.jnu.edu.cn/index-new.asp")!=-1){
			try {
				urlList=getJWCNews(url);
			} catch (Exception e) {
			}
		}
		return urlList;
		
	}
	

	/**
	 * 
	 * @param url  
	 */
	public static ArrayList<String> getxxInfo(String url) throws Exception{
		return null;
		
	}
	
	/**
	 * 获取教务处最新通知
	 * @param url  http://jwcweb.jnu.edu.cn/index-new.asp
	 */
    
	public static  ArrayList<String> getJWCNews(String url) throws Exception{
	  ArrayList<String> urlList = new ArrayList<String>();
  	  String jwc="0";
  	  String tongzhi="0";
		 try { 
             //获取目标链接的Document 
             Document doc = Jsoup.connect(url).get(); 
             //获取所有input标签 
             Elements els = doc.getElementsByTag("A"); 
         //    System.out.println("\n\n\n"+els+"\n"); 
             //遍历所获得的标签 
            /* for (Element e : els) { 
                  System.out.println(e.nodeName()+":\t"+e.val()); 
             } */
       	//  System.out.println("------------------------------解析后的内容--------------------------");
       	//  System.out.println("\t");
             for (Element link : els) {
            	  String linkHref = link.attr("href");
            	  String linkText = link.text();
            	/*  System.out.println("------------------------------获取到的内容--------------------------"+"\t");
            	  System.out.println(link.nodeName()+":\t"+ "http://jwcweb.jnu.edu.cn/"+linkHref+"--"+linkText ); */
            	  
            	  /**由于直接获取回来的url不能直接使用，需要把中文转码才能访问该链接
            	   * 所以需要把url拆开，把中文名字编码后再重构
            	   */
            	 
            	  String []split=linkHref.split("教务处");    
            	  String href2=split[0]+jwc+split[1];        
            	  String []last=href2.split("通知");

            	//  System.out.println(link.nodeName()+":\t"+ "http://jwcweb.jnu.edu.cn/"+last[0]+tongzhi+last[1]);
            	//  System.out.println(linkText); 
            	  
            	  urlList.add("http://jwcweb.jnu.edu.cn/"+last[0]+tongzhi+last[1]);
            	  urlList.add(linkText);
            	  
             }
        } catch (IOException e) {          
            e.printStackTrace(); 
        } 
		return urlList;
		
	}


	
	/*public static void main(String[] args) {
	
		try {
			getHtmlInfo("http://jwcweb.jnu.edu.cn/index-new.asp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
	//}
}


// add to class if needed
/**
 * 获取6间房视频时长    
 *//*
private static String getVideoTime(Document doc, String url, String id) {
	String time = null;
	
	Element timeEt = doc.getElementById(id); 
	Elements links = timeEt.select("dt > a");
	
	
	for (Element link : links) {
	  String linkHref = link.attr("href");
	  if(linkHref.equalsIgnoreCase(url)){
		  time = link.parent().getElementsByTag("em").first().text();
		  break;
	  }
	}
	return time;
}*/

		
/**
 * 获取script某个变量的值
 * @param name  变量名称
 * @return   返回获取的值 
 *//*
private static String getScriptVarByName(String name, String content){
	String script = content;
	
	int begin = script.indexOf(name);
	
	script = script.substring(begin+name.length()+2);
	
	int end = script.indexOf(",");
	
	script = script.substring(0,end);
	
	String result=script.replaceAll("'", "");
	result = result.trim();
	
	return result;
}
*/

/**
 * 根据HTML的ID键及属于名，获取属于值
 * @param id  HTML的ID键
 * @param attrName  属于名
 * @return  返回属性值
 *//*
private static String getElementAttrById(Document doc, String id, String attrName)throws Exception{
	Element et = doc.getElementById(id);
	String attrValue = et.attr(attrName);
	
	return attrValue;
}*/



/**
 * 获取网页的内容
 */
/*
private static Document getURLContent(String url) throws Exception{
	Document doc = Jsoup.connect(url)
	  .data("query", "Java")
	  .userAgent("Mozilla")
	  .cookie("auth", "token")
	  .timeout(6000)
	  .post();
	return doc;
}
*/ 

















