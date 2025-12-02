
package com.spark.lms.courseservice.util;

import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class S3Util {

    private final Path uploadRoot;

    public S3Util() {
        this.uploadRoot = Paths.get("uploads").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.uploadRoot);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory", e);
        }
    }

    /**
     * Upload file to local uploads folder (DEV). Returns a URL string that can be used by clients.
     * Replace this method with AWS S3 upload code when moving to cloud.
     */
    public String uploadFile(MultipartFile file, String key) throws IOException {

        // key could contain directories: course_1/material_...
        Path target = this.uploadRoot.resolve(key).normalize();
        Files.createDirectories(target.getParent());

        try (InputStream is = file.getInputStream()) {
            Files.copy(is, target);
        }

        // Build a URL to access the file. For local dev we return filesystem url or pseudo-http.
        // If you have a static resource mapping to 'uploads' in Spring, you can return http://host/uploads/{key}
        // For now return absolute file URI:
        URI uri = target.toUri();
        return uri.toString();
    }

    // Optional helper to get a UrlResource (if you want to serve files via controller)
    public UrlResource getFileAsResource(String key) throws MalformedURLException {
        Path filePath = this.uploadRoot.resolve(key).normalize();
        return new UrlResource(filePath.toUri());
    }
}
