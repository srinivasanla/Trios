package controllers;

import java.util.Date;
import java.util.List;

import models.*;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;

import play.libs.Mail;
import play.mvc.Controller;

import play.data.validation.*;
import play.data.validation.Error;

public class admin extends Controller {
	
	public static void index() {
		List acc = Accounts.find("isEnabled = false  And isDeleted = false order by id desc").fetch();
		render(acc);
	}

	public static void sendemail(String to,String subject,String message) throws EmailException {
		SimpleEmail email = new SimpleEmail();
		email.setFrom("3iosemags@gmail.com");
		email.addTo(to);
		email.setSubject(subject);
		email.setMsg(message);
		Mail.send(email);
		index();
	}
	public static void reportuser(Long a)    {
	Accounts c = Accounts.find("byId",a).first();
	render(c);
	email();
		
	}
	
	public static void email() {
		render();
	}
	public static void registerstudent(String studentName,String registerNo,String departmentName,String year,Boolean haveAccount)  {
		validation.required(studentName);
		validation.required(registerNo);
		validation.required(departmentName);
		validation.required(year);
		
		if(validation.hasErrors())   {
			for(Error error : validation.errors()) {
			 System.out.println(error.message());
				params.flash(); // add http parameters to the flash scope
				validation.keep(); // keep the errors for the next request
				regstudent();
			}
		}
		
		
		RegisterStudent r =new RegisterStudent();
		r.studentName = studentName;
		r.departmentName = departmentName;
		r.registerNo = Long.parseLong(registerNo);
		r.haveAccount = haveAccount;
		r.year = Integer.parseInt(year);
		System.out.println(r.studentName);
		System.out.println(r.departmentName);
		System.out.println(r.registerNo);
		System.out.println(r.year);
		r.save();
		index();
	}
	public static void allusers()  {
		List acc = Accounts.find("isEnabled = true And isDeleted = false order by id desc").fetch();
		render(acc);
		
	}
	public static void regstudent()  {
		render();
	}
	public static void deletestudent()  {
		List account = Accounts.find("isDeleted = true order by id desc").fetch();
		render(account);
	}
	
	/*public static void register(String Username,String accountType)
	{	
		System.out.println(Username);
		System.out.println(accountType);
		Accounts account = Accounts.find("byUsername",Username).first();
		account.isEnabled=true;
		account.accountType=accountType;
		account.save();
		reportuser(account.id);
	}*/
	public static void register(String name,String password, long registerno, String mailId, 
			boolean accessLevel,
			Boolean isEnabled, String accountType) throws EmailException {
		Accounts account = new Accounts();
		// Get the account instance if the its valid
		Accounts _account = Accounts.find("byRegisterNo", registerno).first();
		//_logger.info("Register Number we are searching for is - " + registerno);
		if (_account != null)
			account = _account;
		
		account.name = name;
		account.registerNo = registerno;
		account.password = password;
		account.mailId = mailId;
		account.isEnabled = isEnabled;
		account.accountType = accountType;
		account.save();
		reportuser(account.id);		
	}
	public static void deleteuser(String name, 
			String password, long registerno, String mailId, 
			String accountType,Long Id, boolean accessLevel,
			Boolean isDeleted) throws EmailException {
		Accounts account = new Accounts();

		
		Accounts _account = Accounts.find("byRegisterNo", registerno).first();
		
		if (_account != null)
			account = _account;
		
		account.name = name;
		account.registerNo = registerno;
		account.password = password;
		account.mailId = mailId;
		account.accountType = accountType;
		account.isDeleted = isDeleted;
		account.save();
		reportuser(account.id);
	}
	

	public static void tostring() {
		render();

	}

	public static void lastarticles() {
		render();

	}

	public static void retriveaccount(Accounts ac) throws EmailException {

		render(ac);
		//reportuser(ac.id);
	}
	public static void retriveaccounts(Accounts ac) throws EmailException {

		render(ac);
		reportuser(ac.id);
			
	}
	public static void userdelete()  {
		render();
	}

	
	public static void message() {
		render();

	}

	public static void upload() {
		render();

	}

	public static void display(Long id) {
		AllArticles art = AllArticles.findById(id);
		render(art);

	}

	public static void retrivearticles(AllArticles art) {
		render();
	}
	

	public static void article(Long id) {
		AllArticles article = new AllArticles();
		render(article);

	}

	/**
	 * Post the article
	 * 
	 * @param title
	 * @param articleContent
	 */
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
	//	index();
			
	}

	public static void posted() {
		String s = "Admin";
		List art = AllArticles.find("isPublished = false And Author = ?",s).fetch();
		render(art);
	}
	public static void allposted() {
		
		List art = AllArticles.find("isPublished = false order by id desc").fetch();
		render(art);
	}
	public static void allpublished() {
		
		List art = AllArticles.find("isPublished = true order by id desc").fetch();
		render(art);
	}
	
	public static void published() {
		String s = "Admin";
		List art = AllArticles.find("isPublished = true And Author = ?",s).fetch();
		render(art);
		
	}

	public static void draft() {
		String s = "Admin";
		List art = AllArticles.find("isPublished = false And Enabled = false And Author = ?",s).fetch();
		render(art);
		

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

	public static void profile() {
		render();
	}
	public static void profileupdate() {
		render();
	}

	public static void postedarticle() {
		render();
	}
	public static void searchstudent(Long keyword) throws EmailException {
		Accounts acc = Accounts.find("registerNo= ? ",keyword).first();
		if (acc != null) {
			retriveaccounts(acc);
		} else
			nousers();
	}
	public static void nousers()  {
		render();
	}
	public static void search(String keyword) {
		
		
		AllArticles art = AllArticles.find("byTitle", keyword).first();
	//	Comment com = Comment.find("byArticleId",art.id).first();
		if (art != null) {
			readarticle(art.id);
		} else
			articlenotfound();
	}
	public static void readarticle(Long id) {
		AllArticles art = AllArticles.find("byId",id).first();
		//List com = Comment.find("byArticleId",b).fetch();
		render(art);
	}
	public static void articlenotfound() {
		render();
	}

	public static void logout()  {
	//	a.delete();
		Application.index();
	}
	public static void preview() {
		render();
	}
	public static void delete(Long a)  {
		AllArticles art = AllArticles.findById(a);
		art._delete();
		userdelete();
	}
	public static void home() {
		render();
	}
	public static void adminlogin(String username,String password) {
	
		if(username.matches("admin") && password.matches("pass"))
		index();
		else
		logout();
	}
}