

import java.io.*;
import java.util.Scanner;

/**
 * @main the principal class to call and control all the other classes
 */

public class Main {

    public static void main(String[] args) {
        FilesImage file= new FilesImage();
        ObtainDate date= new ObtainDate();
        MakeVideo Mk = new MakeVideo();
        ConcatVideo Cv= new ConcatVideo();
        Location loc= new Location();

        String[] name= file.GetMultimedia(); //with extensions
        String[] names = date.GetDate(name); //just name
        String[] newNames=Mk.ImgToVideo(names);
        loc.GpsPosition();
        Cv.EndVideo(newNames);






    }
}
