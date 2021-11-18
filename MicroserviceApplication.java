package com.java.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceApplication.class, args);
	}

}
/*
 
  
  				WHY?


	Micro-Service[s]
			|
		service - "a typical activity" to "serve"   "a purpose"
		
		
		7 features of enterprise application
		1. Remote Capability
		2. High Availability
		3. Multi threading
		4. Pooling of resources
		5. Caching of resources
		6. Transaction management [ @Transactional ]
		7. Security
		
					MONOLITHIC APPLICATION - a BIG STONE
					
					
		Machine1					DATABASE <-- data logic, tables/views/indexes
		|  RC						|						 stored procedures/functions [ 1 ]
		|							|
		|	HA						|
[ cluster management  ]				|		
Machine2,Machine3,Machine4		BANK APPLICATION <-- monolithic application hosted here [ 5 <4> 3 2 ]
20		|	20		20			|
		|				-------------------------------------
		|				|		|		|		|		|
		Machines		PC		ATM		VISA	SMS		SMARTPHONE <--- 6 <-- presentation layer
		/devices		|		|		|		 |		|		
					Browser		Cash	restau/ IVR		android app
										pump    
					
					
					
		 
		 eg.					DepartmentRepository --> Department  -> Database [ dept ]
		 		DeptService - in spring |
		 			createDepartmentService(Department);
		 			findDepartmentService(int deptno);
		 			..
		 					3 EmployeeRepository -> 2 Employee -> Database [ 1 emp ]	
6	 5	 	4	EmpService	|
		 		 |	createEmployee(Employee)
		 		 |	findEmployee(int empno)
		 		 |	
		 		 |		RegistrationService  -> RegistrationRepository -> Database [ customer ]
		 		 |		|		registerCustomer(Customer)
		 		 |		|		
		 		 |		|	LoginService -> LoginRepository -> Database [ login ]
		 		 |		|	|	login(Customer)
6	5	  	4	CustomerService  -> 3 CustomerRepositry -> 2 Customer - > Database [ 1 customer ]
		 			|profileEditingService()
		 			|placeOrder(Order)
		 			|	  
		 		OrderService --> OrderRepository -> Database [ order ]       JpaRepository   
		 				| processOrder(Item)
		 				|
		 		ItemService -> ItemREpository -> Database [ item ] 
		 			|  processItems(Inventory)
		 			|
		 		InventoryService	-> InventoryRepository -> Database [ inventory db ]
		 			|	reduceStock(Product)
		 			|	increaseStock(Product)
		 			|
		 		ProductService  -> ProductRepository -> Database [ product ]
		 			|	viewProducts()
		 			|	addProduct()
		 			|	modifyProduct()
		 			|	deleteProduct()
		 		PriceService	->PriceRepository -> Database [ price ]
		 			|	getPriceOfAPeriod(Date,Product)		
		 			|
		 		DeliveryService  -> DeliveryRepository -> Database [ Customer, Product, Order, Address ]
		 			|		
		 		MailService  -> MailRepository -> Database [ Customer mail address ]
		 			|
		 		SMSService  -> SMSREpository -> Database [ Customer phone ]
		 		
		 		
		 		
		 		DB|DB|DB		DB	Machine2.1	DB	Machine3.1	DB
		 		|				|	|			|	|			|
		 		Machine1		Machine2		Machine3		Machine4
		 		1-5				6-10			11-15			16-20
		 		Emp Dept		Cust Ord		Item Product	Delivery-MailSMS
		 		8080			8085			8088			8089
		 						|				|
		 						|	http://ip:8088/item/getItems/101
								|	 |
		 						|    |
		 	http://ip:8085/cust/getCust/101		
		 								|
		 						consolidated output of above 2 services
		 							|
		 							UI <-- 6
		 						
		 						
		 	Challenges
		 	
		 		I. Automation of the components
		 		
		 		1. building
		 		2. monitoring <-- difficult to monitor and identification of problems
		 		3. deployement				
		 						
		 		II. Perceptibility
		 					small number of components to deploy is difficult [ monitor ]
		 					
	 	1 Servent [ car maintain |  room cleaning |  chef	 |   gardening | bill payment ]	
	 				servent1		servent2		servent3	servent4	 servent5 <-- HA
	 				
	 				
	 				Ms SQL Server <-- Highly Available [ HA ] 
	 								
	 			III. Configuration
	 			
	 			IV. Debugging
	 								
	 			V. Consistency
	 			
	 								
		 			
		 						




		product
		-------
		prodid	productname   price
		101		Onion			45	
		
		
		
		price
		--------
		prodid	startdate	enddate			minprice	maxprice	stdprice
		101		1-Jan-2021  31-Mar-2021		30			40			35
		101		1-Apr-2021	30-Jun-2021		35			45			40
		101		1-Jul-2021	30-Sep-2021		50			70			60
		101		1-Oct-2021	31-Dec-2021		80			100			90
		
		
	
	1 3  5 10 20 30 40 50 	80rs 100rs kg
	
	
	
							BankService() { transfer(); withdraw(); getBalance() }
							|10objects
	Machine1			Machine2 			Machine3 - registry server
	|JAVA				|JAVA						key and value 
	| int,String		|int, String
	proxy				proxy
	marshalling			unmarshalling
	|					|
	ip/port				ip/port
	|					|
	socket<------------>socket
	RMI					RMI
	
	
	Marker interface
	|
	Serializable
	Externalizable
	Remote
	
	
	interface Reactive <-- marker interface
	{
		//no methods here..its empty
	}
	
	interface Responsive
	{
	
	}
	
	interface Proactive 
	{
	
	}
	
	class Person implements Reactive <-- Person is Reactive
	{
	
	}
	
	class Student extends Person implements Responsive
	{
	
	}
	class Employee extends Student implements Proactive
	{
	}
	
	class Seminar
	{
			void entryLine1(Proactive p) { } <-- Employee can be passed here
			void entryLine2(Responsive p) { } <-- Student,Employee
			
			void entryLine3(Reactive p) { } <-- Person,Student,Employee
			
	}
	
	
	
	
	
	
	Remote method invocation - RPC [ ibm ]
		
	
	1. Eureka Service
			-this will register the microservice
			-owned by Netflix
			-spring cloud -- declarative management [ annotation way ]
	
	2. Item catalog service 
			some data - API generated
			
	3. Edge service
	
	
				1. registration		[ machine2 ]				[ machine 3]
									port : 8088						port : 8089
					+-----------Item Catalog Service  --------- Edge Service
					|											|   |
					|			  3. produce sports brands		|	|
					|											|	|	
					|	+----------------------------------------	|4. filter top sports brands
					|	|		2 . registration					|
			Eureka Server										Top Sports Brand
			port:8761
			[ machine1 ]
	
	
	
	1. Acutator - monitoring and managing application
	2. JPA - for persistence
	3. H2 fo memory database
	4. eureka discovery - for service registration
	5. rest repo - expose jpa repo as rest
	6. web - tomcat and spring mvc
	7. devtool - auto relead the context as the files change
	8. lombok - to reduce the boiler plate code
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
		
		
		



*/