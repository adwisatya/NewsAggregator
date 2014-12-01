

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adwisatya
 */

import gui.Engine;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myta.engine.LearningEngine;
import myta.model.Berita;
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
		String update =  tmpReq.getParameter("update");
		String namafile= null;
        //process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);
              
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
						namafile = name;
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                    }
                }
           
               //File uploaded successfully
               request.setAttribute("message", "<a href=\"file:///D:/output.csv\">"+Engine.checkMulti("D:/"+namafile)+"</a>");
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }          
         
        }else{
			//Bagian ini yang bakal nanganin input form selain upload file.COntoh dibawah ini adalah ngasih hasil klasifikasi teks "sdasdsa" terus diparsing ke index.jsp
            //request.setAttribute("message", tmpReq.getParameter("textInput"));
			if(islink.contains("satu")){
				try {
					String judul = Engine.getJudul(tmpReq.getParameter("textInput"));
					String konten = Engine.getKontenX(tmpReq.getParameter("textInput"));
					request.setAttribute("judul", judul);
					request.setAttribute("konten", konten);
					request.setAttribute("message", Engine.getLabel(judul,konten));

					//request.setAttribute("message", Engine.getKonten((tmpReq.getParameter("textInput"))));
				} catch (Exception ex) {
					Logger.getLogger(FileUploadHandler.class.getName()).log(Level.SEVERE, null, ex);
				}
			}else if(update.contains("satu")){
				//prosedur update untuk perbaiki label
				String konten = request.getParameter("konten");
				String judul =  request.getParameter("judul");
				String label = request.getParameter("labelBenar");
				//update model
				Berita berita = new Berita();
				berita.setFullText(konten);
				berita.setJudul(judul);
				berita.setKategori(label);
				try {
					LearningEngine.getInstance().updateModel(berita);
				} catch (Exception ex) {
					Logger.getLogger(FileUploadHandler.class.getName()).log(Level.SEVERE, null, ex);
				}
				request.setAttribute("message", "Label sudah diupdate");
			}else{
				try {
					request.setAttribute("message", Engine.getLabel(tmpReq.getParameter("textInput"),tmpReq.getParameter("textInputKonten")));
					request.setAttribute("judul", tmpReq.getParameter("textInput"));
					request.setAttribute("konten", tmpReq.getParameter("textInputKonten"));
				} catch (Exception ex) {
					Logger.getLogger(FileUploadHandler.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
        }
    
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
  
}

