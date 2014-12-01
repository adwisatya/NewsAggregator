

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adwisatya
 */
import gui.engine;
 import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet to handle File upload request from Client
 * @author Javin Paul
 */
public class FileUploadHandler extends HttpServlet {
    private final String UPLOAD_DIRECTORY = "D:/";
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		HttpServletRequest tmpReq;
		tmpReq = request;
		String islink = tmpReq.getParameter("islink");
        //process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);
              
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                    }
                }
           
               //File uploaded successfully
               request.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }          
         
        }else{
			//Bagian ini yang bakal nanganin input form selain upload file.COntoh dibawah ini adalah ngasih hasil klasifikasi teks "sdasdsa" terus diparsing ke index.jsp
            //request.setAttribute("message", tmpReq.getParameter("textInput"));
			if(islink.contains("satu")){
				request.setAttribute("message", engine.getKonten((tmpReq.getParameter("textInput"))));
			}else{
				request.setAttribute("message", tmpReq.getParameter("textInput"));
			}
        }
    
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
  
}

