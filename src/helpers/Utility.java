/*
 * Jim Klayder -- spring 2018
 * 
 * Here are several utility routines that might be helpful.
 */
package helpers;


import java.io.*;
import java.net.*;


public class Utility {
    
    
    
    //original idea from:
    //     https://duckduckgo.com/?q=java+html+get+web+page+code+how+to&t=seamonkey&ia=qa&iax=qa
    
    //https://stackoverflow.com/questions/8616781/how-to-get-a-web-pages-source-code-from-java#8616808
    
    //alterations by Klayder
    public static String getUrlSource(String url){
            
        StringBuilder a = new StringBuilder("could not retrieve html code from:  "+url);
        try{ 
            URL theURL = new URL(url);
            URLConnection connection = theURL.openConnection();
            
            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream(), "UTF-8"))) 
            {
                String inputLine;
                a = new StringBuilder();
                while ((inputLine = in.readLine()) != null)
                    a.append(inputLine);
            }
          }catch (Exception ex)
        {
            System.out.println("ex:   "+ex.toString());
        }

        return a.toString();
    }
    
    /*
    in the example call:
        int num = getRandomFromTo(10,15);
    
    num should be set to 10, 11, 12, 13, 14, or 15
    
    */
    public static int getRandomFromTo(int from, int to)
    {
        if (from > to)
        {
            System.out.println("error in Utility.getRandomFromTo");
            System.out.println("can't have 'from' < 'to'");
            System.out.println("from:  "+from+"      to:  "+to);
            return -99999;
        }else
        {
            int range = to - from + 1;
            int answer = (int)(Math.random() * range);
            return from + answer;
        }
    }
    
}
