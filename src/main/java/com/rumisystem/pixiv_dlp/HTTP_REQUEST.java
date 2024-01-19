package com.rumisystem.pixiv_dlp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;

public class HTTP_REQUEST {
	private URL REQIEST_URI = null;

	public HTTP_REQUEST(String INPUT_REQ_URL){
		try{
			REQIEST_URI = new URL(INPUT_REQ_URL);
		}catch (Exception EX) {
			System.err.println(EX);
			System.exit(1);
		}
	}

	//GET
	public String GET(){
		try{
			HttpURLConnection HUC = (HttpURLConnection) REQIEST_URI.openConnection();

			//GETリクエストだと主張する
			HUC.setRequestMethod("GET");

			//ヘッダーを入れる
			HUC.setRequestProperty("Host", "i.pximg.net");
			HUC.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
			HUC.setRequestProperty("Referer", "https://www.pixiv.net/");

			//レスポンスコード
			int RES_CODE = HUC.getResponseCode();
			BufferedReader BR = new BufferedReader(new InputStreamReader(HUC.getInputStream(), StandardCharsets.UTF_8));
			StringBuilder RES_STRING = new StringBuilder();

			String INPUT_LINE;
			while ((INPUT_LINE = BR.readLine()) != null){
				RES_STRING.append(INPUT_LINE);
			}

			BR.close();

			return RES_STRING.toString();
		}catch (Exception EX){
			EX.printStackTrace();
			System.exit(1);
			return null;
		}
	}
}