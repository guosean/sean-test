package com.zip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.utils.file.FileUtils;

public final class EncryptZipUtil {

    public static byte[] getEncryptZipByte(File[] srcfile,String password) {
    	
            ByteArrayOutputStream tempOStream = new ByteArrayOutputStream(1024);
                  byte[] tempBytes = null;
                  byte[] buf = new byte[1024];
                  try {
                          EncryptZipOutput out = new EncryptZipOutput(tempOStream,password);
                          for (int i = 0; i < srcfile.length; i++) {
                                  FileInputStream in = new FileInputStream(srcfile[i]);
                                  out.putNextEntry(new EncryptZipEntry(srcfile[i].getName()));
//                                out.putNextEntry(new EncryptZipEntry(StringUtil.encodeStr2GBK(srcfile[i].getName())));
                                  int len;
                                  while ((len = in.read(buf)) > 0) {
                                          out.write(buf, 0, len);
                                  }
                                  out.closeEntry();
                                  in.close();
                          }
                          tempOStream.flush();
                          out.close();
                          tempBytes = tempOStream.toByteArray();
                          tempOStream.close();
                  } catch (IOException e) {
                          e.printStackTrace();
                  }

                  return tempBytes;
    }
    
    public static void unzipFiles(byte[] zipBytes, String password, String dir) throws IOException {
        InputStream bais = new ByteArrayInputStream(zipBytes);
        EncryptZipInput zin = new EncryptZipInput(bais,password);
        EncryptZipEntry ze;             
        while ((ze = zin.getNextEntry()) != null) {
                ByteArrayOutputStream toScan = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int len;
                while ((len = zin.read(buf)) > 0) {
                        toScan.write(buf, 0, len);
                }
                byte[] fileOut = toScan.toByteArray();
                toScan.close();                 
                FileUtils.writeByteFile(fileOut, new File(dir+File.separator+ze.getName()));
                
        }
        zin.close();
        bais.close();
}
    
}
