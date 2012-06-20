package controllers;


import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.io.*;

import models.*;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;

import play.libs.Mail;
import play.mvc.Controller;

import play.data.validation.*;
import play.data.validation.Error;

import play.cache.Cache;

public class user extends Controller {


	public static void index(Long id) {
		
		Accounts a = (Accounts) Cache.get("userAuth");
		System.out.println(a);
		if(a!=null) {
				Accounts c = Accounts.find("byId",id).first();
				System.out.println(c.registerNo);
				if(c!=null)  {
				render(c);
			 
				}
				else  {
					faq(c.id);
				}
				}
		else {
		Application.pleaselogin();
		}
		
	}
	
	public static void underconstruction(long a) {
		
		Accounts b = (Accounts) Cache.get("userAuth");
		System.out.println(b);
		if(b!=null) {
			Accounts c = Accounts.find("byId",a).first();
			System.out.println(c.id);
			render(c);	
		}
		else {
		Application.pleaselogin();
		}
	}
	public static void supports(long a)  {
		Accounts b = (Accounts) Cache.get("userAuth");
		System.out.println(b);
		if(b!=null) {
			Accounts c = Accounts.find("byId",a).first();
			System.out.println(c.id);
			render(c);
		}
		else {
		Application.pleaselogin();
		}
	}
		
	public static void faq(long a)  {
		Accounts b = (Accounts) Cache.get("userAuth");
		System.out.println(b);
		if(b!=null) {
			Accounts c = Accounts.find("byId",a).first();
			System.out.println(c.id);
			render(c);
		}
		else {
		Application.pleaselogin();
		}
	}
	public static void uploadfile(long a) {
		Accounts b = (Accounts) Cache.get("userAuth");
		System.out.println(b);
		if(b!=null) {
			Accounts c = Accounts.find("byId",a).first();
			System.out.println(c.id);
			render(c);
		}
		else {
		Application.pleaselogin();
		}
	}

	public static void article(Long a) {
		Accounts b = (Accounts) Cache.get("userAuth");
		System.out.println(b);
		if(b!=null) {
			Accounts c = Accounts.find("byId",a).first();
			System.out.println(c.id);
			render(c);
		}
		else {
		Application.pleaselogin();
		}
	}

	public static void uploadarticle(String title,String articleContent,String categoryName,String tagName,String departmentName,String articleType,boolean enabled,String author,String mailId,Long AccountsId) {
		Accounts a = (Accounts) Cache.get("userAuth");
		System.out.println(a);
		if(a!=null) {
			AllArticles up = new AllArticles();
			up.title = title;
			up.articleContent  = articleContent;
			up.save();
		}
		else {
		Application.pleaselogin();
		}
	}
	
	public static void drafts()   {
		Accounts a = (Accounts) Cache.get("userAuth");
		System.out.println(a);
		if(a!=null) {
		render();
		}
		else {
		Application.pleaselogin();
		}
	}
	public static void single() {
		Accounts a = (Accounts) Cache.get("userAuth");
		System.out.println(a);
		if(a!=null) {
				render();
		}
		else {
		Application.pleaselogin();
		}
	}

	public static void message() {
		Accounts a = (Accounts) Cache.get("userAuth");
		System.out.println(a);
		if(a!=null) {
			render();
		}
		else {
		Application.pleaselogin();
		}
	}

	
	public static void upload(Long a) {
		Accounts b = (Accounts) Cache.get("userAuth");
		System.out.println(b);
		if(b!=null) {
			Accounts c = Accounts.find("byId",a).first();
			render(c);
		}
		else {
		Application.pleaselogin();
		}
	}
	public static void draft(Long a)  {
		Accounts b = (Accounts) Cache.get("userAuth");
		System.out.println(b);
		if(b!=null) {
				Accounts c = Accounts.find("byId",a).first();
				boolean AccountsId;
				Long u = c.id;
				AllArticles arti = AllArticles.find("byAccountId",a).first();
				System.out.println(a);
				if(arti != null)  {
					AccountsId = true;
				}
				else  {
					AccountsId=false;
				}
				List art = AllArticles.find("isPublished = false AND Enabled = false AND AccountId=?",u).fetch();	
				render(c,art);			
		}
		else {
		Application.pleaselogin();
		}
	}
	public static void published(Long a) {
		Accounts b = (Accounts) Cache.get("userAuth");
		System.out.println(b);
		if(b!=null) {
				Accounts c = Accounts.find("byId",a).first();
				boolean AccountsId;
				Long u = c.id;
				AllArticles arti = AllArticles.find("byAccountId",a).first();
				System.out.println(a);
				if(arti != null)  {
					AccountsId = true;
				}
				else  {
					AccountsId=false;
				}
				List art = AllArticles.find("isPublished = true  AND AccountId=?",u).fetch();	
				render(c,art);
		}
		else {
		Application.pleaselogin();
		}
	}

