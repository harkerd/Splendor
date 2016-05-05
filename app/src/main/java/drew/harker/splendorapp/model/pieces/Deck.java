package drew.harker.splendorapp.model.pieces;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import drew.harker.splendorapp.R;
import drew.harker.splendorapp.exceptions.CardDoesNotExistException;
import drew.harker.splendorapp.exceptions.InvalidActionException;

public class Deck
{
    public static final int TOP_OF_DECK = 0;
    public static final int CARD_VISIBLE_ONE = 1;
    public static final int CARD_VISIBLE_TWO = 2;
    public static final int CARD_VISIBLE_THREE = 3;
    public static final int CARD_VISIBLE_FOUR = 4;
    public static final int NUMBER_OF_CARDS_VISIBLE = 4;
    private static final Random r = new Random(System.currentTimeMillis());

    public static int getResId(int slotNumber) throws InvalidActionException
    {
        int id;
        switch (slotNumber)
        {
            case 0: id = R.id.slot_one; break;
            case 1: id = R.id.slot_two; break;
            case 2: id = R.id.slot_three; break;
            case 3: id = R.id.slot_four; break;
            default: throw new InvalidActionException();
        }
        return id;
    }

    private List<Card> cards;
    private Card[] cardsOnDisplay = new Card[NUMBER_OF_CARDS_VISIBLE];

    public Deck(List<Card> cards)
    {

        this.cards = cards;
        shuffle();
        int numberOfCardsVisible = NUMBER_OF_CARDS_VISIBLE;
        if(cards.size() < numberOfCardsVisible)
        {
            numberOfCardsVisible = cards.size();
        }

        for(int i = 0; i < numberOfCardsVisible; i++)
        {
            cardsOnDisplay[i] = cards.remove(TOP_OF_DECK);
        }
    }

    private void shuffle()
    {
        int timesToShuffle = 30;

        for(int numberOfShuffles = 0; numberOfShuffles <  timesToShuffle; numberOfShuffles++)
        {
            int size = cards.size();
            int half = size / 2;
            List<Card> topHalf = new LinkedList();
            List<Card> bottomHalf = new LinkedList();

            for(int i = 0; i < size; i++)
            {
                if(i < half)
                {
                    topHalf.add(cards.get(i));
                }
                else
                {
                    bottomHalf.add(cards.get(i));
                }
            }

            cards = new LinkedList<>();


            for(int i = 0; i < size; i++)
            {
                boolean top;
                if(topHalf.size() > 0 && bottomHalf.size() > 0)
                {
                    top = r.nextBoolean();
                }
                else if(topHalf.size() > 0)
                {
                    top = true;
                }
                else
                {
                    top = false;
                }


                if(top)
                {
                    int lastIndex = topHalf.size() - 1;
                    cards.add(topHalf.remove(lastIndex));
                }
                else
                {
                    int lastIndex = bottomHalf.size() - 1;
                    cards.add(bottomHalf.remove(lastIndex));
                }
            }
        }
    }

    public boolean isEmpty()
    {
        return cards.isEmpty();
    }

    public Card takeCard(int cardIndex) throws CardDoesNotExistException, InvalidActionException
    {
        if(cardIndex > NUMBER_OF_CARDS_VISIBLE)
        {
            throw new InvalidActionException();
        }
        else if(cardIndex == TOP_OF_DECK)
        {
            return cards.remove(TOP_OF_DECK);
        }
        else
        {
            Card cardToReturn = cardsOnDisplay[cardIndex - 1];
            if(cardToReturn == null)
            {
                throw new CardDoesNotExistException();
            }
            else
            {
                Card cardToDisplay = null;
                if(cards.size() > 0)
                {
                    cardToDisplay = cards.remove(TOP_OF_DECK);
                }
                cardsOnDisplay[cardIndex - 1] = cardToDisplay;
                return cardToReturn;
            }
        }
    }

    public Card getCardInfo(int cardIndex) throws CardDoesNotExistException, InvalidActionException
    {
        if(cardIndex > NUMBER_OF_CARDS_VISIBLE || cardIndex == TOP_OF_DECK)
        {
            throw new InvalidActionException();
        }
        else
        {
            Card cardToReturn = cardsOnDisplay[cardIndex - 1];
            if(cardToReturn == null)
            {
                throw new CardDoesNotExistException();
            }
            else
            {
                return cardToReturn;
            }
        }
    }

    public GemList getCardPrice(int cardIndex) throws InvalidActionException, CardDoesNotExistException
    {
        if(cardIndex > NUMBER_OF_CARDS_VISIBLE || cardIndex == TOP_OF_DECK)
        {
            throw new InvalidActionException();
        }
        else
        {
            Card card = cardsOnDisplay[cardIndex - 1];
            if(card == null)
            {
                throw new CardDoesNotExistException();
            }
            else
            {
                return card.getRequirement();
            }
        }
    }

    public boolean doesCardExist(int cardIndex) throws InvalidActionException
    {
        if(cardIndex > NUMBER_OF_CARDS_VISIBLE || cardIndex < 0)
        {
            throw new InvalidActionException();
        }
        else if(cardIndex == TOP_OF_DECK)
        {
            return cards.size() > 0;
        }
        else
        {
            return cardsOnDisplay[cardIndex - 1] != null;
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if(o == null)
        {
            return false;
        }
        else if(o.getClass() != this.getClass())
        {
            return false;
        }
        else if(o == this)
        {
            return true;
        }
        else
        {
            Deck that = (Deck) o;

            for(int i = 0; i < NUMBER_OF_CARDS_VISIBLE; i++)
            {
                Card card1 = this.cardsOnDisplay[i];
                Card card2 = that.cardsOnDisplay[i];

                if(card1 == null && card2 == null)
                {
                    continue;
                }
                else if(card1 != null && card2 != null)
                {
                    if(card1.equals(card2))
                    {
                        continue;
                    }
                    else
                    {
                        return false;
                    }
                }
                else //One is null and the other is not.
                {
                    return false;
                }
            }

            if(this.cards.size() != that.cards.size())
            {
                return false;
            }
            for(int i = 0; i < cards.size(); i++)
            {
                Card card1 = this.cards.get(i);
                Card card2 = that.cards.get(i);

                if(card1.equals(card2))
                {
                    continue;
                }
                else
                {
                    return false;
                }
            }

            return true;
        }
    }
}
