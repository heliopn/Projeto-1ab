package mvc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Form {
	
	protected int id;
	protected String question;
	protected String answer;
	protected String autor;
	protected boolean hidden;
	protected int userId;

}
