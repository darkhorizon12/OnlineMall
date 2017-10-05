package org.juon.configurations;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.juon.jpashop.domain.item.Item;
import org.juon.jpashop.repository.ItemRepository;
import org.springframework.web.context.WebApplicationContext;

import com.mysema.commons.lang.URLEncoder;

public class DownloadServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static final String baseDir = "/download/";
	
	private ItemRepository itemRepository;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebApplicationContext _applicationContext = (WebApplicationContext) getServletConfig().getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		itemRepository = (ItemRepository) _applicationContext.getBean("itemRepository");
		final String directory = getServletContext().getRealPath("/upload-dir");
		int idx = req.getRequestURI().indexOf(baseDir) + baseDir.length();
		String imgId = req.getRequestURI().substring(idx);
		System.out.println("IMG ID = > " + imgId);
		Item item = itemRepository.findOne(Long.valueOf(imgId));
		System.out.println("ITEM === > " + item);
		
		File file = new File(directory, item.getFilename());
		if (!file.exists()) {
			resp.sendError(404);
		} else {
			file = new File(directory, URLDecoder.decode(file.getName(), "UTF-8"));
			resp.reset();
			
			if (req.getHeader("User-Agent").toLowerCase().indexOf("firefox") != -1) {
				resp.setHeader("Content-Type", "application/download");
				resp.setHeader("Content-Length", ""+file.length());
				resp.setHeader("Content-Disposition", "attachment; filename=\"" + new String(item.getOrgFilename().getBytes("UTF-8"), "ISO-8859-1") + "\"");
			} else {
				//resp.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(name,"UTF-8"));
				resp.setHeader("Pragma", "public");
				resp.setHeader("Expires", "0");
				resp.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				resp.setHeader("Content-Type", "application-download");
				resp.setHeader("Content-Length", ""+file.length());
				resp.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encodeParam(item.getOrgFilename(), "UTF-8") + "\";");
				resp.setHeader("Content-Transfer-Encoding", "binary");

			}
			
			BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream outs = new BufferedOutputStream(resp.getOutputStream());
			int leng = 0;
			byte b[] = new byte[4096];
			while( (leng = fin.read(b, 0, 4096)) != -1 ){
				outs.write(b,0,leng);
			}
			outs.flush();
	   		outs.close();
	   		fin.close();
			
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	

}
