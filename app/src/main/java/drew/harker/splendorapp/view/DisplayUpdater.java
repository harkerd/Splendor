package drew.harker.splendorapp.view;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import drew.harker.splendorapp.R;
import drew.harker.splendorapp.exceptions.CardDoesNotExistException;
import drew.harker.splendorapp.exceptions.InvalidActionException;
import drew.harker.splendorapp.exceptions.InvalidTypeException;
import drew.harker.splendorapp.model.Game;
import drew.harker.splendorapp.model.Player;
import drew.harker.splendorapp.model.pieces.Card;
import drew.harker.splendorapp.model.pieces.Deck;
import drew.harker.splendorapp.model.pieces.Gem;
import drew.harker.splendorapp.model.pieces.GemList;
import drew.harker.splendorapp.model.pieces.Location;
import drew.harker.splendorapp.model.pieces.Noble;
import drew.harker.splendorapp.model.pieces.Token;
import drew.harker.splendorapp.model.pieces.TokenList;

public class DisplayUpdater
{
    protected static void updateNobleViews(Activity activity, List<Noble> nobles)
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
                    updateValueList(activity.findViewById(resId), n.getVictoryPoints(), n.getRequirement());
                }
                else
                {
                    activity.findViewById(resId).setVisibility(View.GONE);
                }
            }
            catch (InvalidTypeException e)
            {
                e.printStackTrace();
            }
        }
    }

    protected static void updateDeckView(Game game, View deck, Location deckName)
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

                    updateCard(cardView, card);
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

    protected static void updateCard(View cardView, Card card) throws InvalidTypeException
    {
        SourceView sourceView = (SourceView) cardView.findViewById(R.id.source);
        Gem source = card.getSource();
        sourceView.updateColorRes(source);

        updateValueList(cardView, card.getVictoryPoints(), card.getRequirement());
    }

    protected static void updateValueList(View cardView, int points, GemList cost) throws InvalidTypeException
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

    protected static void updateBankView(View bankView, TokenList bank)
    {
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

    protected static void updatePlayerBankView(Activity activity, View playerView, Player player, boolean isCurrentPlayer)
    {
        try
        {
            TextView playerName = (TextView) playerView.findViewById(R.id.player_name);
            playerName.setText(player.getName());

            GemList cardTotals = player.getCardTotals();

            ValueTokenView tokenView = (ValueTokenView) playerView.findViewById(R.id.points);
            int tokenValue = player.getVictoryPoints();
            tokenView.setText(String.valueOf(tokenValue));

            tokenView = (ValueTokenView) playerView.findViewById(R.id.diamondCardCount);
            tokenValue = cardTotals.get(Gem.DIAMOND);
            tokenView.setText(String.valueOf(tokenValue));

            tokenView = (ValueTokenView) playerView.findViewById(R.id.sapphireCardCount);
            tokenValue = cardTotals.get(Gem.SAPPHIRE);
            tokenView.setText(String.valueOf(tokenValue));

            tokenView = (ValueTokenView) playerView.findViewById(R.id.emeraldCardCount);
            tokenValue = cardTotals.get(Gem.EMERALD);
            tokenView.setText(String.valueOf(tokenValue));

            tokenView = (ValueTokenView) playerView.findViewById(R.id.rubyCardCount);
            tokenValue = cardTotals.get(Gem.RUBY);
            tokenView.setText(String.valueOf(tokenValue));

            tokenView = (ValueTokenView) playerView.findViewById(R.id.onyxCardCount);
            tokenValue = cardTotals.get(Gem.ONYX);
            tokenView.setText(String.valueOf(tokenValue));

            updateBankView(playerView, player.getTokens());

            View button = playerView.findViewById(R.id.viewReserveButton);
            setReserveButtonListener(activity, player, button, isCurrentPlayer);

        } catch (InvalidTypeException e)
        {
            e.printStackTrace();
        }
    }

    protected static void setReserveButtonListener(final Activity activity, final Player player, View reserveButton, final boolean isCurrentPlayer)
    {
        reserveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                DialogFragment reserveView = new ReserveView();
                Bundle args = new Bundle();

                args.putParcelableArray(ReserveView.CARD_LIST_TAG, player.getReserveCardArray());
                args.putString(ReserveView.PLAYER_NAME_TAG, player.getName());
                args.putBoolean(ReserveView.IS_CURRENT_PLAYER_TAG, isCurrentPlayer);
                reserveView.setArguments(args);

                reserveView.show(activity.getFragmentManager(), player.getName() + ":reserve");
            }
        });
    }
}
