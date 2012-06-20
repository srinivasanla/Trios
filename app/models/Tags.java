package models;

import java.util.*;
import play.db.jpa.*;
import javax.persistence.*;

/**
 * General representation model that represents the Tags in the system
 * 
 * @author srinivasan
 * 
 */
@Entity
public class Tags extends Model {

	public String tagName;
	public long tagId;
	@ManyToOne
	public AllArticles articleId;
}