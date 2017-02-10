package backbone;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import java.io.*;

public class SSH_Connection {
	
	private static String user = "smallcj";
	private static String password = "900649073";
	
	public void connect() {
		// TODO Auto-generated method stub
		
		String host = "student.cs.appstate.edu";
		int port = 22;
		
		String remoteFile = "/u/css/smallcj/CS2490/programs/program1/TestTextFile.txt";
		
		try
        {
        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
        System.out.println("Establishing Connection...");
        session.connect();
            System.out.println("Connection established.");
        System.out.println("Crating SFTP Channel.");
        ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
        sftpChannel.connect();
        System.out.println("SFTP Channel created.");


        InputStream out= null;
        out= sftpChannel.get(remoteFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(out));
        String line;
        while ((line = br.readLine()) != null)
            System.out.println(line);
        br.close();
        sftpChannel.disconnect();
        }
    catch(Exception e){System.err.print(e);}
    }
	
	public String setUsername(String uName)
	{
		try 
		{
			user = uName;
			return "Sucess";
		}
		catch (Exception e)
		{
			return "Failed";
		}
		
	}

	public String setPassword(String pw)
	{
		try 
		{
			password = pw;
			return "Sucess";
		}
		catch (Exception e)
		{
			return "Failed";
		}
		
	}
	
}
