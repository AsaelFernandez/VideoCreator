import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @class MakeVideo
 * @function ImgToVideo() search al the image files and convert them to video files with exif
 *
 */


public class MakeVideo {

    public String[] ImgToVideo( String[] newNames){
        System.out.println("Converting images to videos...");
        FilesImage files= new FilesImage();
        String[] names= files.GetMultimedia();
        String[] imgExtensions={"png","jpg","bmp"};
        String[] vidExtensions={"mp4","mkv"};
        String[] orientation = new String[names.length];
        String[] extension=new String[names.length];
        String[] concatNames = new String[names.length];
        String[] nameExt= names;



        try {
            for (int i = 0; i < names.length; i++) {


                if(names[i].contains(".") &&  names[i].lastIndexOf(".")!=0){
                    extension[i]=names[i].substring(names[i].lastIndexOf(".")+1);
                }
                for (int j = 0 ; j<imgExtensions.length; j++){
                    /**
                     * Looks for image extensions
                     */
                    if (names[i].endsWith(imgExtensions[j])) {
                        Process process = Runtime.getRuntime().exec("exiftool.exe" + " " + "\"" + "src//Fotos//" + names[i] + "\"" + " -orientation");
                        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        orientation[i]=reader.readLine().substring(34,44);
                        concatNames[i]=newNames[i]+""+newNames[i];
/**
 * Commands to convert the image depending on its orientation
 */

                        if(orientation[i].equals("Horizontal")){
                           Process convertToImage= Runtime.getRuntime().exec("ffmpeg -loop 1 -i src//Fotos//"+names[i]+"  -t 2 -pix_fmt yuv420p src//Fotos//"+concatNames[i]+".mp4");

                        }
                        else if(orientation[i].equals("Rotate 90 ")){
                           Process convertToImage= Runtime.getRuntime().exec("ffmpeg -loop 1 -i src//Fotos//"+names[i]+"  -t 2 -pix_fmt yuv420p -vf \"transpose=1\" src//Fotos//"+concatNames[i]+".mp4");


                        }
                        else if(orientation[i].equals("Rotate 270")){
                           Process convertToImage= Runtime.getRuntime().exec("ffmpeg -loop 1 -i src//Fotos//"+names[i]+"  -t 2 -pix_fmt yuv420p -vf \"transpose=2\" src//Fotos//"+concatNames[i]+".mp4");


                        }
                        else{
                           Process convertToImage= Runtime.getRuntime().exec("ffmpeg -loop 1 -i src//Fotos//"+names[i]+"  -t 2 -pix_fmt yuv420p -vf \"transpose=2,transpose=2\" src//Fotos//"+concatNames[i]+".mp4");


                        }
                    }


                    }
                for(int j=0; j<vidExtensions.length;j++) {
                    if (names[i].endsWith(vidExtensions[j])) {
                        concatNames[i] = newNames[i] +""+ newNames[i];
                       Process convertToImage= Runtime.getRuntime().exec("ffmpeg  -i src//Fotos//"+names[i]+" -map 0 -c:a copy src//Fotos//"+concatNames[i]+".mp4");


                    }
                }

            }


            return  concatNames;
        }catch (IOException e){
        return null;
        }catch (NullPointerException NPE){
            return null;
    }


    }
}
