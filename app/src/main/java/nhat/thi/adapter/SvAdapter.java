package nhat.thi.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import nhat.thi.R;
import nhat.thi.model.Sinhvien;
import nhat.thi.sqlitedao.SVDAO;

public class SvAdapter extends BaseAdapter {
    List<Sinhvien> arrBook;
    public Activity context;
    public LayoutInflater inflater;
    SVDAO SVDAO;

    public SvAdapter(Activity context, List<Sinhvien> arrBook) {
        this.arrBook = arrBook;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.SVDAO = new SVDAO(context);
    }

    @Override
    public int getCount() {
        return arrBook.size();
    }

    @Override
    public Object getItem(int position) {
        return arrBook.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_list, null);
            holder.img = (ImageView) convertView.findViewById(R.id.imgLogo);
            holder.tvMaSV = (TextView) convertView.findViewById(R.id.tvMaSv);
            holder.tvMaLop = (TextView) convertView.findViewById(R.id.tvMaLop);
            holder.tvDiemvan = (TextView) convertView.findViewById(R.id.tvDiemvan);
            holder.tvDiemtoan = (TextView) convertView.findViewById(R.id.tvDiemtoan);

            convertView.setTag(holder);

        } else
            holder = (ViewHolder) convertView.getTag();
        Sinhvien _entry = (Sinhvien) arrBook.get(position);
        holder.img.setImageResource(R.mipmap.ic_launcher_round);
        holder.tvMaSV.setText(_entry.getMaSV());
        holder.tvMaLop.setText(_entry.getMaLop());
        holder.tvDiemtoan.setText(_entry.getDiemtoan() + "");
        holder.tvDiemvan.setText(_entry.getDiemVan() + "");
        return convertView;
    }

    public static class ViewHolder {
        ImageView img;
        TextView tvMaSV;
        TextView tvMaLop;
        TextView tvDiemtoan, tvDiemvan;

    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<Sinhvien> items) {
        this.arrBook = items;
        notifyDataSetChanged();
    }
}