	public static void posted(Long a) {
		Accounts b = (Accounts) Cache.get("userAuth");
		System.out.println(b);
		if(b!=null) {
				Accounts c = Accounts.find("byId",a).first();
				boolean AccountsId;
				Long u = c.id;
				AllArticles arti = AllArticles.find("byAccountId",a).first();
				System.out.println(a);
				if(arti != null)  {
					AccountsId = true;
				}
				else  {
					AccountsId=false;
				}
				List art = AllArticles.find("isPublished = false AND AccountId=?",u).fetch();	
				render(c,art);
		}
		else {
		Application.pleaselogin();
		}
	}

	public static void postarticle(String title,String articleType,String departmentName,String categoryName,String tagName,String author,Long accountId,String mailId,File articleContent,Boolean enabled,String description) throws IOException {
		Accounts a = (Accounts) Cache.get("userAuth");
		System.out.println(a);
		if(a!=null) {
					validation.required(title);
					validation.required(articleType);
					validation.required(departmentName);
					validation.required(categoryName);
					validation.required(tagName);
					validation.required(articleContent);
					validation.required(enabled);
					validation.required(description);
					
					if(validation.hasErrors())   {
						for(Error error : validation.errors()) {
						 System.out.println(error.message());
							params.flash(); // add http parameters to the flash scope
							validation.keep(); // keep the errors for the next request
							upload(accountId);
						}
					}
					
					AllArticles article = new AllArticles();
					
					article.title = title;
					article.articleType = articleType;
					article.departmentName = departmentName;		
					article.categoryName = categoryName;
					article.tagName = tagName;
					article.author = author;
					article.accountId = accountId;
					article.mailId = mailId;
					//article.articleContent = articleContent;
					article.enabled = enabled;
					article.description = description;
					
					// article.save();
					System.out.println(articleContent.getCanonicalPath());
					StringBuilder text = new StringBuilder();
					String NL = System.getProperty("line.separator");
					Scanner scanner = new Scanner(new FileInputStream(articleContent.getAbsolutePath()));
					try {
						while (scanner.hasNextLine()){
						text.append(scanner.nextLine() + NL);
						}
					} finally {
					  scanner.close();
					}
					
					article.articleContent = text.toString();
					// article(accountId);
					article.save();
					index(accountId);
		}
		else {
		Application.pleaselogin();
		}
	}
	public static void post(String title,String articleType,String departmentName,String categoryName,String tagName,String author,Long accountId,String mailId,String articleContent,Boolean enabled) throws IOException {
		Accounts a = (Accounts) Cache.get("userAuth");
		System.out.println(a);
		if(a!=null) {
				AllArticles article = new AllArticles();
				
				article.title = title;
				article.articleType = articleType;
				article.departmentName = departmentName;		
				article.categoryName = categoryName;
				article.tagName = tagName;
				article.author = author;
				article.accountId = accountId;
				article.mailId = mailId;
				article.articleContent = articleContent;
				article.enabled = enabled;
				// article.save();
				article.save();
		}
		else {
		Application.pleaselogin();
		}
	}

	public static void articlepost(String title,String articleType,String departmentName,String categoryName,String tagName,String author,Long accountId,String mailId,String articleContent,Boolean enabled,String description) {
		Accounts a = (Accounts) Cache.get("userAuth");
		System.out.println(a);
		if(a!=null) {
					
					validation.required(title);
					validation.required(articleType);
					validation.required(departmentName);
					validation.required(categoryName);
					validation.required(tagName);
					validation.required(articleContent);
					validation.required(enabled);
					validation.required(description);
					
					if(validation.hasErrors())   {
						for(Error error : validation.errors()) {
						 System.out.println(error.message());
							params.flash(); // add http parameters to the flash scope
							validation.keep(); // keep the errors for the next request
							article(accountId);
						}
					}
					AllArticles art = new AllArticles();
					
					art.title = title;
					art.articleType = articleType;
					art.departmentName = departmentName;		
					art.categoryName = categoryName;
					art.tagName = tagName;
					art.author = author;
					art.accountId = accountId;
					art.mailId = mailId;
					art.articleContent = articleContent;
					art.enabled = enabled;
					art.description = description;
					art.save();
					article(accountId);
					System.out.println(accountId);
		}
		else {
		Application.pleaselogin();
		}
	}
	public static void editdraft(Long a,Long ar)  {
		Accounts b = (Accounts) Cache.get("userAuth");
		System.out.println(b);
		if(b!=null) {
			Accounts c = Accounts.find("byId",a).first();
			System.out.println(c.id);
			AllArticles art = AllArticles.find("byId",ar).first();
			System.out.println(art.author);
			render(c,art);
		}
		else {
		Application.pleaselogin();
		}
	}
	
