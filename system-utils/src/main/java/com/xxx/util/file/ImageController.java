/*package com.xxx.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/imgage")
public class ImageController {
	
	@RequestMapping(value = "/{id:.*}", method = RequestMethod.GET)
	public void writeImgToResponse(@PathVariable("id") String id, HttpServletRequest request,
//		HttpServletResponse response) throws Exception {
//		String fullPath = FilenameUtils.concat("E://contract/template/img", id);
//		File image = new File(fullPath);
//		FileInputStream inputStream = new FileInputStream(image);
//		int length = inputStream.available();
//		byte data[] = new byte[length];
//		response.setContentLength(length);
//		inputStream.read(data);
//		OutputStream toClient = response.getOutputStream();
//		toClient.write(data);
//		toClient.flush();
//		IOUtils.closeQuietly(toClient);
//		IOUtils.closeQuietly(inputStream);
	}
}

*/