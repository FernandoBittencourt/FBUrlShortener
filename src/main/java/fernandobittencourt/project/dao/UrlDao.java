package fernandobittencourt.project.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import fernandobittencourt.project.model.Url;

@Repository
public class UrlDao {
	private Session session = new Configuration().configure().buildSessionFactory().openSession();
	

	public Url get(String shortenerUrl){
        return (Url) session.get(Url.class, shortenerUrl);
	}
	
	public boolean put(String shortenedUrl, String completeUrl, Long access){
		if(get(shortenedUrl)!=null){
			return false;
		}
		 session.beginTransaction();
		 Url url = new Url();

		 	url.setShortenedUrl(shortenedUrl);
		 	url.setCompleteUrl(completeUrl);
		 	url.setAccess(access);

	        session.save(url);
	        session.getTransaction().commit();
	        
	        return true;
	}
	public boolean acessDone(Url url){
		if(url==null){
			return false;
		}
		 session.beginTransaction();
		 
		 url.setAccess(url.getAccess()+1);

	     session.saveOrUpdate(url);
	     session.getTransaction().commit();
	        
	     return true;
	}

	@SuppressWarnings("unchecked")
	public List<Url> topTen() {
		String hql = "FROM Url h "
				+ "ORDER BY h.access DESC ";
		Query query = session.createQuery(hql);
		query.setMaxResults(10);
		List<Url> results = query.list();
		return results;
	}
	
}