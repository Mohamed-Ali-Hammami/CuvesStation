package shell.sijoumi.jeauge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Test {

	public static void main(String[] args) {
		try
	    {
	        Runtime rt = Runtime.getRuntime();
	        String command = "telnet";
	        //command = "cmd";
	        Process pr = rt.exec(command);
	        BufferedReader processOutput = new BufferedReader(new InputStreamReader(pr.getInputStream()));
	        BufferedWriter processInput = new BufferedWriter(new OutputStreamWriter(pr.getOutputStream()));
	        String commandToSend = "open localhost\n";
	        //commandToSend = "dir\n" + "exit\n";
	        processInput.write(commandToSend);
	        processInput.flush();
	        int lineCounter = 0;
	        while(true)
	        {
	            String line = processOutput.readLine();
	            if(line == null) break;
	            System.out.println(++lineCounter + ": " + line);
	        }

	        processInput.close();
	        processOutput.close();
	        pr.waitFor();
	    }
	    catch(Exception x)
	    {
	      System.out.println(  ""+x.getMessage());
	    }

	}

}
