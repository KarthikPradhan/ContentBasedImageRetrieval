package com.main.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.main.backend.Indexer;
import com.main.backend.Searcher;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String DATA_DIRECTORY = "data";
	private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("start of doPost");
		String filePath = null;
		Indexer ie = new Indexer();
		ie.indexingOfFiles("F:/LIRE/TestImages");
		System.out.println("Inside the upload Servlet");
		try {
			// Check that we have a file upload request
			boolean isMultipart = ServletFileUpload.isMultipartContent(req);

			if (!isMultipart) {
				return;
			}

			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// Sets the size threshold beyond which files are written directly
			// to
			// disk.
			factory.setSizeThreshold(MAX_MEMORY_SIZE);

			// Sets the directory used to temporarily store files that are
			// larger
			// than the configured size threshold. We use temporary directory
			// for
			// java
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

			// constructs the folder where uploaded file will be stored
			String uploadFolder = getServletContext().getRealPath("") + File.separator + DATA_DIRECTORY;

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// Set overall request size constraint
			upload.setSizeMax(MAX_REQUEST_SIZE);
			// Parse the request
			List items = upload.parseRequest(req);
			Iterator iter = items.iterator();

			System.out.println(uploadFolder);
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();

				if (!item.isFormField()) {
					String fileName = new File(item.getName()).getName();
					filePath = "F:/LIRE/TestImages/" + fileName;
					File uploadedFile = new File(filePath);
					System.out.println("file path: " + filePath);
					// saves the file to upload directory
					item.write(uploadedFile);
				}
			}

			Searcher sc = new Searcher();
			ArrayList<String> foundImgs = sc.searchImg(filePath);
			// displays done.jsp page after upload finished

			req.setAttribute("foundImgs", foundImgs);
			getServletContext().getRequestDispatcher("/project.jsp").forward(req, resp);

		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
}
