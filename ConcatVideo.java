

import java.io.FileWriter;
import java.io.IOException;

/**
 * @class ConcatVideo
 * @function EndVideo(), join al video files into only one video using ffmpeg
 */

public class ConcatVideo {


    public void EndVideo(String[] finalNames) {

        /**
         * Converting the map images to videos
         * Writing path and name of videos into a .txt file
         */
        System.out.println("Joining videos");
        try {
            Process convertToImage= Runtime.getRuntime().exec("ffmpeg -loop 1 -i src//Fotos//MapWithPath.jpg  -t 2 -pix_fmt yuv420p -vf \"transpose=0\" src//Fotos//MapWithPath.mp4");

        }catch (IOException io){
        }

        try {
            FileWriter myWriter = new FileWriter("path.txt");
            myWriter.write("file 'src//Fotos//MapWithPath.mp4'\n");
            for (int i = 0; i < finalNames.length; i++) {

                myWriter.write("file 'src//Fotos//" + finalNames[i] + ".mp4'\n");
            }
            try {
                Process convertToImage= Runtime.getRuntime().exec("ffmpeg -loop 1 -i src//Fotos//MapWhithMarks.jpg  -t 2 -pix_fmt yuv420p -vf \"transpose=0\" src//Fotos//MapWhithMarks.mp4");
            }catch (IOException io){
            }
           myWriter.write("file 'src//Fotos//MapWhithMarks.mp4'\n");
            myWriter.close();
        } catch (IOException e) {
        }catch (NullPointerException n){

        }

/**
 * concatenate ffmpeg function
 */
        try {
            Process finalVideo = Runtime.getRuntime().exec("ffmpeg -f concat -safe 0 -i path.txt -c:v libx264  -c copy src//Fotos//Final.mp4");

        } catch (IOException ex) {
            ex.printStackTrace();
        }



    }
}
