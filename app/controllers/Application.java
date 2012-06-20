package controllers;

import java.util.Date;

import java.util.List;

import models.*;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import play.mvc.Controller;
import play.libs.*;
import play.cache.*;
import play.data.validation.Validation;
import play.data.validation.Error;
import java.util.*;
import java.lang.*;

import org.apache.commons.lang.NumberUtils;

public class Application extends Controller {
		
	public static void index() {
		
		List articles = AllArticles.find("IsPublished = true order by id desc").from(0).fetch(6);
		//List<AllArticles> art = AllArticles.find("IsPublished = true AND Enabled = true order by Count asc").fetch();

		//Criteria art = session.createCriteria(AllArticles.class);
		//art.addOrder( Order.desc("count") );
		//Criteria art = AllArticles.addOrder( Order.desc("count") );
		//List art = AllArticles.find("isPublished = true order by count desc").fetch();
		//Post.find("order by postDate desc").fetch();
		List acc = Accounts.find("name = NULL order by id desc").fetch();
		render(articles,acc);
			
	}
	
	public static void captcha(String id) {
	    Images.Captcha captcha = Images.captcha();
	    String code = captcha.getText("#E4EAFD");
	    Cache.set(id, code, "10mn");
	    renderBinary(captcha);
	}
	
	public static void show(Long id) {
		AllArticles q = AllArticles.findById(id);
		render(q);
	}

	public static void post() {

		render();
	}

	public static void single() {
		render();
	}

	public static void signup() {
		String randomID = Codec.UUID();
		render(randomID);
	}



	public static void signupForm(String name,
			String password, long registerNo, String mailId
			) {
		System.out.println("regNo - " + registerNo);
		
		validation.required(name).message("Enter Name");
		validation.minSize(password,6);
		validation.required(password).message("Enter Password");
		validation.required(registerNo).message("Enter RegisterNo");
		validation.email(mailId).message("Give a valid E-MailId");
		validation.required(mailId).message("Enter E-MailId");
		
		
		Accounts account = new Accounts();
		account.name = name;
		account.registerNo = (registerNo);
		account.password = password;
		account.mailId = mailId;
		RegisterStudent reg = RegisterStudent.find("RegisterNo=?",registerNo).first();
		
		if(validation.hasErrors())   {
			for(Error error : validation.errors()) {
             System.out.println(error.message());
				params.flash(); // add http parameters to the flash scope
				validation.keep(); // keep the errors for the next request
				signup();
			}
		}
		
		
		if(reg == null)   {
			notallowed();
		}
		
		Accounts acc = Accounts.find("byRegisterNo", registerNo).first();
		if (acc != null) {
			errors();
		} else {
			account.save();
			reg();	
		}

	}

	public static void search(String keyword) {
		AllArticles art = AllArticles.find("Title= ? And isPublished = true Or CategoryName= ?",keyword,keyword).first();
		
		
		if (art != null) {
			 Comment com = Comment.find("byArticleId",art.id).first();
			fullwidth(art.id,com);
		} else
			error();
	}
	public static void notallowed()  {
		render();
	}
	public static void commenterror()  {
		render();
	}
	public static void fullwidth(Long a,Comment c) {
		System.out.println(a);
		AllArticles art = AllArticles.find("byId",a).first();
		List com = Comment.find("byArticleId",a).fetch();
		int count = art.count;
		art.count = count+1;
		art.save();
		render(art,com);
		
	}

	public static void reg() {
		render();
	}

	public static void login() {
		render();
	}

	public static void authentication(Long registerNo, String password) {

		System.out.println(registerNo);
		Accounts account = Accounts.find("byRegisterNoAndPassword", registerNo,
				password).first();
		validation.required(registerNo);
		validation.required(password);
		
		if(validation.hasErrors())   {
			for(Error error : validation.errors()) {
             System.out.println(error.message());
				params.flash(); // add http parameters to the flash scope
				validation.keep(); // keep the errors for the next request
				login();
			}
		}
		String s="student";
		String m="moderator";
		String a="faculty";
			if (account != null && account.isEnabled == true && account.isDeleted == false)	{
				System.out.println(account.accountType);
				
				if(account.accountType.matches(s)) {
					Cache.set("userAuth",account);
					
					System.out.println(account.accountType);
					user.index(account.id);
				}
				else if(account.accountType.matches(a)) {
					System.out.println(account.accountType);
					user.index(account.id);
				}
				else if(account.accountType.matches(m)) {
					System.out.println(account.accountType);
					moderator.index(account.id); 
				}
			} else 
			errorlogin();
			
/*			if (account != null && account.isEnabled == true && account.isDeleted == false && account.accountType == "admin")	{
			account.save();
			admin.index(account.id);
			} else {
			errorlogin();
			}
			if (account != null && account.isEnabled == true && account.isDeleted == false && account.accountType == "moderator")	{
			account.save();
			moderator.index(account.id);
			} else {
			errorlogin();
			}
	*/		
	}
	public static void errorlogin()  {
		render();
	}
	public static void errors() {
		render();
	}
	public static void error() {
		render();
	}
	public static void adminlogin() {
		render();
	}
/*	public static void adminaunthentication(String usernames,String passwords)  {
		String u = "admin";
		String p = "pass";
		System.out.println(u+" "+p);
		System.out.println(usernames+passwords);
		if (usernames=="admin" && passwords=="pass")  {
			
					admin.index();
		}
		else if(usernames=="moderator"  && passwords=="moderator" )  
			moderator.index();
		else  
			adminlogin();
	}
	*/
	public static void feedback() {
		render();
	}

