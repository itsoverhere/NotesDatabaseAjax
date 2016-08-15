package edu.webapde.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.webapde.db.DBPool;
import edu.webapde.dto.Note;

public class NotesService {
	
	public static void addNote(String note){
		String sql = "INSERT INTO " + Note.TABLE_NAME + "(" + Note.COLUMN_NOTE + ")"
					+ " VALUES (?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBPool.getInstance().getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, note);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static ArrayList<Note> getAllNotes(){
		ArrayList<Note> noteList = new ArrayList<Note>();
		String sql="SELECT * FROM " + Note.TABLE_NAME + ";";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBPool.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Note n = new Note();
				n.setId(rs.getInt(Note.COLUMN_ID));
				n.setNote(rs.getString(Note.COLUMN_NOTE));
				n.setVisible(rs.getInt(Note.COLUMN_ISVISIBLE) == 0 ? false : true);
				noteList.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return noteList;
	}
	
	public static int toggleNoteVisibility(int id){
		String sql="UPDATE " + Note.TABLE_NAME + " SET " + Note.COLUMN_ISVISIBLE + " =? "
				  +	" WHERE " + Note.COLUMN_ID + "=?";
		
		int isVisible = Math.abs(getNoteVisibility(id)-1);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBPool.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, isVisible);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return isVisible;
		// if 1, true, else false
	}
	
	public static int getNoteVisibility(int id){
		int isVisible = -1;
		
		String sql= "SELECT " + Note.COLUMN_ISVISIBLE + " FROM " + Note.TABLE_NAME
				  + " WHERE " + Note.COLUMN_ID + " =?;";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBPool.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				isVisible = rs.getInt(Note.COLUMN_ISVISIBLE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return isVisible;
	}

}
