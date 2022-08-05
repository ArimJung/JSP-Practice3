package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.util.JDBCUtil;
import model.vo.MemberVO;

public class MemberDAO {

	Connection conn;
	PreparedStatement pstmt;
	
	final String sql_insert = "INSERT INTO MEMBER VALUES((SELECT NVL(MAX(MPK),0)+1 FROM MEMBER),?,?)";
	final String sql_selectAll = "SELECT * FROM MEMBER"; 
	final String sql_selectOne = "SELECT * FROM MEMBER WHERE MPK=?"; 
	final String sql_update = "UPDATE MEMBER SET SCORE = ? WHERE MPK=?";
	final String sql_delete = "DELETE FROM MEMBER WHERE MPK=?"; 
	
	public boolean insert(MemberVO vo) { 
		conn = JDBCUtil.connect(); // 드라이버 연결
		try {	
				pstmt = conn.prepareStatement(sql_insert);
				pstmt.setString(1, vo.getName());
				pstmt.setInt(2, vo.getScore());
				pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn); // 드라이버 연결 해제
		}
		return true;

	}
	
	
	public ArrayList<MemberVO> selectAll(MemberVO vo){
		ArrayList<MemberVO> datas = new ArrayList<MemberVO>();
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(sql_selectAll);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MemberVO data = new MemberVO();
				data.setMpk(rs.getInt("MPK"));
				data.setName(rs.getString("NAME")); 
				data.setScore(rs.getInt("SCORE"));
				datas.add(data);
			}
			rs.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return datas;
	}
	
	public MemberVO selectOne(MemberVO vo) {
		conn = JDBCUtil.connect();
		ResultSet rs =null;
		try {
			pstmt = conn.prepareStatement(sql_selectOne);
			pstmt.setInt(1, vo.getMpk());
			System.out.println("log2");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("log1");
				MemberVO data = new MemberVO();
				data.setMpk(rs.getInt("MPK"));
				data.setName(rs.getString("NAME"));
				data.setScore(rs.getInt("SCORE"));
				return data;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			JDBCUtil.disconnect(pstmt, conn);
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean update(MemberVO vo) {
		conn = JDBCUtil.connect();
		
		try {
			pstmt = conn.prepareStatement(sql_update);
			pstmt.setInt(1, vo.getScore());
			pstmt.setInt(2, vo.getMpk());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} 
		finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}
	
	public boolean delete(MemberVO vo) {
		conn = JDBCUtil.connect();
		
		try {
			pstmt = conn.prepareStatement(sql_delete);
			pstmt.setInt(1, vo.getMpk());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}
	
}
