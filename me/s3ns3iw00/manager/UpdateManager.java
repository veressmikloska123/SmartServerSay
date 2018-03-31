package me.s3ns3iw00.manager;

import me.s3ns3iw00.main.MainClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UpdateManager {

    private MainClass plugin;
    public UpdateManager(MainClass plugin){
        this.plugin = plugin;
    }

    public boolean needUpdate(){
        boolean is = false;
        String line = "";
        try {
            URL url = new URL("https://endercraft.hu/smartserversay/properties.php?action=checkversion&currentversion="+plugin.current_version);
            URLConnection connection = url.openConnection();
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while((inputLine = in.readLine()) != null)
            {
                line = inputLine;
            }

            in.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        if(line.equalsIgnoreCase("true")){
            is = true;
        }
        return is;
    }

    public String getDowloadLink(){
        String link = "";
        try {
            URL url = new URL("https://endercraft.hu/smartserversay/properties.php?action=getlink");
            URLConnection connection = url.openConnection();
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while((inputLine = in.readLine()) != null)
            {
                link = inputLine;
            }

            in.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return link;
    }

}
