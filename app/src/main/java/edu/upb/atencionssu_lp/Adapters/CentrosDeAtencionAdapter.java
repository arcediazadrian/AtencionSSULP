package edu.upb.atencionssu_lp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import edu.upb.atencionssu_lp.Modelos.CentroDeAtencion;
import edu.upb.atencionssu_lp.R;

/**
 * Created by Adrian on 4/30/2018.
 */

public class CentrosDeAtencionAdapter extends RecyclerView.Adapter<CentrosDeAtencionAdapter.ViewHolder>{
    private ArrayList<CentroDeAtencion> datos;
    private Context context;

    public CentrosDeAtencionAdapter(Context context) {
        datos = new ArrayList<CentroDeAtencion>();
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_centro_de_atencion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CentroDeAtencion centroDeAtencion = datos.get(position);
        holder.nombreTextView.setText(centroDeAtencion.getNombre());
        holder.direccionTextView.setText(centroDeAtencion.getDireccion());
        Glide.with(context).load(centroDeAtencion.getImagenCentroAtencionURL()).into(holder.imagenCentroAtencionImageView);

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void colocarDatos(ArrayList<CentroDeAtencion> datos) {
        this.datos = datos;
        notifyDataSetChanged();
    }

    public void addCentroDeAtencion(CentroDeAtencion centroDeAtencion) {
        datos.add(centroDeAtencion);
        notifyDataSetChanged();
    }

    public void clear() {
        datos.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombreTextView;
        TextView direccionTextView;
        ImageView imagenCentroAtencionImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
            direccionTextView = (TextView) itemView.findViewById(R.id.direccionTextView);
            imagenCentroAtencionImageView = (ImageView) itemView.findViewById(R.id.imagenCentroAtencionImageView);
        }
    }
}
