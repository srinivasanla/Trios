package models;

import java.util.*;
import play.db.jpa.*;
import javax.persistence.*;
//import com.example.db.FileField;
//import com.example.storage.PreviewStorage;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import play.Logger;
import play.data.validation.Max;
import play.data.validation.Min;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class uploadarticle extends Model {

	public long articleId;
	public String title;
	public int createdAt;
	public boolean is_Published;

	// @PostPersist
	// @PostUpdate
	public String file;

	
	public Long accountId;
	public Category categoryId;

	public uploadarticle() {

	}

	public String toString() {
		return title;
	}
}
