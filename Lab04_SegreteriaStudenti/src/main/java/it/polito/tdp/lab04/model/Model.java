package it.polito.tdp.lab04.model;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private StudenteDAO studenteDao;
	private CorsoDAO corsoDao;
	
	public Model() {
		studenteDao = new StudenteDAO();
		corsoDao = new CorsoDAO();
	}

		
	public List<Corso> getCorsi(){
		List<Corso> c = new LinkedList <>();
		Corso cTemp= new Corso("");
		cTemp.setNome("");
		c.add(cTemp);
	    c.addAll(corsoDao.getTuttiICorsi());
		
		return c;
	}
	
	public Studente getStudenteByMatricola(Integer m) {
		return studenteDao.getStudenteByMatricola(m);
	}
	
		
}