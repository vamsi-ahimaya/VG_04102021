package com.jtt.dao;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jtt.exception.CardException;
import com.jtt.model.Card;

/**
 * This is java-technical-test DAO layer, which is used to interact with
 * database.
 * 
 * @author Vamsi_Gandala
 * @date 04-October-2021
 */
@Repository
public class CardDaoServiceImpl implements CardDao {
	private static final Logger LOGGER = LogManager.getLogger(CardDaoServiceImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public Card addUpdateCard(Card card) throws CardException {
		LOGGER.debug("Entering :: addUpdateCard(Card card)");
		Session session = this.sessionFactory.getCurrentSession();
		if (card.getId() > 0) {
			session.update(card);
		} else {
			session.save(card);
		}

		LOGGER.debug("Exiting :: addUpdateCard(Card card)");
		return card;
	}

	@Override
	public boolean deleteCard(Long id) throws CardException {
		LOGGER.debug("Entering :: deleteCard(Card card)");
		Session session = this.sessionFactory.getCurrentSession();
		Card card = (Card) session.load(Card.class, new Long(id));
		if (null != card) {
			session.delete(card);
		}

		LOGGER.debug("Exiting :: deleteCard(Card card)");
		return true;
	}

	@Override
	public Card getCard(Long id) throws CardException {
		LOGGER.debug("Entering :: getCard(Long id)");
		Session session = this.sessionFactory.getCurrentSession();
		Card card = (Card) session.get(Card.class, id);

		LOGGER.debug("Exiting :: getCard(Long id)");
		return card;
	}

	@Override
	public List<Card> getCardList() throws CardException {
		LOGGER.debug("Entering :: getCardList()");
		Session session = this.sessionFactory.getCurrentSession();
		List<Card> cardList = session.createQuery("from Card").list();

		LOGGER.debug("Exiting :: getCardList()");
		return cardList;
	}
}
