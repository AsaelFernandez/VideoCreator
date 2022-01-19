import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @class ObtainDate
 * @function GetDate() looks with exif  the modification date of the files, save them in order
 * @String[] parameters, each with a part of the date
 * @time param with the date converted to a float
 */
public class ObtainDate {

    public String[] GetDate(String[] names){
        System.out.println("Searching creation date...");
        String[] dates= new String[names.length];
        String[] day=new String[names.length];
        String[] month=new String[names.length];
        String[] year=new String[names.length];
        String[] hour= new String[names.length];
        String[] min= new String[names.length];
        String[] sec= new String[names.length];
        long[] time=new long[names.length];
        long[] time2=new long[names.length];


        try {


            for (int i = 0; i < names.length; i++) {

                Process process= Runtime.getRuntime().exec("exiftool.exe"+" "+"\""+"src//Fotos//"+names[i]+"\""+" -filemodifydate");
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                dates[i] = reader.readLine();

                year[i]= dates[i].substring(34,38);
                month[i]=dates[i].substring(39,41);
                day[i]=dates[i].substring(42,44);
                hour[i]=dates[i].substring(45,47);
                min[i]=dates[i].substring(48,50);
                sec[i]=dates[i].substring(51,53);

                time[i]= Long.parseLong((year[i]+""+month[i]+""+day[i]+""+hour[i]+""+min[i]+""+sec[i]));
                time2[i]=time[i];
            }
        }catch (IOException I){
        }catch (NullPointerException NPE){}

        for (int i=0; i<names.length;i++){
            for(int j=(i+1);j < names.length;j++){
                if (time[i] > time[j]) {
                    long aux=time[i];
                    time[i]=time[j];
                    time[j]=aux;

                }
            }

        }


        for (int i=0; i<names.length;i++){
            int lap=1;
            for(int j=0; j<names.length;j++){
                if(time[i]==time2[j]){
                    names[i]=Integer.toString(lap);
                }
                else{
                    lap++;
                }
            }
        }

        return names;
    }
}
