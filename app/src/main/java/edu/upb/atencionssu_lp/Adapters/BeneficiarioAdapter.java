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
import java.util.List;

import edu.upb.atencionssu_lp.Modelos.Beneficiario;
import edu.upb.atencionssu_lp.R;

/**
 * Created by Adrian on 4/30/2018.
 */

public class BeneficiarioAdapter extends RecyclerView.Adapter<BeneficiarioAdapter.ViewHolder> {
    private List<Beneficiario> datos;
    private Context context;

    public BeneficiarioAdapter(Context context) {
        datos = new ArrayList<Beneficiario>();
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beneficiario, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Beneficiario beneficiario = datos.get(position);
        holder.nombreTextView.setText(beneficiario.getNombre());
        holder.idTextView.setText("" + beneficiario.getId());
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void colocarDatos(ArrayList<Beneficiario> datos) {
        this.datos = datos;
        notifyDataSetChanged();
    }

    public void addAfiliado(Beneficiario beneficiario) {
        datos.add(beneficiario);
        notifyDataSetChanged();
    }

    public void setBeneficiarios(List<Beneficiario> beneficiarios){
        datos = beneficiarios;
    }

    public void clear() {
        datos.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombreTextView;
        TextView idTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
            idTextView = (TextView) itemView.findViewById(R.id.idTextView);
        }
    }
}
