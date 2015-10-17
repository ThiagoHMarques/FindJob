package com.example.thiago.findjob.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.thiago.findjob.R;
import com.example.thiago.findjob.activitys.DownloadActivity;
import com.example.thiago.findjob.domain.Aluno;
import com.example.thiago.findjob.domain.Vaga;

import java.util.List;

/**
 * Created by THIAGO on 21/09/2015.
 */
public class CandidatoAdapter extends RecyclerView.Adapter<CandidatoAdapter.MyViewHolder> {

    private List<Aluno> mList;
    private List<Vaga> mListV;
    private LayoutInflater mLayoutInflater;
    private Intent intent;
    private Context context;

    public CandidatoAdapter(Context c, List<Aluno> mList, List<Vaga> mListV) {
        mLayoutInflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mList = mList;
        this.mListV = mListV;
        this.context = c;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_candidatos_card, parent,false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        holder.tvnome.setText(mList.get(position).getNome().toString());
        holder.tvcurso.setText(mList.get(position).getEscolaridade().toString());
        holder.tvemail.setText(mList.get(position).getEmail().toString());
        holder.tvtelefone.setText(mList.get(position).getTelefone().toString());
        holder.tvvaga.setText(mListV.get(position).getCargo().getNome().toString());

        holder.bt_vercurriculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(context, DownloadActivity.class);
                Aluno aluno = mList.get(position);
                intent.putExtra("url", "downloadCurriculo/");
                intent.putExtra("nomeArq", aluno.getAnexo());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tvnome,tvcurso,tvtelefone,tvemail,tvvaga;
        public Button bt_vercurriculo;
        public MyViewHolder(View itemView) {
            super(itemView);

            tvnome = (TextView) itemView.findViewById(R.id.tvnome);
            tvcurso = (TextView) itemView.findViewById(R.id.tvcurso);
            tvtelefone = (TextView) itemView.findViewById(R.id.tvtelefone);
            tvemail = (TextView) itemView.findViewById(R.id.tvemail);
            tvvaga = (TextView) itemView.findViewById(R.id.tvvaga);
            bt_vercurriculo = (Button) itemView.findViewById(R.id.bt_vercurriculo);
        }
    }
}
