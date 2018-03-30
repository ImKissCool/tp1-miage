package com.acme.mailreader.domain;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class MailComparatorTest {
	
	private MailComparator comparator;

	@Before
	public void setUp() throws Exception {
		this.comparator = new MailComparator();
	}

	@Test
	public final void egauxSiDeuxMailsNuls() {
		Mail mail1 = null;
		Mail mail2 = null;
		assertThat(comparator.compare(mail1, mail2), is(0));
	}
	
	@Test
	public final void egauxSiUnDesMailsNuls() {
		Mail mail1 = new Mail();
		Mail mail2 = null;
		assertThat(comparator.compare(mail1, mail2), is(0));
	}
	
	//Autres tests unitaires
	@Test
	public final void egauxSiMemeMail() {
		Mail mail1 = new Mail();
		assertThat(comparator.compare(mail1, mail1), is(0));
	}
	
	@Test
	public final void pasEgauxSiSujetsDifferents() {
		Mail mail1 = new Mail();
		Mail mail2 = new Mail();
		mail2.setSujet("Sujet2");
		mail1.setSujet("Sujet1");
		assertThat(comparator.compare(mail1, mail2), is(-1));
	}
	
	@Test
	public final void pasEgauxSiImportanceDifferentes() {
		Mail mail1 = new Mail();
		Mail mail2 = new Mail();
		mail2.setImportant(true);
		mail1.setImportant(false);
		assertThat(comparator.compare(mail1, mail2), is(1));
	}
		
	
}
