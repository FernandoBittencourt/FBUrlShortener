package fernandobittencourt.project.facade;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import fernandobittencourt.project.dao.UrlDao;
import fernandobittencourt.project.exception.CustomAliasExistsException;
import fernandobittencourt.project.exception.ShortenedUrlNotFoundException;
import fernandobittencourt.project.model.Url;


public class UrlFacade {
	
	
	
	
	public Url shortenUrl(String url,String customAlias) throws CustomAliasExistsException{
		UrlDao dao =new UrlDao();
		
		Long time=System.currentTimeMillis();
		
		Url hashedUrl= new Url();
		
		if(customAlias==null){
			customAlias=suggestCustomAlias();
		}else if(dao.get(customAlias)!=null){
			throw new CustomAliasExistsException();
		}
		
		hashedUrl.setShortenedUrl(customAlias);

		hashedUrl.setAccess(0L);
		hashedUrl.setCompleteUrl(url);
		
		dao.put(hashedUrl.getShortenedUrl(), hashedUrl.getCompleteUrl(),hashedUrl.getAccess());
		
		time=System.currentTimeMillis()-time;
		hashedUrl.setTime(time);
		return hashedUrl;
	}
	
	public String retriveUrl(String alias) throws ShortenedUrlNotFoundException{
		UrlDao dao =new UrlDao();
		
		Url url=dao.get(alias);
		
		if(url==null){
			throw new ShortenedUrlNotFoundException();
		}
		dao.acessDone(url);
		url.setAccess(url.getAccess()+1);
		
		return url.getCompleteUrl();
	}
	
	
	
	public List<Url> topTen() {
		UrlDao dao =new UrlDao();
		return dao.topTen();
	}
	
	
	private String suggestCustomAlias(){
		UrlDao dao =new UrlDao();
		String allowedChar = "0123456789" +
				"abcdefghijklmnopqrstuvwxyz" +
				"ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random generator = new Random();
		
		String customAlias="";
		int i=generator.nextInt(allowedChar.length());
		customAlias=customAlias.concat(allowedChar.substring(i-1,i));
		while(dao.get(customAlias)!=null){
			i=generator.nextInt(allowedChar.length());
			customAlias=customAlias.concat(allowedChar.substring(i-1,i));
		}
		return customAlias;
	}
	
}
