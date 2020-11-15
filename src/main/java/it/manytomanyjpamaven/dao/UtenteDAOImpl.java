package it.manytomanyjpamaven.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.manytomanyjpamaven.model.Ruolo;
import it.manytomanyjpamaven.model.Utente;

public class UtenteDAOImpl implements UtenteDAO {

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Utente> list() throws Exception {
		// dopo la from bisogna specificare il nome dell'oggetto (lettera maiuscola) e
		// non la tabella
		return entityManager.createQuery("from Utente", Utente.class).getResultList();
	}

	@Override
	public Utente get(Long id) throws Exception {
		return entityManager.find(Utente.class, id);
	}

	@Override
	public void update(Utente utenteInstance) throws Exception {
		if (utenteInstance == null) {
			throw new Exception("Problema valore in input");
		}
		utenteInstance = entityManager.merge(utenteInstance);
	}

	@Override
	public void insert(Utente utenteInstance) throws Exception {
		if (utenteInstance == null) {
			throw new Exception("Problema valore in input");
		}
		
		if(this.list().size()>0) { //verifico che ci siano utenti già presenti. Se ci sono, effettuo il controllo, altrimenti salto all'else
			boolean usernameIsPresente = false;
			for(Utente utente:this.list()) {  //ciclo la lista di utenti attualmente presenti a DB, richiamando il metodo .list() già implementato
				 //variabile booleana di appoggio per il controllo
			
				if(utente.equals(utenteInstance)){ //richiamo il mio equals sovrascritto nel model che confronta l'attributo "username"
					usernameIsPresente = true; //richiamo la mia booleana di appoggio per decretare che quel username è già presente a DB
				}
			}
			if (usernameIsPresente) { //se è già presente rimando indietro la richiesta di inserimento e stampo in console il motivo
				System.out.println("\n\n!!ATTENZIONE!! - Lo username " +utenteInstance.getUsername()+ " è già presente!\n\n");
				return;
				
			} else { //altrimenti eseguo quello che devo fare e stampo in console il risultato
				entityManager.persist(utenteInstance);
				System.out.println("\n\nHai aggiunto correttamente l'username [" +utenteInstance.getUsername()+ " ]");
			}
			
		} else { //altrimenti eseguo quello che devo fare e stampo in console il risultato
			entityManager.persist(utenteInstance);
			System.out.println("\n\nHai aggiunto correttamente la Categoria [" +utenteInstance.getUsername()+ " ]");
		 }
	}

	@Override
	public void delete(Utente utenteInstance) throws Exception {
		if (utenteInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(utenteInstance));
	}

	// questo metodo ci torna utile per capire se possiamo rimuovere un ruolo non
	// essendo collegato ad un utente
	public List<Utente> findAllByRuolo(Ruolo ruoloInput) throws Exception{
		TypedQuery<Utente> query = entityManager.createQuery("select u FROM Utente u join u.ruoli r where r = :ruolo",Utente.class);
		query.setParameter("ruolo", ruoloInput);
		return query.getResultList();
	}

	@Override
	public Utente findByUsername(Utente utente) throws Exception {
		TypedQuery<Utente> query = entityManager.createQuery("FROM Utente u where u.username = ?1", Utente.class);
		query.setParameter(1, utente.getUsername());
		return query.getSingleResult();
	}

}
