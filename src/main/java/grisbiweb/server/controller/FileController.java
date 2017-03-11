package grisbiweb.server.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grisbiweb.server.service.GrisbiFileService;
import io.swagger.annotations.Api;
import lombok.SneakyThrows;

@RestController
@RequestMapping(path = "/files")
@Api(value = "/files")
public class FileController {

    @Autowired
    private GrisbiFileService grisbiFileService;

    @SneakyThrows
    @RequestMapping(value = "download", method = RequestMethod.GET)
    public void getDownload(HttpServletResponse response) {

        File file = grisbiFileService.getGrisbiFile();
        // Get your file stream from wherever.
        InputStream myStream = new FileInputStream(file);

        // Set the content type and attachment header.
        response.addHeader("Content-disposition", "attachment;filename=" + file.getName());
        response.setContentType("txt/plain");

        // Copy the stream to the response's output stream.
        IOUtils.copy(myStream, response.getOutputStream());
        response.flushBuffer();
    }
}
