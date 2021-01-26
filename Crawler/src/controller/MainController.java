package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import model.MainService;

@Controller
	public class MainController {
		private MainService service;
		private String URL;
		private String KeyWord;
		
		@Autowired
		public MainController(MainService mainService) {
			super();
			this.service = mainService;
		}

		//메인 페이지 출력
		@RequestMapping(value="/main.do") 
		public String mainDP(HttpServletRequest request,Model model) throws IOException {
			System.out.println("메인 페이지 왔습니다.");
			String SURL="https://www.saramin.co.kr/zf_user/search?";
			String JURL="http://www.jobkorea.co.kr/Search/?";
			
			String SKeyWord = "searchType=search&searchword=";
			String JKeyWord = "stext=";
			
			if(request.getParameter("keyword") != null) {
				SKeyWord += request.getParameter("keyword");
				JKeyWord += request.getParameter("keyword");
			}
			
//			saraminSearch(SURL, SKeyWord);
			jobKoreaSearch(JURL, JKeyWord);
			
			return "main";
		}
		

		public String saraminSearch(String URL, String KeyWord) throws IOException{
			String params = "";
			Document doc = Jsoup.connect(URL+KeyWord).get();
			System.out.println("사람인: "+doc);
			return params;
		}
		
		class MyData {
			String name;
			String url;
			public MyData() {
				super();
			}
			public MyData(String name, String url) {
				super();
				this.name = name;
				this.url = url;
			}
		}
		
		ArrayList<Element> jobNameList = new ArrayList<>();
		ArrayList<Element> jobUrlList = new ArrayList<>();
		ArrayList<MyData> jobList;
		
		public String jobKoreaSearch(String URL, String KeyWord) throws IOException{
			String params = "";
			Document doc = Jsoup.connect(URL+KeyWord).get();
//			System.out.println("잡코리아: "+doc);
			
			Elements ElComName = doc.select(".post-list-corp a.name");
			System.out.println("ElComName size: "+ ElComName.size());
			for (int i = 0; i < ElComName.size(); i++) {
				System.out.println("=======================================================");
				System.out.println("잡코리아: "+ElComName.get(i));
				jobNameList.add(ElComName.get(i));
			}
			
			Elements ElComUrl = doc.select("div.post-list-info a.title");
			System.out.println("ElComUrl size: "+ ElComUrl.size());
			for (int i = 0; i < ElComUrl.size(); i++) {
				System.out.println("===================@@@@@@@@@===========================");
				System.out.println("잡코리아: "+ElComUrl.get(i).toString());
				jobUrlList.add(ElComUrl.get(i));
			}
			
//			for (int i = 0; i < ElComName.size(); i++) {
//				System.out.println("=======================================================");
//				System.out.println("URL: "+ElComUrl.get(i).toString());
//				System.out.println("Name: "+ElComName.get(i).toString());
////				jobList.add(new MyData(jobNameList.get(i).toString(),jobUrlList.get(i).toString()));
//			}
//			
//			for (int i = 0; i < jobList.size(); i++) {
//				System.out.println("=======================================================");
//				System.out.println("J-List: "+jobList.get(i));
//			}
			
			return params;
		}
		
}

	
	
	
	
	
	
	
	
	
	