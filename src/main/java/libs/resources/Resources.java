package libs.resources;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

public class Resources {

    public URL getURL(String path) throws ResourceNotFoundException {
        URL url = ClassLoader.getSystemResource(path);
        if(url == null) {
            throw new ResourceNotFoundException(path);
        }
        else {
            return url;
        }
    }

    public URI getURI(String path) {
        try {
            return this.getURL(path).toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public File getFile(String path) {
        return new File(this.getURI(path));
    }

    public String readFile(String path) {
        try {
            String read = Files.readString(this.getFile(path).toPath());
//            System.out.println(read);
            return read;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
