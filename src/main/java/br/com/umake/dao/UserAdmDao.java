package br.com.umake.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		
		//passar dateLastVisit zerada, nao est'a passando certo ainda
		SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user.setDateOfRegistration(new Date());
		try {
			user.setDateLastVisit(data.parse("0000-00-00 00:00:00"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		try{
			
			this.session.save(user);
			
		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;
		
	}
	
	public UserAdm getUserAdm(UserAdm user){
		
		UserAdm userLoaded = (UserAdm) this.session.load( UserAdm.class , new Long(user.getId()) );
		return userLoaded;

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
	
	public Boolean editUserAdm( UserAdm user ){
		
		try{
			
			this.session.merge(user);
			throw new Exception("erro");
		}catch(Exception e){
			
			e.getMessage();
			return false;
			
		}
		
		//return true;

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