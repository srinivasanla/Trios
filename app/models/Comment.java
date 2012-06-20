package models;

import java.util.*;
import play.db.jpa.*;
import javax.persistence.*;

/**
 * General Entity model that represents the Comments in the system.
 * 
 * @author srinivasan
 * 
 */
@Entity
public class Comment extends Model {
	/**
	 * Holds the actual comment
	 */
	public String comment;
	/**
	 * For which article the comment was made?
	 */

	public Long articleId;
	/**
	 * Who posted the comment?
	 */
	public String postedBy;
	/**
	 * When was the comment posted?
	 */
	public Date postedOn;
	
	public String mailId;
}
