package it.manytomanyjpamaven.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.manytomanyjpamaven.model.Ruolo;
import it.manytomanyjpamaven.model.Utente;

public class RuoloDAOImpl implements RuoloDAO {

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Ruolo> list() throws Exception {
		return entityManager.createQuery("from Ruolo",Ruolo.class).getResultList();
	}

	@Override
	public Ruolo get(Long id) throws Exception {
		return entityManager.find(Ruolo.class, id);
	}

	@Override
	public void update(Ruolo ruoloInstance) throws Exception {
		if (ruoloInstance == null) {
			throw new Exception("Problema valore in input");
		}
		ruoloInstance = entityManager.merge(ruoloInstance);
	}

	@Override
	public void insert(Ruolo ruoloInstance) throws Exception {
		if (ruoloInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(ruoloInstance);

	}

	@Override
	public void delete(Ruolo ruoloInstance) throws Exception {
		if (ruoloInstance == null) {
			throw new Exception("Problema valore in input");
		}
		List<Utente> utenti = new ArrayList<>();
		//ho necessità di verificare che non ci siano degli utenti associati al ruolo che voglio eliminare 
		//richiamo la query di findAllByRuolo per ottenere tutti gli utenti associati a quel ruolo 
		TypedQuery<Utente> query = entityManager.createQuery("select u FROM Utente u join u.ruoli r where r = :ruolo",Utente.class);
		query.setParameter("ruolo", ruoloInstance);
		utenti = query.getResultList();
		
		if(utenti.size()==0) {
			System.out.println("\n\nNON CI SONO UTENTI CON QUESTO RUOLO! Puoi procedere con l'eliminazione.\n\n");
			entityManager.remove(entityManager.merge(ruoloInstance)); //eseguo l'eliminazione dopo il merge di eventuali modifiche fatte sulla "nuvoletta"
			System.out.println("\n\nIl Ruolo " + ruoloInstance.toString() + " è stato correttamente eliminato!\n\n");	
		} else {
			System.out.println("\n\n !!ATTENZIONE!! Il ruolo che cerchi di eliminare è stato assegnato ad almeno un utente pertanto non puoi eliminarlo!\n\n");
		}			
	}


	@Override
	public Ruolo findByDescrizioneAndCodice(String descrizione, String codice) throws Exception {
		TypedQuery<Ruolo> query = entityManager
				.createQuery("select r from Ruolo r where r.descrizione=?1 and r.codice=?2", Ruolo.class)
				.setParameter(1, descrizione)
				.setParameter(2, codice);
		
		return query.getResultStream().findFirst().orElse(null);
	}

}
