
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

import com.java.entity.Department;
import com.java.entity.Employee;


public class OneToManyCRUDTest4 {
	
	
	
	
	@Test
	public void insertDepartmentWithEmployees() {
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("MyJPA"); //persistence.xml is read here 
		
		System.out.println("Entity Manager Factory : "+entityManagerFactory);
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//ctrl+shift+M
		
		System.out.println("Entity Manager : "+entityManager);
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
			Department dept = new Department();
			
			dept.setDepartmentNumber(10);
			dept.setDepartmentName("IT");
			dept.setDepartmentLocation("NY");
			
			Employee empObj1 = new Employee(101,"Jack",5000.0f,dept);
			Employee empObj2 = new Employee(102,"Jane",6000.0f,dept);
			Employee empObj3 = new Employee(103,"Jill",7000.0f,dept);
			
			dept.getEmpSet().add(empObj1);
			dept.getEmpSet().add(empObj2);
			dept.getEmpSet().add(empObj3);
			
			entityManager.persist(dept);
			entityManager.persist(empObj1);
			entityManager.persist(empObj2);
			entityManager.persist(empObj3);
			
		transaction.commit();
	}
	
	
	
	
}





