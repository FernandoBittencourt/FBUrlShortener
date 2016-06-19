package fernandobittencourt.project.exception;

public class CustomAliasExistsException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public CustomAliasExistsException() {
        super("{ERR_CODE: 001, Description:CUSTOM ALIAS ALREADY EXISTS}");
    }
}
