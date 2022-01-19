
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Paths;
import java.nio.file.Files;

/**
 * @Class Location
 * @function GpsPosition() obtain the gps data from each file with exif
 *
 */

public class Location {

    public void GpsPosition(){
        System.out.println("Obtaining gps data...");
        FilesImage file=new FilesImage();
        String[] name= file.GetMultimedia();
        String line=null;
        String[] latitude= new String[name.length];
        String[] longitude= new String[name.length];
        Float[] degLatitude= new Float[name.length];
        Float[] degLongitude=new Float[name.length];
/**
 * Get latitude
 */
        try {
            for (int i = 0; i < name.length; i++) {

                Process process = Runtime.getRuntime().exec("exiftool.exe" + " " + "\"" + "src//Fotos//" + name[i] + "\"" + " -gpslatitude -b");
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                latitude[i] =reader.readLine();
                if(latitude[i] != null){
                    degLatitude[i]=Float.parseFloat(latitude[i]);

                }

/**
 * Get longitude
 */

                Process p = Runtime.getRuntime().exec("exiftool.exe" + " " + "\"" + "src//Fotos//" + name[i] + "\"" + " -gpslongitude -b");
                BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                longitude[i] = r.readLine();
                if(longitude[i] != null){
                    degLongitude[i]=Float.parseFloat(longitude[i]);

                }



            }
        }catch(IOException i){

        }


        /**
         * Creating the map images using the latitude and longitude obtained before
         */

        System.out.println("Creating maps...");
            InputStream inputStream = null;
            try {
                inputStream = new URL("http://maps.googleapis.com/maps/api/staticmap?center="+(degLatitude[0]+degLatitude[(degLatitude.length-1)])/2 + "," + (degLongitude[0]+degLongitude[(degLongitude.length-1)])/2 + "&size=800x800&sensor=true&zoom=5&path=color:red|weight:5|"+degLatitude[0]+","+degLongitude[0]+"|"+degLatitude[(degLatitude.length-1)]+","+degLongitude[(degLongitude.length-1)]+"&key=AIzaSyAouaA8oFY88W6hXEMe0h7ZVpkWLqJcJMc").openStream();
                Files.copy(inputStream, Paths.get("src//Fotos//MapWithPath.jpg"));
            } catch (IOException e) {
                System.out.println("Exception Occurred " + e);
            } catch (NullPointerException e){

            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {

                    }
                }

            }

            for (int i=0; i<name.length;i++){
                float longitudeCenter=0;
                float latitudeCenter=0;
                int loop=0;
                if(longitude[i]!=null) {

                    for (int j = 0; j < longitude.length; j++) {
                        longitudeCenter = longitudeCenter + degLongitude[i];
                        latitudeCenter = latitudeCenter + degLatitude[i];
                        loop++;
                    }
                }
                    longitudeCenter=longitudeCenter/loop;
                    latitudeCenter=latitudeCenter/loop;
                    try {
                        inputStream = new URL("http://maps.googleapis.com/maps/api/staticmap?center="+latitudeCenter+","+longitudeCenter+"&size=800x800&sensor=true&zoom=12&markers="+degLatitude[0]+","+degLongitude[0]+"|"+degLatitude[1]+","+degLongitude[1]+"|"+degLatitude[2]+","+degLongitude[2]+"|"+degLatitude[3]+","+degLongitude[3]+"|"+degLatitude[4]+","+degLongitude[4]+"&key=AIzaSyAouaA8oFY88W6hXEMe0h7ZVpkWLqJcJMc").openStream();
                        Files.copy(inputStream, Paths.get("src//Fotos//MapWhithMarks.jpg"));
                    } catch (IOException e) {

                    } finally {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e) {

                            }
                        }

                    }
                }



    }
}
