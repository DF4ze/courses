package org.df4ze.course2.mdl;

import java.io.ByteArrayOutputStream;
//import org.apache.commons.io.output.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import org.archive.io.ArchiveRecord;
import org.archive.io.ArchiveRecordHeader;
import org.archive.io.warc.WARCReaderFactory;
import org.df4ze.course2.dao.bean.BeansList;
import org.df4ze.course2.dao.factory.DAOFactory;


public class WarcReader {

	private File arcDir = null;
	
	public WarcReader(File arcDir) throws FileNotFoundException {
		if( !arcDir.exists() )
			throw new FileNotFoundException();
		this.arcDir = arcDir;
	}

	protected ArrayList<File> getArcFiles(){
		File arcFiles[] = arcDir.listFiles(new FilenameFilter() 
		{
			public boolean accept(File dir, String name) 
			{
				return name.endsWith(".warc.gz") /*|| name.endsWith(".warc")*/;
			}
		});
		
		return new ArrayList<File>(  Arrays.asList(arcFiles) );
	}
	
	public HashMap<String, String> getURLs(  ){
		HashMap<String, String> URLS = new HashMap<String, String>();
		
		ArrayList<File> arcFiles = getArcFiles();
		for (File arcFile : arcFiles){
			System.out.println("Reading " + arcFile.getName());
			Logger.write("\n\nReading " + arcFile.getName());
			
//			ArchiveReader r;
//			ARCReader r;
			
			try {
				
				Iterator<ArchiveRecord> archIt = WARCReaderFactory.get(arcFile).iterator();
				while (archIt.hasNext()) {
					ArchiveRecord rec = archIt.next();
				
				
//				r = ARCReaderFactory.get(null, new FileInputStream(arcFile), true);
//				//r = ArchiveReaderFactory.get(arcFile);
//				r.setDigest(false);				
				
//				for (ArchiveRecord rec : r){
					
					if (rec != null){
						ArchiveRecordHeader meta = rec.getHeader();
						if (meta.getMimetype().trim().indexOf("request") != -1 && meta.getMimetype().trim().startsWith("application/http")) {
							
//							String referer="root";
//							String temp = getReferer(rec);
//							if( temp != null )
//								referer = temp;
//							
//							URLS.put(meta.getUrl(), referer);

						}else if(meta.getMimetype().trim().indexOf("msgtype=response"/*request"*/) != -1 && meta.getMimetype().trim().startsWith("application/http")){
							indexify(rec);
							
						}
							
					}
				}
		
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return URLS;
	}
	
	public String getReferer( ArchiveRecord rec ){
		ByteArrayOutputStream baosRequest = new ByteArrayOutputStream();
		String key = "Referer: ";
		String referer=null;
		
		try {
			rec.dump(baosRequest);
			
			String fullHeader =  baosRequest.toString("ISO-8859-1");

			
			int pos0 = fullHeader.indexOf(key);
			if( pos0 != -1 ){
				int pos1 = fullHeader.indexOf("\n", pos0);
				if( pos1 != -1 ){
					referer = fullHeader.substring(pos0+key.length(), pos1).trim();
				}
			}
		} catch (IOException e) {
			
			System.err.println("Error reading Record : "+e.getMessage());
		}

		return referer;
	}
	
	protected void indexify( ArchiveRecord rec ){
		//System.out.println("***************************");
		
		//System.out.println(at.ArchiveBody);
		try{
			ByteArrayOutputStream baosRequest = new ByteArrayOutputStream();
			rec.dump(baosRequest);
			ArchiveTransformeur at = new ArchiveTransformeur(baosRequest, null);
			
			
			// prepa des var
			String url = rec.getHeader().getUrl();
			
			Parser parser = new Parser(url, at.ArchiveBody);
			
			BeansList beansList = parser.parse();
			
			
			DAOFactory.getDAOFactory().recordSet(beansList);

		} catch (IOException e) {
			System.err.println("Error reading Record : "+e.getMessage());
			
		}
	}
	
}
