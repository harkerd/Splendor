package drew.harker.splendorapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import drew.harker.splendorapp.R;
import drew.harker.splendorapp.model.Game;
import drew.harker.splendorapp.model.StaticCurrentGameAccess;
import drew.harker.splendorapp.exceptions.InvalidActionException;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    //To test the game view with out having to build the settings first
                    Intent intent = new Intent(MainActivity.this, GameView.class);
                    StaticCurrentGameAccess.setCurrentGame(Game.newGame(3));
                    startActivity(intent);
                } catch (InvalidActionException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
