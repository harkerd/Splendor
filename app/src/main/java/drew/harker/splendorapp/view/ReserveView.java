package drew.harker.splendorapp.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import drew.harker.splendorapp.R;
import drew.harker.splendorapp.exceptions.InvalidTypeException;
import drew.harker.splendorapp.model.StaticCurrentGameAccess;
import drew.harker.splendorapp.model.pieces.Card;

public class ReserveView extends DialogFragment
{
    public static final String PLAYER_NAME_TAG = "player_name";
    public static final String CARD_LIST_TAG = "card_list";
    public static final String IS_CURRENT_PLAYER_TAG = "is_current_player";

    private ReserveViewListener listener;
    private Button positiveButton;
    private Card selectedCard;

    private View slot_one;
    private View slot_two;
    private View slot_three;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();

        Bundle args = getArguments();
        String playerName = args.getString(PLAYER_NAME_TAG);
        Card[] cards = (Card[]) args.getParcelableArray(CARD_LIST_TAG);
        boolean isCurrentPlayer = args.getBoolean(IS_CURRENT_PLAYER_TAG);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(playerName + "'s Reserved Cards");
        View reserve = inflater.inflate(R.layout.reserve, null);
        builder.setView(reserve);

        slot_one = reserve.findViewById(R.id.slot_one);
        buildCardView(slot_one, cards[0], isCurrentPlayer);
        slot_two = reserve.findViewById(R.id.slot_two);
        buildCardView(slot_two, cards[1], isCurrentPlayer);
        slot_three = reserve.findViewById(R.id.slot_three);
        buildCardView(slot_three, cards[2], isCurrentPlayer);

        if(!isCurrentPlayer)
        {
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    ReserveView.this.getDialog().cancel();
                }
            });
        }
        else
        {
            builder.setMessage("Select the card you would like to purchase");
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    listener.cardSelected(selectedCard);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    ReserveView.this.getDialog().cancel();
                }
            });
        }

        AlertDialog dialog = builder.create();
        dialog.show();

        if(isCurrentPlayer) {
            positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            positiveButton.setEnabled(false);
        }

        return dialog;
    }

    private void buildCardView(final View cardView, final Card card, boolean isCurrentPlayer)
    {
        if(card != null)
        {
            try
            {
                DisplayUpdater.updateCard(cardView, card);
                if(isCurrentPlayer)
                {
                    cardView.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            float fade = .3f;
                            slot_one.setAlpha(fade);
                            slot_two.setAlpha(fade);
                            slot_three.setAlpha(fade);

                            cardView.setAlpha(1);
                            positiveButton.setEnabled(true);
                            selectedCard = card;
                        }
                    });
                }
            }
            catch (InvalidTypeException e)
            {
                cardView.setVisibility(View.GONE);
            }
        }
        else
        {
            cardView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = StaticCurrentGameAccess.getController();
    }

    public interface ReserveViewListener {
        void cardSelected(Card card); //Can only be selected by the current player
    }
}
