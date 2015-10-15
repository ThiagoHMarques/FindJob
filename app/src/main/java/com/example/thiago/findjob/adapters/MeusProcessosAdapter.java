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
import com.example.thiago.findjob.domain.Vaga;

import java.util.List;

/**
 * Created by THIAGO on 21/09/2015.
 */
public class MeusProcessosAdapter extends RecyclerView.Adapter<MeusProcessosAdapter.MyViewHolder> {

    private List<Vaga> mList;
    private LayoutInflater mLayoutInflater;
    private Context context;
    private Intent intent;

    public MeusProcessosAdapter(Context c, List<Vaga> mList) {
        mLayoutInflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = c;
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_meusprocessos_card, parent,false);
        MyViewHolder mvh = new MyViewHolder(v);


        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        holder.tvremuneracao.setText(mList.get(position).getRemuneracao().toString());
        holder.tvdesc.setText(mList.get(position).getDesc().toString());
        holder.tvcargo.setText(mList.get(position).getCargo().getNome().toString());

        holder.bt_detalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(context, DownloadActivity.class);
                Vaga vaga = mList.get(position);
                intent.putExtra("url","downloadDesc/");
                intent.putExtra("nomeArq", vaga.getAnexo());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tvdesc,tvcargo,tvremuneracao;
        public Button bt_detalhes;
        public MyViewHolder(View itemView) {
            super(itemView);

            tvcargo = (TextView) itemView.findViewById(R.id.tvcargo);
            tvdesc = (TextView) itemView.findViewById(R.id.tvdesc);
            tvremuneracao = (TextView) itemView.findViewById(R.id.tvremunicacao);
            bt_detalhes = (Button) itemView.findViewById(R.id.bt_mp_detalhes);
        }
    }
}
