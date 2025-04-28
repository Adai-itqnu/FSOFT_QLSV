package com.student.system.dao;

import com.student.system.model.Nganh;
import com.student.system.model.SinhVien;
import com.student.system.model.TotNghiep;
import com.student.system.model.Truong;
import com.student.system.model.ViecLam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/student_system?useUnicode=true&characterEncoding=UTF-8";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "123456";

    private Connection getConnection() throws SQLException {
        try {
            // Explicitly load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            System.out.println("Kết nối database thành công!");
            return conn;
        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy Driver JDBC: " + e.getMessage());
            throw new SQLException("JDBC Driver not found", e);
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối database: " + e.getMessage());
            throw e;
        }
    }

    public void saveSinhVien(SinhVien sinhVien) {
        String sql = "INSERT INTO sinh_vien (soCMND, hoTen, email, soDT) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, sinhVien.getSoCMND());
            stmt.setString(2, sinhVien.getHoTen());
            stmt.setString(3, sinhVien.getEmail());
            stmt.setString(4, sinhVien.getSoDT());
            stmt.executeUpdate();
            System.out.println("Đã thêm sinh viên: " + sinhVien.getHoTen());
        } catch (SQLException e) {
            System.out.println("Lỗi khi lưu thông tin sinh viên: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    public void saveTotNghiep(TotNghiep totNghiep) {
        String sql = "INSERT INTO tot_nghiep (soCMND, maTruong, maNganh, ngayTN) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, totNghiep.getSoCMND());
            stmt.setString(2, totNghiep.getMaTruong());
            stmt.setString(3, totNghiep.getMaNganh());
            stmt.setDate(4, new java.sql.Date(totNghiep.getNgayTN().getTime()));
            stmt.executeUpdate();
            System.out.println("Đã thêm thông tin tốt nghiệp cho sinh viên: " + totNghiep.getSoCMND());
        } catch (SQLException e) {
            System.out.println("Lỗi khi lưu thông tin tốt nghiệp: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    public List<Truong> getAllTruong() {
        List<Truong> truongList = new ArrayList<>();
        String sql = "SELECT * FROM truong";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Truong truong = new Truong();
                truong.setMaTruong(rs.getString("maTruong"));
                truong.setTenTruong(rs.getString("tenTruong"));
                truongList.add(truong);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn danh sách trường: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        return truongList;
    }

    public List<Nganh> getAllNganh() {
        List<Nganh> nganhList = new ArrayList<>();
        String sql = "SELECT * FROM nganh";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Nganh nganh = new Nganh();
                nganh.setMaNganh(rs.getString("maNganh"));
                nganh.setTenNganh(rs.getString("tenNganh"));
                nganhList.add(nganh);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn danh sách ngành: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        return nganhList;
    }

    public List<SinhVien> searchSinhVien(String keyword) {
        List<SinhVien> result = new ArrayList<>();
        String sql = "SELECT * FROM sinh_vien WHERE soCMND LIKE ? OR hoTen LIKE ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                SinhVien sv = new SinhVien();
                sv.setSoCMND(rs.getString("soCMND"));
                sv.setHoTen(rs.getString("hoTen"));
                sv.setEmail(rs.getString("email"));
                sv.setSoDT(rs.getString("soDT"));
                result.add(sv);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm sinh viên: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        return result;
    }
    
    public List<Object[]> searchGraduationAndJob(String keyword) {
        List<Object[]> result = new ArrayList<>();
        String sql = "SELECT sv.soCMND, sv.hoTen, sv.email, sv.soDT, " +
                "tn.maTruong, t.tenTruong, tn.maNganh, n.tenNganh, tn.ngayTN, " +
                "vl.tenCongTy, vl.diaChiCongTy, vl.viTriCongViec " +
                "FROM sinh_vien sv " +
                "LEFT JOIN tot_nghiep tn ON sv.soCMND = tn.soCMND " +
                "LEFT JOIN truong t ON tn.maTruong = t.maTruong " +
                "LEFT JOIN nganh n ON tn.maNganh = n.maNganh " +
                "LEFT JOIN viec_lam vl ON sv.soCMND = vl.soCMND " +
                "WHERE sv.soCMND LIKE ? OR sv.hoTen LIKE ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Object[] row = new Object[12];
                row[0] = rs.getString("soCMND");
                row[1] = rs.getString("hoTen");
                row[2] = rs.getString("email");
                row[3] = rs.getString("soDT");
                row[4] = rs.getString("maTruong");
                row[5] = rs.getString("tenTruong");
                row[6] = rs.getString("maNganh");
                row[7] = rs.getString("tenNganh");
                row[8] = rs.getDate("ngayTN");
                row[9] = rs.getString("tenCongTy");
                row[10] = rs.getString("diaChiCongTy");
                row[11] = rs.getString("viTriCongViec");
                result.add(row);
            }
            
            System.out.println("Tìm thấy " + result.size() + " kết quả cho từ khóa: " + keyword);
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm thông tin chi tiết: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        return result;
    }
    
    // Helper method to close resources
    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Lỗi khi đóng tài nguyên: " + e.getMessage());
        }
    }
}