package com.example.thiago.findjob.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thiago.findjob.R;
import com.example.thiago.findjob.domain.Aluno;
import com.example.thiago.findjob.domain.Empresa;

import java.util.List;

/**
 * Created by THIAGO on 21/09/2015.
 */
public class EmpresaAdapter extends RecyclerView.Adapter<EmpresaAdapter.MyViewHolder> {

    private List<Empresa> mList;
    private LayoutInflater mLayoutInflater;

    public EmpresaAdapter(Context c) {
        mLayoutInflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_empresa_card, parent,false);
        MyViewHolder mvh = new MyViewHolder(v);


        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvnome.setText("Algar Telecom");
        holder.tvsegmento.setText("Tecnologia");
        holder.tvemail.setText("algar@algar.com");
        holder.tvtelefone.setText("(34)3336-3521");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tvnome,tvsegmento,tvtelefone,tvemail;
        public MyViewHolder(View itemView) {
            super(itemView);

            tvnome = (TextView) itemView.findViewById(R.id.tvnome);
            tvsegmento = (TextView) itemView.findViewById(R.id.tvsegmento);
            tvtelefone = (TextView) itemView.findViewById(R.id.tvtelefone);
            tvemail = (TextView) itemView.findViewById(R.id.tvemail);
        }
    }
}