	public static void sample(Long a)  {
		Accounts b = (Accounts) Cache.get("userAuth");
		System.out.println(b);
		if(b!=null) {
				Accounts c = Accounts.find("byId",a).first();
				System.out.println(c.id);
				render(c);
		}
		else {
		Application.pleaselogin();
		}
	}
	public static void sample()   {
		render();
	}
	public static void savedarticle() {
		render();
	}

	public static void profile(Long a) {
		Accounts b = (Accounts) Cache.get("userAuth");
		System.out.println(b);
		if(b!=null) {
			Accounts c = Accounts.find("byId",a).first();
			render(c);
		}
		else {
		Application.pleaselogin();
		}
	}
	public static void profileupdate(String name,String password,Long registerNo,String mailId,String abouthim,String  fb,String blogger,String twitter)   {
		Accounts a = (Accounts) Cache.get("userAuth");
		System.out.println(a);
		if(a!=null) {
					Accounts Accounts = new Accounts();
					Accounts _Accounts = Accounts.find("byRegisterNo", registerNo).first();
					if (_Accounts != null)
						Accounts = _Accounts;
					Accounts.name = name;
					Accounts.registerNo = registerNo;
					Accounts.password = password;
					Accounts.mailId = mailId;
					Accounts.fb = fb;
					Accounts.blogger= blogger;
					Accounts.twitter = twitter;
					Accounts.abouthim = abouthim;
					Accounts.save();
					index(Accounts.id);
		}
		else {
		Application.pleaselogin();
		}				
	}

	public static void postedarticle() {
		Accounts a = (Accounts) Cache.get("userAuth");
		System.out.println(a);
		if(a!=null) {
			render();
		}
		else {
		Application.pleaselogin();
		}
	}
	public static void search(Long a,String keyword) {
		Accounts b = (Accounts) Cache.get("userAuth");
		System.out.println(b);
		if(b!=null) {
				Accounts c =Accounts.find("byId",a).first();
				System.out.println(c.id);
				AllArticles art = AllArticles.find("byTitle", keyword).first();
			//	Comment com = Comment.find("byArticleId",art.id).first();
				if (art != null) {
					readarticle(c.id,art.id);
				} else
					index(c.id);
		}
		else {
		Application.pleaselogin();
		}
	}
	public static void readarticle(Long s,Long b)  {
		Accounts a = (Accounts) Cache.get("userAuth");
		System.out.println(a);
		if(a!=null) {
				Accounts c =Accounts.find("byId",s).first();
				System.out.println(c.id);
				AllArticles art = AllArticles.find("byId",b).first();
				//List com = Comment.find("byArticleId",b).fetch();
				render(c,art);
		}
		else {
		Application.pleaselogin();
		}
	}
	public static void preview(Long a,Long id) {
		Accounts b = (Accounts) Cache.get("userAuth");
		System.out.println(b);
		if(b!=null) {
				Accounts c =Accounts.find("byId",a).first();
				System.out.println(c.id);	
				AllArticles art = AllArticles.findById(id);
				render(art,c);
		}
		else {
		Application.pleaselogin();
		}
	}

	public static void logout(Accounts a)  {
		a.delete();
		Cache.delete("userAuth");
		Application.index();
	}
	public static void delete(Long a,Long id)  {
		Accounts b = (Accounts) Cache.get("userAuth");
		System.out.println(b);
		if(b!=null) {
				AllArticles art = AllArticles.findById(a);
				art._delete();
				Accounts c =Accounts.find("byId",a).first();
				System.out.println(c.id);	
				deletedsucessfully(c.id);
		}
		else {
		Application.pleaselogin();
		}
	}
	public static void deletedsucessfully(Long a)  {
		Accounts b = (Accounts) Cache.get("userAuth");
		System.out.println(b);
		if(b!=null) {
				Accounts c =Accounts.find("byId",a).first();
				System.out.println(c.id);
				render(c);
		}
		else {
		Application.pleaselogin();
		}
	}
	public static void passcheck(Long a)  {
		Accounts b = (Accounts) Cache.get("userAuth");
		System.out.println(b);
		if(b!=null) {
				Accounts c =Accounts.find("byId",a).first();
				System.out.println(c.id);
				render(c);
		}
		else {
		Application.pleaselogin();
		}
	}
	public static void profilecheck(Long id,String password)  {
		Accounts a = (Accounts) Cache.get("userAuth");
		System.out.println(a);
		if(a!=null) {
			validation.required(password);
			
			
				Accounts c =Accounts.find("byId",id).first();
				System.out.println(c.password);
				System.out.println(password);
			if(validation.hasErrors())   {
				for(Error error : validation.errors()) {
				 System.out.println(error.message());
					params.flash(); // add http parameters to the flash scope
					validation.keep(); // keep the errors for the next request
					passcheck(c.id);
				}
			}
				if(c.password.matches(password))
					profile(c.id);
				else
					index(c.id);
		}
		else {
		Application.pleaselogin();
		}
	}
	}