package drew.harker.splendorapp.view;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import drew.harker.splendorapp.R;
import drew.harker.splendorapp.controller.GameController;
import drew.harker.splendorapp.model.Game;
import drew.harker.splendorapp.model.StaticCurrentGameAccess;
import drew.harker.splendorapp.model.pieces.Location;

public class GameActivity extends Activity
{
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);

        game = StaticCurrentGameAccess.getCurrentGame();

        View deck = findViewById(R.id.deck_three);
        TextView cardBack = (TextView) deck.findViewById(R.id.card_back_text);
        cardBack.setText("THREE");

        deck = findViewById(R.id.deck_two);
        cardBack = (TextView) deck.findViewById(R.id.card_back_text);
        cardBack.setText("TWO");

        deck = findViewById(R.id.deck_one);
        cardBack = (TextView) deck.findViewById(R.id.card_back_text);
        cardBack.setText("ONE");

        View button = findViewById(R.id.viewPlayersButton);
        button.setVisibility(View.VISIBLE);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DialogFragment reserveView = new ViewPlayers();
                reserveView.show(getFragmentManager(), "ViewPlayers");
            }
        });

        updateGameDisplay();
        //TODO: add a small dialog list (reserve, buy) to each of the reservable cards
    }

    public void updateGameDisplay()
    {
        DisplayUpdater.updateNobleViews(this, game.getNobles());

        DisplayUpdater.updateDeckView(game, findViewById(R.id.deck_three), Location.DECK_THREE);
        DisplayUpdater.updateDeckView(game, findViewById(R.id.deck_two), Location.DECK_TWO);
        DisplayUpdater.updateDeckView(game, findViewById(R.id.deck_one), Location.DECK_ONE);

        DisplayUpdater.updateBankView(findViewById(R.id.bank), game.getBank());
        DisplayUpdater.updatePlayerBankView(this, findViewById(R.id.player_bank), game.getCurrentPlayer(), true);
    }
}
