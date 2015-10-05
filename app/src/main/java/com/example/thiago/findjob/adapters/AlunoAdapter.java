package com.example.thiago.findjob.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thiago.findjob.R;
import com.example.thiago.findjob.domain.Aluno;
import com.example.thiago.findjob.domain.Vaga;

import java.util.List;

/**
 * Created by THIAGO on 21/09/2015.
 */
public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.MyViewHolder> {

    private List<Aluno> mList;
    private LayoutInflater mLayoutInflater;

    public AlunoAdapter(Context c) {
        mLayoutInflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_aluno_card, parent,false);
        MyViewHolder mvh = new MyViewHolder(v);


        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvnome.setText("Thiago Henrique Silva Marques");
        holder.tvcurso.setText("Analise e desenvolvimento de sistemas");
        holder.tvemail.setText("thiagohsmarques@gmail.com");
        holder.tvtelefone.setText("(34)9196-3161");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tvnome,tvcurso,tvtelefone,tvemail;
        public MyViewHolder(View itemView) {
            super(itemView);

            tvnome = (TextView) itemView.findViewById(R.id.tvnome);
            tvcurso = (TextView) itemView.findViewById(R.id.tvcurso);
            tvtelefone = (TextView) itemView.findViewById(R.id.tvtelefone);
            tvemail = (TextView) itemView.findViewById(R.id.tvemail);
        }
    }
}
