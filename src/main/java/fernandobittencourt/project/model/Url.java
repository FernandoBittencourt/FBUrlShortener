package fernandobittencourt.project.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "HashedUrl")
public class Url implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String shortenedUrl;
	
	private String completeUrl;
	
	private Long access;
	
	private Long time;
	
	public Url(){
	
	}
	
	@Column(name = "completeurl", unique = false, nullable = false, length = 100)
	public String getCompleteUrl() {
		return completeUrl;
	}
	public void setCompleteUrl(String completeUrl) {
		this.completeUrl = completeUrl;
	}
	
	 @Id
	@Column(name = "shortenedurl", unique = true, nullable = false, length = 100)
	public String getShortenedUrl() {
		return shortenedUrl;
	}
	public void setShortenedUrl(String shortenedUrl) {
		this.shortenedUrl = shortenedUrl;
	}
	@Column(name = "access", unique = false, nullable = false, length = 100)
	public Long getAccess() {
		return access;
	}
	public void setAccess(Long access) {
		this.access = access;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	
	@Override
    public String toString() {
        return "HashedUrl [shortenedUrl=" + shortenedUrl + ", completeUrl=" + completeUrl + ", access="
                + access + "]";
    } 
}