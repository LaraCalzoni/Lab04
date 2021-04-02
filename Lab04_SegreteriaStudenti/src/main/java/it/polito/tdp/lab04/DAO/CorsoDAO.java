package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	 List <Corso> listaCorsi;
	 List <Studente> listaStudenti;
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso group BY nome";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				Corso c = new Corso(codins);
				c.setCrediti(numeroCrediti);
				c.setNome(nome);
				c.setPd(periodoDidattico);
				
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				
				corsi.add(c);
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	

	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String codiceCorso) {
	for (Corso c : listaCorsi) {
		if(c.getCodins().equals(codiceCorso)) {
			return c;
		}
	}
		return null;
		
	}

	
	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List <Studente> getStudentiIscrittiAlCorso(String codiceCorso) {
		String sql = "SELECT * "
				+ "FROM studente s, corso c, iscrizione i "
				+ "WHERE s.matricola=i.matricola && c.codins=i.codins && c.codins = ? "
				+"GROUP BY s.cognome";
	
     Studente s = null;
    // Corso c = null;
	 listaCorsi = new LinkedList <>();
	 listaStudenti = new LinkedList <>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			//c = getCorso(codiceCorso);
			st.setString(1, codiceCorso);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				s = new Studente(rs.getInt("matricola"));
				s.setCognome(rs.getString("cognome"));
				s.setNome(rs.getString("nome"));
				s.setCds(rs.getString("cds"));
				listaStudenti.add(s);
				
				
				
			}
			conn.close(); 
			st.close();
			rs.close();
		}
		
		
		
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
		catch (NullPointerException npe) {
			throw new RuntimeException(npe);
		}
		
		return listaStudenti;
		
		
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}

}
