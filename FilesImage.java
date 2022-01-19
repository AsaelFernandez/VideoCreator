import java.io.File;

/**
 * @class FilesImage with two functions
 * @function GetFiles read the dir and create a list with its documents
 * @File[] File array
 * @function GetMultimedia, it works as a filter and ignore the ones not accepted
 *
 *
 */
public class FilesImage {


    public File[] GetFiles(){
        File folder = new File("src\\Fotos");
        File[] listOfFiles = folder.listFiles();
        return listOfFiles;
    }
    public String[] GetMultimedia(){
        System.out.println("Getting files...");
        File[] name =GetFiles();
        String[] names = new String[name.length];
        String[] multimedia = new String[name.length];
        String[] extensions= {".jpg", ".png", ".mp4",".mkv",".bmp" };
        int sizeOfFiles=0;


        try {
            for (int i = 0; i < name.length; i++) {
                names[i] = name[i].getName();
            }

            for (int i = 0; i < name.length; i++) {
                for(int j=0; j<extensions.length;j++) {
                    if (names[i].endsWith(extensions[j])){
                        multimedia[i] = names[i];
                        sizeOfFiles++;
                    }
                }
            }
            String[] justMultimediaFiles=new String[sizeOfFiles];

            for(int i=0; i<sizeOfFiles;i++){
                justMultimediaFiles[i]=multimedia[i];
            }

            return justMultimediaFiles;


        } catch (NullPointerException e) {
            return null;
        }



    }
}
