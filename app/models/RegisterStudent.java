package models;

import javax.persistence.Column;
import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class RegisterStudent extends Model {
	
	public Long registerNo;
	public String studentName;
	public String departmentName;
	public Integer year;
	@Column(columnDefinition = "SMALLINT default  '0'")
	public Boolean haveAccount;

}