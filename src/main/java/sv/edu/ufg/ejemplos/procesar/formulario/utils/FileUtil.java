package sv.edu.ufg.ejemplos.procesar.formulario.utils;

import org.apache.commons.io.FileUtils;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class FileUtil {

    @ConfigProperty(name = "carpeta.imagenes")
    String carpetaImagenes;
    private File folder;

    @PostConstruct
    void init() {
        folder = new File(carpetaImagenes);
    }

    public void escribirImagen(FileUpload file) throws IOException {
        InputStream initialStream = FileUtils.openInputStream(file.uploadedFile().toFile());
        File targetFile = new File(folder, file.fileName());

        FileUtils.writeByteArrayToFile(targetFile, initialStream.readAllBytes());
        initialStream.close();

    }
}
