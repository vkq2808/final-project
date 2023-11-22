package models;

public class MessageFolderModel {
	int MessFoID;
	String MessFoName;
	String MessFoImageLink;
	public MessageFolderModel(int messFoID, String messFoName, String messFoImageLink) {
		super();
		MessFoID = messFoID;
		MessFoName = messFoName;
		MessFoImageLink = messFoImageLink;
	}
	public MessageFolderModel() {
		super();
	}
	public int getMessFoID() {
		return MessFoID;
	}
	public void setMessFoID(int messFoID) {
		MessFoID = messFoID;
	}
	public String getMessFoName() {
		return MessFoName;
	}
	public void setMessFoName(String messFoName) {
		MessFoName = messFoName;
	}
	public String getMessFoImageLink() {
		return MessFoImageLink;
	}
	public void setMessFoImageLink(String messFoImageLink) {
		MessFoImageLink = messFoImageLink;
	}
	
}
