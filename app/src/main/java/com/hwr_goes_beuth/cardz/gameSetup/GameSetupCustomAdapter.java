package com.hwr_goes_beuth.cardz.gameSetup;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.hwr_goes_beuth.cardz.R;
import com.hwr_goes_beuth.cardz.game.opponents.Opponent;

import java.util.ArrayList;

/**
 * Created by User on 12.01.2017.
 */

public class GameSetupCustomAdapter extends BaseAdapter {

    Context context;
    GameSetupPresenter _presenter;
    ArrayList<Opponent> opponentId;


    private static LayoutInflater inflater=null;
    public GameSetupCustomAdapter(GameSetupActivity gameSetupActivity, GameSetupPresenter presenter, ArrayList<Opponent> opponentButtons) {
        context=gameSetupActivity;
        _presenter = presenter;
        opponentId=opponentButtons;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return opponentId.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        Button btn;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.view_simple_opponent, null);

        holder.btn=(Button) rowView.findViewById(R.id.opponent_button);
        holder.btn.setTag(Integer.valueOf(position));
        holder.btn.setText(opponentId.get(position).getName());

        switch (opponentId.get(position).getFaction()) {
            case Raptor:
                if(opponentId.get(position)==_presenter.getSelectedOpponent()) {
                    holder.btn.setBackground(ContextCompat.getDrawable(context, R.drawable.card_background_raptor_selected));
                } else {
                    holder.btn.setBackground(ContextCompat.getDrawable(context, R.drawable.card_background_raptor));
                }

                break;
            case Shark:
                if(opponentId.get(position)==_presenter.getSelectedOpponent()) {
                    holder.btn.setBackground(ContextCompat.getDrawable(context, R.drawable.card_background_shark_selected));
                } else {
                    holder.btn.setBackground(ContextCompat.getDrawable(context, R.drawable.card_background_shark));
                }
                break;
        }



        holder.btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Integer position = (Integer)v.getTag();
                _presenter.setSelectedOpponent(opponentId.get(position));
            }
        });

        return rowView;
    }

}
