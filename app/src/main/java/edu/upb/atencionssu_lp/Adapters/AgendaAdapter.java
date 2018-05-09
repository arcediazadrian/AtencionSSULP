package edu.upb.atencionssu_lp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.upb.atencionssu_lp.Modelos.Agenda;
import edu.upb.atencionssu_lp.R;

/**
 * Created by Adrian on 5/7/2018.
 */

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.ViewHolder>{
    private List<Agenda> datos;
    private Context context;

    public AgendaAdapter(Context context) {
        datos = new ArrayList<Agenda>();
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agenda, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Agenda agenda = datos.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = sdf.format(agenda.getFechaDeAtencion());
        holder.nombreTextView.setText(agenda.getPaciente().getNombreCompleto());
        holder.fechaTextView.setText(fecha);
        holder.horaTextView.setText(agenda.getHoraConsulta());
        holder.medicoTextView.setText(agenda.getMedico().getNombreCompleto());
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void colocarDatos(List<Agenda> datos) {
        this.datos = datos;
        notifyDataSetChanged();
    }

    public void addAgenda(Agenda agenda) {
        datos.add(agenda);
        notifyDataSetChanged();
    }

    public void clear() {
        datos.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombreTextView;
        TextView fechaTextView;
        TextView horaTextView;
        TextView medicoTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
            fechaTextView= (TextView) itemView.findViewById(R.id.fechaTextView);
            horaTextView= (TextView) itemView.findViewById(R.id.horaTextView);
            medicoTextView = (TextView) itemView.findViewById(R.id.medicoTextView);
        }
    }
}


