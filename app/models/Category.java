package models	;

import java.util.*;
import 	play.db.jpa.*;
import javax.persistence.*;

@Entity
public class Category extends Model  {

		public long categoryId;
		public String categoryName;
		
		@ManyToOne
		public AllArticles articleId;

		
		public 	Category(long categoryId,String categoryName)  {
		this.categoryId=categoryId;
		this.categoryName=categoryName;
		}
		
}
