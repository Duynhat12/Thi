package nhat.thi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nhat.thi.adapter.SvAdapter;
import nhat.thi.model.Sinhvien;
import nhat.thi.sqlitedao.SVDAO;

public class MainActivity extends AppCompatActivity {
    private EditText MaSV, MaLop, DiemToan, DiemVan;
    public static List<Sinhvien> arrayList = new ArrayList<>();
    SvAdapter adapter = null;
    SVDAO SVDAO;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MaSV=findViewById(R.id.edMaSv);
        MaLop=findViewById(R.id.edMaLop);
        DiemToan=findViewById(R.id.edMarktoan);
        DiemVan=findViewById(R.id.edMarkvan);
        listView=findViewById(R.id.lvMark);

        SVDAO = new SVDAO(this);
        arrayList = SVDAO.getAllBook();
        adapter = new SvAdapter(this, arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                Bundle b = new Bundle();
                b.putString("idsinhvien", arrayList.get(position).getMaSV());
                b.putString("maLop", arrayList.get(position).getMaLop());
                b.putString("diemtoan", String.valueOf(arrayList.get(position).getDiemtoan()));
                b.putString("diemvan", String.valueOf(arrayList.get(position).getDiemVan()));
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    public void Add(View view) {
        if (MaSV.getText().length() == 0 ||
                MaLop.getText().length() == 0 ||
                DiemToan.getText().length() == 0 ||
                DiemVan.getText().length() == 0)  {
            Toast.makeText(this, "Bạn phải nhập đủ thông tin ", Toast.LENGTH_SHORT).show();

        } else {
            SVDAO = new SVDAO(this);
            Sinhvien sv = new Sinhvien(MaSV.getText().toString(),
                    MaLop.getText().toString(),
                    Double.parseDouble(DiemToan.getText().toString()),
                    Double.parseDouble(DiemVan.getText().toString()));
            try {
                if (validateForm() > 0) {
                    if (SVDAO.insertBook(sv) > 0) {
                        Toast.makeText(getApplicationContext(), "Thêm thành công",
                                Toast.LENGTH_SHORT).show();
                        arrayList.add(sv);
                        adapter.changeDataset(arrayList);

                    } else {
                        Toast.makeText(getApplicationContext(), "Thêm thất bại",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (Exception ex) {
                Log.e("Error", ex.toString());
            }
        }
    }
    public int validateForm() {
        int check = 1;
        if (MaSV.getText().length() == 0 ||
                MaLop.getText().length() == 0 ||
                DiemToan.getText().length() == 0 ||
                DiemVan.getText().length() == 0) {
            Toast.makeText(this, "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    @Override
    protected void onResume() {
        arrayList.clear();
        arrayList = SVDAO.getAllBook();
        adapter.changeDataset(arrayList);
        super.onResume();
    }
}
