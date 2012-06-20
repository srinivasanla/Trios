package controllers;

import java.util.Date;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import models.*;

import play.libs.Mail;
import play.mvc.Controller;

public class moderator extends Controller {

	public static void index(Long a) {
		List article = AllArticles.find("isPublished = false And Enabled = true order by id desc").fetch();
		Accounts c = Accounts.find("byId",a).first();
		render(article,c);
			
	}
	public static void email(String to,String subject,String message,Long accountid) throws EmailException {
		SimpleEmail email = new SimpleEmail();
		email.setFrom("3iosemags@gmail.com");
		email.addTo(to);
		email.setSubject(subject);
		email.setMsg(message);
		Mail.send(email);
		System.out.println(accountid);
		Accounts c = Accounts.find("byId",accountid).first();
		index(c.id);
	}
	
	

	

	public static void message() {
		render();

	}

	public static void upload(Long a) {
	Accounts c = Accounts.find("byId",a).first();
		render(c);

	}

	
	public static void retrivearticles(Long id,Long a) {
		AllArticles art = AllArticles.find("byId",id).first();
		Accounts c = Accounts.find("byId",a).first();
		System.out.println(c.id);
		System.out.println(art.title);
		System.out.println(art.createdAt);
		render(art,c);
	
		
		
	}

	public static void article(Long a) {
		Accounts c = Accounts.find("byId",a).first();
		System.out.println(c.id);
		if(c!=null)
				render(c);
		else
			upload(c.id);
	}

	public static void postarticle(String title,String categoryName,String tagName,String departmentName,String articleContent,boolean enabled,String author,String articleType) {
		AllArticles article = new AllArticles();
		
		article.title = title;
		article.articleType = articleType;
		article.articleContent = articleContent;
		
		article.enabled = enabled;
		article.author = author;
		article.departmentName = departmentName;		
		article.categoryName = categoryName;
		article.tagName = tagName;
		article.save();
		//index();
			
	}
	public static void publisharticle(Long id,Long a) {
	
		AllArticles _article = AllArticles.findById(id);
		
		Accounts c = Accounts.find("byId",a).first();
		// Set publish value to true
		if (_article != null)  {
		_article.isPublished = true;
		_article.save();
		reportuser(id,c.id);
		}
		else
			reportuser(id,c.id);
	}
	
		
	public static void reportuser(Long id,Long a)  {
		
		AllArticles article = AllArticles.find("byId",id).first();
		//System.out.println(article.mailId);
		Accounts c = Accounts.find("byId",a).first();
		
		render(article,c);
		
	}
public static void search(String keyword,Long a) {
		
		
	AllArticles art = AllArticles.find("byTitle", keyword).first();
	Accounts c = Accounts.find("byId",a).first();
	//	Comment com = Comment.find("byArticleId",art.id).first();
		if (art != null) {
			readarticle(art.id,c.id);
		} else
			articlenotfound(c.id);
	}
	public static void readarticle(Long id,Long a) {
		AllArticles art = AllArticles.find("byId",id).first();
		Accounts c = Accounts.find("byId",a).first();
	//List com = Comment.find("byArticleId",b).fetch();
	render(art,c);
	}
	public static void articlenotfound(Long a) {
	Accounts c = Accounts.find("byId",a).first();
	render(c);
	}
	public static void posted(Long a) {
		String s = "Moderator";
		List art = AllArticles.find("isPublished = false And Author = ?",s).fetch();
		Accounts c = Accounts.find("byId",a).first();
		render(art,c);
	}
	
	public static void published(Long a) {
		String s = "Moderator";
		List art = AllArticles.find("isPublished = true And Author = ?",s).fetch();
		Accounts c = Accounts.find("byId",a).first();
		render(art,c);
		
	}

	public static void draft(Long a) {
		String s = "Moderator";
		List art = AllArticles.find("isPublished = false And Enabled = false And Author = ?",s).fetch();
		Accounts c = Accounts.find("byId",a).first();
		render(art,c);
		

	}
	public static void allposted(Long a) {
		
		List art = AllArticles.find("isPublished = false order by id desc").fetch();
		Accounts c = Accounts.find("byId",a).first();
		render(art,c);
	}
	
	public static void allpublished(Long a) {
		
		List art = AllArticles.find("isPublished = true order by id desc").fetch();
		Accounts c = Accounts.find("byId",a).first();
		render(art,c);
	}
	

		public static void feedback() {
		render();
	}

	public static void faq() {
		render();
	}

	public static void savedarticle() {
		render();
	}

	public static void profile(Long a) {
	Accounts c = Accounts.find("byId",a).first();
		render(c);
	}

	public static void postedarticle() {
		render();
	}

	public static void preview() {
		render();
	}
	public static void display(Long id,Long a) {
		AllArticles art = AllArticles.findById(id);
		Accounts c = Accounts.find("byId",a).first();
		render(art,c);

	}
	public static void userdelete(Long a) {
	Accounts c = Accounts.find("byId",a).first();
		render(c);
	}
	public static void delete(Long as,Long a)  {
		AllArticles art = AllArticles.findById(as);
		Accounts c = Accounts.find("byId",a).first();
		art._delete();
		userdelete(c.id);
	}
	public static void profileupdate(String name,String password,Long registerNo,String mailId,String abouthim )   {
		Accounts Accounts = new Accounts();

		
		Accounts _Accounts = Accounts.find("byRegisterNo", registerNo).first();
		
		if (_Accounts != null)
			Accounts = _Accounts;
		Accounts.name = name;
		Accounts.registerNo = registerNo;
		Accounts.password = password;
		Accounts.mailId = mailId;
		Accounts.abouthim = abouthim;
		Accounts.save();
		index(Accounts.id);
	}
	public static void deletearticle(Long artid,Long a) {
	AllArticles art = AllArticles.findById(artid);
	Accounts c = Accounts.find("byId",a).first();
	art.delete();
	userdelete(c.id);
	}
}