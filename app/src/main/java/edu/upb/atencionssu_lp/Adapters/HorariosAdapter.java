package edu.upb.atencionssu_lp.Adapters;

/**
 * Created by Adrian on 5/9/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.upb.atencionssu_lp.Modelos.Agenda;
import edu.upb.atencionssu_lp.Modelos.Horario;
import edu.upb.atencionssu_lp.R;

/**
 * Created by Adrian on 5/7/2018.
 */

public class HorariosAdapter extends RecyclerView.Adapter<HorariosAdapter.ViewHolder>{
    private List<Horario> datos;
    private Context context;
    private OnHorarioClickListener onHorarioClickListener;

    public HorariosAdapter(Context context) {
        datos = new ArrayList<Horario>();
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horario, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Horario horario = datos.get(position);
        holder.horarioTextView.setText(horario.toString());
        holder.horarioLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onHorarioClickListener != null){
                    onHorarioClickListener.onHorarioClick(horario);
                }
            }
        });
    }

    public void setOnHorarioClickListener(OnHorarioClickListener onHorarioClickListener) {
        this.onHorarioClickListener = onHorarioClickListener;
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void colocarDatos(List<Horario> datos) {
        this.datos = datos;
        notifyDataSetChanged();
    }

    public void addAgenda(Horario agenda) {
        datos.add(agenda);
        notifyDataSetChanged();
    }

    public void clear() {
        datos.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout horarioLayout;
        TextView horarioTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            horarioTextView = (TextView) itemView.findViewById(R.id.horarioTextView);
            horarioLayout = (LinearLayout) itemView.findViewById(R.id.horariosLayout);
        }
    }
}


