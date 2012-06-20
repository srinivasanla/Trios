package models;

import java.util.*;
import 	play.db.jpa.*;
import javax.persistence.*;

@Entity
public class Department extends Model  {

		public String departmentname;
		public int departmentId;
		
		
		public Department(int departmentId,String departmentname)  {
		this.departmentname=departmentname;
		this.departmentId=departmentId;
		}
				
}
