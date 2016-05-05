package drew.harker.splendorapp.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
import drew.harker.splendorapp.model.pieces.Location;

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
        cardBack.setText("3");

        deck = findViewById(R.id.deck_two);
        cardBack = (TextView) deck.findViewById(R.id.card_back_text);
        cardBack.setText("2");

        deck = findViewById(R.id.deck_one);
        cardBack = (TextView) deck.findViewById(R.id.card_back_text);
        cardBack.setText("1");

        //updateDisplay();
    }

    public void updateDisplay()
    {
        //updateNoblesView

        updateDeckView(findViewById(R.id.deck_three), Location.DECK_THREE);
        updateDeckView(findViewById(R.id.deck_two), Location.DECK_TWO);
        updateDeckView(findViewById(R.id.deck_one), Location.DECK_ONE);

        //updateBankView
        //updatePlayerBankView
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
                    Card card = game.getCardInfo(deckName, i);

                    TextView pointsView = (TextView) cardView.findViewById(R.id.points);
                    int points = card.getVictoryPoints();
                    if(points == 0)
                    {
                        pointsView.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        pointsView.setText(points);
                    }

                    SourceView sourceView = (SourceView) cardView.findViewById(R.id.source);
                    Gem source = card.getSource();
                    sourceView.updateColorRes(source);

                    //updateCost
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
}
