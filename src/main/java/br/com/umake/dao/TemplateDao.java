package br.com.umake.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

public class TemplateDao {

	private Session session;
	
	public TemplateDao(Session session){
		
		this.session = session;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Template> getAll(){
		
		return this.session.createCriteria(Template.class).list();
		
	}
	
	public Template getCurrentTemplate(){
		
		return (Template) this.session.createCriteria(Template.class).add(Restrictions.eq("status", 1)).uniqueResult();
		
	}
	
	
}
