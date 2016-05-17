package drew.harker.splendorapp.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import drew.harker.splendorapp.R;
import drew.harker.splendorapp.model.Game;
import drew.harker.splendorapp.model.Player;
import drew.harker.splendorapp.model.StaticCurrentGameAccess;

public class ViewPlayers extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View playersView = inflater.inflate(R.layout.view_players, null);
        builder.setView(playersView);

        Game currentGame = StaticCurrentGameAccess.getCurrentGame();
        for(int i = 0; i < Game.MAX_NUMBER_OF_PLAYERS; i++)
        {
            int id = -1;
            switch (i)
            {
                case 0:
                    id = R.id.player0;
                    break;
                case 1:
                    id = R.id.player1;
                    break;
                case 2:
                    id = R.id.player2;
                    break;
                case 3:
                    id = R.id.player3;
                    break;
                default:
                    System.err.println("Illegal number of players");
            }

            if (i >= currentGame.getNumberOfPlayers())
            {
                playersView.findViewById(id).setVisibility(View.GONE);
            }
            else
            {
                Player p = currentGame.getPlayer(i);
                DisplayUpdater.updatePlayerBankView(getActivity(), playersView.findViewById(id), p, p.equals(currentGame.getCurrentPlayer()));
            }
        }

        return builder.create();
    }
}
