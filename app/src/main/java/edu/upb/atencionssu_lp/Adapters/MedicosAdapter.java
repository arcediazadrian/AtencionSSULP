package edu.upb.atencionssu_lp.Adapters;

/**
 * Created by Adrian on 5/8/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.upb.atencionssu_lp.Modelos.Agenda;
import edu.upb.atencionssu_lp.Modelos.Medico;
import edu.upb.atencionssu_lp.R;

/**
 * Created by Adrian on 5/7/2018.
 */

public class MedicosAdapter extends RecyclerView.Adapter<MedicosAdapter.ViewHolder>{
    private List<Medico> datos;
    private Context context;

    private OnMedicoClickListener onMedicoClickListener;

    public MedicosAdapter(Context context) {
        datos = new ArrayList<Medico>();
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medico, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Medico medico = datos.get(position);
        holder.medicoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onMedicoClickListener != null){
                    onMedicoClickListener.onMedicoClick(medico);
                }
            }
        });
        holder.nombreTextView.setText("Medico: " + medico.getNombreSimple());
        holder.especialidadTextView.setText("Especialidad: " + medico.getEspecialidad());
        holder.horarioEntradaTextView.setText("Horario Entrada: " + medico.getHorarioInicio());
        holder.horarioSalidaTextView.setText("Horario Salida: " + medico.getHorarioSalida());
        holder.telefonoMovilTextView.setText("Celular: " + medico.getTelefonoMovil());
        holder.correoDiarioTextView.setText("Mail: " + medico.getCorreoDiario());
        holder.consultorioMedicoTextView.setText("Consultorio: " + medico.getConsultorio().getCodigo() + " en el piso " + medico.getConsultorio().getPiso());
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void colocarDatos(List<Medico> datos) {
        this.datos = datos;
        notifyDataSetChanged();
    }

    public void addAgenda(Medico medico) {
        datos.add(medico);
        notifyDataSetChanged();
    }

    public void clear() {
        datos.clear();
        notifyDataSetChanged();
    }

    public void setOnMedicoClickListener(OnMedicoClickListener onMedicoClickListener) {
        this.onMedicoClickListener = onMedicoClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout medicoLayout;
        TextView nombreTextView;
        TextView especialidadTextView;
        TextView horarioEntradaTextView;
        TextView horarioSalidaTextView;
        TextView telefonoMovilTextView;
        TextView correoDiarioTextView;
        TextView consultorioMedicoTextView;


        public ViewHolder(View itemView) {
            super(itemView);

            medicoLayout = (LinearLayout) itemView.findViewById(R.id.medicoLayout);
            nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
            especialidadTextView= (TextView) itemView.findViewById(R.id.especialidadTextView);
            horarioEntradaTextView = (TextView) itemView.findViewById(R.id.horarioEntradaTextView);
            horarioSalidaTextView = (TextView) itemView.findViewById(R.id.horarioSalidaTextView);
            telefonoMovilTextView = (TextView) itemView.findViewById(R.id.telefonoMovilTextView);
            correoDiarioTextView = (TextView) itemView.findViewById(R.id.correoDiarioTextView);
            consultorioMedicoTextView=  (TextView) itemView.findViewById(R.id.consultorioMedicoTextView);
        }
    }
}



