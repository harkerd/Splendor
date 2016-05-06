package drew.harker.splendorapp.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import drew.harker.splendorapp.R;
import drew.harker.splendorapp.controller.GameController;
import drew.harker.splendorapp.exceptions.CardDoesNotExistException;
import drew.harker.splendorapp.exceptions.InvalidActionException;
import drew.harker.splendorapp.exceptions.InvalidTypeException;
import drew.harker.splendorapp.model.Game;
import drew.harker.splendorapp.model.StaticCurrentGameAccess;
import drew.harker.splendorapp.model.pieces.Card;
import drew.harker.splendorapp.model.pieces.Deck;
import drew.harker.splendorapp.model.pieces.Gem;
import drew.harker.splendorapp.model.pieces.GemList;
import drew.harker.splendorapp.model.pieces.Location;
import drew.harker.splendorapp.model.pieces.Noble;
import drew.harker.splendorapp.model.pieces.Token;
import drew.harker.splendorapp.model.pieces.TokenList;

public class GameView extends Activity
{
    private Game game;
    private GameController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);

        game = StaticCurrentGameAccess.getCurrentGame();
        controller = new GameController(game, this);

        View deck = findViewById(R.id.deck_three);
        TextView cardBack = (TextView) deck.findViewById(R.id.card_back_text);
        cardBack.setText("THREE");

        deck = findViewById(R.id.deck_two);
        cardBack = (TextView) deck.findViewById(R.id.card_back_text);
        cardBack.setText("TWO");

        deck = findViewById(R.id.deck_one);
        cardBack = (TextView) deck.findViewById(R.id.card_back_text);
        cardBack.setText("ONE");

        updateDisplay();
    }

    public void updateDisplay()
    {
        updateNobleViews(game.getNobles());

        updateDeckView(findViewById(R.id.deck_three), Location.DECK_THREE);
        updateDeckView(findViewById(R.id.deck_two), Location.DECK_TWO);
        updateDeckView(findViewById(R.id.deck_one), Location.DECK_ONE);

        updateBankView(game.getBank());
        //updatePlayerBankView
    }

    private void updateNobleViews(List<Noble> nobles)
    {
        int maxNumberOfNobles = 5;

        for(int index = 0; index < maxNumberOfNobles; index++)
        {
            try
            {
                int resId;
                switch(index)
                {
                    case 4: resId = R.id.noble_five; break;
                    case 3: resId = R.id.noble_four; break;
                    case 2: resId = R.id.noble_three; break;
                    case 1: resId = R.id.noble_two; break;
                    case 0: resId = R.id.noble_one; break;
                    default: throw new InvalidTypeException();
                }

                if(nobles.size() > index) //has a noble at the index
                {
                    Noble n = nobles.get(index);
                    updateCard(findViewById(resId), n.getVictoryPoints(), n.getRequirement());
                }
                else
                {
                    findViewById(resId).setVisibility(View.GONE);
                }
            }
            catch (InvalidTypeException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void updateDeckView(View deck, Location deckName)
    {
        for(int i = 0; i < Deck.NUMBER_OF_CARDS_VISIBLE; i++)
        {
            try
            {
                int id = Deck.getResId(i);

                View cardView = deck.findViewById(id);
                try
                {
                    Card card = game.getCardInfo(deckName, i + 1);

                    SourceView sourceView = (SourceView) cardView.findViewById(R.id.source);
                    Gem source = card.getSource();
                    sourceView.updateColorRes(source);

                    updateCard(cardView, card.getVictoryPoints(), card.getRequirement());
                }
                catch (CardDoesNotExistException e)
                {
                    cardView.setVisibility(View.GONE);
                }
            }
            catch (InvalidActionException e)
            {
                e.printStackTrace();
            }
            catch (InvalidTypeException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void updateCard(View cardView, int points, GemList cost) throws InvalidTypeException
    {
        TextView pointsView = (TextView) cardView.findViewById(R.id.points);
        if(points == 0)
        {
            pointsView.setVisibility(View.INVISIBLE);
        }
        else
        {
            pointsView.setText(String.valueOf(points));
        }

        ValueTokenView gemView = (ValueTokenView) cardView.findViewById(R.id.diamondCost);
        int gemValue = cost.get(Gem.DIAMOND);
        gemView.setText(String.valueOf(gemValue));

        gemView = (ValueTokenView) cardView.findViewById(R.id.sapphireCost);
        gemValue = cost.get(Gem.SAPPHIRE);
        gemView.setText(String.valueOf(gemValue));

        gemView = (ValueTokenView) cardView.findViewById(R.id.emeraldCost);
        gemValue = cost.get(Gem.EMERALD);
        gemView.setText(String.valueOf(gemValue));

        gemView = (ValueTokenView) cardView.findViewById(R.id.rubyCost);
        gemValue = cost.get(Gem.RUBY);
        gemView.setText(String.valueOf(gemValue));

        gemView = (ValueTokenView) cardView.findViewById(R.id.onyxCost);
        gemValue = cost.get(Gem.ONYX);
        gemView.setText(String.valueOf(gemValue));
    }

    private void updateBankView(TokenList bank)
    {
        View bankView = findViewById(R.id.bank);
        try
        {

            ValueTokenView tokenView = (ValueTokenView) bankView.findViewById(R.id.diamondBank);
            int tokenValue = 0;

            tokenValue = bank.get(Token.DIAMOND);
            tokenView.setText(String.valueOf(tokenValue));

            tokenView = (ValueTokenView) bankView.findViewById(R.id.sapphireBank);
            tokenValue = bank.get(Token.SAPPHIRE);
            tokenView.setText(String.valueOf(tokenValue));

            tokenView = (ValueTokenView) bankView.findViewById(R.id.emeraldBank);
            tokenValue = bank.get(Token.EMERALD);
            tokenView.setText(String.valueOf(tokenValue));

            tokenView = (ValueTokenView) bankView.findViewById(R.id.rubyBank);
            tokenValue = bank.get(Token.RUBY);
            tokenView.setText(String.valueOf(tokenValue));

            tokenView = (ValueTokenView) bankView.findViewById(R.id.onyxBank);
            tokenValue = bank.get(Token.ONYX);
            tokenView.setText(String.valueOf(tokenValue));

            tokenView = (ValueTokenView) bankView.findViewById(R.id.goldBank);
            tokenValue = bank.get(Token.GOLD);
            tokenView.setText(String.valueOf(tokenValue));

        } catch (InvalidTypeException e)
        {
            e.printStackTrace();
        }
    }
}
