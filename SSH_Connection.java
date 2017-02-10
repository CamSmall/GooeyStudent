package backbone;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;

import java.io.*;

public class SSH_Connection {
	
	private static String user = "smallcj";
	private static String password = "900649073";
	private static ChannelSftp sftpChannel;
	private static BufferedReader br;
	private static Session session;
	
	public static void connect() {
		
		String host = "student.cs.appstate.edu";
		int port = 22;
		
		String remoteFile = "/u/css/smallcj/CS2490/programs/program1/TestTextFile.txt";
		
		try
        {
        JSch jsch = new JSch();
        session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
        System.out.println("Establishing Connection...");
        session.connect();
            System.out.println("Connection established.");
        System.out.println("Crating SFTP Channel.");
        sftpChannel = (ChannelSftp) session.openChannel("sftp");
        sftpChannel.connect();
        System.out.println("SFTP Channel created.");

        InputStream out= null;
        out= sftpChannel.get(remoteFile);
        br = new BufferedReader(new InputStreamReader(out));
        String line;
        while ((line = br.readLine()) != null)
            System.out.println(line);
        
        }
    catch(Exception e){System.err.print(e);}
    }
	
	public static String setUsername(String uName)
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

	public static String setPassword(String pw)
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
	
	public static void closeUp()
	{
		try 
		{
			br.close();
				System.out.println("BufferedReader closed");
			sftpChannel.disconnect();
				System.out.println("SFTPChannel closed");
			session.disconnect();
				System.out.println("Session closed");
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public static String getPath()
	{
		try
		{
			return sftpChannel.pwd();
		}
		catch (Exception e)
		{
			
		}
		
		return "?";
		
		
	}
	
}