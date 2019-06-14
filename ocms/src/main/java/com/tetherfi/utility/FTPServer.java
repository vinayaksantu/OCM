package com.tetherfi.utility;

import java.io.File;
import java.io.IOException;

import com.tetherfi.constants.Constants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.UserAuthenticator;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.auth.StaticUserAuthenticator;
import org.apache.commons.vfs2.impl.DefaultFileSystemConfigBuilder;

public class FTPServer {

    public void transferFileFromRemote(String remoteFileLocation, String destinationFileLocation) {

        File f = new File(destinationFileLocation);
        try {
            if(!f.exists())
            {
                System.out.println("File : "+destinationFileLocation +" does not exist. transferring file from : "+ remoteFileLocation+" to: "+destinationFileLocation);
            }
            else
                System.out.println("File : "+destinationFileLocation +" exists. Transferring(override) file from : "+ remoteFileLocation+" to: "+destinationFileLocation);

            UserAuthenticator auth = new StaticUserAuthenticator(Constants.ftp_domain, Constants.ftp_userName, Constants.ftp_password);
            FileSystemOptions opts = new FileSystemOptions();
            DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth);
            FileObject fo = VFS.getManager().resolveFile(remoteFileLocation, opts);
            FileUtils.copyInputStreamToFile(fo.getContent().getInputStream(),f);
            if(f.exists())
            {
                System.out.println("File transfer from : "+ remoteFileLocation+" to: "+destinationFileLocation+" is successful");
            }
        }

        catch (FileSystemException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
        public void DeleteFilesFromFolder(String filepath) throws Exception {
        	UserAuthenticator auth = new StaticUserAuthenticator(Constants.ftp_domain, Constants.ftp_userName, Constants.ftp_password);
            FileSystemOptions opts = new FileSystemOptions();
            DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth);
        	File folder = new File(filepath);
    		for (final File f : folder.listFiles()) {
    		        f.delete();
    		    }
    		}

		public Boolean FileExist(String filePath) throws Exception {
			UserAuthenticator auth = new StaticUserAuthenticator(Constants.ftp_domain, Constants.ftp_userName, Constants.ftp_password);
            FileSystemOptions opts = new FileSystemOptions();
            DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth);
        	File folder = new File(filePath);
        	if(folder.list().length>0)
        		return true;
        	else
			return false;
		}
		
		public Boolean FolderExist(String filepath) throws Exception {
			UserAuthenticator auth = new StaticUserAuthenticator(Constants.ftp_domain, Constants.ftp_userName, Constants.ftp_password);
            FileSystemOptions opts = new FileSystemOptions();
            DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth);
        	File folder = new File(filepath);
        	if(folder.exists())
        		return true;
        	else
			return false;
			
		}
}