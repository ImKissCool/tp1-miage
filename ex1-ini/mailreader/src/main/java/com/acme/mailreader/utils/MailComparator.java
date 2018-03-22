package com.acme.mailreader.utils;

import java.util.Comparator;

import com.acme.mailreader.model.Mail;

/**
 * Comparateur de mails
 * 
 * Comme on désire afficher les mails les plus importants en premier, l'element
 * le plus grand retourne une valeur négative
 *
 */

public class MailComparator implements Comparator<Mail> {

	public static final int EGAUX = 0;
	public static final int PREMIER_PLUS_PETIT = 1;
	public static final int PREMIER_PLUS_GRAND = -1;

	public int compare(Mail mail, Mail autreMail) {
		if (unDesMailsNul(mail, autreMail)) {
			return EGAUX;
		}

		if (importanceDifferente(mail, autreMail)) {
			return lePlusImportantSelonImportance(mail, autreMail);
		}

		if (statutDifferent(mail, autreMail)) {
			return lePlusImportantSelonStatut(mail, autreMail);
		}

		if (sujetDifferent(mail, autreMail)) {
			return lePlusImportantSelonSujet(mail, autreMail);
		}

		return autreMail.getDate().compareTo(mail.getDate());
	}

	public boolean unDesMailsNul(Mail mail, Mail autreMail) {
		return mail == null || autreMail == null;
	}

	public int lePlusImportantSelonImportance(Mail mail, Mail autreMail) {
		if (mail.isImportant() && !autreMail.isImportant()) {
			return PREMIER_PLUS_GRAND;
		} else {
			return PREMIER_PLUS_PETIT;
		}
	}

	public int lePlusImportantSelonStatut(Mail mail, Mail autreMail) {
		int comp = mail.getStatut().ordinal() - autreMail.getStatut().ordinal();
		return comp > EGAUX ? PREMIER_PLUS_GRAND : PREMIER_PLUS_PETIT;
	}

	public int lePlusImportantSelonSujet(Mail mail, Mail autreMail) {
		return mail.getSujet().compareTo(autreMail.getSujet());
	}

	private boolean importanceDifferente(Mail mail, Mail autreMail) {
		return mail.isImportant() != autreMail.isImportant();
	}

	private boolean statutDifferent(Mail mail, Mail autreMail) {
		return mail.getStatut() != autreMail.getStatut();
	}

	private boolean sujetDifferent(Mail mail, Mail autreMail) {
		return mail.getSujet() != autreMail.getSujet();
	}
}
