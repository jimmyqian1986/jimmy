package jimmy.commons;

import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class AgentTns
{
  public static void main(String[] args)
    throws UnknownHostException
  {
    try
    {
      long startTime = System.currentTimeMillis();
      InetAddress addr = InetAddress.getLocalHost();
      long localHostDuration = System.currentTimeMillis() - startTime;
      String hostAddress = addr.getHostAddress();
      String hostname = addr.getHostName();
      startTime = System.currentTimeMillis();
      String canonicalName = addr.getCanonicalHostName();
      long canonicalNameDuration = System.currentTimeMillis() - startTime;
      
      String outputFormat = "{\"status\": \"0\", \"ip\": \"%s\", \"hostname\": \"%s\", \"canonicalname\": \"%s\", \"localhostDuration\": \"%d\", \"canonicalnameDuration\": \"%d\" }";
      
      String output = String.format(outputFormat, new Object[] { hostAddress, hostname, canonicalName, Long.valueOf(localHostDuration), Long.valueOf(canonicalNameDuration) });
      
      System.out.println(output);
    }
    catch (UnknownHostException e)
    {
      System.out.println("{\"status\": \"1\", \"error\": \"unknown host\"}");
    }
  }
}
