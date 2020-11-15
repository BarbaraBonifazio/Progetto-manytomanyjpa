package it.manytomanyjpamaven.test;
import java.util.Date;

import it.manytomanyjpamaven.dao.EntityManagerUtil;
import it.manytomanyjpamaven.model.Ruolo;
import it.manytomanyjpamaven.model.Utente;
import it.manytomanyjpamaven.service.MyServiceFactory;
import it.manytomanyjpamaven.service.RuoloService;
import it.manytomanyjpamaven.service.UtenteService;

public class ManyToManyTest {

	public static void main(String[] args) {
		UtenteService utenteServiceInstance = MyServiceFactory.getUtenteServiceInstance();
		RuoloService ruoloServiceInstance = MyServiceFactory.getRuoloServiceInstance();

		// ora passo alle operazioni CRUD
		try {

			// inizializzo i ruoli sul db
//			initRuoli(ruoloServiceInstance);
//
//			System.out.println("Elenca utenti: ");
//			for (Utente utenteItem : utenteServiceInstance.listAll()) {
//				System.out.println(utenteItem);
//			}

			Utente utenteNuovo = new Utente("pippo.rossi", "xxx", "pippo", "rossi", new Date());
			Utente utenteNuovo2 = new Utente("carla.verdi", "xxx", "carla", "verdi", new Date());
			Utente utenteNuovo3 = new Utente("ginevra.scarti", "xxx", "ginevra", "scarti", new Date());
			Utente utenteNuovo4 = new Utente("marco.rastelli", "xxx", "marco", "rastelli", new Date());


//			utenteServiceInstance.inserisciNuovo(utenteNuovo);
//			utenteServiceInstance.inserisciNuovo(utenteNuovo2);
//			utenteServiceInstance.inserisciNuovo(utenteNuovo3);
//			utenteServiceInstance.inserisciNuovo(utenteNuovo4);

//
			Ruolo ruoloDaDb = ruoloServiceInstance.caricaSingoloElemento(1L);
			Ruolo ruoloDaDb2 = ruoloServiceInstance.caricaSingoloElemento(2L);
			Ruolo ruoloDaDb3 = ruoloServiceInstance.caricaSingoloElemento(10L);
//
//			Utente utenteDaDb = utenteServiceInstance.listAll().stream().findFirst().orElse(null);
//			if (utenteDaDb != null) {
//				utenteServiceInstance.aggiungiRuolo(utenteDaDb, ruoloDaDb);
//			}
			Utente utenteDaDb2 = utenteServiceInstance.caricaSingoloElemento(6L);
			Utente utenteDaDb3 = utenteServiceInstance.caricaSingoloElemento(8L);
//			if (utenteDaDb2 != null) {
//				utenteServiceInstance.aggiungiRuolo(utenteDaDb2, ruoloDaDb2);
//			}
			
			
//			utenteServiceInstance.rimuoviRuolo(utenteDaDb2, ruoloDaDb2);
//			utenteServiceInstance.rimuovi(utenteDaDb2);
//			utenteServiceInstance.rimuovi(utenteDaDb3);
			
			
//			
//			Utente utenteDaDb3 = utenteServiceInstance.caricaSingoloElemento(3L);
//			if (utenteDaDb2 != null) {
//				utenteServiceInstance.aggiungiRuolo(utenteDaDb3, ruoloDaDb3);
//			}

			 //proviamo a passarlo nello stato ATTIVO
//			Utente utenteDaDb2 = utenteServiceInstance.listAll().stream().findFirst().orElse(null);
//			if (utenteDaDb2 != null) {
//				System.out.println(
//						"stato attuale dell'utente :" + utenteDaDb2.getUsername() + " " + utenteDaDb2.getStato());
//				utenteDaDb2.setStato(StatoUtente.ATTIVO);
//				utenteServiceInstance.aggiorna(utenteDaDb2);
//				System.out.println(
//						"stato nuovo dell'utente :" + utenteDaDb2.getUsername() + " " + utenteDaDb2.getStato());
//			}
				
				 //proviamo a passarlo nello stato ATTIVO richiamando l'utente da DB tramite USERNAME (anzichè ID) per utilizzare soltanto GLI OGGETTI
//					Utente ut = utenteServiceInstance.findByUsername(utenteNuovo2);
//					System.out.println(ut.toString());
//				
//					if (ut != null) {
//						System.out.println(
//								"stato attuale dell'utente :" + ut.getUsername() + " " + ut.getStato());
//						ut.setStato(StatoUtente.ATTIVO);
//						utenteServiceInstance.aggiorna(ut);
//						System.out.println(
//								"stato nuovo dell'utente :" + ut.getUsername() + " " + ut.getStato());
//					}

			
			//rimozione ruolo 
			ruoloServiceInstance.rimuovi(ruoloDaDb2);
//			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}

	}
		
	//questo metodo verifica che esista un ruolo "administrator" 
//	private static void initRuoli(RuoloService ruoloServiceInstance) throws Exception {
//		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN") == null) {
//			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", "ROLE_ADMIN"));
//		}
//
//		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER") == null) {
//			ruoloServiceInstance.inserisciNuovo(new Ruolo("Classic User", "ROLE_CLASSIC_USER"));
//		}
		
//		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Operator", "ROLE_OPERATOR") == null) {
//			ruoloServiceInstance.inserisciNuovo(new Ruolo("Operator", "ROLE_OPERATOR"));
//		}
//	}

}
