package jp.co.topgate.sugawara.web;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStreamReader;

/*
 * This Java source file was generated by the Gradle 'init' task.
 * HttpServer class
 * connectionを管理する
 *
 * @author sakura818
 *
 */
public class HttpServer {

    private static final int PORT = 8086;// Httpサーバでは一般的にポート番号として80番もしくは8080番を使用する
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private String request;
    private String appendRequest;

    public String getRequest() {
        return this.request;
    }
    public String getAppendRequest() {return this.appendRequest;}


    public void connection() throws IOException {
        System.out.println("start up http server http://localhost:" + this.PORT);
        try (ServerSocket serverSocket = new ServerSocket(this.PORT)) {

            while (true) {

                this.socket = serverSocket.accept();
                System.out.println("request incoming");

                /**
                 * Requestの処理
                 * クライアントとの入出力の時点ではバイト列として扱い、内部処理ではStringに変換して操作する
                 *
                 */
                System.out.println("request...");
                InputStream is = this.socket.getInputStream();
                //BufferedInputStream bis = new BufferedInputStream(is);
                BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(is)));
                StringBuilder sb = new StringBuilder();
                while (!(request = br.readLine()).equals("")) {
                    if (request == null) {
                        System.out.println("requestはnullです");
                    }
                    System.out.println(request);
                    sb.append(request);
                    String appendRequest = new String(sb);
                }
                //String appendRequest = new String(sb);
                System.out.println("---------------------------------------");


                /**
                 * Responseの処理
                 * クライアントとの入出力の時点ではバイト列として扱い、内部処理ではStringに変換して操作する
                 *
                 */
                OutputStream outputStream = this.socket.getOutputStream();
                HttpResponse httpResponse = new HttpResponse();
                httpResponse.generateHttpResponse();

            }
        } catch (IOException e) {
            System.out.println("正常にコネクションできないエラーが発生しました");
            e.printStackTrace();
        } finally {
            this.socket.close();


        }
    }

}




