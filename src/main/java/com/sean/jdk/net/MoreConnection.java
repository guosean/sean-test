package com.sean.jdk.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MoreConnection
{
    private static void closeSocket(Socket socket)
    {
        if (socket != null)
            try
            {
                socket.close();
            }
            catch (Exception e) { }
    }

    public static void main(String[] args)
    {
        Socket socket1 = null, socket2 = null;
        try
        {
            // 如果将www.ptpress.com.cn改成其它不存在的域名，将抛出UnknownHostException错误
            // 测试public Socket(String host, int port)
            socket1 = new Socket("www.ptpress.com.cn", 80);
            System.out.println("socket1连接成功!");
            // 测试public Socket(InetAddress inetaddress, int port)
            socket2 = new Socket(InetAddress.getByName("www.ptpress.com.cn"), 80);
            System.out.println("socket2连接成功!");

        }
        catch (UnknownHostException e)
        {
            System.out.println("UnknownHostException 被抛出!");
        }
        catch (IOException e)
        {
            System.out.println("IOException 被抛出!");
        }
        finally
        {
            closeSocket(socket1);
            closeSocket(socket2);
        }
    }
}
