package sv.edu.ufg.ejemplos.procesar.formulario.resources;

import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import sv.edu.ufg.ejemplos.procesar.formulario.utils.FileUtil;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import javax.inject.Inject;
import org.jboss.logging.Logger;

@Path("procesar")
public class FormularioResource {
    
    @Inject
    FileUtil fileUtil;

    /*public static class Person {
        public String firstName;
        public String lastName;
        public String email;
    }*/
    private static final Logger LOG = Logger.getLogger(FormularioResource.class);

    @POST
    @Produces(MediaType.TEXT_HTML)
    public Response multipart(
            @RestForm("photo") FileUpload file,
            @RestForm String firstName, @RestForm String lastName, @RestForm String email) {

        if(file!=null){
            try {
                fileUtil.escribirImagen(file);
            }catch (IOException e){
                LOG.error("Error escribiendo el archivo: ",e);
            }
        }else {
            LOG.error("No se subio el archivo");
        }

        StringBuilder sb = new StringBuilder("");
        sb.append("<html>");
        sb.append("<head><title>Reporte de Persona</title></head>");
        sb.append("<body>");

        sb.append("<ul>");
        sb.append("<li>Nombre: " + firstName + "</li>");
        sb.append("<li>Apellido: " + lastName + "</li>");
        sb.append("<li>E-mail: " + email + "</li>");
        sb.append("</ul>");

        sb.append("</body>");
        sb.append("</html>");
        LOG.info("Se escribio el HTML");

        return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
    }

}