	public static void sports() {
		String s = "sports";
		
		List article = AllArticles.find("isPublished = true  AND ArticleType=?",s).fetch();
		
		render(article);
		
	}

	public static void technology() {
		String s = "technology";
		
		List article = AllArticles.find("isPublished = true  AND ArticleType=?",s).fetch();
		
		render(article);
	}

	public static void entertainment() {
		String s = "entertainment";
		
		List article = AllArticles.find("isPublished = true  AND ArticleType=?",s).fetch();
		
		render(article);
	
	}

	public static void culturals() {
		String s = "culturals";
		
		List article = AllArticles.find("isPublished = true  AND ArticleType=?",s).fetch();
		
		render(article);
	}

	public static void travel() {
		String s = "travels";
		
		List article = AllArticles.find("isPublished = true  AND ArticleType=?",s).fetch();
		
		render(article);
	}

	public static void project() {
		String s = "projects";
		
		List article = AllArticles.find("isPublished = true  AND ArticleType=?",s).fetch();
		
		render(article);
	
	}

	public static void computer() {
		String s = "computer";
		
		List article = AllArticles.find("isPublished = true AND DepartmentName = ?",s).fetch();
		render(article);
	}

	public static void communication() {
		String s = "communication";
		
		List article = AllArticles.find("isPublished = true AND DepartmentName = ?",s).fetch();
		
		render(article);
		
	}

	public static void electricle() {
		String s = "electricle";
		
		List article = AllArticles.find("isPublished = true AND DepartmentName = ?",s).fetch();
		
		render(article);
		
	}

	public static void bio() {
		
		String s = "biotechnology";
		
		List article = AllArticles.find("isPublished = true AND DepartmentName = ?",s).fetch();
		
		render(article);
	}

	public static void civil() {
		String s = "civil";
		
		List article = AllArticles.find("isPublished = true AND DepartmentName = ?",s).fetch();
		
		render(article);
	}

	public static void mechanical() {
		String s = "mechanical";
		
		List article = AllArticles.find("isPublished = true AND DepartmentName = ?",s).fetch();
		
		render(article);
	}

	public static void chemistry() {
		String s = "chemistry";
		
		List article = AllArticles.find("isPublished = true AND DepartmentName = ?",s).fetch();
		
		render(article);
	}
	public static void others() {
		String s = "others";
		
		List article = AllArticles.find("isPublished = true AND DepartmentName = ?",s).fetch();
		
		render(article);
	}
	public static void profile(String a)  {
		System.out.println(a);
		Accounts c = Accounts.find("byName",a).first();
		List article = AllArticles.find("byAuthor",a).fetch();
		System.out.println(c.name);
		//System.out.println(article.author);
		render(c,article);
	}
	
	public static void comment(String mailId,Long registerNo,Long articleId,String comment)  {
		Accounts a = Accounts.find("RegisterNo = ?",registerNo).first();
		System.out.println(registerNo);
		System.out.println(a.name);
		System.out.println(a.registerNo);
		if (a!=null)  {
		Comment c = new Comment();
		c.articleId = articleId;
		c.mailId = mailId;
		c.postedBy = a.name;
		c.comment = comment;
		c.save();
		System.out.println(a.name);System.out.println(a.name);
		System.out.println(a.id);
		System.out.println(c.postedBy);
		
		fullwidth(articleId,c);
		}
		else  {
			commenterror();
		}			
	}
	
	
	public static void download(uploadarticle f)  {
		render(f);
	}
	public static void pleaselogin()  {
		render();
	}
	public static void forgetpassword()  {
		render();
	}
	public static void doesnotmatch()  {
		render();
	}
	public static void passworscomform(Long registerNo,String mailId) throws EmailException  {
		Accounts account = Accounts.find("byRegisterNoAndMailId", registerNo,
				mailId).first();	
		if(account!= null) {
		sendemail(account);
		}
		else {
		doesnotmatch();
		}
	}
	
	public static void sendemail(Accounts a) throws EmailException {
		SimpleEmail email = new SimpleEmail();
		email.setFrom("3iosemags@gmail.com");
		email.addTo(a.mailId);
		String b = a.password;
		email.setSubject("No-reply(e-mags)");
		email.setMsg("your password is "+b+ " click here to login http://localhost:8080/application/login");
		Mail.send(email);
		index();
	}

	
}