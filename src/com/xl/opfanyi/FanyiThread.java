package com.xl.opfanyi;
import android.os.Handler;
import com.xl.game.tool.HttpUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import android.os.Message;

public class FanyiThread extends Thread
{
	private String url = "http://app.yzjlb.net/qq/fanyi.txt.php";
	private String text =null;
	private String output=null;
	Handler handler;
	OnFanyiListener listener;
	
	public FanyiThread(final OnFanyiListener listener){
		super();
		this.listener = listener;
		handler = new Handler(){
			public void handleMessage(android.os.Message msg) {
				if(msg.obj!=null){
					if(listener!=null){
						listener.onOutString((String)msg.obj);
					}
				}
			}
		};
		
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public void setListener(OnFanyiListener listener){
	this.listener = listener;
}	
	public void run(){
		StringBuilder rebuf=new StringBuilder();
		if(text!=null){
			int start=0;
			int num=0;
			String temp=null;
			String msg=null;
			StringBuffer buf=new StringBuffer();
			if(text.length()>=64){
			for(int i=0;i<text.length();i++){
				char c = text.charAt(i);
				num++;
				buf.append(c);
				
				if(num>20 && (c=='\n' || c=='\r' || c=='.' || i==text.length()-1)){
					try
					{
						num=0;
						msg = URLEncoder.encode(buf.toString(), "UTF-8");
						buf = new StringBuffer();
						temp = HttpUtil.get(url+"?msg="+msg);
						rebuf.append(temp+c);
					}
					catch (UnsupportedEncodingException e)
					{
						e.printStackTrace();
					}
				}
			}
			}
			else{
				try
				{
					msg = URLEncoder.encode(text, "UTF-8");
				}
				catch (UnsupportedEncodingException e)
				{
					e.printStackTrace();
				}
				
				temp = HttpUtil.get(url+"?msg="+msg);
				rebuf.append(temp);
			}
			
		}
		output = rebuf.toString();
		Message m = new Message();
		m.obj = output;
		handler.sendMessage(m);
	}
	
	
	public interface OnFanyiListener{
		public void onOutString(String text);
	}
	
	
}
