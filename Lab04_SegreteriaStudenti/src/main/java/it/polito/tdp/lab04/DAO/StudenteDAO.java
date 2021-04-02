package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudenteByMatricola(Integer matricola){
		String sq1 = "SELECT * "+
				"FROM studente "+
				"WHERE matricola= ?";
		
     Studente s = null;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sq1);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				s = new Studente(rs.getInt("matricola"));
				s.setCognome(rs.getString("cognome"));
				s.setNome(rs.getString("nome"));
				s.setCds(rs.getString("cds"));
				
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
		
		return s;
	}
		
		public List <Corso> getCorsiByStudente(Integer matricola){
			
			String sql = "SELECT * "
					+ "FROM iscrizione i, corso c "
					+ "WHERE i.codins= c.codins AND i.matricola= ? "
					+ "GROUP BY c.nome";
			
	    List <Corso> corsi = new LinkedList <>();
	    Corso c = null;
			
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, matricola);
				ResultSet rs = st.executeQuery();
				
				while(rs.next()) {
				c = new Corso(rs.getString("codins"));
				c.setCrediti(rs.getInt("crediti"));
				c.setNome(rs.getString("nome"));
				c.setPd(rs.getInt("pd"));
				corsi.add(c);
				
					
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
			
			return corsi;
			
		}

	
	
	
	
	}
	
	

