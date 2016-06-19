package fernandobittencourt.project.controller;



import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fernandobittencourt.project.exception.CustomAliasExistsException;
import fernandobittencourt.project.exception.ShortenedUrlNotFoundException;
import fernandobittencourt.project.facade.UrlFacade;
import fernandobittencourt.project.model.Url;


@Controller
public class UrlController {  

    @RequestMapping("/shortenUrl")
    public String shortenUrl(@RequestParam String url, @RequestParam String customAlias,Model model) {
    	UrlFacade facade= new UrlFacade();
       String urlResult;
		try {
			urlResult = facade.shortenUrl(url,customAlias).toString();
			model.addAttribute("urlResult",urlResult);
		} catch (CustomAliasExistsException e) {
			model.addAttribute("urlResult","ERROR: Custom Alias Exists!");
		}
    	return "shortenUrl";
    }
    
    
    @RequestMapping("/retriveUrl")
    public ModelAndView retriveUrl(@RequestParam String alias, Model model) {
    	UrlFacade facade= new UrlFacade();
    	
    	String urlResult;
		try {
			urlResult = facade.retriveUrl(alias);
			return new ModelAndView("redirect:" + urlResult);
		} catch (ShortenedUrlNotFoundException e) {
			model.addAttribute("urlResult","ERROR: Shortened Url Not Found!");
		}
    	
		return new ModelAndView("retriveUrl");
    	
    }
    
    @RequestMapping("/topTen")
    public String topTen(Model model) {
    	UrlFacade facade= new UrlFacade();
    	
    	List<Url> urlList = facade.topTen();
    	
    	model.addAttribute("urlList",urlList);
    	return "topTen";
    }
    
    @RequestMapping("/")
    public void index() {
    }
}
