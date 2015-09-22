package com.example.thiago.findjob.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thiago.findjob.R;
import com.example.thiago.findjob.domain.Vaga;

import java.util.List;

/**
 * Created by THIAGO on 21/09/2015.
 */
public class VagaAdapter extends RecyclerView.Adapter<VagaAdapter.MyViewHolder> {

    private List<Vaga> mList;
    private LayoutInflater mLayoutInflater;

    public VagaAdapter(Context c) {
        mLayoutInflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_vaga_card, parent,false);
        MyViewHolder mvh = new MyViewHolder(v);


        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvremuneracao.setText("1500");
        holder.tvdesc.setText("esta e a descrição da vaga.esta e a descrição da vaga.esta e a descrição da vaga.esta e a descrição da vaga.");
        holder.tvcargo.setText("Analista de sistemas");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tvdesc,tvcargo,tvremuneracao;
        public MyViewHolder(View itemView) {
            super(itemView);

            tvcargo = (TextView) itemView.findViewById(R.id.tvcargo);
            tvdesc = (TextView) itemView.findViewById(R.id.tvdesc);
            tvremuneracao = (TextView) itemView.findViewById(R.id.tvremunicacao);
        }
    }
}
