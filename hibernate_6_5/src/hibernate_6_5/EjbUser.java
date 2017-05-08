package hibernate_6_5;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;







/**
 * Session Bean implementation class EjbUser
 */
@TransactionManagement(value=TransactionManagementType.BEAN)
@Stateless
public class EjbUser {

    /**
     * Default constructor. 
     */
    public EjbUser() {
        // TODO Auto-generated constructor stub
    }
    @PersistenceContext(unitName = "hibernate_6_5")
    private EntityManager entityManager;

    @Resource
    private EJBContext context;
    

    
       public List<User> findUsers(){
              TypedQuery<User> query = entityManager.createNamedQuery("User.findAll", User.class);        
              return query.getResultList();
       }
       
       public void addNew(User user)  {
    	   
    	   UserTransaction tx = context.getUserTransaction();
    	   //User user2 = new User();

			try {
				
				
				tx.begin();
				entityManager.persist(user);
				//entityManager.persist(user2);
	            tx.commit();
	           // entityManager.close();
	           

			} catch (Throwable t) {
				// TODO Auto-generated catch block
				//t.printStackTrace();
				try {
					tx.rollback();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			}


			

    	    
             
       }
    
       
       public void deleteU(int user) {
    	   System.err.println("mphka deleteU");
    	   
    	   UserTransaction tx = context.getUserTransaction();
	try {
				
				
				tx.begin();
				User userd = entityManager.find( User.class, user );
    	      entityManager.remove( userd );
    	      
	            tx.commit();
	           // entityManager.close();
	           

			} catch (Throwable t) {
				// TODO Auto-generated catch block
				//t.printStackTrace();
				try {
					tx.rollback();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
       }
       
     
    
       }}
