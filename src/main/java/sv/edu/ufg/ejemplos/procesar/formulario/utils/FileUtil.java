package sv.edu.ufg.ejemplos.procesar.formulario.utils;

import org.apache.commons.io.FileUtils;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {

    private static File folder = new File("C:\\UFG\\imagenes");

    public static void escribirImagen(FileUpload file) throws IOException {
        InputStream initialStream = FileUtils.openInputStream
                (file.uploadedFile().toFile());
        File targetFile = new File(folder,file.fileName());

        FileUtils.writeByteArrayToFile(targetFile,initialStream.readAllBytes());
        initialStream.close();


    }
}
