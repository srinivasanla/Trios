package models;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import play.db.jpa.Model;

/**
 * General representational model that represents the Articles in the system.
 * 
 * @author srinivasan
 * 
 */
@Entity
public class AllArticles extends Model {
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
	public String file;
	public String mailId;
	public String departmentName;
	public String description;
	public Long accountId;
	public int count;
	public String articleType;
	public String author;
	@Lob
	//@column(columnDefinition = "")
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
