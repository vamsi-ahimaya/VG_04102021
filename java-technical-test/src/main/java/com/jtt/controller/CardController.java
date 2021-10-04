package com.jtt.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jtt.model.Card;
import com.jtt.service.CardService;

/**
 * This is controller class which is used to interact with service layer and
 * expose rest API for payment.
 * 
 * @author Vamsi_Gandala
 * @date 04-October-2021
 */
@RestController
public class CardController {
	private static final Logger LOGGER = LogManager.getLogger(CardController.class);

	@Autowired
	CardService cardService;

	@RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=application/json")
	public String goToHomePage() {
		return "redirect:/getCardList";
	}

	@RequestMapping(value = "/getCardList", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Card> getCardList(Model model) {
		LOGGER.debug("Entering :: getCardList()");
		List<Card> cardList = cardService.getCardList();
		model.addAttribute("Card", new Card());
		model.addAttribute("cardList", cardList);

		LOGGER.debug("Exiting :: getCardList()");
		return cardList;
	}

	@RequestMapping(value = "/getCardInfo/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Card getCardInfo(@PathVariable Long id) {
		LOGGER.debug("Entering :: getCardInfo(Long)");
		Card card = cardService.getCard(id);

		LOGGER.debug("Exiting :: getCardInfo(Long)");
		return card;
	}

	@RequestMapping(value = "/addCard", method = RequestMethod.POST, headers = "Accept=application/json")
	public Card addCard(@RequestBody Card card) {
		LOGGER.debug("Entering :: addCard(Card)");
		Card cardObj = cardService.addUpdateCard(card);

		LOGGER.debug("Exiting :: addCard(Card)");
		return cardObj;
	}

	@RequestMapping(value = "/updateCard", method = RequestMethod.PUT, headers = "Accept=application/json")
	public Card updateCard(@RequestBody Card card) {
		LOGGER.debug("Entering :: updateCard(Card)");
		Card cardObj = cardService.addUpdateCard(card);

		LOGGER.debug("Exiting :: updateCard(Card)");
		return cardObj;
	}

	@RequestMapping(value = "/deleteCard/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public boolean deleteCard(@PathVariable("id") Long id) {
		LOGGER.debug("Entering :: deleteCard(Long)");
		boolean flag = cardService.deleteCard(id);

		LOGGER.debug("Exiting :: deleteCard(Long)");
		return flag;
	}
}
