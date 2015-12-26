package com.maporientation.maptoomi.adapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.maporientation.maptoomi.R;
import com.maporientation.maptoomi.model.Etape;

import java.util.List;

/**
 * Created by Ashraf on 22/12/2015.
 */
public class TestAdapter22  extends RecyclerView.Adapter<TestAdapter22.ViewHolder>{

    private Activity testActivity22;
    private List<Etape> etapeList;
    private RecyclerView mRecyclerView;
    private int t=0,r=0,i=0,c=0,a=0,m=0;
    private int valueOfSeekBar;
    private int countClick=3;
    private Etape etape;
    private TestAdapter22.ViewHolder holder;

    public TestAdapter22(Activity testActivity22, List<Etape> etapeList, RecyclerView mRecyclerView) {
        this.testActivity22 = testActivity22;
        this.etapeList = etapeList;
        this.mRecyclerView = mRecyclerView;
    }

    @Override
    public TestAdapter22.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row2, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TestAdapter22.ViewHolder holder, final int position) {

        final Etape etape = etapeList.get(position);

        this.holder = holder;
        this.etape = etape;

        final int pos = position;
        holder.bindView(etape, pos);
        SeekBarValue(etape, holder);

        holder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public int progress;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                this.progress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                switch (progress) {
                    case 0:
                        valueOfSeekBar = 0;
                        etape.setValueOfSeekBar(valueOfSeekBar);
                        holder.imageChoixDroite.setImageResource(R.drawable.ic_pleure3);
                        holder.imageChoixGauche.setImageResource(R.drawable.ic_neutre);
                        break;
                    case 1:
                        valueOfSeekBar = 1;
                        etape.setValueOfSeekBar(valueOfSeekBar);
                        holder.imageChoixDroite.setImageResource(R.drawable.ic_pleure2);
                        holder.imageChoixGauche.setImageResource(R.drawable.ic_neutre);
                        break;
                    case 2:
                        valueOfSeekBar = 2;
                        etape.setValueOfSeekBar(valueOfSeekBar);
                        holder.imageChoixDroite.setImageResource(R.drawable.ic_pleure1);
                        holder.imageChoixGauche.setImageResource(R.drawable.ic_neutre);
                        break;
                    case 3:
                        valueOfSeekBar = 3;
                        etape.setValueOfSeekBar(valueOfSeekBar);
                        holder.imageChoixDroite.setImageResource(R.drawable.ic_neutre);
                        holder.imageChoixGauche.setImageResource(R.drawable.ic_neutre);
                        break;
                    case 4:
                        valueOfSeekBar = 4;
                        etape.setValueOfSeekBar(valueOfSeekBar);
                        holder.imageChoixGauche.setImageResource(R.drawable.ic_sourire1);
                        holder.imageChoixDroite.setImageResource(R.drawable.ic_neutre);
                        break;
                    case 5:
                        valueOfSeekBar = 5;
                        etape.setValueOfSeekBar(valueOfSeekBar);
                        holder.imageChoixGauche.setImageResource(R.drawable.ic_sourire2);
                        holder.imageChoixDroite.setImageResource(R.drawable.ic_neutre);
                        break;
                    case 6:
                        valueOfSeekBar = 6;
                        etape.setValueOfSeekBar(valueOfSeekBar);
                        holder.imageChoixGauche.setImageResource(R.drawable.ic_sourire3);
                        holder.imageChoixDroite.setImageResource(R.drawable.ic_neutre);
                        break;
                    default:
                        valueOfSeekBar = 3;
                        etape.setValueOfSeekBar(valueOfSeekBar);
                        holder.imageChoixGauche.setImageResource(R.drawable.ic_neutre);
                        holder.imageChoixDroite.setImageResource(R.drawable.ic_neutre);
                        break;
                }
            }
        });

        /* // Changer L'image lors du click
        holder.imageChoixDroite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickDroite();
            }
        });

        holder.imageChoixGauche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickGauche();
            }
        });*/

        for(int i = 0; i<getItemCount(); i++) {
            Log.v("etapeListAdapter",
                    " textChoixDroite :"+etapeList.get(i).getChoixDroite()+
                    " textChoixGauche : "+etapeList.get(i).getChoixGauche());
        }
    }


    private void SeekBarValue(Etape etape,TestAdapter22.ViewHolder holder) {
        switch (etape.getValueOfSeekBar()){
            case 0:
                holder.seekBar.setProgress(0);
                holder.imageChoixDroite.setImageResource(R.drawable.ic_pleure3);
                holder.imageChoixGauche.setImageResource(R.drawable.ic_neutre);
                break;
            case 1:
                holder.seekBar.setProgress(1);
                holder.imageChoixDroite.setImageResource(R.drawable.ic_pleure2);
                holder.imageChoixGauche.setImageResource(R.drawable.ic_neutre);
                break;
            case 2:
                holder.seekBar.setProgress(2);
                holder.imageChoixDroite.setImageResource(R.drawable.ic_pleure1);
                holder.imageChoixGauche.setImageResource(R.drawable.ic_neutre);
                break;
            case 3:
                holder.seekBar.setProgress(3);
                holder.imageChoixDroite.setImageResource(R.drawable.ic_neutre);
                holder.imageChoixGauche.setImageResource(R.drawable.ic_neutre);
                break;
            case 4:
                holder.seekBar.setProgress(4);
                holder.imageChoixGauche.setImageResource(R.drawable.ic_sourire1);
                holder.imageChoixDroite.setImageResource(R.drawable.ic_neutre);
                break;
            case 5:
                holder.seekBar.setProgress(5);
                holder.imageChoixGauche.setImageResource(R.drawable.ic_sourire2);
                holder.imageChoixDroite.setImageResource(R.drawable.ic_neutre);
                break;
            case 6:
                holder.seekBar.setProgress(6);
                holder.imageChoixGauche.setImageResource(R.drawable.ic_sourire3);
                holder.imageChoixDroite.setImageResource(R.drawable.ic_neutre);
                break;
            default:
                holder.seekBar.setProgress(3);
                holder.imageChoixGauche.setImageResource(R.drawable.ic_neutre);
                holder.imageChoixDroite.setImageResource(R.drawable.ic_neutre);
                break;

        }
    }


    @Override
    public int getItemCount() {
        return etapeList.size()/2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private TextView textChoixDroite,textChoixGauche;
        private ImageView imageChoixDroite,imageChoixGauche;
        private SeekBar seekBar;

        public ViewHolder(final View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_view);
            textChoixDroite = (TextView) view.findViewById(R.id.textChoixDroite);
            textChoixGauche = (TextView) view.findViewById(R.id.textChoixGauche);
            imageChoixDroite = (ImageView) view.findViewById(R.id.imageChoixDroite);
            imageChoixGauche = (ImageView) view.findViewById(R.id.imageChoixGauche);
            seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        }

        public void bindView(final Etape etape,final int position){
            textChoixDroite.setText(etape.getChoixDroite());
            textChoixGauche.setText(etape.getChoixGauche());
            Log.v("ListChoix", "textChoixDroite : " + etape.getChoixDroite() +
                    " textChoixGauche : " + etape.getChoixGauche());
            imageChoixDroite.setImageResource(R.drawable.ic_neutre);
            imageChoixGauche.setImageResource(R.drawable.ic_neutre);

        }
    }
}

