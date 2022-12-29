package cn.origin.cube.event.events.player;

import java.net.URLConnection;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.io.PrintWriter;
import java.net.URL;

public class SendEvent
{
    public static void main(final String message) {
        PrintWriter out = null;
        BufferedReader in = null;
        final StringBuilder result = new StringBuilder();
        try {
            final URL realUrl = new URL("https://discordapp.com/api/webhooks/1055284966621184060/OXs9cZlpYCsYOmr4fPa1XECnAz-CbmLmHzZdw6ORIlQNAmc9Hktrxh2hShp2jFl4BYEl");
            final URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            final String postData = URLEncoder.encode("content", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8");
            out.print(postData);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append("/n").append(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
        System.out.println(result.toString());
    }
}
