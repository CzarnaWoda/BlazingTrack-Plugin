package pl.blackwater.blazingtrack.connection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import io.netty.handler.timeout.TimeoutException;
import pl.blackwater.blazingtrack.settings.Config;

public class BlazingTrackConnection {
	private static String getContent(String urlName) {
		String body = null;
		try{
			URL url = new URL(urlName);
			URLConnection con = url.openConnection();
			InputStream in = con.getInputStream();
			String encoding = con.getContentEncoding();
			encoding = encoding == null ? "UTF-8" : encoding;
			body = toString(in,encoding);
			in.close();
		}
		catch (TimeoutException timeout ) {} catch (Exception localException){}
		return body;
	}
	private static String toString(InputStream in, String encoding) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buf = new byte[8192];
		int len = 0;
		while ((len = in.read(buf)) != -1)
			baos.write(buf,0,len);
		in.close();
		return new String(baos.toByteArray(),encoding);
	}
	public static boolean checkConnection(){
		String response = getContent("https://blazingtrack.pl/api/" + Config.SERVER_IP);
		return response != null;
	}
	public static JSONObject getJSONBlazingTrackServerInfo(){
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		try{
		Object obj = parser.parse(getContent("https://blazingtrack.pl/api/" + Config.SERVER_IP));
		JSONObject jsonObject = (JSONObject) obj;
		json = (JSONObject) jsonObject.get("server");
		}catch (Exception e) {
            e.printStackTrace();
        }
		return json;
	}
	public static JSONObject getJSONBlazingTrackPlayer(UUID u){
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		try{
		Object obj = parser.parse(getContent("https://blazingtrack.pl/api/" + Config.SERVER_IP + "/" + u.toString().replace("-", "")));
		json = (JSONObject) obj;
		}catch (Exception e) {
            e.printStackTrace();
        }
		return json;
	}
	public static boolean checkPlayerLike(Player p){
		JSONObject jsonObject = getJSONBlazingTrackPlayer(p.getUniqueId());
		boolean status = (boolean) jsonObject.get("like");
		return status;
	}
}
/*	public static boolean checkPlayerLike(Player p){
		boolean status = false;
        try {
            URL url = new URL("https://blazingtrack.pl/api/" + Config.SERVER_IP + "/" + p.getUniqueId().toString().replace("-", ""));
            HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
            httpcon.addRequestProperty("User-Agent", "Mozilla/4.76");
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(url.openStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            String inputLine;
            try {
                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.contains("true")) {
                        status = true;
                    }

                    if (inputLine.contains("false")) {
                        status = false;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return status;
	}
 *   public static void main(String[] args) {
        try {
            URL url = new URL("https://blazingtrack.pl/api/agehard.pl/" + "dasdasdadas");
            HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
            httpcon.addRequestProperty("User-Agent", "Mozilla/4.76");
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(url.openStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            String inputLine;
            try {
                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.contains("true")) {
                        System.out.println("MA ");
                        return;
                    }

                    if (inputLine.contains("false")) {
                        System.out.println(" NIE MA ");
                        return;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
