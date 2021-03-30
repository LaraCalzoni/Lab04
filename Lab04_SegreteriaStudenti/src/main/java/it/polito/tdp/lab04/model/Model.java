package it.polito.tdp.lab04.model;
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
		return corsoDao.getTuttiICorsi();
	}
	
	public List <Studente> getStudenteByMatricola(Integer m) {
		return studenteDao.getStudenteByMatricola(m);
	}
	
		
}