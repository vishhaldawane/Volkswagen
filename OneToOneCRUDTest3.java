import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.java.entity.Department;
import com.java.one2one.Passport;
import com.java.one2one.Person;

public class OneToOneCRUDTest3 {
	
	
	
	
	@Test
	public void insertPerson() {
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("MyJPA"); //persistence.xml is read here 
		
		System.out.println("Entity Manager Factory : "+entityManagerFactory);
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//ctrl+shift+M
		
		System.out.println("Entity Manager : "+entityManager);
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
			Person person = new Person(); //new/blank entity object 
			person.setPersonName("Smith");
			entityManager.persist(person); //generate the insert query for us 
		transaction.commit();
	}
	
	@Test
	public void insertPassport() {
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("MyJPA"); //persistence.xml is read here 
		
		System.out.println("Entity Manager Factory : "+entityManagerFactory);
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//ctrl+shift+M
		
		System.out.println("Entity Manager : "+entityManager);
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
			Passport passport = new Passport(); //new/blank entity object 
			passport.setIssuedBy("Govt.Of India");
			passport.setIssueDate(LocalDate.of(2021, 12, 25));
			passport.setExpiryDate(LocalDate.of(2031, 12, 25));
		
			entityManager.persist(passport); //generate the insert query for us 
		transaction.commit();
	}
	
	@Test
	void assignExistingPassportToExistingPerson()
	{
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("MyJPA"); //persistence.xml is read here 
		
		System.out.println("Entity Manager Factory : "+entityManagerFactory);
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//ctrl+shift+M
		
		System.out.println("Entity Manager : "+entityManager);
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
	
		
				Person person 	  = entityManager.find(Person.class, 69);
				Passport passport = entityManager.find(Passport.class, 65);
				
				person.setPassport(passport);// are we setting the FK?
				passport.setPerson(person); // are we setting the FK?
				
				entityManager.merge(person);
				entityManager.merge(passport);
				
		transaction.commit();		
		
	}
	
	@Test
	public void insertNewPersonForExistingPassport() {
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("MyJPA"); //persistence.xml is read here 
		
		System.out.println("Entity Manager Factory : "+entityManagerFactory);
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//ctrl+shift+M
		
		System.out.println("Entity Manager : "+entityManager);
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
			Person person = new Person(); //transient - in memory
			person.setPersonName("King"); //transient - in memory
			
			Passport passport = entityManager.find(Passport.class, 67);
		
			person.setPassport(passport); //set the FK
			passport.setPerson(person); //set the FK
			
			entityManager.persist(person);
			entityManager.merge(passport); //update the FK of existing passport 

			transaction.commit();
		
	}
	
	@Test
	public void insertNewPassportForExistingPerson() {
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("MyJPA"); //persistence.xml is read here 
		
		System.out.println("Entity Manager Factory : "+entityManagerFactory);
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//ctrl+shift+M
		
		System.out.println("Entity Manager : "+entityManager);
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
			Person person = entityManager.find(Person.class, 68);
			
		
			Passport passport = new Passport(); //new object 
			passport.setIssuedBy("Govt.Of India");
			passport.setIssueDate(LocalDate.of(2018, 12, 25));
			passport.setExpiryDate(LocalDate.of(2028, 12, 25));
		 
			passport.setPerson(person); // set the FK of the passport
			person.setPassport(passport); //set the FK of the Person
			
			entityManager.persist(passport); // if the object does not exist, it will insert or update
			entityManager.merge(person);
		
			transaction.commit();
		
	}
	
	
	@Test
	public void insertNewPersonAlongWithNewPassport() {
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("MyJPA"); //persistence.xml is read here 
		
		System.out.println("Entity Manager Factory : "+entityManagerFactory);
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//ctrl+shift+M
		
		System.out.println("Entity Manager : "+entityManager);
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
			Person person = new Person(); //transient - in memory
			person.setPersonName("Robert"); //transient - in memory
			
			Passport passport = new Passport(); //new/blank entity object 
			passport.setIssuedBy("Govt.Of Nepal");
			passport.setIssueDate(LocalDate.of(2020, 12, 25));
			passport.setExpiryDate(LocalDate.of(2030, 12, 25));
		
			person.setPassport(passport); //set the FK
			passport.setPerson(person); //set the FK
			
			entityManager.persist(person);
			entityManager.persist(passport); //generate the insert query for us 

			transaction.commit();
	}
}





