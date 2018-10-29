package nhat.thi.model;

public class Sinhvien {
    private String MaSV, MaLop;
    private Double Diemtoan, DiemVan;

    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String maSV) {
        MaSV = maSV;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String maLop) {
        MaLop = maLop;
    }

    public Double getDiemtoan() {
        return Diemtoan;
    }

    public void setDiemtoan(Double diemtoan) {
        Diemtoan = diemtoan;
    }

    public Double getDiemVan() {
        return DiemVan;
    }

    public void setDiemVan(Double diemVan) {
        DiemVan = diemVan;
    }

    public Sinhvien(String maSV, String maLop, Double diemtoan, Double diemVan) {
        MaSV = maSV;
        MaLop = maLop;
        Diemtoan = diemtoan;
        DiemVan = diemVan;
    }
    public Sinhvien() {

    }
}
