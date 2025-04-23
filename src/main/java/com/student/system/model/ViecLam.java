package com.student.system.model;

public class ViecLam {
    private String soCMND;
    private String tenCongTy;
    private String diaChiCongTy;
    private String viTriCongViec;

    public ViecLam() {}

    public ViecLam(String soCMND, String tenCongTy, String diaChiCongTy, String viTriCongViec) {
        this.soCMND = soCMND;
        this.tenCongTy = tenCongTy;
        this.diaChiCongTy = diaChiCongTy;
        this.viTriCongViec = viTriCongViec;
    }

    public String getSoCMND() {
        return soCMND;
    }

    public void setSoCMND(String soCMND) {
        this.soCMND = soCMND;
    }

    public String getTenCongTy() {
        return tenCongTy;
    }

    public void setTenCongTy(String tenCongTy) {
        this.tenCongTy = tenCongTy;
    }

    public String getDiaChiCongTy() {
        return diaChiCongTy;
    }

    public void setDiaChiCongTy(String diaChiCongTy) {
        this.diaChiCongTy = diaChiCongTy;
    }

    public String getViTriCongViec() {
        return viTriCongViec;
    }

    public void setViTriCongViec(String viTriCongViec) {
        this.viTriCongViec = viTriCongViec;
    }
}