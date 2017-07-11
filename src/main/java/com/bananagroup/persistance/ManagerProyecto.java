package com.bananagroup.persistance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.bananagroup.models.Proyecto;
import com.rcallejas.models.Departamento;
import com.rcallejas.models.Employee;

public class ManagerProyecto {

	private SessionFactory factory = null;

	private static ManagerProyecto instance = null;

	private ManagerProyecto() {
		// LE traemos la informacion del FristServlet
		factory = new Configuration().configure().buildSessionFactory();
	}

	public static ManagerProyecto getInstance() {
		if (instance == null) {
			instance = new ManagerProyecto();
		}
		return instance;
	}
/*
	public void insertEntities() {
		Session session = factory.openSession();

		Transaction t = session.beginTransaction();
		Employee e1 = new Employee("Juaquin", "Pardo");
		session.persist(e1);
		
		Departamento d1 = new Departamento("Marqueting", "Barcelona");
		session.persist(d1);
		//Esta forma del objeto Employee con 2 elementos no la teniamos definida en el modl
		//..As� es que necesitamos construir el onstructor apropiado o hacer "SET's"
	
		t.commit();// transaction is commited
		session.close();	
	}*/
	
//-----------practica 03 ---------
	//Nueva practica   Insert Empleado/ save identificador en llugar de persist 7 get  de la informacion
	//Y llamaremos los 2 metodos desde Servlet 
	/*
	public int insertEmpleado(Employee empleadoNuevo){
		Session session = factory.openSession();
		
		Transaction t = session.beginTransaction();
		int eid = ((Integer) session.save(empleadoNuevo)).intValue();
		
		t.commit();// transaction is commited
		session.close();
		
		return eid;
	}*/
	
	public Proyecto getProject(int pid){
		Session session = factory.openSession();
		
		Transaction t = session.beginTransaction();
		Employee elEmpleado =  session.get(Employee.class,eid);
		
		t.commit();// transaction is commited
		session.close();
		
		return elEmpleado;
	}
	//----------- fin practica 03 ---------
	
}
