package xmlrpc;

import java.sql.Date;

public class Post {
	    public Date dateCreated;
        public String description;
        public String title;
        public String postid;
        public String[] categories; 

        public Post()
        {
        	this.description="";
        	this.title="";
        	this.postid="";
     
        }
        
        public String toString()
        {
        	return description+title+postid;
        }
}

