package xmlrpc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class XmlRpcTest {

	public static void main(String[] args) throws XmlRpcException, IOException {
		// TODO Auto-generated method stub
		
		String url="https://rpc.cnblogs.com/metaweblog/pandachen";
		String userName="chenxiaopang";
		String passWord="chenjy@7512"; 
		
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
	    config.setServerURL(new URL(url));
	    XmlRpcClient client = new XmlRpcClient();
	    client.setConfig(config);
	    
	    
		//method metaWeblog.newPost
		{
			System.out.println("\n\n**********method metaWeblog.newPost***********\n\n");
			String title = "Markdown教程";
			StringBuffer buffer = new StringBuffer();
	        BufferedReader bf= new BufferedReader(new FileReader("/home/cb/tmp/test.md"));
	        String s = null;
	        while((s = bf.readLine())!=null){//使用readLine方法，一次读一行
	            buffer.append(s);
	            buffer.append("\n");
	        }
	        String content = buffer.toString();
	        System.out.println("content="+content);
			String tags = "tag1, tag2";
			String[] markdown=new String[] {"[Markdown]"};
			HashMap map1 = new HashMap();
			map1.put("title", title);
			map1.put("description", content);
			map1.put("mt_keywords", tags);
			map1.put("categories",markdown );
			Object[] params1 = new Object[] { "", userName, passWord, map1, true };
			client.execute("metaWeblog.newPost", params1);
		}
	    
	    //method metaWeblog.getRecentPosts
		{
			System.out.println("\n\n**********method metaWeblog.getRecentPosts***********\n\n");
			Object[] params = new Object[] { "", userName, passWord, 0 }; //0---获取所有
			Object[] result = (Object[]) client.execute("metaWeblog.getRecentPosts", params);
			String title=null;
			String postID=null;
			for (int i=0;i<result.length;i++) {
				HashMap map = (HashMap) result[i];
				title=(String) map.get("title");
				postID=(String)map.get("postid");
				System.out.println(title+"  postid="+postID);
				
			}
		}
		
	    //method blogger.deletePost
		{
			System.out.println("\n\n**********method blogger.deletePost***********\n\n");
			Object[] params = new Object[] { "", userName, passWord, 1 }; // 0---获取所有
			Object[] result = (Object[]) client.execute("metaWeblog.getRecentPosts", params);
			String title = null;
			String postID = null;
			HashMap map = (HashMap) result[0];
			title = (String) map.get("title");
			postID = (String) map.get("postid");
			System.out.println("Delete a post: "+title + "  postid=" + postID);
			// delete a recent post
			Object[] params1 = new Object[] { "", postID, userName, passWord, true }; 
			client.execute("blogger.deletePost", params1);
		}
		
		//method metaWeblog.getPost
		{
			System.out.println("\n\n**********method metaWeblog.getPost***********\n\n");
			String postID="11336020";   //图解SSH隧道功能  postid=11336020
			Object[] params = new Object[] {postID, userName, passWord};
			Object result = (Object) client.execute("metaWeblog.getPost", params);
			String title=null;
			String description=null;
			HashMap map = (HashMap) result;
			title = (String) map.get("title");
			int postID1 = (int) map.get("postid");
			description = (String) map.get("description");
			System.out.println(title + "  postid=" + postID1);
			System.out.println(description);
				

		}
		
		//method metaWeblog.getCategories
		{
			System.out.println("\n\n**********method metaWeblog.getCategories***********\n\n");
			Object[] params = new Object[] { "", userName, passWord};
			Object[] result = (Object[]) client.execute("metaWeblog.getCategories", params);
			for (int i=0;i<result.length;i++) 
			{
				HashMap map = (HashMap) result[i];
				System.out.println(map.toString());
			}

		}
		
		//method blogger.getUsersBlogs
		{
			System.out.println("\n\n**********method blogger.getUsersBlogs***********\n\n");
			Object[] params = new Object[] { "", userName, passWord};
			Object[] result = (Object[]) client.execute("blogger.getUsersBlogs", params);
			for (int i=0;i<result.length;i++) 
			{
				HashMap map = (HashMap) result[i];
				System.out.println(map.toString());
			}

		}
	}

}
