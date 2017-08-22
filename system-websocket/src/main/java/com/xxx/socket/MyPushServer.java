package com.xxx.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyPushServer {
	
	 //存储连接ip  
    ArrayList<Socket> sockets = new ArrayList<Socket>();  
    //定时器  
    private ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();  
    //计数  
    private static int i=1;  
    //主程序  
    public static void main(String[] args) {  
        MyPushServer myPushServer = new MyPushServer();  
        try {  
            myPushServer.start();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
    //程序运行  
    public void start() throws IOException {  
        @SuppressWarnings("resource")  
        ServerSocket serverSocket = new ServerSocket(4000);  
        System.out.println("服务启动成功···");  
        while (true) {//长启动  
            Socket socket = serverSocket.accept();  
            System.out.println("server started... http:/"+ socket.getLocalAddress() + ":" + socket.getPort());  
            System.out.println(socket.getLocalSocketAddress() + ":"+ socket.getLocalPort());  
            sockets.add(socket);//添加ip列表  
            if(i == 1){//保证只需起来一次  
                synchronized (ServerSocket.class) {  
                    ServerThread serverThread = new ServerThread();  
                    serverThread.start();  
                }  
            }  
            i++;  
        }  
    }  
  
    //线程任务  
    class ServerThread extends Thread {  
        private BufferedReader reader;  
        private BufferedWriter writer;  
        private BufferedReader read;  
        public ServerThread() {  
            Runnable runnable = new Runnable() {    
                public void run() {    
                    //发送内容-流  
                    String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());  
                    InputStream inputStream = new ByteArrayInputStream(str.getBytes());  
                    reader = new BufferedReader(new InputStreamReader(inputStream));  
                }    
            };   
            // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间    
            // 第一次执行间隔为10毫秒，随后1000毫秒执行循环  
            service.scheduleAtFixedRate(runnable, 10, 1000, TimeUnit.MILLISECONDS);    
        }  
        @Override  
        public void run() {  
            String content = null;//发送内容  
            String getContent = null;//返回内容  
            while (true) {  
                try {  
                    if ((content = reader.readLine()) != null) {  
                        for (Socket socket : sockets) {  
                            System.out.println("socket:"+socket);  
                            try{     
                                socket.sendUrgentData(0xFF);  //验证连接是否断开，以忽悠为一个字节的发送进行验证  
                                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));  
                                writer.write(content);  
                                writer.newLine();  
                                writer.flush();//发送  
                                read = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
                                if((getContent = read.readLine()) != null) {  
                                    System.out.println(getContent);  
                                }  
                            }catch(Exception ex){    
                                sockets.remove(socket);//移除断开的连接  
                                continue;  
                            }     
                        }  
                    }  
                } catch (Exception e) {  
                    e.getStackTrace();  
                }  
            }  
        }  
    }  

}
