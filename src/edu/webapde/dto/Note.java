package edu.webapde.dto;

public class Note {

	public static String TABLE_NAME = "note";
	public static String COLUMN_ID="idnote";
	public static String COLUMN_NOTE="note";
	public static String COLUMN_ISVISIBLE="isVisible";
	
	private String note;
	private int id;
	private boolean visible;
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Note(){}
	
	public Note(String note, int id, boolean visible) {
		super();
		this.note = note;
		this.id = id;
		this.visible = visible;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

}
