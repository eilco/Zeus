package com.maporientation.maptoomi.adapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maporientation.maptoomi.R;
import com.maporientation.maptoomi.model.Etape;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ashraf on 22/12/2015.
 */

public class TestAdapter12  extends RecyclerView.Adapter<TestAdapter12.ViewHolder>{

    private Activity testActivity12;
    private List<Etape> etapeList;
    private RecyclerView mRecyclerView;
    private int t[],r[],i[],c[],a[],m[];
    private int tv=0,rv=0,iv=0,cv=0,av=0,mv=0;

    public TestAdapter12(Activity testActivity12, List<Etape> etapeList, RecyclerView mRecyclerView) {
        this.testActivity12 = testActivity12;
        this.etapeList = etapeList;
        this.mRecyclerView = mRecyclerView;
        t = new int[35]; r = new int[35]; i = new int[35];
        c = new int[35]; a = new int[35]; m = new int[35];
    }

    @Override
    public TestAdapter12.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row1, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TestAdapter12.ViewHolder holder, final int position) {

        final Etape etape = etapeList.get(position);
        final int pos = position;
        holder.bindView(etape, pos);
        setAllArraysToZero();
        ChangeBackGroundColor(etape, holder);

        Log.v("taille List", getItemCount() + "");
        for(int i = 0; i<getItemCount(); i++) {
            Log.v("etapeListAdapter","question "+i+" : " +etapeList.get(i).getQuestion()+
                    " isRightSelected :"+etapeList.get(i).isRightSelected()+
                    " isLeftSelected : "+etapeList.get(i).isLeftSelected());
        }
    }

    private void setAllArraysToZero() {
        Arrays.fill(t, 0);Arrays.fill(r, 0);Arrays.fill(i, 0);
        Arrays.fill(c, 0);Arrays.fill(a, 0);Arrays.fill(m, 0);
    }

    private void ChangeBackGroundColor(Etape etape,TestAdapter12.ViewHolder holder) {
        if(etape.isRightSelected()==true){
            Log.v("isRightSelectedTag","true");
            holder.imageQuestionDroite.setBackgroundColor(testActivity12.getResources()
                                                            .getColor(R.color.red));
            holder.imageQuestionGauche.setBackgroundColor(testActivity12.getResources()
                                                            .getColor(R.color.white));
        }else if(etape.isLeftSelected()==true){
            Log.v("isLeftSelectedTag","true");
            holder.imageQuestionGauche.setBackgroundColor(testActivity12.getResources()
                                                            .getColor(R.color.green));
            holder.imageQuestionDroite.setBackgroundColor(testActivity12.getResources()
                                                            .getColor(R.color.white));
        } else {
            Log.v("isRightSelectedTag","false");
            holder.imageQuestionDroite.setBackgroundColor(testActivity12.getResources()
                                                            .getColor(R.color.white));
            holder.imageQuestionGauche.setBackgroundColor(testActivity12.getResources()
                                                            .getColor(R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return etapeList.size()/2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView1,cardView2;
        private TextView question;
        private ImageView imageQuestionDroite,imageQuestionGauche,imageQuestionMilieu;
        private Etape etape;
        private int position;

        public ViewHolder(final View view) {
            super(view);
            cardView1 = (CardView) view.findViewById(R.id.card_view1);
            cardView2 = (CardView) view.findViewById(R.id.card_view2);
            question = (TextView) view.findViewById(R.id.question);
            imageQuestionDroite = (ImageView) view.findViewById(R.id.imageQuestionDroite);
            imageQuestionGauche = (ImageView) view.findViewById(R.id.imageQuestionGauche );
            imageQuestionMilieu = (ImageView) view.findViewById(R.id.imageQuestionMilieu);

            imageQuestionDroite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (etape.isRightSelected() != true) {
                        etape.setRightSelected(true);
                        etape.setLeftSelected(false);
                        if (etape.isRightSelected() == true) {
                            countDownTricam();
                            countTricam();
                        }
                        notifyDataSetChanged();
                    }
                }
            });
            imageQuestionGauche.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (etape.isLeftSelected() != true) {
                        etape.setLeftSelected(true);
                        etape.setRightSelected(false);
                        addTricam();
                        countTricam();
                        notifyDataSetChanged();
                    }

                }
            });
        }


        public void bindView(final Etape etape,final int position){
            this.etape = etape;
            this.position = position;

            question.setText(etape.getQuestion());
            imageQuestionDroite.setImageResource(R.drawable.ic_pleure3);
            imageQuestionGauche.setImageResource(R.drawable.ic_sourire3);

            imageQuestionMilieu.setImageResource(testActivity12.getResources()
                    .getIdentifier(etape.getImage(),
                            "drawable", testActivity12.getPackageName()));

            Log.v("Image", "" + etape.getImage() + " packageName: " + testActivity12.getPackageName());
        }

        private void addTricam() {

            switch (etape.getValue()){
                case "T": t[position] = 1;
                    break;

                case "R": r[position] = 1;
                    break;

                case "I": i[position] = 1;
                    break;

                case "C": c[position] = 1;
                    break;

                case "A": a[position] = 1;
                    break;

                case "M": m[position] = 1;
                    break;
            }
        }

        private void countDownTricam() {

            switch (etape.getValue()) {
                case "T": t[position] -= 1;
                    break;
                case "R": r[position] -= 1;
                    break;
                case "I": i[position] = i[position] > 0 ?  i[position] -= 1 : 0;
                    break;
                case "C": c[position] = c[position] > 0 ?  c[position] -= 1 : 0;
                    break;
                case "A": a[position] = a[position] > 0 ?  a[position] -= 1 : 0;
                    break;
                case "M": m[position] = m[position] > 0 ?  m[position] -= 1 : 0;
                    break;
            }

        }

        private void countTricam(){

            for (int j=0;j<5;j++){
                tv +=t[j];
                rv +=r[j];
                iv +=i[j];
                cv +=c[j];
                av +=a[j];
                mv +=m[j];

                Log.v("TRICAM", "T "+ tv +" R " + rv + " I " + iv + " C " + cv + " A " + av + " M " + mv);
            }
        }
    }
}
