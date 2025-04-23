CREATE DATABASE student_system;
USE student_system;

CREATE TABLE sinh_vien (
    soCMND VARCHAR(12) PRIMARY KEY,
    hoTen VARCHAR(100),
    email VARCHAR(100),
    soDT VARCHAR(15)
);

CREATE TABLE truong (
    maTruong VARCHAR(10) PRIMARY KEY,
    tenTruong VARCHAR(100)
);

CREATE TABLE nganh (
    maNganh VARCHAR(10) PRIMARY KEY,
    tenNganh VARCHAR(100)
);

CREATE TABLE tot_nghiep (
    soCMND VARCHAR(12),
    maTruong VARCHAR(10),
    maNganh VARCHAR(10),
    ngayTN DATE,
    PRIMARY KEY (soCMND, maTruong, maNganh),
    FOREIGN KEY (soCMND) REFERENCES sinh_vien(soCMND),
    FOREIGN KEY (maTruong) REFERENCES truong(maTruong),
    FOREIGN KEY (maNganh) REFERENCES nganh(maNganh)
);

CREATE TABLE viec_lam (
    soCMND VARCHAR(12),
    tenCongTy VARCHAR(100),
    diaChiCongTy VARCHAR(200),
    viTriCongViec VARCHAR(100),
    PRIMARY KEY (soCMND, tenCongTy),
    FOREIGN KEY (soCMND) REFERENCES sinh_vien(soCMND)
);

INSERT INTO truong (maTruong, tenTruong) VALUES
('T001', 'Đại học Bách Khoa'),
('T002', 'Đại học Kinh Tế'),
('T003', 'Đại học Sư Phạm');

INSERT INTO nganh (maNganh, tenNganh) VALUES
('N001', 'Công nghệ Thông tin'),
('N002', 'Kinh tế Quốc tế'),
('N003', 'Sư phạm Toán');