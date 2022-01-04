package kr.ridibooks.frontcontroller;

public class ViewResolver {

	public static String makeView(String nextPage) {
		   return "/WEB-INF/ridibooks/"+nextPage+".jsp";
	   }
}
