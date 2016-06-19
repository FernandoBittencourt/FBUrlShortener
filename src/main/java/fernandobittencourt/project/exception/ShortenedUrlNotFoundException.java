package fernandobittencourt.project.exception;

public class ShortenedUrlNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public ShortenedUrlNotFoundException() {
        super("{ERR_CODE: 002, Description:SHORTENED URL NOT FOUND}");
    }
}
