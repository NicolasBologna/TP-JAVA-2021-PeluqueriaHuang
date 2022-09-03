package logic;

import java.io.IOException;
import java.util.LinkedList;

import data.CommentData;
import data.PublicationData;
import entities.Comment;
import entities.Publication;

public class Comments {
	public static int add(Comment newComment) throws IOException{
		CommentData pd = new CommentData();
		return pd.add(newComment);
	}
	
}
