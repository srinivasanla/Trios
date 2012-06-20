package models;

import java.util.*;
import play.db.jpa.*;
import javax.persistence.*;

/**
 * General representational model that represents the Articles in the system.
 * 
 * @author srinivasan
 * 
 */
@Entity
public class Draft extends Model {
	/**
	 * What is the name of the article?
	 */
	public String title;
	/**
	 * When was this article posted?
	 */
	@Column(columnDefinition = "TIMESTAMP")
	public Date createdAt;
	/**
	 * Is it yet published?
	 */
	public boolean isPublished;
	/**
	 * Actual content of the article
	 */
	public boolean enabled;
	
	public String mailId;
	public Long accountId;
	public String author;
	@Lob
	public String articleContent;
	/*
	 * Who posted the article?
	 */
	
	
	/**
	 * Which category does it belong to?
	 */
	
	public String categoryName;
	
	public String tagName;
	/**
	 * What are the ratings available?
	 */
/*
	@OneToMany(mappedBy = "articleId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Rating> rateValue;
	
	 * What are the comments available?
	 
	@OneToMany(mappedBy = "articleId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Comment> comment;  
*/
}
