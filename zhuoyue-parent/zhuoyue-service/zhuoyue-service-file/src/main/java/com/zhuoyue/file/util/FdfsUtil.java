package com.zhuoyue.file.util;

import com.zhuoyue.file.FastFdsFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.net.InetSocketAddress;

public class FdfsUtil {

    static {
        try {
            ClientGlobal.init(new ClassPathResource("fdfs_client.conf").getPath());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param fastFdsFile
     * @return 返回文件的url
     * @throws IOException
     * @throws MyException
     */
    public static String upLoad(FastFdsFile fastFdsFile) throws IOException, MyException {
        StorageClient storageClient = new StorageClient(getTrackerServer(), null);
        String[] authors = storageClient.upload_file(null, fastFdsFile.getContent(), fastFdsFile.getExt(),
                new NameValuePair[]{
                        new NameValuePair("author", fastFdsFile.getAuthor())
                });
        InetSocketAddress inetSocketAddress = getStotage(authors[0]).getInetSocketAddress();
        // http://192.168.23.159:8080/group1/00/00/---.jpg
        return "http://" + inetSocketAddress.getHostString() + ":" + ClientGlobal.getG_tracker_http_port() + "/" + authors[0] + "/" + authors[1];
    }

    public static String trackerInfo(int index) {
        InetSocketAddress tracker_server = ClientGlobal.getG_tracker_group().tracker_servers[index];
        return tracker_server.getHostString() + ":" + tracker_server.getPort();
    }

    public static FileInfo getFile(String group, String filePath) throws IOException, MyException {
        StorageClient storageClient = new StorageClient(getTrackerServer(), null);
        return storageClient.get_file_info(group, filePath);
    }

    public static InputStream downLoad(String group, String remoteFile) throws IOException, MyException {
        StorageClient storageClient = new StorageClient(getTrackerServer(), null);
        return new ByteArrayInputStream(downLoadToArray(group, remoteFile));
    }

    public static byte[] downLoadToArray(String group, String remoteFile) throws IOException, MyException {
        StorageClient storageClient = new StorageClient(getTrackerServer(), null);
        return storageClient.download_file(group, remoteFile);
    }

    public static void delFile(String group, String remoteFile) throws IOException, MyException {
        StorageClient storageClient = new StorageClient(getTrackerServer(), null);
        storageClient.delete_file(group, remoteFile);
    }

    public static StorageServer getStotage(String group) throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        return trackerClient.getStoreStorage(getTrackerServer(), group);
    }

    /**
     * 获取TrackerServer
     *
     * @return
     * @throws IOException
     */
    public static TrackerServer getTrackerServer() throws IOException {
        return new TrackerClient().getConnection();
    }

    public static void main(String[] args) {
        try {
            FileInfo file = getFile("group1", "M00/00/00/wKgAal5-3uSATeVUAABl_0G4I1c696.jpg");
            delFile("group1", "M00/00/00/wKgAal5-3uSATeVUAABl_0G4I1c696.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
