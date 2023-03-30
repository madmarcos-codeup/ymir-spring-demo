package docrob.springdemo1.services;

import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Setter
// snatched from https://www.baeldung.com/java-convert-byte-array-to-multipartfile
public class CustomMultipartFile implements MultipartFile {

    private final byte[] input;
    private String name;
    private String originalName;
    private String contentType;

    public CustomMultipartFile(byte[] bytes) {
        this.input = bytes;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " " + originalName + " " + contentType;
    }

    @Override
    public String getOriginalFilename() {
        return originalName;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    //previous methods
    @Override
    public boolean isEmpty() {
        return input == null || input.length == 0;
    }

    @Override
    public long getSize() {
        return input.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return input;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(input);
    }

    @Override
    public void transferTo(File destination) throws IOException, IllegalStateException {
        try(FileOutputStream fos = new FileOutputStream(destination)) {
            fos.write(input);
        }
    }

    public void determineContentType(String namePart) {
        // TODO: there is certainly a library out in the world somewhere to do this for us...
        // get the extension
        String extension = namePart.substring(namePart.length() - 3, namePart.length());
        switch (extension.toLowerCase()) {
            case "gif":
                contentType = "image/gif";
                break;
            case "png":
                contentType = "image/png";
                break;
            case "jpg":
            case "jpeg":
                contentType = "image/jpeg";
                break;
            case "svg":
                contentType = "image/svg+xml";
                break;
            default:
                contentType = "text/plain";
        }
    }
}
