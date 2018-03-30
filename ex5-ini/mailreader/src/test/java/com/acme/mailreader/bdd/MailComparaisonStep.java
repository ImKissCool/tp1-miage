package com.acme.mailreader.bdd;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.acme.mailreader.domain.Mail;
import com.acme.mailreader.domain.Mail.Statut;
import com.acme.mailreader.domain.MailComparator;
import com.acme.mailreader.utils.DateIncorrecteException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Les steps (actions) du test
 * 
 * <p>
 * A noter qu'une nouvelle instance de cette classe est créée à chaque scenario
 * </p>
 *
 */

public class MailComparaisonStep {

	private Mail mail1;
	private Mail mail2;
	private String resultatComparaison;
	Comparator<Mail> comparator = new MailComparator();
	private int resultatCompare;
	private static final Map<Integer, String> resuAsString = new HashMap<Integer, String>();
	static {
		resuAsString.put(MailComparator.PREMIER_PLUS_PETIT, "MAIL1_APRES");
		resuAsString.put(MailComparator.EGAUX, "EGAUX");
		resuAsString.put(MailComparator.PREMIER_PLUS_GRAND, "MAIL1_AVANT");
	}

	@Given("^un premier mail avec l'importance \"([^\"]*)\", le statut \"([^\"]*)\", le sujet \"([^\"]*)\" et la date \"([^\"]*)\"$")
	public void un_premier_mail(boolean importance, Statut statut, String sujet, String date)
			throws DateIncorrecteException {
		// prbl avec date : voir
		// https://stackoverflow.com/questions/37672012/how-to-create-java-time-instant-from-pattern
		mail1 = new Mail();
		
		Instant instant = Instant.parse(date);
		mail1.setImportant(importance);
		mail1.setStatut(statut);
		mail1.setDate(instant);
		mail1.setSujet(sujet);
		// DONE
	}

	@Given("^un second mail avec l'importance \"([^\"]*)\", le statut \"([^\"]*)\", le sujet \"([^\"]*)\" et la date \"([^\"]*)\"$")
	public void un_second_mail(boolean importance, Statut statut, String sujet, String date)
			throws DateIncorrecteException {
		// DONE
		Instant instant = Instant.parse(date);
		mail2 = new Mail();

		mail2.setDate(instant);
		mail2.setImportant(importance);
		mail2.setStatut(statut);
		mail2.setSujet(sujet);

	}

	@When("^je trie$")
	public void je_trie() throws Throwable {
		// TODO
		System.out.println("coucou");
		resultatCompare = comparator.compare(mail1, mail2);
		resultatComparaison = resuAsString.get(resultatCompare);
	}

	@Then("^le tri doit retourner \"([^\"]*)\"$")
	public void le_test_d_egalité(String resu) throws Throwable {
		// DONE
		assertThat("\n Resultat comparaison : " + resultatComparaison + " \n resultat : " + resu, resultatComparaison,
				is(resu));
	}

}
