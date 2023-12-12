package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;

public class UploadUtils {
	public static String processUpload(String fileName, HttpServletRequest req, String storeFolder, String storeFileName) throws IOException, ServletException {
		Part filePart = req.getPart(fileName);
		if(filePart == null || filePart.getSize() == 0)
			return "";
		
		if(storeFolder == null)
			storeFolder = "D:\\uploads";
		
		if(storeFileName == null)
			storeFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		else
			storeFileName += "." + FilenameUtils.getExtension(Paths.get(filePart.getSubmittedFileName()).getFileName().toString());
		
		Path uploadPath = Paths.get(storeFolder);
		
		if(!Files.exists(uploadPath))
			Files.createDirectories(uploadPath);
		
		filePart.write(Paths.get(uploadPath.toString(), storeFileName).toString());
		
		return storeFileName;
	}
	
	public static String processUploadFolderWeb(String fileName, HttpServletRequest req, String storeFolder, String storeFileName) throws IOException, ServletException {
		Part filePart = req.getPart(fileName);
		if(filePart == null || filePart.getSize() == 0)
			return "";
		
		if(storeFolder == null)
			storeFolder = "/uploads";
		
		if(storeFileName == null)
			storeFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		else
			storeFileName += "." + FilenameUtils.getExtension(Paths.get(filePart.getSubmittedFileName()).getFileName().toString());
		//Lấy đường dẫn trong web
		String uploadFolder = req.getServletContext().getRealPath(storeFolder);
		Path uploadPath = Paths.get(uploadFolder);
		
		if(!Files.exists(uploadPath))
			Files.createDirectories(uploadPath);
		
		filePart.write(Paths.get(uploadPath.toString(), storeFileName).toString());
		
		return storeFileName;
	}
}
