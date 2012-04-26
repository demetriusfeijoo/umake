package br.com.umake.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.model.UserAdm;

@Component
public class UserAdmDao {

	private final Session session;

	public UserAdmDao( Session session ) {
		
		this.session = session;

	}
	
	public Boolean insertUserAdm(UserAdm user){

		user.setDateOfRegistration(new Date());
		user.setDateLastVisit(new Date());
		
		try{
			
			this.session.save(user);
			
		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;
		
	}
	
	public UserAdm getUserAdm(UserAdm user){
		
		return(UserAdm) this.session.load( UserAdm.class , new Long(user.getId()) );

	}

	public Boolean deleteUserAdm( UserAdm user ){
		
		try{
			
			this.session.delete(user);

		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<UserAdm> getAllUsersAdm(){
		
		return this.session.createCriteria(UserAdm.class).list();
		
	}

	public List<UserAdm> getUsersLimited(int offset, Integer largura, String sortname, String sortorder ){
		
		String HQL;
		
		if( sortname != null && sortorder != null )
			HQL = String.format("from UserAdm order by %s %s", sortname, sortorder);
		else if(sortname != null && sortorder == null)
			HQL = String.format("from UserAdm order by %s ", sortname);
		else
			HQL = String.format("from UserAdm");
		
		return this.session.createQuery(HQL).setFirstResult(offset).setMaxResults(largura).list();
		
	}
	
	public List<UserAdm> getUserByPropertyName(String propertyName, String value){
	
		if( !propertyName.isEmpty() && !value.isEmpty() )
			return this.session.createCriteria(UserAdm.class).add(Restrictions.ilike(propertyName, value)).list();
		
		return new ArrayList<UserAdm>();
	}
	
	public Boolean editUserAdm( UserAdm user ){
		
		try{
			
			this.session.merge(user);
			
		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;

	}
	
	public boolean updateLastDate(UserAdm user){
		
		try{
			
			this.session.saveOrUpdate(user);
			
		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;		
	}
		
	public UserAdm findUserAdm(UserAdm user) {
		
		UserAdm userFound = (UserAdm) session.createCriteria(UserAdm.class)
				.add(Restrictions.eq("login", user.getLogin()))
				.add(Restrictions.eq("password", user.getPassword()))
				.add(Restrictions.eq("userBlock", false)).uniqueResult();
		
		return userFound;
		
	}

}