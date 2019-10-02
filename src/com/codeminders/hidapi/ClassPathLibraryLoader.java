package com.codeminders.hidapi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


public class ClassPathLibraryLoader {

    private static final String[] HID_LIB_NAMES = {
	        "/tmp/hidapilibs/linux/libhidapi-jni-64.so",
	        "/tmp/hidapilibs/linux/libhidapi-jni-32.so",
	        "/tmp/hidapilibs/mac/libhidapi-jni-64.jnilib",
	        "/tmp/hidapilibs/mac/libhidapi-jni-32.jnilib",
	        "/tmp/hidapilibs/win/hidapi-jni-64.dll",
	        "/tmp/hidapilibs/win/hidapi-jni-32.dll"
	};
	  
	public static boolean loadNativeHIDLibrary()
        {
		  boolean isHIDLibLoaded = false;
		  
    	  for(String path : HID_LIB_NAMES)
          {
		        try {
		                // have to use a stream
		                //InputStream in = ClassPathLibraryLoader.class.getResourceAsStream(path);
		                //if (in != null) {
		                	//try {
		                		/*
				                // always write to different location
								System.out.println("write file "+ path);
				                String tempName = path.substring(path.lastIndexOf('/') + 1);
				                File fileOut = File.createTempFile(tempName.substring(0, tempName.lastIndexOf('.')), tempName.substring(tempName.lastIndexOf('.'), tempName.length()));
				                //fileOut.deleteOnExit();
				                
				                OutputStream out = new FileOutputStream(fileOut);
				                byte[] buf = new byte[1024];
				                int len;
				                while ((len = in.read(buf)) > 0){            
				                	out.write(buf, 0, len);
				                }
				                
				                out.close();
				                */
								System.out.println("Runtime.getRuntime(" + path + ").load START");
				                Runtime.getRuntime().load(path);
								System.out.println("Runtime.getRuntime(" + path + ").load END");
				                isHIDLibLoaded = true;
		                	//} finally {
		                	//	in.close();
		                	//}
		                //}
		        } catch (Exception e) {
		        	  // ignore
					System.out.println("exception e");
					System.out.println(e.toString());
		        } catch (UnsatisfiedLinkError e) {
		        	  // ignore
					System.out.println("UnsatisfiedLinkError e");
					System.out.println(e.toString());
		        }
		        
		        if (isHIDLibLoaded) {
		        	break;
		        }
        }
    	  
    	return isHIDLibLoaded;  
    }

}
