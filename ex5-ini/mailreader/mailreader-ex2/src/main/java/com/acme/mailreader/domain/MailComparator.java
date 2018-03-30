package com.acme.mailreader.domain;

import java.util.Comparator;

/**
 * Comparateur de mails
 * 
 * Comme on désire afficher les mails les plus importants en premier, l'element le plus grand retourne une valeur négative
 *
 */
public class MailComparator implements Comparator<Mail> {

	public int compare(Mail obj1, Mail obj2) {
		if (obj1 == null || obj2 == null) {
			return 0;
		}
		if (obj1.isImportant() != obj2.isImportant()) {
			if (obj1.isImportant() && !obj2.isImportant()) {
				return -1;
			} else {
				return 1;
			}
		}
		if (obj1.getStatut() != obj2.getStatut()) {
			int comp = obj1.getStatut().ordinal()
					- obj2.getStatut().ordinal();
			return comp > 0 ? -1 : 1;
		}
		if (obj1.getSujet() != obj2.getSujet()) {
			return obj2.getSujet().compareTo(obj1.getSujet());
		}
		return obj2.getDate().compareTo(obj1.getDate());
	}
	

}
