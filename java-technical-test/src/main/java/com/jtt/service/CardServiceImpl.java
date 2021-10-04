package com.jtt.service;

import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtt.dao.CardDao;
import com.jtt.exception.CardException;
import com.jtt.model.Card;

/**
 * This is Common Service layer, which is used to interact with Card DAO layer
 * and perform some business logic as well.
 * 
 * @author Vamsi_Gandala
 * @date 04-October-2021
 */
@Service("cardService")
public class CardServiceImpl implements CardService {
	private static final Logger LOGGER = LogManager.getLogger(CardServiceImpl.class);

	@Autowired
	private CardDao cardDao;

	@Override
	@Transactional
	public Card addUpdateCard(Card card) throws CardException {
		LOGGER.debug("Entering :: addUpdateCard(Card card)");
		Card updatedCard = cardDao.addUpdateCard(card);

		LOGGER.debug("Exiting :: addUpdateCard(Card card)");
		return updatedCard;
	}

	@Override
	@Transactional
	public boolean deleteCard(Long id) throws CardException {
		LOGGER.debug("Entering :: deleteCard(Card card)");
		boolean flag = cardDao.deleteCard(id);

		LOGGER.debug("Exiting :: deleteCard(Card card)");
		return flag;
	}

	@Override
	@Transactional
	public Card getCard(Long id) throws CardException {
		LOGGER.debug("Entering :: getCard(Long id)");
		Card card = cardDao.getCard(id);

		LOGGER.debug("Exiting :: getCard(Long id)");
		return card;
	}

	@Override
	@Transactional
	public List<Card> getCardList() throws CardException {
		LOGGER.debug("Entering :: getCardList()");
		List<Card> cardList = cardDao.getCardList();
		Comparator<Card> comparator = (c1, c2) -> c1.getExpiryDate().compareTo(c2.getExpiryDate());
		cardList.sort(comparator.reversed());

		for (Card card : cardList) {
			String strArray[] = card.getCardNumber().split(" ");
			card.setStrCardNumber(strArray[0] + "-XXXX-XXXX-XXXX");
		}

		LOGGER.debug("Exiting :: getCardList()");
		return cardList;
	}
}
