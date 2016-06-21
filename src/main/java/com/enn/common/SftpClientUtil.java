package com.enn.common;
/**
 * 描述：sftp服务器文件上传下载类
 *
 * @author jss
 * @version 1.0, 2012-12-03
 */

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Vector;


public class SftpClientUtil {

    private static Logger logger = LoggerFactory.getLogger(SftpClientUtil.class);

    /**
     * 连接sftp服务器
     * @param host 主机
     * @param port 端口
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public static ChannelSftp connect(String host, int port, String username, String password) {
        ChannelSftp sftp = null;
        try {
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            Session sshSession = jsch.getSession(username, host, port);
            logger.info("创建会话");
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            logger.info("会话连接");
            logger.info("开通通道");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            logger.info("连接到" + host + ".");
        } catch (Exception e) {
            logger.info("sftp服务器连接失败");
        }
        return sftp;
    }

    /**
     * 上传文件
     * @param directory 上传的目录
     * @param uploadFile 要上传的文件
     */
    public static void upload(String host, int port, String username, String password, String directory, String uploadFile) {
        try {
            ChannelSftp sftp = connect(host, port, username, password);
            sftp.cd(directory);
            File file = new File(uploadFile);
            sftp.put(new FileInputStream(file), file.getName());
            logger.info("文件上传成功！");
        } catch (Exception e) {
            logger.info("文件上传失败");
            logger.error("error", e);
        }
    }

    /**
     * 删除文件
     * @param directory 要删除文件所在目录
     * @param deleteFile 要删除的文件
     * @param sftp
     */
    public void delete(String directory, String deleteFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        } catch (Exception e) {
            logger.error("error", e);
        }
    }

    /**
     * 列出目录下的文件
     * @param directory 要列出的目录
     * @param sftp
     * @return
     * @throws SftpException
     */
    public Vector listFiles(String directory, ChannelSftp sftp) throws SftpException {
        return sftp.ls(directory);
    }

    public static void mkdir(String path) {
        ChannelSftp sftp = connect("10.37.148.254", 22, "dev", "dev");
        try {
            sftp.mkdir(path);
        } catch (SftpException e) {
            e.printStackTrace();
        }
    }
}
